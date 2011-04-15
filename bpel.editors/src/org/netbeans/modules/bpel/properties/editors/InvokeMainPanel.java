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
package org.netbeans.modules.bpel.properties.editors;

import static org.netbeans.modules.bpel.properties.PropertyType.NAME;
import org.netbeans.modules.soa.ui.form.CustomNodeEditor;
import org.netbeans.modules.soa.ui.form.EditorLifeCycleAdapter;
import org.netbeans.modules.bpel.properties.editors.controls.MessageConfigurationController;
import org.netbeans.modules.soa.ui.form.valid.ValidStateManager;
import org.netbeans.modules.soa.ui.form.valid.ValidStateManager.ValidStateListener;

/**
 *
 * @author  nk160297
 */
public class InvokeMainPanel extends EditorLifeCycleAdapter {

    static final long serialVersionUID = 1L;

    private CustomNodeEditor myEditor;
    private MessageConfigurationController mcc;

    public InvokeMainPanel(CustomNodeEditor anEditor) {
        this.myEditor = anEditor;
        createContent();
    }
    
    public void createContent() {
        mcc = new MessageConfigurationController(myEditor);
        mcc.createContent();
        mcc.setVisibleVariables(true, true, false);
        mcc.useParthnerRole();
        //
        initComponents();
        bindControls2PropertyNames();
        //
        myEditor.getValidStateManager(true).addValidStateListener(
                new ValidStateListener() {
            public void stateChanged(ValidStateManager source, boolean isValid) {
                if (source.isValid()) {
                    lblErrorMessage.setText("");
                } else {
                    lblErrorMessage.setText(source.getHtmlReasons());
                }
            }
        });
    }
    
    /**
     * Binds simple controls to names of properties.
     * This is necessary for automatic value inicialization and value inquiry.
     */
    private void bindControls2PropertyNames() {
        fldName.putClientProperty(CustomNodeEditor.PROPERTY_BINDER, NAME);
    }
    
    public boolean initControls() {
        mcc.initControls();
        return true;
    }
    
    public boolean subscribeListeners() {
        mcc.subscribeListeners();
        return true;
    }
    
    public boolean unsubscribeListeners() {
        mcc.unsubscribeListeners();
        return true;
    }
    
    @Override
    public boolean applyNewValues() throws Exception {
        mcc.applyNewValues();
        return true;
    }
    
    public boolean afterClose() {
        mcc.afterClose();
        return true;
    }
    
