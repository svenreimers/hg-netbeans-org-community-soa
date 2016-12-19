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

package org.netbeans.modules.soa.jca.jms.ui;

import org.netbeans.modules.soa.jca.base.generator.api.GeneratorUtil;
import org.netbeans.modules.soa.jca.base.generator.api.JavacTreeModel;
import org.netbeans.modules.soa.jca.base.generator.api.JndiBrowser;
import org.netbeans.modules.soa.jca.jms.DestinationPaletteDrop.DestinationType;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.project.Project;
import org.openide.DialogDescriptor;

/**
 *
 * @author  echou
 */
public class DestinationPanel extends javax.swing.JPanel implements DocumentListener {

    private Project project;
    private JavacTreeModel javacTreeModel;
    private DestinationType destType;
    private DialogDescriptor d;

    /** Creates new form DestinationPanel */
    public DestinationPanel(Project project, JavacTreeModel javacTreeModel, DestinationType destType) {
        this.project = project;
        this.javacTreeModel = javacTreeModel;
        this.destType = destType;
        initComponents();

        // set component names for easier testability
        jndiTextField.setName("jndiTxt"); // NOI18N
        variableTextField.setName("variableTxt"); // NOI18N

        if (destType == DestinationType.QUEUE) {
            variableTextField.setText(getNextAvailableVariableName("queue")); // NOI18N
        } else if (destType == DestinationType.TOPIC) {
            variableTextField.setText(getNextAvailableVariableName("topic")); // NOI18N
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jndiLabel = new javax.swing.JLabel();
        jndiTextField = new javax.swing.JTextField();
        jndiButton = new javax.swing.JButton();
        variableLabel = new javax.swing.JLabel();
        variableTextField = new javax.swing.JTextField();
        errorLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(350, 100));
        setLayout(new java.awt.GridBagLayout());

        jndiLabel.setLabelFor(jndiTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jndiLabel, org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jndiLabel, gridBagConstraints);
        jndiLabel.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiLabel.AccessibleContext.accessibleDescription")); // NOI18N

        jndiTextField.setText(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiTextField.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jndiTextField, gridBagConstraints);
        jndiTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiTextField.AccessibleContext.accessibleName")); // NOI18N
        jndiTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiTextField.AccessibleContext.accessibleDescription")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jndiButton, org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiButton.text")); // NOI18N
        jndiButton.setMinimumSize(new java.awt.Dimension(23, 23));
        jndiButton.setPreferredSize(new java.awt.Dimension(23, 23));
        jndiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jndiButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jndiButton, gridBagConstraints);
        jndiButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "a11y.name.browsebutton")); // NOI18N
        jndiButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jndiButton.AccessibleContext.accessibleDescription")); // NOI18N

        variableLabel.setLabelFor(variableTextField);
        org.openide.awt.Mnemonics.setLocalizedText(variableLabel, org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.variableLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(variableLabel, gridBagConstraints);
        variableLabel.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.variableLabel.AccessibleContext.accessibleDescription")); // NOI18N

        variableTextField.setText(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.variableTextField.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(variableTextField, gridBagConstraints);
        variableTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.variableTextField.AccessibleContext.accessibleName")); // NOI18N
        variableTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.variableTextField.AccessibleContext.accessibleDescription")); // NOI18N

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setLabelFor(this);
        org.openide.awt.Mnemonics.setLocalizedText(errorLabel, " ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(errorLabel, gridBagConstraints);
        errorLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.errorLabel.AccessibleContext.accessibleName")); // NOI18N
        errorLabel.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.errorLabel.AccessibleContext.accessibleDescription")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabel1, gridBagConstraints);

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DestinationPanel.class, "DestinationPanel.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void jndiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jndiButtonActionPerformed
        String jndiStr = JndiBrowser.popupJndiBrowserDialog(project, JndiBrowser.Category.ADMIN_OBJECT);
        jndiTextField.setText(jndiStr);
    }//GEN-LAST:event_jndiButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jndiButton;
    private javax.swing.JLabel jndiLabel;
    private javax.swing.JTextField jndiTextField;
    private javax.swing.JLabel variableLabel;
    private javax.swing.JTextField variableTextField;
    // End of variables declaration//GEN-END:variables

    public void setDialogDescriptor(DialogDescriptor d) {
        this.d = d;
        this.d.setValid(isPanelValid());
        jndiTextField.getDocument().addDocumentListener(this);
        variableTextField.getDocument().addDocumentListener(this);
    }

    private boolean isPanelValid() {
        if (jndiTextField.getText() == null || jndiTextField.getText().length() == 0) {
            errorLabel.setText("JNDI name cannot be empty.");
            return false;
        }
        String variableName = variableTextField.getText();
        if (variableName == null || variableName.length() == 0) {
            errorLabel.setText("Variable name cannot be empty.");
            return false;
        }
        if (!GeneratorUtil.isJavaIdentifier(variableName)) {
            errorLabel.setText("Not valid Java identifier for variable name.");
            return false;
        }
        if (javacTreeModel.getVariablesByName(variableName).size() > 0) {
            errorLabel.setText("Variable name already in use.");
            return false;
        }
        errorLabel.setText(null);
        return true;
    }

    private String getNextAvailableVariableName(String variableName) {
        if (javacTreeModel.getVariablesByName(variableName).size() > 0) {
            for (int i = 2; i < Integer.MAX_VALUE; i++) {
                String curVar = variableName + i;
                if (javacTreeModel.getVariablesByName(curVar).size() == 0) {
                    return curVar;
                }
            }
        }
        return variableName;
    }

    private void changeUpdate() {
        d.setValid(isPanelValid());
    }

    public void insertUpdate(DocumentEvent e) {
        changeUpdate();
    }
    public void removeUpdate(DocumentEvent e) {
        changeUpdate();
    }
    public void changedUpdate(DocumentEvent e) {
        changeUpdate();
    }

    public String getJndiName() {
        return jndiTextField.getText();
    }

    public String getVariableName() {
        return variableTextField.getText();
    }
}