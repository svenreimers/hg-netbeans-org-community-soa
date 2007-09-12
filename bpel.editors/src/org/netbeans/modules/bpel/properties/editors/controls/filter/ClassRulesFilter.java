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
package org.netbeans.modules.bpel.properties.editors.controls.filter;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.modules.bpel.properties.editors.controls.ActionProxyNode;
import org.openide.nodes.Node;

/**
 * This node filter make a decision based on the class of parent an child.
 *
 * By default all class pairs are prohibitted.
 * To allow the pair of classes you have to explicitly call addAllowRule.
 *
 * @author nk160293
 */
public class ClassRulesFilter implements NodeChildFilter {

    private List<ClassPair> allowedPairs;
    
    public ClassRulesFilter() {
        allowedPairs = new ArrayList<ClassPair>();
    }
    
    public boolean isPairAllowed(Node parentNode, Node childNode) {
        //
        Class parentNodeClass = null;
        if (parentNode instanceof ActionProxyNode) {
            parentNodeClass = ((ActionProxyNode)parentNode).getOriginalNodeClass();
        } else {
            parentNodeClass = parentNode.getClass();
        }
        //
        Class childNodeClass = null;
        if (childNode instanceof ActionProxyNode) {
            childNodeClass = ((ActionProxyNode)childNode).getOriginalNodeClass();
        } else {
            childNodeClass = childNode.getClass();
        }
        //
        return isPairAllowed(parentNodeClass, childNodeClass);
    }
    
    public boolean isPairAllowed(Class parentClass, Class childClass) {
        for (ClassPair cPair : allowedPairs) {
            if (cPair.parentClass.isAssignableFrom(parentClass) && 
                    cPair.childClass.isAssignableFrom(childClass)) {
                return true;
            }
        }
        return false;
    }
    
    public void addAllowRule(Class parentClass, Class childClass) {
        allowedPairs.add(new ClassPair(parentClass, childClass));
    }
    
    private class ClassPair {
        public Class parentClass;
        public Class childClass;
        
        public ClassPair(Class pClass, Class cClass) {
            parentClass = pClass;
            childClass = cClass;
        }
    }
}
