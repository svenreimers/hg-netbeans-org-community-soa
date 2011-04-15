/*
 * JDBCWizardOptionsPanel.java
 *
 * Created on August 8, 2008, 2:20 PM
 */
package org.netbeans.modules.jdbcwizard.wizards;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.jdbcwizard.builder.dbmodel.DBConnectionDefinition;
import org.openide.WizardDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;

import org.netbeans.api.db.explorer.DatabaseConnection;
import org.netbeans.api.db.explorer.ConnectionManager;
import org.netbeans.api.db.explorer.JDBCDriver;
import org.netbeans.modules.jdbcwizard.builder.DBMetaData;
import org.netbeans.modules.jdbcwizard.builder.dbmodel.impl.DatabaseModelImpl;
import org.netbeans.modules.jdbcwizard.builder.dbmodel.impl.DatabaseObjectFactory;
import org.openide.ErrorManager;
import org.openide.NotifyDescriptor;

/**
 *
 * @author  admin
 */
public class JDBCWizardOptionsPanel extends javax.swing.JPanel implements WizardDescriptor.Panel, ActionListener {

    private DBConnectionDefinition def;
    private String dbtype;

    /** Creates new form JDBCWizardOptionsPanel */
    public JDBCWizardOptionsPanel() {
        initComponents();
        initDataSourceCombo();
    }

    /** Creates new form JDBCWizardOptionsPanel */
    public JDBCWizardOptionsPanel(final String title) {
        initComponents();
        initDataSourceCombo();
        if (title != null && title.trim().length() != 0) {
            this.setName(title);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dataSourcePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        datasourceComboBox = new javax.swing.JComboBox();
        Procedures = new javax.swing.JRadioButton();
        PreparedStatements = new javax.swing.JRadioButton();
        Tables = new javax.swing.JRadioButton();
        sqlFile = new javax.swing.JRadioButton();

        jLabel1.setLabelFor(datasourceComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        datasourceComboBox.setFont(datasourceComboBox.getFont());
        datasourceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        datasourceComboBox.setToolTipText(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.datasourceComboBox.toolTipText")); // NOI18N
        datasourceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datasourceComboBoxActionPerformed(evt);
            }
        });

        Procedures.setFont(Procedures.getFont());
        org.openide.awt.Mnemonics.setLocalizedText(Procedures, org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Procedures.text")); // NOI18N
        Procedures.setToolTipText(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Procedures.toolTipText")); // NOI18N
        Procedures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProceduresActionPerformed(evt);
            }
        });

        PreparedStatements.setFont(PreparedStatements.getFont());
        org.openide.awt.Mnemonics.setLocalizedText(PreparedStatements, org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.PreparedStatements.text")); // NOI18N
        PreparedStatements.setToolTipText(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.PreparedStatements.toolTipText")); // NOI18N
        PreparedStatements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreparedStatementsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(Tables, org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Tables.text")); // NOI18N
        Tables.setToolTipText(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Tables.toolTipText")); // NOI18N
        Tables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TablesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(sqlFile, org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.sqlFile.text")); // NOI18N
        sqlFile.setToolTipText(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.sqlFile.toolTipText")); // NOI18N
        sqlFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqlFileActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataSourcePanelLayout = new org.jdesktop.layout.GroupLayout(dataSourcePanel);
        dataSourcePanel.setLayout(dataSourcePanelLayout);
        dataSourcePanelLayout.setHorizontalGroup(
            dataSourcePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataSourcePanelLayout.createSequentialGroup()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dataSourcePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sqlFile)
                    .add(Procedures)
                    .add(PreparedStatements)
                    .add(Tables)
                    .add(datasourceComboBox, 0, 233, Short.MAX_VALUE))
                .addContainerGap())
        );

        dataSourcePanelLayout.linkSize(new java.awt.Component[] {PreparedStatements, Procedures, Tables}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        dataSourcePanelLayout.setVerticalGroup(
            dataSourcePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataSourcePanelLayout.createSequentialGroup()
                .add(28, 28, 28)
                .add(dataSourcePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(datasourceComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                .add(Tables)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PreparedStatements)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Procedures)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sqlFile)
                .addContainerGap())
        );

        dataSourcePanelLayout.linkSize(new java.awt.Component[] {PreparedStatements, Procedures, Tables}, org.jdesktop.layout.GroupLayout.VERTICAL);

        dataSourcePanelLayout.linkSize(new java.awt.Component[] {datasourceComboBox, jLabel1}, org.jdesktop.layout.GroupLayout.VERTICAL);

        datasourceComboBox.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.datasourceComboBox.AccessibleContext.accessibleName")); // NOI18N
        datasourceComboBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.datasourceComboBox.AccessibleContext.accessibleDescription")); // NOI18N
        Procedures.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Procedures.AccessibleContext.accessibleName")); // NOI18N
        Procedures.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Procedures.AccessibleContext.accessibleDescription")); // NOI18N
        PreparedStatements.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.PreparedStatements.AccessibleContext.accessibleName")); // NOI18N
        PreparedStatements.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.PreparedStatements.AccessibleContext.accessibleDescription")); // NOI18N
        Tables.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Tables.AccessibleContext.accessibleName")); // NOI18N
        Tables.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.Tables.AccessibleContext.accessibleDescription")); // NOI18N
        sqlFile.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.sqlFile.AccessibleContext.accessibleName")); // NOI18N
        sqlFile.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JDBCWizardOptionsPanel.class, "JDBCWizardOptionsPanel.sqlFile.AccessibleContext.accessibleDescription")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(42, 42, 42)
                .add(dataSourcePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(23, 23, 23)
                .add(dataSourcePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void TablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TablesActionPerformed
// TODO add your handling code here:
    String str = evt.toString();
    tablesSelected = true;
    prepStmtsSelected = false;
    proceduresSelected = false;
    sqlFileSelected = false;
    enableRadioButtons();
    this.fireChangeEvent();

}//GEN-LAST:event_TablesActionPerformed

private void datasourceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datasourceComboBoxActionPerformed
// TODO add your handling code here:
    this.updateSourceSchema();
}//GEN-LAST:event_datasourceComboBoxActionPerformed

private void PreparedStatementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreparedStatementsActionPerformed
// TODO add your handling code here:
    String str = evt.toString();
    tablesSelected = false;
    prepStmtsSelected = true;
    proceduresSelected = false;
    sqlFileSelected = false;
    enableRadioButtons();
    this.fireChangeEvent();

}//GEN-LAST:event_PreparedStatementsActionPerformed

private void ProceduresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProceduresActionPerformed
// TODO add your handling code here:
    String str = evt.toString();
    tablesSelected = false;
    prepStmtsSelected = false;
    proceduresSelected = true;
    sqlFileSelected = false;
    enableRadioButtons();
    this.fireChangeEvent();

}//GEN-LAST:event_ProceduresActionPerformed

