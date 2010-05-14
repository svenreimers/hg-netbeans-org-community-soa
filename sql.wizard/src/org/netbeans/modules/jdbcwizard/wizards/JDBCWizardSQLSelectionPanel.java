package org.netbeans.modules.jdbcwizard.wizards;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;

/*
 * JDBCWizardSQLSelectionPanel.java
 *
 * Created on September 8, 2008, 7:46 PM
 */
import org.openide.util.HelpCtx;



/**
 *
 * @author  admin
 */
public class JDBCWizardSQLSelectionPanel extends javax.swing.JPanel implements WizardDescriptor.Panel{

    /** Creates new form JDBCWizardSQLSelectionPanel */
    public JDBCWizardSQLSelectionPanel() {
        initComponents();
    }

    /** Creates new form JDBCWizardPrepstmtPanel */
    public JDBCWizardSQLSelectionPanel(final String title) {
        initComponents();
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

        sqlFileLabel = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        Browse = new java.awt.Button();

        sqlFileLabel.setText(org.openide.util.NbBundle.getMessage(JDBCWizardSQLSelectionPanel.class, "JDBCWizardSQLSelectionPanel.sqlFileLabel.text")); // NOI18N

        textField1.setText(org.openide.util.NbBundle.getMessage(JDBCWizardSQLSelectionPanel.class, "JDBCWizardSQLSelectionPanel.textField1.text")); // NOI18N
        textField1.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                textField1TextValueChanged(evt);
            }
        });

        Browse.setActionCommand(org.openide.util.NbBundle.getMessage(JDBCWizardSQLSelectionPanel.class, "JDBCWizardSQLSelectionPanel.Browse.actionCommand")); // NOI18N
        Browse.setLabel(org.openide.util.NbBundle.getMessage(JDBCWizardSQLSelectionPanel.class, "JDBCWizardSQLSelectionPanel.Browse.label")); // NOI18N
        Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(Browse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(40, 40, 40)
                        .add(sqlFileLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(textField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(99, 99, 99)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, sqlFileLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, textField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Browse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        Browse.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(JDBCWizardSQLSelectionPanel.class, "JDBCWizardSQLSelectionPanel.Browse.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

private void BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseActionPerformed
// TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        File file = null;
        int result = fileChooser.showDialog(this, "Open");
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            textField1.setText(file.toString());
        }
        readSQLFile(file);
        fireChangeEvent();
}//GEN-LAST:event_BrowseActionPerformed

private void textField1TextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_textField1TextValueChanged
// TODO add your handling code here:
}//GEN-LAST:event_textField1TextValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Browse;
    private javax.swing.JLabel sqlFileLabel;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
    private final List /* <ChangeListener> */listeners = new ArrayList();
    private String sqlText;
    
    public Component getComponent() {
        return this;
    }

    public HelpCtx getHelp() {
        return new HelpCtx(JDBCWizardSelectionPanel.class);
    }

    public void readSettings(Object settings) {
       WizardDescriptor wd = null;
        if (settings instanceof JDBCWizardContext) {
            final JDBCWizardContext wizardContext = (JDBCWizardContext) settings;
            wd = (WizardDescriptor) wizardContext.getProperty(JDBCWizardContext.WIZARD_DESCRIPTOR);

        } else if (settings instanceof WizardDescriptor) {
            wd = (WizardDescriptor) settings;
        } 
    }

    public void storeSettings(Object arg0) {
        
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
        fireChangeEvent();
    }
    
    public void readSQLFile(File sqlFile){
        //Read sql file.
        try{    
            BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
            String line = null;
            StringBuffer sql = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                if (sql.length() != 0) {
                    sql.append("\n");
                }
                sql.append(line);
            }
            this.sqlText = sql.toString();
        }catch(Exception e){
          e.printStackTrace();  
        }
    }
    
    public String getSqlText(){
        return this.sqlText;
    }
    @Override
    public boolean isValid() {
        if((this.sqlText != null) && !(sqlText.equalsIgnoreCase(""))) {
            return true;
        }
        return false;
    }
    
}
