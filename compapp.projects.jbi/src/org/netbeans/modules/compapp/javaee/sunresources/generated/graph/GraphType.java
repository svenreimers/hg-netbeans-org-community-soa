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
// Generated on: 2006.12.09 at 06:25:41 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.graph;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GraphType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GraphType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="node" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="locX" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="locY" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="logicalname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GraphType", propOrder = {
    "node"
})
public class GraphType {

    @XmlElement(required = true)
    protected List<GraphType.Node> node;

    /**
     * Gets the value of the node property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the node property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GraphType.Node }
     * 
     * 
     */
    public List<GraphType.Node> getNode() {
        if (node == null) {
            node = new ArrayList<GraphType.Node>();
        }
        return this.node;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="locX" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="locY" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="logicalname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Node {

        @XmlAttribute(required = true)
        protected String locX;
        @XmlAttribute(required = true)
        protected String locY;
        @XmlAttribute(required = true)
        protected String logicalname;

        /**
         * Gets the value of the locX property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocX() {
            return locX;
        }

        /**
         * Sets the value of the locX property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocX(String value) {
            this.locX = value;
        }

        /**
         * Gets the value of the locY property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocY() {
            return locY;
        }

        /**
         * Sets the value of the locY property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocY(String value) {
            this.locY = value;
        }

        /**
         * Gets the value of the logicalname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLogicalname() {
            return logicalname;
        }

        /**
         * Sets the value of the logicalname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLogicalname(String value) {
            this.logicalname = value;
        }

    }

}
