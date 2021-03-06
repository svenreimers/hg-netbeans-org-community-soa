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
// Generated on: 2006.12.09 at 06:25:55 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.sunejb30;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "portComponentName",
    "endpointAddressUri",
    "loginConfigOrMessageSecurityBinding",
    "transportGuarantee",
    "serviceQname",
    "tieClass",
    "servletImplClass",
    "debuggingEnabled"
})
@XmlRootElement(name = "webservice-endpoint")
public class WebserviceEndpoint {

    @XmlElement(name = "port-component-name", required = true)
    protected String portComponentName;
    @XmlElement(name = "endpoint-address-uri")
    protected String endpointAddressUri;
    @XmlElements({
        @XmlElement(name = "login-config", type = LoginConfig.class),
        @XmlElement(name = "message-security-binding", type = MessageSecurityBinding.class)
    })
    protected List<Object> loginConfigOrMessageSecurityBinding;
    @XmlElement(name = "transport-guarantee")
    protected String transportGuarantee;
    @XmlElement(name = "service-qname")
    protected ServiceQname serviceQname;
    @XmlElement(name = "tie-class")
    protected String tieClass;
    @XmlElement(name = "servlet-impl-class")
    protected String servletImplClass;
    @XmlElement(name = "debugging-enabled")
    protected String debuggingEnabled;

    /**
     * Gets the value of the portComponentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortComponentName() {
        return portComponentName;
    }

    /**
     * Sets the value of the portComponentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortComponentName(String value) {
        this.portComponentName = value;
    }

    /**
     * Gets the value of the endpointAddressUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointAddressUri() {
        return endpointAddressUri;
    }

    /**
     * Sets the value of the endpointAddressUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointAddressUri(String value) {
        this.endpointAddressUri = value;
    }

    /**
     * Gets the value of the loginConfigOrMessageSecurityBinding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loginConfigOrMessageSecurityBinding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoginConfigOrMessageSecurityBinding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoginConfig }
     * {@link MessageSecurityBinding }
     * 
     * 
     */
    public List<Object> getLoginConfigOrMessageSecurityBinding() {
        if (loginConfigOrMessageSecurityBinding == null) {
            loginConfigOrMessageSecurityBinding = new ArrayList<Object>();
        }
        return this.loginConfigOrMessageSecurityBinding;
    }

    /**
     * Gets the value of the transportGuarantee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportGuarantee() {
        return transportGuarantee;
    }

    /**
     * Sets the value of the transportGuarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportGuarantee(String value) {
        this.transportGuarantee = value;
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

    /**
     * Gets the value of the tieClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTieClass() {
        return tieClass;
    }

    /**
     * Sets the value of the tieClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTieClass(String value) {
        this.tieClass = value;
    }

    /**
     * Gets the value of the servletImplClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServletImplClass() {
        return servletImplClass;
    }

    /**
     * Sets the value of the servletImplClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServletImplClass(String value) {
        this.servletImplClass = value;
    }

    /**
     * Gets the value of the debuggingEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebuggingEnabled() {
        return debuggingEnabled;
    }

    /**
     * Sets the value of the debuggingEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebuggingEnabled(String value) {
        this.debuggingEnabled = value;
    }

}
