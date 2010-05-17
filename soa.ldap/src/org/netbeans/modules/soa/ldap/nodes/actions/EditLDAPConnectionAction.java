/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
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

package org.netbeans.modules.soa.ldap.nodes.actions;

import java.util.HashSet;
import java.util.Set;
import org.netbeans.modules.soa.ldap.LDAP;
import org.netbeans.modules.soa.ldap.LDAPConnection;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author anjeleevich
 */
public class EditLDAPConnectionAction extends NodeAction {

    public EditLDAPConnectionAction() {
    }

    @Override
    protected void performAction(Node[] nodes) {
        LDAPConnection connection = getLDAPConnection(nodes);

        if (connection != null) {
            LDAP.INSTANCE.editConnection(connection);
        }
    }

    @Override
    protected boolean enable(Node[] nodes) {
        return getLDAPConnection(nodes) != null;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(getClass(),
                "EditConnectionActionName"); // NOI18N
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

    private static LDAPConnection getLDAPConnection(Node[] nodes) {
        if (nodes == null) {
            return null;
        }

        Node node = null;
        if (nodes.length == 1) {
            node = nodes[0];
        } else if (nodes.length == 2 && nodes[0] == nodes[1]) {
            node = nodes[0];
        } else if (nodes.length > 2) {
            Set<Node> nodesSet = new HashSet<Node>();
            for (Node n : nodes) {
                nodesSet.add(n);
            }
            if (nodesSet.size() == 1) {
                node = nodesSet.iterator().next();
            }
        }

        if (node == null) {
            return null;
        }

        Lookup lookup = node.getLookup();
        return lookup.lookup(LDAPConnection.class);
    }
}
