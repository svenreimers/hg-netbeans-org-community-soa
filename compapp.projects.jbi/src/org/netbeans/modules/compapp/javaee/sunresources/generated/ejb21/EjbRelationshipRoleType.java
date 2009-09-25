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
 * 
 * 	  The ejb-relationship-roleType describes a role within a
 * 	  relationship. There are two roles in each relationship.
 * 
 * 	  The ejb-relationship-roleType contains an optional
 * 	  description; an optional name for the relationship role; a
 * 	  specification of the multiplicity of the role; an optional
 * 	  specification of cascade-delete functionality for the role;
 * 	  the role source; and a declaration of the cmr-field, if any,
 * 	  by means of which the other side of the relationship is
 * 	  accessed from the perspective of the role source.
 * 
 * 	  The multiplicity and role-source element are mandatory.
 * 
 * 	  The relationship-role-source element designates an entity
 * 	  bean by means of an ejb-name element. For bidirectional
 * 	  relationships, both roles of a relationship must declare a
 * 	  relationship-role-source element that specifies a cmr-field
 * 	  in terms of which the relationship is accessed. The lack of
 * 	  a cmr-field element in an ejb-relationship-role specifies
 * 	  that the relationship is unidirectional in navigability and
 * 	  the entity bean that participates in the relationship is
 * 	  "not aware" of the relationship.
 * 
 * 	  Example:
 * 
 * 	  <ejb-relation>
 * 	      <ejb-relation-name>Product-LineItem</ejb-relation-name>
 * 	      <ejb-relationship-role>
 * 		  <ejb-relationship-role-name>product-has-lineitems
 * 		  </ejb-relationship-role-name>
 * 		  <multiplicity>One</multiplicity>
 * 		  <relationship-role-source>
 * 		  <ejb-name>ProductEJB</ejb-name>
 * 		  </relationship-role-source>
 * 	       </ejb-relationship-role>
 * 	  </ejb-relation>
 * 
 * 	  
 *       
 * 
 * <p>Java class for ejb-relationship-roleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ejb-relationship-roleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://java.sun.com/xml/ns/j2ee}descriptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ejb-relationship-role-name" type="{http://java.sun.com/xml/ns/j2ee}string" minOccurs="0"/>
 *         &lt;element name="multiplicity" type="{http://java.sun.com/xml/ns/j2ee}multiplicityType"/>
 *         &lt;element name="cascade-delete" type="{http://java.sun.com/xml/ns/j2ee}emptyType" minOccurs="0"/>
 *         &lt;element name="relationship-role-source" type="{http://java.sun.com/xml/ns/j2ee}relationship-role-sourceType"/>
 *         &lt;element name="cmr-field" type="{http://java.sun.com/xml/ns/j2ee}cmr-fieldType" minOccurs="0"/>
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
@XmlType(name = "ejb-relationship-roleType", propOrder = {
    "description",
    "ejbRelationshipRoleName",
    "multiplicity",
    "cascadeDelete",
    "relationshipRoleSource",
    "cmrField"
})
public class EjbRelationshipRoleType {

    protected List<DescriptionType> description;
    @XmlElement(name = "ejb-relationship-role-name")
    protected org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21.String ejbRelationshipRoleName;
    @XmlElement(required = true)
    protected MultiplicityType multiplicity;
    @XmlElement(name = "cascade-delete")
    protected EmptyType cascadeDelete;
    @XmlElement(name = "relationship-role-source", required = true)
    protected RelationshipRoleSourceType relationshipRoleSource;
    @XmlElement(name = "cmr-field")
    protected CmrFieldType cmrField;
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
     * Gets the value of the ejbRelationshipRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21.String }
     *     
     */
    public org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21.String getEjbRelationshipRoleName() {
        return ejbRelationshipRoleName;
    }

    /**
     * Sets the value of the ejbRelationshipRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21.String }
     *     
     */
    public void setEjbRelationshipRoleName(org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21.String value) {
        this.ejbRelationshipRoleName = value;
    }

    /**
     * Gets the value of the multiplicity property.
     * 
     * @return
     *     possible object is
     *     {@link MultiplicityType }
     *     
     */
    public MultiplicityType getMultiplicity() {
        return multiplicity;
    }

    /**
     * Sets the value of the multiplicity property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiplicityType }
     *     
     */
    public void setMultiplicity(MultiplicityType value) {
        this.multiplicity = value;
    }

    /**
     * Gets the value of the cascadeDelete property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getCascadeDelete() {
        return cascadeDelete;
    }

    /**
     * Sets the value of the cascadeDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setCascadeDelete(EmptyType value) {
        this.cascadeDelete = value;
    }

    /**
     * Gets the value of the relationshipRoleSource property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipRoleSourceType }
     *     
     */
    public RelationshipRoleSourceType getRelationshipRoleSource() {
        return relationshipRoleSource;
    }

    /**
     * Sets the value of the relationshipRoleSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipRoleSourceType }
     *     
     */
    public void setRelationshipRoleSource(RelationshipRoleSourceType value) {
        this.relationshipRoleSource = value;
    }

    /**
     * Gets the value of the cmrField property.
     * 
     * @return
     *     possible object is
     *     {@link CmrFieldType }
     *     
     */
    public CmrFieldType getCmrField() {
        return cmrField;
    }

    /**
     * Sets the value of the cmrField property.
     * 
     * @param value
     *     allowed object is
     *     {@link CmrFieldType }
     *     
     */
    public void setCmrField(CmrFieldType value) {
        this.cmrField = value;
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
