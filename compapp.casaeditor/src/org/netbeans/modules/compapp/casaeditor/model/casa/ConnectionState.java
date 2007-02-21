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
package org.netbeans.modules.compapp.casaeditor.model.casa;

/**
 *
 * @author jqian
 */
public enum ConnectionState {
    
    NEW("new"),
    DELETED("deleted"),
    UNCHANGED("unchanged");
    
    ConnectionState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    public static ConnectionState getConnectionState(String state) {
        if (state == null) {
            return null;
        } else if (state.equals("new")) {
            return NEW;
        } else if (state.equals("deleted")) {
            return DELETED;
        } else if (state.equals("unchanged")) {
            return UNCHANGED;
        } else {
            throw new RuntimeException("Illegal connection state: " + state);
        }
    }
    
    private final String state;
}
