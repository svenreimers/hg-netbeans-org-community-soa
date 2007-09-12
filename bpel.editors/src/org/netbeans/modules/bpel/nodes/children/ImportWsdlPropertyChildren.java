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

package org.netbeans.modules.bpel.nodes.children;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.netbeans.modules.bpel.editors.api.nodes.NodeType;
import org.netbeans.modules.bpel.nodes.navigator.NavigatorNodeFactory;
import org.netbeans.modules.bpel.nodes.synchronizer.ModelSynchronizer;
import org.netbeans.modules.bpel.nodes.synchronizer.SynchronisationListener;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.xml.wsdl.model.extensions.bpel.CorrelationProperty;
import org.netbeans.modules.xml.wsdl.model.extensions.bpel.PropertyAlias;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.dom.NamedComponentReference;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Vitaly Bychkov
 * @version 12 April 2006
 */
public class ImportWsdlPropertyChildren extends Children.Keys implements SynchronisationListener{
    
    private Lookup myLookup;
    private CorrelationProperty myCorrProp;
    private WSDLModel myWsdlModel;
    private ModelSynchronizer synchronizer;
    
    public ImportWsdlPropertyChildren(WSDLModel wsdlModel, CorrelationProperty corrProp, Lookup lookup) {
        myLookup = lookup;
        myCorrProp = corrProp;
        if (wsdlModel != null) {
            myWsdlModel = wsdlModel;
            synchronizer = new ModelSynchronizer(this);
            synchronizer.subscribe(wsdlModel);
            
        }
        
    }
    
    protected Node[] createNodes(Object key) {
        NavigatorNodeFactory factory = NavigatorNodeFactory.getInstance();
        Node childNode = null;
        
        if (key instanceof PropertyAlias) {
            childNode = factory.createNode(
                    NodeType.CORRELATION_PROPERTY_ALIAS, key, myLookup);
        }
        
        return childNode == null ? new Node[0] : new Node[] {childNode};
    }
    
    Collection getNodeKeys() {
        if (myWsdlModel == null) {
            return Collections.EMPTY_LIST;
        }
        
        List<Object> childs = new ArrayList<Object>();
        
        // set propertyAlias nodes
        List<PropertyAlias> propAliasList = myWsdlModel.getDefinitions().
                getExtensibilityElements(PropertyAlias.class);
        if (myCorrProp == null) {
            return Collections.EMPTY_LIST;
        }

        if (propAliasList != null && propAliasList.size() > 0) {
            for (PropertyAlias tmpPropAlias : propAliasList) {
                NamedComponentReference<CorrelationProperty> tmpCorrProp 
                        = tmpPropAlias.getPropertyName();
                if (tmpCorrProp != null 
                        && myCorrProp != null 
                        && myCorrProp.equals(tmpCorrProp.get())) 
                {
                    childs.add(tmpPropAlias);
                }
            }
        }
        
        return childs;
    }
    
    protected void addNotify() {
        reload();
    }
    
    protected void removeNotify() {
        setKeys(Collections.EMPTY_SET);
    }
    
    public void reload() {
        setKeys(getNodeKeys());
    }
    
    
    
    public void componentUpdated(Component component) {
       //do nothing
    }
    
    public void childrenUpdated(Component component) {
        if (component == myWsdlModel.getDefinitions()
            || component instanceof PropertyAlias)
        {
            reload();
        }
        
    }
    
    
    
}

