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

package org.netbeans.modules.worklist.editor.multiview;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.wlm.model.api.DeadlineOrDuration;
import org.netbeans.modules.wlm.model.api.TDeadlineExpr;
import org.netbeans.modules.wlm.model.api.TDurationExpr;
import org.netbeans.modules.wlm.model.api.TEscalation;
import org.netbeans.modules.wlm.model.api.TExpression;
import org.netbeans.modules.worklist.dataloader.WorklistDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionInnerPanel;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author  radval
 */
public class DeadlineOrDurationPanel extends SectionInnerPanel {
    
    private WorklistDataObject dObj;
    
    private DeadlineOrDuration deadlineOrDuration;
    
    private TExpression expression;
    
    private String oldDeadlineOrDurationValue;
    
   
    private boolean isDeadline = true;
    
    /** Creates new form DeadlineOrDurationPanel */
    public DeadlineOrDurationPanel(SectionView sectionView, 
                                    WorklistDataObject dObj,
                                    DeadlineOrDuration deadlineOrDuration) {
        super(sectionView);
        this.dObj=dObj;
        this.deadlineOrDuration = deadlineOrDuration;
        
        expression = this.deadlineOrDuration.getDeadline();
        if(expression == null) {
            expression = this.deadlineOrDuration.getDuration();
        }

        if(expression == null) {
            expression = dObj.getModel().getFactory().createDeadline(dObj.getModel());
            dObj.getModel().startTransaction();
            try {
                this.deadlineOrDuration.setDeadline((TDeadlineExpr) expression);
            } finally {
                dObj.getModel().endTransaction();
            }
        }
        
        initComponents();
        initGUI();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        deadlineRadioButton = new javax.swing.JRadioButton();
        durationRadioButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        deadlineOrDurationTextArea = new javax.swing.JTextArea();

        deadlineRadioButton.setText(org.openide.util.NbBundle.getMessage(DeadlineOrDurationPanel.class, "DeadlineOrDurationPanel.deadlineRadioButton.text")); // NOI18N
        deadlineRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        deadlineRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        durationRadioButton.setText(org.openide.util.NbBundle.getMessage(DeadlineOrDurationPanel.class, "DeadlineOrDurationPanel.durationRadioButton.text")); // NOI18N
        durationRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        durationRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        deadlineOrDurationTextArea.setColumns(20);
        deadlineOrDurationTextArea.setRows(5);
        jScrollPane1.setViewportView(deadlineOrDurationTextArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(deadlineRadioButton)
                    .add(durationRadioButton)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .add(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(deadlineRadioButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(durationRadioButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    public void linkButtonPressed(Object ddBean, String property) {
    }
    
    public javax.swing.JComponent getErrorComponent(String name) {
        return null;
    }
    
    public void setValue(JComponent source, Object value)  {
        
    }
    
    public void documentChanged(javax.swing.text.JTextComponent comp, String value) {
    }
    
    public void rollbackValue(javax.swing.text.JTextComponent source) {
    }
    
    private void initGUI() {
        buttonGroup.add(deadlineRadioButton);
        buttonGroup.add(durationRadioButton);
        
        if(expression != null) {
            if(expression instanceof TDeadlineExpr) {
                buttonGroup.setSelected(deadlineRadioButton.getModel(), true);
                isDeadline = true;
            } else {
                buttonGroup.setSelected(durationRadioButton.getModel(), true);
                isDeadline = false;
            }
            
            deadlineOrDurationTextArea.setText(expression.getContent());
        } 
        
        buttonGroup.getSelection().addItemListener(new DeadlineOrDurationItemListener());
        deadlineOrDurationTextArea.getDocument().addDocumentListener(new DeadlineOrDurationDocumentListener());
    }
    
    class DeadlineOrDurationItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            String oldText = deadlineOrDurationTextArea.getText();
            
            if(buttonGroup.isSelected(deadlineRadioButton.getModel())) {
                isDeadline = true;
                expression = dObj.getModel().getFactory().createDeadline(dObj.getModel());
                dObj.getModel().startTransaction();
                try {
                    deadlineOrDuration.setDuration(null);
                    deadlineOrDuration.setDeadline((TDeadlineExpr) expression);
                } finally {
                    dObj.getModel().endTransaction();
                }
            } else {
                isDeadline = false;
                expression = dObj.getModel().getFactory().createDuration(dObj.getModel());
                dObj.getModel().startTransaction();
                try {
                    deadlineOrDuration.setDeadline(null);
                    deadlineOrDuration.setDuration((TDurationExpr) expression);
                } finally {
                    dObj.getModel().endTransaction();
                }
            }
            
            if(oldDeadlineOrDurationValue != null) {
                deadlineOrDurationTextArea.setText(oldDeadlineOrDurationValue);
            } else {
                deadlineOrDurationTextArea.setText("");
            }
            
            updateExpression();
            oldDeadlineOrDurationValue = oldText;
        }
    }
    
     private void updateExpression() {
        dObj.getModel().startTransaction();
        try {
            expression.setContent(deadlineOrDurationTextArea.getText());
        } finally {
            dObj.getModel().endTransaction();
        }
    }
     
    class DeadlineOrDurationDocumentListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updateExpression();
        }

        public void removeUpdate(DocumentEvent e) {
            updateExpression();
        }

        public void changedUpdate(DocumentEvent e) {
            updateExpression();
        }
        
       
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JTextArea deadlineOrDurationTextArea;
    private javax.swing.JRadioButton deadlineRadioButton;
    private javax.swing.JRadioButton durationRadioButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
