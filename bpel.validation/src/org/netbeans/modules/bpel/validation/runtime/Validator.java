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
package org.netbeans.modules.bpel.validation.runtime;

import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import javax.xml.namespace.QName;
import org.netbeans.modules.bpel.model.api.ExtensionEntity;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.netbeans.modules.bpel.model.api.references.BpelReference;
import org.netbeans.modules.bpel.model.api.Activity;
import org.netbeans.modules.bpel.model.api.Assign;
import org.netbeans.modules.bpel.model.api.BpelContainer;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.Catch;
import org.netbeans.modules.bpel.model.api.CatchAll;
import org.netbeans.modules.bpel.model.api.Compensate;
import org.netbeans.modules.bpel.model.api.CompensationHandler;
import org.netbeans.modules.bpel.model.api.Correlation;
import org.netbeans.modules.bpel.model.api.CorrelationSet;
import org.netbeans.modules.bpel.model.api.CorrelationSetContainer;
import org.netbeans.modules.bpel.model.api.CorrelationContainer;
import org.netbeans.modules.bpel.model.api.CorrelationsHolder;
import org.netbeans.modules.bpel.model.api.CorrelationSet;
import org.netbeans.modules.bpel.model.api.CreateInstanceActivity;
import org.netbeans.modules.bpel.model.api.Documentation;
import org.netbeans.modules.bpel.model.api.ExtensibleAssign;
import org.netbeans.modules.bpel.model.api.ExtensibleElements;
import org.netbeans.modules.bpel.model.api.Flow;
import org.netbeans.modules.bpel.model.api.ForEach;
import org.netbeans.modules.bpel.model.api.From;
import org.netbeans.modules.bpel.model.api.Import;
import org.netbeans.modules.bpel.model.api.Invoke;
import org.netbeans.modules.bpel.model.api.LinkContainer;
import org.netbeans.modules.bpel.model.api.MessageExchangeContainer;
import org.netbeans.modules.bpel.model.api.OnEvent;
import org.netbeans.modules.bpel.model.api.OnMessage;
import org.netbeans.modules.bpel.model.api.PartnerLink;
import org.netbeans.modules.bpel.model.api.PartnerLinkContainer;
import org.netbeans.modules.bpel.model.api.Process;
import org.netbeans.modules.bpel.model.api.ReThrow;
import org.netbeans.modules.bpel.model.api.Receive;
import org.netbeans.modules.bpel.model.api.Reply;
import org.netbeans.modules.bpel.model.api.Scope;
import org.netbeans.modules.bpel.model.api.SourceContainer;
import org.netbeans.modules.bpel.model.api.TargetContainer;
import org.netbeans.modules.bpel.model.api.TerminationHandler;
import org.netbeans.modules.bpel.model.api.To;
import org.netbeans.modules.bpel.model.api.Validate;
import org.netbeans.modules.bpel.model.api.Variable;
import org.netbeans.modules.bpel.model.api.support.Initiate;
import org.netbeans.modules.bpel.model.api.support.TBoolean;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentComponent;
import org.netbeans.modules.xml.xam.spi.Validator.ResultType;
import org.netbeans.modules.bpel.validation.core.BpelValidator;
import static org.netbeans.modules.soa.ui.util.UI.*;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2007.05.03
 */
public final class Validator extends BpelValidator {
    
