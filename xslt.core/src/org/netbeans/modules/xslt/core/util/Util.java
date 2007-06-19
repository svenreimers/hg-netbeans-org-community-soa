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
package org.netbeans.modules.xslt.core.util;

import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.openide.filesystems.FileObject;
import org.netbeans.modules.xml.retriever.catalog.Utilities;
import org.netbeans.modules.xml.xam.ModelSource;
import org.netbeans.modules.xml.catalogsupport.ProjectConstants;
import org.netbeans.modules.xslt.model.spi.XslModelFactory;
import org.netbeans.modules.xslt.model.XslModel;


/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 */
public class Util {
    
    public static XslModel getXslModel(FileObject xslFo) {
        XslModel model = null;
        if (xslFo != null) {
            ModelSource modelSource = Utilities.getModelSource(xslFo, true);
            model = XslModelFactory.XslModelFactoryAccess.getFactory().getModel(modelSource);
        }
        
        return model;
    }

    public static FileObject getProjectSource(Project project) {
        FileObject projectSource = null;
        if (project == null) {
            return null; // sometimes project couldn't be founded for nb development project
//            throw new IllegalArgumentException("project shouldn't be null");
        }
        Sources sources = ProjectUtils.getSources(project);
        SourceGroup[] sourceGroups = sources.getSourceGroups(ProjectConstants.SOURCES_TYPE_XML);
        if (sourceGroups != null && sourceGroups.length > 0) {
            projectSource = sourceGroups[0].getRootFolder();
        }

        return projectSource;
    }

    public static Project getProject(FileObject projectFo) {
        return FileOwnerQuery.getOwner(projectFo);
    }

}
