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
package org.netbeans.modules.bpel.nodes.refactoring;

import org.netbeans.modules.bpel.nodes.BpelNode;
import org.openide.nodes.Node;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 *
 */
public class UsageDetailNodeCreator extends RefactoringNodeCreatorAbstract {

    public UsageDetailNodeCreator() {
    }

    public boolean isSupported(UsageNodeType nodeType) {
        return nodeType == UsageNodeType.USAGE_DETAIL;
    }

    public Node create(UsageNodeType nodeType, Object reference) {
        Node node = getInternalNode(nodeType, reference);
        
        if (node != null) {
            node =  new UsageDetailNode((BpelNode) node);
        }
        
        return node;
    }
}