    private void processCorrelationsHolder(CorrelationsHolder holder) {
//out();
//out();
//out("processCorrelationsHolder: " + holder);
      if (holder instanceof Reply) {
//out("[skip]");
        return;
      }
      CreateInstanceActivity creator = getCreateInstanceActivity(holder);
      CorrelationContainer container = holder.getCorrelationContainer();
//out("creator: " + creator);

      // # 105786
      if (container == null && !isCreateInstanceYes(creator)) {
        // # 99711
        addWarning("FIX_Empty_Correlations", holder); // NOI18N
        return;
      }
      if (container == null) {
        return;
      }
      Correlation [] correlations = container.getCorrelations();

      // # 105786
      if ((correlations == null || correlations.length == 0) && !isCreateInstanceYes(creator)) {
        // # 99711
        addWarning("FIX_Empty_Correlations", container); // NOI18N
        return;
      }
      // # 81537
      List<CorrelationSet> sets = new ArrayList<CorrelationSet>();

      for (Correlation correlation : correlations) {
        if (correlation.getInitiate() != Initiate.YES) {
          continue;
        }
        CorrelationSet set = correlation.getSet().get();

        if (sets.contains(set)) {
          addError("FIX_Repeated_Corelation_Sets", container); // NOI18N
          return;
        }
        sets.add(set);
      }
      if (creator != null && creator.getCreateInstance() == TBoolean.YES) {
        return;
      }
      // # 96091
      for (Correlation correlation : correlations) {
        Initiate initiate = correlation.getInitiate();
//out("  see: " + initiate);

        if (initiate == null || initiate == Initiate.NO) {
//out("    ok");
          return;
        }
      }
      addWarning("FIX_Correlating_Activity", container); // NOI18N
    }

    @Override
    public void visit(Process process) {
        String queryLang = process.getQueryLanguage();

        if (queryLang != null) {
            addWarning(FIX_ATTRIBUTE, process, Process.QUERY_LANGUAGE);
        }
        String expression = process.getExpressionLanguage();
        
        if (expression != null) {
            addWarning(FIX_ATTRIBUTE, process, Process.EXPRESSION_LANGUAGE);
        }
        TBoolean value = process.getSuppressJoinFailure();
        
        if (value != null) {
            addWarning(FIX_ATTRIBUTE, process, Process.SUPPRESS_JOIN_FAILURE);
        }
        value = process.getExitOnStandardFault();
        
        if (value != null) {
            addWarning(FIX_ATTRIBUTE, process, Process.EXIT_ON_STANDART_FAULT);
        }
        checkValidURI(process, Process.QUERY_LANGUAGE, process.getQueryLanguage());
        checkValidURI(process, Process.EXPRESSION_LANGUAGE, process.getExpressionLanguage());
    }
    
    @Override
    public void visit(Validate validate) {
        addElementError(validate);
    }
    
    @Override
    public void visit(PartnerLink partnerLink) {
        if ( partnerLink.getInitializePartnerRole() != null ) {
            addWarning(FIX_ATTRIBUTE, partnerLink, PartnerLink.INITIALIZE_PARTNER_ROLE);
        }
    }
    
    @Override
    public void visit( Variable variable ) {
        From from = variable.getFrom();
        if ( from != null ) {
            addElementsInParentError( variable , from );
        }
    }
    
    @Override
    public void visit( TargetContainer container ) {
        addElementError(container);
    }
    
    @Override
    public void visit(SourceContainer container) {
        addElementError(container);
    }
    
    @Override
    public void visit(Invoke invoke) {
        super.visit(invoke);
        Catch[] catches = invoke.getCatches();

        if (catches != null && catches.length > 0) {
            addElementsInParentError(invoke, (BpelEntity[]) catches);
        }
        CatchAll catchAll = invoke.getCatchAll();

        if (catchAll != null ) {
            addElementsInParentError(invoke, catchAll);
        }
        if (invoke.getFromPartContaner() != null) {
            addElementsInParentError(invoke, FROM_PARTS);
        }
        if (invoke.getToPartContaner() != null) {
            addElementsInParentError(invoke, TO_PARTS);
        }
    }
    
    @Override
    public void visit(ExtensibleAssign extensibleAssign) {
        addElementError(extensibleAssign);
    }
    
    @Override
    public void visit(Assign assign) {
        super.visit(assign);

        if (assign.getValidate() != null) {
            addWarning(FIX_ATTRIBUTE, assign, Assign.VALIDATE);
        }
    }
    
