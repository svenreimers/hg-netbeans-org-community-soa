/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.bpel.nodes.actions;

import org.netbeans.modules.bpel.editors.api.nodes.actions.ActionType;
import org.netbeans.modules.xml.reference.ReferenceUtil;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2009.09.24
 */
public class OpenWebServiceModule extends UpdateWebService {

    protected String getBundleName() {
        return NbBundle.getMessage(UpdateWebService.class, "CTL_OpenWebServiceModule"); // NOI18N
    }
    
    public void performAction(Node[] nodes) {
        FileObject wsdl = getWsdlFromEjbModule(nodes);
//System.out.println();
//System.out.println("ejb wsdl: "  + wsdl);

        if (wsdl == null) {
            return;
        }
        Project project = ReferenceUtil.getProject(wsdl);

        if (project == null) {
            return;
        }
        OpenProjects.getDefault().open(new Project[] { project }, false);
    }

    public ActionType getType() {
        return ActionType.OPEN_WEB_SERVICE_MODULE;
    }
}
