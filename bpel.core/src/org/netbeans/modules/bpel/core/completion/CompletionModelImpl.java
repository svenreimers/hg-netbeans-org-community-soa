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
package org.netbeans.modules.bpel.core.completion;

import org.netbeans.modules.xml.schema.completion.spi.CompletionModelProvider.CompletionModel;
import org.netbeans.modules.xml.schema.model.Schema;
import org.netbeans.modules.xml.schema.model.SchemaModel;

/**
 *
 * @author ads
 */
class CompletionModelImpl extends CompletionModel {
    
    CompletionModelImpl(SchemaModel model) {
        myModel = model;
    }
    
    public String getSuggestedPrefix() {
        return "bpel";                               // NOI18N
    }
    
    public SchemaModel getSchemaModel() {
        return myModel;
    }
    
    public String getTargetNamespace() {
        Schema schema = myModel.getSchema();
        if (schema != null) {
            return schema.getTargetNamespace();
        }
        return null;
    }
    
    private SchemaModel myModel;
}