    @Override
    public void visit(From from) {
        Documentation[] docs = from.getDocumentations();

        if (docs!= null && docs.length > 0) {
            addElementsInParentError(from, (BpelEntity[]) docs);
        }
        if (from.getExpressionLanguage()!= null ) {
            addWarning(FIX_ATTRIBUTE, from, From.EXPRESSION_LANGUAGE);
        }
        if (from.getProperty() != null ) {
            addWarning(FIX_ATTRIBUTE, from, From.PROPERTY);
        }
// # 123382
//        if (from.getPartnerLink() != null) {
//            addWarning(FIX_ATTRIBUTE, from, From.PARTNER_LINK);
//        }
// # 128665
//        if (from.getEndpointReference() != null) {
//            addWarning(FIX_ATTRIBUTE, from, From.ENDPOINT_REFERENCE);
//        }
        checkAbsenceExtensions(from);
    }
    
    public void visit(To to) {
        Documentation[] docs = to.getDocumentations();
    
        if (docs!= null && docs.length > 0) {
            addElementsInParentError(to, (BpelEntity[]) docs);
        }
        if (to.getProperty () != null) {
            addWarning(FIX_ATTRIBUTE, to, To.PROPERTY);
        }
// # 123382
//        if (to.getPartnerLink () != null) {
//            addWarning(FIX_ATTRIBUTE, to, To.PARTNER_LINK);
//        }
        checkAbsenceExtensions( to );
    }
    
    @Override
    public void visit( Flow flow ) {
        super.visit(flow);
        LinkContainer container = flow.getLinkContainer();

        if (container!= null) {
            addElementError(container);
        }
    }
    
    @Override
    public void visit( Scope scope ) {
        super.visit(scope);
        PartnerLinkContainer container = scope.getPartnerLinkContainer();
        if ( container!= null ) {
            addElementsInParentError( scope, container );
        }
        CorrelationSetContainer setContainer = scope.getCorrelationSetContainer();
        if ( setContainer!= null ) {
            addElementsInParentError( scope, setContainer );
        }
        
        if ( scope.getIsolated() != null ) {
            addWarning(FIX_ATTRIBUTE, scope, Scope.ISOLATED);
        }
        if ( scope.getExitOnStandardFault()!= null ) {
            addWarning(FIX_ATTRIBUTE, scope, Scope.EXIT_ON_STANDART_FAULT);
        }
    }
    
    @Override
    public void visit( ForEach forEach ) {
        super.visit(forEach);
        if ( TBoolean.YES.equals( forEach.getParallel())) {
            addWarning(FIX_ATTRIBUTE, forEach, ForEach.PARALLEL);
        }
    }
    
    @Override
    protected void visit( Activity activity ) {
        if ( activity.getSuppressJoinFailure() !=null ) {
            addWarning(FIX_ATTRIBUTE, activity, Activity.SUPPRESS_JOIN_FAILURE);
        }
    }
    
    
    @Override
    public void visit(Import bpelImport) {
        
        if( ! isAttributeValueSpecified(bpelImport.getLocation())) {
            addAttributeNeededForRuntime(bpelImport.LOCATION, bpelImport);
        }
        
        if( ! isAttributeValueSpecified(bpelImport.getNamespace())) {
            addAttributeNeededForRuntime(bpelImport.NAMESPACE, bpelImport);
        }
        
    }
    
    @Override
    public void visit(Receive receive) {
        super.visit(receive);

        if (receive.getFromPartContaner()!= null ) {
            addElementsInParentError(receive, FROM_PARTS);
        }
        if (receive.getMessageExchange() != null) {
            addWarning(FIX_ATTRIBUTE, receive, Receive.MESSAGE_EXCHANGE);
        }
        processCorrelationsHolder(receive);
    }
    
    @Override
    public void visit(Reply reply) {
        super.visit(reply);

        if(reply.getToPartContaner() != null ) {
            addElementsInParentError(reply, TO_PARTS);
        }
        if(reply.getMessageExchange() != null) {
            addWarning(FIX_ATTRIBUTE, reply, Reply.MESSAGE_EXCHANGE);
        }
        processCorrelationsHolder(reply);
    }
    
