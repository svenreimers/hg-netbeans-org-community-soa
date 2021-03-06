/*
 * DatabaseTableColumnSelectionPanel.java
 *
 * Created on April 15, 2008, 6:38 PM
 */

package org.netbeans.modules.iep.editor.wizard.database;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JTree;


/**
 *
 * @author  radval
 */
public class PollingTableDatabaseTableColumnSelectionPanel extends javax.swing.JPanel {

    private DatabaseTableColumnSelectionPanel mTableColumnSelectionPanel;
    
    /** Creates new form DatabaseTableColumnSelectionPanel */
    public PollingTableDatabaseTableColumnSelectionPanel() {
        initComponents();
        mTableColumnSelectionPanel = new DatabaseTableColumnSelectionPanel();
        tableColumnSelectionPanelContainer.setLayout(new BorderLayout());
        tableColumnSelectionPanelContainer.add(mTableColumnSelectionPanel, BorderLayout.CENTER);
        
        String whereClauseLabel = org.openide.util.NbBundle.getMessage(PollingTableDatabaseTableColumnSelectionPanel.class, "DatabaseTableColumnSelectionPanel.jLabel2.text");
        org.openide.awt.Mnemonics.setLocalizedText(this.jLabel2, whereClauseLabel);
        
    }
    
    public JTree getTableColumnsTree() {
        return this.mTableColumnSelectionPanel.getTableColumnsTree();
    }
    
    public DatabaseTableColumnSelectionPanel getDatabaseTableColumnSelectionPanel() {
        return this.mTableColumnSelectionPanel;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        whereClausePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        tableColumnSelectionPanelContainer = new javax.swing.JPanel();

        jLabel2.setLabelFor(jTextArea1);
        jLabel2.setText(org.openide.util.NbBundle.getMessage(PollingTableDatabaseTableColumnSelectionPanel.class, "DatabaseTableColumnSelectionPanel.jLabel2.text")); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PollingTableDatabaseTableColumnSelectionPanel.class, "PollingTableDatabaseTableColumnSelectionPanel.jTextArea1.AccessibleContext.accessibleName")); // NOI18N
        jTextArea1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PollingTableDatabaseTableColumnSelectionPanel.class, "PollingTableDatabaseTableColumnSelectionPanel.jTextArea1.AccessibleContext.accessibleDescription")); // NOI18N

        org.jdesktop.layout.GroupLayout whereClausePanelLayout = new org.jdesktop.layout.GroupLayout(whereClausePanel);
        whereClausePanel.setLayout(whereClausePanelLayout);
        whereClausePanelLayout.setHorizontalGroup(
            whereClausePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .add(whereClausePanelLayout.createSequentialGroup()
                .add(jLabel2)
                .addContainerGap())
        );
        whereClausePanelLayout.setVerticalGroup(
            whereClausePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, whereClausePanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout tableColumnSelectionPanelContainerLayout = new org.jdesktop.layout.GroupLayout(tableColumnSelectionPanelContainer);
        tableColumnSelectionPanelContainer.setLayout(tableColumnSelectionPanelContainerLayout);
        tableColumnSelectionPanelContainerLayout.setHorizontalGroup(
            tableColumnSelectionPanelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 390, Short.MAX_VALUE)
        );
        tableColumnSelectionPanelContainerLayout.setVerticalGroup(
            tableColumnSelectionPanelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 230, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tableColumnSelectionPanelContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(whereClausePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(tableColumnSelectionPanelContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(whereClausePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public void setSelectedTables(List<TableInfo> tables) {
    	if(tables == null) {
    		return;
    	}
    	
        mTableColumnSelectionPanel.setSelectedTables(tables);
    	
    }
    
    public void setJoinCondition(String joinCondition) {
    	this.jTextArea1.setText(joinCondition);
    }
    
    public String getJoinCondition() {
        return this.jTextArea1.getText();
    }
    
    public List<ColumnInfo> getSelectedColumns() {
    	return this.mTableColumnSelectionPanel.getSelectedColumns();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel tableColumnSelectionPanelContainer;
    private javax.swing.JPanel whereClausePanel;
    // End of variables declaration//GEN-END:variables

}
