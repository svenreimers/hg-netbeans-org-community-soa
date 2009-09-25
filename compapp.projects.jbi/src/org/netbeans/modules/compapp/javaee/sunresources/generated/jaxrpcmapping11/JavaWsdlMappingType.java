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
// Generated on: 2006.12.09 at 06:26:06 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * 	The element describes the Java mapping to a known WSDL document.
 * 
 * 	It contains the mapping between package names and XML namespaces,
 * 	WSDL root types and Java artifacts, and the set of mappings for
 * 	services.
 * 
 *       
 * 
 * <p>Java class for java-wsdl-mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="java-wsdl-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="package-mapping" type="{http://java.sun.com/xml/ns/j2ee}package-mappingType" maxOccurs="unbounded"/>
 *         &lt;element name="java-xml-type-mapping" type="{http://java.sun.com/xml/ns/j2ee}java-xml-type-mappingType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exception-mapping" type="{http://java.sun.com/xml/ns/j2ee}exception-mappingType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="service-interface-mapping" type="{http://java.sun.com/xml/ns/j2ee}service-interface-mappingType" minOccurs="0"/>
 *           &lt;element name="service-endpoint-interface-mapping" type="{http://java.sun.com/xml/ns/j2ee}service-endpoint-interface-mappingType" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="version" use="required" type="{http://java.sun.com/xml/ns/j2ee}dewey-versionType" fixed="1.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "java-wsdl-mappingType", propOrder = {
    "packageMapping",
    "javaXmlTypeMapping",
    "exceptionMapping",
    "serviceInterfaceMappingAndServiceEndpointInterfaceMapping"
})
public class JavaWsdlMappingType {

    @XmlElement(name = "package-mapping", required = true)
    protected List<PackageMappingType> packageMapping;
    @XmlElement(name = "java-xml-type-mapping")
    protected List<JavaXmlTypeMappingType> javaXmlTypeMapping;
    @XmlElement(name = "exception-mapping")
    protected List<ExceptionMappingType> exceptionMapping;
    @XmlElements({
        @XmlElement(name = "service-endpoint-interface-mapping", type = ServiceEndpointInterfaceMappingType.class),
        @XmlElement(name = "service-interface-mapping", type = ServiceInterfaceMappingType.class)
    })
    protected List<Object> serviceInterfaceMappingAndServiceEndpointInterfaceMapping;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;
    @XmlAttribute(required = true)
    protected BigDecimal version;

    /**
     * Gets the value of the packageMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageMappingType }
     * 
     * 
     */
    public List<PackageMappingType> getPackageMapping() {
        if (packageMapping == null) {
            packageMapping = new ArrayList<PackageMappingType>();
        }
        return this.packageMapping;
    }

    /**
     * Gets the value of the javaXmlTypeMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the javaXmlTypeMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJavaXmlTypeMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JavaXmlTypeMappingType }
     * 
     * 
     */
    public List<JavaXmlTypeMappingType> getJavaXmlTypeMapping() {
        if (javaXmlTypeMapping == null) {
            javaXmlTypeMapping = new ArrayList<JavaXmlTypeMappingType>();
        }
        return this.javaXmlTypeMapping;
    }

    /**
     * Gets the value of the exceptionMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exceptionMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExceptionMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExceptionMappingType }
     * 
     * 
     */
    public List<ExceptionMappingType> getExceptionMapping() {
        if (exceptionMapping == null) {
            exceptionMapping = new ArrayList<ExceptionMappingType>();
        }
        return this.exceptionMapping;
    }

    /**
     * Gets the value of the serviceInterfaceMappingAndServiceEndpointInterfaceMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceInterfaceMappingAndServiceEndpointInterfaceMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceInterfaceMappingAndServiceEndpointInterfaceMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceEndpointInterfaceMappingType }
     * {@link ServiceInterfaceMappingType }
     * 
     * 
     */
    public List<Object> getServiceInterfaceMappingAndServiceEndpointInterfaceMapping() {
        if (serviceInterfaceMappingAndServiceEndpointInterfaceMapping == null) {
            serviceInterfaceMappingAndServiceEndpointInterfaceMapping = new ArrayList<Object>();
        }
        return this.serviceInterfaceMappingAndServiceEndpointInterfaceMapping;
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

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        if (version == null) {
            return new BigDecimal("1.1");
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

}
