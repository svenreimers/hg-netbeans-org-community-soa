package org.netbeans.modules.etl.ui.view.wizardsloader;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;


import org.netbeans.modules.sql.framework.common.utils.DBURL;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import net.java.hulp.i18n.Logger;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.netbeans.modules.etl.logger.Localizer;
import org.netbeans.modules.etl.ui.ETLEditorSupport;
import org.netbeans.modules.sql.framework.common.utils.DBExplorerUtil;

public final class SelectOrCreateDBVisualPanel extends JPanel {

    private static transient final Logger mLogger = Logger.getLogger(SelectOrCreateDBVisualPanel.class.getName());
    private static transient final Localizer mLoc = Localizer.get();
    private SelectOrCreateDBPanel owner;
    private boolean populated;
    private boolean canProceed = false;
    private String selectedDB;
    private String dbcreatedname = null;
    private final String newdbnameDefault = "NewDbName";
    private boolean createnewdb = false;
    private final String fs = File.separator;
    private String defaultdbpath_prifix = ETLEditorSupport.PRJ_PATH + fs + "nbproject" + fs + "private" + fs + "databases";

    /**
     * Creates new form ChooseTableVisualPanel
     */
    public SelectOrCreateDBVisualPanel() {
        initComponents();
        databasesCombo.removeAllItems();
        createnewdbButton.setMnemonic('C');
        //Block New DB Creation by Default
        initNewDBPanel();
    }

