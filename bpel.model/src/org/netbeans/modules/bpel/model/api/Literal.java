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

/**
 *
 */
package org.netbeans.modules.bpel.model.api;

/**
 * @author ads *
 *         <p>
 *         Java class for anonymous complex type.
 *         <p>
 *         The following schema fragment specifies the expected content
 *         contained within this class.
 *
 * <pre>
 *   &lt;xsd:element name="literal" type="tLiteral"/>
 *   &lt;xsd:complexType name="tLiteral" mixed="true">
 *       &lt;xsd:sequence>
 *           &lt;xsd:any namespace="##any" processContents="lax" minOccurs="0" maxOccurs="1"/>
 *       &lt;/xsd:sequence>
 *   &lt;/xsd:complexType>
 * </pre>
 */
public interface Literal extends BpelContainer, ContentElement, XmlContentElement, 
        CDataContentElement, FromChild 
{

    public enum LiteralForm {
        SUBELEMENT,
        TEXT_CONTENT,
        CDATA_SUBELEMENT,
        EMPTY;
    };
}
