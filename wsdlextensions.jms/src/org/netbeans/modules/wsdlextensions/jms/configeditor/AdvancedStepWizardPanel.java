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
package org.netbeans.modules.wsdlextensions.jms.configeditor;

import java.awt.Component;
import javax.xml.namespace.QName;

import org.netbeans.modules.xml.wsdl.bindingsupport.spi.WSDLWizardContext;
import org.netbeans.modules.xml.wsdl.bindingsupport.spi.WSDLWizardDescriptorPanel;
import org.netbeans.modules.xml.wsdl.model.Definitions;
import org.netbeans.modules.xml.wsdl.model.WSDLComponent;

import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

public class AdvancedStepWizardPanel extends WSDLWizardDescriptorPanel {

    QName mQName = null;
    WSDLComponent mComponent = null;
    WizardDescriptor mWizardDescriptor = null;    

    /**
     * Controller associated with this wizard panel
     */
    InboundPersistenceController mController = null;
    
    public AdvancedStepWizardPanel(WSDLWizardContext context) {
        super(context);
    }

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private JMSAdvancedPanel mPanel;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (mPanel == null) {
            mPanel = new JMSAdvancedPanel(mQName, mComponent);
        }

        if (mController == null) {
            mController = new InboundPersistenceController(mComponent, mPanel);
        }
                
        return mPanel;
    }

    public boolean isFinishPanel() {
        return true;
    }

    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
    }

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.     
    // when prev panel's 'Next' is called, our readSettings is called
    public void readSettings(Object settings) {
        
       WSDLWizardContext myContext = getWSDLWizardContext();
       if ((myContext != null) && (myContext.getWSDLModel() != null)) {
           WSDLModel wsdlModel = myContext.getWSDLModel();
           Definitions defs = wsdlModel.getDefinitions();
           mComponent = JMSUtilities.getJMSAddress(defs);
           if (mComponent != null) {
               
           }
       }        
    }
    
    // when 'Finish' is called while we're in this panel
    public void storeSettings(Object settings) {
       WSDLWizardContext myContext = getWSDLWizardContext();
       if ((myContext != null) && (myContext.getWSDLModel() != null)) {
           WSDLModel wsdlModel = myContext.getWSDLModel();
           Definitions defs = wsdlModel.getDefinitions();
           mComponent = JMSUtilities.getJMSAddress(defs);
           if (mComponent != null) {
               
           }
       }
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(InboundMessageStepWizardPanel.class,
                "AdvancedStepWizardPanel.StepLabel");
    }

    public boolean commit() {
        boolean ok = true;
        if (mController != null) {
            ok = mController.commit();
        }
        return ok;
    } 
}

