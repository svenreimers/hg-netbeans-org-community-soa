/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.0-06/22/2005 01:29 PM(ryans)-EA2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2005.09.05 at 07:05:33 PM MSD
//
package org.netbeans.modules.bpel.model.api;

import org.netbeans.modules.bpel.model.api.support.TBoolean;

/**
 * <p>
 * Java class for tCopy complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 *   &lt;complexType name=&quot;tCopy&quot;&gt;
 *     &lt;complexContent&gt;
 *       &lt;extension base=&quot;{http://docs.oasis-open.org/wsbpel/2.0/process/executable}tExtensibleElements&quot;&gt;
 *         &lt;sequence&gt;
 *           &lt;element ref=&quot;{http://docs.oasis-open.org/wsbpel/2.0/process/executable}from&quot;/&gt;
 *           &lt;element ref=&quot;{http://docs.oasis-open.org/wsbpel/2.0/process/executable}to&quot;/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/extension&gt;
 *     &lt;/complexContent&gt;
 *   &lt;/complexType&gt;
 * </pre>
 */
public interface Copy extends ExtensibleElements, AssignChild, BpelContainer {
    
    String KEEP_SRC_ELEMENT_NAME    = "keepSrcElementName";    // NOI18N
    
    String IGNORE_MISSING_FROM_DATA = "ignoreMissingFromData"; // NOI18N

    /**
     * Gets the value of the from property.
     * 
     * @return possible object is {@link From }
     */
    From getFrom();

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *            allowed object is {@link From }
     */
    void setFrom( From value );

    /**
     * Gets the value of the to property.
     * 
     * @return possible object is {@link To }
     */
    To getTo();

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *            allowed object is {@link To }
     */
    void setTo( To value );
    
    /**
     * @return "keepSrcElementName" attribute value.
     */
    TBoolean getKeepSrcElementName();
    
    /**
     * Setter for "keepSrcElementName" attribute.
     * @param value New "keepSrcElementName" attribute value.
     */
    void setKeepSrcElementName( TBoolean value );
    
    /**
     * Removes "keepSrcElementName" attribute.
     */
    void removeKeepSrcElementName();
    
    /**
     * @return "ignoreMissingFromData" attribute value.
     */
    TBoolean getIgnoreMissingFromData();
    
    /**
     * Setter for "ignoreMissingFromData" attribute.
     * @param value New "keepSrcElementName" attribute value.
     */
    void setIgnoreMissingFromData( TBoolean value );
    
    /**
     * Removes "ignoreMissingFromData" attribute.
     */
    void removeIgnoreMissingFromData();
}
