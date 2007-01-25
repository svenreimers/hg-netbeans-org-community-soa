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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.wsdlextensions.jms;

/**
 *
 * JMSAddress
 */
public interface JMSAddress extends JMSComponent {

    public static final String ATTR_CONNECTION_URL = "connectionURL";
    public static final String ATTR_USERNAME = "username";
    public static final String ATTR_PASSWORD = "password";


    public String getConnectionURL();
    public void setConnectionURL(String val);
    
    public String getUsername();
    public void setUsername(String val);

    public String getPassword();
    public void setPassword(String val);    
}
