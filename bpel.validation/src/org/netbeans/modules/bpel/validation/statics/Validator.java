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
 * License. When distributing the software, include this License Header
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
package org.netbeans.modules.bpel.validation.statics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.xml.namespace.QName;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.netbeans.modules.bpel.model.api.BaseScope;
import org.netbeans.modules.bpel.model.api.BpelContainer;
import org.netbeans.modules.bpel.model.api.BpelModel;
import org.netbeans.modules.bpel.model.api.Catch;
import org.netbeans.modules.bpel.model.api.Compensate;
import org.netbeans.modules.bpel.model.api.CompensateScope;
import org.netbeans.modules.bpel.model.api.CompensationHandler;
import org.netbeans.modules.bpel.model.api.CorrelationContainer;
import org.netbeans.modules.bpel.model.api.CorrelationSet;
import org.netbeans.modules.bpel.model.api.CorrelationSetContainer;
import org.netbeans.modules.bpel.model.api.EventHandlers;
import org.netbeans.modules.bpel.model.api.Extension;
import org.netbeans.modules.bpel.model.api.FaultHandlers;
import org.netbeans.modules.bpel.model.api.Flow;
import org.netbeans.modules.bpel.model.api.ForEach;
import org.netbeans.modules.bpel.model.api.From;
import org.netbeans.modules.bpel.model.api.FromPart;
import org.netbeans.modules.bpel.model.api.FromPartContainer;
import org.netbeans.modules.bpel.model.api.Import;
import org.netbeans.modules.bpel.model.api.Invoke;
import org.netbeans.modules.bpel.model.api.Link;
import org.netbeans.modules.bpel.model.api.LinkContainer;
import org.netbeans.modules.bpel.model.api.MessageExchange;
import org.netbeans.modules.bpel.model.api.MessageExchangeReference;
import org.netbeans.modules.bpel.model.api.OnEvent;
import org.netbeans.modules.bpel.model.api.OnMessage;
import org.netbeans.modules.bpel.model.api.OperationReference;
import org.netbeans.modules.bpel.model.api.PartnerLink;
import org.netbeans.modules.bpel.model.api.PartnerLinkContainer;
import org.netbeans.modules.bpel.model.api.PatternedCorrelation;
import org.netbeans.modules.bpel.model.api.PatternedCorrelationContainer;
import org.netbeans.modules.bpel.model.api.Pick;
import org.netbeans.modules.bpel.model.api.Process;
import org.netbeans.modules.bpel.model.api.ReThrow;
import org.netbeans.modules.bpel.model.api.Receive;
import org.netbeans.modules.bpel.model.api.Reply;
import org.netbeans.modules.bpel.model.api.Requester;
import org.netbeans.modules.bpel.model.api.Responder;
import org.netbeans.modules.bpel.model.api.Scope;
import org.netbeans.modules.bpel.model.api.Source;
import org.netbeans.modules.bpel.model.api.SourceContainer;
import org.netbeans.modules.bpel.model.api.Target;
import org.netbeans.modules.bpel.model.api.TargetContainer;
import org.netbeans.modules.bpel.model.api.TerminationHandler;
import org.netbeans.modules.bpel.model.api.Variable;
import org.netbeans.modules.bpel.model.api.VariableContainer;
import org.netbeans.modules.bpel.model.api.VariableDeclaration;
import org.netbeans.modules.bpel.model.api.references.BpelReference;
import org.netbeans.modules.bpel.model.api.references.SchemaReference;
import org.netbeans.modules.bpel.model.api.references.WSDLReference;
import org.netbeans.modules.bpel.model.api.support.ImportHelper;
import org.netbeans.modules.bpel.model.api.support.Pattern;
import org.netbeans.modules.bpel.model.api.support.Roles;
import org.netbeans.modules.bpel.model.api.support.TBoolean;
import org.netbeans.modules.bpel.model.api.support.Utils;
import org.netbeans.modules.bpel.model.api.support.Utils.Pair;
import org.netbeans.modules.bpel.model.api.support.ContainerIterator;
import org.netbeans.modules.xml.schema.model.GlobalElement;
import org.netbeans.modules.xml.schema.model.GlobalSimpleType;
import org.netbeans.modules.xml.schema.model.GlobalType;
import org.netbeans.modules.xml.wsdl.model.Types;
import org.netbeans.modules.xml.wsdl.model.Input;
import org.netbeans.modules.xml.wsdl.model.Definitions;
import org.netbeans.modules.xml.wsdl.model.Message;
import org.netbeans.modules.xml.wsdl.model.NotificationOperation;
import org.netbeans.modules.xml.wsdl.model.OneWayOperation;
import org.netbeans.modules.xml.wsdl.model.Operation;
import org.netbeans.modules.xml.wsdl.model.Output;
import org.netbeans.modules.xml.wsdl.model.Part;
import org.netbeans.modules.xml.wsdl.model.PortType;
import org.netbeans.modules.xml.wsdl.model.RequestResponseOperation;
import org.netbeans.modules.xml.wsdl.model.SolicitResponseOperation;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.xml.wsdl.model.extensions.bpel.CorrelationProperty;
import org.netbeans.modules.xml.wsdl.model.extensions.bpel.PropertyAlias;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.Named;
import org.netbeans.modules.xml.xam.Model;
import org.netbeans.modules.xml.xam.dom.NamedComponentReference;
import org.netbeans.modules.xml.schema.model.Schema;
import org.netbeans.modules.xml.schema.model.SchemaModel;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.Activity;
import org.netbeans.modules.xml.xam.Model.State;
import org.netbeans.modules.bpel.model.api.support.Initiate;
import org.netbeans.modules.bpel.model.api.NamedElement;
import org.netbeans.modules.bpel.model.api.CreateInstanceActivity;
import org.netbeans.modules.bpel.model.api.Sequence;
import org.netbeans.modules.bpel.model.api.ExtendableActivity;
import org.netbeans.modules.bpel.model.api.CompositeActivity;
import org.netbeans.modules.bpel.model.api.Correlation;
import org.netbeans.modules.bpel.model.api.BaseCorrelation;
import org.netbeans.modules.bpel.model.api.While;
import org.netbeans.modules.bpel.model.api.ActivityHolder;
import org.netbeans.modules.bpel.model.api.ToPart;
import org.netbeans.modules.bpel.model.api.RepeatUntil;
import org.netbeans.modules.bpel.model.api.PartnerLinkReference;
import org.netbeans.modules.bpel.model.api.PortTypeReference;
import org.netbeans.modules.bpel.validation.core.BpelValidator;
import org.netbeans.modules.bpel.model.api.support.SimpleBpelModelVisitor;
import org.netbeans.modules.bpel.model.api.support.SimpleBpelModelVisitorAdaptor;
import org.netbeans.modules.xml.misc.Xml;
import org.netbeans.modules.soa.ui.SoaUtil;
import static org.netbeans.modules.xml.misc.UI.*;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2007.05.03
 */
public final class Validator extends BpelValidator {

