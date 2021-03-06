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

package org.netbeans.modules.compapp.projects.jbi;

import org.netbeans.modules.compapp.projects.jbi.api.JbiProjectConstants;
import org.netbeans.modules.compapp.projects.jbi.api.JbiDefaultComponentInfo;
import org.netbeans.modules.compapp.projects.jbi.api.JbiProjectHelper;
import org.netbeans.modules.compapp.projects.jbi.ui.JbiCustomizerProvider;
import org.netbeans.modules.compapp.projects.jbi.ui.JbiLogicalViewProvider;
import org.netbeans.modules.compapp.projects.jbi.ui.customizer.JbiProjectProperties;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ant.AntArtifact;
import org.netbeans.spi.java.project.support.ui.BrokenReferencesSupport;
import org.netbeans.spi.project.AuxiliaryConfiguration;
import org.netbeans.spi.project.SubprojectProvider;
import org.netbeans.spi.project.ant.AntArtifactProvider;
import org.netbeans.spi.project.support.ant.*;
import org.netbeans.spi.project.ui.PrivilegedTemplates;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.netbeans.spi.project.ui.RecommendedTemplates;
import org.netbeans.spi.queries.FileBuiltQueryImplementation;
import org.openide.ErrorManager;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Lookup;
import org.openide.util.Mutex;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.queries.FileEncodingQuery;
import org.netbeans.modules.compapp.projects.jbi.queries.JbiProjectEncodingQueryImpl;
import org.netbeans.modules.sun.manager.jbi.management.model.ComponentInformationParser;
import org.netbeans.modules.sun.manager.jbi.management.model.JBIComponentStatus;
import org.netbeans.spi.project.support.ant.ReferenceHelper.RawReference;
import org.netbeans.spi.project.support.ant.SourcesHelper.SourceRootConfig;
import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileUtil;
import org.openide.util.ImageUtilities;

/**
 * Represents one Composite Application (JBI) Project.
 *
 * @author Chris Webster
 */
