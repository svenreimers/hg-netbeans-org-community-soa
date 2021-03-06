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

import org.netbeans.modules.xslt.tmap.model.api.Transform;
import org.netbeans.modules.xslt.tmap.model.api.Variable;
import org.netbeans.modules.xslt.tmap.model.api.VariableReference;
import org.netbeans.modules.xslt.tmap.util.Util;
import org.openide.util.NbBundle;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 */
public class DecoratedTransform  extends DecoratedTMapComponentAbstract<Transform> {

    public DecoratedTransform(Transform orig) {
        super(orig);
    }
    

    @Override
    public String getHtmlDisplayName() {
        Transform ref = getReference();
        String file = ref == null ? null : ref.getFile();

        String addon = null;
        if (file != null) {
            addon = TMapComponentNode.WHITE_SPACE+file; // NOI18N
        }
        
        return Util.getGrayString(super.getHtmlDisplayName(), addon);
    }
    
    @Override
    public String getTooltip() {
        Transform ref = getReference();
        StringBuffer attributesTooltip = new StringBuffer();
        if (ref != null) {
            attributesTooltip.append(
                    Util.getLocalizedAttribute(ref.getFile()
                    , Transform.FILE));
            
            attributesTooltip.append(
                    Util.getLocalizedAttribute(ref.getSource()
                    , Transform.SOURCE));

            attributesTooltip.append(
                    Util.getLocalizedAttribute(ref.getResult()
                    , Transform.RESULT));
        }

        return NbBundle.getMessage(TMapComponentNode.class, 
                "LBL_LONG_TOOLTIP_HTML_TEMPLATE", super.getName(), 
                attributesTooltip.toString());    
    }
    
}

