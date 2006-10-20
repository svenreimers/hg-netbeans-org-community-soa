/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.

 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.bpel.nodes;

import org.netbeans.modules.bpel.model.api.Compensate;
import org.netbeans.modules.bpel.design.nodes.DiagramExtInfo;
import org.netbeans.modules.bpel.properties.Constants;
import org.netbeans.modules.bpel.design.nodes.NodeType;
import org.netbeans.modules.bpel.model.api.CompensateScope;
import org.netbeans.modules.bpel.model.api.NamedElement;
import org.netbeans.modules.bpel.properties.Util;
import org.netbeans.modules.bpel.properties.props.PropertyUtils;
import org.openide.nodes.Sheet;
import static org.netbeans.modules.bpel.properties.PropertyType.*;
import org.openide.util.Lookup;

/**
 *
 * @author nk160297
 */
public class CompensateScopeNode extends DiagramBpelNode<CompensateScope, DiagramExtInfo> {
    
    public CompensateScopeNode(CompensateScope reference, Lookup lookup) {
        super(reference, lookup);
    }
    
    public NodeType getNodeType() {
        return NodeType.COMPENSATE_SCOPE;
    }
    
    protected Sheet createSheet() {
        Sheet sheet = super.createSheet();
        if (getReference() == null) {
            // The related object has been removed!
            return sheet;
        }
        //
        DiagramExtInfo diagramReference = getDiagramReference();
        //
        Sheet.Set mainPropertySet = 
                getPropertySet(sheet, Constants.PropertiesGroups.MAIN_SET);
        //
        PropertyUtils.registerAttributeProperty(this, mainPropertySet,
                NamedElement.NAME, NAME, "getName", "setName", null); // NOI18N
        //
        PropertyUtils.registerAttributeProperty(this, mainPropertySet,
                CompensateScope.TARGET, COMPENSATION_TARGET, 
                "getTarget", "setTarget", "removeTarget"); // NOI18N
        //
        return sheet;
    }

    protected String getImplHtmlDisplayName() {
        CompensateScope compensate = getReference();
        if (compensate == null) {
            return super.getImplHtmlDisplayName();
        }
        String result = "";
        
        return Util.getGrayString(super.getImplHtmlDisplayName(), result);
    }
}
