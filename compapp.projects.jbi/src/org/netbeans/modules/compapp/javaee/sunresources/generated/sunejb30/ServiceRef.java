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
// Generated on: 2006.12.09 at 06:25:55 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.sunejb30;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceRefName",
    "portInfo",
    "callProperty",
    "wsdlOverride",
    "serviceImplClass",
    "serviceQname"
})
@XmlRootElement(name = "service-ref")
public class ServiceRef {

    @XmlElement(name = "service-ref-name", required = true)
    protected String serviceRefName;
    @XmlElement(name = "port-info")
    protected List<PortInfo> portInfo;
    @XmlElement(name = "call-property")
    protected List<CallProperty> callProperty;
    @XmlElement(name = "wsdl-override")
    protected String wsdlOverride;
    @XmlElement(name = "service-impl-class")
    protected String serviceImplClass;
    @XmlElement(name = "service-qname")
    protected ServiceQname serviceQname;

    /**
     * Gets the value of the serviceRefName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRefName() {
        return serviceRefName;
    }

    /**
     * Sets the value of the serviceRefName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRefName(String value) {
        this.serviceRefName = value;
    }

    /**
     * Gets the value of the portInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortInfo }
     * 
     * 
     */
    public List<PortInfo> getPortInfo() {
        if (portInfo == null) {
            portInfo = new ArrayList<PortInfo>();
        }
        return this.portInfo;
    }

    /**
     * Gets the value of the callProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the callProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCallProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CallProperty }
     * 
     * 
     */
    public List<CallProperty> getCallProperty() {
        if (callProperty == null) {
            callProperty = new ArrayList<CallProperty>();
        }
        return this.callProperty;
    }

    /**
     * Gets the value of the wsdlOverride property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlOverride() {
        return wsdlOverride;
    }

    /**
     * Sets the value of the wsdlOverride property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlOverride(String value) {
        this.wsdlOverride = value;
    }

    /**
     * Gets the value of the serviceImplClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceImplClass() {
        return serviceImplClass;
    }

    /**
     * Sets the value of the serviceImplClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceImplClass(String value) {
        this.serviceImplClass = value;
    }

    /**
     * Gets the value of the serviceQname property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceQname }
     *     
     */
    public ServiceQname getServiceQname() {
        return serviceQname;
    }

    /**
     * Sets the value of the serviceQname property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceQname }
     *     
     */
    public void setServiceQname(ServiceQname value) {
        this.serviceQname = value;
    }

}
