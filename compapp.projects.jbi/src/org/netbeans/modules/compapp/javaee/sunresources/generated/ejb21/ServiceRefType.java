/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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
// Generated on: 2006.12.09 at 06:25:52 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21;

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
 * 	The service-ref element declares a reference to a Web
 * 	service. It contains optional description, display name and
 * 	icons, a declaration of the required Service interface,
 * 	an optional WSDL document location, an optional set
 * 	of JAX-RPC mappings, an optional QName for the service element,
 * 	an optional set of Service Endpoint Interfaces to be resolved
 * 	by the container to a WSDL port, and an optional set of handlers.
 * 
 *       
 * 
 * <p>Java class for service-refType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="service-refType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://java.sun.com/xml/ns/j2ee}descriptionGroup"/>
 *         &lt;element name="service-ref-name" type="{http://java.sun.com/xml/ns/j2ee}jndi-nameType"/>
 *         &lt;element name="service-interface" type="{http://java.sun.com/xml/ns/j2ee}fully-qualified-classType"/>
 *         &lt;element name="wsdl-file" type="{http://java.sun.com/xml/ns/j2ee}xsdAnyURIType" minOccurs="0"/>
 *         &lt;element name="jaxrpc-mapping-file" type="{http://java.sun.com/xml/ns/j2ee}pathType" minOccurs="0"/>
 *         &lt;element name="service-qname" type="{http://java.sun.com/xml/ns/j2ee}xsdQNameType" minOccurs="0"/>
 *         &lt;element name="port-component-ref" type="{http://java.sun.com/xml/ns/j2ee}port-component-refType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="handler" type="{http://java.sun.com/xml/ns/j2ee}service-ref_handlerType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "service-refType", propOrder = {
    "description",
    "displayName",
    "icon",
    "serviceRefName",
    "serviceInterface",
    "wsdlFile",
    "jaxrpcMappingFile",
    "serviceQname",
    "portComponentRef",
    "handler"
})
public class ServiceRefType {

    protected List<DescriptionType> description;
    @XmlElement(name = "display-name")
    protected List<DisplayNameType> displayName;
    protected List<IconType> icon;
    @XmlElement(name = "service-ref-name", required = true)
    protected JndiNameType serviceRefName;
    @XmlElement(name = "service-interface", required = true)
    protected FullyQualifiedClassType serviceInterface;
    @XmlElement(name = "wsdl-file")
    protected XsdAnyURIType wsdlFile;
    @XmlElement(name = "jaxrpc-mapping-file")
    protected PathType jaxrpcMappingFile;
    @XmlElement(name = "service-qname")
    protected XsdQNameType serviceQname;
    @XmlElement(name = "port-component-ref")
    protected List<PortComponentRefType> portComponentRef;
    protected List<ServiceRefHandlerType> handler;
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
     * Gets the value of the serviceRefName property.
     * 
     * @return
     *     possible object is
     *     {@link JndiNameType }
     *     
     */
    public JndiNameType getServiceRefName() {
        return serviceRefName;
    }

    /**
     * Sets the value of the serviceRefName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JndiNameType }
     *     
     */
    public void setServiceRefName(JndiNameType value) {
        this.serviceRefName = value;
    }

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
     * Gets the value of the wsdlFile property.
     * 
     * @return
     *     possible object is
     *     {@link XsdAnyURIType }
     *     
     */
    public XsdAnyURIType getWsdlFile() {
        return wsdlFile;
    }

    /**
     * Sets the value of the wsdlFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link XsdAnyURIType }
     *     
     */
    public void setWsdlFile(XsdAnyURIType value) {
        this.wsdlFile = value;
    }

    /**
     * Gets the value of the jaxrpcMappingFile property.
     * 
     * @return
     *     possible object is
     *     {@link PathType }
     *     
     */
    public PathType getJaxrpcMappingFile() {
        return jaxrpcMappingFile;
    }

    /**
     * Sets the value of the jaxrpcMappingFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType }
     *     
     */
    public void setJaxrpcMappingFile(PathType value) {
        this.jaxrpcMappingFile = value;
    }

    /**
     * Gets the value of the serviceQname property.
     * 
     * @return
     *     possible object is
     *     {@link XsdQNameType }
     *     
     */
    public XsdQNameType getServiceQname() {
        return serviceQname;
    }

    /**
     * Sets the value of the serviceQname property.
     * 
     * @param value
     *     allowed object is
     *     {@link XsdQNameType }
     *     
     */
    public void setServiceQname(XsdQNameType value) {
        this.serviceQname = value;
    }

    /**
     * Gets the value of the portComponentRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portComponentRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortComponentRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortComponentRefType }
     * 
     * 
     */
    public List<PortComponentRefType> getPortComponentRef() {
        if (portComponentRef == null) {
            portComponentRef = new ArrayList<PortComponentRefType>();
        }
        return this.portComponentRef;
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
     * {@link ServiceRefHandlerType }
     * 
     * 
     */
    public List<ServiceRefHandlerType> getHandler() {
        if (handler == null) {
            handler = new ArrayList<ServiceRefHandlerType>();
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
