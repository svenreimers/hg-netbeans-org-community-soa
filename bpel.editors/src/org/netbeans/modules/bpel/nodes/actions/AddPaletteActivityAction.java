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
package org.netbeans.modules.bpel.nodes.actions;

import java.util.concurrent.Callable;
import org.netbeans.modules.bpel.editors.api.nodes.NodeType;
import org.netbeans.modules.bpel.model.api.Activity;
import org.netbeans.modules.bpel.model.api.ActivityHolder;
import org.netbeans.modules.bpel.model.api.BpelContainer;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.BpelModel;
import org.netbeans.modules.bpel.model.api.CompositeActivity;
import org.netbeans.modules.bpel.model.api.ExtendableActivity;
import org.netbeans.modules.bpel.model.api.Scope;
import org.netbeans.modules.bpel.model.api.ScopeHolder;
import org.netbeans.modules.bpel.model.api.Sequence;
import org.openide.ErrorManager;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 *
 */
public abstract  class AddPaletteActivityAction extends BpelNodeAction {
    private static final long serialVersionUID = 1L;

    public AddPaletteActivityAction() {
    }    
    
    protected abstract String getBundleName();
    
    public abstract ActionType getType();
    
    private void addActivity(BpelContainer parent, ExtendableActivity child) {
        if (parent instanceof ScopeHolder && child instanceof Scope) {
            addActivity((ScopeHolder)parent, (Scope)child);
        } else if (parent instanceof CompositeActivity) {
            addActivity((CompositeActivity)parent, child);
        } else if (parent instanceof ActivityHolder) {
            ExtendableActivity childActivity = ((ActivityHolder)parent).getActivity();
            if ( childActivity != null ) {
                Sequence sequence = 
                        parent.getBpelModel().getBuilder().createSequence();
                ExtendableActivity newChildActivity = 
                        (ExtendableActivity)childActivity.cut();
                sequence.addActivity(newChildActivity);
                sequence.addActivity(child);
                child = sequence;
            }
            addActivity((ActivityHolder)parent, child);
        }
    }

    public boolean isContainerSupportChildType(BpelContainer container) {
        if (container instanceof ScopeHolder) {
            return false;
        }
        return true;
    }
    
    private void addActivity(ScopeHolder parent, Scope child) {
        parent.setScope(child);
    }

    private void addActivity(CompositeActivity parent, ExtendableActivity child) {
        assert parent != null && child != null;

        parent.addActivity(child);
    }
    
    private void addActivity(ActivityHolder parent, ExtendableActivity child) {
        parent.setActivity(child);
    }
    
    protected abstract ExtendableActivity getPaletteActivity(BpelModel model);
    
    protected void performAction(BpelEntity[] bpelEntities) {
        if (!enable(bpelEntities)) {
            return;
        }
        final BpelContainer entity = (BpelContainer)bpelEntities[0];
        
        Callable performActionCall =  new Callable<Object> () {
            public Object call() throws Exception {
                ExtendableActivity paletteActivity = getPaletteActivity(entity.getBpelModel());
                
                addActivity(entity, paletteActivity);
                return null;
            }
        };
        
        try {
            entity.getBpelModel().invoke(performActionCall , null);
        } catch (Exception ex) {
            ErrorManager.getDefault().notify(ex);
        }
    }

    protected boolean enable(BpelEntity[] bpelEntities) {
        return super.enable(bpelEntities)
        && bpelEntities[0] instanceof BpelContainer 
                && isContainerSupportChildType((BpelContainer) bpelEntities[0]);
    }

}
