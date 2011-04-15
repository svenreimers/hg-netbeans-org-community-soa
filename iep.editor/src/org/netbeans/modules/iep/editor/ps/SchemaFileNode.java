/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */

package org.netbeans.modules.iep.editor.ps;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTree;

import org.netbeans.modules.iep.editor.xsd.nodes.AbstractSchemaArtifactNode;
import org.netbeans.modules.iep.editor.xsd.nodes.AxiModelHelper;
import org.netbeans.modules.xml.axi.AXIModel;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;

/**
 *
 * @author radval
 */
public class SchemaFileNode extends AbstractSchemaArtifactNode {

    private Node mFileDelegateNode;
    
    private List<String> mExistingArtificatNames = new ArrayList<String>();
    
    private JTree mTree;
    
    private List<AbstractSchemaArtifactNode> mNodesToBeExpanded = new ArrayList<AbstractSchemaArtifactNode>();
    
    public SchemaFileNode(Node fileDelegate, 
            List<String> existingArtificatNames,
            JTree tree) {
        super(fileDelegate.getDisplayName());
        this.mFileDelegateNode = fileDelegate;
        this.mTree = tree;
        //this.setUserObject(projectDelegate.getDisplayName());
        
        this.mIcon = new ImageIcon(this.mFileDelegateNode.getIcon(BeanInfo.ICON_COLOR_16x16)); 
        this.mExistingArtificatNames = existingArtificatNames;
        
        populateSchemaFiles();
        
        
    }
    
   
    private void populateSchemaFiles() {
        try {
            DataObject dObject = this.mFileDelegateNode.getLookup().lookup(DataObject.class);
            if(dObject != null) {
                FileObject fileObject = dObject.getPrimaryFile();
                AXIModel model = AxiModelHelper.getAXIModel(fileObject);
                SchemaAxiTreeNodeVisitor mVisitor = new SchemaAxiTreeNodeVisitor(this, mExistingArtificatNames, mTree);
                model.getRoot().accept(mVisitor);
                mNodesToBeExpanded = mVisitor.getNodesToBeExpanded();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<AbstractSchemaArtifactNode> getNodesToBeExpanded() {
        return this.mNodesToBeExpanded;
    }
}
