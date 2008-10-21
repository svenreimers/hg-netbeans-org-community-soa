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

import org.netbeans.modules.bpel.model.api.Receive;
import org.netbeans.modules.bpel.properties.PropertyType;
import org.netbeans.modules.soa.ui.form.CustomNodeEditor;
import org.netbeans.modules.soa.ui.form.EditorLifeCycleAdapter;
import org.netbeans.modules.bpel.properties.editors.controls.MessageConfigurationController;
import org.netbeans.modules.bpel.properties.editors.controls.MessageExchangeController;
import org.netbeans.modules.soa.ui.form.valid.ValidStateManager;
import org.netbeans.modules.soa.ui.form.valid.ValidStateManager.ValidStateListener;

/**
 *
 * @author  nk160297
 */
public class ReceiveMainPanel extends EditorLifeCycleAdapter {
    
    static final long serialVersionUID = 1L;
    
    private CustomNodeEditor<Receive> myEditor;
    
    private MessageConfigurationController mcc;
    private MessageExchangeController mec;
    
    public ReceiveMainPanel(CustomNodeEditor<Receive> anEditor) {
        this.myEditor = anEditor;
        createContent();
    }
    
    public void createContent() {
        mcc = new MessageConfigurationController(myEditor);
        mcc.createContent();
        mcc.setVisibleVariables(true, false, false);
        mcc.useMyRole();
        //
        mec = new MessageExchangeController(myEditor);
        mec.createContent();
        //
        initComponents();
        bindControls2PropertyNames();
        
        // Issue 85553 start
        lblMessageExchange.setVisible(false);
        fldMessageExchange.setVisible(false);
        btnChooseMessEx.setVisible(false);
        // Issue 85553 end
        
        
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
        fldName.putClientProperty(
                CustomNodeEditor.PROPERTY_BINDER, PropertyType.NAME);
        chbxCreateInstance.putClientProperty(
                CustomNodeEditor.PROPERTY_BINDER, PropertyType.CREATE_INSTANCE);
    }
    
    public boolean initControls() {
        mcc.initControls();
        mec.initControls();
        return true;
    }
    
    public boolean subscribeListeners() {
        mcc.subscribeListeners();
        mec.subscribeListeners();
        return true;
    }
    
    public boolean unsubscribeListeners() {
        mcc.unsubscribeListeners();
        mec.unsubscribeListeners();
        return true;
    }
    
    public boolean applyNewValues() throws Exception {
        mcc.applyNewValues();
        mec.applyNewValues();
        //
        return true;
    }
    
    public boolean afterClose() {
        mcc.afterClose();
        mec.afterClose();
        return true;
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
        fldMessageExchange = mec.getFldMessageExchange();
        btnChooseMessEx = mec.getBtnChooseMsgEx();
        chbxCreateInstance = new javax.swing.JCheckBox();
        lblErrorMessage = new javax.swing.JLabel();
        lblMessageExchange = new javax.swing.JLabel();

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

        btnChooseMessEx.setText(org.openide.util.NbBundle.getMessage(FormBundle.class,"BTN_ChooseMessageExchange")); // NOI18N
        btnChooseMessEx.setMargin(new java.awt.Insets(0, 4, 0, 4));

        chbxCreateInstance.setText(org.openide.util.NbBundle.getMessage(FormBundle.class,"CHBX_CreateInstance")); // NOI18N
        chbxCreateInstance.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbxCreateInstance.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lblErrorMessage.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorMessage.setAlignmentX(0.5F);

        lblMessageExchange.setLabelFor(fldMessageExchange);
        lblMessageExchange.setText(org.openide.util.NbBundle.getMessage(FormBundle.class,"LBL_MessageExchange")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblErrorMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lblInputVariable)
                            .add(lblOperation)
                            .add(lblPartnerLink)
                            .add(lblName))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, cbxPartnerLink, 0, 355, Short.MAX_VALUE)
                            .add(cbxOperation, 0, 355, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(fldInputVariable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnNewInputVariable)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnChooseInputVariable))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, fldName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)))
                    .add(chbxCreateInstance)
                    .add(layout.createSequentialGroup()
                        .add(lblMessageExchange)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(fldMessageExchange, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnChooseMessEx)))
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
                    .add(btnChooseMessEx)
                    .add(lblMessageExchange)
                    .add(fldMessageExchange, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chbxCreateInstance)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblErrorMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_Name")); // NOI18N
        fldName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSN_TXTFLD_ReceiveName")); // NOI18N
        fldName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSD_TXTFLD_ReceiveName")); // NOI18N
        lblPartnerLink.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_PartnerLink")); // NOI18N
        lblPartnerLink.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_PartnerLink")); // NOI18N
        cbxPartnerLink.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSN_CBX_PartnerLink")); // NOI18N
        cbxPartnerLink.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSD_CBX_PartnerLink")); // NOI18N
        lblOperation.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_Operation")); // NOI18N
        lblOperation.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_Operation")); // NOI18N
        cbxOperation.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSN_CBX_Operation")); // NOI18N
        cbxOperation.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSD_CBX_Operation")); // NOI18N
        lblInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_InputVariable")); // NOI18N
        lblInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_InputVariable")); // NOI18N
        fldInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSN_TXTFLD_InputVariable")); // NOI18N
        fldInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSD_TXTFLD_InputVariable")); // NOI18N
        btnNewInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_CreateInputVariable")); // NOI18N
        btnNewInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_CreateInputVariable")); // NOI18N
        btnChooseInputVariable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_BrowseInputVarible")); // NOI18N
        btnChooseInputVariable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_BrowseInputVarible")); // NOI18N
        fldMessageExchange.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSN_TXTFLD_MessageExchange")); // NOI18N
        fldMessageExchange.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSD_TXTFLD_MessageExchange")); // NOI18N
        btnChooseMessEx.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_ChooseMessageExchange")); // NOI18N
        btnChooseMessEx.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_ChooseMessageExchange")); // NOI18N
        chbxCreateInstance.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_CHBX_CreateInstance")); // NOI18N
        chbxCreateInstance.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_CHBX_CreateInstance")); // NOI18N
        lblMessageExchange.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_LBL_MessageExchange")); // NOI18N
        lblMessageExchange.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_LBL_MessageExchange")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSN_LBL_Main_Tab")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ReceiveMainPanel.class, "ACSD_LBL_Main_Tab")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseInputVariable;
    private javax.swing.JButton btnChooseMessEx;
    private javax.swing.JButton btnNewInputVariable;
    private javax.swing.JComboBox cbxOperation;
    private javax.swing.JComboBox cbxPartnerLink;
    private javax.swing.JCheckBox chbxCreateInstance;
    private javax.swing.JTextField fldInputVariable;
    private javax.swing.JTextField fldMessageExchange;
    private javax.swing.JTextField fldName;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblInputVariable;
    private javax.swing.JLabel lblMessageExchange;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOperation;
    private javax.swing.JLabel lblPartnerLink;
    // End of variables declaration//GEN-END:variables
}
