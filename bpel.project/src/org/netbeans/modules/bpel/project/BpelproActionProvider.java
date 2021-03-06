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
package org.netbeans.modules.bpel.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Task;
import org.openide.util.TaskListener;
import org.openide.windows.IOProvider;
import org.openide.windows.OutputWriter;
import org.netbeans.modules.j2ee.deployment.plugins.api.*;
import org.netbeans.api.debugger.*;
import org.netbeans.modules.j2ee.deployment.devmodules.api.*;
import org.netbeans.modules.compapp.projects.base.ui.customizer.IcanproProjectProperties;
import org.netbeans.modules.compapp.projects.base.ui.customizer.VisualClassPathItem;

import org.netbeans.spi.project.support.ant.ReferenceHelper;
import org.openide.*;
import org.netbeans.api.project.ant.AntArtifact;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.netbeans.modules.bpel.project.ProjectConstants;

class BpelproActionProvider implements ActionProvider {

    // Commands available from Web project
    private static final String[] supportedActions = {
        COMMAND_BUILD,
        COMMAND_CLEAN,
        COMMAND_REBUILD,
        COMMAND_DELETE, 
        ProjectConstants.POPULATE_CATALOG,
        COMMAND_DELETE,
        COMMAND_COPY,
        COMMAND_MOVE,
        COMMAND_RENAME
    };

    // Project
    BpelproProject project;

    // Ant project helper of the project
    private AntProjectHelper antProjectHelper;
    private ReferenceHelper refHelper;

    /** Map from commands to ant targets */
    Map/*<String,String[]>*/ commands;

    public BpelproActionProvider(BpelproProject project, AntProjectHelper antProjectHelper, ReferenceHelper refHelper) {
        commands = new HashMap();
        commands.put(COMMAND_BUILD, new String[] {"dist_se"}); // NOI18N
        commands.put(COMMAND_CLEAN, new String[] {"clean"}); // NOI18N
        commands.put(COMMAND_REBUILD, new String[] {"clean", "dist_se"}); // NOI18N
        commands.put(ProjectConstants.POPULATE_CATALOG, new String[] {"populate"});

        this.antProjectHelper = antProjectHelper;
        this.project = project;
        this.refHelper = refHelper;
    }
    
    /**
     * @return array of targets or null to stop execution; can return empty array
     */
    /*private*/ String[] getTargetNames(String command, Lookup context, Properties p) throws IllegalArgumentException {
        String[] targetNames = (String[])commands.get(command);
        return targetNames;
    }
    private FileObject findBuildXml() {
        return project.getProjectDirectory().getFileObject(project.getBuildXmlName ());
    }

    public String[] getSupportedActions() {
        return supportedActions;
    }

    public void invokeAction( String command, Lookup context ) throws IllegalArgumentException {
        if (COMMAND_COPY.equals(command)) {
            DefaultProjectOperations.performDefaultCopyOperation(project);
            return ;
        }
        
        if (COMMAND_MOVE.equals(command)) {
            DefaultProjectOperations.performDefaultMoveOperation(project);
            return ;
        }
        
        if (COMMAND_RENAME.equals(command)) {
            DefaultProjectOperations.performDefaultRenameOperation(project, null);
            return ;
        }
        if (COMMAND_DELETE.equals(command)) {
            DefaultProjectOperations.performDefaultDeleteOperation(project);
            return ;
        }
        if (command.equals(ProjectConstants.POPULATE_CATALOG)) {
            BpelProjectRetriever bpRetriever = new BpelProjectRetriever(project.getProjectDirectory());
            bpRetriever.execute();
            return;
        }
        Properties p = null;
        String[] targetNames = (String[])commands.get(command);
            if(command.equals(COMMAND_BUILD)) {
                try {
                    buildDependentProjectsAndRunTask(targetNames, p);
                } catch (IOException e) {
                    ErrorManager.getDefault().notify(e);
                }
            } else {
                runTask(targetNames, p);
            }
    }

    private void runTask(String[] targetNames, Properties p)  {
        try {
            ActionUtils.runTarget(findBuildXml(), targetNames, p);
        }
        catch (IOException e) {
            ErrorManager.getDefault().notify(e);
        }
    }

    private void buildDependentProjectsAndRunTask(String[] targetNames, Properties p) throws IOException  {
        IcanproProjectProperties app = this.project.getProjectProperties();
        List items = (List) app.get(IcanproProjectProperties.JAVAC_CLASSPATH);
        ArrayList artifacts = new ArrayList();

        if (items != null) {
          for (int i = 0, size = items.size(); i < size; i++) {
              VisualClassPathItem vi = (VisualClassPathItem) items.get(i);
              AntArtifact aa = (AntArtifact) vi.getObject();
              String loc =  aa.getProject().getProjectDirectory().getPath() + "/" +  aa.getArtifactLocations()[0].getPath();
              File asa = new File(loc);
              log("Dependent Project artifact jar: "+ loc + ", [" + (asa.exists()?"exist":"missing") + "]");
              if (! asa.exists()) {
                  artifacts.add(aa);
              }
          }
        }
        if(artifacts.size() != 0) {
            //use AntTaskListener which invokes the target on
            //current project build script after all the depedent projects
            //are build
            AntTaskListener antTaskListener = new AntTaskListener(targetNames, p);
            antTaskListener.setTotalTasks(artifacts.size());
            Iterator it = artifacts.iterator();
            while(it.hasNext()) {
                AntArtifact aa = (AntArtifact) it.next();
                String loc =  aa.getProject().getProjectDirectory().getPath() + "/" +  aa.getArtifactLocations()[0].getPath();
                log("Building dependent project "+ loc + "...");
                ExecutorTask task = ActionUtils.runTarget(aa.getScriptFile(), new String[] { aa.getTargetName() }, null);
                task.addTaskListener(antTaskListener);
            }
        } else {
            //no need to build depedent projects
            //directly invoke target on current project build script;
            runTask(targetNames, p);
        }
    }

    public boolean isActionEnabled( String command, Lookup context ) {
        return findBuildXml() != null;
    }

    private boolean isDebugged() {
        return false;
    }

    private void log(String str) {
        OutputWriter out = IOProvider.getDefault().getStdOut();
        out.println(str);
        out.flush();
    }

    class AntTaskListener implements TaskListener {
        int totalTaskCount;
        int finishedTaskCount = 0;
        private String[] mTargetNames;
        private Properties mProperties;

        public AntTaskListener(String[] targetNames, Properties p) {
            this.mTargetNames = targetNames;
            this.mProperties = p;
        }

        public void setTotalTasks(int total) {
            this.totalTaskCount = total;
        }

        public void taskFinished(Task task) {
            finishedTaskCount++;
            if(finishedTaskCount == totalTaskCount) {
                runTask(this.mTargetNames, this.mProperties);
            }
        }
    }
}
