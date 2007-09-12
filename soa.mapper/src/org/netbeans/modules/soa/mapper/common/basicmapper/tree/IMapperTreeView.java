/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package org.netbeans.modules.soa.mapper.common.basicmapper.tree;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.netbeans.modules.soa.mapper.common.basicmapper.IBasicMapperView;

/**
 * <p>
 *
 * Title: </p> IMapperTreeView <p>
 *
 * Description: </p> IMapperTreeView provides a functioalities for a java tree
 * as the mapper view. <p>
 *
 * @author    Un Seng Leong
 * @created   December 23, 2002
 */

public interface IMapperTreeView
     extends IBasicMapperView {
    /**
     * Return true if this tree view contains the specified tree node, false
     * otherwise.
     *
     * @param node  the specifed tree node to find
     * @return      true if this tree view contains the specified tree node,
     *      false otherwise.
     */
    public boolean contains(IMapperTreeNode node);

    /**
     * Return the mapper tree node of this view that contains the specified tree
     * path.
     *
     * @param treePath  the tree path to be store in the mapper tree node.
     * @return          the mapper tree node of this view that contains the
     *      specified tree path.
     */
    public IMapperTreeNode getMapperTreeNode(TreePath treePath);

    /**
     * Return a new mapper tree node of this view that contains the specified
     * tree path.
     *
     * @param treePath  the tree path to be store in the mapper tree node.
     * @return          a new mapper tree node of this view that contains the
     *      specified tree path.
     * @deprecated      createMapperTreeNode should no longer be called due the
     *      cache issue of the tree. use getMapperTreeNode(TreePath) to get and
     *      create a IMapperTreeNode. Or use IBasicMapper.createMapperTreeNode.
     */
    public IMapperTreeNode createMapperTreeNode(TreePath treePath);

    /**
     * Return the java tree component that this mapper tree repersenting.
     *
     * @return   the java tree component that this mapper tree repersenting.
     */
    public JTree getTree();
    
    /**
     * Return the bounding rectangle of the specified tree path.
     */
    public Rectangle getShowingPathRectBound(TreePath path);
    
    /**
     * Gets the location of where the node point would be for the bounding rectangle.
     */
    public Point getTreeNodePoint(Rectangle pathRect);
    
    /**
     * Gets the view offset for the tree. For example, if this tree is in a scroller,
     * then the x and y offset of the view is returned.
     */
    public Point getViewOffset();
}
