/*
 * IEPAttributeConfigurationPanel.java
 *
 * Created on January 8, 2008, 4:50 PM
 */

package org.netbeans.modules.iep.editor.wizard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import org.netbeans.api.project.Project;
import org.netbeans.modules.iep.editor.designer.JTextFieldFilter;
import org.netbeans.modules.iep.editor.ps.SelectPanelTableCellRenderer;
import org.netbeans.modules.iep.editor.share.SharedConstants;

/**
 *
 * @author  radval
 */
public class IEPAttributeConfigurationPanel extends javax.swing.JPanel {
    
    private IEPAttributeTableModel mTableModel;
    
    private static JTextField mTextFieldNumeric;
    private static JTextField mTextFieldANU;
    private static JTextField mTextFieldAny;
    
    private static DefaultCellEditor mCellEditorNumeric;
    private static DefaultCellEditor mCellEditorANU;
    private static DefaultCellEditor mCellEditorAny;
    private static DefaultCellEditor mCellEditorSqlType;
    private SelectPanelTableCellRenderer  spTCRenderer;
    
    private static JComboBox mComboBoxSqlType;
    
    private Project mProject;
    
    /** Creates new form IEPAttributeConfigurationPanel */
    public IEPAttributeConfigurationPanel() {
        initComponents();
        initGUI();
    }
    
    public IEPAttributeConfigurationPanel(Project project) {
        this();
        this.mProject = project;
    }
    
    public void addDefaultIEPAttributes(List<XSDToIEPAttributeNameVisitor.AttributeNameToType> nameToTypeList) {
        Iterator<XSDToIEPAttributeNameVisitor.AttributeNameToType> it = nameToTypeList.iterator();
            
        while(it.hasNext()) {
            XSDToIEPAttributeNameVisitor.AttributeNameToType attrNameToType = it.next();
            PlaceholderSchemaAttribute attr = new PlaceholderSchemaAttribute();
            String attrName = attrNameToType.getAttributeName();
            String attrType = attrNameToType.getAttributeType();
            attr.setAttributeName(attrName);
            attr.setAttributeType(attrType);
            if(SharedConstants.SQL_TYPE_VARCHAR.equals(attrType)) {
                attr.setAttributeSize("50"); //by default use 50 for size. size is required for VARCHAR
            }
            
            mTableModel.addRow(attr);
        }
    }
    
    public void clearAttributes() {
        mTableModel.clear();
    }
    
