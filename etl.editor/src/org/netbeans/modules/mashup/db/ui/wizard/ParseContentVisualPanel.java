/*
 * ParseContentVisualPanel.java
 *
 * Created on April 4, 2007, 12:39 PM
 */

package org.netbeans.modules.mashup.db.ui.wizard;

/**
 *
 * @author  karthikeyan s
 */
public class ParseContentVisualPanel extends javax.swing.JPanel {
    
    private ParseContentPanel owner;
    
    /** Creates new form ParseContentVisualPanel */
    public ParseContentVisualPanel(ParseContentPanel panel) {
        initComponents();
        owner = panel;
    }
    
    public String getName() {
        return "Import Table MetaData";
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
            //.addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            //.addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
