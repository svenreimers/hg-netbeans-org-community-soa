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

package org.netbeans.modules.xslt.tmap.nodes;

import java.awt.Image;
import org.netbeans.modules.xslt.tmap.model.api.Operation;
import org.netbeans.modules.xslt.tmap.model.api.TMapComponent;
import org.openide.util.NbBundle;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 */
public class DecoratedVariablesContainer extends DecoratedTMapComponentAbstract<Operation>{

    private static final String VARIABLES = NbBundle.getMessage(TMapComponentNode.class, 
                "LBL_Variables"); // 
    
    public DecoratedVariablesContainer(Operation orig) {
        super(orig);
    }

    @Override
    public String getName() {
        return VARIABLES;
    }

    @Override
    public Image getIcon() {
        return NodeType.VARIABLES_CONTAINER.getImage();
    }

    @Override
    public String getTooltip() {
        return NbBundle.getMessage(TMapComponentNode.class, 
                "LBL_VariablesTooltip");
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        
        if (obj instanceof DecoratedTMapComponent) {
            Object objComponent = ((DecoratedTMapComponent)obj).getReference();
            TMapComponent origComponent = getReference();
            if (origComponent != null ) {
                return origComponent.equals(objComponent) && getClass().equals(obj.getClass());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        TMapComponent origComponent = getReference();
        return origComponent == null ? origComponent.hashCode()*getClass().hashCode() : super.hashCode();
    }
    
}