    public List<PlaceholderSchemaAttribute> getAttributeList() {
        return mTableModel.getAttributeList();
    }
            
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mAttributeTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(IEPAttributeConfigurationPanel.class, "IEPAttributeConfigurationPanel.border.title"))); // NOI18N

        mAttributeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(mAttributeTable);

        addButton.setText(org.openide.util.NbBundle.getMessage(IEPAttributeConfigurationPanel.class, "IEPAttributeConfigurationPanel.addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText(org.openide.util.NbBundle.getMessage(IEPAttributeConfigurationPanel.class, "IEPAttributeConfigurationPanel.deleteButton.text")); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        moveUpButton.setText(org.openide.util.NbBundle.getMessage(IEPAttributeConfigurationPanel.class, "IEPAttributeConfigurationPanel.moveUpButton.text")); // NOI18N
        moveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButtonActionPerformed(evt);
            }
        });

        moveDownButton.setText(org.openide.util.NbBundle.getMessage(IEPAttributeConfigurationPanel.class, "IEPAttributeConfigurationPanel.moveDownButton.text")); // NOI18N
        moveDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .add(15, 15, 15))
                    .add(layout.createSequentialGroup()
                        .add(addButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(moveUpButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(moveDownButton)
                        .addContainerGap(94, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 232, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addButton)
                    .add(deleteButton)
                    .add(moveUpButton)
                    .add(moveDownButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        //mTableModel.addNewRow();
        
        SchemaArtifactSelectionDialog.showDialog(this.mProject);
}//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        List<PlaceholderSchemaAttribute> attrList = new ArrayList<PlaceholderSchemaAttribute>();
        
        int[] rows = mAttributeTable.getSelectedRows();
        for(int i = 0; i < rows.length; i++) {
            int rowIndex = rows[i];
            PlaceholderSchemaAttribute attr = mTableModel.getRowData(rowIndex);
            attrList.add(attr);
        }

        Iterator<PlaceholderSchemaAttribute> it = attrList.iterator();
        while(it.hasNext()) {
            PlaceholderSchemaAttribute attr = it.next();
            mTableModel.removeRow(attr);
        }
        
}//GEN-LAST:event_deleteButtonActionPerformed

    private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
        int[] rows = mAttributeTable.getSelectedRows();
        
        PlaceholderSchemaAttribute prevSibling = mTableModel.getRowData(rows[0]-1);
        mTableModel.removeRow(rows[0]-1);
        mTableModel.insertRow(rows[rows.length -1], prevSibling);
        
//        for(int i = 0; i < rows.length; i++) {
//            int row = rows[i];
//            PlaceholderSchemaAttribute attr = mTableModel.getRowData(row);
//            mTableModel.removeRow(row);
//            mTableModel.insertRow(row-1, attr);
////            mTableModel.
//            
//        }
}//GEN-LAST:event_moveUpButtonActionPerformed

    private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
        int[] rows = mAttributeTable.getSelectedRows();
        
        PlaceholderSchemaAttribute afterSibling = mTableModel.getRowData(rows[rows.length -1] +1);
        mTableModel.removeRow(rows[rows.length -1] +1);
        mTableModel.insertRow(rows[0], afterSibling);
        
        
//        int[] rows = mAttributeTable.getSelectedRows();
//        for(int i = 0; i < rows.length; i++) {
//            int row = rows[i];
//            PlaceholderSchemaAttribute attr = mTableModel.getRowData(row);
//            mTableModel.removeRow(row);
//            mTableModel.insertRow(row + 1, attr);
////            mTableModel.
//            
//        }
}//GEN-LAST:event_moveDownButtonActionPerformed
    
    
    private void initGUI() {
        mTableModel = new IEPAttributeTableModel();
        this.mAttributeTable.setModel(mTableModel);
        
        this.mAttributeTable.getSelectionModel().addListSelectionListener(new AttributeTableSelectionListener());
        this.mAttributeTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                
        this.deleteButton.setEnabled(false);
        this.moveUpButton.setEnabled(false);
        this.moveDownButton.setEnabled(false);
        
        Vector mSqlType = new Vector();
        mSqlType.add("");
        for(int i = 0; i < SharedConstants.SQL_TYPE_NAMES.length; i++)
            mSqlType.add(SharedConstants.SQL_TYPE_NAMES[i]);
        
        mTextFieldANU = new JTextField();
        mTextFieldANU.setDocument(JTextFieldFilter.newAlphaNumericUnderscore());
        mCellEditorANU = new DefaultCellEditor(mTextFieldANU);
        
        mTextFieldNumeric = new JTextField();
        mTextFieldNumeric.setDocument(JTextFieldFilter.newNumeric());
        mCellEditorNumeric = new DefaultCellEditor(mTextFieldNumeric);
        
        mTextFieldAny = new JTextField();
        mCellEditorAny = new DefaultCellEditor(mTextFieldAny);
        
        mComboBoxSqlType = new JComboBox(mSqlType);
        mCellEditorSqlType = new DefaultCellEditor(mComboBoxSqlType);
        
        spTCRenderer = new SelectPanelTableCellRenderer();
        
        TableColumnModel tcm = this.mAttributeTable.getColumnModel();
        
        tcm.getColumn(0).setCellEditor(mCellEditorANU);
        tcm.getColumn(1).setCellEditor(mCellEditorSqlType);
        //mComboBoxSqlType.addActionListener(new SQLTypeComboBoxActionListener());
        tcm.getColumn( 2).setCellEditor(mCellEditorNumeric);
        tcm.getColumn(3).setCellEditor(mCellEditorNumeric);
        tcm.getColumn(4).setCellEditor(mCellEditorAny);
        
        // setting up renderer
        tcm.getColumn(0).setCellRenderer(spTCRenderer);
        tcm.getColumn(1).setCellRenderer(spTCRenderer);
        tcm.getColumn(2).setCellRenderer(spTCRenderer);
        tcm.getColumn(3).setCellRenderer(spTCRenderer);
        tcm.getColumn(4).setCellRenderer(spTCRenderer);
        
    }
    
    class AttributeTableSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            int row = mAttributeTable.getSelectedRow();
            if(row != -1) {
                deleteButton.setEnabled(true);
            } else {
                deleteButton.setEnabled(false);
            }
            
            int rowCount = mAttributeTable.getRowCount();
            
            int[] rows = mAttributeTable.getSelectedRows();
            
            if(rows.length != 0) {
	            //if the first row is first row then moveup should be disabled
	            if(rows[0] == 0) {
	                moveUpButton.setEnabled(false);
	            } else {
	                moveUpButton.setEnabled(true);
	            }
	            
	            //if the last row is last row then move down should be disabled
	            if(rows[rows.length -1] == mAttributeTable.getRowCount() -1) {
	                moveDownButton.setEnabled(false);
	            } else {
	                moveDownButton.setEnabled(true);
	            }
            }
            
//            if(rowCount > 1) {
//                moveUpButton.setEnabled(true);
//                moveDownButton.setEnabled(true);
//            } else {
//                moveUpButton.setEnabled(false);
//                moveDownButton.setEnabled(false);
//            }
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mAttributeTable;
    private javax.swing.JButton moveDownButton;
    private javax.swing.JButton moveUpButton;
    // End of variables declaration//GEN-END:variables
    
}
