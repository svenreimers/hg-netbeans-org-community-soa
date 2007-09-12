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

/*
 * PanelOptionsVisual1.java
 *
 * Created on January 26, 2006, 5:24 PM
 */

package org.netbeans.modules.bpel.core.wizard;


import java.text.MessageFormat;
import javax.swing.JTextField;

import org.openide.WizardDescriptor;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;

/**
 *
 * @author  Praveen
 */
public class BpelOptionsPanel extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private static final String DEFAULT_SERVICE_NAME =
            NbBundle.getMessage(BpelOptionsPanel.class,
            "TXT_defaultServiceName");                                      //NOI18N
    private static final String DEFAULT_PROJECT_NAME =
            NbBundle.getMessage(BpelOptionsPanel.class,
            "TXT_defaultProjectName");                                      //NOI18N
    private static final String TARGET_URL_PREFIX =
            NbBundle.getMessage(BpelOptionsPanel.class,"TXT_defaultTNS");   //NOI18N
    
    /** Creates new form PanelOptionsVisual1 */
    public BpelOptionsPanel(NewBpelFilePanel newBpelFilePanel) {
        initComponents();
        
        this.newBpelFilePanel = newBpelFilePanel;
        NameSpaceListener namespaceListener = new NameSpaceListener();
        namespaceTextField.getDocument().addDocumentListener(namespaceListener);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        Mnemonics.setLocalizedText(jLabel1, NbBundle.getMessage(BpelOptionsPanel.class, "LBL_Namespace"));
        namespaceTextField = new javax.swing.JTextField();

        jLabel1.setLabelFor(namespaceTextField);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(namespaceTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 460, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(namespaceTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(198, 198, 198))
        );

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/bpel/core/wizard/Bundle"); // NOI18N
        jLabel1.getAccessibleContext().setAccessibleName(bundle.getString("ACS_NamespaceLabel")); // NOI18N
        namespaceTextField.getAccessibleContext().setAccessibleName(bundle.getString("ACS_NamespaceTextField")); // NOI18N
        namespaceTextField.getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_NamespaceTextFieldDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    boolean valid(WizardDescriptor wizardDescriptor) {
        return true;
    }
    
    void store(WizardDescriptor d) {
        // Set value for namespace
        d.putProperty(WizardProperties.NAMESPACE, namespaceTextField.getText());
    }
    
    void read(WizardDescriptor d) {
    }
    
    protected void setNamespaceTextField(String namespace) {
        namespaceTextField.setText(namespace);
    }
    
    protected String getNamespaceTextField() {
        return namespaceTextField.getText();
    }
    
    String getWsName() {
        if (fileNameTF!=null)
            return fileNameTF.getText();
        else
            return DEFAULT_SERVICE_NAME;
    }
    
    void attachFileNameListener(JTextField fileNameTF) {
        
        this.fileNameTF=fileNameTF;
        if (fileNameTF!=null) {
            DocListener listener = new DocListener();
            javax.swing.text.Document doc = fileNameTF.getDocument();
            doc.addDocumentListener(listener);
        }
        
        String newNamespace = generateNamespace();
        namespaceTextField.setText(newNamespace);
        
        // initialise.
        prevNamespace = newNamespace;
        
    }
    
    /**
     * Generates a namespace by the template. 
     * Names of the project and the process are used here.
     */ 
    private String generateNamespace() {
        String projectName = null;
        Project proj = newBpelFilePanel.getProject();
        if (proj != null) {
            ProjectInformation projInfo = ProjectUtils.getInformation(proj);
            if (projInfo != null) {
                projectName = projInfo.getName();
            }
        }
        if (projectName == null || projectName.length() == 0) {
            projectName = DEFAULT_PROJECT_NAME;
        }
        //
        String serviceName = null;
        if (fileNameTF != null) {
            serviceName = fileNameTF.getText();
        }
        if (serviceName == null || serviceName.length() == 0) {
            serviceName = DEFAULT_SERVICE_NAME;
        }
        //
        String result = MessageFormat.format(
                TARGET_URL_PREFIX, projectName, serviceName);
        return result;
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField namespaceTextField;
    // End of variables declaration//GEN-END:variables
    
    NewBpelFilePanel newBpelFilePanel;
    private JTextField fileNameTF;
    private String prevNamespace;
    private boolean nameSpaceModifiedFlag = false;
    
    private void doUpdate() {
        if(namespaceTextField.getText()!=null && prevNamespace != null)
            if(!namespaceTextField.getText().equals(prevNamespace))
                nameSpaceModifiedFlag = true;
        
        if(!nameSpaceModifiedFlag) {
            String newNamespace = generateNamespace();
            namespaceTextField.setText(newNamespace);
            prevNamespace = newNamespace;
        }
    }
    
    private class DocListener implements javax.swing.event.DocumentListener {
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            doUpdate();
        }
        
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            doUpdate();
        }
        
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            doUpdate();
        }
    };
    
    private class NameSpaceListener implements javax.swing.event.DocumentListener {
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            checkValidNamespace();
            newBpelFilePanel.fireChange();
        }
        
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            checkValidNamespace();
            newBpelFilePanel.fireChange();
        }
        
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            checkValidNamespace();
            newBpelFilePanel.fireChange();
        }
    };
    
    
    private void checkValidNamespace() {
        if(newBpelFilePanel.getTemplateWizard() == null)
            return;
        
        if(namespaceTextField.getText().contains(" ")) {
            newBpelFilePanel.getTemplateWizard().
                    putProperty("WizardPanel_errorMessage",                  // NOI18N
                    NbBundle.getMessage(BpelOptionsPanel.class,
                    "MSG_Namespace_Contains_Space"));            // NOI18N
        } else {
            newBpelFilePanel.getTemplateWizard().
                    putProperty("WizardPanel_errorMessage", null);               //NOI18N
        }
    }
    
}
