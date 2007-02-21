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
package org.netbeans.modules.compapp.casaeditor.model.casa;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;

/**
 *
 * @author jqian
 */
public enum XlinkAttributeQName {
    HREF("href"),
    TYPE("type");
    
    public static final String XLINK_NS_URI = "http://www.w3.org/2000/xlink";
    public static final String XLINK_NS_PREFIX = "xlink";
        
    private QName qname;
    
    XlinkAttributeQName(String localName) {
        qname = new QName(XLINK_NS_URI, localName, XLINK_NS_PREFIX);
    }
    
    public QName getQName() {
        return qname;
    }
    
    public String getLocalName() {
        return qname.getLocalPart();
    }
    
    public String getQualifiedName() {
        return qname.getPrefix() + ":" + qname.getLocalPart();
    }    
}
