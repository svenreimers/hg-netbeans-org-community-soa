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
 * @author ads
 */
public interface CompositeActivity extends Activity {

    /**
     * Gets the value of the activity property. Objects of the
     * ExtendableActivity type could appear in this list.
     * 
     * @return array of activities.
     */
    ExtendableActivity[] getActivities();

    /**
     * @param i
     *            index in array.
     * @return child activity on i-th position.
     */
    ExtendableActivity getActivity( int i );

    /**
     * Set activity on ith place.
     * 
     * @param activity
     *            object for set.
     * @param i
     *            position for set.
     */
    void setActivity( ExtendableActivity activity, int i );

    /**
     * Removes child activity.
     * 
     * @param i
     *            position for remove.
     */
    void removeActivity( int i );

    /**
     * Insert child activtity on the ith place.
     * 
     * @param activity
     *            object for insert.
     * @param i
     *            position for insert.
     */
    void insertActivity( ExtendableActivity activity, int i );

    /**
     * Add activity to the end of list.
     * 
     * @param activity
     *            add object to this parent.
     */
    void addActivity( ExtendableActivity activity );

    /**
     * Size of child activities.
     * 
     * @return size of children.
     */
    int sizeOfActivities();

    /**
     * Set array of activities as new list of activities children.
     * 
     * @param activities
     *            array of activitty objects.
     */
    void setActivtities( ExtendableActivity[] activities );

}