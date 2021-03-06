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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */
package org.netbeans.modules.encoder.coco.ui.wizard;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public final class GenerateXSDVisualPanel extends JPanel {
    
    /** Creates new form GenerateXSDVisualPanel */
    public GenerateXSDVisualPanel() {
        initComponents();
        jComboBoxCopybookCodePage.setModel(new javax.swing.DefaultComboBoxModel());
        jComboBoxDISPLAY1CodePage.setModel(new javax.swing.DefaultComboBoxModel());
        jComboBoxDISPLAYCodePage.setModel(new javax.swing.DefaultComboBoxModel());
        jComboBoxPostencodeCoding.setModel(new javax.swing.DefaultComboBoxModel());
        jComboBoxPredecodeCoding.setModel(new javax.swing.DefaultComboBoxModel());
    }
    
    @Override
    public String getName() {
        return "Specify Encoder Options";
    }
    
    public void setCopybookCodePageList(String[] list) {
        setComboBoxList(jComboBoxCopybookCodePage, list);
    }
    
    public void setCopybookCodePage(String cp) {
        jComboBoxCopybookCodePage.getEditor().setItem(cp);
    }
    
    public String getCopybookCodePage() {
        return jComboBoxCopybookCodePage.getEditor().getItem().toString();
    }
    
    public void setDisplayCodePageList(String[] list) {
        setComboBoxList(jComboBoxDISPLAYCodePage, list);
    }
    
    public void setDisplayCodePage(String cp) {
        jComboBoxDISPLAYCodePage.getEditor().setItem(cp);
    }
    
    public String getDisplayCodePage() {
        return jComboBoxDISPLAYCodePage.getEditor().getItem().toString();
    }
    
    public void setDisplay1CodePageList(String[] list) {
        setComboBoxList(jComboBoxDISPLAY1CodePage, list);
    }
    
    public void setDisplay1CodePage(String cp) {
        jComboBoxDISPLAY1CodePage.getEditor().setItem(cp);
    }
    
    public String getDisplay1CodePage() {
        return jComboBoxDISPLAY1CodePage.getEditor().getItem().toString();
    }
    
    public void setPredecodeCodingList(String[] list) {
        setComboBoxList(jComboBoxPredecodeCoding, list);
    }
    
    public void setPredecodeCoding(String cp) {
        jComboBoxPredecodeCoding.getEditor().setItem(cp);
    }
    
    public String getPredecodeCoding() {
        return jComboBoxPredecodeCoding.getEditor().getItem().toString();
    }
    
    public void setPostencodeCodingList(String[] list) {
        setComboBoxList(jComboBoxPostencodeCoding, list);
    }
    
    public void setPostencodeCoding(String cp) {
        jComboBoxPostencodeCoding.getEditor().setItem(cp);
    }
    
    public String getPostencodeCoding() {
        return jComboBoxPostencodeCoding.getEditor().getItem().toString();
    }
    
    public void setIgnore72ColBeyond(boolean val) {
        jCheckBoxIgnore72ColBeyond.setSelected(val);
    }
    
    public boolean getIgnore72ColBeyond() {
        return jCheckBoxIgnore72ColBeyond.isSelected();
    }
    
    public void setOverwriteExisting(boolean val) {
        jCheckBoxOverwriteExisting.setSelected(val);
    }
    
    public boolean getOverwriteExisting() {
        return jCheckBoxOverwriteExisting.isSelected();
    }
    
    public void setCheckReservedWords(boolean val) {
        jCheckBoxCheckReservedWords.setSelected(val);
    }
    
    public boolean getCheckReservedWords() {
        return jCheckBoxCheckReservedWords.isSelected();
    }
    
    public void setTargetNamespace(String val) {
        jTextFieldTargetNamespace.setText(val);
    }
    
    public String getTargetNamespace() {
        return jTextFieldTargetNamespace.getText();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCopybookCodePage = new javax.swing.JLabel();
        jComboBoxCopybookCodePage = new JComboBoxAutoComplete();
        jLabelDISPLAYCodePage = new javax.swing.JLabel();
        jComboBoxPredecodeCoding = new JComboBoxAutoComplete();
        jLabelDISPLAY1CodePage = new javax.swing.JLabel();
        jComboBoxDISPLAYCodePage = new JComboBoxAutoComplete();
        jLabelPredecodeCoding = new javax.swing.JLabel();
        jComboBoxDISPLAY1CodePage = new JComboBoxAutoComplete();
        jLabelPostencodeCoding = new javax.swing.JLabel();
        jComboBoxPostencodeCoding = new JComboBoxAutoComplete();
        jCheckBoxIgnore72ColBeyond = new javax.swing.JCheckBox();
        jCheckBoxCheckReservedWords = new javax.swing.JCheckBox();
        jCheckBoxOverwriteExisting = new javax.swing.JCheckBox();
        jLabelTargetNamespace = new javax.swing.JLabel();
        jTextFieldTargetNamespace = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();

        setMinimumSize(new java.awt.Dimension(0, 0));

        jLabelCopybookCodePage.setDisplayedMnemonic('k');
        jLabelCopybookCodePage.setLabelFor(jComboBoxCopybookCodePage);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelCopybookCodePage, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelCopybookCodePage")); // NOI18N
        jLabelCopybookCodePage.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelCopybookCodePage.toolTipText")); // NOI18N

        jComboBoxCopybookCodePage.setEditable(true);
        jComboBoxCopybookCodePage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCopybookCodePage.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxCopybookCodePage.toolTipText")); // NOI18N
        jComboBoxCopybookCodePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCopybookCodePageActionPerformed(evt);
            }
        });

        jLabelDISPLAYCodePage.setDisplayedMnemonic('D');
        jLabelDISPLAYCodePage.setLabelFor(jComboBoxDISPLAYCodePage);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelDISPLAYCodePage, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAYCodePage")); // NOI18N
        jLabelDISPLAYCodePage.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAYCodePage.toolTipText")); // NOI18N

        jComboBoxPredecodeCoding.setEditable(true);
        jComboBoxPredecodeCoding.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPredecodeCoding.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxPredecodeCoding.toolTipText")); // NOI18N

        jLabelDISPLAY1CodePage.setDisplayedMnemonic('1');
        jLabelDISPLAY1CodePage.setLabelFor(jComboBoxDISPLAY1CodePage);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelDISPLAY1CodePage, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAY1CodePage")); // NOI18N
        jLabelDISPLAY1CodePage.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAY1CodePage.toolTipText")); // NOI18N

        jComboBoxDISPLAYCodePage.setEditable(true);
        jComboBoxDISPLAYCodePage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDISPLAYCodePage.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxDISPLAYCodePage.toolTipText")); // NOI18N

        jLabelPredecodeCoding.setDisplayedMnemonic('P');
        jLabelPredecodeCoding.setLabelFor(jComboBoxPredecodeCoding);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelPredecodeCoding, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelPredecodeCoding")); // NOI18N
        jLabelPredecodeCoding.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelPredecodeCoding.toolTipText")); // NOI18N

        jComboBoxDISPLAY1CodePage.setEditable(true);
        jComboBoxDISPLAY1CodePage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDISPLAY1CodePage.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxDISPLAY1CodePage.toolTipText")); // NOI18N

        jLabelPostencodeCoding.setDisplayedMnemonic('e');
        jLabelPostencodeCoding.setLabelFor(jComboBoxPostencodeCoding);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelPostencodeCoding, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelPostencodeCoding")); // NOI18N
        jLabelPostencodeCoding.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelPostencodeCoding.toolTipText")); // NOI18N

        jComboBoxPostencodeCoding.setEditable(true);
        jComboBoxPostencodeCoding.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPostencodeCoding.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxPostencodeCoding.toolTipText")); // NOI18N

        jCheckBoxIgnore72ColBeyond.setMnemonic('i');
        jCheckBoxIgnore72ColBeyond.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxIgnore72ColBeyond, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxIgnore72ColBeyond")); // NOI18N
        jCheckBoxIgnore72ColBeyond.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxIgnore72ColBeyond.toolTipText")); // NOI18N
        jCheckBoxIgnore72ColBeyond.setActionCommand(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxIgnore72ColBeyond.actionCommand")); // NOI18N
        jCheckBoxIgnore72ColBeyond.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxIgnore72ColBeyond.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jCheckBoxCheckReservedWords.setMnemonic('r');
        jCheckBoxCheckReservedWords.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxCheckReservedWords, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxCheckReservedWords")); // NOI18N
        jCheckBoxCheckReservedWords.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxCheckReservedWords.toolTipText")); // NOI18N
        jCheckBoxCheckReservedWords.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxCheckReservedWords.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jCheckBoxOverwriteExisting.setMnemonic('o');
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxOverwriteExisting, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxOverwriteExisting.text")); // NOI18N
        jCheckBoxOverwriteExisting.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxOverwriteExisting.toolTipText")); // NOI18N
        jCheckBoxOverwriteExisting.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxOverwriteExisting.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabelTargetNamespace.setDisplayedMnemonic('t');
        jLabelTargetNamespace.setLabelFor(jTextFieldTargetNamespace);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelTargetNamespace, org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelTargetNamespace")); // NOI18N
        jLabelTargetNamespace.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelTargetNamespace.toolTipText")); // NOI18N

        jTextFieldTargetNamespace.setToolTipText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextFieldTargetNamespace.toolTipText")); // NOI18N

        jTextPane1.setBackground(new java.awt.Color(240, 240, 240));
        jTextPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jTextPane1.setText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextPane1.text")); // NOI18N
        jScrollPane1.setViewportView(jTextPane1);
        jTextPane1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextPane1.AccessibleContext.accessibleName")); // NOI18N

        jTextPane2.setBackground(new java.awt.Color(240, 240, 240));
        jTextPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jTextPane2.setText(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextPane2.text")); // NOI18N
        jScrollPane2.setViewportView(jTextPane2);
        jTextPane2.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextPane2.AccessibleContext.accessibleName")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 412, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 412, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(10, 10, 10))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelDISPLAYCodePage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelCopybookCodePage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelTargetNamespace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelPostencodeCoding, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabelPredecodeCoding, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, Short.MAX_VALUE)
                            .add(jLabelDISPLAY1CodePage))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBoxPredecodeCoding, 0, 287, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBoxPostencodeCoding, 0, 287, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBoxCopybookCodePage, 0, 287, Short.MAX_VALUE)
                            .add(jComboBoxDISPLAY1CodePage, 0, 287, Short.MAX_VALUE)
                            .add(jTextFieldTargetNamespace, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .add(jComboBoxDISPLAYCodePage, 0, 287, Short.MAX_VALUE))
                        .addContainerGap(10, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jCheckBoxCheckReservedWords)
                            .add(jCheckBoxIgnore72ColBeyond))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jCheckBoxOverwriteExisting)
                        .addContainerGap(260, Short.MAX_VALUE))))
        );

        layout.linkSize(new java.awt.Component[] {jScrollPane1, jScrollPane2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabelCopybookCodePage)
                    .add(jComboBoxCopybookCodePage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelDISPLAYCodePage)
                    .add(jComboBoxDISPLAYCodePage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBoxDISPLAY1CodePage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelDISPLAY1CodePage))
                .add(8, 8, 8)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBoxPredecodeCoding, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelPredecodeCoding))
                .add(8, 8, 8)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBoxPostencodeCoding, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelPostencodeCoding))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelTargetNamespace)
                    .add(jTextFieldTargetNamespace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jCheckBoxIgnore72ColBeyond)
                .add(1, 1, 1)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBoxCheckReservedWords)
                .add(1, 1, 1)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBoxOverwriteExisting)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelCopybookCodePage.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelCopybookCodePage.AccessibleContext.accessibleName")); // NOI18N
        jLabelCopybookCodePage.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelCopybookCodePage.AccessibleContext.accessibleDescription")); // NOI18N
        jComboBoxCopybookCodePage.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxCopybookCodePage.AccessibleContext.accessibleName")); // NOI18N
        jLabelDISPLAYCodePage.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAYCodePage.AccessibleContext.accessibleName")); // NOI18N
        jLabelDISPLAYCodePage.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAYCodePage.AccessibleContext.accessibleDescription")); // NOI18N
        jLabelDISPLAY1CodePage.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAY1CodePage.AccessibleContext.accessibleName")); // NOI18N
        jLabelDISPLAY1CodePage.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelDISPLAY1CodePage.AccessibleContext.accessibleDescription")); // NOI18N
        jComboBoxDISPLAYCodePage.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxDISPLAYCodePage.AccessibleContext.accessibleName")); // NOI18N
        jComboBoxDISPLAY1CodePage.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxDISPLAY1CodePage.AccessibleContext.accessibleName")); // NOI18N
        jLabelPostencodeCoding.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelPostencodeCoding.AccessibleContext.accessibleName")); // NOI18N
        jComboBoxPostencodeCoding.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxPostencodeCoding.AccessibleContext.accessibleName")); // NOI18N
        jComboBoxPostencodeCoding.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jComboBoxPostencodeCoding.AccessibleContext.accessibleDescription")); // NOI18N
        jCheckBoxIgnore72ColBeyond.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxIgnore72ColBeyond.AccessibleContext.accessibleName")); // NOI18N
        jCheckBoxIgnore72ColBeyond.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxIgnore72ColBeyond")); // NOI18N
        jCheckBoxCheckReservedWords.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxCheckReservedWords.AccessibleContext.accessibleName")); // NOI18N
        jCheckBoxCheckReservedWords.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxCheckReservedWords")); // NOI18N
        jCheckBoxOverwriteExisting.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxOverwriteExisting.AccessibleContext.accessibleName")); // NOI18N
        jCheckBoxOverwriteExisting.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jCheckBoxOverwriteExisting")); // NOI18N
        jLabelTargetNamespace.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelTargetNamespace.AccessibleContext.accessibleName")); // NOI18N
        jLabelTargetNamespace.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jLabelTargetNamespace.AccessibleContext.accessibleDescription")); // NOI18N
        jTextFieldTargetNamespace.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextFieldTargetNamespace.AccessibleContext.accessibleName")); // NOI18N
        jTextFieldTargetNamespace.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jTextFieldTargetNamespace.AccessibleContext.accessibleDescription")); // NOI18N
        jScrollPane1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jScrollPane1.AccessibleContext.accessibleName")); // NOI18N
        jScrollPane1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jScrollPane1.AccessibleContext.accessibleDescription")); // NOI18N
        jScrollPane2.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jScrollPane2.AccessibleContext.accessibleName")); // NOI18N
        jScrollPane2.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.jScrollPane2.AccessibleContext.accessibleDescription")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(GenerateXSDVisualPanel.class, "GenerateXSDVisualPanel.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCopybookCodePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCopybookCodePageActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jComboBoxCopybookCodePageActionPerformed

    private void setComboBoxList(JComboBox comboBox, String[] list) {
        comboBox.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            comboBox.addItem(list[i]);
        }
        comboBox.setSelectedIndex(-1);
        comboBox.setEditor(new BasicComboBoxEditor());
        comboBox.setEditable(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxCheckReservedWords;
    private javax.swing.JCheckBox jCheckBoxIgnore72ColBeyond;
    private javax.swing.JCheckBox jCheckBoxOverwriteExisting;
    private javax.swing.JComboBox jComboBoxCopybookCodePage;
    private javax.swing.JComboBox jComboBoxDISPLAY1CodePage;
    private javax.swing.JComboBox jComboBoxDISPLAYCodePage;
    private javax.swing.JComboBox jComboBoxPostencodeCoding;
    private javax.swing.JComboBox jComboBoxPredecodeCoding;
    private javax.swing.JLabel jLabelCopybookCodePage;
    private javax.swing.JLabel jLabelDISPLAY1CodePage;
    private javax.swing.JLabel jLabelDISPLAYCodePage;
    private javax.swing.JLabel jLabelPostencodeCoding;
    private javax.swing.JLabel jLabelPredecodeCoding;
    private javax.swing.JLabel jLabelTargetNamespace;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldTargetNamespace;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
    
}