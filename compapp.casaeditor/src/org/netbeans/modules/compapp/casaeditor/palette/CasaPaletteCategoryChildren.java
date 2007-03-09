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

package org.netbeans.modules.compapp.casaeditor.palette;

import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author rdara
 */
public class CasaPaletteCategoryChildren extends Children.Keys {
    
    private static CasaPalette.CASA_CATEGORY_TYPE[] CategoryTypes = new CasaPalette.CASA_CATEGORY_TYPE[] {
        CasaPalette.CASA_CATEGORY_TYPE.WSDL_BINDINGS,
        CasaPalette.CASA_CATEGORY_TYPE.SERVICE_UNITS,
        CasaPalette.CASA_CATEGORY_TYPE.END_POINTS};
    
    private Lookup mLookup;
    
    
    public CasaPaletteCategoryChildren(Lookup lookup) {
        mLookup = lookup;
    }
    
    
    protected Node[] createNodes(Object key) {
        CasaPaletteCategory obj = (CasaPaletteCategory) key;
        return new Node[] { new CasaPaletteCategoryNode(obj, mLookup) };
    }
    
    protected void addNotify() {
        super.addNotify();
        CasaPaletteCategory[] objs = new CasaPaletteCategory[CategoryTypes.length];
        for (int i = 0; i < objs.length; i++) {
            CasaPaletteCategory cat = new CasaPaletteCategory();
            cat.setCasaCategoryType(CategoryTypes[i]);
            objs[i] = cat;
        }
        setKeys(objs);
    }
}