    public MessageConfigurationController getMCC() {
        return mcc;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        fldName = new javax.swing.JTextField();
        lblPartnerLink = new javax.swing.JLabel();
        cbxPartnerLink = mcc.getCbxPartnerLink();
        lblOperation = new javax.swing.JLabel();
        cbxOperation = mcc.getCbxOperation();
        lblInputVariable = new javax.swing.JLabel();
        fldInputVariable = mcc.getFldInputVariable();
        btnNewInputVariable = mcc.getBtnNewInputVariable();
        btnChooseInputVariable = mcc.getBtnChooseInputVariable();
        lblOutputVariable = new javax.swing.JLabel();
        fldOutputVariable = mcc.getFldOutputVariable();
        btnNewOutputVariable = mcc.getBtnNewOutputVariable();
        btnChooseOutputVariable = mcc.getBtnChooseOutputVariable();
        lblErrorMessage = new javax.swing.JLabel();

        lblName.setLabelFor(fldName);
        lblName.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "LBL_Name")); // NOI18N

        fldName.setColumns(40);
        fldName.setName(""); // NOI18N

        lblPartnerLink.setLabelFor(cbxPartnerLink);
        lblPartnerLink.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "LBL_PartnerLink")); // NOI18N

        lblOperation.setLabelFor(cbxOperation);
        lblOperation.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "LBL_Operation")); // NOI18N

        lblInputVariable.setLabelFor(fldInputVariable);
        lblInputVariable.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "LBL_InputVariable")); // NOI18N

        fldInputVariable.setColumns(30);
        fldInputVariable.setEditable(false);

        btnNewInputVariable.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_CreateInputVariable")); // NOI18N
        btnNewInputVariable.setMargin(new java.awt.Insets(0, 4, 0, 4));

        btnChooseInputVariable.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_BrowseInputVarible")); // NOI18N
        btnChooseInputVariable.setMargin(new java.awt.Insets(0, 2, 0, 2));

        lblOutputVariable.setLabelFor(fldOutputVariable);
        lblOutputVariable.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "LBL_OutputVariable")); // NOI18N

        fldOutputVariable.setColumns(30);
        fldOutputVariable.setEditable(false);

        btnNewOutputVariable.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_CreateOutputVariable")); // NOI18N
        btnNewOutputVariable.setMargin(new java.awt.Insets(0, 4, 0, 4));

        btnChooseOutputVariable.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_BrowseOutputVarible")); // NOI18N
        btnChooseOutputVariable.setMargin(new java.awt.Insets(0, 2, 0, 2));

        lblErrorMessage.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorMessage.setAlignmentX(0.5F);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lblErrorMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lblName)
                            .add(lblPartnerLink)
                            .add(lblOperation)
                            .add(lblInputVariable)
                            .add(lblOutputVariable))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(fldName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .add(cbxPartnerLink, 0, 349, Short.MAX_VALUE)
                            .add(cbxOperation, 0, 349, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(fldOutputVariable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .add(fldInputVariable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(layout.createSequentialGroup()
                                        .add(btnNewInputVariable)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(btnChooseInputVariable))
                                    .add(layout.createSequentialGroup()
                                        .add(btnNewOutputVariable)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(btnChooseOutputVariable)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblName)
                    .add(fldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblPartnerLink)
                    .add(cbxPartnerLink, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblOperation)
                    .add(cbxOperation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblInputVariable)
                    .add(btnChooseInputVariable)
                    .add(btnNewInputVariable)
                    .add(fldInputVariable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnChooseOutputVariable)
                    .add(btnNewOutputVariable)
                    .add(lblOutputVariable)
                    .add(fldOutputVariable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblErrorMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_Name")); // NOI18N
        lblName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_Name")); // NOI18N
        fldName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSN_TXTFLD_InvokeName")); // NOI18N
        fldName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSD_TXTFLD_InvokeName")); // NOI18N
        lblPartnerLink.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_PartnerLink")); // NOI18N
        lblPartnerLink.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_PartnerLink")); // NOI18N
        cbxPartnerLink.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSN_CBX_PartnerLink")); // NOI18N
        cbxPartnerLink.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSD_CBX_PartnerLink")); // NOI18N
        lblOperation.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_Operation")); // NOI18N
        lblOperation.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_Operation")); // NOI18N
        cbxOperation.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSN_CBX_Operation")); // NOI18N
        cbxOperation.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSD_CBX_Operation")); // NOI18N
        lblInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_InputVariable")); // NOI18N
        lblInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_InputVariable")); // NOI18N
        fldInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSN_TXTFLD_InputVariable")); // NOI18N
        fldInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSD_TXTFLD_InputVariable")); // NOI18N
        btnNewInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_CreateInputVariable")); // NOI18N
        btnNewInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_CreateInputVariable")); // NOI18N
        btnChooseInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_BrowseInputVarible")); // NOI18N
        btnChooseInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_BrowseInputVarible")); // NOI18N
        lblOutputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_OutputVariable")); // NOI18N
        lblOutputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_OutputVariable")); // NOI18N
        fldOutputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSN_TXTFLD_OutputVariable")); // NOI18N
        fldOutputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSD_TXTFLD_OutputVariable")); // NOI18N
        btnNewOutputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_CreateOutputVariable")); // NOI18N
        btnNewOutputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_CreateOutputVariable")); // NOI18N
        btnChooseOutputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_BrowseOutputVarible")); // NOI18N
        btnChooseOutputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_BrowseOutputVarible")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSN_LBL_Main_Tab")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InvokeMainPanel.class, "ACSD_LBL_Main_Tab")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseInputVariable;
    private javax.swing.JButton btnChooseOutputVariable;
    private javax.swing.JButton btnNewInputVariable;
    private javax.swing.JButton btnNewOutputVariable;
    private javax.swing.JComboBox cbxOperation;
    private javax.swing.JComboBox cbxPartnerLink;
    private javax.swing.JTextField fldInputVariable;
    private javax.swing.JTextField fldName;
    private javax.swing.JTextField fldOutputVariable;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblInputVariable;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOperation;
    private javax.swing.JLabel lblOutputVariable;
    private javax.swing.JLabel lblPartnerLink;
    // End of variables declaration//GEN-END:variables
}
