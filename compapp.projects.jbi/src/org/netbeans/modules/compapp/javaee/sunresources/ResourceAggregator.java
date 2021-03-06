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

package org.netbeans.modules.compapp.javaee.sunresources;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.netbeans.api.project.Project;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.AdminObjectResource;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.ConnectorConnectionPool;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.ConnectorResource;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.JdbcConnectionPool;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.JdbcResource;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.ObjectFactory;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.Property;
import org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13.Resources;
import org.netbeans.modules.compapp.javaee.sunresources.tool.archive.FileUtil;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 *
 * @author echou
 */
public class ResourceAggregator {
    
    public enum OrphanStatus { FILE_ONLY, ANNO_ONLY, BOTH }
    public enum SourceType { EJB, MDB, DD }
    
    private Project p;
    private List<ResourceEntry> resources = new ArrayList<ResourceEntry> ();
    private JAXBContext jc;
    private Unmarshaller unmarshaller;
    private List<PoolEntry> pools = new ArrayList<PoolEntry> ();
    
    /** Creates a new instance of ResourceAggregator */
    public ResourceAggregator() throws Exception {
        this(null);
    }
    
    public ResourceAggregator(Project p) throws Exception {
        this.p = p;
        jc = JAXBContext.newInstance("org.netbeans.modules.compapp.javaee.sunresources.generated.sunresources13", //NOI18N
                this.getClass().getClassLoader());
        unmarshaller = jc.createUnmarshaller();
    }
    
    public Project getProject() {
        return this.p;
    }
    
    public List<ResourceEntry> getResources() {
        return this.resources;
    }
    
    public void createResource(Object resource, FileObject fileObject) {
        ResourceEntry entry = new ResourceEntry();
        entry.resource = resource;
        entry.fileObject = fileObject;
        resources.add(entry);
    }
    
    // called from command-line
    public void addResource(File f) throws Exception {
        Resources inputResources = (Resources) unmarshaller.unmarshal(f);
        List inputList = inputResources.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool();
        for (Iterator iter = inputList.iterator(); iter.hasNext(); ) {
            Object resource = iter.next();
            if (resource instanceof ConnectorConnectionPool ||
                    resource instanceof JdbcConnectionPool) {
                PoolEntry entry = new PoolEntry();
                entry.pool = resource;
                //entry.fileObject = f;
                pools.add(entry);
            } else {
                ResourceEntry entry = new ResourceEntry();
                entry.resource = resource;
                //entry.fileObject = f;
                resources.add(entry);
            }
        }
    }
    
    
    public void addResource(FileObject fo) throws Exception {
        InputStream is = null;
        try {
            is = fo.getInputStream();
            Resources inputResources = (Resources) unmarshaller.unmarshal(is);
            List inputList = inputResources.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool();
            for (Iterator iter = inputList.iterator(); iter.hasNext(); ) {
                Object resource = iter.next();
                if (resource instanceof ConnectorConnectionPool ||
                        resource instanceof JdbcConnectionPool) {
                    PoolEntry entry = new PoolEntry();
                    entry.pool = resource;
                    entry.fileObject = fo;
                    pools.add(entry);
                } else {
                    ResourceEntry entry = new ResourceEntry();
                    entry.resource = resource;
                    entry.fileObject = fo;
                    resources.add(entry);
                }
            }
        } finally {
            if (is != null){
                try {
                    is.close();
                } catch (Exception ex){
                    // Ignore.
                }
            }
        }
    }
     
    
    public String toSunResourcesXML() throws Exception {
        ObjectFactory factory = new ObjectFactory();
        Resources jaxbRoot = factory.createResources();
        // write resources
        for (Iterator<ResourceEntry> iter = resources.iterator(); iter.hasNext(); ) {
            ResourceEntry entry = iter.next();
            jaxbRoot.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool(
                    ).add(entry.resource);
        }
        // write pools
        for (Iterator<PoolEntry> iter = pools.iterator(); iter.hasNext(); ) {
            PoolEntry entry = iter.next();
            jaxbRoot.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool(
                    ).add(entry.pool);
        }
        
        
        // create marshaller
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE); //NOI18N
        
