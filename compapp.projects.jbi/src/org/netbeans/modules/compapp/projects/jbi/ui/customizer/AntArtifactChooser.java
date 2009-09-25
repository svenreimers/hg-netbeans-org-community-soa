/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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
package org.netbeans.modules.compapp.projects.jbi.ui.customizer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.modules.compapp.projects.jbi.api.POJOHelper;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.netbeans.api.project.ant.AntArtifact;
import org.netbeans.modules.compapp.projects.jbi.api.JbiProjectConstants;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.netbeans.spi.project.ant.AntArtifactProvider;

/** Accessory component used in the ProjectChooser for choosing project
 * artifacts.
 *
 * @author  phrebejk
 */
public class AntArtifactChooser extends javax.swing.JPanel
        implements PropertyChangeListener {

    private List<String> artifactTypes;

    public AntArtifactChooser(List<String> nArtifactTypes, JFileChooser chooser) {
        this.artifactTypes = nArtifactTypes;
        initComponents();
        jListArtifacts.setModel(new DefaultListModel());
        chooser.addPropertyChangeListener(this);
    }

    /** Creates new form JarArtifactChooser */
    public AntArtifactChooser(String artifactType, JFileChooser chooser) {
        List<String> newList = new ArrayList<String>();
        newList.add(artifactType);
        this.artifactTypes = newList;
        initComponents();
        jListArtifacts.setModel(new DefaultListModel());
        chooser.addPropertyChangeListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelJarFiles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListArtifacts = new javax.swing.JList();

        jLabelName.setLabelFor(jTextFieldName);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelName, org.openide.util.NbBundle.getMessage(AntArtifactChooser.class, "LBL_AACH_ProjectName_JLabel"));

        jTextFieldName.setEditable(false);
        jTextFieldName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(AntArtifactChooser.class, "ACS_AACH_ProjectName_A11YDesc"));

        jLabelJarFiles.setLabelFor(jListArtifacts);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelJarFiles, org.openide.util.NbBundle.getMessage(AntArtifactChooser.class, "LBL_AACH_ProjectJarFiles_JLabel"));

        jScrollPane1.setViewportView(jListArtifacts);
        jListArtifacts.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getBundle(AntArtifactChooser.class).getString("ACS_AACH_ProjectJarFiles_A11YName"));
        jListArtifacts.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(AntArtifactChooser.class, "ACS_AACH_ProjectJarFiles_A11YDesc"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabelName)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jTextFieldName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(layout.createSequentialGroup()
                        .add(jLabelJarFiles)
                        .addContainerGap(176, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .add(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabelName)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelJarFiles)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    public void propertyChange(PropertyChangeEvent e) {

        if (JFileChooser.SELECTED_FILES_CHANGED_PROPERTY.equals(e.getPropertyName())) {
            // We have to update the Accessory
            JFileChooser chooser = (JFileChooser) e.getSource();
            File[] dirs = chooser.getSelectedFiles();

            List<Project> projects = getProjects(dirs);
            populateAccessory(projects);
        }
    }

    private List<Project> getProjects(File[] projectDirs) {
        List<Project> ret = new ArrayList<Project>();

        if (projectDirs == null || projectDirs.length == 0) { // #46744
            return ret;
        }

        try {
            ProjectManager projectManager = ProjectManager.getDefault();
            for (File projectDir : projectDirs) {
                FileObject projectRoot = FileUtil.toFileObject(projectDir.getCanonicalFile());

                if (projectRoot != null) {
                    Project project = projectManager.findProject(projectRoot);
                    if (project != null) {
                        ret.add(project);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    private void populateAccessory(List<Project> projects) {
        assert projects != null;

        DefaultListModel model = (DefaultListModel) jListArtifacts.getModel();
        model.clear();

        if (projects.size() == 0) {
            jTextFieldName.setText(""); // NOI18N
        } else if (projects.size() == 1) {
            String projDisplayName =
                    ProjectUtils.getInformation(projects.get(0)).getDisplayName();
            jTextFieldName.setText(projDisplayName);
        } else {
            String msg = NbBundle.getMessage(AntArtifactChooser.class, 
                    "N_PROJECTS", projects.size()); // NOI18N   
            jTextFieldName.setText(msg); 
        }

        for (Project project : projects) {
            AntArtifactProvider prov =
                    project.getLookup().lookup(AntArtifactProvider.class);
            if (prov != null) {
                AntArtifact[] artifacts = prov.getBuildArtifacts();
                Iterator<String> artifactTypeItr = null;
                if (artifacts != null) {
                    for (int i = 0; i < artifacts.length; i++) {
                        artifactTypeItr = this.artifactTypes.iterator();
                        while (artifactTypeItr.hasNext()) {
                            String artifactType = artifactTypeItr.next();
                            if (artifacts[i].getType().startsWith(artifactType)) {
                                model.addElement(new ArtifactItem(artifacts[i]));
                                break;
                            } else {
                                //POJOSE: If the project is a POJO SE based project 
                                //add the artifact
                                if (artifacts[i].getType().equals("jar") && 
                                    project.getClass().getName().equals(JbiProjectConstants.JAVA_SE_PROJECT_CLASS_NAME) && 
                                    POJOHelper.getProjectProperty(project, 
                                                                  JbiProjectConstants.POJO_PROJECT_PROPERTY) != 
                                    null) {
                                    model.addElement(new ArtifactItem(artifacts[i]));
                                    break;
                                }
                            }
                        }
                        
                    }
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelJarFiles;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JList jListArtifacts;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
    
    private static void applyFilefilters(JFileChooser fc, 
            List<FileFilter> filefilterList, FileFilter def) {
        
        if (filefilterList != null) {
            if (def == null) {
                def = fc.getFileFilter();
            }

            for (FileFilter ff : filefilterList) {
                fc.addChoosableFileFilter(ff);
            }
            if (def != null) {
                fc.setFileFilter(def);
            }
        }
    }

    public static AntArtifact[] showDialog(List<String> artifactTypes, 
            Project p, List<FileFilter> filters, FileFilter defFilter) {
        
        JFileChooser chooser = ProjectChooser.projectChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setDialogTitle(NbBundle.getMessage(
                AntArtifactChooser.class, "LBL_AACH_Title")); // NOI18N
        chooser.setApproveButtonText(NbBundle.getMessage(
                AntArtifactChooser.class, "LBL_AACH_SelectProject")); // NOI18N

        AntArtifactChooser accessory = 
                new AntArtifactChooser(artifactTypes, chooser);
        chooser.setAccessory(accessory);
        
        if (p != null) {
            FileObject dobj = p.getProjectDirectory().getParent();
            if (dobj != null) {
                chooser.setCurrentDirectory(FileUtil.toFile(dobj));
            }
        }
        
        if (filters != null) {
            applyFilefilters(chooser, filters, defFilter);
        }

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            DefaultListModel model = 
                    (DefaultListModel) accessory.jListArtifacts.getModel();
            AntArtifact artifacts[] = new AntArtifact[model.size()];

            for (int i = 0; i < artifacts.length; i++) {
                artifacts[i] = ((ArtifactItem) model.getElementAt(i)).getArtifact();
            }

            return artifacts;
        } else {
            return null;
        }
    }

    /** Shows dialog with the artifact chooser
     * @return null if canceled selected jars if some jars selected
     */
    public static AntArtifact[] showDialog(String artifactType, Project p) {
        List<String> arts = new ArrayList<String>();
        arts.add(artifactType);
        return showDialog(arts, p, null, null);
    }

    private static class ArtifactItem {

        private AntArtifact artifact;

        ArtifactItem(AntArtifact artifact) {
            this.artifact = artifact;
        }

        AntArtifact getArtifact() {
            return artifact;
        }

        public String toString() {
            return artifact.getArtifactLocations()[0].toString();
        }
    }
}
