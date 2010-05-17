/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 1997-2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.worklist.editor.view;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import javax.swing.SwingUtilities;
import org.netbeans.modules.wlm.model.api.WLMModel;
import org.netbeans.modules.worklist.dataloader.WorklistDataObject;
import org.netbeans.modules.worklist.util.Util;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.loaders.DataObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 *
 * @author  radval
 */
public class WSDLOperationPanel extends javax.swing.JPanel implements ExplorerManager.Provider {
    
    private BeanTreeView btv;
    
    private ExplorerManager manager;
    
    private Node mRootNode;

    private DataObject mDObj;
    
    /** Creates new form WSDLOperationPanel */
    public WSDLOperationPanel(DataObject dObj) {
        initComponents();
        this.mDObj = dObj;
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
        this.setLayout(new BorderLayout());

            manager = new ExplorerManager();
          
            mRootNode = new AbstractNode(new Children.Array());
            populateRootNode();
            manager.setRootContext( mRootNode );

            
            // Create the templates view
            btv = new BeanTreeView();
            btv.setRootVisible( false );
            btv.setSelectionMode( javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION );
            btv.setPopupAllowed( false );
            btv.expandNode(mRootNode);
            btv.setDefaultActionAllowed(false);
            Util.expandNodes(btv, 2, mRootNode);
            manager.setExploredContext(mRootNode);
            this.add(btv, BorderLayout.CENTER);
            btv.setName(NbBundle.getMessage(WSDLOperationPanel.class, "ElementOrTypeOrMessagePartPropertyPanel.btv.name")); // NOI18N
            btv.getAccessibleContext().setAccessibleName(NbBundle.getMessage(WSDLOperationPanel.class, "ElementOrTypeOrMessagePartPropertyPanel.btv.AccessibleContext.accessibleName")); // NOI18N
            btv.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(WSDLOperationPanel.class, "ElementOrTypeOrMessagePartPropertyPanel.btv.AccessibleContext.accessibleDescription")); // NOI18N
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
     private void populateRootNode() {
            MessagePartChooserHelper wsdlHelper = new MessagePartChooserHelper(this.mDObj);
            wsdlHelper.populateNodes(mRootNode);
            
//            Node elementOrTypeFolderNode = new FolderNode(new Children.Array());
//            elementOrTypeFolderNode.setDisplayName(NbBundle.getMessage(ElementOrTypeOrMessagePartPropertyPanel.class, "LBL_ElementOrType_DisplayName"));
//            ElementOrTypeChooserHelper schemaHelper = new ElementOrTypeChooserHelper(mModel);
//            schemaHelper.populateNodes(elementOrTypeFolderNode);
//            mRootNode.getChildren().add(new Node[] {elementOrTypeFolderNode});
//            
//            if (previousSelection != null) {
//                ParameterType type = previousSelection.getParameterType();
//                Node selected = null;
//                switch (type) {
//                case ELEMENT:
//                    selected = schemaHelper.selectNode(previousSelection.getElement());
//                    break;
//                case TYPE:
//                    selected = schemaHelper.selectNode(previousSelection.getType());
//                    break;
//                case MESSAGEPART:
//                    selected = wsdlHelper.selectNode(previousSelection.getMessagePart());
//                    break;
//                case NONE :
//
//                }
//                
//                if (selected != null) {
//                    selectNode(selected);
//                    firePropertyChange(ElementOrTypeChooserPanel.PROP_ACTION_APPLY, false, true);
//                }
//            } else {
//                selectNode(mRootNode);
//            }

        }
     
      private void selectNode(Node node) {
            final Node finalNode = node;
            Runnable run = new Runnable() {
                public void run() {
                    if(manager != null) {
                        try {
                            manager.setExploredContextAndSelection(finalNode, new Node[] {finalNode});
                            btv.expandNode(finalNode);
                        } catch(PropertyVetoException ex) {
                            //ignore this
                        }

                    }
                }
            };
            SwingUtilities.invokeLater(run);
        }
    

    public ExplorerManager getExplorerManager() {
        return this.manager;
    }
    
     
}