    public SelectOrCreateDBVisualPanel(SelectOrCreateDBPanel panel) {
        this();
        this.owner = panel;
        populateDBList();

        databasesCombo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox) e.getSource();
                DBURL selectedUrl = (DBURL) combo.getSelectedItem();
                if (selectedUrl != null) {
                    selectedDB = selectedUrl.getURL();
                    //selectedDbpath.setText(selectedUrl.getWorkDir());
                    selectedDbpath.setToolTipText(selectedUrl.getWorkDir());
                    selectedDbpath.setText("${project.home}" + File.separator + "nbproject" + File.separator + "private" + File.separator + "databases");
                    if (selectedDB != null) {
                        SelectOrCreateDBVisualPanel.this.owner.fireChangeEvent();
                    }
                }
            }
        });


        if (databasesCombo.getItemCount() != 0) {
            databasesCombo.setSelectedIndex(0);
        }
    }

    private void initNewDBPanel() {
        this.newdbname.setEnabled(false);
        this.newdbname.setText(newdbnameDefault);
        this.createnewdbButton.setEnabled(false);
    }

    @Override
    public String getName() {
        String nbBundle1 = mLoc.t("BUND777: Select Or Create Database");
        return nbBundle1.substring(15);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        String nbBundle1 = mLoc.t("BUND904: Database URL");
        databasesCombo = new javax.swing.JComboBox();
        String nbBundle5 = mLoc.t("BUND906: Create and Use New Database");
        createNewDBCheckBox = new javax.swing.JCheckBox();
        errorOnPanelOne = new javax.swing.JLabel();
        selectDburllbl = new javax.swing.JLabel();
        selectedDbpathlbl = new javax.swing.JLabel();
        selectedDbpath = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        newdbnamelbl = new javax.swing.JLabel();
        newdbpathlbl = new javax.swing.JLabel();
        newdbname = new javax.swing.JTextField();
        createnewdbButton = new javax.swing.JButton();
        errorOnPenalTwo = new javax.swing.JLabel();
        prompt = new javax.swing.JLabel();
        newdbpath = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Select Database for External Data Sources"));
        setMaximumSize(new java.awt.Dimension(450, 350));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(525, 340));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Existing Database for Staging External Data Sources"));

        org.openide.awt.Mnemonics.setLocalizedText(createNewDBCheckBox, "Create and Use New Database");
        createNewDBCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                createNewDBCheckBoxItemStateChanged(evt);
            }
        });

        errorOnPanelOne.setForeground(new java.awt.Color(255, 0, 0));

        selectDburllbl.setLabelFor(databasesCombo);
        org.openide.awt.Mnemonics.setLocalizedText(selectDburllbl, "DB URL");

        selectedDbpathlbl.setLabelFor(selectedDbpath);
        org.openide.awt.Mnemonics.setLocalizedText(selectedDbpathlbl, "DB Path");

        selectedDbpath.setForeground(new java.awt.Color(0, 0, 204));
        selectedDbpath.setPreferredSize(new java.awt.Dimension(28, 14));
        selectedDbpath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectedDbpathMouseEnetered(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(selectedDbpathlbl)
                            .add(selectDburllbl))
                        .add(38, 38, 38)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(selectedDbpath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 392, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(databasesCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 392, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, createNewDBCheckBox)))
                    .add(errorOnPanelOne))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selectDburllbl)
                    .add(databasesCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(selectedDbpathlbl)
                    .add(selectedDbpath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9)
                .add(createNewDBCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(errorOnPanelOne)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        databasesCombo.getAccessibleContext().setAccessibleName(nbBundle1.substring(15));
        databasesCombo.getAccessibleContext().setAccessibleDescription(nbBundle1.substring(15));
        createNewDBCheckBox.getAccessibleContext().setAccessibleName(nbBundle5.substring(15));
        createNewDBCheckBox.getAccessibleContext().setAccessibleDescription(nbBundle5.substring(15));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Create New Database"));

        newdbnamelbl.setLabelFor(newdbname);
        org.openide.awt.Mnemonics.setLocalizedText(newdbnamelbl, "DB Name");

        newdbpathlbl.setLabelFor(newdbpath);
        org.openide.awt.Mnemonics.setLocalizedText(newdbpathlbl, "DB Path");

        newdbname.setText("NewDbName");
        newdbname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newdbnameKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(createnewdbButton, "Create Database");
        createnewdbButton.setEnabled(false);
        createnewdbButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewDBButtomActionPerformed(evt);
            }
        });

        errorOnPenalTwo.setForeground(new java.awt.Color(255, 0, 0));

        prompt.setForeground(new java.awt.Color(0, 0, 255));

        newdbpath.setForeground(new java.awt.Color(0, 0, 204));
        newdbpath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dbPathMouseEntered(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(prompt)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(errorOnPenalTwo))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(newdbnamelbl)
                            .add(newdbpathlbl))
                        .add(31, 31, 31)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(newdbpath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 380, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(newdbname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 201, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(333, Short.MAX_VALUE)
                .add(createnewdbButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newdbnamelbl)
                    .add(newdbname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newdbpathlbl)
                    .add(newdbpath))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(createnewdbButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(errorOnPenalTwo)
                    .add(prompt)))
        );

        newdbname.getAccessibleContext().setAccessibleName("NewDBName");
        newdbname.getAccessibleContext().setAccessibleDescription("NewDBName");
        createnewdbButton.getAccessibleContext().setAccessibleDescription("Create Database");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(8, 8, 8))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void createNewDBCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_createNewDBCheckBoxItemStateChanged

    if (evt.getStateChange() == ItemEvent.DESELECTED) {
        //CheckBox Deselected
        createnewdb = false;
        //databasesCombo.removeAllItems();
        populateDBList();
        databasesCombo.setEnabled(true);
        newdbname.setEnabled(false);
        newdbname.setText(newdbnameDefault);
        newdbpath.setText("");
        newdbpath.setToolTipText("");
        selectedDbpath.setToolTipText("");
        errorOnPenalTwo.setText("");
        createnewdbButton.setEnabled(false);
    } else {
        //CheckBox Selected
        checkNewDBName();
        createnewdb = true;
        //databasesCombo.removeAllItems();
        databasesCombo.setEnabled(false);
        newdbname.setText(newdbnameDefault);
        newdbname.setEnabled(true);
        newdbpath.setToolTipText(defaultdbpath_prifix + fs + newdbname.getText());
        newdbpath.setText("${project.home}" + File.separator + "nbproject" + File.separator + "private" + File.separator + "databases" + File.separator + newdbname.getText());
        createnewdbButton.setEnabled(true);
        prompt.setText("");
        SelectOrCreateDBVisualPanel.this.owner.fireChangeEvent();
    }
}//GEN-LAST:event_createNewDBCheckBoxItemStateChanged

private void createNewDBButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewDBButtomActionPerformed
// TODO add your handling code here:
    checkNewDBName();
    if (canProceed) {
        String dbname = ETLEditorSupport.PRJ_NAME + "_" + newdbname.getText();
        boolean success = this.owner.createNewAxionDbInstance(dbname.toUpperCase(), defaultdbpath_prifix + fs + newdbname.getText());
        if (success) {
            prompt.setText("Database Created Successfully!");
            dbcreatedname = newdbname.getText();
            createNewDBCheckBox.setSelected(false);
            createNewDBCheckBox.setEnabled(true);
        } else {
            prompt.setText("Failed to create requested db. Pls check the logs for error");
        }
    }
}//GEN-LAST:event_createNewDBButtomActionPerformed