    @Override
    protected SimpleBpelModelVisitor getVisitor() {
        return new SimpleBpelModelVisitorAdaptor() {

            // # 86958
            private void checkDuplicate(Process process) {
                Import[] imports = process.getImports();

                if (imports == null) {
                    return;
                }
                List<Model> wsdls = new ArrayList<Model>();
                List<Model> schemas = new ArrayList<Model>();

//out();
                for (Import imp : imports) {
//out("see: " + imp.getLocation());
                    WSDLModel wsdl = ImportHelper.getWsdlModel(imp, false);

                    if (wsdl != null && !wsdls.contains(wsdl)) {
                        wsdls.add(wsdl);
                    }
                    SchemaModel schema = ImportHelper.getSchemaModel(imp, false);

                    if (schema == null) {
                        continue;
                    }
                    if (schemas.contains(schema)) {
                        addWarning("FIX_SA00014", imp); // NOI18N
                    } else {
                        schemas.add(schema);
                    }
                }
                for (Model wsdl : wsdls) {
                    Definitions definitions = ((WSDLModel) wsdl).getDefinitions();

                    if (definitions == null) {
                        continue;
                    }
                    Types types = definitions.getTypes();

                    if (types == null) {
                        continue;
                    }
                    Collection<Schema> inlines = types.getSchemas();

                    if (inlines == null) {
                        continue;
                    }
                    for (Schema inline : inlines) {
                        schemas.add(inline.getModel());
                    }
                }
                checkModels(process, wsdls);
                checkModels(process, schemas);
            }

            private void checkModels(Process process, List<Model> models) {
//out();
                for (int i = 0; i < models.size(); i++) {
                    for (int j = i + 1; j < models.size(); j++) {
                        checkDuplicate(process, models.get(i), models.get(j));
                    }
                }
            }

            private void checkDuplicate(Process process, Model model1, Model model2) {
//out("check: ");
                String targetNamespace1 = getTargetNamespace(model1);
//out("  tns1: " + targetNamespace1);

                if (targetNamespace1 == null) {
                    return;
                }
                String targetNamespace2 = getTargetNamespace(model2);
//out("  tns2: " + targetNamespace1);

                if (targetNamespace2 == null) {
                    return;
                }
                if (!targetNamespace1.equals(targetNamespace2)) {
                    return;
                }
//out("check: ");
//out("  " + model1);
//out("  " + model2);
                if (model1 instanceof WSDLModel) {
                    checkDuplicate(process, ((WSDLModel) model1).getDefinitions(), ((WSDLModel) model2).getDefinitions());
                } else if (model1 instanceof SchemaModel) {
                    Schema schema1 = ((SchemaModel) model1).getSchema();
                    Schema schema2 = ((SchemaModel) model2).getSchema();
                    if (schema1 != null && schema2 != null) {
                        checkDuplicate(process, schema1, schema2);
                    }
                }
            }

            private void checkDuplicate(Process process, Definitions definitions1, Definitions definitions2) {
                checkDuplicate(process, definitions1.getMessages(), definitions2.getMessages());
                checkDuplicate(process, definitions1.getPortTypes(), definitions2.getPortTypes());
                checkDuplicate(process, definitions1.getBindings(), definitions2.getBindings());
                checkDuplicate(process, definitions1.getServices(), definitions2.getServices());
            }

            private void checkDuplicate(Process process, Schema schema1, Schema schema2) {
                checkDuplicate(process, schema1.getAttributes(), schema2.getAttributes());
                checkDuplicate(process, schema1.getAttributeGroups(), schema2.getAttributeGroups());
                checkDuplicate(process, schema1.getGroups(), schema2.getGroups());
                checkDuplicate(process, schema1.getNotations(), schema2.getNotations());

                checkDuplicate(process, schema1.getElements(), schema2.getElements());
                checkDuplicate(process, schema1.getSimpleTypes(), schema2.getSimpleTypes());
                checkDuplicate(process, schema1.getComplexTypes(), schema2.getComplexTypes());
            }

            private void checkDuplicate(Process process, Collection<? extends Named> collection1, Collection<? extends Named> collection2) {
                List<Named> list1 = list(collection1);
                List<Named> list2 = list(collection2);

                for (int i = 0; i < list1.size(); i++) {
                    checkDuplicate(process, list1.get(i), list2);
                }
            }

            private void checkDuplicate(Process process, Named named1, List<Named> list) {
                String name1 = named1.getName();

                if (name1 == null || name1.length() == 0) {
                    return;
                }
                String file1 = getFileName(named1);

                for (int i = 0; i < list.size(); i++) {
                    Named named2 = list.get(i);

                    if (!(name1.equals(named2.getName()))) {
                        continue;
                    }
                    String file2 = getFileName(named2);

                    if (getPresentation(named1).equals(getPresentation(named2))) {
                        if (file1 == null || file2 == null) {
                            addWarning("FIX_SA00014_Identical", process, named1.getName()); // NOI18N
                        } else {
                            addWarning("FIX_SA00014_Identical_File", process, named1.getName(), file1, file2); // NOI18N
                        }
                    } else {
                        if (file1 == null || file2 == null) {
                            addError("FIX_SA00014_Different", process, named1.getName()); // NOI18N
                        } else {
//out();
//out("1: " + getPresentation(named1));
//out("2: " + getPresentation(named2));
                            addError("FIX_SA00014_Different_File", process, named1.getName(), file1, file2); // NOI18N
                        }
                    }
                }
            }

            private String getPresentation(Component component) {
                StringBuilder builder;

                if (getName(component) == null) {
                    builder = new StringBuilder();
                } else {
                    builder = new StringBuilder(getName(component));
                }
                builder.append("("); // NOI18N
                List children = component.getChildren();

                for (Object child : children) {
                    if (!(child instanceof Component)) {
                        continue;
                    }
                    builder.append(getPresentation((Component) child));
                }
                builder.append(")"); // NOI18N

                return builder.toString();
            }

            private String getName(Component component) {
                if (!(component instanceof Named)) {
                    return "[]"; // NOI18N
                }
                return ((Named) component).getName();
            }

            private String getFileName(Component component) {
                if (component == null) {
                    return null;
                }
                FileObject file = SoaUtil.getFileObjectByModel(component.getModel());

                if (file == null) {
                    return null;
                }
                return Xml.getLocation(FileUtil.toFile(file), component);
            }

            private List<Named> list(Collection<? extends Named> collection) {
                List<Named> list = new ArrayList<Named>();

                if (collection == null) {
                    return list;
                }
                Iterator<? extends Named> iterator = collection.iterator();

                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }
                return list;
            }

            private String getTargetNamespace(Import imp) {
                String targetNamespace = getTargetNamespace(ImportHelper.getWsdlModel(imp, false));

                if (targetNamespace != null) {
                    return targetNamespace;
                }
                return getTargetNamespace(ImportHelper.getSchemaModel(imp, false));
            }

            private String getTargetNamespace(Model model) {
                if (model instanceof WSDLModel) {
                    return getTargetNamespace((WSDLModel) model);
                }
                if (model instanceof SchemaModel) {
                    return getTargetNamespace((SchemaModel) model);
                }
                return null;
            }

            private String getTargetNamespace(WSDLModel model) {
//out();
//out("getTargetNamespace: " + model);
                if (model == null) {
                    return null;
                }
                Definitions definitions = model.getDefinitions();
//out("definitions: " + definitions);

                if (definitions == null) {
                    return null;
                }
//out("definitions.getTargetNamespace: " + definitions.getTargetNamespace());
                return definitions.getTargetNamespace();
            }

            private String getTargetNamespace(SchemaModel model) {
                if (model == null) {
                    return null;
                }
                Schema schema = model.getSchema();

                if (schema == null) {
                    return null;
                }
                return schema.getTargetNamespace();
            }

            @Override
            public void visit(PartnerLink p) {
                // A partnerLink MUST specify the myRole or the partnerRole, or both.
                // This syntactic constraint MUST be statically enforced.
                if ((p.getMyRole() == null || p.getMyRole().equals("")) && (p.getPartnerRole() == null || p.getPartnerRole().equals(""))) {
                    addError("FIX_SA00016", p); // NOI18N
                }
                // The initializePartnerRole attribute MUST NOT be used on a partnerLink
                // that does not have a partner role; this restriction MUST be statically enforced.
                if (p.getPartnerRole() == null || p.getPartnerRole().equals("")) {
                    if ((p.getInitializePartnerRole() != null) && (!p.getInitializePartnerRole().equals(TBoolean.INVALID))) {
                        addError("FIX_SA00017", p); // NOI18N
                    }
                }
            }

            @Override
            public void visit(Invoke invoke) {
                // Porttype should not be solicit-response or Notification.
                checkSolicitResponsePortType(invoke, invoke.getPortType());
                checkNotificationPortType(invoke, invoke.getPortType());

                // PortType should not contain overloaded operation names.
                checkOverloadedPortTypeOperation(invoke, invoke.getPortType());

                // Input variable and toPart should not be used in combinatation.
                checkInputVariableToPartCombination(invoke);

                // Output variable and fromPart should not be used in combinatation.
                checkOutputVariableFromPartCombination(invoke);

                // FromPart element should have a valid part attribute.
                checkValidPartAttributeFromPartElement(invoke);

                // toPart element should have a valid part attribute.
                checkValidPartAttributeToPartElement(invoke);

                /* If the portType attribute is included for readability,
                 * in a <receive>, <reply>, <invoke>, <onEvent> or <onMessage>
                 * element, the value of the portType  attribute MUST match the
                 * portType value implied by the combination of the specified
                 * partnerLink and the role implicitly specified by the activity.
                 */
                checkPortTypeCombinedPartnerLink(invoke);

                /* For <invoke>, one-way invocation requires only the
                 * inputVariable (or its equivalent <toPart>'s) since a response
                 * is not expected as part of the operation. Request-response
                 * invocation requires both an inputVariable
                 * (or its equivalent <toPart>'s) and an outputVariable
                 * (or its equivalent <fromPart>'s). If a WSDL message definition
                 * does not contain any parts, then the associated attributes variable,
                 * inputVariable or outputVariable, or the associated <fromParts> or
                 * <toParts> elements MAY be omitted. The outputVariable (or its
                 * equivalent <fromPart>'s) must be only specified for request-response
                 * invocations.
                 *
                 * When the optional inputVariable and outputVariable
                 * attributes are being used in an <invoke> activity,
                 * the variables referenced by inputVariable and outputVariable
                 * MUST be messageType variables whose QName matches the QName of the
                 * input and output message type used in the operation, respectively,
                 * except as follows: if the WSDL operation used in an <invoke>
                 * activity uses a message containing exactly one part which itself is
                 * defined using an element, then a variable of the same element type
                 * as used to define the part MAY be referenced by the inputVariable
                 * and outputVariable attributes respectively.
                 */
                checkInputOutputVariableOperation(invoke);

                // All the WSDL message parts must be completely initialised
                // when using <toPart> element.
                checkAnyMissingToPartElementInInvoke(invoke);
            }

            @Override
            public void visit(Receive receive) {
                // Porttype should not be solicit-response or Notification.
                checkSolicitResponsePortType(receive, receive.getPortType());
                checkNotificationPortType(receive, receive.getPortType());

                // PortType should not contain overloaded operation names.
                checkOverloadedPortTypeOperation(receive, receive.getPortType());

                /*
                 * On <receive> variable and <fromPart> must not be used at the same time.
                 *
                 * One-way invocation requires only the inputVariable (or its
                 * equivalent <toPart> elements) since a response is not
                 * expected as part of the operation (see section 10.4. Providing
                 * Web Service Operations Receive and Reply in spec). Requestresponse
                 * invocation requires both an inputVariable (or its
                 * equivalent <toPart> elements) and an outputVariable (or
                 * its equivalent <fromPart> elements). If a WSDL message
                 * definition does not contain any parts, then the associated
                 * attributes variable, inputVariable or outputVariable,
                 * MAY be omitted,and the <fromParts> or <toParts>
                 * construct MUST be omitted., # 87444
                 */
                checkReceiveVariableFromPartCombination(receive);

                /*
                 * If the portType attribute is included for readability,
                 * in a <receive>, <reply>, <invoke>, <onEvent> or <onMessage>
                 * element, the value of the portType  attribute MUST match the
                 * portType value implied by the combination of the specified
                 * partnerLink and the role implicitly specified by the activity.
                 */
                checkPortTypeCombinedPartnerLink(receive);

                /*
                 * A "start activity" is a <receive> or <pick> activity that is
                 * annotated with a createInstance="yes" attribute.
                 * Activities other than the following: start activities, <scope>,
                 * <flow> and <sequence> MUST NOT be performed prior to
                 * or simultaneously with start activities.
                 */
                checkOrderOfActivities(receive);
            }

            @Override
            public void visit(Reply reply) {
                // Porttype should not be solicit-response or Notification.
                checkSolicitResponsePortType(reply, reply.getPortType());
                checkNotificationPortType(reply, reply.getPortType());

                // PortType should not contain overloaded operation names.
                checkOverloadedPortTypeOperation(reply, reply.getPortType());

                /* On <reply> variable and <toPart> must not be used at the same time.
                 *
                 * One-way invocation requires only the inputVariable (or its
                 * equivalent <toPart> elements) since a response is not
                 * expected as part of the operation (see section 10.4. Providing
                 * Web Service Operations Receive and Reply in spec). Requestresponse
                 * invocation requires both an inputVariable (or its
                 * equivalent <toPart> elements) and an outputVariable (or
                 * its equivalent <fromPart> elements). If a WSDL message
                 * definition does not contain any parts, then the associated
                 * attributes variable, inputVariable or outputVariable,
                 * MAY be omitted,and the <fromParts> or <toParts>
                 * construct MUST be omitted., # 87444
                 */
                checkReplyVariableToPartCombination(reply);

                /*
                 * If the portType attribute is included for readability,
                 * in a <receive>, <reply>, <invoke>, <onEvent> or <onMessage>
                 * element, the value of the portType  attribute MUST match the
                 * portType value implied by the combination of the specified
                 * partnerLink and the role implicitly specified by the activity.
                 */
                checkPortTypeCombinedPartnerLink(reply);

                // All the WSDL message parts must be completely initialised
                // when using <toPart> element.
                checkAnyMissingToPartElementInReply(reply);
            }

            @Override
            public void visit(OnMessage onMessage) {
                // Porttype should not be solicit-response or Notification.
                checkSolicitResponsePortType(onMessage, onMessage.getPortType());
                checkNotificationPortType(onMessage, onMessage.getPortType());

                // PortType should not contain overloaded operation names.
                checkOverloadedPortTypeOperation(onMessage, onMessage.getPortType());

                /*
                 * If the portType attribute is included for readability,
                 * in a <receive>, <reply>, <invoke>, <onEvent> or <onMessage>
                 * element, the value of the portType  attribute MUST match the
                 * portType value implied by the combination of the specified
                 * partnerLink and the role implicitly specified by the activity.
                 */
                checkPortTypeCombinedPartnerLink(onMessage);

                // Variable and <fromPart> must not be used at the simultaneously.
                checkOnMessageVariableFromPartCombination(onMessage);
            }

            @Override
            public void visit(ReThrow reThrow) {
                /*
                 * The <rethrow> activity MUST only be used within a faultHandler
                 * (i.e. <catch> and <catchAll> elements).
                 */
                boolean isInsideFaultHandlers = Utils.hasAscendant(reThrow, FaultHandlers.class);

                if (!isInsideFaultHandlers) {
                    addError("FIX_SA00006", reThrow); // NOI18N
                }
            }

            @Override
            public void visit(Compensate compensate) {
                checkCompensateOccurance(compensate);
            }

            @Override
            public void visit(CompensateScope compensateScope) {
                checkCompensateOccurance(compensateScope);
            }

            @Override
            public void visit(From from) {
                Roles roles = from.getEndpointReference();
                if (Roles.MY_ROLE.equals(roles)) {
                    BpelReference<PartnerLink> ref = from.getPartnerLink();
                    boolean referenceHasMyRole = (ref != null) && (ref.get() != null) && (ref.get().getMyRole() != null);
                    /*
                     * In the from-spec of the partnerLink variant of <assign>
                     * the value "myRole" for attribute endpointReference is only
                     * permitted when the partnerLink specifies the attribute myRole.
                     */
                    if (!referenceHasMyRole) {
                        addError("FIX_SA00035_SA00036", from, Roles.MY_ROLE.toString()); // NOI18N
                    }
                } else if (Roles.PARTNER_ROLE.equals(roles)) {
                    BpelReference<PartnerLink> ref = from.getPartnerLink();
                    boolean referenceHasPartnerRole = (ref != null) && (ref.get() != null) && (ref.get().getPartnerRole() != null);
                    /*
                     * In the from-spec of the partnerLink variant of <assign> the
                     * value "partnerRole" for attribute endpointReference is only
                     * permitted when the partnerLink specifies the attribute
                     * partnerRole.
                     */
                    if (!referenceHasPartnerRole) {
                        addError("FIX_SA00035_SA00036", from, Roles.PARTNER_ROLE.toString()); // NOI18N
                    }
                }
            }

            @Override
            public void visit(Process process) {
                //
                checkDuplicate(process);
                //
                visitBaseScope(process);

                /*
                 * To be instantiated, an executable business process MUST contain at
                 * least one <receive> or <pick> activity annotated with a
                 * createInstance="yes" attribute.
                 *
                 * If a process has multiple start activities with
                 * correlation sets then all such activities MUST share at
                 * least one common correlationSet and all common correlationSets defined
                 * on all the activities MUST have the value of the initiate
                 * attribute be set to "join".
                 */
                checkInstantiableActivities(process);

                /*
                 * A WS-BPEL process definition MUST NOT be accepted for
                 * processing if it defines two or more propertyAliases for the
                 * same property name and WS-BPEL variable type.
                 */
                checkPropertyAliasMultiplicity(process);

                /*
                 * Determine which languages are referenced by queryLanguage or
                 * expressionLanguage attributes either in the WS-BPEL process
                 * definition itself or in any WS-BPEL property definitions in
                 * associated WSDLs and if any referenced language is unsupported by the
                 * WS-BPEL processor then the processor MUST reject the submitted
                 * WS-BPEL process definition.
                 */
                String query = process.getQueryLanguage();
                String expression = process.getExpressionLanguage();

                if ((query != null && !SUPPORTED_LANGAGE.equals(query)) || (expression != null && !SUPPORTED_LANGAGE.equals(expression))) {
                    addError("FIX_SA00004", process, SUPPORTED_LANGAGE); // NOI18N
                }
                checkMessageExchangeHolders(process);
            }

            // # 83742
            private void checkMessageExchangeHolders(Process process) {
                List<Reply> replies = new ArrayList<Reply>();
                List<MessageExchangeReference> IMAs = new ArrayList<MessageExchangeReference>();

                collectMessageExchangeHolders(process, replies, IMAs);
//out();
//out("replies: " + replies.size());
//out("IMAs: " + IMAs.size());
                for (Reply reply : replies) {
                    MessageExchangeReference ima = findIMAwithMessageExchange(IMAs, reply);

                    if (ima != null) {
                        continue;
                    }
                    addError("FIX_SA00061", reply); // NOI18N
                }
            }

            private MessageExchangeReference findIMAwithMessageExchange(List<MessageExchangeReference> IMAs, Reply reply) {
                for (MessageExchangeReference ima : IMAs) {
                    if (theSame(ima, reply)) {
                        return ima;
                    }
                }
                return null;
            }

            private boolean theSame(MessageExchangeReference ima, Reply reply) {
                if (reply.getMessageExchange().get() != ima.getMessageExchange().get()) {
                    return false;
                }
                if (!(ima instanceof PartnerLinkReference)) {
                    return false;
                }
                if (!theSame((PartnerLinkReference) ima, reply)) {
                    return false;
                }
                if (!(ima instanceof OperationReference)) {
                    return false;
                }
                if (!theSame((OperationReference) ima, reply)) {
                    return false;
                }
                return true;
            }

            private boolean theSame(PartnerLinkReference pl1, PartnerLinkReference pl2) {
                BpelReference<PartnerLink> ref1 = pl1.getPartnerLink();

                if (ref1 == null) {
                    return false;
                }
                BpelReference<PartnerLink> ref2 = pl2.getPartnerLink();

                if (ref2 == null) {
                    return false;
                }
                return ref1.get() == ref2.get();
            }

            private boolean theSame(OperationReference op1, OperationReference op2) {
                WSDLReference<Operation> ref1 = op1.getOperation();

                if (ref1 == null) {
                    return false;
                }
                WSDLReference<Operation> ref2 = op2.getOperation();

                if (ref2 == null) {
                    return false;
                }
                return ref1.get() == ref2.get();
            }

            private void collectMessageExchangeHolders(BpelEntity entity, List<Reply> replies, List<MessageExchangeReference> IMAs) {
                List<BpelEntity> children = entity.getChildren();

                for (BpelEntity child : children) {
                    collectMessageExchangeHolders(child, replies, IMAs);

                    if (!(child instanceof MessageExchangeReference)) {
                        continue;
                    }
                    if (!hasMessageExchange((MessageExchangeReference) child)) {
                        continue;
                    }
                    if (child instanceof Reply) {
                        replies.add((Reply) child);
                    } else {
                        IMAs.add((MessageExchangeReference) child);
                    }
                }
            }

            private boolean hasMessageExchange(MessageExchangeReference holder) {
                BpelReference<MessageExchange> reference = holder.getMessageExchange();

                if (reference == null) {
                    return false;
                }
                return reference.get() != null;
            }

            @Override
            public void visit(Scope scope) {
                visitBaseScope(scope);

                /*
                 * A scope with the isolated  attribute set to "yes" is called
                 * an isolated scope. Isolated scopes MUST NOT contain other isolated scopes.
                 */
                TBoolean isolated = scope.getIsolated();
                if (TBoolean.YES.equals(isolated)) {
                    List<Component> collection = new ArrayList<Component>();
                    collectIsolatedScopes(scope, collection);

                    if (collection.size() > 0) {
                        collection.add(scope);

                        for (Component component : collection) {
                            addError("FIX_SA00091", component); // NOI18N
                        }
                    }
                }
            }

            @Override
            public void visit(LinkContainer container) {
                /*
                 * A links name MUST be unique amongst all link names
                 * defined within the same immediately enclosing flow.
                 * This requirement MUST be statically enforced.
                 */
                Link[] links = container.getLinks();
                if (links == null) {
                    return;
                }
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();

                for (Link link : links) {
                    addNamedToMap(link, map);
                }
                addErrorForNamed(map, "FIX_SA00064"); // NOI18N
            }

            @Override
            public void visit(SourceContainer container) {
                /*
                 * An activity MAY declare itself to be the source
                 * of one or more links by including one or more <source> elements.
                 * Each <source> element MUST use a distinct link name.
                 */
                Source[] sources = container.getSources();
                if (sources == null) {
                    return;
                }
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();

                for (Source source : sources) {
                    addNamedToMap(source, map, LazyHolder.SOURCE_LINK_NAME_ACCESS);
                }
                addErrorForNamed(map, "FIX_SA00065"); // NOI18N
            }

            @Override
            public void visit(TargetContainer container) {
                /*
                 * An activity MAY declare itself to be the target of one or more
                 * links by including one or more <target> elements. Each <target>
                 * element associated with a given activity MUST use a link name distinct
                 * from all other <target> elements at that activity.
                 */
                Target[] targets = container.getTargets();
                if (targets == null) {
                    return;
                }
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();

                for (Target target : targets) {
                    addNamedToMap(target, map, LazyHolder.TARGET_LINK_NAME_ACCESS);
                }
                addErrorForNamed(map, "FIX_SA00068_SA00069"); // NOI18N
            }

            @Override
            public void visit(ForEach forEach) {
                /*
                 * For <forEach> the enclosed scope MUST NOT declare a variable
                 * with the same name as specified in the counterName  attribute of <forEach>.
                 */
                String counterName = forEach.getCounterName();
                if (counterName == null) {
                    return;
                }
                ContainerIterator<BaseScope> containerIterator = new ContainerIterator<BaseScope>(forEach, BaseScope.class);
                BaseScope scope = containerIterator.next();

                if (scope == null) {
                    return;
                }
                VariableContainer container = scope.getVariableContainer();

                if (container == null) {
                    return;
                }
                Variable[] variables = container.getVariables();

                if (variables == null) {
                    return;
                }
                for (Variable variable : variables) {
                    if (counterName.equals(variable.getName())) {
                        Collection<Component> collection = new ArrayList<Component>(2);
                        collection.add(forEach);
                        collection.add(variable);

                        for (Component component : collection) {
                            addError("FIX_SA00076_SA00077_SA00078", component, counterName); // NOI18N
                        }
                        break;
                    }
                }
                // Check Variable name
                checkVariableName(forEach);
            }

            @Override
            public void visit(VariableContainer container) {
                checkVariableContainer(container);
            }

            @Override
            public void visit(OnEvent onEvent) {
                if (onEvent.getVariable() != null && onEvent.getMessageType() == null && onEvent.getElement() == null) {
                    addError("FIX_SA00090", onEvent); // NOI18N
                }
                /*
                 * the portType attribute is included for readability,
                 * in a <receive>, <reply>, <invoke>, <onEvent> or <onMessage>
                 * element, the value of the portType  attribute MUST match the
                 * portType value implied by the combination of the specified
                 * partnerLink and the role implicitly specified by the activity.
                 */
                checkPortTypeCombinedPartnerLink(onEvent);

                /*
                 * Check variable name.
                 */
                checkVariableName(onEvent);

                /*
                 * For <onEvent>, the type of the variable (as specified by the
                 * messageType attribute) MUST be the same as the type of the input
                 * message defined by operation referenced by the operation attribute.
                 * Optionally the messageType attribute may be omitted and instead the
                 * element attribute substituted if the message to be received has a
                 * single part and that part is defined with an element type.
                 * That element type MUST be an exact match of the element type
                 * referenced by the element attribute.
                 */
                checkMessageType(onEvent);

                /*
                 * For <onEvent>, variables referenced by the variable attribute of
                 * <fromPart> elements or the variable attribute of an <onEvent> element
                 * are implicitly declared in the associated scope of the event handler.
                 * Variables of the same names MUST NOT be explicitly declared in the
                 * associated scope. The variable references are resolved to the
                 * associated scope only and MUST NOT be resolved to the ancestor
                 * scopes.
                 */
                checkImplicitlyDeclaredVars(onEvent);
                FromPartContainer fromParts = onEvent.getFromPartContaner();

                if (fromParts != null) {
                    for (FromPart part : fromParts.getFromParts()) {
                        checkVariableName(part);
                    }
                }
            }

            @Override
            public void visit(EventHandlers handlers) {
                /*
                 * An event handler MUST contain at least one <onEvent> or <onAlarm>  element.
                 */
                if (handlers.sizeOfOnAlarms() == 0 && handlers.sizeOfOnEvents() == 0) {
                    addError("FIX_SA00083_SA00088", handlers); // NOI18N
                }
            }

            @Override
            public void visit(Catch catc) {
                /*
                 * For the <catch> construct; to have a defined type
                 * associated with the fault variable, the faultVariable
                 * attribute MUST only be used if either the faultMessageType or
                 * faultElement attributes, but not both, accompany it.
                 * The faultMessageType and faultElement attributes MUST NOT be
                 * used unless accompanied by faultVariable attribute.
                 */
                String faultVariable = catc.getFaultVariable();
                SchemaReference<GlobalElement> element = catc.getFaultElement();
                WSDLReference<Message> message = catc.getFaultMessageType();

                if (faultVariable != null && element == null && message == null) {
                    addError("FIX_SA00081", catc); // NOI18N
                }
                if (element != null && message != null) {
                    addError("FIX_SA00081", catc); // NOI18N
                }
                if (faultVariable == null && (element != null || message != null)) {
                    addError("FIX_SA00081", catc); // NOI18N
                }
                // Check fault variable name
                checkVariableName(catc);
            }

            @Override
            public void visit(FaultHandlers handlers) {
                /*
                 * There MUST be at least one <catch> or <catchAll>  element
                 * within a <faultHandlers> element.
                 */
                if (handlers.sizeOfCathes() == 0 && handlers.getCatchAll() == null) {
                    addError("FIX_SA00080", handlers); // NOI18N
                }

                // The root scope inside a FCT-handler MUST not have a compensation handler.
                checkFCTScope(handlers);
            }

            @Override
            public void visit(Import imp) {
                Model model = getImportModel(imp);

                /*
                 * If the model is null - skip all other checks, but DO NOT generate
                 * a warning or an error, as it will be done by other validator.
                 */
                if (model == null) {
                    return;
                }
                String namespace = imp.getNamespace();
                String ns = getTargetNamespace(imp);

                if (namespace == null) {
                    /*
                     * If no namespace is specified then the imported definitions MUST NOT
                     * contain a targetNamespace specification.
                     * This requirement MUST be statically enforced.
                     */
                    if (ns != null) {
                        addError("FIX_SA00012", imp); // NOI18N
                    }
                } else {
                    /*
                     * If a namespace attribute is specified on an <import> then
                     * the imported definitions MUST be in that namespace.
                     * This requirement MUST be statically enforced.
                     */
                    if (!namespace.equals(ns)) {
//out();
//out("namespace: " + namespace);
//out("       ns: " + ns);
//out();
                        addError("FIX_SA00011", imp); // NOI18N
                    }
                }

                /*
                 * The value of the importType attribute of element <import>
                 * MUST be set to http://www.w3.org/2001/XMLSchema when importing XML
                 * Schema 1.0 documents, and to http://schemas.xmlsoap.org/wsdl/ when
                 * importing WSDL 1.1 documents.
                 */
                checkImportType(imp);
            }

            @Override
            public void visit(Variable variable) {
                checkVariableName(variable);

                /*
                 * The messageType, type or element attributes are used to
                 * specify the type of a variable. Exactly one of these attributes
                 * MUST be used.
                 */
                int count = variable.getType() == null ? 0 : 1;
                count += variable.getMessageType() == null ? 0 : 1;
                count += variable.getElement() == null ? 0 : 1;

                if (count != 1) {
                    addError("FIX_SA00025", variable); // NOI18N
                }
            }

            @Override
            public void visit(Pick pick) {
                if (TBoolean.YES.equals(pick.getCreateInstance()) && pick.sizeOfOnAlarms() != 0) {
                    Collection<Component> collection = Arrays.asList((Component[]) pick.getOnAlarms());

                    for (Component component : collection) {
                        addError("FIX_SA00062", component); // NOI18N
                    }
                }
                /*
                 * A "start activity" is a <receive> or <pick> activity that is
                 * annotated with a createInstance="yes" attribute.
                 * Activities other than the following: start activities, <scope>,
                 * <flow> and <sequence> MUST NOT be performed prior to
                 * or simultaneously with start activities.
                 */
                checkOrderOfActivities(pick);
            }

            @Override
            public void visit(Flow flow) {
                /*
                 * Every link declared within a <flow> activity MUST have exactly
                 * one activity within the <flow> as its source and exactly
                 * one activity within the <flow> as its target.
                 *
                 * and this method also peform one more:
                 *
                 * Two different links MUST NOT share the same source
                 * and target activities; that is, at most one link may be used
                 * to connect two activities.
                 * (check for this rule will be started only when previous rule
                 * is succeeded).
                 *
                 * A link that crosses a <faultHandlers> or <terminationHandler>
                 * element boundary MUST be outbound only, that is, it MUST have its
                 * source activity within the <faultHandlers> or <terminationHandler>,
                 * and its target activity outside of the scope associated with the handler.
                 *
                 * A link MUST NOT cross the boundary of a repeatable construct or
                 * the <compensationHandler> element. This means, a link used within a
                 * repeatable construct (<while>, <repeatUntil>, <forEach>, <eventHandlers>)
                 * or a <compensationHandler>  MUST be declared in a <flow> that is itself
                 * nested inside the repeatable construct or <compensationHandler>.
                 */
                checkLinks(flow);
            }

            @Override
            public void visit(CompensationHandler handler) {
                /*
                 * The root scope inside a FCT-handler MUST not have a compensation handler.
                 */
                checkFCTScope(handler);
            }

            @Override
            public void visit(TerminationHandler handler) {
                /*
                 * The root scope inside a FCT-handler MUST not have a compensation handler.
                 */
                checkFCTScope(handler);
            }

            @Override
            public void visit(CorrelationSet set) {
                /*
                 * Properties used in a <correlationSet> MUST be defined
                 * using XML Schema simple types. This restriction MUST be statically
                 * enforced.
                 */
                List<WSDLReference<CorrelationProperty>> list = set.getProperties();
                if (list == null) {
                    return;
                }
                for (WSDLReference<CorrelationProperty> reference : list) {
                    CorrelationProperty property = reference.get();
                    // if it null then reference validator will report about error
                    if (property != null) {
                        NamedComponentReference<GlobalType> typeRef = property.getType();

                        if (typeRef == null || !(typeRef.get() instanceof GlobalSimpleType)) {
                            addError("FIX_SA00045", set); // NOI18N
                        }
                    }
                }
            }

            @Override
            public void visit(PatternedCorrelation correlation) {
                /*
                 * The pattern attribute used in <correlation>  within
                 * <invoke> is required for request-response operations, and
                 * disallowed when a one-way operation is invoked.
                 */
                Pattern pattern = correlation.getPattern();

                BpelContainer container = correlation.getParent().getParent();
                assert container instanceof Invoke;
                Invoke invoke = (Invoke) container;

                // If operation is absent or broken then other validators said about this.

                WSDLReference<Operation> operationRef = invoke.getOperation();
                if (operationRef == null) {
                    return;
                }
                Operation operation = operationRef.get();
                if (operation == null) {
                    return;
                }
                // # 84129
                boolean oneWayOperationPatternExist = pattern != null && operation instanceof OneWayOperation;
                boolean twoWayOperationPatternAbsent = pattern == null && operation instanceof RequestResponseOperation;
                boolean flag = oneWayOperationPatternExist || twoWayOperationPatternAbsent;

                if (flag) {
                    addError("FIX_SA00046", invoke); // NOI18N
                }
            }

            @Override
            public void visit(CorrelationContainer container) {
                /*
                 * Static analysis MUST detect property usages where
                 * propertyAliases for the associated variable's type are not found
                 * in any WSDL definitions directly imported by the WS-BPEL process.
                 */
                BpelContainer parent = container.getParent();

                if (parent instanceof OperationReference && parent instanceof Responder) {
                    checkPropertyUsageInInputMessage((OperationReference) parent, container.getCorrelations());
                }
                if (parent instanceof OperationReference && parent instanceof Requester) {
                    checkPropertyUsageInOutputMessage((OperationReference) parent, container.getCorrelations());
                }
            }

            @Override
            public void visit(PatternedCorrelationContainer container) {
                /*
                 * Static analysis MUST detect property usages where
                 * propertyAliases for the associated variable's type are not found
                 * in any WSDL definitions directly imported by the WS-BPEL process.
                 */
                BpelContainer parent = container.getParent();

                if (parent instanceof OperationReference && parent instanceof Responder) {
                    checkPropertyUsageInInputMessage((OperationReference) parent, container.getPatternedCorrelations());
                }
                if (parent instanceof OperationReference && parent instanceof Requester) {
                    checkPropertyUsageInOutputMessage((OperationReference) parent, container.getPatternedCorrelations());
                }
            }

            @Override
            public void visit(CorrelationSetContainer container) {
                /*
                 * The name of a <correlationSet> MUST be unique amongst the names of
                 * all <correlationSet> defined within the same immediately
                 * enclosing scope.
                 */
                CorrelationSet[] correlations = container.getCorrelationSets();
                if (correlations == null) {
                    return;
                }
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();

                for (CorrelationSet correlation : correlations) {
                    addNamedToMap(correlation, map);
                }
                addErrorForNamed(map, "FIX_SA00044"); // NOI18N
            }

            @Override
            public void visit(PartnerLinkContainer container) {
                /*
                 * The name of a partnerLink MUST be unique amongst the names of all
                 * partnerLinks defined within the same immediately enclosing scope.
                 * This requirement MUST be statically enforced.
                 */
                PartnerLink[] links = container.getPartnerLinks();

                if (links == null) {
                    return;
                }
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();

                for (PartnerLink link : links) {
                    addNamedToMap(link, map);
                }
                addErrorForNamed(map, "FIX_SA00018"); // NOI18N
            }

            @Override
            public void visit(Extension extension) {
                /*
                 * In the case of mandatory extensions declared in the
                 * <extensions> element not supported by a WS-BPEL implementation, the
                 * process definition MUST be rejected.
                 */
                TBoolean mustUnderstand = extension.getMustUnderstand();
                if (TBoolean.YES.equals(mustUnderstand)) {
                    BpelModel model = extension.getBpelModel();

                    if (!model.isSupportedExpension(extension.getNamespace())) {
                        String ns = extension.getNamespace();

                        if (ns == null) {
                            ns = "";
                        }
                        addError("FIX_SA00009", extension, ns); // NOI18N
                    }
                }
            }

            private void checkVariableName(VariableDeclaration declaration) {
                String name = declaration.getVariableName();
                if (name != null && name.indexOf('.') != -1) {
                    addError("FIX_SA00024", declaration); // NOI18N
                }
            }

            private Model getImportModel(Import imp) {
                if (Import.WSDL_IMPORT_TYPE.equals(imp.getImportType())) {
                    return ImportHelper.getWsdlModel(imp, false);
                }
                if (Import.SCHEMA_IMPORT_TYPE.equals(imp.getImportType())) {
                    return ImportHelper.getSchemaModel(imp, false);
                }
                return null;
            }

            public void checkNotificationPortType(BpelEntity bpelEntity,
                    WSDLReference<PortType> portType) {
                if (portType == null || portType.get() == null) {
                    return;
                }
                Collection<Operation> operations = portType.get().getOperations();
                for (Operation operation : operations) {
                    if (operation instanceof NotificationOperation) {
                        addError("FIX_SA00001", bpelEntity); // NOI18N
                    }
                }
            }

            public void checkSolicitResponsePortType(BpelEntity bpelEntity,
                    WSDLReference<PortType> portType) {
                if (portType == null || portType.get() == null) {
                    return;
                }
                Collection<Operation> operations = portType.get().getOperations();
                for (Operation operation : operations) {
                    if (operation instanceof SolicitResponseOperation) {
                        addError("FIX_SA00001", bpelEntity); // NOI18N
                    }
                }
            }

            public void checkOverloadedPortTypeOperation(BpelEntity bpelEntity,
                    WSDLReference<PortType> portType) {
                if (portType == null || portType.get() == null) {
                    return;
                }
                Collection<Operation> operations = portType.get().getOperations();
                Set<String> operationsSet = new HashSet<String>();

                for (Operation operation : operations) {
                    operationsSet.add(operation.getName());
                }

                if (operationsSet.size() != operations.size()) {
                    addError("FIX_SA00002", bpelEntity); // NOI18N
                }
            }

            public void checkInputVariableToPartCombination(Invoke invoke) {
                if (invoke.getInputVariable() != null) {
                    if (invoke.getToPartContaner() != null && invoke.getToPartContaner().sizeOfToParts() != 0) {
                        addError("FIX_SA00051", invoke); // NOI18N
                    }
                }
            }

            public void checkOutputVariableFromPartCombination(Invoke invoke) {
                if (invoke.getOutputVariable() != null) {
                    if (invoke.getFromPartContaner() != null && invoke.getFromPartContaner().sizeOfFromParts() != 0) {
                        addError("FIX_SA00052", invoke); // NOI18N
                    }
                }
            }

            public void checkValidPartAttributeFromPartElement(Invoke invoke) {
                // Only if one or more <fromPart> elements is defined.
                if (invoke.getFromPartContaner() != null && invoke.getFromPartContaner().sizeOfFromParts() != 0) {
                    // For each <fromPart> element.
                    FromPart[] fromParts = invoke.getFromPartContaner().getFromParts();
                    for (FromPart fromPart : fromParts) {
                        boolean valid = false;

                        if (fromPart == null || fromPart.getPart() == null) {
                            return;
                        }
                        WSDLReference<Part> part = fromPart.getPart();

                        Part partRef = part.get();

                        if (partRef != null) {
                            // This will be handled by another rule.
                            if (invoke.getOperation() == null ||
                                    invoke.getOperation().get() == null ||
                                    invoke.getOperation().get().getOutput() == null ||
                                    invoke.getOperation().get().getOutput().getMessage() == null ||
                                    invoke.getOperation().get().getOutput().getMessage().get() == null ||
                                    invoke.getOperation().get().getOutput().getMessage().get().getParts() == null) {
                                return;
                            }
                            for (Part wsdlPart : invoke.getOperation().get().getOutput().getMessage().get().getParts()) {
                                if (wsdlPart.equals(partRef)) {
                                    valid = true;
                                    break;
                                }
                            }
                        }
                        if (!valid) {
                            addError("FIX_SA00053", fromPart); // NOI18N
                        }
                    }
                }
            }

            public void checkValidPartAttributeToPartElement(Invoke invoke) {
                // Only if one or more <toPart> elements is defined.
                if (invoke.getToPartContaner() != null && invoke.getToPartContaner().sizeOfToParts() != 0) {
                    // For each <toPart> element.
                    ToPart[] toParts = invoke.getToPartContaner().getToParts();
                    for (ToPart toPart : toParts) {
                        boolean valid = false;

                        if (toPart == null || toPart.getPart() == null) {
                            return;
                        }
                        WSDLReference<Part> part = toPart.getPart();

                        Part partRef = part.get();
                        if (partRef != null) {
                            // This will be handled by another rule.
                            if (invoke.getOperation() == null || invoke.getOperation().
                                    get() == null ||
                                    invoke.getOperation().get().getInput() == null ||
                                    invoke.getOperation().get().getInput().getMessage() == null ||
                                    invoke.getOperation().get().getInput().getMessage().get() == null ||
                                    invoke.getOperation().get().getInput().getMessage().get().getParts() == null) {
                                return;
                            }
                            for (Part wsdlPart : invoke.getOperation().get().getInput().getMessage().get().getParts()) {
                                if (wsdlPart.equals(partRef)) {
                                    valid = true;
                                    break;
                                }
                            }
                        }
                        if (!valid) {
                            addError("FIX_SA00054", toPart); // NOI18N
                        }
                    }
                }
            }

            /**
             * For toPart usage, check whether all parts in the WSDL message are
             * completely assigned.
             */
            public void checkAnyMissingToPartElementInInvoke(Invoke invoke) {

                // This will be handled by another rule.
                if (invoke.getOperation() == null || invoke.getOperation().get() == null ||
                        invoke.getOperation().get().getInput() == null ||
                        invoke.getOperation().get().getInput().getMessage() == null ||
                        invoke.getOperation().get().getInput().getMessage().get() == null ||
                        invoke.getOperation().get().getInput().getMessage().get().
                        getParts() == null) {
                    return;
                } else {
                    if (invoke.getToPartContaner() == null || invoke.getToPartContaner().sizeOfToParts() == 0) {
                        return;
                    }
                    for (Part wsdlPart : invoke.getOperation().get().getInput().getMessage().get().getParts()) {
                        // Each part in the wsdl message must be assigned via a <toPart>
                        boolean assigned = false;
                        ToPart[] toParts = invoke.getToPartContaner().getToParts();
                        for (ToPart toPart : toParts) {
                            // # 84147
                            WSDLReference<Part> partRef = toPart.getPart();
                            if (partRef == null) {
                                continue;
                            }
                            Part part = partRef.get();
                            if (part != null && wsdlPart.equals(part)) {
                                assigned = true;
                                break;
                            }
                        }
                        if (!assigned) {
                            addError("FIX_SA00050", invoke); // NOI18N
                            break;
                        }
                    }
                }
            }

            public void checkAnyMissingToPartElementInReply(Reply reply) {
                if (reply.getOperation() == null || reply.getOperation().get() == null ||
                        reply.getOperation().get().getOutput() == null ||
                        reply.getOperation().get().getOutput().getMessage() == null ||
                        reply.getOperation().get().getOutput().getMessage().get() == null ||
                        reply.getOperation().get().getOutput().getMessage().get().getParts() == null) {
                    return;
                } else {
                    if (reply.getToPartContaner() == null || reply.getToPartContaner().sizeOfToParts() == 0) {
                        return;
                    }
                    for (Part wsdlPart : reply.getOperation().get().getOutput().getMessage().get().getParts()) {
                        // Each part in the wsdl message must be assigned via a <toPart>
                        boolean assigned = false;
                        ToPart[] toParts = reply.getToPartContaner().getToParts();

                        for (ToPart toPart : toParts) {
                            WSDLReference<Part> partRef = toPart.getPart();
                            if (partRef == null) {
                                continue;
                            }
                            Part part = partRef.get();
                            if (part != null && wsdlPart.equals(part)) {
                                assigned = true;
                                break;
                            }
                        }
                        if (!assigned) {
                            addError("FIX_SA00050", reply); // NOI18N
                            break;
                        }
                    }
                }
            }

            public void checkReceiveVariableFromPartCombination(Receive receive) {
                if (receive == null) {
                    return;
                }
                if (receive.getVariable() != null) {
                    if (receive.getFromPartContaner() != null && receive.getFromPartContaner().sizeOfFromParts() != 0) {
                        addError("FIX_SA00055", receive); // NOI18N
                    }
                }
                // SA00047
                WSDLReference<Operation> operationRef = receive.getOperation();

                if (operationRef == null) {
                    return;
                }
                Operation operation = operationRef.get();

                if (operation == null) {
                    return;
                }
                if (isAbsentInputVariable(receive, operation)) {
                    addError("FIX_SA00047_4", receive); // NOI18N
                }
            }

            public void checkOnMessageVariableFromPartCombination(OnMessage onMessage) {
                if (onMessage == null) {
                    return;
                }
                if (onMessage.getVariable() != null) {
                    if (onMessage.getFromPartContaner() != null && onMessage.getFromPartContaner().sizeOfFromParts() != 0) {
                        addError("FIX_SA00063", onMessage); // NOI18N
                    }
                }
            }

            public void checkReplyVariableToPartCombination(Reply reply) {
                if (reply == null) {
                    return;
                }
                if (reply.getVariable() != null) {
                    if (reply.getToPartContaner() != null && reply.getToPartContaner().sizeOfToParts() != 0) {
                        addError("FIX_SA00059", reply); // NOI18N
                    }
                }
                // SA00047
                WSDLReference<Operation> operationRef = reply.getOperation();

                if (operationRef == null) {
                    return;
                }
                Operation operation = operationRef.get();

                if (operation == null) {
                    return;
                }
                if (isAbsentOutputVariable(reply, operation)) {
                    addError("FIX_SA00047_6", reply); // NOI18N
                }
            }

            private void checkPortTypeCombinedPartnerLink(PortTypeReference portTypeReference) {
//out();
//out("see: " + portTypeReference);
                if (!(portTypeReference instanceof PartnerLinkReference)) {
                    return;
                }
//out("    1");
                PartnerLinkReference partnerLinkReference = (PartnerLinkReference) portTypeReference;
                WSDLReference<PortType> portTypeDirectRef = portTypeReference.getPortType();

                if (portTypeDirectRef == null) {
                    return;
                }
//out("    2");
                BpelReference<PartnerLink> partnerLinkRef = partnerLinkReference.getPartnerLink();
                NamedComponentReference<PortType> portTypeRef = Utils.getPortTypeRef(partnerLinkRef, (Component) portTypeReference);

                if (portTypeRef == null || !Utils.equals(portTypeDirectRef.get(), portTypeRef.get())) {
                    addError("FIX_SA00005", (BpelEntity) portTypeReference); // NOI18N
                }
//out("    1: " + getName(portTypeRef.get()));
//out("    2: " + getName(portTypeDirectRef.get()));
            }

            private void addNamedToMap(BpelEntity named, Map<String, Collection<Component>> map) {
                addNamedToMap(named, map, DEFAULT_NAME_ACESS);
            }

            private void addErrorForNamed(Map<String, Collection<Component>> map, String key) {
                for (Entry<String, Collection<Component>> entry : map.entrySet()) {
                    String name = entry.getKey();
                    assert name != null;
                    Collection<Component> collection = entry.getValue();

                    if (collection != null && collection.size() > 1) {
                        for (Component component : collection) {
                            addError(key, component, getName(component));
                        }
                    }
                }
            }

            private void addNamedToMap(BpelEntity entity, Map<String, Collection<Component>> map, NameAccess access) {
                String name = access.getName(entity);

                if (name == null) {
                    return;
                }
                Collection<Component> collection = map.get(name);

                if (collection == null) {
                    collection = new ArrayList<Component>();
                    map.put(name, collection);
                }
                collection.add(entity);
            }

            private void checkCompensateOccurance(Activity compensate) {
                /*
                 * The <compensate(Scope)> activity MUST only be used from within a faultHandler,
                 * another compensationHandler, or a terminationHandler.
                 */
                if (Utils.hasAscendant(compensate, FaultHandlers.class)) {
                    return;
                }
                if (Utils.hasAscendant(compensate, CompensationHandler.class)) {
                    return;
                }
                if (Utils.hasAscendant(compensate, TerminationHandler.class)) {
                    return;
                }
                addError("FIX_SA00007_SA00008", compensate, compensate.getPeer().getLocalName()); // NOI18N
            }

            private void addNamedActivity(BpelEntity entity, Map<String, Collection<Component>> map) {
                if (entity instanceof ExtendableActivity && entity instanceof NamedElement) {
                    String name = ((NamedElement) entity).getName();

                    if (name == null) {
                        return;
                    }
                    Collection<Component> collection = map.get(name);

                    if (collection == null) {
                        collection = new ArrayList<Component>();
                        map.put(name, collection);
                    }
                    collection.add((Component) entity);
                }
            }

            private void collectIsolatedScopes(BpelContainer container, Collection<Component> collection) {
                List<BpelEntity> children = container.getChildren();

                for (BpelEntity entity : children) {
                    if (entity instanceof Scope && TBoolean.YES.equals(
                            ((Scope) entity).getIsolated())) {
                        collection.add(entity);
                    }
                    if (entity instanceof BpelContainer) {
                        collectIsolatedScopes((BpelContainer) entity, collection);
                    }
                }
            }

            private void checkOrderOfActivities(CreateInstanceActivity activity) {
                if (!TBoolean.YES.equals(activity.getCreateInstance())) {
                    return;
                }
                /*
                 * I will put into this set visited container (sequence and flow)
                 * for avoiding visiting them one more time while following up of tree.
                 *
                 * This will fix bug # 85727
                 */
                Set<CompositeActivity> set = new HashSet<CompositeActivity>();
                ExtendableActivity beforeOrSimultaneously = findPreviouslyPerformedActivities((Activity) activity, set);

                if (beforeOrSimultaneously == null) {
                    return;
                }
//out();
//out("1: " + getName((Component) activity));
//out("2: " + getName(beforeOrSimultaneously));
//out();
                // # 137890
                if (inFlow(beforeOrSimultaneously) && hasCIAfirst(beforeOrSimultaneously)) {
//out("REMOVED: " + getName(beforeOrSimultaneously));
                    return;
                }
                addError("FIX_SA00056", beforeOrSimultaneously); // NOI18N
            }

            private boolean inFlow(BpelEntity entity) {
                if (entity == null) {
                    return false;
                }
                return isFlow(entity.getParent());
            }

            private boolean isFlow(BpelEntity entity) {
                if (entity == null) {
                    return false;
                }
                if (entity instanceof Flow) {
                    return true;
                }
                return isFlow(entity.getParent());
            }

            private boolean hasCIAfirst(BpelEntity entity) {
                if (entity == null) {
                    return false;
                }
                Component component = entity.getParent();

                if (!(component instanceof BpelContainer)) {
                    return false;
                }
                BpelContainer parent = (BpelContainer) component;
                int k = parent.indexOf(BpelEntity.class, entity);
//out("k: " + k);
                if (k == -1) {
                    return false;
                }
                List<BpelEntity> children = parent.getChildren();
                int i = 0;

                for (BpelEntity child : children) {
                    if (i >= k) {
                        break;
                    }
                    if (child instanceof CreateInstanceActivity && TBoolean.YES.equals(((CreateInstanceActivity) child).getCreateInstance())) {
//out("found: " + getName(child));
                        return true;
                    }
                    i++;
                }
                return false;
            }

            private void visitBaseScope(BaseScope baseScope) {
                /*
                 * The name of a named activity MUST be unique amongst
                 * all named activities present within the same immediately
                 * enclosing scope. This requirement MUST be statically enforced.
                 */
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();
                collectActivitiesInScope(baseScope, map);
                addErrorForNamed(map, "FIX_SA00092"); // NOI18N

                /*
                 * If the value of exitOnStandardFault of a <scope> or <process>
                 * is set to "yes", then a fault handler that explicitly targets the
                 * WS-BPEL standard faults MUST NOT be used in that scope.
                 * A process definition that violates this condition MUST be
                 * detected and rejected by static analysis.
                 */
                if (TBoolean.YES.equals(baseScope.getExitOnStandardFault()) &&
                        baseScope.getFaultHandlers() != null) {
                    Catch[] catches = baseScope.getFaultHandlers().getCatches();
                    Collection<Component> collection = new ArrayList<Component>();
                    for (Catch catc : catches) {
                        QName qName = catc.getFaultName();
                        if (qName != null && BpelEntity.BUSINESS_PROCESS_NS_URI.equals(qName.getNamespaceURI())) {
                            /*
                             * suggest that all qnames in bpws namespace are standart
                             * faults (may be this is wrong sugestion)
                             */
                            collection.add(catc);
                        }
                    }
                    if (collection.size() > 0) {
                        addErrorCollection("FIX_SA00003", collection); // NOI18N
                    }
                }
            }

            private void collectActivitiesInScope(BpelContainer container, Map<String, Collection<Component>> map) {
                List<BpelEntity> children = container.getChildren();

                for (BpelEntity entity : children) {
                    addNamedActivity(entity, map);
                    if (entity instanceof BaseScope) {
                        // we do not need to go further in scope for searching activities with duplicate names.
                        continue;
                    }
                    if (entity instanceof BpelContainer) {
                        collectActivitiesInScope((BpelContainer) entity, map);
                    }
                }
            }

            private void checkImportType(Import imp) {
                String importType = imp.getImportType();

                if (!Import.WSDL_IMPORT_TYPE.equals(importType) && !Import.SCHEMA_IMPORT_TYPE.equals(importType)) {
                    addError("FIX_SA00013", imp); // NOI18N
                }
            }

            private void checkInstantiableActivities(Process process) {
                Collection<Activity> collection = getInstantiableActivities(process);

                if (collection.size() == 0) {
                    addError("FIX_SA00015", process); // NOI18N
                }

                if (collection.size() > 0) {
                    checkCorellations(collection);
                }
            }

            private void checkMessageType(OnEvent onEvent) {
                WSDLReference<Message> messageRef = onEvent.getMessageType();
                WSDLReference<Operation> operationRef = onEvent.getOperation();
                if (operationRef == null || operationRef.get() == null) {
                    return;
                }
                Operation operation = operationRef.get();

                if (messageRef != null) {
                    if (!checkMessageTypeInOnEvent(messageRef, operation)) {
                        addError("FIX_SA00087", onEvent); // NOI18N
                    }
                } else {
                    SchemaReference<GlobalElement> elementRef = onEvent.getElement();

                    if (elementRef == null) {
                        return;
                    }
                    if (!checkElementInOnEvent(elementRef, operation)) {
                        addError("FIX_SA00087", onEvent); // NOI18N
                    }
                }
            }

            private void checkLinks(Flow flow) {
                LinkContainer linkContainer = flow.getLinkContainer();

                if (linkContainer == null) {
                    return;
                }
                Link[] links = linkContainer.getLinks();
                Set<Link> list = new HashSet<Link>(Arrays.asList(links));
                List<BpelEntity> children = flow.getChildren();

                Map<Link, Collection<Component>> sources = new HashMap<Link, Collection<Component>>();
                Map<Link, Collection<Component>> targets = new HashMap<Link, Collection<Component>>();

                for (BpelEntity child : children) {
                    collectLinks(child, list, sources, targets);
                }
                Map<Pair<Component>, Collection<Link>> foundSourcesAndTargets = new HashMap<Pair<Component>, Collection<Link>>();
                for (Link link : list) {
                    boolean isUsed = false;
                    Collection<Component> collection = sources.get(link);
                    isUsed = checkLink(link, collection, "FIX_SA00066"); // NOI18N
                    Component source = null;

                    if (isUsed) {
                        source = collection.iterator().next();
                    }
                    collection = targets.get(link);

                    if (!isUsed || !checkLink(link, collection, "FIX_SA00066")) { // NOI18N
                        addError("FIX_SA00066", link); // NOI18N
                    } else {
                        Component target = collection.iterator().next();
                        /*
                         * Here we perform one more check. It will be started only when
                         * link appear between  source and target (from previous check)
                         * for just ONE activity as source and ONE activity as target.
                         */
                        checkLinkSingleton(foundSourcesAndTargets, link, source, target);
                        checkLinkBoundaries(link, (BpelEntity) source, (BpelEntity) target);
                    }
                }
            }

            private void checkInputOutputVariableOperation(Invoke invoke) {
                WSDLReference<Operation> operationRef = invoke.getOperation();
                if (operationRef == null) {
                    return;
                }
                Operation operation = operationRef.get();

                if (operation instanceof RequestResponseOperation) {
                    if (isAbsentInputVariable(invoke, operation) || isAbsentOutputVariable(invoke, operation)) {
                        addError("FIX_SA00047_2", invoke); // NOI18N
                    }
                    else {
                        // only if all is ok we start the next check - equality of variable types and message type
                        checkInputVariable(invoke, operation);
                        checkOutputVariable(invoke, operation);
                    }
                } else if (operation instanceof OneWayOperation) {
                    if (isAbsentInputVariable(invoke, operation)) {
                        addError("FIX_SA00047", invoke); // NOI18N
                    }
                    else {
                        // only if all is ok we start the next check - equality of variable types and message type
                        checkInputVariable(invoke, operation);
                    }
                }
            }

            private void addErrorCollection(String key, Collection<Component> collection) {
                for (Component component : collection) {
                    addError(key, component);
                }
            }

            private void checkFCTScope(BpelContainer container) {
                Collection<Scope> scopes = getScopes(container);
                for (Scope scope : scopes) {
                    if (scope.getCompensationHandler() != null) {
                        Collection<Component> collection =
                                new ArrayList<Component>(2);
                        collection.add(container);
                        collection.add(scope);
                        addErrorCollection("FIX_SA00079", collection); // NOI18N
                    }
                }
            }

            private void checkPropertyAliasMultiplicity(Process process) {
                Collection<PropertyAlias> aliases =
                        getPropertyAliases(null, null, process.getBpelModel());
                Set<Pair<QName>> qNames = new HashSet<Pair<QName>>(); // # 80412
                for (PropertyAlias alias : aliases) {
                    NamedComponentReference<CorrelationProperty> propRef =
                            alias.getPropertyName();
                    NamedComponentReference<Message> messageRef = alias.getMessageType();
                    if (propRef != null && messageRef != null) {
                        QName name = propRef.getQName();
                        QName forCheckName = new QName(name.getNamespaceURI(), name.getLocalPart());
                        QName message = messageRef.getQName();      // # 80412
                        QName forCheckMessage = new QName(message.getNamespaceURI(), message.getLocalPart());
                        Pair<QName> pair = new Pair<QName>(forCheckName, forCheckMessage); // # 80412
                        if (qNames.contains(pair)) {
                            addError("FIX_SA00022", process); // NOI18N
                        } else {
                            qNames.add(pair);
                        }
                    }
                }
            }

            private void checkPropertyUsageInInputMessage(OperationReference reference, BaseCorrelation[] correlations) {
                if (correlations.length == 0) {
                    return;
                }
                WSDLReference<Operation> operationRef = reference.getOperation();
                if (operationRef == null) {
                    return;
                }
                Operation operation = operationRef.get();
                if (operation == null) {
                    return;
                }
                Message message = getInputMessage(operation);
                if (message == null) {
                    return;
                }
                for (BaseCorrelation correlation : correlations) {
                    boolean flag = true;
                    if (correlation instanceof PatternedCorrelation) {
                        Pattern pattern = ((PatternedCorrelation) correlation).getPattern();
                        flag = Pattern.isRequestApplicable(pattern);
                    }
                    if (flag) {
                        checkPropertyList(correlation, message);
                    }
                }
            }

            private void checkPropertyUsageInOutputMessage(OperationReference reference, BaseCorrelation[] correlations) {
                if (correlations.length == 0) {
                    return;
                }
                WSDLReference<Operation> operationRef = reference.getOperation();
                if (operationRef == null) {
                    return;
                }
                Operation operation = operationRef.get();
                if (operation == null) {
                    return;
                }
                Message message = getOutputMessage(operation);
                if (message == null) {
                    return;
                }
                for (BaseCorrelation correlation : correlations) {
                    boolean flag = true;
                    if (correlation instanceof PatternedCorrelation) {
                        Pattern pattern = ((PatternedCorrelation) correlation).getPattern();
                        flag = Pattern.isResponseApplicable(pattern);
                    }
                    if (flag) {
                        checkPropertyList(correlation, message);
                    }
                }
            }

            private void checkVariableContainer(VariableContainer container) {
                /*
                 * The name of a variable MUST be unique amongst the names of all
                 * variables defined within the same immediately enclosing scope. This
                 * requirement MUST be statically enforced.
                 */
                Variable[] variables = container.getVariables();
                if (variables == null) {
                    return;
                }
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();

                for (Variable variable : variables) {
                    addNamedToMap(variable, map);
                }
                addErrorForNamed(map, "FIX_SA00023"); // NOI18N
            }

            private void checkImplicitlyDeclaredVars(OnEvent onEvent) {
                FromPartContainer parts = onEvent.getFromPartContaner();

                if (parts == null || parts.getFromParts() == null) {
                    return;
                }
                /*
                 * This map is used for collecting duplicate variable declarations in
                 * fromParts.
                 */
                Map<String, Collection<Component>> map = new HashMap<String, Collection<Component>>();
                /*
                 * This map is used for collecting all fromParts names. It will be used
                 * for checking absence of duplicate varaible names.
                 */
                Map<String, Component> mapVarsInScope = new HashMap<String, Component>();
                for (VariableDeclaration decl : parts.getFromParts()) {
                    addNamedToMap(decl, map, LazyHolder.VAR_DECL_NAME_ACCESS);
                    mapVarsInScope.put(decl.getVariableName(), decl);
                }

                addNamedToMap(onEvent, map, LazyHolder.VAR_DECL_NAME_ACCESS);
                mapVarsInScope.put(onEvent.getVariableName(), onEvent);

                Scope scope = onEvent.getScope();
                if (scope != null && scope.getVariableContainer() != null) {
                    VariableContainer container = scope.getVariableContainer();
                    Variable[] variables = container.getVariables();

                    for (Variable variable : variables) {
                        String name = variable.getName();

                        if (mapVarsInScope.containsKey(name)) {
                            addNamedToMap(variable, map, LazyHolder.VAR_DECL_NAME_ACCESS);
                        }
                    }
                }
                addErrorForNamed(map, "FIX_SA00084_SA00086_SA00089"); // NOI18N
            }

            private void checkCorellations(Collection<Activity> collection) {
                Set<CorrelationSet> sharedCorrelations = new HashSet<CorrelationSet>();
                boolean first = true;
                Collection<Component> components = new ArrayList<Component>(
                        collection.size());
                for (Activity activity : collection) {
                    components.add(activity);
                    Collection<CorrelationSet> correlations =
                            getJoinedCorrelationSets(activity);
                    if (first) {
                        first = false;
                        sharedCorrelations.addAll(correlations);
                    } else {
                        sharedCorrelations.retainAll(correlations);
                        checkEmptySet(sharedCorrelations, components);
                    }
                }
            }

            private void checkEmptySet(Set<CorrelationSet> sharedCorrelations, Collection<Component> components) {
                if (sharedCorrelations.size() == 0) {
                    addErrorCollection("FIX_SA00057", components); // NOI18N
                }
            }

            @SuppressWarnings("unchecked") // NOI18N
            private Collection<CorrelationSet> getJoinedCorrelationSets(Activity activity) {
                if (activity.getElementType().equals(Receive.class)) {
                    Receive receive = (Receive) activity;
                    return getJoinedCorrelationSets(receive.getCorrelationContainer());
                } else if (activity.getElementType().equals(Pick.class)) {
                    Pick pick = (Pick) activity;
                    OnMessage[] onMessages = pick.getOnMessages();
                    Collection<CorrelationSet> collection = null;
                    for (OnMessage onMessage : onMessages) {
                        if (collection == null) {
                            collection = getJoinedCorrelationSets(
                                    onMessage.getCorrelationContainer());
                        } else {
                            collection.addAll(getJoinedCorrelationSets(
                                    onMessage.getCorrelationContainer()));
                        }
                    }
                    // # 83773
                    if (collection == null) {
                        collection = Collections.EMPTY_LIST;
                    }
                    return collection;
                }
                return Collections.EMPTY_LIST;
            }

            private void checkPropertyList(BaseCorrelation correlation, Message message) {
                BpelReference<CorrelationSet> setRef = correlation.getSet();

                if (setRef == null) {
                    return;
                }
                CorrelationSet set = setRef.get();

                if (set == null) {
                    return;
                }
                List<WSDLReference<CorrelationProperty>> list = set.getProperties();

                if (list == null) {
                    return; // # 80696
                }
                for (WSDLReference<CorrelationProperty> reference : list) {
                    if (reference == null) {
                        continue;
                    }
                    Collection<PropertyAlias> collection = getPropertyAliases(reference.getQName(), message, correlation.getBpelModel());

                    if (collection.size() == 0 && reference != null && reference.get() != null) {
                        addError("FIX_SA00021", correlation, reference.get().getName(), set.getName()); // NOI18N
                    }
                }
            }

            @SuppressWarnings("unchecked") // NOI18N
            private Set<PropertyAlias> getPropertyAliases(QName name, Message message, BpelModel model) {
                Import[] imports = model.getProcess().getImports();
                if (imports.length == 0) {
                    return Collections.EMPTY_SET;
                } else {
                    Set<PropertyAlias> list = new HashSet<PropertyAlias>();
                    for (Import imp : imports) {
                        collectPropertyAliases(name, message, imp, list);
                    }
                    return list;
                }
            }

            private void collectPropertyAliases(QName name, Message message, Import imp, Set<PropertyAlias> list) {
                WSDLModel model = ImportHelper.getWsdlModel(imp);

                if (model == null) {
                    return;
                }
                if (model.getState() != State.VALID) {
                    return;
                }
                List<PropertyAlias> properties =
                        model.getDefinitions().getExtensibilityElements(PropertyAlias.class);
                for (PropertyAlias alias : properties) {
                    NamedComponentReference<CorrelationProperty> propRef =
                            alias.getPropertyName();
                    if (propRef == null) {
                        continue;
                    }
                    if (name != null && !Utils.equals(propRef.getQName(), name)) {
                        continue;
                    }
                    if (message == null) {
                        list.add(alias);
                        continue;
                    }
                    NamedComponentReference<Message> messageRef = alias.getMessageType();
                    if (messageRef == null) {
                        continue;
                    } else if (messageRef.references(message)) {
                        list.add(alias);
                    }
                }
            }

            @SuppressWarnings("unchecked") // NOI18N
            private Collection<CorrelationSet> getJoinedCorrelationSets(CorrelationContainer container) {
                Collection<CorrelationSet> collection = new ArrayList<CorrelationSet>();
                if (container == null) {
                    return Collections.EMPTY_LIST;
                }
                Correlation[] correlations = container.getCorrelations();
                for (Correlation correlation : correlations) {
                    if (Initiate.JOIN.equals(correlation.getInitiate())) {
                        BpelReference<CorrelationSet> setRef = correlation.getSet();
                        if (setRef != null && setRef.get() != null) {
                            collection.add(setRef.get());
                        }
                    }
                }
                return collection;
            }

            private Message getInputMessage(Operation operation) {
                Input input = operation.getInput();
                if (input == null) {
                    return null;
                }
                NamedComponentReference<Message> messageRef = input.getMessage();
                if (messageRef == null) {
                    return null;
                }
                return messageRef.get();
            }

            private Message getOutputMessage(Operation operation) {
                Output output = operation.getOutput();

                if (output == null) {
                    return null;
                }
                NamedComponentReference<Message> messageRef = output.getMessage();
                if (messageRef == null) {
                    return null;
                }
                return messageRef.get();
            }

            private void checkInputVariable(Invoke invoke, Operation operation) {
                BpelReference<VariableDeclaration> varRef = invoke.getInputVariable();
                Message message = getInputMessage(operation);
                checkVariable(invoke, varRef, message);
            }

            private void checkOutputVariable(Invoke invoke, Operation operation) {
                BpelReference<VariableDeclaration> varRef = invoke.getOutputVariable();
                Message message = getOutputMessage(operation);
                checkVariable(invoke, varRef, message);
            }

            private void checkVariable(Invoke invoke, BpelReference<VariableDeclaration> varRef, Message message) {
                if (varRef == null) {
                    return;
                }
                VariableDeclaration variable = varRef.get();
                if (variable == null) {
                    return;
                }
                if (message == null) {
                    return;
                }
                WSDLReference<Message> messageRef = variable.getMessageType();
                if (messageRef != null) {
                    if (!messageRef.references(message)) {
                        addError("FIX_SA00048_1", invoke); // NOI18N
                    }
                } else if (variable.getElement() != null) {
                    SchemaReference<GlobalElement> varElement = variable.getElement();

                    if (!checkElementType(message, varElement)) {
                        addError("FIX_SA00048_2", invoke); // NOI18N
                    }
                }
            }

            private boolean checkElementType(Message message, SchemaReference<GlobalElement> varElement) {
                if (message.getParts().size() != 1) {
                    return false;
                }
                Part part = message.getParts().iterator().next();
                NamedComponentReference<GlobalElement> elementRef = part.getElement();

                if (elementRef == null) {
                    return false;
                }
                GlobalElement element = elementRef.get();

                if (element == null) {
                    return false;
                }
                if (varElement.references(element)) {
                    return true;
                }
                return false;
            }

            private boolean isAbsentInputVariable(Invoke invoke, Operation operation) {
                Message message = getInputMessage(operation);

                if (message == null) {
                    return false;
                }
                // # 109292
                if (message.getParts().size() == 0 && invoke.getInputVariable() != null) {
                    addWarning("FIX_SA00047_1", invoke); // NOI18N
                    return false;
                }
                // # 164476
                if (message.getParts().size() == 0 && invoke.getInputVariable() == null) {
                    return false;
                } 
                if (message.getParts().size() != 0 && invoke.getInputVariable() == null && (invoke.getToPartContaner() == null || invoke.getToPartContaner().sizeOfToParts() == 0)) {
                    return true;
                }
                if (message.getParts().size() == 0) {
                    return invoke.getToPartContaner() == null || invoke.getToPartContaner().sizeOfToParts() == 0;
                }
                return false;
            }

            private boolean isAbsentOutputVariable(Reply reply, Operation operation) {
                Message message = getOutputMessage(operation);

                if (message == null) {
                    return false;
                }
                // # 109292
                if (message.getParts().size() == 0 && reply.getVariable() != null) {
                    addWarning("FIX_SA00047_7", reply); // NOI18N
                    return false;
                }
                // # 164476
                if (message.getParts().size() == 0 && reply.getVariable() == null) {
                    return false;
                } 
                if (message.getParts().size() != 0 && reply.getVariable() == null && (reply.getToPartContaner() == null || reply.getToPartContaner().sizeOfToParts() == 0)) {
                    return true;
                }
                if (message.getParts().size() == 0) {
                    return reply.getToPartContaner() == null || reply.getToPartContaner().sizeOfToParts() == 0;
                }
                return false;
            }

            private boolean isAbsentOutputVariable(Invoke invoke, Operation operation) {
                Message message = getOutputMessage(operation);

                if (message == null) {
                    return false;
                }
                // # 109292
                if (message.getParts().size() == 0 && invoke.getOutputVariable() != null) {
                    addWarning("FIX_SA00047_3", invoke); // NOI18N
                    return false;
                }
                // # 164476
                if (message.getParts().size() == 0 && invoke.getOutputVariable() == null) {
                    return false;
                } 
                if (message.getParts().size() != 0 && invoke.getOutputVariable() == null && (invoke.getFromPartContaner() == null || invoke.getFromPartContaner().sizeOfFromParts() == 0)) {
                    return true;
                }
                if (message.getParts().size() == 0) {
                    return invoke.getFromPartContaner() == null || invoke.getFromPartContaner().sizeOfFromParts() == 0;
                }
                return false;
            }

            private boolean isAbsentInputVariable(Receive receive, Operation operation) {
                Message message = getInputMessage(operation);

                if (message == null) {
                    return false;
                }
                // # 109292
                if (message.getParts().size() == 0 && receive.getVariable() != null) {
                    addWarning("FIX_SA00047_5", receive); // NOI18N
                    return false;
                }
                // # 164476
                if (message.getParts().size() == 0 && receive.getVariable() == null) {
                    return false;
                } 
                if (message.getParts().size() != 0 && receive.getVariable() == null && (receive.getFromPartContaner() == null || receive.getFromPartContaner().sizeOfFromParts() == 0)) {
                    return true;
                } 
                if (message.getParts().size() == 0) {
                    return receive.getFromPartContaner() == null || receive.getFromPartContaner().sizeOfFromParts() == 0;
                }
                return false;
            }

            private boolean checkElementInOnEvent(SchemaReference<GlobalElement> ref, Operation operation) {
                GlobalElement element = ref.get();

                if (element == null) {
                    return false;
                }
                NamedComponentReference<Message> messageOperation = getMessageRef(operation);

                if (messageOperation == null) {
                    return false;
                }
                Message message = messageOperation.get();

                if (message == null) {
                    return false;
                }
                Collection<Part> parts = message.getParts();

                if (parts.size() != 1) {
                    return false;
                }
                Part part = parts.iterator().next();
                NamedComponentReference<GlobalElement> elementOperationRef = part.getElement();

                if (elementOperationRef == null) {
                    return false;
                }
                return elementOperationRef.references(element);
            }

            private boolean checkMessageTypeInOnEvent(WSDLReference<Message> messageRef, Operation operation) {
                Message message = messageRef.get();

                if (message == null) {
                    return false;
                }
                NamedComponentReference<Message> messageOperation = getMessageRef(operation);

                if (messageOperation == null) {
                    return false;
                }
                return messageOperation.references(message);
            }

            private NamedComponentReference<Message> getMessageRef(Operation operation) {
                Input input = operation.getInput();

                if (input == null) {
                    return null;
                }
                return input.getMessage();
            }

            private Collection<Scope> getScopes(BpelContainer container) {
                Collection<Scope> collection = new ArrayList<Scope>();
                collectScopes(container, collection);
                return collection;
            }

            private void collectScopes(BpelEntity container,
                    Collection<Scope> collection) {
                if (container instanceof Scope) {
                    collection.add((Scope) container);
                } else {
                    List<BpelEntity> children = container.getChildren();
                    for (BpelEntity child : children) {
                        collectScopes(child, collection);
                    }
                }
            }

            private void checkLinkSingleton(Map<Pair<Component>, Collection<Link>> map,
                    Link link, Component source, Component target) {
                Pair<Component> pair = new Pair<Component>(source, target);
                Collection<Link> linkCollection = map.get(pair);
                if (linkCollection == null) {
                    linkCollection = new HashSet<Link>();
                    map.put(pair, linkCollection);
                }
                if (linkCollection.size() > 0) {
                    Collection<Component> components = new ArrayList<Component>(linkCollection);
                    components.add(link);
                    components.add(source);
                    components.add(target);
                    addErrorCollection("FIX_SA00067", components); // NOI18N
                } else {
                    linkCollection.add(link);
                }
            }

            private boolean checkLink(Link link, Collection<Component> collection, String bundleKey) {
                if (collection != null && collection.size() > 1) {
                    collection.add(link);
                    addErrorCollection(bundleKey, collection);
                }
                return collection != null && collection.size() > 0;
            }

            private Collection<Activity> getInstantiableActivities(BpelContainer container) {
                Collection<Activity> collection = new ArrayList<Activity>();
                collectInstantiableActivities(container, collection);
                return collection;
            }

            private void collectInstantiableActivities(BpelContainer container, Collection<Activity> collection) {
                List<BpelEntity> list = container.getChildren();
                for (BpelEntity entity : list) {
                    if (entity.getElementType().equals(Receive.class) || entity.getElementType().equals(Pick.class)) {
                        TBoolean isInstance = ((CreateInstanceActivity) entity).getCreateInstance();
                        if (TBoolean.YES.equals(isInstance)) {
                            collection.add((Activity) entity);
                        }
                    } else if (entity instanceof BpelContainer) {
                        collectInstantiableActivities((BpelContainer) entity, collection);
                    }
                }
            }

            private void collectLinks(BpelEntity entity, Set<Link> set, Map<Link, Collection<Component>> sourcesMap, Map<Link, Collection<Component>> targetsMap) {
                if (entity instanceof Activity) {
                    collectLinkInTargets((Activity) entity, set, targetsMap);
                    collectLinkInSources((Activity) entity, set, sourcesMap);
                }
                List<BpelEntity> children = entity.getChildren();

                for (BpelEntity child : children) {
                    collectLinks(child, set, sourcesMap, targetsMap);
                }
            }

            private void collectLinkInTargets(Activity activity, Set<Link> set, Map<Link, Collection<Component>> targetsMap) {
                TargetContainer targetContainer = activity.getTargetContainer();
                if (targetContainer != null) {
                    Target[] targets = targetContainer.getTargets();
                    for (Target target : targets) {
                        BpelReference<Link> ref = target.getLink();
                        collectLinks(activity, set, targetsMap, ref);
                    }
                }
            }

            private void collectLinkInSources(Activity activity, Set<Link> set, Map<Link, Collection<Component>> sourcesMap) {
                SourceContainer sourceContainer = activity.getSourceContainer();

                if (sourceContainer != null) {
                    Source[] sources = sourceContainer.getSources();
                    for (Source source : sources) {
                        BpelReference<Link> ref = source.getLink();
                        collectLinks(activity, set, sourcesMap, ref);
                    }
                }
            }

            private void collectLinks(Activity activity, Set<Link> set, Map<Link, Collection<Component>> targetsMap, BpelReference<Link> reference) {
                if (reference == null) {
                    return;
                }
                Link link = reference.get();
                if (set.contains(link)) {
                    Collection<Component> collection = targetsMap.get(link);
                    if (collection == null) {
                        collection = new ArrayList<Component>();
                        targetsMap.put(link, collection);
                    }
                    collection.add(activity);
                }
            }

            private void checkLinkBoundaries(Link link, BpelEntity source, BpelEntity target) {
                checkFTBoundaries(link, source, target);
                checkRepeatableConstract(link, source, target);
            }

            private void checkRepeatableConstract(Link link, BpelEntity source, BpelEntity target) {
                /*
                 * link MUST NOT cross the boundary of a repeatable construct or
                 * the <compensationHandler> element. This means, a link used within a
                 * repeatable construct (<while>, <repeatUntil>, <forEach>, <eventHandlers>)
                 * or a <compensationHandler>  MUST be declared in a <flow> that is itself
                 * nested inside the repeatable construct or <compensationHandler>.
                 */
                BpelContainer flow = link.getParent().getParent();
                Class[] containers = new Class[] { While.class, RepeatUntil.class, ForEach.class, EventHandlers.class, CompensationHandler.class };
                boolean targetInside = getContainer(target, flow, containers) != null;
                boolean sourceInside = getContainer(source, flow, containers) != null;
                Collection<Component> collection = new ArrayList<Component>();
                if (targetInside) {
                    collection.add(target);
                }
                if (sourceInside) {
                    collection.add(source);
                }
                if (collection.size() > 0) {
                    collection.add(link);
                    addErrorCollection("FIX_SA00070", collection); // NOI18N
                }
            }

            private void checkFTBoundaries(Link link, BpelEntity source, BpelEntity target) {
                BpelContainer flow = link.getParent().getParent();
                /*
                 * A link that crosses a <faultHandlers> or <terminationHandler>
                 * element boundary MUST be outbound only, that is, it MUST have its
                 * source activity within the <faultHandlers> or <terminationHandler>,
                 * and its target activity outside of the scope associated with the handler.
                 *
                 */
                boolean badHandlersBoundaries = true;
                Class[] containers = new Class[] { FaultHandlers.class, TerminationHandler.class };
                BpelContainer targetParentHandler = getContainer(target, flow, containers);

                if (targetParentHandler == null || hasParent(source, targetParentHandler, flow)) {
                    // source should be inside the same FT container as target
                    // otherwise link will be inbound
                    badHandlersBoundaries = false;
                }
                if (!badHandlersBoundaries) {
                    BpelContainer sourceParentHandler = getContainer(source, flow,
                            containers);
                    if (sourceParentHandler != null) {
                        BpelContainer scope = sourceParentHandler.getParent();
                        if (hasParent(target, scope, flow) &&
                                !hasParent(target, sourceParentHandler, flow)) {
                            badHandlersBoundaries = true;
                        }
                    }
                }
                if (badHandlersBoundaries) {
                    Collection<Component> collection = new ArrayList<Component>();
                    collection.add(target);
                    collection.add(source);
                    collection.add(link);
                    addErrorCollection("FIX_SA00071", collection); // NOI18N
                }
            }

            @SuppressWarnings("unchecked") // NOI18N
            private BpelContainer getContainer(BpelEntity child, BpelContainer parent, Class... classes) {
                BpelContainer container = child.getParent();

                while (container != null && !container.equals(parent)) {
                    for (Class clazz : classes) {
                        if (clazz.isAssignableFrom(container.getClass())) {
                            return container;
                        }
                    }
                    container = container.getParent();
                }
                return null;
            }

            private boolean hasParent(BpelEntity entity, BpelContainer container, BpelContainer parent) {
                BpelEntity child = entity;

                while (child != null && child != parent) {
                    if (child == container) {
                        return true;
                    }
                    child = child.getParent();
                }
                return false;
            }

            private ExtendableActivity findPreviouslyPerformedActivities(ExtendableActivity activity, Set<CompositeActivity> set) {
                if (!isAcceptableActivity(activity)) {
                    return activity;
                }
                BpelContainer container = activity.getParent();

                if (!(container instanceof ExtendableActivity)) {
                    return null;
                }
                if (container instanceof ActivityHolder) {
                    return findPreviouslyPerformedActivities((ExtendableActivity) container, set);
                }
                if (container instanceof CompositeActivity) {
                    ExtendableActivity found = findExecutableActivity((CompositeActivity) container, activity, set);

                    if (found == null) {
                        found = findPreviouslyPerformedActivities((ExtendableActivity) container, set);
                    }
                    return found;
                }
                return null;
            }

            private ExtendableActivity findExecutableActivity(CompositeActivity container, ExtendableActivity activity, Set<CompositeActivity> set) {
                // # 85727
                set.add(container);

                if (container instanceof Sequence) {
                    Sequence sequence = (Sequence) container;
                    int i = sequence.indexOf(ExtendableActivity.class, activity);
                    return findExecutableActivityInSequence(sequence, i, set);
                } else if (container instanceof Flow) {
                    return findExecutableActivityInFlow((Flow) container, activity, set);
                } else {
                    assert false;
                }
                return null;
            }

            private ExtendableActivity findExecutableActivityInFlow(Flow flow, ExtendableActivity activity, Set<CompositeActivity> compositeActivities) {
                Set<ExtendableActivity> set = getLogicallyPreceding(activity);
                ExtendableActivity found = findDescendantActivity(set);

                if (found != null) {
                    return found;
                }
                return getUntargetedUnacceptableActivity(flow, compositeActivities);
            }

            private ExtendableActivity getUntargetedUnacceptableActivity(Activity activity, Set<CompositeActivity> set) {
                /*
                 * We are trying to find here unacceptable activity inside flow that
                 * do not have target at all.
                 * If there is some activity with target then it precede some other
                 * activity (may be situated inside ascendant flow, not this flow),
                 * so when we appear in appropriate flow we find this preceding
                 * activity. If it has "acceptable" activity then all ok, because
                 * all following (by links order) activity will be after
                 * "acceptable". If it does not have acceptable activity then we will
                 * find it on this step.
                 */
                List<Activity> children = activity.getChildren(Activity.class);
                for (Activity child : children) {
                    if (set.contains(child)) {
                        continue;
                    }
                    TargetContainer container = child.getTargetContainer();
                    if (container == null || container.getTargets().length == 0) {
                        if (!isAcceptableActivity(child)) {
                            return child;
                        }
                        ExtendableActivity found =
                                getUntargetedUnacceptableActivity(child, set);
                        if (found != null) {
                            return found;
                        }
                    }
                }
                return null;
            }

            private ExtendableActivity findDescendantActivity(Set<ExtendableActivity> set) {
                for (ExtendableActivity preceding : set) {
                    ExtendableActivity found = findDescendantActivity(preceding);

                    if (found != null) {
                        return found;
                    }
                }
                return null;
            }

            @SuppressWarnings("unchecked") // NOI18N
            private Set<ExtendableActivity> getLogicallyPreceding(ExtendableActivity activity) {
                /*
                 * This method collect all preceding activities for activity.
                 * So resulting set will contain activities that are source
                 * for some links and those links have activity as target.
                 * Then we put on the place  "activity" found source activities
                 * and search sources for them. And so on.
                 */
                if (!(activity instanceof Activity)) {
                    return Collections.EMPTY_SET;
                }
                Set<ExtendableActivity> set = new HashSet<ExtendableActivity>();
                collectPreceding((Activity) activity, set);
                return set;
            }

            private void collectPreceding(Activity activity, Set<ExtendableActivity> set) {
                if (set.contains(activity)) {
                    return;
                }
                TargetContainer container = activity.getTargetContainer();

                if (container == null) {
                    return;
                }
                Target[] targets = container.getTargets();

                for (Target target : targets) {
                    BpelReference<Link> linkRef = target.getLink();

                    if (linkRef == null) {
                        continue;
                    }
                    Link link = linkRef.get();

                    if (link == null) {
                        continue;
                    }
                    BpelContainer flow = link.getParent().getParent();
                    Activity source = findSource(flow, link);

                    if (source != null) {
                        set.add(source);
                        collectPreceding(source, set);
                    }
                }
            }

            private Activity findSource(BpelContainer container, Link link) {
                List<Activity> children = container.getChildren(Activity.class);
                for (Activity child : children) {
                    SourceContainer sourceContainer = child.getSourceContainer();

                    if (sourceContainer != null && checkSource(link, sourceContainer)) {
                        return child;
                    }
                    if (child instanceof BpelContainer) {
                        Activity found = findSource((BpelContainer) child, link);

                        if (found != null) {
                            return found;
                        }
                    }
                }
                return null;
            }

            private boolean checkSource(Link link, SourceContainer sourceContainer) {
                Source[] sources = sourceContainer.getSources();
                for (Source source : sources) {
                    BpelReference<Link> linkRef = source.getLink();
                    if (linkRef != null && linkRef.references(link)) {
                        return true;
                    }
                }
                return false;
            }

            private ExtendableActivity findExecutableActivityInSequence(Sequence sequence, int i, Set<CompositeActivity> set) {
                ExtendableActivity[] children = sequence.getActivities();
                for (int j = 0; j < i; j++) {
                    if (set.contains(children[j])) {
                        continue;
                    }
                    ExtendableActivity found = findDescendantActivity(children[j]);
                    if (found != null) {
                        return found;
                    }
                }
                return null;
            }

            private ExtendableActivity findDescendantActivity(ExtendableActivity activity) {
                if (!isAcceptableActivity(activity)) {
                    return activity;
                }
                List<ExtendableActivity> children = activity.getChildren(ExtendableActivity.class);
                for (ExtendableActivity child : children) {
                    ExtendableActivity found = findDescendantActivity(child);
                    if (found != null) {
                        return found;
                    }
                }
                return null;
            }

            private boolean isAcceptableActivity(ExtendableActivity activity) {
                if (activity instanceof CreateInstanceActivity) {
                    if (TBoolean.YES.equals(((CreateInstanceActivity) activity).getCreateInstance())) {
                        return true;
                    }
                }
                Class clazz = activity.getElementType();

                return clazz.equals(Scope.class) || clazz.equals(Flow.class) || clazz.equals(Sequence.class);
            }
        };
    }

    // -----------------------------------------------------------
    private static class DefaultNameAccess implements NameAccess {

        public String getName(BpelEntity entity) {
            if (entity instanceof NamedElement) {
                return ((NamedElement) entity).getName();
            }
            return null;
        }
    }

    private static final class LazyHolder {

        static final NameAccess SOURCE_LINK_NAME_ACCESS = new NameAccess() {

            public String getName(BpelEntity entity) {
                if (entity instanceof Source) {
                    BpelReference<Link> ref = ((Source) entity).getLink();
                    if (ref != null && ref.getRefString() != null) {
                        return ref.getRefString();
                    }
                }
                return null;
            }
        };
        static final NameAccess TARGET_LINK_NAME_ACCESS = new NameAccess() {

            public String getName(BpelEntity entity) {
                if (entity instanceof Target) {
                    BpelReference<Link> ref = ((Target) entity).getLink();
                    if (ref != null && ref.getRefString() != null) {
                        return ref.getRefString();
                    }
                }
                return null;
            }
        };
        static final NameAccess VAR_DECL_NAME_ACCESS = new NameAccess() {

            public String getName(BpelEntity entity) {
                if (entity instanceof VariableDeclaration) {
                    return ((VariableDeclaration) entity).getVariableName();
                }
                return null;
            }
        };
    }

    private static interface NameAccess {

        String getName(BpelEntity entity);
    }

    private static final DefaultNameAccess DEFAULT_NAME_ACESS = new DefaultNameAccess();
    private static final String SUPPORTED_LANGAGE = "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0"; // NOI18N
}
