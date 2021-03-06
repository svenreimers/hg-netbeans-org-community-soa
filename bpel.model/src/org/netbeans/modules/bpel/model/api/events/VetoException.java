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

/**
 *
 */
package org.netbeans.modules.bpel.model.api.events;

/**
 * This class is intended for fired Exception about wrong change in Model was
 * trying to perform. This action should be rolled back.
 *
 * @author ads
 */
public class VetoException extends org.netbeans.modules.soa.ui.properties.VetoException {

    private static final long serialVersionUID = 3499029788731463455L;

    /**
     * Constructor for exception that contains message about wrong change. Also
     * it contains event that model trying to fire.
     *
     * @param message
     *            error message about wrong change.
     * @param event
     *            event that model is tying to fire.
     */
    public VetoException( String message, ChangeEvent event ) {
        super(message, event);
    }
}
