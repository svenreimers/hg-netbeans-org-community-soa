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
package org.netbeans.modules.xslt.tmap.multiview.tree;

import org.netbeans.modules.xslt.tmap.navigator.TMapLogicalPanel;
import org.netbeans.modules.xslt.tmap.nodes.LogicalTreeHandler;
import org.openide.explorer.view.BeanTreeView;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 * 
 */
public class TreeMultiViewVisualPanel extends TMapLogicalPanel {

    public TreeMultiViewVisualPanel() {
        super();
    }
    
    @Override
    protected BeanTreeView getBeanTreeView() {
        boolean isInited = initNavTree();
        if (!isInited) {
            return null;
        }
        
        BeanTreeView beanTree = null;
        if (myTreeHandler != null) {
            myTreeHandler.removeListeners();
            myTreeHandler = null;
        }
        
        myTreeHandler = new MultiviewTreeHandler(
                getExplorerManager(),
                getModel(),
                getContextLookup());
                
        beanTree = myTreeHandler.getBeanTreeView();
        beanTree.getAccessibleContext().setAccessibleName(getAccessibleContext().getAccessibleName());
        beanTree.getAccessibleContext().setAccessibleDescription(getAccessibleContext().getAccessibleDescription());
        return beanTree;
    }
    
    private LogicalTreeHandler myTreeHandler;
}
