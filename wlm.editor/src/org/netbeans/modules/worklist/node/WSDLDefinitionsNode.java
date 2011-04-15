/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
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
 *
 * Contributor(s):
 *
 * Portions Copyrighted 1997-2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.worklist.node;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.netbeans.modules.xml.wsdl.model.Definitions;
import org.netbeans.modules.xml.wsdl.model.PortType;
import org.netbeans.modules.xml.wsdl.model.WSDLComponent;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;

/**
 *
 * @author radval
 */
public class WSDLDefinitionsNode extends AbstractNode {

    public static final String IMPORTS_FOLDER = "IMPORTS_FOLDER"; //NOI18N
    public static final String MESSAGES_FOLDER = "MESSAGES_FOLDER";//NOI18N
    public static final String PORTTYPES_FOLDER = "PORTTYPES_FOLDER";//NOI18N
    public static final String BINDING_FOLDER  = "BINDING_FOLDER";//NOI18N
    public static final String SERVICES_FOLDER = "SERVICES_FOLDER";//NOI18N
    public static final String EXTENSIBILITY_ELEMENTS_FOLDER = "EXTENSIBILITY_ELEMENTS_FOLDER";//NOI18N
    
    private Image ICON = ImageUtilities.loadImage(
            "org/netbeans/modules/worklist/editor/view/resources/wsdl16.png"); // NOI18N
  
    private Definitions definitions;
    
    public WSDLDefinitionsNode(Definitions definitions, Children children) {
        super(children);
        this.definitions = definitions;
        
    }
    
    public WSDLDefinitionsNode(Definitions mWSDLDef, List<Class<? extends WSDLComponent>> filters) {
        this(mWSDLDef, new DefinitionsChildren(mWSDLDef, filters));
    }
   
    @Override
    public Image getIcon(int type) {
        return ICON;
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return ICON;
    }
    
    protected void updateDisplayName() {
        Definitions defs = (Definitions) getWSDLComponent();
        String name = defs.getTargetNamespace();
        if (name == null) {
//            name = NbBundle.getMessage(DefinitionsNode.class,
//                    "LBL_DefinitionsNode_NoTargetNamespace");
            name = "No TargetNamespace";
        }
        setDisplayName(name);
    }
     
    WSDLComponent getWSDLComponent() {
        return this.definitions;
    }
    
     public static final class DefinitionsChildren extends GenericWSDLComponentChildren {
        
        List<Class<? extends WSDLComponent>> filters;
        public DefinitionsChildren(Definitions definitions) {
            super(definitions);
        }
        
        //Hack for creating children with only specific categories
        /**
         * Only top level filters are supported.
         * Message, Import, Types, Documentation, PortType, Binding , Service and ExtensibilityElement are supported.
         * If filters are specified, then only those folders which support that top level component are created.
         * 
         */
        public DefinitionsChildren(Definitions definitions, List<Class<? extends WSDLComponent>> filters) {
            this(definitions);
            this.filters = filters;
        }
        
        @Override
        protected Node[] createNodes(Object key) {
            Node node = null;
           
            if (IMPORTS_FOLDER.equals(key)) {
                
            }
            if (MESSAGES_FOLDER.equals(key)) {
                
            }
            if (PORTTYPES_FOLDER.equals(key)) {
                node = new PortTypeFolderNode((Definitions) getWSDLComponent());
            }
            if (BINDING_FOLDER.equals(key)) {
                
            }
            if (SERVICES_FOLDER.equals(key)) {
                
            }
            
            if (EXTENSIBILITY_ELEMENTS_FOLDER.equals(key)) {
                
            }
            
            
            if(node != null) {
                return new Node[] {node};
            }
            
            return new Node[] {node};
        }
        
   
        protected Collection getKeys() {
            Collection<Object> keys = new ArrayList<Object>();
            
            
            Definitions def = (Definitions) getWSDLComponent();
            
            
            if (filters == null || filters.contains(PortType.class)) {
                keys.add(PORTTYPES_FOLDER);
            }
            

            return keys;
        }

      
        
        
    }

}
