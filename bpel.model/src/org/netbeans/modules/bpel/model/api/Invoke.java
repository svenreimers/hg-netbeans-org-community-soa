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

import org.netbeans.modules.bpel.model.api.references.BpelReference;

/**
 * <p>
 * Java class for tInvoke complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 *   &lt;xsd:element name="invoke" type="tInvoke"/>
 *   &lt;xsd:complexType name="tInvoke">
 *       &lt;xsd:annotation>
 *           &lt;xsd:documentation>
 *               XSD Authors: The child element correlations needs to be a Local Element Declaration, 
 *               because there is another correlations element defined for the non-invoke activities.
 *           &lt;/xsd:documentation>
 *       &lt;/xsd:annotation>
 *       &lt;xsd:complexContent>
 *           &lt;xsd:extension base="tActivity">
 *               &lt;xsd:sequence>
 *                   &lt;xsd:element name="correlations" type="tCorrelationsWithPattern" minOccurs="0"/>
 *                   &lt;xsd:element ref="catch" minOccurs="0" maxOccurs="unbounded"/>
 *                   &lt;xsd:element ref="catchAll" minOccurs="0"/>
 *                   &lt;xsd:element ref="compensationHandler" minOccurs="0"/>
 *                   &lt;xsd:element ref="toParts" minOccurs="0"/>
 *                   &lt;xsd:element ref="fromParts" minOccurs="0"/>
 *               &lt;/xsd:sequence>
 *               &lt;xsd:attribute name="partnerLink" type="xsd:NCName" use="required"/>
 *               &lt;xsd:attribute name="portType" type="xsd:QName" use="optional"/>
 *               &lt;xsd:attribute name="operation" type="xsd:NCName" use="required"/>
 *               &lt;xsd:attribute name="inputVariable" type="BPELVariableName" use="optional"/>
 *               &lt;xsd:attribute name="outputVariable" type="BPELVariableName" use="optional"/>
 *           &lt;/xsd:extension>
 *       &lt;/xsd:complexContent>
 *   &lt;/xsd:complexType>
 * </pre>
 */
public interface Invoke extends Activity, BaseFaultHandlers, 
        OperationReference , PortTypeReference, CompensationHandlerHolder, 
        PartnerLinkReference, ToPartsHolder, FromPartsHolder, 
        Responder, Requester
{

    /**
     * inputVariable attribute name.
     */
    String INPUT_VARIABLE = "inputVariable";        // NOI18N

    /**
     * outputVariable attribute name.
     */
    String OUTPUT_VARIABLE = "outputVariable";      // NOI18N

    /**
     * Gets the value of the correlations property.
     * 
     * @return possible object is {@link PatternedCorrelationContainer }
     */
    PatternedCorrelationContainer getPatternedCorrelationContainer();

    /**
     * Sets the value of the correlations property.
     * 
     * @param value
     *            allowed object is {@link PatternedCorrelationContainer }
     */
    void setPatternedCorrelationContainer( PatternedCorrelationContainer value );

    /**
     * Removes PatternedCorrelationContainer.
     */
    void removePatternedCorrelationContainer();

    /**
     * Gets the value of the "inputVariable" property.
     * 
     * @return possible object is VariableReference.
     */
    BpelReference<VariableDeclaration> getInputVariable();

    /**
     * Sets the value of the inputVariable property.
     * 
     * @param value
     *            allowed object is VariableReference.
     */
    void setInputVariable( BpelReference<VariableDeclaration> value );

    /**
     * Removes inputVariable attribute.
     */
    void removeInputVariable();

    /**
     * Gets the value of the outputVariable property.
     * 
     * @return possible object is VariableReference.
     */
    BpelReference<VariableDeclaration> getOutputVariable();

    /**
     * Sets the value of the outputVariable property.
     * 
     * @param value
     *            allowed object is VariableReference.
     */
    void setOutputVariable( BpelReference<VariableDeclaration> value );

    /**
     * Removes outputVariable attribute.
     */
    void removeOutputVariable();

}