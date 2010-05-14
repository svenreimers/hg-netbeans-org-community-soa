/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
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
 * Portions Copyrighted 1997-2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.worklist.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.modules.worklist.editor.chooser.OperationChooser;
import org.netbeans.modules.xml.wsdl.model.Operation;

import org.openide.loaders.DataObject;

public final class TaskPanel extends JPanel {
    
    public static final String PROP_WSDL_OPERATION = "PROP_WSDL_OPERATION";
    private DataObject dObj;
    
    private Operation selectedOperation;
    
    private WorklistWizardBottomPanel1 parent;
       
    /** Creates new form WorklistVisualPanel1 */
    public TaskPanel(WorklistWizardBottomPanel1 parent, DataObject folder) {
        this.dObj = folder;
        this.parent = parent;
        initComponents();
        initGUI();
    }
    
    public String getName() {
        return "Task Name and WSDL Operation";
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        taskNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        wsdlOperationTextField = new javax.swing.JTextField();
        selectOperationButton = new javax.swing.JButton();

        jLabel1.setLabelFor(taskNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.jLabel1.text")); // NOI18N

        jLabel2.setLabelFor(wsdlOperationTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.jLabel2.text")); // NOI18N

        wsdlOperationTextField.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(selectOperationButton, org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.selectOperationButton.text")); // NOI18N
        selectOperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOperationButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(wsdlOperationTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .add(taskNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectOperationButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(taskNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selectOperationButton)
                    .add(wsdlOperationTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)))
        );

        jLabel1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.jLabel1.AccessibleContext.accessibleName")); // NOI18N
        jLabel1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.jLabel1.AccessibleContext.accessibleDescription")); // NOI18N
        taskNameTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.taskNameTextField.AccessibleContext.accessibleName")); // NOI18N
        taskNameTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.taskNameTextField.AccessibleContext.accessibleDescription")); // NOI18N
        jLabel2.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.jLabel2.AccessibleContext.accessibleName")); // NOI18N
        jLabel2.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.jLabel2.AccessibleContext.accessibleDescription")); // NOI18N
        wsdlOperationTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.wsdlOperationTextField.AccessibleContext.accessibleName")); // NOI18N
        wsdlOperationTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.wsdlOperationTextField.AccessibleContext.accessibleDescription")); // NOI18N
        selectOperationButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.selectOperationButton.AccessibleContext.accessibleName")); // NOI18N
        selectOperationButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.selectOperationButton.AccessibleContext.accessibleDescription")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(TaskPanel.class, "TaskPanel.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

private void selectOperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOperationButtonActionPerformed
//    WSDLOperationPanel wsdlOperations = new WSDLOperationPanel(this.dObj);
//    DialogDescriptor  dd = new DialogDescriptor(wsdlOperations, "select operation");
//    dd.setValid(false);
//    ExplorerPropertyChangeListener listener = new ExplorerPropertyChangeListener(dd);
//    wsdlOperations.getExplorerManager().addPropertyChangeListener(listener);
//    DialogDisplayer dDis = DialogDisplayer.getDefault();
//    dDis.notify(dd);
//
//    if (dd.getValue() == DialogDescriptor.OK_OPTION ) {
//        Operation oldOperation = this.selectedOperation;
//        this.selectedOperation = listener.getSelectedOperation();
//        if(this.selectedOperation != null) {
//            this.wsdlOperationTextField.setText(this.selectedOperation.getName());
//        }
//
//        pSupport.firePropertyChange(PROP_WSDL_OPERATION, oldOperation, selectedOperation);
//    }

    Operation oldOperation = selectedOperation;
    Operation newOperation = new OperationChooser(dObj)
            .choose(oldOperation);
    
    if (newOperation != null && newOperation != oldOperation) {
        selectedOperation = newOperation;

        this.wsdlOperationTextField.setText(newOperation.getName());

        firePropertyChange(PROP_WSDL_OPERATION, oldOperation, newOperation);
    }

}//GEN-LAST:event_selectOperationButtonActionPerformed
    
    public String getTaskName() {
        return this.taskNameTextField.getText();
    }
    
    public void setTaskName(String taskName) {
        this.taskNameTextField.setText(taskName);
    }
    
    public Operation getSelectedOperation() {
        return this.selectedOperation;
    }
    
    public JTextField getTaskNameTextField() {
        return this.taskNameTextField;
    }
    
    public JTextField getTaskOperationTextField() {
        return this.wsdlOperationTextField;
    }
    
    private void initGUI() {
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton selectOperationButton;
    private javax.swing.JTextField taskNameTextField;
    private javax.swing.JTextField wsdlOperationTextField;
    // End of variables declaration//GEN-END:variables
    
}