private void sqlFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqlFileActionPerformed
// TODO add your handling code here:
    String str = evt.toString();
    tablesSelected = false;
    prepStmtsSelected = false;
    proceduresSelected = false;
    sqlFileSelected = true;
    enableRadioButtons();
    this.fireChangeEvent();
}//GEN-LAST:event_sqlFileActionPerformed
 
    private void enableRadioButtons() {
        Tables.setSelected(tablesSelected);
        PreparedStatements.setSelected(prepStmtsSelected);
        Procedures.setSelected(proceduresSelected);
        sqlFile.setSelected(sqlFileSelected);
        //PP temporary fix for netbeans bug with connection not established on the first try.
        this.updateSourceSchema();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton PreparedStatements;
    private javax.swing.JRadioButton Procedures;
    private javax.swing.JRadioButton Tables;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel dataSourcePanel;
    private javax.swing.JComboBox datasourceComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton sqlFile;
    // End of variables declaration//GEN-END:variables
    private final List /* <ChangeListener> */ listeners = new ArrayList();
    DefaultComboBoxModel providers;
    private DatabaseConnection selectedConnection;
    private static final String NEW_DATA_SOURCE = "New Datasource...";
    boolean tablesSelected = false;
    boolean prepStmtsSelected = false;
    boolean proceduresSelected = false;
    boolean sqlFileSelected = false;

    public void initDataSourceCombo() {
        providers = new DefaultComboBoxModel();
        //int longinx = 0, longest = 0;
        final DatabaseConnection[] conns = ConnectionManager.getDefault().getConnections();
        providers.addElement("<Select an Item from the list>");
        if (conns.length == 1) {
            providers.addElement("");
        }
        if (conns.length > 0) {
            for (int i = 0; i < conns.length; i++) {
                providers.addElement(new ConnectionWrapper(conns[i]));
            /*   if(longest < (new ConnectionWrapper(conns[i])).getDatabaseConnection().getDisplayName().length()){
            longinx = i;
            longest = (new ConnectionWrapper(conns[i])).getDatabaseConnection().getDisplayName().length();
            }*/
            }
        } else {
            providers.addElement("<Configure connections in the services tab>");
        }

        this.datasourceComboBox.setModel(providers);
        this.datasourceComboBox.setSelectedIndex(0);
    //this.datasourceComboBox.setPrototypeDisplayValue((new ConnectionWrapper(conns[longinx])).getDatabaseConnection().getDisplayName());

    }

    @Override
    public boolean isValid() {
        if ((datasourceComboBox.getSelectedIndex() > 0) && 
                (tablesSelected || prepStmtsSelected || proceduresSelected || sqlFileSelected)) {
            
            return true;
        }
        return false;
    }

    private void updateSourceSchema() {
        Set oldConnections = new HashSet(Arrays.asList(ConnectionManager.getDefault().getConnections()));

        final Object item = this.datasourceComboBox.getSelectedItem();
        if (item instanceof ConnectionWrapper) {
            final ConnectionWrapper cw = (ConnectionWrapper) item;
            this.selectedConnection = cw.getDatabaseConnection();

            ConnectionManager.getDefault().showConnectionDialog(this.selectedConnection);
            Connection jDBCConnection = this.selectedConnection.getJDBCConnection();
            providers.removeElement("");
            this.persistModel();
        }/*else{
        ConnectionManager.getDefault().showAddConnectionDialog(null);
        }*/
        // try to find the new connection
        DatabaseConnection[] newConnections = ConnectionManager.getDefault().getConnections();
        if (newConnections.length == oldConnections.size()) {
            // no new connection, so...
            return;
        }

        providers.removeElement(new String(NEW_DATA_SOURCE));
        for (int i = 0; i < newConnections.length; i++) {
            if (!oldConnections.contains(newConnections[i])) {
                providers.addElement(new ConnectionWrapper(newConnections[i]));
                break;
            }
        }
    }

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

    public Component getComponent() {
        return this;
    }

    public HelpCtx getHelp() {
        return null;
    }

    public void readSettings(Object settings) {
    }

    public void storeSettings(Object settings) {
        WizardDescriptor wd = null;
        if (settings instanceof JDBCWizardContext) {
            final JDBCWizardContext wizardContext = (JDBCWizardContext) settings;
            wd = (WizardDescriptor) wizardContext.getProperty(JDBCWizardContext.WIZARD_DESCRIPTOR);

        } else if (settings instanceof WizardDescriptor) {
            wd = (WizardDescriptor) settings;
        }

        final Object selectedOption = wd.getValue();
        if (NotifyDescriptor.CANCEL_OPTION == selectedOption || NotifyDescriptor.CLOSED_OPTION == selectedOption) {
            return;
        }
        if (wd != null) {
            wd.putProperty(JDBCWizardContext.DBTYPE, this.dbtype);
            wd.putProperty(JDBCWizardContext.CONNECTION_INFO, def);
            if (prepStmtsSelected == true) {
                wd.putProperty(WizardConstants.PREPSTMTS_SELECTED, new Boolean(true));
                wd.putProperty(WizardConstants.TABLES_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.PROCEDURES_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.SQLFILE_SELECTED, new Boolean(false));
            } else if (tablesSelected == true) {
                wd.putProperty(WizardConstants.TABLES_SELECTED, new Boolean(true));
                wd.putProperty(WizardConstants.PROCEDURES_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.PREPSTMTS_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.SQLFILE_SELECTED, new Boolean(false));
            } else if (proceduresSelected == true) {
                wd.putProperty(WizardConstants.PROCEDURES_SELECTED, new Boolean(true));
                wd.putProperty(WizardConstants.PREPSTMTS_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.TABLES_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.SQLFILE_SELECTED, new Boolean(false));
            }else if (sqlFileSelected == true) {
                wd.putProperty(WizardConstants.PROCEDURES_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.PREPSTMTS_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.TABLES_SELECTED, new Boolean(false));
                wd.putProperty(WizardConstants.SQLFILE_SELECTED, new Boolean(true));
            }
        }

    }

    public void addChangeListener(ChangeListener l) {
        this.listeners.add(l);
    }

    public void removeChangeListener(ChangeListener l) {
        this.listeners.remove(l);

    }

    public void fireChangeEvent() {
        Iterator it;

        synchronized (this.listeners) {
            it = new HashSet(this.listeners).iterator();
        }

        final ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            ((ChangeListener) it.next()).stateChanged(ev);
        }
    }

    public void actionPerformed(ActionEvent e) {
        this.fireChangeEvent();

    }

    public DatabaseConnection getConnection() {
        return this.selectedConnection;
    }

    public void persistModel() {
        //final DBMetaData meta = new DBMetaData();
        final Connection connection = this.selectedConnection.getJDBCConnection();
        if (connection != null) {
            try {
                //meta.connectDB(this.selectedConnection.getJDBCConnection());
                def = DatabaseObjectFactory.createDBConnectionDefinition(
                        this.selectedConnection.getDisplayName(), this.selectedConnection.getDriverClass(),
                        this.selectedConnection.getDatabaseURL(), this.selectedConnection.getUser(),
                        this.selectedConnection.getPassword(), "Descriptive info here", DBMetaData.getDBType(connection));

                this.dbtype = DBMetaData.getDBType(this.selectedConnection.getJDBCConnection());

            } catch (final Exception ex) {
                ex.printStackTrace();
                ErrorManager.getDefault().log(ErrorManager.ERROR, ex.getMessage());
                ErrorManager.getDefault().notify(ErrorManager.ERROR, ex);
            }
        }
    }
}
