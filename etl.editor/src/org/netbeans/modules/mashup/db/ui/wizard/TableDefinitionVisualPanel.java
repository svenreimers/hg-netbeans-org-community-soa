/*
 * TableDefinitionVisualPanel.java
 *
 * Created on April 4, 2007, 12:56 PM
 */

package org.netbeans.modules.mashup.db.ui.wizard;

import net.java.hulp.i18n.Logger;
import org.netbeans.modules.etl.logger.Localizer;

/**
 *
 * @author  karthikeyan s
 */
public class TableDefinitionVisualPanel extends javax.swing.JPanel {
    
    private TableDefinitionPanel owner;
    private static transient final Logger mLogger = Logger.getLogger(TableDefinitionVisualPanel.class.getName());
    private static transient final Localizer mLoc = Localizer.get();
    /** Creates new form TableDefinitionVisualPanel */
    public TableDefinitionVisualPanel(TableDefinitionPanel panel) {
        initComponents();
        owner = panel;
    }
    
    public String getName() {
        String nbBundle1 = mLoc.t("BUND252: Enter Column Properties");
        return nbBundle1.substring(15);
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            //.add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            //.add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
