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
package org.netbeans.modules.iep.editor.ps;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

import org.netbeans.api.project.Project;
import org.netbeans.modules.iep.editor.wizard.ElementOrTypeChooserHelper;
import org.netbeans.modules.iep.editor.wizard.SchemaArtifactSelectionDialog;
import org.netbeans.modules.iep.editor.xsd.SchemaArtifactTreeCellEditor;
import org.netbeans.modules.iep.editor.xsd.SchemaArtifactTreeCellRenderer;
import org.netbeans.modules.iep.editor.xsd.nodes.FolderNode;
import org.netbeans.modules.iep.editor.xsd.nodes.SchemaArtifactTreeModel;
import org.netbeans.modules.xml.axi.AXIComponent;
import org.openide.util.NbBundle;

/**
 * @author Sun Microsystems.
 *
 */
public class SchemaSelectionPanel extends JPanel {
    //private List<String> mExistingArtificatNames = new ArrayList<String>();
    //private List<AXIComponent> mSelectedSchemas = new ArrayList<AXIComponent>();
    private SchemaTreeCellEditor mEditor = null;
     
    /** Creates new form SchemaArtifactSelectionPanel */
    public SchemaSelectionPanel() {
        initComponents();
    }

    public SchemaSelectionPanel(List<String> existingArtificatNames, Project project) {
        this();
        FolderNode rootNode = new FolderNode(NbBundle.getMessage(ElementOrTypeChooserHelper.class, "LBL_ByFile_DisplayName"));
        //this.mExistingArtificatNames = existingArtificatNames;
        
        SchemaTreeModel model = new SchemaTreeModel(rootNode, project, existingArtificatNames,
                                                    jTree1);
        
        
        this.jTree1.setModel(model);
        //this.jTree1.addMouseListener(new TreeMouseListener());
        String title = NbBundle.getMessage(SchemaSelectionDialog.class, "SchemaSelectionDialog.Title");
        String tooltip = NbBundle.getMessage(SchemaSelectionDialog.class, "SchemaSelectionDialog.Tooltip");
        
        this.jTree1.setToolTipText(tooltip);
        this.jTree1.getAccessibleContext().setAccessibleDescription(tooltip);
        this.jTree1.getAccessibleContext().setAccessibleName(title);
        
        TreeCellRenderer renderer = new SchemaArtifactTreeCellRenderer();
        //TreeCellEditor editor = new SchemaArtifactTreeCellEditor(jTree1, renderer, existingArtificatNames);
        mEditor = new SchemaTreeCellEditor(jTree1, renderer, existingArtificatNames);
        this.jTree1.setCellRenderer(renderer);
        this.jTree1.setCellEditor(mEditor);
        this.jTree1.setEditable(true);
    }
    
    public List<AXIComponent> getSelectedArtifactNames() {
        //return this.mSelectedSchemas;
	return mEditor.getSelectedSchemas();
    }
    
    public List<AXIComponent> getRemovedArtifactNames() {
	return mEditor.getRemovedSchemas();
    }
	
	
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        jScrollPane1.setViewportView(jTree1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    
}
