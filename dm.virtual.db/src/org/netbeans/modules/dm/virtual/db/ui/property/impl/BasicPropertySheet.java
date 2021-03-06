/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
package org.netbeans.modules.dm.virtual.db.ui.property.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.netbeans.modules.dm.virtual.db.ui.property.IProperty;
import org.netbeans.modules.dm.virtual.db.ui.property.IPropertyGroup;
import org.netbeans.modules.dm.virtual.db.ui.property.IPropertySheet;
import org.openide.explorer.propertysheet.PropertySheet;
import org.openide.nodes.Node;


/**
 * @author Ritesh Adval
 */
public class BasicPropertySheet extends JPanel implements IPropertySheet {

    private Object bean;
    private PropertyNode pNode;

    public BasicPropertySheet(Object bean, PropertyNode node) {
        this(node);
        this.bean = bean;
    }

    public BasicPropertySheet(PropertyNode node) {
        this.pNode = node;
        init();
    }

    public void commitChanges() {
        if (bean != null) {
            PropUtil.setModifiedPropertyValues(bean, pNode);
        }
    }

    public List<IProperty> getProperties() {
        ArrayList<IProperty> list = new ArrayList<IProperty>();

        Node.PropertySet[] pSets = pNode.getPropertySets();
        for (int i = 0; i < pSets.length; i++) {
            Node.PropertySet pSet = pSets[i];
            Node.Property[] properties = pSet.getProperties();
            for (int j = 0; j < properties.length; j++) {
                Node.Property property = properties[j];
                IProperty p = (IProperty) property;
                list.add(p);
            }
        }
        return list;
    }

    public IPropertyGroup getPropertyGroup(String groupName) {
        return pNode.getPropertyGroup(groupName);
    }

    public Component getPropertySheet() {
        return this;
    }

    public HashMap getPropertyValues() {
        return getPropertyValues(pNode);
    }

    public void setBean(Object bean) {
        this.bean = bean;
        PropUtil.setInitialPropertyValues(bean, null, pNode);
    }

    public void setBean(Object bean, Node node) {
        this.pNode = (PropertyNode) node;
        setBean(bean); // set initialze value from bean to noe
        init();
    }

    private void addPropertyValues(HashMap<String, Object> map, Node.PropertySet pSet) {
        Node.Property[] properties = pSet.getProperties();
        for (int i = 0; i < properties.length; i++) {
            Node.Property property = properties[i];
            IProperty p = (IProperty) property;
            if (p.isReadOnly()) {
                continue;
            }

            try {
                map.put(property.getName(), property.getValue());
            } catch (Exception ex) {
                ex.printStackTrace(); // XXX: use logger
            }
        }
    }

    private HashMap<String, Object> getPropertyValues(PropertyNode node) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Node.PropertySet[] pSets = node.getPropertySets();
        for (int i = 0; i < pSets.length; i++) {
            Node.PropertySet pSet = pSets[i];
            addPropertyValues(map, pSet);
        }
        return map;
    }

    private void init() {
        this.setLayout(new BorderLayout());
        PropertySheet pSheet = new PropertySheet();
        // hack don't show tool bar
        pSheet.add(new JLabel(), BorderLayout.NORTH);
        this.add(pSheet, BorderLayout.CENTER);
        pSheet.setNodes(new Node[] { pNode});

        // set sorting mode to unsorted so that we get properties sorted as defined by
        // position attribute in xml
        try {
            pSheet.setSortingMode(PropertySheet.UNSORTED);
        } catch (Exception ex) {
        }
    }
}

