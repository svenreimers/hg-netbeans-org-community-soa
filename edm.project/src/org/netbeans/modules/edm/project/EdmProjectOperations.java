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
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.edm.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.MoveOperationImplementation;
import org.netbeans.spi.project.support.ant.GeneratedFilesHelper;
import org.netbeans.spi.project.support.ant.PropertyEvaluator;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.netbeans.modules.compapp.projects.base.ui.customizer.IcanproProjectProperties;

public class EdmProjectOperations implements DeleteOperationImplementation, CopyOperationImplementation, MoveOperationImplementation {

    private EdmproProject project;

    public EdmProjectOperations(EdmproProject project) {
        this.project = project;
    }

    public List getDataFiles() {
        List files = new ArrayList();
        files.add(project.getSourceDirectory());
        PropertyEvaluator evaluator = project.evaluator();
        String prop = evaluator.getProperty(IcanproProjectProperties.SOURCE_ROOT);
        if (prop != null) {
            FileObject projectDirectory = project.getProjectDirectory();
            FileObject srcDir = project.getAntProjectHelper().resolveFileObject(prop);
            if (projectDirectory != srcDir && !files.contains(srcDir)) {
                files.add(srcDir);
            }
        }
        return files;
    }

    private static void addFile(FileObject projectDirectory, String fileName, List result) {
        FileObject file = projectDirectory.getFileObject(fileName);
        if (file != null) {
            result.add(file);
        }
    }

    public List getMetadataFiles() {
        FileObject projectDirectory = project.getProjectDirectory();
        List files = new ArrayList();
        addFile(projectDirectory, "nbproject", files); // NOI18N
        addFile(projectDirectory, "private", files); // NOI18N
        addFile(projectDirectory, "build.xml", files); // NOI18N        
        addFile(projectDirectory, "Collaborations", files); //NOI18N
        addFile(projectDirectory, projectDirectory.getName(), files); //NOI18N

        return files;
    }

    public void notifyDeleting() throws IOException {
        EdmproActionProvider ap = (EdmproActionProvider) project.getLookup().lookup(EdmproActionProvider.class);
        assert ap != null;

        Lookup context = Lookups.fixed(new Object[0]);
        Properties p = new Properties();
        String[] targetNames = ap.getTargetNames(ActionProvider.COMMAND_CLEAN, context, p);
        FileObject buildXML = project.getProjectDirectory().getFileObject(GeneratedFilesHelper.BUILD_XML_PATH);

        assert targetNames != null;
        assert targetNames.length > 0;

        ActionUtils.runTarget(buildXML, targetNames, p).waitFinished();
    }

    public void notifyDeleted() throws IOException {

        project.getAntProjectHelper().notifyDeleted();
    }

    public void notifyCopied(Project original, File originalPath, final String newName) {
        if (original == null) {
            return;
        }
        project.getReferenceHelper().fixReferences(originalPath);
        project.setName(newName);
    }

    public void notifyCopying() {
    }

    public void notifyMoved(Project original, File originalPath, final String newName) {
        if (original == null) {
            project.getAntProjectHelper().notifyDeleted();
            return;
        }
        project.setName(newName);
        project.getReferenceHelper().fixReferences(originalPath);
    }

    public void notifyMoving() throws IOException {
        notifyDeleting();
    }
}
