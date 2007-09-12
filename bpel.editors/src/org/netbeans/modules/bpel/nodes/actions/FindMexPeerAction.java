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

import javax.swing.KeyStroke;
import org.netbeans.modules.bpel.editors.api.nodes.NodeType;
import org.netbeans.modules.bpel.editors.api.utils.Util;
import org.netbeans.modules.bpel.model.api.BpelContainer;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.MessageExchange;
import org.netbeans.modules.bpel.model.api.MessageExchangeReference;
import org.netbeans.modules.bpel.model.api.references.BpelReference;
import org.netbeans.modules.bpel.properties.PropertyNodeFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 *
 * @author Vitaly Bychkov
 * @version 23 March 2006
 *
 */
public class FindMexPeerAction extends BpelNodeAction {
    private static final long serialVersionUID = 1L;
  
    public static final String KEYSTROKE = 
            NbBundle.getMessage(FindMexPeerAction.class,"ACT_FindMexPeerAction");// NOI18N
    
    public FindMexPeerAction() {
        super();
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KEYSTROKE));
    }
    
    protected String getBundleName() {
        return NbBundle.getMessage(FindMexPeerAction.class, "CTL_FindMexPeerAction"); // NOI18N
    }
    
    /**
     * Used just to declare public scope instead protected
     */
    public boolean enable(Node[] nodes) {
        return super.enable(nodes);
    }
    

    public boolean isChangeAction() {
        return false;
    }    
    
    /**
     * Used just to declare public scope instead protected
     */
    public void performAction(Node[] nodes) {
        super.performAction(nodes);
    }
    
    protected void performAction(BpelEntity[] bpelEntities) {
        if (!enable(bpelEntities)) {
            return;
        }
        
        MessageExchangeReference ref = (MessageExchangeReference)bpelEntities[0];
        
        
        MessageExchangeReference peer = new MexPeerFinder(ref).findPeer();
        if (peer == null) {
            return;
        }
        
        
        NodeType type = Util.getBasicNodeType((BpelEntity)peer);
        if (type == null) {
            return;
        }

        Lookup lookup = bpelEntities[0].getModel().getModelSource().getLookup();
        
        Node node = PropertyNodeFactory.getInstance().createNode(
                type, 
                peer, 
                lookup);
        
        if (node == null){
            return;
        }
        
        TopComponent active_tc = TopComponent.getRegistry().getActivated();
        if (active_tc != null) {
            active_tc.setActivatedNodes(new Node[]{node});
        }
    






    }
    protected class MexPeerFinder{
        private MessageExchange mex;
        private MessageExchangeReference self;
        private boolean armed;
        public MexPeerFinder(MessageExchangeReference self){
            this.self = self;
            this.mex = self.getMessageExchange().get();
        }
        
        public MessageExchangeReference findPeer(){
            
            
            //trying to locate the scope we and MEX belongs to
            //This scope will be a root element for our search
            if (mex.getParent() == null || mex.getParent().getParent() == null){
                return null;
            }
            
            BpelContainer scope = mex.getParent().getParent();
            
            armed = false;
            
            MessageExchangeReference result = findPeerImpl(scope);
            
            if (result == null && armed == true){
                //current element is last in sequence. try search from the begin.
                result = findPeerImpl(scope);
                if (result == self){
                    //seems that we are the only element which
                    //uses this mex inside current scope
                    result = null;
                }
            }
            return result;
            
        }
        
        protected MessageExchangeReference findPeerImpl(BpelEntity entity){
            if (entity instanceof MessageExchangeReference){
                if (armed) {
                    BpelReference<MessageExchange> ref =
                            ((MessageExchangeReference) entity).getMessageExchange();
                    
                    if (ref != null && ref.references(this.mex)){
                        return ((MessageExchangeReference) entity);
                    }
                } else {
                    if (entity == self) {
                        //mean that next entity found will be our search result
                        armed = true;
                    }
                }
            }
            
            for( BpelEntity e: entity.getChildren()){
                MessageExchangeReference result =  findPeerImpl(e);
                
                if (result != null){
                    return result;
                }
                
            }
            return null;
            
        }
        
    }
    protected boolean enable(BpelEntity[] bpelEntities) {
        if (!super.enable(bpelEntities) || !(bpelEntities[0] instanceof MessageExchangeReference)){
            return false;
        }
        MessageExchangeReference ref = (MessageExchangeReference)bpelEntities[0];
        
        return (ref.getMessageExchange() != null ) &&
                (!ref.getMessageExchange().isBroken());
        
        
        
    }
    
    public ActionType getType() {
        return ActionType.CYCLE_MEX;
    }
}
