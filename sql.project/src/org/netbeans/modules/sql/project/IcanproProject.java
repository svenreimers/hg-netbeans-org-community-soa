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


package org.netbeans.modules.sql.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;


import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ant.AntArtifact;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.modules.sql.project.spi.JbiArtifactProvider;
import org.netbeans.modules.compapp.projects.base.ui.IcanproCustomizerProvider;
import org.netbeans.modules.compapp.projects.base.ui.IcanproLogicalViewProvider;
import org.netbeans.modules.compapp.projects.base.ui.customizer.IcanproProjectProperties;
import org.netbeans.spi.project.SubprojectProvider;
import org.netbeans.spi.project.support.ant.AntProjectEvent;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.netbeans.spi.project.support.ant.AntProjectListener;
import org.netbeans.spi.project.support.ant.GeneratedFilesHelper;
import org.netbeans.spi.project.support.ant.ProjectXmlSavedHook;
import org.netbeans.spi.project.ui.PrivilegedTemplates;
import org.netbeans.spi.project.ui.RecommendedTemplates;
import org.netbeans.spi.project.support.ant.ReferenceHelper;
import org.netbeans.spi.project.support.ant.PropertyEvaluator;
import org.netbeans.spi.project.support.ant.SourcesHelper;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.netbeans.spi.queries.FileBuiltQueryImplementation;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Mutex;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;
import org.netbeans.spi.project.AuxiliaryConfiguration;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Represents one ejb module project
 * @author Chris Webster
 */
public final class IcanproProject implements Project, AntProjectListener {

//    private static final Icon PROJECT_ICON = new ImageIcon(Utilities.loadImage("com/sun/jbi/ui/devtoolT/projects/icanpro/ui/resources/icanproProjectIcon.gif")); // NOI18N
    private static final Icon PROJECT_ICON = new ImageIcon(Utilities.loadImage("org/netbeans/modules/sql/project/ui/resources/sqlproProjectIcon.gif")); // NOI18N
    public static final String SOURCES_TYPE_ICANPRO = "BIZPRO";

    private final AntProjectHelper helper;
    private final PropertyEvaluator eval;
    private final ReferenceHelper refHelper;
    private final GeneratedFilesHelper genFilesHelper;
    private final Lookup lookup;

    public IcanproProject(final AntProjectHelper helper) throws IOException {
        this.helper = helper;
        eval = createEvaluator();
        AuxiliaryConfiguration aux = helper.createAuxiliaryConfiguration();
        refHelper = new ReferenceHelper(helper, aux, helper.getStandardPropertyEvaluator());
        genFilesHelper = new GeneratedFilesHelper(helper);
        lookup = createLookup(aux);
        helper.addAntProjectListener(this);
    }

    public FileObject getProjectDirectory() {
        return helper.getProjectDirectory();
    }

    public String toString() {
        return "IcanproProject[" + getProjectDirectory() + "]"; // NOI18N
    }

    private PropertyEvaluator createEvaluator() {
        // XXX might need to use a custom evaluator to handle active platform substitutions... TBD
        return helper.getStandardPropertyEvaluator();
    }

    PropertyEvaluator evaluator() {
        return eval;
    }

    public Lookup getLookup() {
        return lookup;
    }

