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

package org.netbeans.modules.soa.pojo.wizards;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.java.queries.UnitTestForSourceQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Parameters;

/**
 *
 * @author sgenipudi
 */
public class SourceGroups {
    private static final Logger LOGGER = Logger.getLogger(SourceGroups.class.getName());
    
    private SourceGroups() {
    }

    /**
     * Gets the Java source groups of the given <code>project</code>.
     * 
     * @param project the project whose source groups to get; must not be null.
     * @return the Java source groups of the given <code>project</code>, 
     * <strong>excluding</strong> test source groups.
     */ 
    public static SourceGroup[] getJavaSourceGroups(Project project) {
        Parameters.notNull("project", project); //NOI18N
        SourceGroup[] sourceGroups = ProjectUtils.getSources(project).getSourceGroups(
                JavaProjectConstants.SOURCES_TYPE_JAVA);
        List<SourceGroup> result = new ArrayList<SourceGroup>();
        for(SourceGroup sourceGroup : sourceGroups){
            if (UnitTestForSourceQuery.findUnitTests(sourceGroup.getRootFolder()).length > 0){
                result.add(sourceGroup);
            }
        }
        return result.toArray(new SourceGroup[result.size()]);
    }

    /**
     * Checks whether the folder identified by the given <code>packageName</code> is
     * writable or is in a writable parent directory but does not exist yet.
     * 
     * @param sourceGroup the source group of the folder; must not be null.
     * @param packageName the package to check; must not be null.
     * @return true if the folder is writable or can be created (i.e. the parent 
     * folder, or the root folder of the given <code>sourceGroup</code> if there is no other 
     * parent for the folder, is writable), false otherwise.
     */ 
    public static boolean isFolderWritable(SourceGroup sourceGroup, String packageName) {
        Parameters.notNull("sourceGroup", sourceGroup); //NOI18N
        Parameters.notNull("packageName", packageName); //NOI18N
        try {
            FileObject fo = getFolderForPackage(sourceGroup, packageName, false);

            while ((fo == null) && (packageName.lastIndexOf('.') != -1)) {
                packageName = packageName.substring(0, packageName.lastIndexOf('.'));
                fo = getFolderForPackage(sourceGroup, packageName, false);
            }
            return fo == null ? sourceGroup.getRootFolder().canWrite() : fo.canWrite();
        } catch (IOException ex) {
            LOGGER.log(Level.INFO, null, ex);
            return false;
        }
    }

    /**
     * Gets the {@link SourceGroup} of the given <code>folder</code>.
     * 
     * @param sourceGroups the source groups to search; must not be null.
     * @param folder the folder whose source group is to be get; must not be null.
     * @return the source group containing the given <code>folder</code> or 
     * null if not found.
     */ 
    public static SourceGroup getFolderSourceGroup(SourceGroup[] sourceGroups, FileObject folder) {
        Parameters.notNull("sourceGroups", sourceGroups); //NOI18N
        Parameters.notNull("folder", folder); //NOI18N
        for (int i = 0; i < sourceGroups.length; i++) {
            if (FileUtil.isParentOf(sourceGroups[i].getRootFolder(), folder)) {
                return sourceGroups[i];
            }
        }
        return null;
    }

    /**
     * Converts the path of the given <code>folder</code> to a package name.
     * 
     * @param sourceGroup the source group for the folder; must not be null.
     * @param folder the folder to convert; must not be null.
     * @return the package name of the given <code>folder</code>.
     * @throws IllegalStateException if the given <code>folder</code> was not 
     * in the same tree as the root folder of the given <code>sourceGroup</code>.
     */ 
    public static String getPackageForFolder(SourceGroup sourceGroup, FileObject folder) {
        Parameters.notNull("sourceGroup", sourceGroup); //NOI18N
        Parameters.notNull("folder", folder); //NOI18N
        String relative = FileUtil.getRelativePath(sourceGroup.getRootFolder(), folder);
        if (relative == null){
            throw new IllegalStateException("The given folder [ " + folder.getPath() + "] is not in the " +
                    "same tree with [" + sourceGroup.getRootFolder().getPath() + "]"); //NOI18N
        }
        return relative.replace('/', '.'); // NOI18N
    }

    /**
     * Gets the folder representing the given <code>packageName</code>. If the
     * folder does not exists, it will be created.
     * 
     * @param sourceGroup the source group of the package.
     * @param packageName the name of the package.
     * @return the folder representing the given package.
     */
    public static FileObject getFolderForPackage(SourceGroup sourceGroup, String packageName) throws IOException {
        return getFolderForPackage(sourceGroup, packageName, true);
    }

    /**
     * Gets the folder representing the given <code>packageName</code>.
     * 
     * @param sourceGroup the source group of the package; must not be null.
     * @param packageName the name of the package; must not be null.
     * @param create specifies whether the folder should be created if it does not exist.
     * @return the folder representing the given package or null if it was not found.
     */
    public static FileObject getFolderForPackage(SourceGroup sourceGroup, String packageName, boolean create) throws IOException {
        Parameters.notNull("sourceGroup", sourceGroup); //NOI18N
        Parameters.notNull("packageName", packageName); //NOI18N
        
        String relativePkgName = packageName.replace('.', '/');
        FileObject folder = sourceGroup.getRootFolder().getFileObject(relativePkgName);
        if (folder != null) {
            return folder;
        } else if (create) {
            return FileUtil.createFolder(sourceGroup.getRootFolder(), relativePkgName);
        }
        return null;
    }

    /**
     * Gets the {@link SourceGroup} of the given <code>project</code> which contains the
     * given <code>fqClassName</code>.
     * 
     * @param project the project; must not be null.
     * @param fqClassName the fully qualified name of the class whose 
     * source group to get; must not be empty or null.
     * @return the source group containing the given <code>fqClassName</code> or <code>null</code>
     * if the class was not found in the source groups of the project.
     */
    public static SourceGroup getClassSourceGroup(Project project, String fqClassName) {
        Parameters.notNull("project", project); //NOI18N
        Parameters.notEmpty("fqClassName", fqClassName); //NOI18N

        String classFile = fqClassName.replace('.', '/') + ".java"; // NOI18N
        SourceGroup[] sourceGroups = ProjectUtils.getSources(project).getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);
        
        for (SourceGroup sourceGroup : sourceGroups) {
            FileObject classFO = sourceGroup.getRootFolder().getFileObject(classFile);
            if (classFO != null) {
                return sourceGroup;
            }
        }
        return null;
    }

}
