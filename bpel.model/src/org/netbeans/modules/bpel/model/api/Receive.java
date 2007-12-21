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
 * Java class for tReceive complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 *   &lt;xsd:element name="receive" type="tReceive"/>
 *   &lt;xsd:complexType name="tReceive">
 *       &lt;xsd:complexContent>
 *           &lt;xsd:extension base="tActivity">
 *               &lt;xsd:sequence>
 *                   &lt;xsd:element name="correlations" type="tCorrelations" minOccurs="0"/>
 *                   &lt;xsd:element ref="fromParts" minOccurs="0"/>
 *               &lt;/xsd:sequence>
 *               &lt;xsd:attribute name="partnerLink" type="xsd:NCName" use="required"/>
 *               &lt;xsd:attribute name="portType" type="xsd:QName" use="optional"/>
 *               &lt;xsd:attribute name="operation" type="xsd:NCName" use="required"/>
 *               &lt;xsd:attribute name="variable" type="BPELVariableName" use="optional"/>
 *               &lt;xsd:attribute name="createInstance" type="tBoolean" default="no"/>
 *               &lt;xsd:attribute name="messageExchange" type="xsd:NCName" use="optional"/>
 *           &lt;/xsd:extension>
 *       &lt;/xsd:complexContent>
 *   &lt;/xsd:complexType>
 * </pre>
 */
public interface Receive extends Activity, CorrelationsHolder,
        OperationReference, VariableReference, PortTypeReference,
        CreateInstanceActivity, PartnerLinkReference, FromPartsHolder,
        MessageExchangeReference, Responder
{

}