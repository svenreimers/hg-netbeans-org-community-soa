/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.sun.manager.jbi.management;

import java.util.ResourceBundle;

/**
 *
 * @author Ludo, Petr Hrebejk
 */
// stolen from org.netbeans.modules.j2ee.sun.ide.editors.AdminAuthenticator
public class PasswordPanel extends javax.swing.JPanel {

    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 0;
    /** Generated serialVersionUID */
    static final long serialVersionUID = 1555749205340031767L;
    ResourceBundle bundle = org.openide.util.NbBundle.getBundle(PasswordPanel.class);

    /** Creates new form PasswordPanel */
    public PasswordPanel(String userName) {
        initComponents();
        usernameField.setText(userName);
        usernameField.setSelectionStart(0);
        usernameField.setSelectionEnd(userName.length());
        usernameField.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_UserNameField"));
        passwordField.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_PasswordField"));
    }

    public java.awt.Dimension getPreferredSize() {
        java.awt.Dimension sup = super.getPreferredSize();
        return new java.awt.Dimension(Math.max(sup.width, DEFAULT_WIDTH), Math.max(sup.height, DEFAULT_HEIGHT));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {
        setLayout(new java.awt.BorderLayout());

        mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        mainPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 0, 11)));

        promptLabel = new javax.swing.JLabel();
        promptLabel.setHorizontalAlignment(0);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 6, 0);
        mainPanel.add(promptLabel, gridBagConstraints1);

        jLabel1 = new javax.swing.JLabel();
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1,
                bundle.getString("LAB_AUTH_User_Name")); // NOI18N            

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 5, 12);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(jLabel1, gridBagConstraints1);

        usernameField = new javax.swing.JTextField();
        usernameField.setMinimumSize(new java.awt.Dimension(70, 20));
        usernameField.setPreferredSize(new java.awt.Dimension(70, 20));
        jLabel1.setLabelFor(usernameField);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 5, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        mainPanel.add(usernameField, gridBagConstraints1);

        jLabel2 = new javax.swing.JLabel();
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2,
                bundle.getString("LAB_AUTH_Password")); // NOI18N            

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 12);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(jLabel2, gridBagConstraints1);

        passwordField = new javax.swing.JPasswordField();
        passwordField.setMinimumSize(new java.awt.Dimension(70, 20));
        passwordField.setPreferredSize(new java.awt.Dimension(70, 20));
        jLabel2.setLabelFor(passwordField);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        mainPanel.add(passwordField, gridBagConstraints1);

        add(mainPanel, "Center"); // NOI18N

    }    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel promptLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passwordField;
    // End of variables declaration//GEN-END:variables
    String getUsername() {
        return usernameField.getText();
    }

    char[] getPassword() {
        return passwordField.getPassword();
    }

    String getTPassword() {
        return passwordField.getText();
    }

    void setPrompt(String prompt) {
        if (prompt == null) {
            promptLabel.setVisible(false);
            getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_NbAuthenticatorPasswordPanel"));
        } else {
            promptLabel.setVisible(true);
            promptLabel.setText(prompt);
            getAccessibleContext().setAccessibleDescription(prompt);
        }
    }
}
