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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.09 at 06:26:06 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * 	The wsdl-message-mapping element defines the mapping to a
 * 	specific message and its part. Together they define uniquely
 * 	the mapping for a specific parameter. Parts within a message
 * 	context are uniquely identified with their names.
 * 
 * 	The parameter-mode is defined by the mapping to indicate
 * 	whether the mapping will be IN, OUT, or INOUT..  The presence
 * 	of the soap-header element indicates that the parameter is
 * 	mapped to a soap header only.  When absent, it means that the
 * 	wsdl-message is mapped to a Java parameter. The soap headers
 * 	are interpreted in the order they are provided in the mapping.
 * 
 * 	Used in: method-param-parts-mapping
 * 
 *       
 * 
 * <p>Java class for wsdl-message-mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsdl-message-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsdl-message" type="{http://java.sun.com/xml/ns/j2ee}wsdl-messageType"/>
 *         &lt;element name="wsdl-message-part-name" type="{http://java.sun.com/xml/ns/j2ee}wsdl-message-part-nameType"/>
 *         &lt;element name="parameter-mode" type="{http://java.sun.com/xml/ns/j2ee}parameter-modeType"/>
 *         &lt;element name="soap-header" type="{http://java.sun.com/xml/ns/j2ee}emptyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsdl-message-mappingType", propOrder = {
    "wsdlMessage",
    "wsdlMessagePartName",
    "parameterMode",
    "soapHeader"
})
public class WsdlMessageMappingType {

    @XmlElement(name = "wsdl-message", required = true)
    protected WsdlMessageType wsdlMessage;
    @XmlElement(name = "wsdl-message-part-name", required = true)
    protected WsdlMessagePartNameType wsdlMessagePartName;
    @XmlElement(name = "parameter-mode", required = true)
    protected ParameterModeType parameterMode;
    @XmlElement(name = "soap-header")
    protected EmptyType soapHeader;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the wsdlMessage property.
     * 
     * @return
     *     possible object is
     *     {@link WsdlMessageType }
     *     
     */
    public WsdlMessageType getWsdlMessage() {
        return wsdlMessage;
    }

    /**
     * Sets the value of the wsdlMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsdlMessageType }
     *     
     */
    public void setWsdlMessage(WsdlMessageType value) {
        this.wsdlMessage = value;
    }

    /**
     * Gets the value of the wsdlMessagePartName property.
     * 
     * @return
     *     possible object is
     *     {@link WsdlMessagePartNameType }
     *     
     */
    public WsdlMessagePartNameType getWsdlMessagePartName() {
        return wsdlMessagePartName;
    }

    /**
     * Sets the value of the wsdlMessagePartName property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsdlMessagePartNameType }
     *     
     */
    public void setWsdlMessagePartName(WsdlMessagePartNameType value) {
        this.wsdlMessagePartName = value;
    }

    /**
     * Gets the value of the parameterMode property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterModeType }
     *     
     */
    public ParameterModeType getParameterMode() {
        return parameterMode;
    }

    /**
     * Sets the value of the parameterMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterModeType }
     *     
     */
    public void setParameterMode(ParameterModeType value) {
        this.parameterMode = value;
    }

    /**
     * Gets the value of the soapHeader property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getSoapHeader() {
        return soapHeader;
    }

    /**
     * Sets the value of the soapHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setSoapHeader(EmptyType value) {
        this.soapHeader = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}
