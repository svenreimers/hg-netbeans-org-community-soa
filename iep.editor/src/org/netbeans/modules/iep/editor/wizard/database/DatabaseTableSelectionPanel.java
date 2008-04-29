/*
 * DatabaseTableSelectionPanel.java
 *
 * Created on April 15, 2008, 6:29 PM
 */

package org.netbeans.modules.iep.editor.wizard.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.netbeans.api.db.explorer.ConnectionManager;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.openide.ErrorManager;

/**
 *
 * @author  radval
 */
public class DatabaseTableSelectionPanel extends javax.swing.JPanel {

    private DatabaseConnection mSelectedConnection;
    
    private TableInfoListModel mAvailableTableListModel = new TableInfoListModel();
    
    private TableInfoListModel mSelectedTableListModel = new TableInfoListModel();
    
    /** Creates new form DatabaseTableSelectionPanel */
    public DatabaseTableSelectionPanel() {
        initComponents();
        init();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mDatasourceConnectionComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mSelectedTablesList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        mAvailableTablesList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        mMoveToSelectedTableListButton = new javax.swing.JButton();
        mMoveToAvailableTableListButton = new javax.swing.JButton();
        mMoveAllToSelectedTableListButton = new javax.swing.JButton();
        mMoveAllToAvailableTableListButton = new javax.swing.JButton();

        jLabel1.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.jLabel1.text")); // NOI18N

        mDatasourceConnectionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        mDatasourceConnectionComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mDatasourceConnectionComboBoxItemStateChanged(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.jLabel3.text")); // NOI18N

        mSelectedTablesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(mSelectedTablesList);

        mAvailableTablesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(mAvailableTablesList);

        mMoveToSelectedTableListButton.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.mMoveToSelectedTableListButton.text")); // NOI18N
        mMoveToSelectedTableListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveToSelectedTableListButtonActionPerformed(evt);
            }
        });

        mMoveToAvailableTableListButton.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.mMoveToAvailableTableListButton.text")); // NOI18N
        mMoveToAvailableTableListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveToAvailableTableListButtonActionPerformed(evt);
            }
        });

        mMoveAllToSelectedTableListButton.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.mMoveAllToSelectedTableListButton.text")); // NOI18N
        mMoveAllToSelectedTableListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveAllToSelectedTableListButtonActionPerformed(evt);
            }
        });

        mMoveAllToAvailableTableListButton.setText(org.openide.util.NbBundle.getMessage(DatabaseTableSelectionPanel.class, "DatabaseTableSelectionPanel.mMoveAllToAvailableTableListButton.text")); // NOI18N
        mMoveAllToAvailableTableListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveAllToAvailableTableListButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(mMoveAllToSelectedTableListButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(mMoveAllToAvailableTableListButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(mMoveToAvailableTableListButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(mMoveToSelectedTableListButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(mMoveToSelectedTableListButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mMoveToAvailableTableListButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mMoveAllToSelectedTableListButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mMoveAllToAvailableTableListButton)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mDatasourceConnectionComboBox, 0, 338, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 87, Short.MAX_VALUE)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 145, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                .add(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(mDatasourceConnectionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel3))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(59, 59, 59)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public List<TableInfo> getSelectedTables() {
        return this.mSelectedTableListModel.getAllTables();
    }
    
    public Connection getSelectedConnection() {
    	return this.mSelectedConnection.getJDBCConnection();
    }
    
    private void init() {
        this.mAvailableTablesList.setModel(this.mAvailableTableListModel);
        this.mSelectedTablesList.setModel(this.mSelectedTableListModel);
        
        MyListSelectionListener listener = new MyListSelectionListener();
        this.mAvailableTablesList.addListSelectionListener(listener);
        this.mSelectedTablesList.addListSelectionListener(listener);
        
        initDataSourceCombo();
        resetButtonStates();
    }
    
private void mDatasourceConnectionComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mDatasourceConnectionComboBoxItemStateChanged
    updateSourceTables();
}//GEN-LAST:event_mDatasourceConnectionComboBoxItemStateChanged

private void mMoveToSelectedTableListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveToSelectedTableListButtonActionPerformed
    Object[] selected = this.mAvailableTablesList.getSelectedValues();
    if(selected != null) {
        for(int i = 0; i < selected.length; i++) {
            TableInfo table = (TableInfo) selected[i];
            this.mSelectedTableListModel.addTable(table);
            this.mAvailableTableListModel.removeTable(table);
        }
    }
}//GEN-LAST:event_mMoveToSelectedTableListButtonActionPerformed

private void mMoveToAvailableTableListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveToAvailableTableListButtonActionPerformed
    Object[] selected = this.mSelectedTablesList.getSelectedValues();
    if(selected != null) {
        for(int i = 0; i < selected.length; i++) {
            TableInfo table = (TableInfo) selected[i];
            this.mAvailableTableListModel.addTable(table);
            this.mSelectedTableListModel.removeTable(table);
        }
    }
}//GEN-LAST:event_mMoveToAvailableTableListButtonActionPerformed

private void mMoveAllToSelectedTableListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveAllToSelectedTableListButtonActionPerformed
    List<TableInfo> allTables = this.mAvailableTableListModel.getAllTables();
    this.mSelectedTableListModel.setTables(allTables);
    this.mAvailableTableListModel.setTables(new ArrayList<TableInfo>());
}//GEN-LAST:event_mMoveAllToSelectedTableListButtonActionPerformed

private void mMoveAllToAvailableTableListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveAllToAvailableTableListButtonActionPerformed
    List<TableInfo> allTables = this.mSelectedTableListModel.getAllTables();
    Iterator<TableInfo> it = allTables.iterator();
    while(it.hasNext()) {
        TableInfo table = it.next();
        this.mAvailableTableListModel.addTable(table);
    }
    
}//GEN-LAST:event_mMoveAllToAvailableTableListButtonActionPerformed

    
    
    
    private void resetButtonStates() {
        if(this.mAvailableTablesList.getSelectedIndex() != -1) {
            this.mMoveToSelectedTableListButton.setEnabled(true);
        } else {
            this.mMoveToSelectedTableListButton.setEnabled(false);
        }
        
        if(this.mSelectedTablesList.getSelectedIndex() != -1) {
            this.mMoveToAvailableTableListButton.setEnabled(true);
        } else {
            this.mMoveToAvailableTableListButton.setEnabled(false);
        }
        
        
    }
            
    private void initDataSourceCombo() {
        DefaultComboBoxModel providers = new DefaultComboBoxModel();
        //int longinx = 0, longest = 0;
        final DatabaseConnection[] conns = ConnectionManager.getDefault().getConnections();
        
        providers.addElement("Select Data Source");
        
        if (conns.length > 0) {
            for (int i = 0; i < conns.length; i++) {
                providers.addElement(new ConnectionWrapper(conns[i]));
            }
        } else {
            providers.addElement("<None>");
        }
       
        this.mDatasourceConnectionComboBox.setModel(providers);
        this.mDatasourceConnectionComboBox.setSelectedIndex(0);

    }

    /**
     * Connect to the datasource selected in the combo
     */
    private void updateSourceTables() {
        final Object item = this.mDatasourceConnectionComboBox.getSelectedItem();
        if (item instanceof ConnectionWrapper) {
            final ConnectionWrapper cw = (ConnectionWrapper) item;
            this.mSelectedConnection = cw.getDatabaseConnection();
            ConnectionManager.getDefault().showConnectionDialog(this.mSelectedConnection);
//            this.persistModel();
            reInitializeAvailableTableList();
        }/*else{
             ConnectionManager.getDefault().showAddConnectionDialog(null);
        }*/
        
    }

    private void reInitializeAvailableTableList() {
        try {
        	if(this.mSelectedConnection != null) {
	            Connection connection = this.mSelectedConnection.getJDBCConnection();
	            if(connection != null) {
	            	List<TableInfo> tables = DatabaseMetaDataHelper.getTablesAndViews("", "", "", false, connection);
	            	this.mAvailableTableListModel.setTables(tables);
	            }
        	}
        } catch(Exception ex) {
            ErrorManager.getDefault().notify(ex);
        }
    }
    
    
    
