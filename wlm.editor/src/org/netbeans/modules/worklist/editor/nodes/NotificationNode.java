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
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.worklist.editor.nodes;

import java.beans.PropertyEditorManager;
import org.netbeans.modules.worklist.editor.nodes.children.NotificationChildren;
import org.netbeans.modules.worklist.editor.utils.DisplayNameBuilder;
import org.netbeans.modules.worklist.editor.utils.StringUtils;
import org.netbeans.modules.wlm.model.api.TNotification;
import org.netbeans.modules.worklist.editor.nodes.properties.NameProperty;
import org.openide.ErrorManager;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author anjeleevich
 */
public class NotificationNode extends WLMNode<TNotification> {
    private String cachedName;

    public NotificationNode(TNotification notification, Lookup lookup) {
        this(notification, new NotificationChildren(notification, lookup),
                lookup);
    }
    
    public NotificationNode(TNotification notification, Children children,
            Lookup lookup)
    {
        super(notification, children, lookup, "notification.png");
        updateDisplayName();
    }

    @Override
    public void updateDisplayName() {
        TNotification notification = getWLMComponent();

        String name = notification.getName();

        if (name == null) {
            name = "";
        } else {
            name = name.trim();
        }

        if (!StringUtils.equals(name, cachedName)) {
            cachedName = name;

            DisplayNameBuilder builder = new DisplayNameBuilder();

            if (name.length() > 0) {
                builder.append(name);
            } else {
                builder.append("Unnamed notification");
            }

            setDisplayName(builder);
        }
    }

    @Override
    public WLMNodeType getType() {
        return WLMNodeType.NOTIFICATION;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        set.put(new NameProperty(getWLMComponent()));

        sheet.put(set);

        return sheet;
    }

    @Override
    protected void fireWLMPropertiesChanged() {
        String newName = getWLMComponent().getName();
        firePropertyChange(NameProperty.NAME, "", newName); // NOI18N
    }
}
