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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.netbeans.modules.dm.virtual.db.ui.property.IElement;
import org.netbeans.modules.dm.virtual.db.ui.property.INode;
import org.netbeans.modules.dm.virtual.db.ui.property.IPropertyGroup;


/**
 * @author Ritesh Adval
 */
public class Template implements INode {

    private String displayName;
    private String name;
    private INode parent;
    private ArrayList<IPropertyGroup> propertyGroupList = new ArrayList<IPropertyGroup>();

    private String toolTip;

    public Template() {
    }

    public void add(IElement element) {
        element.setParent(this);
        addPropertyGroup((IPropertyGroup) element);
    }

    public void addPropertyGroup(IPropertyGroup propertyG) {
        propertyGroupList.add(propertyG);
    }

    public int getChildCount() {
        return propertyGroupList.size();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return this.name;
    }

    public INode getParent() {
        return parent;
    }

    public IPropertyGroup getPropertyGroup(String groupName) {
        Iterator it = propertyGroupList.iterator();
        while (it.hasNext()) {
            IPropertyGroup pg = (IPropertyGroup) it.next();
            if (pg.getName().equals(groupName)) {
                return pg;
            }
        }
        return null;
    }

    public List<IPropertyGroup>  getPropertyGroupList() {
        return propertyGroupList;
    }

    public String getToolTip() {
        return this.toolTip;
    }

    public void setDisplayName(String dName) {
        this.displayName = dName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(INode parent) {
        this.parent = parent;
    }

    public void setToolTip(String tTip) {
        this.toolTip = tTip;
    }
}

