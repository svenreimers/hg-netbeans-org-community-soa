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

package org.netbeans.modules.compapp.javaee.sunresources.tool.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.modules.compapp.javaee.sunresources.ResourceAggregator;
import org.netbeans.modules.compapp.javaee.sunresources.SunResourcesUtil;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.Resources;

import org.netbeans.modules.compapp.javaee.sunresources.tool.annotation.JavaEEAnnotationProcessor;
import org.netbeans.modules.compapp.javaee.sunresources.tool.archive.ArchiveConstants.ArchiveType;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.CMap;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.CMapNode;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.EJBDepend;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.EJBNode;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.MDBNode;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.ResourceDepend;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.ResourceNode;
import org.netbeans.modules.compapp.javaee.sunresources.tool.cmap.ResourceNode.ResourceType;
import org.netbeans.modules.compapp.javaee.sunresources.tool.graph.JAXBHandler;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 * @author echou
 *
 */
public class ApplicationArchive extends Archive {

    private Project p;
    private String name;
    private ResourceAggregator resAggregator;
    
    private CMap cmap;
    private SunResourcesDDJaxbHandler sunResourcesDD;
    private JAXBHandler jaxbHandler;
    private List<Archive> subProjects = new ArrayList<Archive> ();
    private URLClassLoader appClassLoader;
    
    public ApplicationArchive(Project p) throws Exception {
        this.p = p;
        this.name = ProjectUtils.getInformation(p).getName();
        this.resAggregator = new ResourceAggregator(p);
        this.cmap = new CMap(this.name);
        
        List<Project> childrenProjects = SunResourcesUtil.getSubProjects(p);
        for (int i = 0; i < childrenProjects.size(); i++) {
            Project childProject = childrenProjects.get(i);
            ArchiveConstants.ArchiveType projType = 
                    SunResourcesUtil.getJavaEEProjectType(childProject);
            
            if (projType == ArchiveConstants.ArchiveType.EJB) {
                EJBArchive ejbArchive = new EJBArchive(childProject);
                subProjects.add(ejbArchive);
            } else if (projType == ArchiveConstants.ArchiveType.WAR) {
                WebArchive webArchive = new WebArchive(childProject);
                subProjects.add(webArchive);
            } else {
                throw new Exception (
                        NbBundle.getMessage(ApplicationArchive.class, "EXC_bad_project_type", projType));
            }
        }
        
        /*
        this.jaxbHandler = 
                new JAXBHandler(new File(archive, ArchiveConstants.GRAPH_DESCRIPTOR_PATH));
         **/
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    public void open() throws Exception {        
        // aggregate *.sun-resource files into memory here
        FileObject resourceDirFO = SunResourcesUtil.getResourceDir(this.p);
        FileObject[] children = resourceDirFO.getChildren();
        for (int i = 0; i < children.length; i++) {
            FileObject fo = children[i];
            if (!fo.isFolder() && fo.getExt().equalsIgnoreCase("sun-resource")) { // NOI18N
                resAggregator.addResource(fo);
            }
        }
        
        for (int i = 0; i < subProjects.size(); i++) {
            Archive subProject = subProjects.get(i);
            subProject.open();
        }
        
        //process();
    }
    
    /*
    private void process() throws Exception {
        processAnnotations();
        processDD();
        postProcess();
    }
    

    private void processAnnotations() throws Exception {
        JavaEEAnnotationProcessor annoProcessor = 
            new JavaEEAnnotationProcessor(cmap, sunResourcesDD);
        for (int i = 0; i < subProjects.size(); i++) {
            EJBArchive archive = subProjects.get(i);
            for (int j = 0; j < archive.getClassNames().size(); j++) {
                String className = archive.getClassNames().get(j);
                Class<?> cls = Class.forName(className, true, appClassLoader);
                // process each class object
                annoProcessor.process(cls);
            }
        }
        annoProcessor.postProcess();
        
    }
    

    private void processDD() throws Exception {
        for (int i = 0; i < subProjects.size(); i++) {
            EJBArchive archive = subProjects.get(i);
            ArrayList<CMapNode> nodes = archive.getCMapNodes();
            if (nodes != null) {
                for (int j = 0; j < nodes.size(); j++) {
                    CMapNode node = nodes.get(j);
                    cmap.addNode(node.getNodeClass(), node);
                }
            }
        }
    }
    
    private void postProcess() throws Exception {
        // find all the target node for EJB dependency
        for (Iterator<CMapNode> iter = cmap.getNodes(); iter.hasNext(); ) {
            CMapNode node = iter.next();
            ArrayList<EJBDepend> ejbDepends = node.getEjbDepends();
            for (int i = 0; i < ejbDepends.size(); i++) {
                EJBDepend ejbDepend = ejbDepends.get(i);
                CMapNode targetNode = findEJBNodeImplIntf(ejbDepend.getTargetIntfName());
                ejbDepend.setTarget(targetNode);
            }
        }
        
        // find all the target node for Resource dependency, if none exists,
        // create one, since resource nodes are abstract
        for (Iterator<CMapNode> iter = cmap.getNodes(); iter.hasNext(); ) {
            CMapNode node = iter.next();
            ArrayList<ResourceDepend> resDepends = node.getResDepends();
            for (int i = 0; i < resDepends.size(); i++) {
                ResourceDepend resDepend = resDepends.get(i);
                ResourceNode resNode = cmap.findResNode(resDepend);
                if (resNode == null) {
                    String logicalName;
                    if (resDepend.getMappedName() == null || 
                            resDepend.getMappedName().equals("")) {
                        logicalName = resDepend.getTargetResJndiName();
                    } else {
                        logicalName = resDepend.getMappedName();
                    }
                    resNode = new ResourceNode(logicalName, 
                            resDepend.getTargetResType(),
                            resDepend.getTargetResJndiName(), 
                            resDepend.getType(),
                            resDepend.getProps());
                    cmap.addResNode(resNode);
                }
                resDepend.setTarget(resNode);
            }
        }
        
        // after all the Resource nodes are created, we need to find out
        // which resource node is each MDB listening to
        for (Iterator<CMapNode> iter = cmap.getNodes(); iter.hasNext(); ) {
            CMapNode node = iter.next();
            if (node instanceof MDBNode) {
                MDBNode mdbNode = (MDBNode) node;
                ResourceNode resNode = cmap.findResNodeByLogicalName(mdbNode.getMappedName());
                if (resNode == null) {
                    // if the resource node does not exist, then create it
                    resNode = new ResourceNode(mdbNode.getMappedName(),
                            null,
                            null,
                            ResourceType.JMS,
                            null);
                    cmap.addResNode(resNode);
                }
                mdbNode.setTargetListenNode(resNode);
            }
        }
    }
     */

    private EJBNode findEJBNodeImplIntf(String targetIntfName) {
        for (Iterator<CMapNode> iter = cmap.getNodes(); iter.hasNext(); ) {
            CMapNode node = iter.next();
            if (node instanceof EJBNode) {
                EJBNode ejbNode = (EJBNode) node;
                if (ejbNode.implementsInterface(targetIntfName)) {
                    return ejbNode;
                }
            }
        }
        return null;
    }
    
    public void close() throws Exception {
        this.appClassLoader = null;
        this.resAggregator.close();
        this.resAggregator = null;
    }

    public String toString() {
        return this.name;
    }
    
    public CMap getCMap() {
        return this.cmap;
    }
    
    @Override
    public JAXBHandler getJAXBHandler() {
        return this.jaxbHandler;
    }

    public ResourceAggregator getResourceAggregator() {
        return this.resAggregator;
    }

    public FileObject getResourceDir() {
        return null;
    }
}
