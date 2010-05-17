/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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
 * 	Declares the handler for a port-component. Handlers can access the
 * 	init-param name/value pairs using the HandlerInfo interface. If
 * 	port-name is not specified, the handler is assumed to be associated
 * 	with all ports of the service.
 * 
 * 	Used in: service-ref
 * 
 *       
 * 
 * <p>Java class for service-ref_handlerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="service-ref_handlerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://java.sun.com/xml/ns/j2ee}descriptionGroup"/>
 *         &lt;element name="handler-name" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;element name="handler-class" type="{http://java.sun.com/xml/ns/j2ee}fully-qualified-classType"/>
 *         &lt;element name="init-param" type="{http://java.sun.com/xml/ns/j2ee}param-valueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="soap-header" type="{http://java.sun.com/xml/ns/j2ee}xsdQNameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="soap-role" type="{http://java.sun.com/xml/ns/j2ee}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="port-name" type="{http://java.sun.com/xml/ns/j2ee}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "service-ref_handlerType", propOrder = {
    "description",
    "displayName",
    "icon",
    "handlerName",
    "handlerClass",
    "initParam",
    "soapHeader",
    "soapRole",
    "portName"
})
public class ServiceRefHandlerType {

    protected List<DescriptionType> description;
    @XmlElement(name = "display-name")
    protected List<DisplayNameType> displayName;
    protected List<IconType> icon;
    @XmlElement(name = "handler-name", required = true)
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String handlerName;
    @XmlElement(name = "handler-class", required = true)
    protected FullyQualifiedClassType handlerClass;
    @XmlElement(name = "init-param")
    protected List<ParamValueType> initParam;
    @XmlElement(name = "soap-header")
    protected List<XsdQNameType> soapHeader;
    @XmlElement(name = "soap-role")
    protected List<org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String> soapRole;
    @XmlElement(name = "port-name")
    protected List<org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String> portName;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType }
     * 
     * 
     */
    public List<DescriptionType> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptionType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisplayNameType }
     * 
     * 
     */
    public List<DisplayNameType> getDisplayName() {
        if (displayName == null) {
            displayName = new ArrayList<DisplayNameType>();
        }
        return this.displayName;
    }

    /**
     * Gets the value of the icon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the icon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIcon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IconType }
     * 
     * 
     */
    public List<IconType> getIcon() {
        if (icon == null) {
            icon = new ArrayList<IconType>();
        }
        return this.icon;
    }

    /**
     * Gets the value of the handlerName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String getHandlerName() {
        return handlerName;
    }

    /**
     * Sets the value of the handlerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String }
     *     
     */
    public void setHandlerName(org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String value) {
        this.handlerName = value;
    }

    /**
     * Gets the value of the handlerClass property.
     * 
     * @return
     *     possible object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public FullyQualifiedClassType getHandlerClass() {
        return handlerClass;
    }

    /**
     * Sets the value of the handlerClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public void setHandlerClass(FullyQualifiedClassType value) {
        this.handlerClass = value;
    }

    /**
     * Gets the value of the initParam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the initParam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInitParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParamValueType }
     * 
     * 
     */
    public List<ParamValueType> getInitParam() {
        if (initParam == null) {
            initParam = new ArrayList<ParamValueType>();
        }
        return this.initParam;
    }

    /**
     * Gets the value of the soapHeader property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soapHeader property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoapHeader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XsdQNameType }
     * 
     * 
     */
    public List<XsdQNameType> getSoapHeader() {
        if (soapHeader == null) {
            soapHeader = new ArrayList<XsdQNameType>();
        }
        return this.soapHeader;
    }

    /**
     * Gets the value of the soapRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soapRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoapRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String }
     * 
     * 
     */
    public List<org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String> getSoapRole() {
        if (soapRole == null) {
            soapRole = new ArrayList<org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String>();
        }
        return this.soapRole;
    }

    /**
     * Gets the value of the portName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String }
     * 
     * 
     */
    public List<org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String> getPortName() {
        if (portName == null) {
            portName = new ArrayList<org.netbeans.modules.compapp.javaee.sunresources.generated.webservices11.String>();
        }
        return this.portName;
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
