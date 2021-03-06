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

package org.netbeans.modules.encoder.custom.aip;

import java.beans.PropertyEditorSupport;

/**
 * The property editor for the node order property.
 *
 * @author Jun Xu
 */
public class OrderPropertyEditor extends PropertyEditorSupport {
    
    /** Creates a new instance of OrderPropertyEditor */
    public OrderPropertyEditor() {
    }
    
    @Override
    public String[] getTags() {
        return EncodingOption.orderTagList().toArray(new String[0]);
    }

    @Override
    public String getAsText() {
        return (String) getValue();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text);
    }

    @Override
    public String getJavaInitializationString() {
        String value = (String) getValue();
        if (value == null) {
            return "null";  //NOI18N
        }
        return "\"" + value + "\"";  //NOI18N
    }
}
