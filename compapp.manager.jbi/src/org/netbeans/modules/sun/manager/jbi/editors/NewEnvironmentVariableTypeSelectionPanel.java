/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package org.netbeans.modules.sun.manager.jbi.editors;

/**
 *
 * @author  jqian
 */
public class NewEnvironmentVariableTypeSelectionPanel extends javax.swing.JPanel {
    
    /** Creates new form NewEnvironmentVariableTypeSelectionPanel */
    public NewEnvironmentVariableTypeSelectionPanel() {
        initComponents();
    }
    
    public String getTypeChoice() {
        return buttonGroup1.getSelection().getActionCommand();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        typeSelectionLabel = new javax.swing.JLabel();
        stringRB = new javax.swing.JRadioButton();
        numberRB = new javax.swing.JRadioButton();
        booleanRB = new javax.swing.JRadioButton();
        passwordRB = new javax.swing.JRadioButton();

        typeSelectionLabel.setText(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "LBL_TYPE_SELECTION")); // NOI18N

        buttonGroup1.add(stringRB);
        stringRB.setSelected(true);
        stringRB.setText(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "LBL_STRING_TYPE")); // NOI18N
        stringRB.setActionCommand("STRING"); // NOI18N
        stringRB.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        stringRB.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup1.add(numberRB);
        numberRB.setText(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "LBL_NUMBER_TYPE")); // NOI18N
        numberRB.setActionCommand("NUMBER"); // NOI18N
        numberRB.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        numberRB.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup1.add(booleanRB);
        booleanRB.setText(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "LBL_BOOLEAN_TYPE")); // NOI18N
        booleanRB.setActionCommand("BOOLEAN"); // NOI18N
        booleanRB.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        booleanRB.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup1.add(passwordRB);
        passwordRB.setText(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "LBL_PASSWORD_TYPE")); // NOI18N
        passwordRB.setActionCommand("PASSWORD"); // NOI18N
        passwordRB.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        passwordRB.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(numberRB)
                            .add(stringRB)
                            .add(booleanRB)
                            .add(passwordRB)))
                    .add(typeSelectionLabel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(typeSelectionLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(stringRB)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numberRB)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(booleanRB)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(passwordRB)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        typeSelectionLabel.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "ACS_TYPE_SELECTION")); // NOI18N
        stringRB.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "ACS_STRING_TYPE")); // NOI18N
        numberRB.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "ACS_NUMBER_TYPE")); // NOI18N
        booleanRB.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "ACS_BOOLEAN_TYPE")); // NOI18N
        passwordRB.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewEnvironmentVariableTypeSelectionPanel.class, "ACS_PASSWORD_TYPE")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton booleanRB;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton numberRB;
    private javax.swing.JRadioButton passwordRB;
    private javax.swing.JRadioButton stringRB;
    private javax.swing.JLabel typeSelectionLabel;
    // End of variables declaration//GEN-END:variables
    
}
