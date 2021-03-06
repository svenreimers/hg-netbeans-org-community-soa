/*
 * HL7BindingsConfigurationEditorPanel.java
 *
 * Created on July 30, 2008, 12:02 PM
 */

package org.netbeans.modules.wsdlextensions.hl7.configeditor.panels;

import javax.swing.JPanel;

/**
 *
 * @author  Vishnuvardhan P.R
 */
public class HL7BindingsConfigurationEditorPanel extends javax.swing.JPanel {

    /** Creates new form HL7BindingsConfigurationEditorPanel */
    public HL7BindingsConfigurationEditorPanel() {
        initComponents();
    }
    
    public void addTab(String title, JPanel jPanel){
        jTabbedPane1.addTab(title, jPanel);
    }

    public void doFirePropertyChange(String propertyName, Object oldValue, Object newValue) {
        firePropertyChange(propertyName, oldValue, newValue);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
