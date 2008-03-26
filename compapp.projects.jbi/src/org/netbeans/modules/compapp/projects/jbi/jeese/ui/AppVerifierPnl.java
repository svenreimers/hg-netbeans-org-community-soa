/*
 * AppVerifierPnl.java
 *
 * Created on March 25, 2008, 11:04 AM
 */

package org.netbeans.modules.compapp.projects.jbi.jeese.ui;

import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.netbeans.modules.compapp.javaee.util.JavaEEVerifierReportItem;

/**
 *
 * @author  gpatil
 */
public class AppVerifierPnl extends javax.swing.JPanel {

    static class ResultModel extends DefaultTableModel {
        private List<JavaEEVerifierReportItem> ris;
        
        ResultModel(List<JavaEEVerifierReportItem> list){
            super(list.size(), 7);
            this.ris = list;
        }
        @Override
        public String getColumnName(int column) {
            String ret = ""; //NOI18N
            Map<String, String> map = JavaEEVerifierReportItem.getDisplayNames();
            switch (column){
                case 0: ret = map.get(JavaEEVerifierReportItem.KEY_FL_NAME); break;
                case 1: ret = map.get(JavaEEVerifierReportItem.KEY_JNDI_NAME); break;
                case 2: ret = map.get(JavaEEVerifierReportItem.KEY_STATUS); break;
                case 3: ret = map.get(JavaEEVerifierReportItem.KEY_MSG); break;
                case 4: ret = map.get(JavaEEVerifierReportItem.KEY_EXPECTED_CLASS); break;
                case 5: ret = map.get(JavaEEVerifierReportItem.KEY_REFERENCING_CLASS); break;
                case 6: ret = map.get(JavaEEVerifierReportItem.KEY_REFERENCING_EJB); break;
            }
            return ret;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String ret = ""; //NOI18N
            JavaEEVerifierReportItem item = this.ris.get(rowIndex);
            switch (columnIndex){
                case 0: ret = item.getFileName(); break;
                case 1: ret = item.getJndiName(); break;
                case 2: ret = item.getStatusStr(); break;
                case 3: ret = item.getMessage(); break;
                case 4: ret = item.getExpectedClass(); break;
                case 5: ret = item.getReferencingClass(); break;
                case 6: ret = item.getReferencingEjb(); break;
            }
            
            return ret;
        }
        
    }
    
    
    /** Creates new form AppVerifierPnl */
    public AppVerifierPnl(List<JavaEEVerifierReportItem> ris) {
        this.ris = ris;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(550, 115));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setRequestFocusEnabled(false);

        jTable1.setModel(getTableModel());
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setEnabled(false);
        jTable1.setPreferredSize(new java.awt.Dimension(500, 100));
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AppVerifierPnl.class, "AN_App_Verifier_Table")); // NOI18N
        jTable1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(AppVerifierPnl.class, "ASD_App_Verifier_Table")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jScrollPane1, gridBagConstraints);
        jScrollPane1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AppVerifierPnl.class, "AN_App_Verifier_Table")); // NOI18N
        jScrollPane1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(AppVerifierPnl.class, "ASD_App_Verifier_Table")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private List<JavaEEVerifierReportItem> ris = null;
    
    TableModel getTableModel(){
        TableModel ret = new ResultModel(this.ris);
        return ret;
    }
}
