/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.iep.editor.wizard;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import org.netbeans.api.project.Project;
import org.netbeans.modules.xml.schema.model.SchemaComponent;
import org.openide.util.NbBundle;

public final class IEPVisualPanel2 extends JPanel {

    private Project mProject;
    
    private ElementOrTypeChooserPanel mElementOrTypePanel;
    
    public IEPVisualPanel2(Project project) {
        this.mProject = project;
        initComponents();
        initGUI();
    }
            
    @Override
    public String getName() {
    	return NbBundle.getMessage(IEPVisualPanel1.class, "IEPVisualPanel2_title");
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
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void initGUI() {
        mElementOrTypePanel = new ElementOrTypeChooserPanel(this.mProject);
        this.setLayout(new BorderLayout());
        
        this.add(mElementOrTypePanel, BorderLayout.CENTER);
    }
    
    public SchemaComponent getSelectedSchemaComponent() {
        return mElementOrTypePanel.getSelectedSchemaComponent();
    }
    
    

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        mElementOrTypePanel.addPropertyChangeListener(listener);
    }

    @Override
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        mElementOrTypePanel.removePropertyChangeListener(listener);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

