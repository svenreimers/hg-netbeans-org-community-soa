/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.wsdlextensions.ldap;

import java.util.regex.Pattern;
import javax.swing.JPanel;
import org.openide.WizardDescriptor;

public final class LDAPServerBrowserVisualPanel1 extends JPanel {

    /** Creates new form LDAPServerBrowserVisualPanel1 */
    public LDAPServerBrowserVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return "File name setting";
    }
    
    public String validateInput(){
        String name=jTextFieldFileName.getText();
        if("".equals(name)){
            return "Please input the file name";
        }
        if(!Pattern.matches("\\w+", name)){
            return "Not file name format";
        }
        return "";
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    public void store(WizardDescriptor wd){
        wd.putProperty("FILE_NAME", jTextFieldFileName.getText());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelFileName = new javax.swing.JLabel();
        jTextFieldFileName = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        jLabelFileName.setLabelFor(jTextFieldFileName);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelFileName, org.openide.util.NbBundle.getMessage(LDAPServerBrowserVisualPanel1.class, "LDAPServerBrowserVisualPanel1.jLabelFileName.text")); // NOI18N
        jLabelFileName.setName("jLabelFileName"); // NOI18N

        jTextFieldFileName.setName("jTextFieldFileName"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(53, 53, 53)
                .add(jLabelFileName)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(38, 38, 38)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelFileName)
                    .add(jTextFieldFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTextFieldFileName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(LDAPServerBrowserVisualPanel1.class, "LDAPServerBrowserVisualPanel1.jTextFieldFileName.FileName")); // NOI18N
        jTextFieldFileName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(LDAPServerBrowserVisualPanel1.class, "LDAPServerBrowserVisualPanel1.jTextFieldFileName.desc")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelFileName;
    private javax.swing.JTextField jTextFieldFileName;
    // End of variables declaration//GEN-END:variables
}

