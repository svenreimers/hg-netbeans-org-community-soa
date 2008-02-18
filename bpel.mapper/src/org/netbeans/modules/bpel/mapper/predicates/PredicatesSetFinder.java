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

package org.netbeans.modules.bpel.mapper.predicates;

import org.netbeans.modules.bpel.mapper.tree.search.SimpleFinder;
import org.netbeans.modules.xml.schema.model.SchemaComponent;

/**
 * It is intended to look for a set of predicated schema tree item. 
 * @author nk160297
 */
public class PredicatesSetFinder extends SimpleFinder {

    private SchemaComponent mSchemaComp;
    
    public PredicatesSetFinder(SchemaComponent schemaComp) {
        mSchemaComp = schemaComp;
    }
    
    protected boolean isFit(Object treeItem) {
        if (mSchemaComp == null) {
            return treeItem instanceof AbstractPredicate;
        } else {
            if (treeItem instanceof AbstractPredicate) {
                SchemaComponent sComp = 
                        ((AbstractPredicate)treeItem).getSComponent();
                return sComp.equals(mSchemaComp);
            }
        }
        //
        return false;
    }

    protected boolean drillDeeper(Object treeItem) {
        return false;
    }

}