//    public void persistModel() {
//        //final DBMetaData meta = new DBMetaData();
//    	final Connection connection = this.mSelectedConnection.getJDBCConnection();
//        
//        DatabaseMetaData dbMetaData = connection.getMetaData();
//        dbMetaData.get
//        
//    	List recycleBinTables = null;
//        if(connection != null){
//            try {
//                //meta.connectDB(this.selectedConnection.getJDBCConnection());
//                 def = DatabaseObjectFactory.createDBConnectionDefinition(
//                        this.selectedConnection.getDisplayName(), this.selectedConnection.getDriverClass(),
//                        this.selectedConnection.getDatabaseURL(), this.selectedConnection.getUser(),
//                        this.selectedConnection.getPassword(), "Descriptive info here", DBMetaData.getDBType(connection));
//                
//                this.dbtype = DBMetaData.getDBType(this.selectedConnection.getJDBCConnection());
//                this.dbmodel = new DatabaseModelImpl(this.selectedConnection.getDisplayName(), def);
//    
//    		    final String[][] tableList = DBMetaData.getTablesAndViews("", this.selectedConnection.getSchema(), "", true,connection);
//    		    if ("ORACLE".equalsIgnoreCase(this.dbtype) && DBMetaData.getDatabaseMajorVersion(connection) >= 10) { // NOI18N
//                    recycleBinTables = DBMetaData.getOracleRecycleBinTables(connection);
//                } else {
//                    recycleBinTables = Collections.EMPTY_LIST;
//                }
//
//    			String[] currTable = null;
//    			List tableNamesList = new ArrayList();
//                if (tableList != null) {
//                    for (int i = 0; i < tableList.length; i++) {
//                        currTable = tableList[i];
//                        	if (!recycleBinTables.contains(currTable[DBMetaData.NAME])) {
//                        		tableNamesList.add(currTable[DBMetaData.NAME]);
//                            }
//                        }
//                       
//                    }
//    			this.initListModel(tableNamesList);           
//                
//            } catch (final Exception ex) {
//                ex.printStackTrace();
//                ErrorManager.getDefault().log(ErrorManager.ERROR, ex.getMessage());
//                ErrorManager.getDefault().notify(ErrorManager.ERROR, ex);
//            }
//      }
//    }
//    
    private static class ConnectionWrapper {
        private DatabaseConnection conn;

        ConnectionWrapper(final DatabaseConnection conn) {
            this.conn = conn;
        }

        public DatabaseConnection getDatabaseConnection() {
            return this.conn;
        }

        public String toString() {
            return this.conn.getDisplayName();
        }
    }
    
    class TableInfoListModel extends AbstractListModel {
        
        private List<TableInfo> mTables;
        public TableInfoListModel() {
            mTables = new ArrayList<TableInfo>();
        }
        
        public int getSize() {
            return this.mTables.size();
        }

        public Object getElementAt(int index) {
            return this.mTables.get(index);
        }
        
        public void addTable(TableInfo table) {
            this.mTables.add(table);
            Collections.sort(this.mTables);
            //this.fireIntervalAdded(this, this.mTables.indexOf(table), this.mTables.indexOf(table));
            this.fireContentsChanged(this, 0, this.mTables.size()-1);
        }
        
        public void removeTable(TableInfo  table) {
            if(this.mTables.contains(table)) {
                int index = this.mTables.indexOf(table);
                this.mTables.remove(table);
                this.fireIntervalRemoved(table, index, index);
            }
        }
        
        public void setTables(List<TableInfo> tables) {
            this.mTables = tables;
            if(tables != null) {
                Collections.sort(this.mTables);
                this.fireContentsChanged(this, 0, tables.size()-1);
            } else {
                this.fireContentsChanged(this, 0, 0);
            }
        }
        
        public List<TableInfo> getAllTables() {
            return this.mTables;
        }
        
    }
    
    class MyListSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            resetButtonStates();
        }

        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList mAvailableTablesList;
    private javax.swing.JComboBox mDatasourceConnectionComboBox;
    private javax.swing.JButton mMoveAllToAvailableTableListButton;
    private javax.swing.JButton mMoveAllToSelectedTableListButton;
    private javax.swing.JButton mMoveToAvailableTableListButton;
    private javax.swing.JButton mMoveToSelectedTableListButton;
    private javax.swing.JList mSelectedTablesList;
    // End of variables declaration//GEN-END:variables

}