public final class JbiProject 
        implements Project, AntProjectListener, ProjectPropertyProvider {

    private static final Icon PROJECT_ICON = new ImageIcon(
            ImageUtilities.loadImage(
            "org/netbeans/modules/compapp/projects/jbi/ui/resources/composite_application_project.png" // NOI18N
            )
            ); // NOI18N    

    public static final String SOURCES_TYPE_JBI = "JBI"; // NOI18N

    //public static final String MODULE_INSTALL_NAME = "modules/org-netbeans-modules-compapp-projects-jbi.jar"; // NOI18N
    public static final String JAVA_MODULE_INSTALL_NAME = "modules/org-netbeans-modules-junit.jar"; // NOI18N
    public static final String ENTERPRISE_MODULE_INSTALL_NAME = "modules/org-netbeans-modules-j2ee-sun-appsrv.jar"; // NOI18N
    public static final String XML_MODULE_INSTALL_NAME = "modules/org-netbeans-modules-xml-wsdl-extensions.jar"; // NOI18N
    public static final String IDE_MODULE_INSTALL_NAME = "modules/org-netbeans-modules-xml-wsdl-model.jar"; // NOI18N
    public static final String SOA_MODULE_INSTALL_NAME = "modules/org-netbeans-modules-compapp-projects-jbi.jar"; // NOI18N
    
    //public static final String MODULE_INSTALL_CBN = "org.netbeans.modules.compapp.projects.jbi"; // NOI18N
    public static final String JAVA_MODULE_INSTALL_CBN = "org.netbeans.modules.junit"; // NOI18N
    public static final String ENTERPRISE_MODULE_INSTALL_CBN = "org.netbeans.modules.j2ee.sun.api"; // NOI18N
    public static final String XML_MODULE_INSTALL_CBN = "org.netbeans.modules.xml.wsdl.model.extensions"; // NOI18N
    public static final String IDE_MODULE_INSTALL_CBN = "org.netbeans.modules.xml.wsdl.model"; // NOI18N
    public static final String SOA_MODULE_INSTALL_CBN = "org.netbeans.modules.compapp.projects.jbi"; // NOI18N
    
    //public static final String MODULE_INSTALL_DIR = "module.install.dir"; // NOI18N
    public static final String JAVA_MODULE_INSTALL_DIR = "java.module.install.dir"; // NOI18N
    public static final String ENTERPRISE_MODULE_INSTALL_DIR = "enterprise.module.install.dir"; // NOI18N
    public static final String XML_MODULE_INSTALL_DIR = "xml.module.install.dir"; // NOI18N
    public static final String IDE_MODULE_INSTALL_DIR = "ide.module.install.dir"; // NOI18N
    public static final String SOA_MODULE_INSTALL_DIR = "soa.module.install.dir"; // NOI18N
    
    public static final String COMPONENT_INFO_FILE_NAME = "ComponentInformation.xml"; // NOI18N    
    public static final String BINDING_COMPONENT_INFO_FILE_NAME = "BindingComponentInformation.xml"; // NOI18N    
    public static final String ASSEMBLY_INFO_FILE_NAME = "AssemblyInformation.xml"; // NOI18N
    
//    /** Last time in ms when the Broken References alert was shown. */
//    private static long brokenAlertLastTime = 0;
//    
//    /** Is Broken References alert shown now? */
//    private static boolean brokenAlertShown = false;
//    
//    /** Timeout within which request to show alert will be ignored. */
//    private static int BROKEN_ALERT_TIMEOUT = 1000;
    
    private final AntProjectHelper helper;
    private final PropertyEvaluator eval;
    private final ReferenceHelper refHelper;
    private final GeneratedFilesHelper genFilesHelper;
    private final Lookup lookup;
    private AntBasedProjectType abpt;
    private JbiLogicalViewProvider lvp;    
    private FileChangeListener casaFileListener;
    
    private static final Logger LOG = Logger.getLogger(JbiProject.class.getName());
    
    
    /**
     * Creates a new JbiProject object.
     *
     * @param helper DOCUMENT ME!
     * @param abpt DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public JbiProject(final AntProjectHelper helper, AntBasedProjectType abpt)
            throws IOException {
        this.helper = helper;
        this.abpt = abpt;
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
    
    @Override
    public String toString() {
        return "JbiProject[" + getProjectDirectory() + "]"; // NOI18N
    }
    
    private PropertyEvaluator createEvaluator() {
        // XXX might need to use a custom evaluator to handle active platform substitutions... TBD
        return helper.getStandardPropertyEvaluator();
    }
        
    public Lookup getLookup() {
        return lookup;
    }
    
    private Lookup createLookup(AuxiliaryConfiguration aux) {
        SubprojectProvider spp = new JbiSubprojectProvider(refHelper.createSubprojectProvider());
        FileBuiltQueryImplementation fileBuilt = helper.createGlobFileBuiltQuery(
                helper.getStandardPropertyEvaluator(), new String[] {"${src.dir}/*.java"}, // NOI18N
                new String[] {"${build.classes.dir}/*.class"} // NOI18N
        );
        final SourcesHelper sourcesHelper = new SourcesHelper(this, helper, eval);
        String webModuleLabel = org.openide.util.NbBundle.getMessage(
                JbiCustomizerProvider.class, "LBL_Node_EJBModule" // NOI18N
                );
        String srcJavaLabel = org.openide.util.NbBundle.getMessage(
                JbiCustomizerProvider.class, "LBL_Node_Sources" // NOI18N
                );

        SourceRootConfig sourceRootConfig =
                sourcesHelper.sourceRoot("${" + JbiProjectProperties.SOURCE_ROOT + "}"); // NOI18N
        sourceRootConfig.displayName(webModuleLabel);
        sourceRootConfig.add();

        sourceRootConfig =
                sourcesHelper.sourceRoot("${" + JbiProjectProperties.SRC_DIR + "}"); // NOI18N
        sourceRootConfig.displayName(srcJavaLabel);
        sourceRootConfig.add();

        sourceRootConfig =
                sourcesHelper.sourceRoot("${" + JbiProjectProperties.SRC_DIR + "}"); // NOI18N
        sourceRootConfig.displayName(srcJavaLabel);
        sourceRootConfig.type(SOURCES_TYPE_JBI);
        sourceRootConfig.add();

        sourceRootConfig =
                sourcesHelper.sourceRoot("${" + JbiProjectProperties.SRC_DIR + "}"); // NOI18N
        sourceRootConfig.displayName(srcJavaLabel);
        sourceRootConfig.type(JavaProjectConstants.SOURCES_TYPE_JAVA);
        sourceRootConfig.add();
        
        ProjectManager.mutex().postWriteRequest(
                new Runnable() {
            public void run() {
                sourcesHelper.registerExternalRoots(
                        FileOwnerQuery.EXTERNAL_ALGORITHM_TRANSIENT
                        );
            }
        }
        );
        
        casaFileListener = new FileChangeAdapter() {
            @Override
            public void fileChanged(FileEvent fe) {                
                propertiesChanged(null);
            }
            
            @Override
            public void fileDeleted(FileEvent fe) {
                propertiesChanged(null);
            }
        };
        
        return Lookups.fixed(new Object[] {
            this,
            new Info(),
            aux,
            helper.createCacheDirectoryProvider(),
            helper,
            eval,
            spp,
            new JbiActionProvider(this, helper),
            lvp = new JbiLogicalViewProvider(this, helper, eval, spp, refHelper),
            new JbiCustomizerProvider(this, helper, refHelper),
            new AntArtifactProviderImpl(), 
            new ProjectXmlSavedHookImpl(),
            new ProjectOpenedHookImpl(),
            new JbiProjectOperations(this),
            new HashSet<TopComponent>(),
            fileBuilt,
            new RecommendedTemplatesImpl(),
            new JbiProjectEncodingQueryImpl(eval),
            refHelper,
            sourcesHelper.createSources(),
            casaFileListener,
            helper.createSharabilityQuery(
                    eval, new String[] {"${" + JbiProjectProperties.SOURCE_ROOT + "}"}, // NOI18N
                    new String[] {
                "${" + JbiProjectProperties.BUILD_DIR + "}", // NOI18N
                "${" + JbiProjectProperties.DIST_DIR + "}", // NOI18N
                "${" + JbiProjectProperties.TEST_RESULTS_DIR + "}", // NOI18N
                "${" + JbiProjectProperties.SRC_BUILD_DIR + "}" // NOI18N
            }
            )
        }
        );
    }
    
    /**
     * DOCUMENT ME!
     *
     * @param ev DOCUMENT ME!
     */
    public void configurationXmlChanged(AntProjectEvent ev) {
        if (ev.getPath().equals(AntProjectHelper.PROJECT_XML_PATH)) {
            // Could be various kinds of changes, but name & displayName might have changed.
            Info info = (Info) getLookup().lookup(ProjectInformation.class);
            info.firePropertyChange(ProjectInformation.PROP_NAME);
            info.firePropertyChange(ProjectInformation.PROP_DISPLAY_NAME);
        }
    }
    
    /**
     * DOCUMENT ME!
     *
     * @param ev DOCUMENT ME!
     */
    public void propertiesChanged(AntProjectEvent ev) {
        if (lvp != null) {
            lvp.refreshRootNode();
        }
    }
    
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getBuildXmlName() {
        String storedName = helper.getStandardPropertyEvaluator().getProperty(
                JbiProjectProperties.BUILD_FILE
                );
        
        return (storedName == null) ? GeneratedFilesHelper.BUILD_XML_PATH : storedName;
    }
    
    /**
     * Return the test directory of a JBI project
     *
     * @return JBI test directory
     */
    public FileObject getTestDirectory() {
        String testDir = helper.getStandardPropertyEvaluator().getProperty(JbiProjectProperties.TEST_DIR);
        
        try {
            return helper.resolveFileObject(testDir);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Create the test directory of a JBI project
     *
     * @return JBI test directory
     */
    public FileObject createTestDirectory() {
        String testDir = helper.getStandardPropertyEvaluator().getProperty(JbiProjectProperties.TEST_DIR); // NOI18N
        
        if (helper.resolveFileObject(testDir) == null) {
            try {
                getProjectDirectory().createFolder(testDir);
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return helper.resolveFileObject(testDir);
    }
    
    /**
     * Return the test results directory of a JBI project
     *
     * @return JBI test results directory
     */
    public FileObject getTestResultsDirectory() {
        String testResultsDir = helper.getStandardPropertyEvaluator().getProperty(JbiProjectProperties.TEST_RESULTS_DIR);
        
        return helper.resolveFileObject(testResultsDir);
    }
    
    // Package private methods -------------------------------------------------
    FileObject getSourceDirectory() {
        String srcDir = helper.getStandardPropertyEvaluator().getProperty(JbiProjectProperties.SRC_DIR); // NOI18N
        
        return srcDir == null ? null : helper.resolveFileObject(srcDir);
    }
    
    /**
     * Return configured project name.
     *
     * @return DOCUMENT ME!
     */
    public String getName() {
        return (String) ProjectManager.mutex().readAccess(
                new Mutex.Action() {
            public Object run() {
                Element data = helper.getPrimaryConfigurationData(true);
                
                // XXX replace by XMLUtil when that has findElement, findText, etc.
                NodeList nl = data.getElementsByTagNameNS(
                        JbiProjectType.PROJECT_CONFIGURATION_NAMESPACE, "name" // NOI18N
                        );
                
                if (nl.getLength() == 1) {
                    nl = nl.item(0).getChildNodes();
                    
                    if ((nl.getLength() == 1) && (nl.item(0).getNodeType() == Node.TEXT_NODE)) {
                        return ((Text) nl.item(0)).getNodeValue();
                    }
                }
                
                return "???"; // NOI18N
            }
        }
        );
    }
    
    /** Store configured project name. */
    public void setName(final String name) {
        ProjectManager.mutex().writeAccess(new Mutex.Action() {
            public Object run() {
                Element data = helper.getPrimaryConfigurationData(true);
                // XXX replace by XMLUtil when that has findElement, findText, etc.
                NodeList nl = data.getElementsByTagNameNS(JbiProjectType.PROJECT_CONFIGURATION_NAMESPACE, "name"); // NOI18N
                Element nameEl;
                if (nl.getLength() == 1) {
                    nameEl = (Element) nl.item(0);
                    NodeList deadKids = nameEl.getChildNodes();
                    while (deadKids.getLength() > 0) {
                        nameEl.removeChild(deadKids.item(0));
                    }
                } else {
                    nameEl = data.getOwnerDocument().createElementNS(JbiProjectType.PROJECT_CONFIGURATION_NAMESPACE, "name"); // NOI18N
                    data.insertBefore(nameEl, /* OK if null */data.getChildNodes().item(0));
                }
                nameEl.appendChild(data.getOwnerDocument().createTextNode(name));
                helper.putPrimaryConfigurationData(data, true);
                return null;
            }
        });
    }

    /**
     * Gets a list of external service unit names in this project.
     *
     * @return  external service units' names
     */
    public List<String> getExternalServiceUnitNames() {

        List<String> ret = new ArrayList<String>();

        // 1. get all reference projects names
        for (RawReference rawReference : refHelper.getRawReferences()) {
            String foreignProjName = rawReference.getForeignProjectName();
            ret.add(foreignProjName);
        }

        // 2. remove internal su project names
        Element data = helper.getPrimaryConfigurationData(true);
        NodeList libs = data.getElementsByTagNameNS(
                JbiProjectType.PROJECT_CONFIGURATION_NAMESPACE, "included-library" // NOI18N
                );

        for (int i = 0; i < libs.getLength(); i++) {
            Element lib = (Element) libs.item(i);
            String cpItem = lib.getTextContent();
            // cpItem's format: reference.Synchronous3.dist_se
            String projName = cpItem.substring(cpItem.indexOf(".") + 1,
                    cpItem.lastIndexOf("."));
            ret.remove(projName);
        }

        return ret;
    }
   
    public JbiProjectProperties getProjectProperties() {
        return new JbiProjectProperties(this, helper, refHelper);
    }
       
    // Private innerclasses ----------------------------------------------------
    private final class Info implements ProjectInformation {
        private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
        
        Info() {
        }
        
        void firePropertyChange(String prop) {
            pcs.firePropertyChange(prop, null, null);
        }
        
        public String getName() {
            return JbiProject.this.getName();
        }
        
        public String getDisplayName() {
            return JbiProject.this.getName();
        }

        public Icon getIcon() {
            return PROJECT_ICON;
        }
        
        public Project getProject() {
            return JbiProject.this;
        }
        
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            pcs.addPropertyChangeListener(listener);
        }
        
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            pcs.removePropertyChangeListener(listener);
        }
    }
    
    private final class ProjectXmlSavedHookImpl extends ProjectXmlSavedHook {
       
        ProjectXmlSavedHookImpl() {
        }
        
        protected void projectXmlSaved() throws IOException {
            genFilesHelper.refreshBuildScript(
                    GeneratedFilesHelper.BUILD_IMPL_XML_PATH,
                    JbiProject.class.getResource("resources/build-impl.xsl"), false // NOI18N
                    );
            genFilesHelper.refreshBuildScript(
                    getBuildXmlName(), JbiProject.class.getResource("resources/build.xsl"), false // NOI18N
                    );
        }
    }
    
    private final class ProjectOpenedHookImpl extends ProjectOpenedHook {
       
        ProjectOpenedHookImpl() {
        }
       
        protected void projectOpened() {
            try {
                // Check up on build scripts.
                genFilesHelper.refreshBuildScript(
                        GeneratedFilesHelper.BUILD_IMPL_XML_PATH,
                        JbiProject.class.getResource("resources/build-impl.xsl"), true // NOI18N
                        );
                genFilesHelper.refreshBuildScript(
                        getBuildXmlName(), 
                        JbiProject.class.getResource("resources/build.xsl"), true // NOI18N
                        );
            } catch (IOException e) {
                ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
            }
                        
            // Make it easier to run headless builds on the same machine at least.
            ProjectManager.mutex().writeAccess(
                    new Mutex.Action() {
                public Object run() {
                         
                    // 1. Update component info and binding component info if needed...
                    String name = JbiProject.this.getName();
                    // On Solaris x86, JbiProject.this.getProjectDirectory().getPath() 
                    // is missing the preceding "/".
                    //System.out.println("projPath is " + JbiProject.this.getProjectDirectory().getPath());
                    //System.out.println("projPath (absolute) is " + FileUtil.toFile(JbiProject.this.getProjectDirectory()).getAbsolutePath());
                    //String confDir = JbiProject.this.getProjectDirectory().getPath() + "/src/conf"; // NOI18N
                    FileObject projDir = JbiProject.this.getProjectDirectory();
                    String confDir = FileUtil.toFile(projDir).getAbsolutePath() + 
                            File.separator + "src" + File.separator + "conf"; // NOI18N                    
                    updateComponentDocuments(confDir);
                    
                    // 2. Make sure the component target list is not corrupted.
                    JbiProjectProperties projectProperties = getProjectProperties(); 
                    try {
                        projectProperties.fixComponentTargetList();
                    } catch (Exception e) {
                        // The failure is probably due to unresolved references.
                        // Once the reference problem is fixed, we will try
                        // fixing the component target list again.
                        return null;
                    }
                    
                    // 3.1 Migrate old casa.wsdl to <Proj>.wsdl, if applicable
                    String projDirLoc = JbiProject.this.getProjectDirectory().getPath();
                    String srcDirLoc =
                            projDirLoc + File.separator +
                            helper.getStandardPropertyEvaluator().getProperty(
                            JbiProjectProperties.SOURCE_ROOT);
                    String projName = JbiProjectHelper.getJbiProjectName(JbiProject.this);
                    MigrationHelper.migrateCasaWSDL(srcDirLoc, projName);
                    
                    // 3.2 Migrate old compapp properties
                    EditableProperties projectEP = helper.getProperties(
                            AntProjectHelper.PROJECT_PROPERTIES_PATH);
                    MigrationHelper.migrateCompAppProperties(projDirLoc, projectEP);
                    
                    // 3.3 Add project encoding for old projects
                    if (projectEP.getProperty(JbiProjectProperties.SOURCE_ENCODING) == null) {
                        projectEP.setProperty(JbiProjectProperties.SOURCE_ENCODING,
                                // FIXME: maybe we should use Charset.defaultCharset() instead?
                                // See comments in JbiProjectEncodingQueryImpl.java
                                FileEncodingQuery.getDefaultEncoding().name());
                    }
                    
                    helper.putProperties(
                            AntProjectHelper.PROJECT_PROPERTIES_PATH, projectEP);
                    
                    // 4. Set private properties
                    EditableProperties privateEP = helper.getProperties(
                            AntProjectHelper.PRIVATE_PROPERTIES_PATH);
                    privateEP.setProperty(
                            "netbeans.user", System.getProperty("netbeans.user")); // NOI18N
                    
                    InstalledFileLocator installedFileLocator = InstalledFileLocator.getDefault();
                    
                    File f = installedFileLocator.locate(
                            SOA_MODULE_INSTALL_NAME, SOA_MODULE_INSTALL_CBN, false);                    
                    if (f != null) {
                        privateEP.setProperty(SOA_MODULE_INSTALL_DIR, f.getParentFile().getPath());
                    }
                    
                    f = installedFileLocator.locate(
                            JAVA_MODULE_INSTALL_NAME, JAVA_MODULE_INSTALL_CBN, false);                    
                    if (f != null) {
                        privateEP.setProperty(JAVA_MODULE_INSTALL_DIR, f.getParentFile().getPath());
                    }
                    
                    f = installedFileLocator.locate(
                            XML_MODULE_INSTALL_NAME, XML_MODULE_INSTALL_CBN, false);                    
                    if (f != null) {
                        privateEP.setProperty(XML_MODULE_INSTALL_DIR, f.getParentFile().getPath());
                    }
                    
                    f = installedFileLocator.locate(
                            IDE_MODULE_INSTALL_NAME, IDE_MODULE_INSTALL_CBN, false);                    
                    if (f != null) {
                        privateEP.setProperty(IDE_MODULE_INSTALL_DIR, f.getParentFile().getPath());
                    }
                    
                    f = installedFileLocator.locate(
                            ENTERPRISE_MODULE_INSTALL_NAME, ENTERPRISE_MODULE_INSTALL_CBN, false);                    
                    if (f != null) {
                        privateEP.setProperty(ENTERPRISE_MODULE_INSTALL_DIR, f.getParentFile().getPath());
                    }
                    
                    helper.putProperties(AntProjectHelper.PRIVATE_PROPERTIES_PATH, privateEP);
                    
                    try {
                        ProjectManager.getDefault().saveProject(JbiProject.this);
                    } catch (IOException e) {
                        ErrorManager.getDefault().notify(e);
                    }
                    
                    // 5. Create casa file if it doesn't exist yet.
                    CasaHelper.getCasaFileObject(JbiProject.this, true);
                                                        
                    // 6. Update ASI.xml
                    getProjectProperties().saveAssemblyInfo();
                    
                    // 7. Clean up locks.
                    CasaHelper.cleanupLocks(JbiProject.this);
                   
                    return null;
                }   
            }
            );
            
            if (JbiLogicalViewProvider.hasBrokenLinks(helper, refHelper)) {
                BrokenReferencesSupport.showAlert();
            }
            
            String prop = eval.getProperty(JbiProjectProperties.SOURCE_ENCODING);
            if (prop != null) {
                try {
                    Charset c = Charset.forName(prop);
                } catch (IllegalCharsetNameException e) {
                    //Broken property, log & ignore
                    LOG.warning("Illegal charset: " + prop+ " in project: " + // NOI18N
                            FileUtil.getFileDisplayName(getProjectDirectory())); 
                } catch (UnsupportedCharsetException e) {
                    //todo: Needs UI notification like broken references.
                    LOG.warning("Unsupported charset: " + prop+ " in project: " + // NOI18N
                            FileUtil.getFileDisplayName(getProjectDirectory())); 
                }
            }
        }
        
        private void updateComponentDocuments(String confDir) {
            
            try {
                // Load component info..
                File compFile = new File(confDir + File.separator + 
                        JbiProject.COMPONENT_INFO_FILE_NAME);
                List<JBIComponentStatus> compList = 
                        ComponentInformationParser.parse(compFile);
                
                // Load binding component info..
                File bindingCompFile = new File(confDir + File.separator + 
                        JbiProject.BINDING_COMPONENT_INFO_FILE_NAME);
                List<JBIComponentStatus> bindingCompList = 
                        ComponentInformationParser.parse(bindingCompFile);
                
                // Update component namespaces using info from binding component doc
                for (JBIComponentStatus bindingComp : bindingCompList) {
                    String name = bindingComp.getName();
                    List<String> nsList = bindingComp.getNamespaces();
                    for (JBIComponentStatus comp : compList) {
                        if (comp.getName().equals(name)) {
                            comp.setNamespaces(nsList);
                            break;
                        }
                    }
                }
                
                // Get known SE/BCs at design-time
                JbiDefaultComponentInfo defaultCompInfo =
                        JbiDefaultComponentInfo.getJbiDefaultComponentInfo();
                Map<String, JBIComponentStatus> defaultCompInfoMap =
                        defaultCompInfo.getComponentHash();
                
                List<JBIComponentStatus> deltaList = new ArrayList<JBIComponentStatus>();
                
                boolean nsListUpdated = false;
                
                for (String name : defaultCompInfoMap.keySet()) {
                    JBIComponentStatus compInMap = defaultCompInfoMap.get(name);
                    boolean found = false;
                    for (JBIComponentStatus comp : compList) {
                        if (comp.getName().equals(name)) {
                            for (String ns : compInMap.getNamespaces()) {
                                if (comp.addNamespace(ns)) {
                                    nsListUpdated = true;
                                }
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        deltaList.add(compInMap);
                    }
                }
                
                if (deltaList != null && deltaList.size() > 0 || nsListUpdated) {
                    List<JBIComponentStatus> list = new ArrayList<JBIComponentStatus>();
                    list.addAll(compList);
                    list.addAll(deltaList);
                    new ComponentInfoGenerator(confDir, list).doIt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        protected void projectClosed() {
            Set topComponentSet = (Set) JbiProject.this.getLookup().lookup(HashSet.class);
            if (topComponentSet != null) {
                for (Object tc : topComponentSet) {
                    if (tc instanceof TopComponent) {
                        ((TopComponent)tc).close();
                    }
                }
            }
            
            // Probably unnecessary, but just in case:
            try {
                ProjectManager.getDefault().saveProject(JbiProject.this);
            } catch (IOException e) {
                ErrorManager.getDefault().notify(e);
            }
        }
    }
    
    /**
     * Exports the main JAR as an official build product for use from other scripts. The type of
     * the artifact will be {@link AntArtifact}.
     */
    private final class AntArtifactProviderImpl implements AntArtifactProvider {
      
        public AntArtifact[] getBuildArtifacts() {
            return new AntArtifact[] {
                helper.createSimpleAntArtifact(
                        JbiProjectConstants.ARTIFACT_TYPE_JBI_AU, "dist.jar", // NOI18N
                        helper.getStandardPropertyEvaluator(), "dist", "clean" // NOI18N
                        ), // NOI18N
            };
        }
    }
    
    private static final class RecommendedTemplatesImpl 
            implements RecommendedTemplates, PrivilegedTemplates {
        
        // List of primarily supported templates
        private static final String[] TYPES = new String[] {
            "XML", // NOI18N
            "simple-files" // NOI18N
        };
        
        private static final String[] PRIVILEGED_NAMES = new String[] {
            "Templates/XML/WSDL.wsdl",    // NOI18N
            "Templates/XML/XmlSchema.xsd", // NOI18N
            "Templates/XML/retrieveSchemaResource", // NOI18N
            "Templates/XML/retrieveWSDLResource", // NOI18N
        };
        
        public String[] getRecommendedTypes() {
            return TYPES;
        }
        
        public String[] getPrivilegedTemplates() {
            return PRIVILEGED_NAMES;
        }
    }
}
