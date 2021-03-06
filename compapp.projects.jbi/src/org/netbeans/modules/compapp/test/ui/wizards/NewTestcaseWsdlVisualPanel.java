/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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

package org.netbeans.modules.compapp.test.ui.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.netbeans.api.project.Project;
import org.openide.explorer.ExplorerManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;

public class NewTestcaseWsdlVisualPanel extends javax.swing.JPanel implements TreeSelectionListener {

    private Project mProject;
    private WsdlTreeViewPanel mWsdlTreeViewPanel;   
    private NewTestcaseWsdlWizardPanel mPanel;
    
    /** Creates new form NewTestcaseWsdlVisualPanel */
    public NewTestcaseWsdlVisualPanel(Project project, NewTestcaseWsdlWizardPanel panel) {
        mProject = project;     
        mPanel = panel;
        
        initComponents(); 
    }
     
    @Override
    public String getName() {
        return NbBundle.getMessage(NewTestcaseWsdlVisualPanel.class, 
                                   "LBL_Select_the_WSDL_document");  // NOI18N
    }

    public FileObject getSelectedWsdlFile() {
        return mWsdlTreeViewPanel.getSelectedWsdlFile();
    }

    public WsdlTreeViewPanel getWsdlTreeView() {
        return mWsdlTreeViewPanel;
    }
        
    public void valueChanged(TreeSelectionEvent e) {
         mPanel.fireChangeEvent(); // Notify that the panel changed
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelSelectedWSDL = new javax.swing.JLabel();
        jTextFieldSelectedWSDL = new javax.swing.JTextField();
        jLabelWSDLDocuments = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                expandWsdlTree(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 396, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 251, Short.MAX_VALUE)
        );

        jLabelSelectedWSDL.setLabelFor(jTextFieldSelectedWSDL);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelSelectedWSDL, org.openide.util.NbBundle.getMessage(NewTestcaseWsdlVisualPanel.class, "LBL_The_WSDL_selected")); // NOI18N

        jTextFieldSelectedWSDL.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelWSDLDocuments, org.openide.util.NbBundle.getMessage(NewTestcaseWsdlVisualPanel.class, "LBL_WSDL_Documents")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabelSelectedWSDL)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldSelectedWSDL, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(jLabelWSDLDocuments)
                .addContainerGap())
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabelWSDLDocuments)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelSelectedWSDL)
                    .add(jTextFieldSelectedWSDL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        // init the WSDL tree view
        {
            mWsdlTreeViewPanel = new WsdlTreeViewPanel(mProject);
            mWsdlTreeViewPanel.getExplorerManager().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent evt) {
                    if(!evt.getPropertyName().equals(ExplorerManager.PROP_SELECTED_NODES)) {
                        return;
                    }
                    FileObject wsdlFile = mWsdlTreeViewPanel.getSelectedWsdlFile();
                    jTextFieldSelectedWSDL.setText(wsdlFile == null ?
                        "" : FileUtil.toFile(wsdlFile).getPath());  // NOI18N
                }
            });

            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mWsdlTreeViewPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mWsdlTreeViewPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            );

            JTree tree = (JTree) mWsdlTreeViewPanel.getTreeView().getViewport().getComponent(0);
            tree.getSelectionModel().addTreeSelectionListener(this);
            jLabelWSDLDocuments.setLabelFor(tree);
        }
        jLabelSelectedWSDL.getAccessibleContext().setAccessibleName("Selected WSDL: ");
        jLabelSelectedWSDL.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewTestcaseWsdlVisualPanel.class, "ACS_SELECTED_WSDL_LABEL")); // NOI18N
        jLabelWSDLDocuments.getAccessibleContext().setAccessibleName("WSDL Documents:");
        jLabelWSDLDocuments.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewTestcaseWsdlVisualPanel.class, "ACS_WSDL_DOCUMENTS_LABEL")); // NOI18N

        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewTestcaseWsdlVisualPanel.class, "ACS_NewTestcaseWsdlVisualPanel_A11YDesc")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void expandWsdlTree(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_expandWsdlTree
        /*JTree tree = (JTree) mWsdlTreeView.getTreeView().getViewport().getComponent(0);
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        int cnt = root.getChildCount();
        for (int i = cnt - 1; i >= 0; i--) {
            System.out.println(((DefaultMutableTreeNode)root.getChildAt(i)).getPath());
            tree.expandPath(new TreePath(((DefaultMutableTreeNode)root.getChildAt(i)).getPath()));
        }*/
}//GEN-LAST:event_expandWsdlTree

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelSelectedWSDL;
    private javax.swing.JLabel jLabelWSDLDocuments;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldSelectedWSDL;
    // End of variables declaration//GEN-END:variables
    
}
