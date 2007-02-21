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

/*
 * NamespacePanel.java
 *
 * Created on December 20, 2005, 5:49 PM
 */

package org.netbeans.modules.compapp.casaeditor.properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.namespace.QName;
import org.netbeans.modules.compapp.casaeditor.properties.NamespaceEditor.PrefixNamespacePair;

/**
 * Modified from SchemaUI.
 * @author Todd Fast, todd.fast@sun.com
 */
public class NamespaceEditorPanel extends JPanel
implements ActionListener, ListSelectionListener, DocumentListener {

    static final long serialVersionUID = 1L;
    
    public static final String PROP_VALID_SELECTION="validSelection";
    
    private boolean isValid = true;
    private Collection<PrefixNamespacePair> mURIs;
    
    
    public NamespaceEditorPanel(
            QName currentTns, 
            Collection<PrefixNamespacePair> uris, 
            Collection<NamespaceEditor.Option> options)
    {
        mURIs = uris;
        initComponents();
        initializeCustomListeners();
        initialize(currentTns, options);
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        schemaNamespaceRadioButton = new javax.swing.JRadioButton();
        otherNamespaceRadioButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        uriList = new javax.swing.JList();
        otherNamespaceTextField = new javax.swing.JTextField();
        dummyLabel1 = new javax.swing.JLabel();
        dummyLabel2 = new javax.swing.JLabel();
        namespaceEntryLabel = new javax.swing.JLabel();
        prefixEntryLabel = new javax.swing.JLabel();
        prefixTextField = new javax.swing.JTextField();
        localNameEntryLabel = new javax.swing.JLabel();
        localNameTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(schemaNamespaceRadioButton, org.openide.util.NbBundle.getMessage(NamespaceEditorPanel.class, "LBL_SchemaNamespace"));
        schemaNamespaceRadioButton.setToolTipText(org.openide.util.NbBundle.getMessage(NamespaceEditorPanel.class, "HINT_SchemaNamespace"));
        schemaNamespaceRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        schemaNamespaceRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        buttonGroup1.add(schemaNamespaceRadioButton);
        schemaNamespaceRadioButton.addActionListener(this);

        org.openide.awt.Mnemonics.setLocalizedText(otherNamespaceRadioButton, org.openide.util.NbBundle.getMessage(NamespaceEditorPanel.class, "LBL_OtherNamespace"));
        otherNamespaceRadioButton.setToolTipText(org.openide.util.NbBundle.getMessage(NamespaceEditorPanel.class, "HINT_OtherNamespace"));
        otherNamespaceRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        otherNamespaceRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        buttonGroup1.add(otherNamespaceRadioButton);
        otherNamespaceRadioButton.addActionListener(this);

        uriList.addListSelectionListener(this);
        jScrollPane1.setViewportView(uriList);

        otherNamespaceTextField.getDocument().addDocumentListener(this);
        otherNamespaceTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(NamespaceEditorPanel.class, "HINT_OtherNamespace"));
        otherNamespaceTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NamespaceEditorPanel.class, "HINT_OtherNamespace"));

        org.openide.awt.Mnemonics.setLocalizedText(namespaceEntryLabel, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/casaeditor/properties/Bundle").getString("LBL_NamespaceEntry"));

        org.openide.awt.Mnemonics.setLocalizedText(prefixEntryLabel, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/casaeditor/properties/Bundle").getString("LBL_PrefixEntry"));

        prefixTextField.setToolTipText(java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/casaeditor/properties/Bundle").getString("HINT_Prefix"));

        org.openide.awt.Mnemonics.setLocalizedText(localNameEntryLabel, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/casaeditor/properties/Bundle").getString("LBL_LocalNameEntry"));

        localNameTextField.setToolTipText(java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/casaeditor/properties/Bundle").getString("HINT_LocalName"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, dummyLabel1)
                    .add(layout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                    .add(otherNamespaceRadioButton)
                    .add(layout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(prefixEntryLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(prefixTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(namespaceEntryLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(otherNamespaceTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))))
                    .add(layout.createSequentialGroup()
                        .add(schemaNamespaceRadioButton)
                        .add(22, 22, 22)
                        .add(dummyLabel2)
                        .add(151, 151, 151))
                    .add(layout.createSequentialGroup()
                        .add(localNameEntryLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(localNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {namespaceEntryLabel, prefixEntryLabel}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(dummyLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dummyLabel2)
                    .add(localNameEntryLabel)
                    .add(localNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(schemaNamespaceRadioButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(otherNamespaceRadioButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(namespaceEntryLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(otherNamespaceTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(prefixEntryLabel)
                    .add(prefixTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void initializeCustomListeners() {
        prefixTextField.getDocument().addDocumentListener(this);
    }
    
    private void initialize(
            QName currentTns, 
            Collection<NamespaceEditor.Option> options)
    {
        PrefixNamespacePair currentPairing = null;
        if (currentTns != null) {
            currentPairing = new PrefixNamespacePair(
                    currentTns.getPrefix(), 
                    currentTns.getNamespaceURI());
        }
        
        // hide radio button for uri list and uri list
        if (mURIs == null || mURIs.isEmpty()) {
            schemaNamespaceRadioButton.setVisible(false);
            uriList.setVisible(false);
            jScrollPane1.setVisible(false);
        } else {
            uriList.setListData(mURIs.toArray(new PrefixNamespacePair[mURIs.size()]));
        }
        if(currentTns==null) {
            uriList.setEnabled(false);
            if (otherNamespaceRadioButton.isVisible()) {
                otherNamespaceRadioButton.setSelected(true);
                otherNamespaceTextField.setEnabled(true);
                prefixTextField.setEnabled(true);
            }
        } else if (mURIs != null && mURIs.contains(currentPairing)) {
            schemaNamespaceRadioButton.setSelected(true);
            uriList.setSelectedValue(currentPairing,true);
            uriList.setEnabled(true);
            otherNamespaceTextField.setEnabled(false);
            prefixTextField.setEnabled(false);
        } else {
            uriList.setEnabled(false);
            if(otherNamespaceRadioButton.isVisible()) {
                otherNamespaceRadioButton.setSelected(true);
                otherNamespaceTextField.setText(currentTns.getNamespaceURI());
                prefixTextField.setText(currentTns.getPrefix());
                localNameTextField.setText(currentTns.getLocalPart());
                otherNamespaceTextField.setEnabled(true);
                prefixTextField.setEnabled(true);
            }
        }
    }
    
    public void setEditable(boolean isEditable) {
        schemaNamespaceRadioButton.setEnabled(isEditable);
        otherNamespaceRadioButton.setEnabled(isEditable);
        uriList.setEnabled(isEditable);
        localNameTextField.setEditable(isEditable);
        prefixTextField.setEditable(isEditable);
        otherNamespaceTextField.setEditable(isEditable);
    }
    
    public void checkValidity() {
        boolean newValue = false;
        if (schemaNamespaceRadioButton.isSelected()) {
            newValue = 
                localNameTextField.getText().trim().length() > 0 &&
                uriList.getSelectedValue() != null;
        } else if (otherNamespaceRadioButton.isSelected()) {
            newValue = 
                localNameTextField.getText().trim().length() > 0      &&
                otherNamespaceTextField.getText().trim().length() > 0 &&
                prefixTextField.getText().trim().length() > 0;
        }
        firePropertyChange(PROP_VALID_SELECTION, isValid, newValue);
        isValid = newValue;
    }

    public QName getCurrentSelection() {
        if (schemaNamespaceRadioButton.isSelected()) {
            PrefixNamespacePair selectedPair = 
                    (PrefixNamespacePair) uriList.getSelectedValue();
            return new QName(
                    selectedPair.getNamespace(), 
                    localNameTextField.getText().trim(), 
                    selectedPair.getPrefix());
        } else if (otherNamespaceRadioButton.isSelected()) {
            return new QName(
                    otherNamespaceTextField.getText().trim(), 
                    localNameTextField.getText().trim(), 
                    prefixTextField.getText().trim());
        }
        return null;
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source.equals(otherNamespaceRadioButton)) {
            uriList.setEnabled(false);
            otherNamespaceTextField.setEnabled(true);
            prefixTextField.setEnabled(true);
        } else if(source.equals(schemaNamespaceRadioButton)) {
            otherNamespaceTextField.setEnabled(false);
            prefixTextField.setEnabled(false);
            uriList.setEnabled(true);
        }
        checkValidity();
    }

    public void valueChanged(ListSelectionEvent e) {
        checkValidity();
    }

    public void insertUpdate(DocumentEvent e) {
        checkValidity();
    }

    public void removeUpdate(DocumentEvent e) {
        checkValidity();
    }

    public void changedUpdate(DocumentEvent e) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JLabel dummyLabel1;
    public javax.swing.JLabel dummyLabel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel localNameEntryLabel;
    public javax.swing.JTextField localNameTextField;
    public javax.swing.JLabel namespaceEntryLabel;
    public javax.swing.JRadioButton otherNamespaceRadioButton;
    public javax.swing.JTextField otherNamespaceTextField;
    public javax.swing.JLabel prefixEntryLabel;
    public javax.swing.JTextField prefixTextField;
    public javax.swing.JRadioButton schemaNamespaceRadioButton;
    public javax.swing.JList uriList;
    // End of variables declaration//GEN-END:variables

}
