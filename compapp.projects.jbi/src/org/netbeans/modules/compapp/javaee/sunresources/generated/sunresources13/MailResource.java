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
// Generated on: 2006.12.09 at 06:26:10 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "property"
})
@XmlRootElement(name = "mail-resource")
public class MailResource {

    @XmlAttribute(name = "jndi-name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String jndiName;
    @XmlAttribute(name = "store-protocol")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String storeProtocol;
    @XmlAttribute(name = "store-protocol-class")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String storeProtocolClass;
    @XmlAttribute(name = "transport-protocol")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String transportProtocol;
    @XmlAttribute(name = "transport-protocol-class")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String transportProtocolClass;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String host;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String user;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String from;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String debug;
    @XmlAttribute(name = "object-type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String objectType;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String enabled;
    protected String description;
    protected List<Property> property;

    /**
     * Gets the value of the jndiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJndiName() {
        return jndiName;
    }

    /**
     * Sets the value of the jndiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJndiName(String value) {
        this.jndiName = value;
    }

    /**
     * Gets the value of the storeProtocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreProtocol() {
        if (storeProtocol == null) {
            return "imap";
        } else {
            return storeProtocol;
        }
    }

    /**
     * Sets the value of the storeProtocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreProtocol(String value) {
        this.storeProtocol = value;
    }

    /**
     * Gets the value of the storeProtocolClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreProtocolClass() {
        if (storeProtocolClass == null) {
            return "com.sun.mail.imap.IMAPStore";
        } else {
            return storeProtocolClass;
        }
    }

    /**
     * Sets the value of the storeProtocolClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreProtocolClass(String value) {
        this.storeProtocolClass = value;
    }

    /**
     * Gets the value of the transportProtocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportProtocol() {
        if (transportProtocol == null) {
            return "smtp";
        } else {
            return transportProtocol;
        }
    }

    /**
     * Sets the value of the transportProtocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportProtocol(String value) {
        this.transportProtocol = value;
    }

    /**
     * Gets the value of the transportProtocolClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportProtocolClass() {
        if (transportProtocolClass == null) {
            return "com.sun.mail.smtp.SMTPTransport";
        } else {
            return transportProtocolClass;
        }
    }

    /**
     * Sets the value of the transportProtocolClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportProtocolClass(String value) {
        this.transportProtocolClass = value;
    }

    /**
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the debug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebug() {
        if (debug == null) {
            return "false";
        } else {
            return debug;
        }
    }

    /**
     * Sets the value of the debug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebug(String value) {
        this.debug = value;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectType() {
        if (objectType == null) {
            return "user";
        } else {
            return objectType;
        }
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectType(String value) {
        this.objectType = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnabled() {
        if (enabled == null) {
            return "true";
        } else {
            return enabled;
        }
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnabled(String value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getProperty() {
        if (property == null) {
            property = new ArrayList<Property>();
        }
        return this.property;
    }

}
