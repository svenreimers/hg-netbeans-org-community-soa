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

package org.netbeans.modules.sun.manager.jbi.nodes;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import org.netbeans.modules.sun.manager.jbi.management.AdministrationService;
import org.netbeans.modules.sun.manager.jbi.management.AppserverJBIMgmtController;
import org.netbeans.modules.sun.manager.jbi.nodes.property.JBIPropertySupportFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.NbBundle;

/**
 * Abstract super class for all nodes in JBI manager.
 *
 * @author jqian
 */
public abstract class AppserverJBIMgmtNode extends AbstractNode {
    
    protected static final String GENERAL_SHEET_SET_NAME = "General"; // NOI18N
    
    private static Logger logger;
    
    private NodeType nodeType;
    private AppserverJBIMgmtController appsrvrJBIMgmtController;
    
    /**
     *
     *
     */
    public AppserverJBIMgmtNode(final AppserverJBIMgmtController controller, 
            final Children children, final NodeType nodeType) {
        super(children);
        setNodeProperties(nodeType);

        appsrvrJBIMgmtController = controller;
    }
    
    public NodeType getNodeType(){
        return nodeType;
    }

    /**
     *
     *
     */
    public AppserverJBIMgmtController getAppserverJBIMgmtController() {
        try {
            if(appsrvrJBIMgmtController == null) { 
                getLogger().log(Level.FINE, 
                        "AppserverJBIMgmtController is " + "null for [" + getNodeType() + "]");  // NOI18N
            }
        } catch(Exception e) {
            getLogger().log(Level.FINE, e.getMessage(), e);
        }
        return appsrvrJBIMgmtController;
    }
    
    /**
     *
     */
    protected String getNodeDisplayName() {
        return getNodeType().getDisplayName();
    }    
       
    /**
     *
     */
    protected String getNodeShortDescription() {
        try {
            return getNodeType().getShortDescription();
        } catch (Exception e) {
            return ""; // not necessarily defined // NOI18N
        }
    }
    
    /**
     * Creates a properties Sheet for viewing when a user chooses the option
     * from the right-click menu.
     *
     * @returns the Sheet to display when Properties is chosen by the user.
     */
    protected Sheet createSheet() {
        Sheet sheet = new Sheet();
        
        Sheet.Set sheetSet = createSheetSet(GENERAL_SHEET_SET_NAME,
                "LBL_GENERAL_PROPERTIES", // NOI18N
                "DSC_GENERAL_PROPERTIES", // NOI18N
                getSheetProperties());
        if (sheetSet != null) {
            sheet.put(sheetSet);
        }
        
        return sheet;
    }
        
    /**
     * Creates a property sheet set.
     *
     * @param name                  name of the property sheet set
     * @param displayNameLabel      bundle name of the diaplay name of the 
     *                              property sheet set 
     * @param descriptoinLabel      bundle name of the description of the 
     *                              property sheet set
     * @parm properties             property map
     */
    protected Sheet.Set createSheetSet(String name, 
            String displayNameLabel, 
            String descriptionLabel, 
            Map<Attribute, ? extends MBeanAttributeInfo> properties) {
        
        if (properties == null) {
            return null;
        }

        PropertySupport[] propertySupports = 
                createPropertySupportArray(properties);
        
        Sheet.Set sheetSet = createSheetSet(
                name, displayNameLabel, descriptionLabel, propertySupports);
        
        return sheetSet;
    }
    
    protected Sheet.Set createSheetSet(String name, 
            String displayNameLabel, 
            String descriptionLabel, 
            PropertySupport[] propertySupports) {
        
        Sheet.Set sheetSet = new Sheet.Set();
        sheetSet.setName(name);
        sheetSet.setDisplayName(
                NbBundle.getMessage(AppserverJBIMgmtNode.class, displayNameLabel)); 
        sheetSet.setShortDescription(
                NbBundle.getMessage(AppserverJBIMgmtNode.class, descriptionLabel)); 
        if (propertySupports != null) {
            sheetSet.put(propertySupports);
        }
        
        return sheetSet;
    }
    
    /**
     * Creates a PropertySupport array from a map of component properties.
     *
     * @param properties The properties of the component.
     * @return An array of PropertySupport objects.
     */
    protected PropertySupport[] createPropertySupportArray(
            final Map<Attribute, ? extends MBeanAttributeInfo> attrMap) {
        PropertySupport[] supports = new PropertySupport[attrMap.size()];
        
        int i = 0;        
        for (Attribute attr : attrMap.keySet()) {
            MBeanAttributeInfo info = attrMap.get(attr);
            supports[i++] = 
                JBIPropertySupportFactory.getPropertySupport(this, attr, info);
        }
        return supports; 
    }
    
    protected AdministrationService getAdminService() {
        return getAppserverJBIMgmtController().getJBIAdministrationService();
    }  
    
    
    /**
     * Returns all the properties of the leaf node to disply in the properties
     * window (or Sheet). This must be overriden in order for the Sheet to be
     * processed.
     *
     * @returns a java.util.Map of all properties to be accessed from the Sheet.
     */
    protected abstract Map<Attribute, MBeanAttributeInfo> getSheetProperties();
    
    /**
     * Sets the property as an attribute to the underlying AMX mbeans. It 
     * usually will delegate to the controller object which is responsible for
     * finding the correct AMX mbean objectname in order to execute a 
     * JMX setAttribute.
     *
     * @param attrName The name of the property to be set.
     * @param value The value retrieved from the property sheet to be set in the
     *        backend.
     * @returns the updated Attribute accessed from the Sheet.
     */
    public abstract Attribute setSheetProperty(String attrName, Object value);
           
    /**
     * Returns the logger for all nodes.
     *
     * @returns The java.util.logging.Logger impl. for this node.
     */
    protected final static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("org.netbeans.modules.sun.manager.jbi.nodes");
        }
        return logger;
    }
        
    private void setNodeProperties(NodeType nodeType) {
        this.nodeType = nodeType;
        setDisplayName(getNodeDisplayName());
//        setIconBaseWithExtension(getNodeIconPath());
        setShortDescription(getNodeShortDescription());
    }
}
