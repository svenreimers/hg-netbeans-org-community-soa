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
 * 	The variable-mapping element defines the correlation between a
 * 	Java class data member or JavaBeans property to an XML element
 * 	or attribute name of an XML root type. If the data-member
 * 	element is present, the Java variable name is a public data
 * 	member.  If data-member	is not present, the Java variable name
 * 	is a JavaBeans property.
 * 
 * 	Used in: java-xml-type-mapping
 * 
 *       
 * 
 * <p>Java class for variable-mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="variable-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="java-variable-name" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;element name="data-member" type="{http://java.sun.com/xml/ns/j2ee}emptyType" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="xml-attribute-name" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *           &lt;element name="xml-element-name" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *           &lt;element name="xml-wildcard" type="{http://java.sun.com/xml/ns/j2ee}emptyType"/>
 *         &lt;/choice>
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
@XmlType(name = "variable-mappingType", propOrder = {
    "javaVariableName",
    "dataMember",
    "xmlAttributeName",
    "xmlElementName",
    "xmlWildcard"
})
public class VariableMappingType {

    @XmlElement(name = "java-variable-name", required = true)
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String javaVariableName;
    @XmlElement(name = "data-member")
    protected EmptyType dataMember;
    @XmlElement(name = "xml-attribute-name")
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String xmlAttributeName;
    @XmlElement(name = "xml-element-name")
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String xmlElementName;
    @XmlElement(name = "xml-wildcard")
    protected EmptyType xmlWildcard;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the javaVariableName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String getJavaVariableName() {
        return javaVariableName;
    }

    /**
     * Sets the value of the javaVariableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public void setJavaVariableName(org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String value) {
        this.javaVariableName = value;
    }

    /**
     * Gets the value of the dataMember property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getDataMember() {
        return dataMember;
    }

    /**
     * Sets the value of the dataMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setDataMember(EmptyType value) {
        this.dataMember = value;
    }

    /**
     * Gets the value of the xmlAttributeName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String getXmlAttributeName() {
        return xmlAttributeName;
    }

    /**
     * Sets the value of the xmlAttributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public void setXmlAttributeName(org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String value) {
        this.xmlAttributeName = value;
    }

    /**
     * Gets the value of the xmlElementName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String getXmlElementName() {
        return xmlElementName;
    }

    /**
     * Sets the value of the xmlElementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String }
     *     
     */
    public void setXmlElementName(org.netbeans.modules.compapp.javaee.sunresources.generated.jaxrpcmapping11.String value) {
        this.xmlElementName = value;
    }

    /**
     * Gets the value of the xmlWildcard property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getXmlWildcard() {
        return xmlWildcard;
    }

    /**
     * Sets the value of the xmlWildcard property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setXmlWildcard(EmptyType value) {
        this.xmlWildcard = value;
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