private void newdbnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newdbnameKeyReleased
    checkNewDBName();
    newdbpath.setText("${project.home}" + File.separator + "nbproject" + File.separator + "private" + File.separator + "databases" + File.separator + newdbname.getText());
    newdbpath.repaint();
}//GEN-LAST:event_newdbnameKeyReleased

private void dbPathMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dbPathMouseEntered
    newdbpath.setToolTipText(defaultdbpath_prifix + fs + newdbname.getText());
}//GEN-LAST:event_dbPathMouseEntered

private void selectedDbpathMouseEnetered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedDbpathMouseEnetered
    selectedDbpath.setToolTipText(defaultdbpath_prifix + fs + databasesCombo.getSelectedItem().toString());
}//GEN-LAST:event_selectedDbpathMouseEnetered

    public String getSelectedDatabase() {
        return selectedDB;
    }

    public boolean canAdvance() {
        if (createnewdb) {
            canProceed = false;
        } else {
            if ((this.populated) && (this.databasesCombo.getSelectedItem() != null)) {
                canProceed = true;
            }
        }
        return canProceed;
    }

    private void populateDBList() {
        List<DatabaseConnection> models = new ArrayList<DatabaseConnection>();
        //DBExplorerUtil.recreateMissingFlatfileConnectionInDBExplorer();
        /*DatabaseConnection[] dbconns = ConnectionManager.getDefault().getConnections();
        for (int i = 0; i < dbconns.length; i++) {
        if (dbconns[i].getDriverClass().equals(DBExplorerUtil.AXION_DRIVER)) {
        //Select databases that exist only in the default project directory
        if (dbconns[i].getDatabaseURL().indexOf(defaultdbpath_prifix) != -1) {
        models.add(dbconns[i]);
        }
        }
        }*/
        models.addAll(DBExplorerUtil.getDatabasesForCurrentProject());
        databasesCombo.removeAllItems();
        for (DatabaseConnection model : models) {
            databasesCombo.addItem(new DBURL(model.getDatabaseURL(), false));
        }

        if (databasesCombo.getItemCount() != 0) {
            this.populated = true;
            errorOnPanelOne.setText("");
        } else {
            String nbBundle2 = mLoc.t("BUND229: No File Database found.");
            errorOnPanelOne.setText(nbBundle2.substring(15));
            createNewDBCheckBox.setSelected(true); //Allow New DB to be created at this condition
            createNewDBCheckBox.setEnabled(false);
            this.populated = false;
        }

        // Set selected to new database name if created
        if (dbcreatedname != null) {
            for (int i = 0; i < databasesCombo.getItemCount(); i++) {
                if (((DBURL) databasesCombo.getItemAt(i)).toString().equalsIgnoreCase(ETLEditorSupport.PRJ_NAME + "_" + dbcreatedname)) {
                    databasesCombo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void checkNewDBName() {
        String name = newdbname.getText().trim();

        if (name != null) {
            if ((name.equals("")) || (name.length() == 0)) {
                errorOnPenalTwo.setText("Database name cannot be left null or blank");
            } else {
                 File f = new File(defaultdbpath_prifix + fs + newdbname.getText());   
                char[] ch = name.toCharArray();
                if (ch.length != 0) {
                    if (f.exists()) {
                        String nbBundle5 = mLoc.t("BUND270: Database {0} already exists. ", name);
                        errorOnPenalTwo.setText(nbBundle5.substring(15));
                        canProceed = false;
                    } else if (Character.isDigit(ch[0])) {
                        String nbBundle6 = mLoc.t("BUND267: Database name should start with an alphabet.");
                        errorOnPenalTwo.setText(nbBundle6.substring(15));
                        canProceed = false;
                    } else {
                        errorOnPenalTwo.setText("");
                        canProceed = true;
                    }
                } else {
                    errorOnPenalTwo.setText("Database name cannot be blank");
                    canProceed = false;
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox createNewDBCheckBox;
    private javax.swing.JButton createnewdbButton;
    private javax.swing.JComboBox databasesCombo;
    private javax.swing.JLabel errorOnPanelOne;
    private javax.swing.JLabel errorOnPenalTwo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField newdbname;
    private javax.swing.JLabel newdbnamelbl;
    private javax.swing.JLabel newdbpath;
    private javax.swing.JLabel newdbpathlbl;
    private javax.swing.JLabel prompt;
    private javax.swing.JLabel selectDburllbl;
    private javax.swing.JLabel selectedDbpath;
    private javax.swing.JLabel selectedDbpathlbl;
    // End of variables declaration//GEN-END:variables
}
