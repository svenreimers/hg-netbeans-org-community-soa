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
package org.netbeans.modules.soa.xpath.mapper.model;

import javax.swing.tree.TreePath;
import org.netbeans.modules.soa.mappercore.model.Graph;
import org.netbeans.modules.soa.mappercore.model.Link;
import org.netbeans.modules.soa.mappercore.model.SourcePin;
import org.netbeans.modules.soa.mappercore.model.TargetPin;
import org.netbeans.modules.soa.mappercore.model.TreeSourcePin;
import org.netbeans.modules.soa.mappercore.model.Vertex;
import org.netbeans.modules.soa.mappercore.model.VertexItem;
import org.netbeans.modules.soa.xpath.mapper.tree.MapperSwingTreeModel;
import org.netbeans.modules.soa.xpath.mapper.utils.XPathMapperUtils;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 */
public interface ConnectionConstraint {
    boolean canConnect(TreePath treePath, SourcePin source, 
            TargetPin target, TreePath oldTreePath, Link oldLink);
    
    public class GeneralConstraint implements ConnectionConstraint {

        private XPathMapperModel myModel;
        
        public GeneralConstraint(XPathMapperModel model) {
            myModel = model;
        }

        public boolean canConnect(TreePath treePath, SourcePin source,
                                  TargetPin target, TreePath oldTreePath,
                                  Link oldLink) 
        {
            assert myModel != null;
            MapperSwingTreeModel rModel = myModel.getRightTreeModel();
            MapperSwingTreeModel lModel = myModel.getLeftTreeModel();
            if (rModel == null || lModel == null) {
                return false;
            }
            

        if (oldTreePath != null && !oldTreePath.equals(treePath)) {
            // Reconnect
            // link to another graph is not allowed for a while
            if (!(oldLink.getSource() instanceof TreeSourcePin)) {
                return false;
            }
        }
        
        boolean result = true;
       
        if (target instanceof Graph) {
            if (!rModel.isConnectable(treePath)) {
                result = false;
            }
            //
            if (((Graph) target).hasOutgoingLinks()) {
                // The target tree node already has a connected link
                result =((Graph) target).getOutgoingLink() == oldLink ;
            }
       
        }
        SourcePin oldSource = null;
        TargetPin oldTarget = null;
         if (oldLink != null) {
            oldSource = oldLink.getSource();
            oldTarget = oldLink.getTarget();
            oldLink.setSource(null);
            oldLink.setTarget(null);
        }
        //
        if (source instanceof TreeSourcePin) {
            TreePath sourceTreePath = ((TreeSourcePin) source).getTreePath();
            if (!lModel.isConnectable(sourceTreePath)) {
                result = false;
            }
        }
        //
        // Check there is only one outgoing link
        if (source instanceof Vertex) {
            Link outgoingLink = ((Vertex) source).getOutgoingLink();
            if (outgoingLink != null) {
                result = false;
            }
        }
        
        //
        if (target instanceof VertexItem) {
            // Check the item doesn't have incoming link yet
            Link ingoingLink = ((VertexItem) target).getIngoingLink();
            if (ingoingLink != null) {
                result = false;
            }
            //
            // Check connection 2 vertexes 
            if (source instanceof Vertex) {
                //
                // Trying connect the vertex to itself isn't allowed
                Vertex targetVertex = ((VertexItem) target).getVertex();
                if (targetVertex == source) {
                    result = false;
                }
                // Check cyclic dependences
                if (XPathMapperUtils.areVertexDependent((Vertex) source, targetVertex)) {
                    result = false;
                }
            }
        }
        //
        if (oldLink != null) {
            oldLink.setSource(oldSource);
            oldLink.setTarget(oldTarget);
        }
        return result;
        }
    }
    
}
