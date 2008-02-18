/*
 * GraphView.java
 *
 * Created on August 3, 2007, 3:46 PM
 */

package org.netbeans.modules.iep.editor.designer;

import java.awt.BorderLayout;

import org.netbeans.modules.iep.editor.PlanDataObject;
import org.netbeans.modules.iep.model.IEPModel;

/**
 *
 * @author  radval
 */
public class GraphView extends javax.swing.JPanel {

	private IEPModel mModel;
	
	private PlanCanvas mCanvas;
	
    /** Creates new form GraphView */
    public GraphView(IEPModel model) {
    	this.mModel = model;
        initComponents();
        initGUI();
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
    	this.mCanvas = new PlanCanvas(this.mModel);
    	this.setLayout(new BorderLayout());
    	this.add(BorderLayout.CENTER, this.mCanvas);
    }

    public PlanCanvas getPlanCanvas() {
    	return this.mCanvas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   
}
