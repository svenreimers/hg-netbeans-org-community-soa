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
package org.netbeans.modules.xslt.model;


/**
 * <pre>
 * &lt;xs:element name="output-character">
 *      &lt;xs:complexType>
 *          &lt;xs:complexContent>
 *              &lt;xs:extension base="xsl:element-only-versioned-element-type">
 *                  &lt;xs:attribute name="character" type="xsl:char" use="required"/>
 *                  &lt;xs:attribute name="string" type="xs:string" use="required"/>
 *              &lt;/xs:extension>
 *          &lt;/xs:complexContent>
 *      &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 * @author ads
 *
 */
public interface OutputCharacter extends XslComponent {
    
    String CHARACTER = "character";     // NOI18N
    
    String STRING = "string";           // NOI18N
    
    /**
     * @return "string" attribute value
     */
    String getString();
    
    /**
     * Set "string" attribute value. 
     * @param string new value
     */
    void setString( String string );

}
