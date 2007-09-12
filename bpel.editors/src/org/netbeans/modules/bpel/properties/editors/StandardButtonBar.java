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
package org.netbeans.modules.bpel.properties.editors;

import javax.swing.JPanel;

/**
 * This panel is responsible for creation of standatr button bar with the following buttons:
 * Up, Down, Add, Edit, Delete.
 * Button variables have public access, so they can be easy hidden or renamed if necessaty.
 * @author  nk160297
 */
public class StandardButtonBar extends JPanel {
    
    static final long serialVersionUID = 1L;
    
    public StandardButtonBar() {
        super();
    }

    public void createContent() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        btnAdd.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_Add"));
        btnAdd.setMargin(new java.awt.Insets(2, 5, 2, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(btnAdd, gridBagConstraints);
        btnAdd.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_Add"));
        btnAdd.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_Add"));

        btnEdit.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_Edit"));
        btnEdit.setMargin(new java.awt.Insets(2, 5, 2, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(btnEdit, gridBagConstraints);
        btnEdit.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_Edit"));
        btnEdit.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_Edit"));

        btnDelete.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_Remove"));
        btnDelete.setMargin(new java.awt.Insets(2, 5, 2, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(btnDelete, gridBagConstraints);
        btnDelete.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_Remove"));
        btnDelete.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_Remove"));

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        btnUp.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_Up"));
        btnUp.setMargin(new java.awt.Insets(2, 5, 2, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(btnUp, gridBagConstraints);
        btnUp.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_Up"));
        btnUp.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_Up"));

        btnDown.setText(org.openide.util.NbBundle.getMessage(FormBundle.class, "BTN_Down"));
        btnDown.setMargin(new java.awt.Insets(2, 5, 2, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        add(btnDown, gridBagConstraints);
        btnDown.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSN_BTN_Down"));
        btnDown.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FormBundle.class,"ACSD_BTN_Down"));

    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAdd;
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnDown;
    public javax.swing.JButton btnEdit;
    public javax.swing.JButton btnUp;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
