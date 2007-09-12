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

package org.netbeans.modules.bpel.debugger.pem;

import org.netbeans.modules.bpel.debugger.api.pem.PemEntity;
import org.netbeans.modules.bpel.debugger.api.pem.PemEntity.State;
import org.netbeans.modules.bpel.debugger.api.psm.PsmEntity;

/**
 *
 * @author Alexander Zgursky
 */
public abstract class PemEntityImpl implements PemEntity {
    private final ProcessExecutionModelImpl myModel;
    private final PsmEntity myPsmEntity;
    private final String myBranchId;
    private final boolean myIsReceivingEvents;
    
    private State myState;
    private PemEntityImpl myParent;
    private int myIndex;
    private int myStartedChildrenCount;
    private int myLastStartedEventIndex;
    
    /** Creates a new instance of PemEntityImpl */
    protected PemEntityImpl(ProcessExecutionModelImpl model,
            PsmEntity psmEntity, String branchId, boolean isReceivingEvents)
    {
        myModel = model;
        myBranchId = branchId;
        myPsmEntity = psmEntity;
        myIsReceivingEvents = isReceivingEvents;
        myState = State.UNKNOWN;
    }
    
    public ProcessExecutionModelImpl getModel() {
        return myModel;
    }
    
    public PsmEntity getPsmEntity() {
        return myPsmEntity;
    }

    public PemEntity.State getState() {
        return myState;
    }
    
    public PemEntityImpl getParent() {
        return myParent;
    }
    
    public int getIndex() {
        return myIndex;
    }
    
    public boolean isDescendantOf(PemEntity pemEntity) {
        PemEntityImpl ent = getParent();
        while (ent != null && ent != pemEntity) {
            ent = ent.getParent();
        }
        return ent != null;
    }
    
    public boolean isInTree(PemEntity pemEntity) {
        return pemEntity == this || isDescendantOf(pemEntity);
    }
    
    public int getLastStartedEventIndex() {
        return myLastStartedEventIndex;
    }
    
    public abstract PemEntityImpl[] getChildren();
    
    public abstract PemEntityImpl[] getChildren(PsmEntity psmEntity);
    
    protected void setState(State state) {
        if (myState == state) {
            return;
        }
        
        if (myParent != null) {
            myParent.onBeforeChildStateChanged(this, myState, state);
        }
        myState = state;
    }
    
    protected void onBeforeChildStateChanged(
            PemEntity pemEntity, State oldState, State newState)
    {
        if (myIsReceivingEvents) {
            //TODO:ugly hack for the runtime bug - make sure that if child is started
            //then it's parent is also started
            if (getState() == State.UNKNOWN && newState == State.STARTED) {
//                System.out.println("Have not received STARTED event for " + getPsmEntity().getXpath());
                setState(State.STARTED);
            }
            //end of hack
            
            //not interested - I'll get my own event for state change
            return;
        }
        
        if (oldState == State.STARTED) {
            myStartedChildrenCount--;
        } else if (newState == State.STARTED) {
            myStartedChildrenCount++;
        }
        
        if (myStartedChildrenCount > 0) {
            setState(State.STARTED);
        } else {
            setState(State.COMPLETED);
        }
    }
    
    protected abstract void addChild(PemEntityImpl child);
    
    protected void setParent(PemEntityImpl parent) {
        myParent = parent;
    }
    
    protected void setIndex(int index) {
        myIndex = index;
    }
    
    protected void setLastStartedEventIndex(int index) {
        myLastStartedEventIndex = index;
        if (myParent != null) {
            myParent.setLastStartedEventIndex(index);
        }
    }
}
