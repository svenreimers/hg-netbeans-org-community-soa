/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

/*
 * ElementOrTypeChooserPanel.java
 *
 * Created on September 1, 2006, 2:37 PM
 */

package org.netbeans.modules.iep.editor.ps;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import javax.swing.SwingUtilities;

import org.netbeans.api.project.Project;
import org.netbeans.modules.iep.editor.wizard.Utility;
import org.netbeans.modules.iep.model.WsOperatorComponent;
import org.netbeans.modules.iep.model.OperatorComponent;
import org.netbeans.modules.xml.schema.model.SchemaComponent;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 *
 * 
 */
public class StreamInputChooserPanel extends javax.swing.JPanel implements ExplorerManager.Provider {
    
    private Project mProject;
    private SchemaComponent mPreviousSelectedComponent;
    
    /** Creates new form ElementOrTypeChooserPanel */
    public StreamInputChooserPanel(Project project) {
        this.mProject = project;
        initComponents();
        initGUI();
    }
    
    public StreamInputChooserPanel(Project project, 
                                     SchemaComponent previousSelectedComponent) {
        this.mProject = project;
        this.mPreviousSelectedComponent = previousSelectedComponent;
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
        beanTreeView1 = new org.openide.explorer.view.BeanTreeView();

        beanTreeView1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        beanTreeView1.setAutoscrolls(true);
        beanTreeView1.setDefaultActionAllowed(false);
        beanTreeView1.setDragSource(false);
        beanTreeView1.setDropTarget(false);
        beanTreeView1.setPopupAllowed(false);
        beanTreeView1.setRootVisible(false);
        beanTreeView1.setSelectionMode(1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(beanTreeView1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(beanTreeView1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void initGUI() {
        manager = new ExplorerManager();
        manager.addPropertyChangeListener(new ExplorerPropertyChangeListener());
        Node rootNode = new AbstractNode(new Children.Array());
        manager.setRootContext(rootNode);
        populateRootNode(rootNode);
        Utility.expandNodes(beanTreeView1, 2, rootNode);
        
        String treeACDN = NbBundle.getMessage(StreamInputChooserPanel.class, "ACSN_StreamInputChooserPanel.SelectStreamInput");
        String treeACDS = NbBundle.getMessage(StreamInputChooserPanel.class, "ACDS_StreamInputChooserPanel.SelectStreamInput");
        beanTreeView1.getAccessibleContext().setAccessibleName(treeACDN);
        beanTreeView1.getAccessibleContext().setAccessibleDescription(treeACDS);
        
    }
    
    private void populateRootNode(Node rootNode) {
        StreamInputChooserHelper schemaHelper = new StreamInputChooserHelper(mProject);
        schemaHelper.populateNodes(rootNode);
        
        if (mPreviousSelectedComponent != null) {
            Node node = schemaHelper.selectNode(mPreviousSelectedComponent);
            if (node != null) {
                selectNode(node);
                firePropertyChange(StreamInputChooserPanel.PROP_SELECTED_INPUT_OPERATOR_COMPONENT, null, mPreviousSelectedComponent);
            }
        } else {
            selectNode(rootNode);
        }
        
    }
    
    private void selectNode(Node node) {
        final Node finalNode = node;
        Runnable run = new Runnable() {
            public void run() {
                if(manager != null) {
                    try {
                        manager.setExploredContextAndSelection(finalNode, new Node[] {finalNode});
                        beanTreeView1.expandNode(finalNode);
                    } catch(PropertyVetoException ex) {
                        //ignore this
                    }

                }
            }
        };
        SwingUtilities.invokeLater(run);
    }
    
    public ExplorerManager getExplorerManager() {
        return manager;
    }
    
    
    
    public static final void main(String[] args) {
/*        JFrame frame = new JFrame();
        frame.add(new ElementOrTypeChooserEditorPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
    }

    
    @Override
    public void removeNotify() {
//        if (mEnv != null && mEnv.getState().equals(PropertyEnv.STATE_VALID)) {
//            if (selectedComponent != null) {
//                this.firePropertyChange(ElementOrTypePropertyEditor.PROP_NAME, null, selectedComponent);
//            }
//        }
        
        super.removeNotify();
    }
    
    private ExplorerManager manager;
    public static String PROP_SELECTED_INPUT_OPERATOR_COMPONENT = "PROP_SELECTED_INPUT_OPERATOR_COMPONENT";
    private WsOperatorComponent selectedComponent;
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openide.explorer.view.BeanTreeView beanTreeView1;
    // End of variables declaration//GEN-END:variables
    class ExplorerPropertyChangeListener implements PropertyChangeListener {
        
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals(ExplorerManager.PROP_SELECTED_NODES)) {
                Node[] nodes = (Node[]) evt.getNewValue();
                if(nodes.length > 0) {
                    Node node = nodes[0];
                    //set the selected node to null and state as invalid by default
                    WsOperatorComponent previousComp = selectedComponent;
                    selectedComponent = null;
                    
                    firePropertyChange(PROP_SELECTED_INPUT_OPERATOR_COMPONENT, previousComp, selectedComponent);
                    WsOperatorComponent comp = node.getLookup().lookup(WsOperatorComponent.class);
                    if (comp != null) {
                        previousComp = selectedComponent;
                        selectedComponent = comp;
                        firePropertyChange(PROP_SELECTED_INPUT_OPERATOR_COMPONENT, previousComp, selectedComponent);
                    }
                    
                }
            }
        }
    }

    public WsOperatorComponent getSelectedInputOperatorComponent() {
        return selectedComponent;
    }
 
   
}