    private Lookup createLookup(AuxiliaryConfiguration aux) {
        SubprojectProvider spp = refHelper.createSubprojectProvider();
        FileBuiltQueryImplementation fileBuilt = helper.createGlobFileBuiltQuery(helper.getStandardPropertyEvaluator(),
            new String[] {"${src.dir}/*.java"}, // NOI18N
            new String[] {"${build.classes.dir}/*.class"} // NOI18N
        );
        final SourcesHelper sourcesHelper = new SourcesHelper(helper, evaluator());
        String webModuleLabel = org.openide.util.NbBundle.getMessage(IcanproCustomizerProvider.class, "LBL_Node_EJBModule"); //NOI18N
        String srcJavaLabel = org.openide.util.NbBundle.getMessage(IcanproCustomizerProvider.class, "LBL_Node_Sources"); //NOI18N

        sourcesHelper.addPrincipalSourceRoot("${"+IcanproProjectProperties.SOURCE_ROOT+"}", webModuleLabel, /*XXX*/null, null);
        sourcesHelper.addPrincipalSourceRoot("${"+IcanproProjectProperties.SRC_DIR+"}", srcJavaLabel, /*XXX*/null, null);

        sourcesHelper.addTypedSourceRoot("${"+IcanproProjectProperties.SRC_DIR+"}", SOURCES_TYPE_ICANPRO, srcJavaLabel, /*XXX*/null, null);
        // sourcesHelper.addTypedSourceRoot("${"+IcanproProjectProperties.SRC_DIR+"}", JavaProjectConstants.SOURCES_TYPE_JAVA, srcJavaLabel, /*XXX*/null, null);
        ProjectManager.mutex().postWriteRequest(new Runnable() {
            public void run() {
                sourcesHelper.registerExternalRoots(FileOwnerQuery.EXTERNAL_ALGORITHM_TRANSIENT);
            }
        });
        return Lookups.fixed(new Object[] {
            new Info(),
            aux,
            helper.createCacheDirectoryProvider(),
            // XXX the helper should not be exposed
            helper,
            spp,
            new IcanproActionProvider( this, helper, refHelper ),
            new IcanproLogicalViewProvider(this, helper, evaluator(), spp, refHelper),
            new IcanproCustomizerProvider( this, helper, refHelper ),
            new JbiArtifactProviderImpl(),
            new ProjectXmlSavedHookImpl(),
            new ProjectOpenedHookImpl(),
            fileBuilt,
            new RecommendedTemplatesImpl(),
            refHelper,
            sourcesHelper.createSources(),
            helper.createSharabilityQuery(evaluator(),
                new String[] {"${"+IcanproProjectProperties.SOURCE_ROOT+"}"},
                new String[] {
                    "${"+IcanproProjectProperties.BUILD_DIR+"}",
                    "${"+IcanproProjectProperties.DIST_DIR+"}"}
                )
        });
    }

    public void configurationXmlChanged(AntProjectEvent ev) {
        if (ev.getPath().equals(AntProjectHelper.PROJECT_XML_PATH)) {
            // Could be various kinds of changes, but name & displayName might have changed.
            Info info = (Info)getLookup().lookup(ProjectInformation.class);
            info.firePropertyChange(ProjectInformation.PROP_NAME);
            info.firePropertyChange(ProjectInformation.PROP_DISPLAY_NAME);
        }
    }

    public void propertiesChanged(AntProjectEvent ev) {
        // currently ignored
        //TODO: should not be ignored!
    }

    String getBuildXmlName() {
        String storedName = helper.getStandardPropertyEvaluator().getProperty(IcanproProjectProperties.BUILD_FILE);
        return storedName == null ? GeneratedFilesHelper.BUILD_XML_PATH : storedName;
    }

    // Package private methods -------------------------------------------------

    FileObject getSourceDirectory() {
        String srcDir = helper.getStandardPropertyEvaluator().getProperty("src.dir"); // NOI18N
        return helper.resolveFileObject(srcDir);
    }

    /** Return configured project name. */
    public String getName() {
        return (String) ProjectManager.mutex().readAccess(new Mutex.Action() {
            public Object run() {
                Element data = helper.getPrimaryConfigurationData(true);
                // XXX replace by XMLUtil when that has findElement, findText, etc.
                NodeList nl = data.getElementsByTagNameNS(SQLproProjectType.PROJECT_CONFIGURATION_NAMESPACE, "name");
                if (nl.getLength() == 1) {
                    nl = nl.item(0).getChildNodes();
                    if (nl.getLength() == 1 && nl.item(0).getNodeType() == Node.TEXT_NODE) {
                        return ((Text) nl.item(0)).getNodeValue();
                    }
                }
                return "???"; // NOI18N
            }
        });
    }

