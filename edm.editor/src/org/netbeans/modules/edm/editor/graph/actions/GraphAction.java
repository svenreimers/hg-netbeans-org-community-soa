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
package org.netbeans.modules.edm.editor.graph.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;


/**
 * @author Ritesh Adval
 * @version $Revision$
 */
public abstract class GraphAction extends AbstractAction {

    /* log4j logger category */
    private static final String LOG_CATEGORY = GraphAction.class.getName();
    private static transient final Logger mLogger = Logger.getLogger(GraphAction.class.getName());
    private static HashMap actionMap = new HashMap();

    /**
     * called when this action is performed in the ui
     * 
     * @param ev event
     */
    public abstract void actionPerformed(ActionEvent ev);

    public static GraphAction getAction(Class actionClass) {
        GraphAction action = findAction(actionClass);
        if (action != null) {
            return action;
        }

        action = createAction(actionClass);

        return action;
    }

    private static GraphAction findAction(Class actionClass) {
        return (GraphAction) actionMap.get(actionClass);
    }

    private static GraphAction createAction(Class actionClass) {
        GraphAction action = null;
        try {
            action = (GraphAction) actionClass.newInstance();
            actionMap.put(actionClass, action);
        } catch (InstantiationException e1) {
            mLogger.log(Level.INFO,NbBundle.getMessage(GraphAction.class, "LOG.INFO_Error_creating_instance", new Object[] {actionClass.getName()}),e1);
        } catch (IllegalAccessException e2) {
            mLogger.log(Level.INFO,NbBundle.getMessage(GraphAction.class, "LOG.INFO_Error_creating_instance", new Object[] {actionClass.getName()}),e2);
        }

        return action;
    }

    /**
     * return a component that can be used instead of action itself
     * 
     * @return component
     */
    public Component getComponent() {
        return null;
    }
}
