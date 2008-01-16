/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.bpel.project.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

class PanelOptionsVisual extends SettingsPanel implements PropertyChangeListener {
    
//    private static boolean lastMainClassCheck = false; // XXX Store somewhere
    
    private PanelConfigureProject panel;
//    private boolean valid;
    
    /** Creates new form PanelOptionsVisual */
    public PanelOptionsVisual(PanelConfigureProject panel) {
        initComponents();
        this.panel = panel;
//B        initJ2EESpecLevels();
    }
    
    public void propertyChange(PropertyChangeEvent event) {
        if (PanelProjectLocationVisual.PROP_PROJECT_NAME.equals(event.getPropertyName())) {
            String newProjectName = ((String) event.getNewValue()).trim();
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        setAsMainCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        setAsMainCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(setAsMainCheckBox, java.util.ResourceBundle.getBundle("org/netbeans/modules/bpel/project/wizards/Bundle").getString("LBL_NWP1_SetAsMain_CheckBox"));
        setAsMainCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        add(setAsMainCheckBox, java.awt.BorderLayout.CENTER);
        setAsMainCheckBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PanelOptionsVisual.class, "ACS_LBL_NWP1_SetAsMain_A11YDesc"));

        jLabel1.setText("                                   ");
        add(jLabel1, java.awt.BorderLayout.NORTH);

    }// </editor-fold>//GEN-END:initComponents
            
    boolean valid(WizardDescriptor settings) {        
        settings.putProperty("WizardPanel_errorMessage", null); // NOI18N
        return true;
    }
    
    boolean validateFileName(WizardDescriptor wizardDescriptor, String fileName, String errMsgKey) {
        if (fileName.length() == 0
                || fileName.indexOf('/')  > 0         //NOI18N
//                || fileName.indexOf('/')  > 0         //NOI18N
                || fileName.indexOf('\\') > 0         //NOI18N
                || fileName.indexOf(':')  > 0) {      //NOI18N
            wizardDescriptor.putProperty("WizardPanel_errorMessage", // NOI18N
                    NbBundle.getMessage(PanelProjectLocationVisual.class, errMsgKey));
            return false; 
        }
        
        final File tmpFile = new File(fileName).getAbsoluteFile();
        if (getCanonicalFile(tmpFile) == null) {
            String message = NbBundle.getMessage(PanelProjectLocationVisual.class, errMsgKey);
            wizardDescriptor.putProperty("WizardPanel_errorMessage", message);  // NOI18N
            return false;
        }
        
        return true;
    }
    
    void store(WizardDescriptor d) {
        d.putProperty(WizardProperties.SET_AS_MAIN, setAsMainCheckBox.isSelected() ? Boolean.TRUE : Boolean.FALSE );
    }
    
    void read(WizardDescriptor d) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox setAsMainCheckBox;
    // End of variables declaration//GEN-END:variables
    
    private void bpelNameChanged() {
//        String bpelName = this.bpelNameTextField.getText();
//        this.valid = bpelName.endsWith(".bpel");
        this.panel.fireChangeEvent();
    }
    
    private void wsdlNameChanged() {
//        String wsdlName = this.wsdlNameTextField.getText();
//        this.valid = wsdlName.endsWith(".wsdl");
        this.panel.fireChangeEvent();
    }
    
    static File getCanonicalFile(File file) {
        try {
            return file.getCanonicalFile();
        } catch (IOException e) {
            return null;
        }
    }
}
