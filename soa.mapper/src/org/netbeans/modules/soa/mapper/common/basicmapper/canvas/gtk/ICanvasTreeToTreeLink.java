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

package org.netbeans.modules.soa.mapper.common.basicmapper.canvas.gtk;

import org.netbeans.modules.soa.mapper.common.basicmapper.tree.IMapperTreeNode;

/**
 * <p>
 *
 * Title: </p> <p>
 *
 * Description: </p> ICanvasNodeToNodeLink describes the visual repersentation
 * of a canvas link that connects both ends to tree nodes. <p>
 *
 * @author    Un Seng Leong
 * @created   January 2, 2003
 */
public interface ICanvasTreeToTreeLink
     extends ICanvasMapperLink {
    /**
     * Return the tree node repersetns the start point of this link.
     *
     * @return   the tree node repersetns the start point of this link.
     */
    public IMapperTreeNode getSourceTreeAddress();

    /**
     * Return the tree node repersetns the end point of this link.
     *
     * @return   the tree node repersetns the end point of this link.
     */
    public IMapperTreeNode getDestTreeAddress();
}