        // write preamble
        StringWriter out = new StringWriter();
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"); //NOI18N
        out.write("<!DOCTYPE resources PUBLIC \"-//Sun Microsystems, Inc.//DTD Application Server 9.0 Resource Definitions //EN\" \"http://www.sun.com/software/appserver/dtds/sun-resources_1_3.dtd\">\n"); //NOI18N
        marshaller.marshal(jaxbRoot, out);
        return out.toString();
    }
    
    public void writeToSunXML(int index) throws Exception {
        Object targetResource = resources.get(index).resource;
        FileObject fileObject = resources.get(index).fileObject;
        if (fileObject == null) {
            throw new Exception(NbBundle.getMessage(ResourceAggregator.class, "EXC_fileobject_null"));
        }
        
        // find all resources that needs to be written to the same FileObject
        ObjectFactory factory = new ObjectFactory();
        Resources jaxbRoot = factory.createResources();
        for (Iterator<ResourceEntry> iter = resources.iterator(); iter.hasNext(); ) {
            ResourceEntry entry = iter.next();
            if (entry.fileObject == null || !entry.fileObject.equals(fileObject)) {
                continue;
            }
            jaxbRoot.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool(
                    ).add(entry.resource);
        }
        
        addReferencedPool(targetResource, jaxbRoot);
        
        FileLock lock = fileObject.lock();
        try {
            OutputStream os = fileObject.getOutputStream(lock);
            PrintWriter out = new PrintWriter(os);
            try {
                Marshaller marshaller = jc.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(jaxbRoot, out);
            } finally {
                FileUtil.safeclose(out);                
                FileUtil.safeclose(os);
            }
        } finally {
            lock.releaseLock();
        }
    }
    
    public void writeToSunXML(int index, File resourceFile) throws Exception {
        ResourceEntry entry = resources.get(index);
        ObjectFactory factory = new ObjectFactory();
        Resources jaxbRoot = factory.createResources();
        jaxbRoot.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool(
                    ).add(entry.resource);
        
        addReferencedPool(entry.resource, jaxbRoot);
        
        PrintWriter out = new PrintWriter(resourceFile);
        try {
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(jaxbRoot, out);
        } finally {
            out.close();
        }
        
        entry.orphanStatus = OrphanStatus.BOTH;
        entry.fileObject = org.openide.filesystems.FileUtil.toFileObject(resourceFile);
    }
    
    public void deleteResourceFile(int index) throws Exception {
        ResourceEntry entry = resources.get(index);
        if (entry == null || entry.orphanStatus == OrphanStatus.ANNO_ONLY ||
                entry.fileObject == null) {
            throw new Exception(NbBundle.getMessage(ResourceAggregator.class, "EXC_error_delete"));
        }
        FileObject fo = entry.fileObject;
        // find for all resources that might be using the same physical file
        for (Iterator<ResourceEntry> iter = findResourcesByFileObject(fo).iterator(); iter.hasNext(); ) {
            ResourceEntry curEntry = iter.next();
            if (curEntry.orphanStatus == OrphanStatus.FILE_ONLY) {
                resources.remove(curEntry);
            } else if (curEntry.orphanStatus == OrphanStatus.BOTH) {
                curEntry.fileObject = null;
                curEntry.orphanStatus = OrphanStatus.ANNO_ONLY;
            }
        }
        // delete physical file
        fo.delete();
    }
    
    private ArrayList<ResourceEntry> findResourcesByFileObject(FileObject fo) {
        ArrayList<ResourceEntry> newList = new ArrayList<ResourceEntry> ();
        for (Iterator<ResourceEntry> iter = resources.iterator(); iter.hasNext(); ) {
            ResourceEntry entry = iter.next();
            if (entry.fileObject != null && entry.fileObject.equals(fo)) {
                newList.add(entry);
            }
        }
        return newList;
    }
    
    private void addReferencedPool(Object targetResource, Resources jaxbRoot) {
       // add connection pools
        if (targetResource instanceof ConnectorResource) {
            String poolName = ((ConnectorResource) targetResource).getPoolName();
            PoolEntry poolEntry = getConnectorConnectionPoolEntry(poolName);
            if (poolEntry == null) {
                poolEntry = createDefaultConnectorConnectionPool(poolName);
            }
            if (poolEntry != null && (poolEntry.pool instanceof ConnectorConnectionPool)) {
                jaxbRoot.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool(
                        ).add(poolEntry.pool);
            }
        }
        if (targetResource instanceof JdbcResource) {
            String poolName = ((JdbcResource) targetResource).getPoolName();
            PoolEntry poolEntry = getJdbcConnectionPoolEntry(poolName);
            if (poolEntry == null) {
                poolEntry = createDefaultJdbcConnectionPool(poolName);
            }
            if (poolEntry != null && (poolEntry.pool instanceof JdbcConnectionPool)) {
                jaxbRoot.getCustomResourceOrExternalJndiResourceOrJdbcResourceOrMailResourceOrPersistenceManagerFactoryResourceOrAdminObjectResourceOrConnectorResourceOrResourceAdapterConfigOrJdbcConnectionPoolOrConnectorConnectionPool(
                        ).add(poolEntry.pool);
            }
        }
    }
    
    public void close() {
        
    }
    
    public ResourceEntry getAdminObjectResourceEntry(String jndiName) {
        for (Iterator<ResourceEntry> iter = resources.iterator(); iter.hasNext(); ) {
            ResourceEntry entry = iter.next();
            if (entry.resource instanceof AdminObjectResource) {
                AdminObjectResource adminObjectResource = (AdminObjectResource) entry.resource;
                if (adminObjectResource.getJndiName().equals(jndiName)) {
                    return entry;
                }
            }
        }
        return null;
    }
    
    public ResourceEntry addAdminObjectResourceEntry(String jndiName, String resType, 
            String resAdapterName, Properties adminObjProps) {
        AdminObjectResource adminObjResource = new AdminObjectResource();
        adminObjResource.setJndiName(jndiName);
        adminObjResource.setResType(resType);
        adminObjResource.setResAdapter(resAdapterName);
        adminObjResource.setDescription(""); // NOI18N
        for (Enumeration<?> e = adminObjProps.propertyNames(); e.hasMoreElements(); ) {
            String propName = (String) e.nextElement();
            String propValue = adminObjProps.getProperty(propName);
            Property p = new Property();
            p.setName(propName);
            p.setValue(propValue);
            adminObjResource.getProperty().add(p);
        }
        ResourceEntry entry = new ResourceEntry();
        entry.resource = adminObjResource;
        entry.orphanStatus = OrphanStatus.ANNO_ONLY;
        entry.obsolete = false;
        resources.add(entry);
        return entry;
    }
    
    public ResourceEntry getConnectorResourceResourceEntry(String jndiName) {
        for (Iterator<ResourceEntry> iter = resources.iterator(); iter.hasNext(); ) {
            ResourceEntry entry = iter.next();
            if (entry.resource instanceof ConnectorResource) {
                ConnectorResource connectorResource = (ConnectorResource) entry.resource;
                if (connectorResource.getJndiName().equals(jndiName)) {
                    return entry;
                }
            }
        }
        return null;
    }
    
    public ResourceEntry addConnectorResourceEntry(String jndiName, String connDefName, 
            String resAdapterName) {
        ConnectorResource connectorResource = new ConnectorResource();
        connectorResource.setJndiName(jndiName);
        
        // make sure poolName has not been used yet
        String poolName = jndiName;
        int count = 1;
        while (getConnectorConnectionPoolEntry(poolName) != null) {
            poolName = poolName + Integer.toString(count);
        }
        connectorResource.setPoolName(poolName);
        connectorResource.setDescription(""); // NOI18N
        
        // create pool object
        ConnectorConnectionPool poolResource = new ConnectorConnectionPool();
        poolResource.setName(poolName);
        poolResource.setResourceAdapterName(resAdapterName);
        poolResource.setConnectionDefinitionName(connDefName);
        poolResource.setDescription(""); // NOI18N
        PoolEntry poolEntry = new PoolEntry();
        poolEntry.pool = poolResource;
        pools.add(poolEntry);
        
        ResourceEntry entry = new ResourceEntry();
        entry.resource = connectorResource;
        entry.orphanStatus = OrphanStatus.ANNO_ONLY;
        entry.obsolete = false;
        resources.add(entry);
        return entry;
    }
    
    public PoolEntry getConnectorConnectionPoolEntry(String poolName) {
        for (Iterator<PoolEntry> iter = pools.iterator(); iter.hasNext(); ) {
            PoolEntry entry = iter.next();
            if (entry.pool instanceof ConnectorConnectionPool) {
                ConnectorConnectionPool connectionPool = (ConnectorConnectionPool) entry.pool;
                if (connectionPool.getName().equals(poolName)) {
                    return entry;
                }
            }
        }
        return null;
    }
    
    public ResourceEntry getJdbcResourceResourceEntry(String jndiName) {
        for (Iterator<ResourceEntry> iter = resources.iterator(); iter.hasNext(); ) {
            ResourceEntry entry = iter.next();
            if (entry.resource instanceof JdbcResource) {
                JdbcResource jdbcResource = (JdbcResource) entry.resource;
                if (jdbcResource.getJndiName().equals(jndiName)) {
                    return entry;
                }
            }
        }
        return null;
    }
    
    public ResourceEntry addJdbcResourceEntry(String jndiName, String resTypeName, 
            String dataSourceClassName) {
        JdbcResource jdbcResource = new JdbcResource();
        jdbcResource.setJndiName(jndiName);
        
        // make sure poolName has not been used yet
        String poolName = jndiName;
        int count = 1;
        while (getJdbcConnectionPoolEntry(poolName) != null) {
            poolName = poolName + Integer.toString(count);
        }
        jdbcResource.setPoolName(poolName);
        jdbcResource.setDescription(""); // NOI18N
        
        // create pool object
        JdbcConnectionPool poolResource = new JdbcConnectionPool();
        poolResource.setName(poolName);
        poolResource.setResType(resTypeName);
        poolResource.setDatasourceClassname(dataSourceClassName);
        poolResource.setDescription(""); // NOI18N
        PoolEntry poolEntry = new PoolEntry();
        poolEntry.pool = poolResource;
        pools.add(poolEntry);
        
        ResourceEntry entry = new ResourceEntry();
        entry.resource = jdbcResource;
        entry.orphanStatus = OrphanStatus.ANNO_ONLY;
        entry.obsolete = false;
        resources.add(entry);
        return entry;
    }
    
    public PoolEntry getJdbcConnectionPoolEntry(String poolName) {
        for (Iterator<PoolEntry> iter = pools.iterator(); iter.hasNext(); ) {
            PoolEntry entry = iter.next();
            if (entry.pool instanceof JdbcConnectionPool) {
                JdbcConnectionPool jdbcPool = (JdbcConnectionPool) entry.pool;
                if (jdbcPool.getName().equals(poolName)) {
                    return entry;
                }
            }
        }
        return null;
    }
    
    public PoolEntry createDefaultConnectorConnectionPool(String poolName) {
        ConnectorConnectionPool poolResource = new ConnectorConnectionPool();
        poolResource.setName(poolName);
        poolResource.setResourceAdapterName("jmsra"); // NOI18N
        poolResource.setConnectionDefinitionName("javax.jms.ConnectionFactory"); // NOI18N
        poolResource.setDescription(""); // NOI18N
        PoolEntry poolEntry = new PoolEntry();
        poolEntry.pool = poolResource;
        pools.add(poolEntry);
        return poolEntry;
    }
    
    public PoolEntry createDefaultJdbcConnectionPool(String poolName) {
        JdbcConnectionPool poolResource = new JdbcConnectionPool();
        poolResource.setName(poolName);
        poolResource.setDatasourceClassname("org.apache.derby.jdbc.ClientDataSource"); // NOI18N
        poolResource.setDescription(""); // NOI18N
        PoolEntry poolEntry = new PoolEntry();
        poolEntry.pool = poolResource;
        pools.add(poolEntry);
        return poolEntry;
    }
    
    public static class ResourceEntry {
        public Object resource;
        public FileObject fileObject;
        public OrphanStatus orphanStatus = OrphanStatus.FILE_ONLY;
        public boolean obsolete = true;
        public List<ResourceUsage> usages = new ArrayList<ResourceUsage> ();
        
        public void addResourceUsage(String sourceName, SourceType sourceType) {
            ResourceUsage usage = new ResourceUsage();
            usage.sourceName = sourceName;
            usage.sourceType = sourceType;
            usages.add(usage);
        }
    }
    
    public static class PoolEntry {
        public Object pool;
        public FileObject fileObject;
    }
    
    public static class ResourceUsage {
        public String sourceName;
        public SourceType sourceType;
    }
}
