/*
 * BindingsConfigurationEditorPanel.java
 *
 * Created on July 14, 2008, 2:27 PM
 */

package org.netbeans.modules.wsdlextensions.mq.editor.panels;

/**
 *
 * @author  nang
 */
public class BindingsConfigurationEditorPanel extends javax.swing.JPanel {
    
    /** Creates new form BindingsConfigurationEditorPanel */
    public BindingsConfigurationEditorPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mMessagingLabel.setFont(mMessagingLabel.getFont().deriveFont(mMessagingLabel.getFont().getStyle() | java.awt.Font.BOLD));
        org.openide.awt.Mnemonics.setLocalizedText(mMessagingLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mMessagingLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mMessageTypeLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mMessageTypeLabel.text")); // NOI18N

        mMessageTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Text", "Binary" }));
        mMessageTypeComboBox.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mMessageTypeComboBox.toolTipText")); // NOI18N

        mQueueLabel.setLabelFor(mQueueTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mQueueLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueLabel.text")); // NOI18N

        mQueueTextField.setColumns(15);
        mQueueTextField.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mQueueAdvancedButton, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueAdvancedButton.text")); // NOI18N
        mQueueAdvancedButton.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueAdvancedButton.toolTipText")); // NOI18N

        mPollIntervalLabel.setLabelFor(mPollIntervalTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mPollIntervalLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPollIntervalLabel.text")); // NOI18N

        mPollIntervalTextField.setColumns(6);
        mPollIntervalTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mPollIntervalTextField.setText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPollIntervalTextField.text")); // NOI18N
        mPollIntervalTextField.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPollIntervalTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mMillisecondLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mMillisecondLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mSyncpointCheckbox, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mSyncpointCheckbox.text")); // NOI18N
        mSyncpointCheckbox.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mSyncpointCheckbox.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mTransactionalCheckbox, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mTransactionalCheckbox.text")); // NOI18N
        mTransactionalCheckbox.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mTransactionalCheckbox.toolTipText")); // NOI18N
        mTransactionalCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTransactionalCheckboxActionPerformed(evt);
            }
        });

