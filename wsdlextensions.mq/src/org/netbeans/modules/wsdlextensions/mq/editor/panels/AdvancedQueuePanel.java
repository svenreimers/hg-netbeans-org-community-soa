/*
 * AdvancedQueuePanel.java
 *
 * Created on July 14, 2008, 4:25 PM
 */

package org.netbeans.modules.wsdlextensions.mq.editor.panels;

/**
 *
 * @author  nang
 */
public class AdvancedQueuePanel extends javax.swing.JPanel {

    /** Creates new form AdvancedQueuePanel */
    public AdvancedQueuePanel() {
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        mQueueLabel.setLabelFor(mQueueTextField);
        org.openide.awt.Mnemonics.setLocalizedText(mQueueLabel, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mQueueLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mReadOptionsLabel, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionsLabel.text")); // NOI18N

        mQueueReadOptionsButtonGroup.add(mReadOptionSharedButton);
        org.openide.awt.Mnemonics.setLocalizedText(mReadOptionSharedButton, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionSharedButton.text")); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mReadOptionExclusiveButton, org.jdesktop.beansbinding.ObjectProperty.create(), mReadOptionSharedButton, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        mReadOptionSharedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mReadOptionSharedButtonActionPerformed(evt);
            }
        });

        mQueueReadOptionsButtonGroup.add(mReadOptionExclusiveButton);
        org.openide.awt.Mnemonics.setLocalizedText(mReadOptionExclusiveButton, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionExclusiveButton.text")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mReadOptionDefaultButton, org.jdesktop.beansbinding.ObjectProperty.create(), mReadOptionExclusiveButton, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        mQueueReadOptionsButtonGroup.add(mReadOptionDefaultButton);
        mReadOptionDefaultButton.setMnemonic('d');
        mReadOptionDefaultButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(mReadOptionDefaultButton, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionDefaultButton.text")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mBindingOptionNoBindButton, org.jdesktop.beansbinding.ObjectProperty.create(), mReadOptionDefaultButton, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        org.openide.awt.Mnemonics.setLocalizedText(mBindingOptionsLabel, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionsLabel.text")); // NOI18N

        mQueueBindingOptionsButtonGroup.add(mBindingOptionNoBindButton);
        org.openide.awt.Mnemonics.setLocalizedText(mBindingOptionNoBindButton, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionNoBindButton.text")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mBindingOptionOnOpenButton, org.jdesktop.beansbinding.ObjectProperty.create(), mBindingOptionNoBindButton, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        mQueueBindingOptionsButtonGroup.add(mBindingOptionOnOpenButton);
        org.openide.awt.Mnemonics.setLocalizedText(mBindingOptionOnOpenButton, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionOnOpenButton.text")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mBindingOptionDefaultButton, org.jdesktop.beansbinding.ObjectProperty.create(), mBindingOptionOnOpenButton, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        mQueueBindingOptionsButtonGroup.add(mBindingOptionDefaultButton);
        mBindingOptionDefaultButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(mBindingOptionDefaultButton, org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionDefaultButton.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, mReadOptionExclusiveButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, mReadOptionDefaultButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, mReadOptionSharedButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mBindingOptionDefaultButton)
                            .add(mBindingOptionNoBindButton)
                            .add(mBindingOptionOnOpenButton))
                        .addContainerGap())
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(113, 113, 113)
                                    .add(mBindingOptionsLabel))
                                .add(layout.createSequentialGroup()
                                    .add(mQueueLabel)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(mQueueTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(mReadOptionsLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(341, 341, 341)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mQueueLabel)
                    .add(mQueueTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mReadOptionsLabel)
                    .add(mBindingOptionsLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mReadOptionSharedButton)
                    .add(mBindingOptionNoBindButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mReadOptionExclusiveButton)
                    .add(mBindingOptionOnOpenButton))
                .add(0, 0, 0)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mReadOptionDefaultButton)
                    .add(mBindingOptionDefaultButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        mQueueLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mQueueLabel.AccessibleContext.accessibleName")); // NOI18N
        mQueueTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mQueueTextField.AccessibleContext.accessibleName")); // NOI18N
        mReadOptionsLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionsLabel.AccessibleContext.accessibleName")); // NOI18N
        mReadOptionSharedButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionSharedButton.AccessibleContext.accessibleName")); // NOI18N
        mReadOptionExclusiveButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionExclusiveButton.AccessibleContext.accessibleName")); // NOI18N
        mReadOptionDefaultButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mReadOptionDefaultButton.AccessibleContext.accessibleName")); // NOI18N
        mBindingOptionsLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionsLabel.AccessibleContext.accessibleName")); // NOI18N
        mBindingOptionNoBindButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionNoBindButton.AccessibleContext.accessibleName")); // NOI18N
        mBindingOptionOnOpenButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionOnOpenButton.AccessibleContext.accessibleName")); // NOI18N
        mBindingOptionDefaultButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.mBindingOptionDefaultButton.AccessibleContext.accessibleName")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AdvancedQueuePanel.class, "AdvancedQueuePanel.AccessibleContext.accessibleName")); // NOI18N

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

private void mReadOptionSharedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mReadOptionSharedButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_mReadOptionSharedButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected final javax.swing.JRadioButton mBindingOptionDefaultButton = new javax.swing.JRadioButton();
    protected final javax.swing.JRadioButton mBindingOptionNoBindButton = new javax.swing.JRadioButton();
    protected final javax.swing.JRadioButton mBindingOptionOnOpenButton = new javax.swing.JRadioButton();
    private final javax.swing.JLabel mBindingOptionsLabel = new javax.swing.JLabel();
    protected final javax.swing.ButtonGroup mQueueBindingOptionsButtonGroup = new javax.swing.ButtonGroup();
    private final javax.swing.JLabel mQueueLabel = new javax.swing.JLabel();
    protected final javax.swing.ButtonGroup mQueueReadOptionsButtonGroup = new javax.swing.ButtonGroup();
    protected final javax.swing.JTextField mQueueTextField = new javax.swing.JTextField();
    protected final javax.swing.JRadioButton mReadOptionDefaultButton = new javax.swing.JRadioButton();
    protected final javax.swing.JRadioButton mReadOptionExclusiveButton = new javax.swing.JRadioButton();
    protected final javax.swing.JRadioButton mReadOptionSharedButton = new javax.swing.JRadioButton();
    private final javax.swing.JLabel mReadOptionsLabel = new javax.swing.JLabel();
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
