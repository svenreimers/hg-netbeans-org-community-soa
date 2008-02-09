/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
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
package org.netbeans.modules.bpel.validation.xpath;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;

import org.netbeans.modules.bpel.model.api.Activity;
import org.netbeans.modules.bpel.model.api.BooleanExpr;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.BpelModel;
import org.netbeans.modules.bpel.model.api.Branches;
import org.netbeans.modules.bpel.model.api.Condition;
import org.netbeans.modules.bpel.model.api.ContentElement;
import org.netbeans.modules.bpel.model.api.DeadlineExpression;
import org.netbeans.modules.bpel.model.api.ExpressionLanguageSpec;
import org.netbeans.modules.bpel.model.api.FinalCounterValue;
import org.netbeans.modules.bpel.model.api.For;
import org.netbeans.modules.bpel.model.api.From;
import org.netbeans.modules.bpel.model.api.OnAlarmEvent;
import org.netbeans.modules.bpel.model.api.Query;
import org.netbeans.modules.bpel.model.api.RepeatEvery;
import org.netbeans.modules.bpel.model.api.StartCounterValue;
import org.netbeans.modules.bpel.model.api.To;
import org.netbeans.modules.bpel.model.api.support.ExNamespaceContext;
import org.netbeans.modules.bpel.model.api.support.XPathModelFactory;
import org.netbeans.modules.bpel.model.impl.references.SchemaReferenceBuilder;
import org.netbeans.modules.xml.schema.model.SchemaModel;
import org.netbeans.modules.xml.xpath.ext.XPathModelHelper;
import org.netbeans.modules.xml.xpath.ext.XPathException;
import org.netbeans.modules.xml.xpath.ext.XPathExpression;
import org.netbeans.modules.xml.xpath.ext.XPathModel;
import org.netbeans.modules.xml.xpath.ext.spi.ExternalModelResolver;
import org.netbeans.modules.bpel.model.api.support.PathValidationContext;
import org.netbeans.modules.bpel.model.api.support.BpelXPathNamespaceContext;
import org.netbeans.modules.bpel.model.api.support.BpelVariableResolver;
import org.netbeans.modules.bpel.model.api.support.BpelXpathExtFunctionResolver;
import static org.netbeans.modules.soa.ui.util.UI.*;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2008.02.08
 */
public final class Validator extends org.netbeans.modules.bpel.validation.core.Validator {

  @Override
  public void visit(BooleanExpr expr) {
      checkXPathExpression(expr);
  }
  
  @Override
  public void visit(Branches branches) {
      checkXPathExpression(branches);
  }

  @Override
  public void visit(Condition condition) {
      checkXPathExpression(condition);
  }
  
  @Override
  public void visit(DeadlineExpression expression) {
      checkXPathExpression(expression);
  }
  
  @Override
  public void visit(FinalCounterValue value) {
      checkXPathExpression(value);
  }
  
  @Override
  public void visit(For fo) {
      checkXPathExpression(fo);
  }
  
  @Override
  public void visit(From from) {
      checkXPathExpression(from);
  }
  
  @Override
  public void visit(Query query) {
      checkXPathExpression(query);
  }
  
  @Override
  public void visit(RepeatEvery repeatEvery) {
      checkXPathExpression(repeatEvery);
  }
  
  @Override
  public void visit(StartCounterValue value) {
      checkXPathExpression(value);
  }
  
  @Override
  public void visit(To to) {
      checkXPathExpression(to);
  }
  
  @Override
  public void visit(OnAlarmEvent event) {
      myValidatedActivity = event;
  }
  
  @Override
  protected void visit(Activity activity) {
      myValidatedActivity = activity;
  }
  
  private void checkXPathExpression(ContentElement element) {
      String content = element.getContent();
      
      if (content == null) {
          return;
      }
      content = content.trim();

      if (content.length() == 0) {
          return;
      }
      String expressionLang = null;
      
      if (element instanceof ExpressionLanguageSpec) {
          expressionLang = ((ExpressionLanguageSpec) element).
                  getExpressionLanguage();
      }
      checkExpression(expressionLang, content, element);
  }
  
  public void checkExpression(String exprLang, String exprText, final ContentElement element) {
      boolean isXPathExpr = exprLang == null || XPathModelFactory.DEFAULT_EXPR_LANGUAGE.equals(exprLang);

      if ( !isXPathExpr) {
          return;
      }
      XPathModelHelper helper= XPathModelHelper.getInstance();
      XPathModel model = helper.newXPathModel();
      assert myValidatedActivity != null;

      final PathValidationContext context = new PathValidationContext(model, this, this, myValidatedActivity, element);
      model.setValidationContext(context);

      ExNamespaceContext nsContext = ((BpelEntity)element).getNamespaceContext();
      model.setNamespaceContext(new BpelXPathNamespaceContext(nsContext));

      model.setVariableResolver(new BpelVariableResolver(context, myValidatedActivity));
      model.setExtensionFunctionResolver(new BpelXpathExtFunctionResolver());

      model.setExternalModelResolver(new ExternalModelResolver() {
          public Collection<SchemaModel> getModels(String modelNsUri) {
              BpelModel bpelModel = ((BpelEntity)element).getBpelModel();
              return SchemaReferenceBuilder.getSchemaModels(bpelModel, modelNsUri);
          }

          public Collection<SchemaModel> getVisibleModels() {
              context.addResultItem(Validator.ResultType.ERROR, "ABSOLUTE_PATH_DISALLOWED"); // NOI18N
              return null;
          }

          public boolean isSchemaVisible(String schemaNamespaceUri) {
              return context.isSchemaImported(schemaNamespaceUri);
          }
      });
      //
      // Checks if the expression contains ";". 
      // If it does, then split it to parts and verifies them separately.
      if (exprText.contains(XPathModelFactory.XPATH_EXPR_DELIMITER)) {
          // Notify the user that the expression is not completed
          context.addResultItem(exprText, Validator.ResultType.ERROR, "INCOMPLETE_XPATH"); // NOI18N

          String[] partsArr = exprText.split(
                  XPathModelFactory.XPATH_EXPR_DELIMITER);
          for (String anExprText : partsArr) {
              if (anExprText != null && anExprText.length() != 0) {
                  // Only the first expression graph has to be connected 
                  // to the right tree! The isFirst flag is used for it. 
                  checkSingleExpr(model, anExprText);
              }
          }
      } else {
          checkSingleExpr(model, exprText);
      }
  }

  private void checkSingleExpr(XPathModel model, String exprText) {
      try {
          XPathExpression xpath = model.parseExpression(exprText);
          // Common validation will be made here!
          model.resolveExtReferences(true);
      } 
      catch (XPathException e) {
          // Nothing to do here because of the validation context 
          // was specified before and it has to be populated 
          // with a set of problems.
      }
  }

  private BpelEntity myValidatedActivity; 
}