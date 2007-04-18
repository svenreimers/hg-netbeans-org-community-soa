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

import java.util.ArrayList;
import java.util.List;
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
 * 	The service-interface-mapping element defines how a Java type for
 * 	the service interface maps to a WSDL service.
 * 
 * 	Used in: java-wsdl-mapping
 * 
 *       
 * 
 * <p>Java class for service-interface-mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="service-interface-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service-interface" type="{http://java.sun.com/xml/ns/j2ee}fully-qualified-classType"/>
 *         &lt;element name="wsdl-service-name" type="{http://java.sun.com/xml/ns/j2ee}xsdQNameType"/>
 *         &lt;element name="port-mapping" type="{http://java.sun.com/xml/ns/j2ee}port-mappingType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "service-interface-mappingType", propOrder = {
    "serviceInterface",
    "wsdlServiceName",
    "portMapping"
})
public class ServiceInterfaceMappingType {

    @XmlElement(name = "service-interface", required = true)
    protected FullyQualifiedClassType serviceInterface;
    @XmlElement(name = "wsdl-service-name", required = true)
    protected XsdQNameType wsdlServiceName;
    @XmlElement(name = "port-mapping")
    protected List<PortMappingType> portMapping;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the serviceInterface property.
     * 
     * @return
     *     possible object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public FullyQualifiedClassType getServiceInterface() {
        return serviceInterface;
    }

    /**
     * Sets the value of the serviceInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public void setServiceInterface(FullyQualifiedClassType value) {
        this.serviceInterface = value;
    }

    /**
     * Gets the value of the wsdlServiceName property.
     * 
     * @return
     *     possible object is
     *     {@link XsdQNameType }
     *     
     */
    public XsdQNameType getWsdlServiceName() {
        return wsdlServiceName;
    }

    /**
     * Sets the value of the wsdlServiceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link XsdQNameType }
     *     
     */
    public void setWsdlServiceName(XsdQNameType value) {
        this.wsdlServiceName = value;
    }

    /**
     * Gets the value of the portMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortMappingType }
     * 
     * 
     */
    public List<PortMappingType> getPortMapping() {
        if (portMapping == null) {
            portMapping = new ArrayList<PortMappingType>();
        }
        return this.portMapping;
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
