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


package org.netbeans.modules.bpel.debugger;

import javax.xml.namespace.QName;
import org.netbeans.modules.bpel.debugger.api.EditorContextBridge;
import org.netbeans.modules.bpel.debugger.api.Position;
import org.netbeans.modules.bpel.debugger.bdiclient.impl.BDIDebugFrame;
import org.netbeans.modules.bpel.debuggerbdi.rmi.api.DebugFrame;

/**
 * Represents a break position, which is a line in a BPEL file
 * that corresponds to a process instance (debug frame).
 * When a breakpoint is reached, a break position is created that
 * represents where that breakpoint is, and to what back-end
 * process instance the breakpoint corresponds to.
 *
 * @author Sun Microsystems
 */
public class BreakPosition implements Position {

    private final BDIDebugFrame mFrame;
    private final String mXpath;
    
    public BreakPosition(
            BDIDebugFrame frame,
            String xpath)
    {
        mFrame = frame;
        mXpath = EditorContextBridge.normalizeXpath(xpath);
    }
    
    public QName getProcessQName() {
        return mFrame.getProcessInstance().getProcessQName();
    }

    public String getXpath() {
        return mXpath;
    }
    
    public String getBranchId() {
        return mFrame.getId();
    }
    
    public BDIDebugFrame getFrame() {
        return mFrame;
    }

    public String toString() {
        return mFrame.getId() + " at " + mXpath;
    }
}
