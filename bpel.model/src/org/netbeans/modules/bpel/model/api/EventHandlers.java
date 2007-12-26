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

/**
 * <p>
 * Java class for tEventHandlers complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 *   &lt;complexType name=&quot;tEventHandlers&quot;&gt;
 *     &lt;complexContent&gt;
 *       &lt;extension base=&quot;{http://docs.oasis-open.org/wsbpel/2.0/process/executable}tExtensibleElements&quot;&gt;
 *         &lt;sequence&gt;
 *           &lt;element name=&quot;onEvent&quot; type=&quot;{http://docs.oasis-open.org/wsbpel/2.0/process/executable}tOnEvent&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *           &lt;element name=&quot;onAlarm&quot; type=&quot;{http://docs.oasis-open.org/wsbpel/2.0/process/executable}tOnAlarmEvent&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/extension&gt;
 *     &lt;/complexContent&gt;
 *   &lt;/complexType&gt;
 * </pre>
 */
public interface EventHandlers extends ExtensibleElements, BpelContainer {

    /**
     * Returns aray of onEvent children.
     * 
     * @return array of onMessage.
     */
    OnEvent[] getOnEvents();

    /**
     * Returns ith OnEvent child .
     * 
     * @param i
     *            index
     * @return ith OnEvent object.
     */
    OnEvent getOnEvent( int i );

    /**
     * Removes ith onMessage object.
     * 
     * @param i
     *            index
     */
    void removeOnEvent( int i );

    /**
     * Set new array of OnEvent children.
     * 
     * @param events
     *            new array for set.
     */
    void setOnEvent( OnEvent[] events );

    /**
     * Set <code>event</code> to the ith place.
     * 
     * @param event
     *            object for set.
     * @param i
     *            index
     */
    void setOnEvent( OnEvent event, int i );

    /**
     * Adds OnEvent.
     * 
     * @param event
     *            object for add.
     */
    void addOnEvent( OnEvent event );

    /**
     * Insert <code>event</code> on the ith place.
     * 
     * @param event
     *            object for insert.
     * @param i
     *            index
     */
    void insertOnEvent( OnEvent event, int i );

    /**
     * Returns array of onAlarmEvent children.
     * 
     * @return array of onAlarmEvent children.
     */
    OnAlarmEvent[] getOnAlarms();

    /**
     * Returns ith onAlarmEvent.
     * 
     * @param i
     *            index in array.
     * @return ith onAlarmEvent.
     */
    OnAlarmEvent getOnAlarm( int i );

    /**
     * Removes ith onAlarmEvent.
     * 
     * @param i
     *            index for remove.
     */
    void removeOnAlarm( int i );

    /**
     * Set new array of onAlarmEvent children.
     * 
     * @param alarm
     *            array for set.
     */
    void setOnAlarms( OnAlarmEvent[] alarm );

    /**
     * Set ith onAlarmEvent to <code>alarm</code> object.
     * 
     * @param alarm
     *            object for set.
     * @param i
     *            index.
     */
    void setOnAlarm( OnAlarmEvent alarm, int i );

    /**
     * Add <code>alarm</code>.
     * 
     * @param alarm
     *            object for add.
     */
    void addOnAlarm( OnAlarmEvent alarm );

    /**
     * Insert <code>alarm</code> to the ith place.
     * 
     * @param alarm
     *            object for set.
     * @param i
     *            index
     */
    void insertOnAlarm( OnAlarmEvent alarm, int i );

    /**
     * @return size of onMessage children.
     */
    int sizeOfOnEvents();

    /**
     * @return size of onAlarm children.
     */
    int sizeOfOnAlarms();

}