    @Override
    public void visit(OnEvent onEvent) {
        if(onEvent.getFromPartContaner() != null ) {  
            addElementsInParentError(onEvent, FROM_PARTS);
        }
        if(onEvent.getMessageExchange() != null) {
            addWarning(FIX_ATTRIBUTE, onEvent, OnEvent.MESSAGE_EXCHANGE);
        }
        processCorrelationsHolder(onEvent);
    }
    
    @Override
    public void visit(OnMessage onMessage) {
        if(onMessage.getFromPartContaner() != null ) {
            addElementsInParentError(onMessage, FROM_PARTS);
        }
        if(onMessage.getMessageExchange() != null) {
            addWarning(FIX_ATTRIBUTE, onMessage, OnMessage.MESSAGE_EXCHANGE);
        }
        processCorrelationsHolder(onMessage);
    }
    
    @Override
    public void  visit(MessageExchangeContainer messageExchangeContainer) {
            addElementError(messageExchangeContainer);
    }

    private void checkAbsenceExtensions( ExtensibleElements element ) {
        if ( element instanceof AbstractDocumentComponent ){
            AbstractDocumentComponent component =
                    (AbstractDocumentComponent)element;
            
            Map map = component.getAttributeMap();
            for( Object obj : map.keySet() ){
                QName qName = (QName)obj;
                if ( qName.getNamespaceURI()!= null && qName.getNamespaceURI().length()>0 ){
                    addWarning(FIX_ATTRIBUTE, element, qName.toString());
                }
            }
            
            NodeList list = component.getPeer().getChildNodes();
            for ( int i=0 ; i<list.getLength() ; i++ ){
                Node node = list.item(i);
                if ( node instanceof Element ){
                    Element childElement = (Element) node;
                    if ( !BpelEntity.BUSINESS_PROCESS_NS_URI.equals(childElement.getNamespaceURI() ) ) {
                        addElementsInParentError( element, childElement.getLocalName() );
                    }
                }
            }
        }
    }
    
    private void addElementError(BpelEntity entity) {
        addError(FIX_ELEMENT, entity, entity.getPeer().getLocalName());
    }
    
    private void addElementsInParentError(BpelContainer parent, BpelEntity... entities) {
        addError(FIX_ELEMENT_IN_PARENT, entities[0], entities[0].getPeer().getLocalName(), parent.getPeer().getLocalName());
    }
    
    private void addElementsInParentError( BpelContainer parent, String tagName ) {
        addError(FIX_ELEMENT_IN_PARENT, parent, tagName,parent.getPeer().getLocalName());
    }
    
    private void addAttributeNeededForRuntime(String attributeName, Component component) {
        addWarning(FIX_ATTRIBUTE_REQUIRED_SUN_BPELSE, component, attributeName);
    }
    
    private boolean isAttributeValueSpecified(String value) {
        return value != null && !value.trim().equals("");
    }

    private void checkValidURI(BpelEntity bpelEntity, String attribute, String attributeValue) {
        if(attributeValue != null) {
            try {
                new URI(attributeValue);
            }
            catch (URISyntaxException ex) {
                addError(FIX_INVALID_URI, bpelEntity, attribute);
            }
        }
    }
    
    private BpelContainer hasParent( BpelEntity entity, Class<? extends BpelContainer>... types) {
        BpelContainer parent = entity.getParent();

        while( parent != null ) {
            for( Class<? extends BpelContainer> clazz :types ) {
                if (clazz.isInstance(parent)) {
                    return parent;
                }
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    private static final String FIX_ATTRIBUTE = "FIX_Attribute";    // NOI18N
    private static final String FIX_ELEMENT = "FIX_Element";        // NOI18N
    private static final String FIX_ELEMENT_IN_PARENT = "FIX_ElementInParent"; // NOI18N
    private static final String FIX_ATTRIBUTE_REQUIRED_SUN_BPELSE = "FIX_Attribute_Required_For_Sun_BpelSE"; // NOI18N
    private static final String FROM_PARTS = "<fromParts>";  // NOI18N
    private static final String TO_PARTS = "<toParts>";
    private static final String FIX_INVALID_URI = "FIX_INVALID_URI"; // NOI18N
}
