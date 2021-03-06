/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.soa.pojo.wizards;

import javax.swing.JPanel;

public final class POJOConsumerPalleteAdvancedPanel extends JPanel {

    /** Creates new form POJOConsumerPalleteVisualPanel2 */
    public POJOConsumerPalleteAdvancedPanel() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Configure Consumer Defaults";
    }

    public boolean useDefaultValues() {
        return this.jCBDefaultValues.isSelected();
    }
    
    public void setInputMessageType(String inMsgType) {
        this.txtInputMsgType.setText(inMsgType);
    }
    
    public String getInputMessageType() {
        return this.txtInputMsgType.getText();
    }
    
    public void setInputMessageTypeNS(String inMsgTypeNS) {
         this.txtInputMsgTypeNS.setText(inMsgTypeNS);
    }
    
    public String getInputMessageTypeNS() {
        return this.txtInputMsgTypeNS.getText();
    }
    
    public String getReplyMethodName() {
        return this.txtAsynchReplyMethodName.getText();
    }
    
    public void setReplyMethodName(String methodName) {
        this.txtAsynchReplyMethodName.setText(methodName);
    }

    public String getDoneMethodName() {
        return this.txtAsynchOnDoneMethodName.getText();
    }
    
    public void setDoneMethodName(String methodName) {
        this.txtAsynchOnDoneMethodName.setText(methodName);
    }

    public void enableEdit(boolean b) {
        if (b) {
            this.txtAsynchOnDoneMethodName.setEditable(b);
            this.txtAsynchReplyMethodName.setEditable(b);
            this.txtInputMsgType.setEditable(b);
            this.txtInputMsgTypeNS.setEditable(b);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInputMsgType = new javax.swing.JLabel();
        lblInputMsgTypeNs = new javax.swing.JLabel();
        txtInputMsgType = new javax.swing.JTextField();
        txtInputMsgTypeNS = new javax.swing.JTextField();
        lblAsynchReplyMethodName = new javax.swing.JLabel();
        txtAsynchReplyMethodName = new javax.swing.JTextField();
        lblAsynchOnDoneMethodName = new javax.swing.JLabel();
        txtAsynchOnDoneMethodName = new javax.swing.JTextField();
        jCBDefaultValues = new javax.swing.JCheckBox();
        pnlOkCancel = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblInputMsgType, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteVisualPanel2.jLabel5.text")); // NOI18N
        lblInputMsgType.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_inptMsgType")); // NOI18N
        lblInputMsgType.setName("lblInputMsgType"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblInputMsgTypeNs, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteVisualPanel2.jLabel6.text")); // NOI18N
        lblInputMsgTypeNs.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_InptMsgTypeNS")); // NOI18N
        lblInputMsgTypeNs.setName("lblInputMsgTypeNs"); // NOI18N

        txtInputMsgType.setEditable(false);
        txtInputMsgType.setText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteVisualPanel2.jTextField5.text")); // NOI18N
        txtInputMsgType.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_inptMsgType")); // NOI18N
        txtInputMsgType.setName("txtInputMsgType"); // NOI18N

        txtInputMsgTypeNS.setEditable(false);
        txtInputMsgTypeNS.setText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.txtInputMsgTypeNS.text")); // NOI18N
        txtInputMsgTypeNS.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_InptMsgTypeNS")); // NOI18N
        txtInputMsgTypeNS.setName("txtInputMsgTypeNS"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblAsynchReplyMethodName, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.lblAsynchReplyMethodName.text")); // NOI18N
        lblAsynchReplyMethodName.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_OnReplyMethodName")); // NOI18N
        lblAsynchReplyMethodName.setName("lblAsynchReplyMethodName"); // NOI18N

        txtAsynchReplyMethodName.setEditable(false);
        txtAsynchReplyMethodName.setText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.txtAsynchReplyMethodName.text")); // NOI18N
        txtAsynchReplyMethodName.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_OnReplyMethodName")); // NOI18N
        txtAsynchReplyMethodName.setName("txtAsynchReplyMethodName"); // NOI18N
        txtAsynchReplyMethodName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsynchReplyMethodNameActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblAsynchOnDoneMethodName, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.lblAsynchOnDoneMethodName.text")); // NOI18N
        lblAsynchOnDoneMethodName.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_OnDoneMethodName")); // NOI18N
        lblAsynchOnDoneMethodName.setName("lblAsynchOnDoneMethodName"); // NOI18N

        txtAsynchOnDoneMethodName.setEditable(false);
        txtAsynchOnDoneMethodName.setText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.txtAsynchOnDoneMethodName.text")); // NOI18N
        txtAsynchOnDoneMethodName.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_AdvConsumer_OnDoneMethodName")); // NOI18N
        txtAsynchOnDoneMethodName.setName("txtAsynchOnDoneMethodName"); // NOI18N
        txtAsynchOnDoneMethodName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsynchOnDoneMethodNameActionPerformed(evt);
            }
        });

        jCBDefaultValues.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCBDefaultValues, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.jCBDefaultValues.text")); // NOI18N
        jCBDefaultValues.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_Adv_Consumer_Default_values")); // NOI18N
        jCBDefaultValues.setName("jCBDefaultValues"); // NOI18N

        pnlOkCancel.setName("pnlOkCancel"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnOk, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.btnOk.text")); // NOI18N
        btnOk.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_Consumer_Adv_Ok")); // NOI18N
        btnOk.setName("btnOk"); // NOI18N
        pnlOkCancel.add(btnOk);

        org.openide.awt.Mnemonics.setLocalizedText(btnCancel, org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "POJOConsumerPalleteAdvancedPanel.btnCancel.text")); // NOI18N
        btnCancel.setToolTipText(org.openide.util.NbBundle.getMessage(POJOConsumerPalleteAdvancedPanel.class, "TT_Adv_Cons_Cancel")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        pnlOkCancel.add(btnCancel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(lblAsynchOnDoneMethodName)
                        .add(10, 10, 10)
                        .add(txtAsynchOnDoneMethodName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                    .add(pnlOkCancel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jCBDefaultValues)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(lblAsynchReplyMethodName)
                                    .add(lblInputMsgTypeNs)
                                    .add(lblInputMsgType))
                                .add(24, 24, 24)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(txtAsynchReplyMethodName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .add(txtInputMsgType, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .add(txtInputMsgTypeNS, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jCBDefaultValues)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtInputMsgType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblInputMsgType))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtInputMsgTypeNS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblInputMsgTypeNs))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtAsynchReplyMethodName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblAsynchReplyMethodName))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtAsynchOnDoneMethodName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblAsynchOnDoneMethodName))
                .add(18, 18, Short.MAX_VALUE)
                .add(pnlOkCancel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
private void txtAsynchOnDoneMethodNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsynchOnDoneMethodNameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtAsynchOnDoneMethodNameActionPerformed

private void txtAsynchReplyMethodNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsynchReplyMethodNameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtAsynchReplyMethodNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JCheckBox jCBDefaultValues;
    private javax.swing.JLabel lblAsynchOnDoneMethodName;
    private javax.swing.JLabel lblAsynchReplyMethodName;
    private javax.swing.JLabel lblInputMsgType;
    private javax.swing.JLabel lblInputMsgTypeNs;
    private javax.swing.JPanel pnlOkCancel;
    private javax.swing.JTextField txtAsynchOnDoneMethodName;
    private javax.swing.JTextField txtAsynchReplyMethodName;
    private javax.swing.JTextField txtInputMsgType;
    private javax.swing.JTextField txtInputMsgTypeNS;
    // End of variables declaration//GEN-END:variables
}

