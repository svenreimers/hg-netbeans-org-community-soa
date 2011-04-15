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
 * 	The service-endpoint-method-mapping element defines the mapping of
 * 	Java methods to operations (which are not uniquely qualified by
 * 	qnames).
 * 
 * 	The wsdl-operation should be interpreted with respect to the
 * 	portType and binding in which this definition is embedded within.
 * 	See the definitions for service-endpoint-interface-mapping and
 * 	service-interface-mapping to acquire the proper context.  The
 * 	wrapped-element indicator should only be specified when a WSDL
 * 	message wraps an element type.  The wsdl-return-value-mapping is
 * 	not specified for one-way operations.
 * 
 * 	Used in: service-endpoint-interface-mapping
 * 
 *       
 * 
 * <p>Java class for service-endpoint-method-mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="service-endpoint-method-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="java-method-name" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;element name="wsdl-operation" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;element name="wrapped-element" type="{http://java.sun.com/xml/ns/j2ee}emptyType" minOccurs="0"/>
 *         &lt;element name="method-param-parts-mapping" type="{http://java.sun.com/xml/ns/j2ee}method-param-parts-mappingType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsdl-return-value-mapping" type="{http://java.sun.com/xml/ns/j2ee}wsdl-return-value-mappingType" minOccurs="0"/>
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
@XmlType(name = "service-endpoint-method-mappingType", propOrder = {
    "javaMethodName",
    "wsdlOperation",
    "wrappedElement",
    "methodParamPartsMapping",
    "wsdlReturnValueMapping"
})
public class ServiceEndpointMethodMappingType {

    @XmlElement(name = "java-method-name", required = true)
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String javaMethodName;
    @XmlElement(name = "wsdl-operation", required = true)
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String wsdlOperation;
    @XmlElement(name = "wrapped-element")
    protected EmptyType wrappedElement;
    @XmlElement(name = "method-param-parts-mapping")
    protected List<MethodParamPartsMappingType> methodParamPartsMapping;
    @XmlElement(name = "wsdl-return-value-mapping")
    protected WsdlReturnValueMappingType wsdlReturnValueMapping;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the javaMethodName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String getJavaMethodName() {
        return javaMethodName;
    }

    /**
     * Sets the value of the javaMethodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public void setJavaMethodName(org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String value) {
        this.javaMethodName = value;
    }

    /**
     * Gets the value of the wsdlOperation property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String getWsdlOperation() {
        return wsdlOperation;
    }

    /**
     * Sets the value of the wsdlOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public void setWsdlOperation(org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String value) {
        this.wsdlOperation = value;
    }

    /**
     * Gets the value of the wrappedElement property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getWrappedElement() {
        return wrappedElement;
    }

    /**
     * Sets the value of the wrappedElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setWrappedElement(EmptyType value) {
        this.wrappedElement = value;
    }

    /**
     * Gets the value of the methodParamPartsMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the methodParamPartsMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMethodParamPartsMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MethodParamPartsMappingType }
     * 
     * 
     */
    public List<MethodParamPartsMappingType> getMethodParamPartsMapping() {
        if (methodParamPartsMapping == null) {
            methodParamPartsMapping = new ArrayList<MethodParamPartsMappingType>();
        }
        return this.methodParamPartsMapping;
    }

    /**
     * Gets the value of the wsdlReturnValueMapping property.
     * 
     * @return
     *     possible object is
     *     {@link WsdlReturnValueMappingType }
     *     
     */
    public WsdlReturnValueMappingType getWsdlReturnValueMapping() {
        return wsdlReturnValueMapping;
    }

    /**
     * Sets the value of the wsdlReturnValueMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsdlReturnValueMappingType }
     *     
     */
    public void setWsdlReturnValueMapping(WsdlReturnValueMappingType value) {
        this.wsdlReturnValueMapping = value;
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
