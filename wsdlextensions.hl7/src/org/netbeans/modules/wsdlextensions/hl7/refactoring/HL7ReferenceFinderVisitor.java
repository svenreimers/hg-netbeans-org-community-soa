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
package org.netbeans.modules.wsdlextensions.hl7.refactoring;

import java.util.List;
import org.netbeans.modules.wsdlextensions.hl7.HL7CommunicationControl;
import org.netbeans.modules.wsdlextensions.hl7.HL7CommunicationControls;
import org.netbeans.modules.wsdlextensions.hl7.HL7Component;
import org.netbeans.modules.wsdlextensions.hl7.HL7Address;
import org.netbeans.modules.wsdlextensions.hl7.HL7Binding;
import org.netbeans.modules.wsdlextensions.hl7.HL7Message;
import org.netbeans.modules.wsdlextensions.hl7.HL7Operation;
import org.netbeans.modules.wsdlextensions.hl7.HL7ProtocolProperties;
import org.netbeans.modules.xml.wsdl.model.BindingInput;
import org.netbeans.modules.xml.wsdl.model.BindingOutput;
import org.netbeans.modules.xml.wsdl.model.ExtensibilityElement;
import org.netbeans.modules.xml.wsdl.model.Input;
import org.netbeans.modules.xml.wsdl.model.Message;
import org.netbeans.modules.xml.wsdl.model.Output;
import org.netbeans.modules.xml.wsdl.model.Part;
import org.netbeans.modules.xml.wsdl.model.WSDLComponent;
import org.netbeans.modules.xml.wsdl.model.visitor.ChildVisitor;
import org.netbeans.modules.xml.wsdl.model.visitor.WSDLVisitor;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.Reference;
import org.netbeans.modules.xml.xam.Referenceable;
import org.netbeans.modules.xml.xam.dom.NamedComponentReference;

/**
 *
 * @author skini
 */
public class HL7ReferenceFinderVisitor extends ChildVisitor implements WSDLVisitor, HL7Component.Visitor {

    private Referenceable referenced;
    private List<Component> components;

    public HL7ReferenceFinderVisitor(Referenceable referenced, List<Component> components) {
        this.referenced = referenced;
        this.components = components;
    }

    @Override
    public void visit(ExtensibilityElement ee) {
        if (ee instanceof HL7Message) {
            visit((HL7Message) ee);
        }
        visitComponent(ee);
    }

    public void visit(HL7Address target) {
        //nothing to refactor
    }

    public void visit(HL7Binding target) {
    }

    public void visit(HL7Operation target) {
    }

    public void visit(HL7Message target) {
        if (referenced instanceof Part) {
            Part part = (Part) referenced;
            if (part.getName().equals(target.getPart())) {
                WSDLComponent parent = target.getParent();

                if (parent != null) {
                    NamedComponentReference<Message> messageRef = null;
                    if (parent instanceof BindingInput) {
                        Reference<Input> input = ((BindingInput) parent).getInput();
                        if (input != null && input.get() != null) {
                            messageRef = input.get().getMessage();
                        }
                    } else if (parent instanceof BindingOutput) {
                        Reference<Output> output = ((BindingOutput) parent).getOutput();
                        if (output != null && output.get() != null) {
                            messageRef = output.get().getMessage();
                        }
                    }

                    if (messageRef != null && messageRef.get() != null) {
                        if (messageRef.get().equals(part.getParent())) {
                            components.add(target);
                        }
                    }
                }
            }
        }
    }

    public void visit(HL7ProtocolProperties target) {
        //nothing to refactor
    }

    public void visit(HL7CommunicationControls target) {
        
    }

    public void visit(HL7CommunicationControl target) {
        
    }
}
