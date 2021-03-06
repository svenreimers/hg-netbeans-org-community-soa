/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */
package org.netbeans.modules.bpel.model.api.support;

import javax.xml.namespace.QName;
import org.netbeans.modules.xml.xpath.ext.schema.resolver.XPathSchemaContext;
import org.netbeans.modules.bpel.model.api.references.SchemaReference;
import org.netbeans.modules.bpel.model.ext.editor.api.PseudoComp;
import org.netbeans.modules.xml.schema.model.GlobalType;
import org.netbeans.modules.xml.xpath.ext.XPathException;
import org.netbeans.modules.xml.xpath.ext.XPathExpression;
import org.netbeans.modules.xml.xpath.ext.XPathModel;
import org.netbeans.modules.xml.xpath.ext.XPathSchemaContextHolder;
import org.netbeans.modules.xml.xpath.ext.spi.XPathCastResolver;
import org.netbeans.modules.xml.xpath.ext.spi.XPathPseudoComp;
import org.openide.ErrorManager;

/**
 * @author nk160297
 */
public class XPathPseudoCompImpl implements XPathPseudoComp {

    private PseudoComp mPseudoComp;
    private String myParentPathText;
    private GlobalType mType;
    private String mName;
    private String mNamespace;
    private boolean mIsAttribute;
    private XPathExpression mXPathParentExpression;
    private XPathCastResolver mXPathResolver;

    public static XPathExpression getExpression(PseudoComp pseudoComp,
            XPathCastResolver parentResolver) {
        String parentPathText = pseudoComp.getParentPath();
        XPathModel xPathModel = 
                BpelXPathModelFactory.create(pseudoComp, parentResolver);
        XPathExpression xPathExpr = null;
        try {
            xPathExpr = xPathModel.parseExpression(parentPathText);
        } catch (XPathException ex) {
            ErrorManager.getDefault().log(ErrorManager.WARNING,
                    "Unresolved XPath: " + parentPathText); //NOI18N

        }
        return xPathExpr;
    }
    
    public static XPathPseudoCompImpl convert(PseudoComp pseudoComp, 
            XPathCastResolver parentResolver) {
        SchemaReference<? extends GlobalType> gTypeRef = pseudoComp.getType();
        if (gTypeRef == null) {
            return null;
        }
        GlobalType gType = gTypeRef.get();
        String parentPathText = pseudoComp.getParentPath();
        //
        if (pseudoComp == null || parentPathText == null || parentPathText.length() == 0) {
            return null;
        }
        XPathPseudoCompImpl result = new XPathPseudoCompImpl();
        result.mPseudoComp = pseudoComp;
        result.myParentPathText = parentPathText;
        result.mType = gType;
        //
        QName qName = pseudoComp.getName();
        if (qName == null) {
            return null;
        }
        result.mName = qName.getLocalPart();
        result.mNamespace = qName.getNamespaceURI();
        //
        result.mIsAttribute = pseudoComp.isAttribute();
        result.mXPathResolver = parentResolver;
        //
        return result;
    }

    public static XPathPseudoCompImpl convert(PseudoComp pseudoComp) {
        SchemaReference<? extends GlobalType> gTypeRef = pseudoComp.getType();
        if (gTypeRef == null) {
            return null;
        }
        GlobalType gType = gTypeRef.get();
        String parentPathText = pseudoComp.getParentPath();
        //
        if (pseudoComp == null || parentPathText == null || parentPathText.length() == 0) {
            return null;
        }
        XPathPseudoCompImpl result = new XPathPseudoCompImpl();
        result.mPseudoComp = pseudoComp;
        result.myParentPathText = parentPathText;
        result.mType = gType;
        //
        QName qName = pseudoComp.getName();
        if (qName == null) {
            return null;
        }
        result.mName = qName.getLocalPart();
        result.mNamespace = qName.getNamespaceURI();
        //
        result.mIsAttribute = pseudoComp.isAttribute();
        //
        return result;
    }

    private XPathPseudoCompImpl() {
    }
    
    public XPathPseudoCompImpl(XPathExpression parent, GlobalType type, 
            String name, String namespace, boolean isAttribute) {
        mXPathParentExpression = parent;
        mType = type;
        mName = name;
        mNamespace = namespace;
        mIsAttribute = isAttribute;
    }
    
    public String getParentPathText() {
        if (myParentPathText == null) {
            assert mXPathParentExpression != null;
            myParentPathText = mXPathParentExpression.getExpressionString();
        }
        return myParentPathText;
    }

    public GlobalType getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    public String getNamespace() {
        return mNamespace;
    }
    
    public boolean isAttribute() {
        return mIsAttribute;
    }

    public XPathExpression getParentPathExpression() {
        if (mXPathParentExpression == null) {
            mXPathParentExpression = getExpression(mPseudoComp, mXPathResolver);
        }
        return mXPathParentExpression;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof XPathPseudoComp) {
            XPathPseudoComp otherPC = (XPathPseudoComp)other;
            if (otherPC.isAttribute() != this.isAttribute()) {
                return false;
            }
            if (!(otherPC.getName().equals(this.getName()))) {
                // different name
                return false;
            } 
            if (!(otherPC.getNamespace().equals(this.getNamespace()))) {
                // different namespace
                return false;
            } 
            if (!(otherPC.getType().equals(this.getType()))) {
                // different type
                return false;
            }
            boolean sameParentSContext = XPathSchemaContext.Utilities.equalsChain(
                    this.getSchemaContext(), otherPC.getSchemaContext());
            if (!sameParentSContext) {
                // different location
                return false;
            }
            //
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.mType != null ? this.mType.getName().hashCode() : 0);
        hash = 83 * hash + (this.mName != null ? this.mName.hashCode() : 0);
        hash = 83 * hash + (this.mNamespace != null ? this.mNamespace.hashCode() : 0);
        hash = 83 * hash + (this.mIsAttribute ? 1 : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        if (isAttribute()) {
            return mXPathParentExpression.getExpressionString() + "/@" + 
                    mName; 
        } else {
            return mXPathParentExpression.getExpressionString() + "/" + 
                    mName; 
        }
    }
    
    public XPathSchemaContext getSchemaContext() {
        XPathExpression expr = getParentPathExpression();
        if (expr != null && expr instanceof XPathSchemaContextHolder) {
            XPathSchemaContext sContext = 
                    ((XPathSchemaContextHolder)expr).getSchemaContext();
            //
            // TODO: add pseudo element/attribute
            //
            return sContext;
        }
        //
        return null;
    }

    public void setSchemaContext(XPathSchemaContext newContext) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