        mConnectionLabel.setFont(mConnectionLabel.getFont().deriveFont(mConnectionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        org.openide.awt.Mnemonics.setLocalizedText(mConnectionLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mConnectionLabel.text")); // NOI18N

        mQueueManagerLabel.setLabelFor(mQueueManagerTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mQueueManagerLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerLabel.text")); // NOI18N

        mQueueManagerTextField.setColumns(15);
        mQueueManagerTextField.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mQueueManagerAdvancedButton, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerAdvancedButton.text")); // NOI18N
        mQueueManagerAdvancedButton.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerAdvancedButton.toolTipText")); // NOI18N

        mHostLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mHostLabel.setLabelFor(mHostTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mHostLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mHostLabel.text")); // NOI18N

        mHostTextField.setColumns(15);
        mHostTextField.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mHostTextField.toolTipText")); // NOI18N

        mChannelLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mChannelLabel.setLabelFor(mChannelTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mChannelLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mChannelLabel.text")); // NOI18N

        mChannelTextField.setColumns(15);
        mChannelTextField.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mChannelTextField.toolTipText")); // NOI18N

        mPortLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mPortLabel.setLabelFor(mPortTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mPortLabel, org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPortLabel.text")); // NOI18N

        mPortTextField.setColumns(5);
        mPortTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mPortTextField.setText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPortTextField.text")); // NOI18N
        mPortTextField.setToolTipText(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPortTextField.toolTipText")); // NOI18N
        mPortTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPortTextFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(mMessagingLabel)
                        .addContainerGap(448, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mMessagingSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(mMessageTypeLabel)
                                    .add(mPollIntervalLabel))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(mMessageTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(mQueueLabel)
                                        .add(4, 4, 4)
                                        .add(mQueueTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(mQueueAdvancedButton))
                                    .add(layout.createSequentialGroup()
                                        .add(mPollIntervalTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(4, 4, 4)
                                        .add(mMillisecondLabel)
                                        .add(18, 18, 18)
                                        .add(mSyncpointCheckbox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(mTransactionalCheckbox)))
                                .add(90, 90, 90)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(mConnectionLabel)
                        .addContainerGap(445, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(mHostLabel)
                            .add(mQueueManagerLabel)
                            .add(mChannelLabel)
                            .add(mPortLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(mQueueManagerTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(mHostTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(mQueueManagerAdvancedButton))
                            .add(mChannelTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(mPortTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(210, 210, 210))
                    .add(layout.createSequentialGroup()
                        .add(mConnectionSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(mMessagingLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mMessagingSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mMessageTypeLabel)
                    .add(mMessageTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mQueueLabel)
                    .add(mQueueTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mQueueAdvancedButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mPollIntervalLabel)
                    .add(mPollIntervalTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mMillisecondLabel)
                    .add(mSyncpointCheckbox)
                    .add(mTransactionalCheckbox))
                .add(18, 18, 18)
                .add(mConnectionLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mConnectionSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mQueueManagerLabel)
                    .add(mQueueManagerTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mQueueManagerAdvancedButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mHostLabel)
                    .add(mHostTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mChannelLabel)
                    .add(mChannelTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mPortLabel)
                    .add(mPortTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        mMessagingSeparator.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.jSeparator1.AccessibleContext.accessibleName")); // NOI18N
        mQueueLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueLabel.AccessibleContext.accessibleName")); // NOI18N
        mQueueTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueTextField.AccessibleContext.accessibleName")); // NOI18N
        mQueueAdvancedButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueAdvancedButton.AccessibleContext.accessibleName")); // NOI18N
        mQueueManagerLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerLabel.AccessibleContext.accessibleName")); // NOI18N
        mQueueManagerTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerTextField.AccessibleContext.accessibleName")); // NOI18N
        mQueueManagerAdvancedButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mQueueManagerAdvancedButton.AccessibleContext.accessibleName")); // NOI18N
        mHostLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mHostLabel.AccessibleContext.accessibleName")); // NOI18N
        mHostTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mHostTextField.AccessibleContext.accessibleName")); // NOI18N
        mChannelLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mChannelLabel.AccessibleContext.accessibleName")); // NOI18N
        mChannelTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mChannelTextField.AccessibleContext.accessibleName")); // NOI18N
        mPortLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPortLabel.AccessibleContext.accessibleName")); // NOI18N
        mPortTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.mPortTextField.AccessibleContext.accessibleName")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(BindingsConfigurationEditorPanel.class, "BindingsConfigurationEditorPanel.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

private void mPortTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPortTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_mPortTextFieldActionPerformed

private void mTransactionalCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTransactionalCheckboxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_mTransactionalCheckboxActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected final javax.swing.JLabel mChannelLabel = new javax.swing.JLabel();
    protected final javax.swing.JTextField mChannelTextField = new javax.swing.JTextField();
    protected final javax.swing.JLabel mConnectionLabel = new javax.swing.JLabel();
    protected final javax.swing.JSeparator mConnectionSeparator = new javax.swing.JSeparator();
    protected final javax.swing.JLabel mHostLabel = new javax.swing.JLabel();
    protected final javax.swing.JTextField mHostTextField = new javax.swing.JTextField();
    protected final javax.swing.JComboBox mMessageTypeComboBox = new javax.swing.JComboBox();
    protected final javax.swing.JLabel mMessageTypeLabel = new javax.swing.JLabel();
    protected final javax.swing.JLabel mMessagingLabel = new javax.swing.JLabel();
    protected final javax.swing.JSeparator mMessagingSeparator = new javax.swing.JSeparator();
    protected final javax.swing.JLabel mMillisecondLabel = new javax.swing.JLabel();
    protected final javax.swing.JLabel mPollIntervalLabel = new javax.swing.JLabel();
    protected final javax.swing.JTextField mPollIntervalTextField = new javax.swing.JTextField();
    protected final javax.swing.JLabel mPortLabel = new javax.swing.JLabel();
    protected final javax.swing.JTextField mPortTextField = new javax.swing.JTextField();
    protected final javax.swing.JButton mQueueAdvancedButton = new javax.swing.JButton();
    protected final javax.swing.JLabel mQueueLabel = new javax.swing.JLabel();
    protected final javax.swing.JButton mQueueManagerAdvancedButton = new javax.swing.JButton();
    protected final javax.swing.JLabel mQueueManagerLabel = new javax.swing.JLabel();
    protected final javax.swing.JTextField mQueueManagerTextField = new javax.swing.JTextField();
    protected final javax.swing.JTextField mQueueTextField = new javax.swing.JTextField();
    protected final javax.swing.JCheckBox mSyncpointCheckbox = new javax.swing.JCheckBox();
    protected final javax.swing.JCheckBox mTransactionalCheckbox = new javax.swing.JCheckBox();
    // End of variables declaration//GEN-END:variables
    
}