    // Private innerclasses ----------------------------------------------------

    private final class Info implements ProjectInformation {

        private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

        Info() {}

        void firePropertyChange(String prop) {
            pcs.firePropertyChange(prop, null, null);
        }

        public String getName() {
            return IcanproProject.this.getName();
        }

        public String getDisplayName() {
            return IcanproProject.this.getName();
        }

        public Icon getIcon() {
            return PROJECT_ICON;
        }

        public Project getProject() {
            return IcanproProject.this;
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            pcs.addPropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            pcs.removePropertyChangeListener(listener);
        }

    }

    private final class ProjectXmlSavedHookImpl extends ProjectXmlSavedHook {

        ProjectXmlSavedHookImpl() {}

        protected void projectXmlSaved() throws IOException {
            genFilesHelper.refreshBuildScript(
                GeneratedFilesHelper.BUILD_IMPL_XML_PATH,
                IcanproProject.class.getResource("resources/build-impl.xsl"),
                false);
            genFilesHelper.refreshBuildScript(
                getBuildXmlName(),
                IcanproProject.class.getResource("resources/build.xsl"),
                false);
        }

    }

    private final class ProjectOpenedHookImpl extends ProjectOpenedHook {

        ProjectOpenedHookImpl() {}

        protected void projectOpened() {
            try {
                // Check up on build scripts.
                genFilesHelper.refreshBuildScript(
                GeneratedFilesHelper.BUILD_IMPL_XML_PATH,
                IcanproProject.class.getResource("resources/build-impl.xsl"),
                true);
                genFilesHelper.refreshBuildScript(
                getBuildXmlName(),
                IcanproProject.class.getResource("resources/build.xsl"),
                true);
            } catch (IOException e) {
                ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
            }

            if (IcanproLogicalViewProvider.hasBrokenLinks(helper, refHelper)) {
                // BrokenReferencesSupport.showAlert();
            }
        }

        protected void projectClosed() {
            // Probably unnecessary, but just in case:
            try {
                ProjectManager.getDefault().saveProject(IcanproProject.this);
            } catch (IOException e) {
                ErrorManager.getDefault().notify(e);
            }
        }

    }

    /**
     * Exports the main JAR as an official build product for use from other scripts.
     * The type of the artifact will be {@link AntArtifact}.
     */
    private final class JbiArtifactProviderImpl implements JbiArtifactProvider {

        public AntArtifact[] getBuildArtifacts() {
            return new AntArtifact[] {
                helper.createSimpleAntArtifact(JbiArtifactProvider.ARTIFACT_TYPE_JBI_ASA+":"+
                        helper.getStandardPropertyEvaluator().getProperty(IcanproProjectProperties.JBI_SETYPE_PREFIX),
                        IcanproProjectProperties.SE_DEPLOYMENT_JAR,
                        helper.getStandardPropertyEvaluator(), "dist_se", "clean"), // NOI18N
            };
        }

        public String getJbiServiceAssemblyType() {
            return helper.getStandardPropertyEvaluator().getProperty(IcanproProjectProperties.JBI_SETYPE_PREFIX);
        }
    }

    private static final class RecommendedTemplatesImpl implements RecommendedTemplates, PrivilegedTemplates {

        // List of primarily supported templates

        private static final String[] TYPES = new String[] {
            "XML",                  // NOI18N
            "simple-files"          // NOI18N
        };

        private static final String[] PRIVILEGED_NAMES = new String[] {
            "Templates/CAPS/schema.xsd",
            "Templates/CAPS/untitled.bpel",
            "Templates/CAPS/untitled.wsdl"
        };

        public String[] getRecommendedTypes() {
            return TYPES;
        }

        public String[] getPrivilegedTemplates() {
            return PRIVILEGED_NAMES;
        }

    }
}
