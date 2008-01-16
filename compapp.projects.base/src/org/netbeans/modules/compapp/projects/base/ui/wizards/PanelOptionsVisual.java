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


package org.netbeans.modules.compapp.projects.base.ui.wizards;

import org.netbeans.modules.compapp.projects.base.ui.customizer.IcanproProjectProperties;
import org.openide.WizardDescriptor;

class PanelOptionsVisual
    extends javax.swing.JPanel
    implements org.netbeans.modules.compapp.projects.base.IcanproConstants {

//    private static boolean lastMainClassCheck = false; // XXX Store somewhere

    private PanelConfigureProject panel;
    private String j2eeLevel = IcanproProjectProperties.J2EE_1_4;
    
    /** Creates new form PanelOptionsVisual */
    public PanelOptionsVisual(PanelConfigureProject panel) {
        initComponents();
        this.panel = panel;
//B        initJ2EESpecLevels();
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
        org.openide.awt.Mnemonics.setLocalizedText(setAsMainCheckBox, org.openide.util.NbBundle.getMessage(PanelOptionsVisual.class, "LBL_NWP1_SetAsMain_CheckBox"));
        setAsMainCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        add(setAsMainCheckBox, java.awt.BorderLayout.CENTER);
        setAsMainCheckBox.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PanelOptionsVisual.class, "LBL_NWP1_SetAsMain_CheckBox"));
        setAsMainCheckBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PanelOptionsVisual.class, "ACS_LBL_NWP1_SetAsMain_A11YDesc"));

        jLabel1.setText("                                   ");
        add(jLabel1, java.awt.BorderLayout.NORTH);

    }// </editor-fold>//GEN-END:initComponents
    
    boolean valid(WizardDescriptor wizardDescriptor) {
        return true;
    }

    void store(WizardDescriptor d) {
        d.putProperty(WizardProperties.SET_AS_MAIN, setAsMainCheckBox.isSelected() ? Boolean.TRUE : Boolean.FALSE );
        d.putProperty(WizardProperties.J2EE_LEVEL, j2eeLevel);
    }
    
    void read(WizardDescriptor d) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox setAsMainCheckBox;
    // End of variables declaration//GEN-END:variables

//B    private void initJ2EESpecLevels() {
//B      j2eeSpecComboBox.addItem(NbBundle.getBundle(WIZARD_BUNDLE).getString("J2EESpecLevel_0")); //NOI18N
//B      j2eeSpecComboBox.setSelectedIndex(0);
//B   }
}
