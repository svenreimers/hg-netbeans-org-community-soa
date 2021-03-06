/*
 * DocumentationComponent.java
 *
 * Created on July 25, 2007, 5:44 PM
 */

package org.netbeans.modules.iep.editor.designer;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JScrollBar;

import org.netbeans.modules.iep.model.Documentation;
import org.netbeans.modules.iep.model.OperatorComponent;

import com.nwoods.jgo.JGoControl;
import com.nwoods.jgo.JGoView;

/**
 *
 * @author  radval
 */
public class DocumentationComponent extends javax.swing.JPanel {

    private JGoView mView;
    
    private JGoControl mControl;
    
    private EntityNode mNode;
    
    private boolean adjustScrollBarAtStart = false;
    
    /** Creates new form DocumentationComponent */
    public DocumentationComponent() {
        initComponents();
        initGUI();
    }
    
    public DocumentationComponent(JGoView view, JGoControl control, EntityNode node) {
        this();
        this.mView = view;
        this.mControl = control;
        this.mNode = node;
        Documentation doc = mNode.getModelComponent().getDocumentation();
        if(doc != null && doc.getTextContent() != null) {
            setDocumentation(doc.getTextContent());
        }
        
//        documentationTextArea.setWrapStyleWord(true);
//        documentationTextArea.setLineWrap(true);
        documentationTextArea.setCaretPosition(0);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        documentationTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        documentationTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        documentationTitleLabel.setText("jLabel1");

        documentationTextArea.setColumns(20);
        documentationTextArea.setRows(5);
        jScrollPane1.setViewportView(documentationTextArea);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error_badge.png"))); // NOI18N
        closeButton.setText(org.openide.util.NbBundle.getMessage(DocumentationComponent.class, "DocumentationComponent.closeButton.text")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(documentationTitleLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(closeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(closeButton)
                    .add(documentationTitleLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1)
                .add(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    this.mView.removeObject(this.mControl);
    String docStr = this.documentationTextArea.getText();
    OperatorComponent opComp = this.mNode.getModelComponent();
    Documentation doc = this.mNode.getModelComponent().getDocumentation();
    
    if(docStr != null) {
        
        if(doc != null) {
            opComp.getModel().startTransaction();
            doc.setTextContent(docStr);
            opComp.getModel().endTransaction();
        } else {
            doc = opComp.getModel().getFactory().createDocumentation(opComp.getModel());
            doc.setTextContent(docStr);
            opComp.getModel().startTransaction();
            opComp.setDocumentation(doc);
            opComp.getModel().endTransaction();
        }
    } else {
        opComp.setDocumentation(null);
    }
    
}//GEN-LAST:event_closeButtonActionPerformed

    private void initGUI() {
//        try {
//            ImageIcon icon = new ImageIcon( DocumentationComponent.class.getResource("/images/error_badge.png"));
//            closeButton.setIcon(icon);
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
        documentationTitleLabel.setText("<html><body><b>Documentation</b><body></html>");
        
    }
    
    public void setDocumentation(String text) {
        this.documentationTextArea.setText(text);
    }
    
    public String getDocumentation() {
        return this.documentationTextArea.getText();
    }
    
    public void storeDocumentation() {
        String docStr = getDocumentation();
        OperatorComponent opComp = this.mNode.getModelComponent();
        if(opComp == null || opComp.getModel() == null) {
            return;
        }
        
        if(docStr != null) {
            Documentation doc = opComp.getDocumentation();
            if(doc == null) {
                doc  = opComp.getModel().getFactory().createDocumentation(opComp.getModel());
                doc.setTextContent(docStr);
                opComp.getModel().startTransaction();
                opComp.setDocumentation(doc);
                opComp.getModel().endTransaction();
            } else {
                opComp.getModel().startTransaction();
                doc.setTextContent(docStr);
                opComp.getModel().endTransaction();
            }
        } else {
            opComp.getModel().startTransaction();
            opComp.setDocumentation(null);
            opComp.getModel().endTransaction();
        }
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea documentationTextArea;
    private javax.swing.JLabel documentationTitleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    
    
}
