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

package org.netbeans.modules.bpel.debugger.variables;

import org.netbeans.modules.bpel.debugger.api.variables.Value;
import org.netbeans.modules.bpel.debugger.api.variables.WsdlMessageValue;
import org.netbeans.modules.bpel.debugger.api.variables.WsdlMessageVariable;
import org.netbeans.modules.bpel.debugger.api.variables.XmlElementValue;
import org.netbeans.modules.bpel.debugger.BreakPosition;
import org.netbeans.modules.bpel.debuggerbdi.rmi.api.BPELVariable;
import org.netbeans.modules.bpel.debuggerbdi.rmi.api.DebuggableEngine;
import org.netbeans.modules.bpel.debuggerbdi.rmi.api.SchemaViolationException;
import org.netbeans.modules.bpel.debuggerbdi.rmi.api.WSDLMessage;
import org.netbeans.modules.bpel.debuggerbdi.rmi.api.XpathExpressionException;
import org.w3c.dom.Node;

/**
 *
 * @author Alexander Zgursky
 */
public class WsdlMessageVariableImpl extends VariableSupport implements WsdlMessageVariable {
    private Value myValue;
    private boolean myValueIsInitialized;
    
    /** Creates a new instance of WsdlMessageVariableImpl */
    public WsdlMessageVariableImpl(String name, BreakPosition breakPosition, BPELVariable engineVariable) {
        super(name, breakPosition, engineVariable);
        assert engineVariable.isWSDLMessage() : "Provided engine variable should be a WSDL Message Variable";
    }
    
    public Value getValue() {
        if (!myValueIsInitialized) {
            myValueIsInitialized = true;
        
            WSDLMessage engineWsdlMessage = getEngineVariable().getWSDLMessage();
            if (engineWsdlMessage != null) {
                myValue = new WsdlMessageValueImpl(getBreakPosition(), this);
            }
        }
        return myValue;
    }

    public void setPartNodeValue(WsdlMessageValue.Part part, Node node, String newValue) {
        //System.out.println("Trying to set part node value for " + this);
        
        DebuggableEngine varContext = getBreakPosition().getFrame().getDebuggableEngine();
        String xpath = XmlElementValue.Helper.xpath(node);
        try {
            varContext.changeVariableMessageTypeValue(getName(), part.getName(), xpath, newValue);
        } catch (XpathExpressionException ex) {
            //TODO:handle this
            ex.printStackTrace();
        } catch (SchemaViolationException ex) {
            //TODO:handle this
            ex.printStackTrace();
        }

    }

    public void setPartSimpleValue(WsdlMessageValue.Part part, String newValue) {
        DebuggableEngine varContext = getBreakPosition().getFrame().getDebuggableEngine();
        
        try {
            varContext.changeVariableMessageTypeValue(getName(), part.getName(), newValue);
        } catch (SchemaViolationException ex) {
            //TODO:handle this
            ex.printStackTrace();
        }
    }
}
