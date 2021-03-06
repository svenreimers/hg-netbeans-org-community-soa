/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */

package org.netbeans.modules.xslt.core.text.completion.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.netbeans.modules.xml.text.syntax.dom.Tag;
import org.netbeans.modules.xml.xam.Model.State;
import org.netbeans.modules.xslt.core.text.completion.XSLTCompletionConstants;
import org.netbeans.modules.xslt.core.text.completion.XSLTCompletionResultItem;
import org.netbeans.modules.xslt.core.text.completion.XSLTCompletionUtil;
import org.netbeans.modules.xslt.core.text.completion.XSLTEditorComponentHolder;
import org.netbeans.modules.xslt.core.text.completion.XSLTTemplateParameterResultItem;
import org.netbeans.modules.xslt.core.text.completion.XSLTUnnamedTemplateResultItem;
import org.netbeans.modules.xslt.model.Param;
import org.netbeans.modules.xslt.model.Stylesheet;
import org.netbeans.modules.xslt.model.Template;
import org.w3c.dom.Node;

/**
 * @author Alex Petrov (06.06.2008)
 */
public class HandlerWithParamName extends BaseCompletionHandler implements 
    XSLTCompletionConstants {
    @Override
    public List<XSLTCompletionResultItem> getResultItemList(
        XSLTEditorComponentHolder editorComponentHolder) {
        initHandler(editorComponentHolder);
        return getTemplatesParamNameList();
    }
    
    private List<XSLTCompletionResultItem> getTemplatesParamNameList() {
        if ((surroundTag == null) || (attributeName == null) || (xslModel == null)) 
            return Collections.emptyList();
        
        String tagName = surroundTag.getTagName();
        if (! tagName.contains(XSLT_TAG_NAME_WITH_PARAM))
            return Collections.emptyList();
        if (! attributeName.equals(XSLTCompletionUtil.ATTRIB_NAME))
            return Collections.emptyList();

        if ((xslModel != null) && (xslModel.getState().equals(State.NOT_WELL_FORMED))) {
            return getIncorrectDocumentResultItem();
        }
        
        Node parentTemplateNode = surroundTag.getParentNode();
        if (! (parentTemplateNode instanceof Tag)) return Collections.emptyList();
        
        tagName = ((Tag) parentTemplateNode).getTagName();
        if (tagName.contains(HandlerCallTemplateName.XSLT_TAG_NAME_CALL_TEMPLATE)) {
            String valueofAttributeName = ((Tag) parentTemplateNode).getAttribute(
                XSLTCompletionUtil.ATTRIB_NAME);
            if ((valueofAttributeName == null) || 
                (valueofAttributeName.length() < 1)) return Collections.emptyList();
                
            return getNamedTemplatesParamNameList(valueofAttributeName);
        } else if (tagName.contains(XSLT_TAG_NAME_APPLY_TEMPLATES)) {
            return getUnnamedTemplatesParamNameList();
        }
        return Collections.emptyList();
    }
    
    private List<XSLTCompletionResultItem> getNamedTemplatesParamNameList(
        String templateName) {
        if (templateName == null) return Collections.emptyList();
        
        Stylesheet stylesheet = xslModel.getStylesheet();
        List<Template> templateList = stylesheet.getChildren(Template.class);
        if (templateList.isEmpty()) return Collections.emptyList();

        Template namedTemplate = null;
        for (Template template : templateList) {
            QName valueofAttributeName = template.getName();
            if ((valueofAttributeName != null) &&
                (templateName.equals(valueofAttributeName.toString()))) {
                namedTemplate = template;
                break;
            }   
        }        
        if (namedTemplate == null) return Collections.emptyList();
        
        return getNamedTemplateParamNameList(new ArrayList<Template>(Arrays.asList(
                                             new Template[] {namedTemplate})));
    }
    
    private List<XSLTCompletionResultItem> getNamedTemplateParamNameList(
        List<Template> templateList) {
        if (templateList == null) return Collections.emptyList();
        
        List<XSLTCompletionResultItem> resultItemList = 
            new ArrayList<XSLTCompletionResultItem>();
        for (Template template : templateList) {
            List<Param> paramList = template.getChildren(Param.class);
            if ((paramList == null) || (paramList.isEmpty())) return Collections.emptyList();
            
            for (Param parameter : paramList) {
                QName valueofAttributeName = parameter.getName();
                if (valueofAttributeName != null) {
                    XSLTCompletionResultItem resultItem = new XSLTCompletionResultItem(
                        valueofAttributeName.toString(), document, caretOffset);
                    resultItem.setSortPriority(resultItemList.size());
                    resultItemList.add(resultItem);
                }                
            }
        }
        return resultItemList;
    }

    private List<XSLTCompletionResultItem> getUnnamedTemplatesParamNameList() {
        Stylesheet stylesheet = xslModel.getStylesheet();
        List<Template> templateList = stylesheet.getChildren(Template.class);
        if ((templateList == null) || (templateList.isEmpty())) 
            return Collections.emptyList();

        List<Template> unnamedTemplateList = new ArrayList<Template>();
        for (Template template : templateList) {
            String valueofAttributeMatch = template.getMatch();
            if (valueofAttributeMatch != null) {
                unnamedTemplateList.add(template);
            }   
        }        
        return getUnnamedTemplatesParamNameList(unnamedTemplateList);
    }
    
    private List<XSLTCompletionResultItem> getUnnamedTemplatesParamNameList(
        List<Template> templateList) {
        if (templateList == null) return Collections.emptyList();
        
        List<XSLTCompletionResultItem> resultItemList = 
            new ArrayList<XSLTCompletionResultItem>();
        for (Template template : templateList) {
            List<Param> paramList = template.getChildren(Param.class);
            if ((paramList == null) || (paramList.isEmpty())) continue;
                
            XSLTCompletionResultItem resultItem = XSLTUnnamedTemplateResultItem.create(
                template, document, caretOffset);
            if (resultItem == null) continue;
            
            resultItem.setSortPriority(resultItemList.size());
            resultItemList.add(resultItem);
            
            for (Param parameter : paramList) {
                resultItem = XSLTTemplateParameterResultItem.create(parameter, document, 
                    caretOffset);
                if (resultItem == null) continue;
                
                resultItem.setSortPriority(resultItemList.size());
                resultItemList.add(resultItem);
            }
        }
        return resultItemList;
   }
}