/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.iep.editor.wizard;

import javax.swing.ButtonModel;
import javax.swing.JPanel;

import org.openide.util.NbBundle;

public final class IEPVisualPanel1 extends JPanel {

    /** Creates new form IEPVisualPanel1 */
    public IEPVisualPanel1() {
        initComponents();
        initGUI();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(IEPVisualPanel1.class, "IEPVisualPanel1_title");
    }

    public String getSelectedOption() {
        String selOption = WizardConstants.WIZARD_FIRST_PANEL_KEY_VALUE_CREATE_EMPTY_IEP;
        
        ButtonModel  selModel = buttonGroup1.getSelection();
        if(selModel.equals(jRadioButton2.getModel())) {
            selOption = WizardConstants.WIZARD_FIRST_PANEL_KEY_VALUE_CREATE_IEP_USING_INPUT_SCHEMA;
        }
        
        return selOption;
    }
    
    public ButtonModel getButtonModel() {
        return this.buttonGroup1.getSelection();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        jRadioButton1.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButton1, "Create Empty Intelligent Event Processor");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButton2, "Create Intelligent Event Processor Using Existing Schema");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jRadioButton1)
                    .add(jRadioButton2))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jRadioButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jRadioButton2)
                .addContainerGap(244, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void initGUI() {
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}

