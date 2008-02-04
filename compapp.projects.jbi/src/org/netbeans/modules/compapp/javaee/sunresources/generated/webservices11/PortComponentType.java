/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.09 at 06:26:00 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11;

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
 * 	The port-component element associates a WSDL port with a web service
 * 	interface and implementation.  It defines the name of the port as a
 * 	component, optional description, optional display name, optional iconic
 * 	representations, WSDL port QName, Service Endpoint Interface, Service
 * 	Implementation Bean.
 * 
 *       
 * 
 * <p>Java class for port-componentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="port-componentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://java.sun.com/xml/ns/j2ee}descriptionType" minOccurs="0"/>
 *         &lt;element name="display-name" type="{http://java.sun.com/xml/ns/j2ee}display-nameType" minOccurs="0"/>
 *         &lt;element name="icon" type="{http://java.sun.com/xml/ns/j2ee}iconType" minOccurs="0"/>
 *         &lt;element name="port-component-name" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;element name="wsdl-port" type="{http://java.sun.com/xml/ns/j2ee}xsdQNameType"/>
 *         &lt;element name="service-endpoint-interface" type="{http://java.sun.com/xml/ns/j2ee}fully-qualified-classType"/>
 *         &lt;element name="service-impl-bean" type="{http://java.sun.com/xml/ns/j2ee}service-impl-beanType"/>
 *         &lt;element name="handler" type="{http://java.sun.com/xml/ns/j2ee}port-component_handlerType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "port-componentType", propOrder = {
    "description",
    "displayName",
    "icon",
    "portComponentName",
    "wsdlPort",
    "serviceEndpointInterface",
    "serviceImplBean",
    "handler"
})
public class PortComponentType {

    protected DescriptionType description;
    @XmlElement(name = "display-name")
    protected DisplayNameType displayName;
    protected IconType icon;
    @XmlElement(name = "port-component-name", required = true)
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String portComponentName;
    @XmlElement(name = "wsdl-port", required = true)
    protected XsdQNameType wsdlPort;
    @XmlElement(name = "service-endpoint-interface", required = true)
    protected FullyQualifiedClassType serviceEndpointInterface;
    @XmlElement(name = "service-impl-bean", required = true)
    protected ServiceImplBeanType serviceImplBean;
    protected List<PortComponentHandlerType> handler;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptionType }
     *     
     */
    public DescriptionType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptionType }
     *     
     */
    public void setDescription(DescriptionType value) {
        this.description = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link DisplayNameType }
     *     
     */
    public DisplayNameType getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisplayNameType }
     *     
     */
    public void setDisplayName(DisplayNameType value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link IconType }
     *     
     */
    public IconType getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link IconType }
     *     
     */
    public void setIcon(IconType value) {
        this.icon = value;
    }

    /**
     * Gets the value of the portComponentName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String getPortComponentName() {
        return portComponentName;
    }

    /**
     * Sets the value of the portComponentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String }
     *     
     */
    public void setPortComponentName(org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String value) {
        this.portComponentName = value;
    }

    /**
     * Gets the value of the wsdlPort property.
     * 
     * @return
     *     possible object is
     *     {@link XsdQNameType }
     *     
     */
    public XsdQNameType getWsdlPort() {
        return wsdlPort;
    }

    /**
     * Sets the value of the wsdlPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link XsdQNameType }
     *     
     */
    public void setWsdlPort(XsdQNameType value) {
        this.wsdlPort = value;
    }

    /**
     * Gets the value of the serviceEndpointInterface property.
     * 
     * @return
     *     possible object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public FullyQualifiedClassType getServiceEndpointInterface() {
        return serviceEndpointInterface;
    }

    /**
     * Sets the value of the serviceEndpointInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public void setServiceEndpointInterface(FullyQualifiedClassType value) {
        this.serviceEndpointInterface = value;
    }

    /**
     * Gets the value of the serviceImplBean property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceImplBeanType }
     *     
     */
    public ServiceImplBeanType getServiceImplBean() {
        return serviceImplBean;
    }

    /**
     * Sets the value of the serviceImplBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceImplBeanType }
     *     
     */
    public void setServiceImplBean(ServiceImplBeanType value) {
        this.serviceImplBean = value;
    }

    /**
     * Gets the value of the handler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the handler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHandler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortComponentHandlerType }
     * 
     * 
     */
    public List<PortComponentHandlerType> getHandler() {
        if (handler == null) {
            handler = new ArrayList<PortComponentHandlerType>();
        }
        return this.handler;
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