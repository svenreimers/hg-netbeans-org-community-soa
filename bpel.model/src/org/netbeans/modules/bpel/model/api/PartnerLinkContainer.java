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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.0-06/22/2005 01:29 PM(ryans)-EA2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2005.09.05 at 07:05:33 PM MSD
//
package org.netbeans.modules.bpel.model.api;

/**
 * <p>
 * Java class for tPartnerLinks complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 *   &lt;xsd:element name="partnerLinks" type="tPartnerLinks"/>
 *   &lt;xsd:complexType name="tPartnerLinks">
 *       &lt;xsd:complexContent>
 *           &lt;xsd:extension base="tExtensibleElements">
 *               &lt;xsd:sequence>
 *                   &lt;xsd:element ref="partnerLink" minOccurs="1" maxOccurs="unbounded"/>
 *               &lt;/xsd:sequence>
 *           &lt;/xsd:extension>
 *       &lt;/xsd:complexContent>
 *   &lt;/xsd:complexType>
 * </pre>
 */
public interface PartnerLinkContainer extends ExtensibleElements, BpelContainer
{

    /**
     * @return array of partnerLink children.
     */
    PartnerLink[] getPartnerLinks();

    /**
     * @param i
     *            index
     * @return ith partnerLink.
     */
    PartnerLink getPartnerLink( int i );

    /**
     * Set ith partnerLink to <code>link</code>.
     * 
     * @param link
     *            object for set.
     * @param i
     *            index
     */
    void setPartnerLink( PartnerLink link, int i );

    /**
     * Set new array of partnerLinks.
     * 
     * @param links
     *            array for set.
     */
    void setPartnerLinks( PartnerLink[] links );

    /**
     * Add partnerLink <code>link</code>.
     * 
     * @param link
     *            object for add.
     */
    void addPartnerLink( PartnerLink link );

    /**
     * Insert parnterLink <code>link</code> to the ith place.
     * 
     * @param link
     *            object for insert.
     * @param i
     *            index.
     */
    void insertPartnerLink( PartnerLink link, int i );

    /**
     * Removes ith partnerLink.
     * 
     * @param i
     *            index.
     */
    void removePartnerLink( int i );

    /**
     * @return size of partnerLink children.
     */
    int sizeOfPartnerLink();
}
