package org.netbeans.modules.wlm.model.common;
/*
 * TestCatalogModel.java
 *
 * Created on April 2, 2006, 10:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.text.Document;
import org.netbeans.modules.xml.retriever.catalog.impl.CatalogFileWrapperDOMImpl;
import org.netbeans.modules.xml.retriever.catalog.impl.CatalogWriteModelImpl;
import org.netbeans.modules.xml.schema.model.SchemaModel;
import org.netbeans.modules.xml.schema.model.SchemaModelFactory;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.xml.wsdl.model.WSDLModelFactory;
import org.netbeans.modules.xml.xam.locator.CatalogModel;
import org.netbeans.modules.xml.xam.locator.CatalogModelException;
import org.netbeans.modules.xml.xam.ModelSource;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author girix
 */

public class TestCatalogModel extends CatalogWriteModelImpl{
    private TestCatalogModel(File file) throws IOException{
        super(file);
    }
    
    static TestCatalogModel singletonCatMod = null;
    public static TestCatalogModel getDefault(){
        if (singletonCatMod == null){
            CatalogFileWrapperDOMImpl.TEST_ENVIRONMENT = true;
            try {
                singletonCatMod = new TestCatalogModel(new File(System.getProperty("java.io.tmpdir")));
                FileObject catalogFO = singletonCatMod.getCatalogFileObject();
                File catFile = FileUtil.toFile(catalogFO);
                catFile.deleteOnExit();
                initCatalogFile();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return singletonCatMod;
    }
    
    
    
    /**
     * This method could be overridden by the Unit testcase to return a special
     * ModelSource object for a FileObject with custom impl of classes added to the lookup.
     * This is optional if both getDocument(FO) and createCatalogModel(FO) are overridden.
     */
    protected ModelSource createModelSource(final FileObject thisFileObj, boolean editable) throws CatalogModelException{
        assert thisFileObj != null : "Null file object.";
        final CatalogModel catalogModel = createCatalogModel(thisFileObj);
        final DataObject dobj;
        try {
            dobj = DataObject.find(thisFileObj);
        } catch (DataObjectNotFoundException ex) {
            throw new CatalogModelException(ex);
        }
        Lookup proxyLookup = Lookups.proxy(
                new Lookup.Provider() {
            public Lookup getLookup() {
                Document document = null;
                Logger l = Logger.getLogger(getClass().getName());
                document = getDocument(thisFileObj);
                return Lookups.fixed(new Object[] {
                    thisFileObj,
                    document,
                    dobj,
                    catalogModel
                });
            }
        }
        );
        return new ModelSource(proxyLookup, editable);
    }
    
    private Document  getDocument(FileObject fo){
        Document result = null;
        if (documentPooling) {
            result = documentPool().get(fo);
        }
        if (result != null) return result;
        try {
            
            File file = FileUtil.toFile(fo);
            FileInputStream fis = new FileInputStream(file);
            byte buffer[] = new byte[fis.available()];
            result = new org.netbeans.editor.BaseDocument(
                    org.netbeans.modules.xml.text.syntax.XMLKit.class, false);
            result.remove(0, result.getLength());
            fis.read(buffer);
            fis.close();
            String str = new String(buffer);
            result.insertString(0,str,null);
            
        } catch (Exception dObjEx) {
            return null;
        }
        if (documentPooling) {
            documentPool().put(fo, result);
        }
        return result;
    }
    
    protected CatalogModel createCatalogModel(FileObject fo) throws CatalogModelException{
        return getDefault();
    }
    
    public ModelSource createTestModelSource(FileObject fo, boolean editable) throws CatalogModelException{
        final DataObject dobj;
        final CatalogModel catalogModel = createCatalogModel(fo);
        try {
            dobj = DataObject.find(fo);
        } catch (DataObjectNotFoundException ex) {
            throw new CatalogModelException(ex);
        }
        Lookup lookup = Lookups.proxy(new Lookup.Provider() {
            public Lookup getLookup() {
                        return Lookups.fixed(new Object[] {
                            dobj.getPrimaryFile(),
                            getDocument(dobj.getPrimaryFile()),
                            dobj,
                            catalogModel
                        });

            }
        } );
        return new ModelSource(lookup, editable);
    }
    
    public void addNamespace(NamespaceLocation nl) throws Exception {
        this.addURI(nl.getLocationURI(), nl.getResourceURI());
    }
    
    public SchemaModel getSchemaModel(NamespaceLocation nl) throws Exception {
        return getSchemaModel(nl.getLocationURI());
    }
    
    public SchemaModel getSchemaModel(URI lcationURI) throws Exception {
        SchemaModel model = SchemaModelFactory.getDefault().getModel(
                singletonCatMod.getModelSource(lcationURI));
        return model;
    }
    
    private Map<FileObject,Document> fileToDocumentMap;
    private Map<FileObject,Document> documentPool() {
        if (fileToDocumentMap == null) {
            fileToDocumentMap = new HashMap<FileObject,Document>();
        }
        return fileToDocumentMap;
    }
    private boolean documentPooling = true;
    
    public void setDocumentPooling(boolean v) {
        documentPooling = v;
        if (! documentPooling) {
            clearDocumentPool();
        }
    }

    public void clearDocumentPool() {
        fileToDocumentMap = null;
    }
    
    private static void initCatalogFile() throws Exception {
        TestCatalogModel instance = singletonCatMod;
        for (NamespaceLocation nl:NamespaceLocation.values()) {
            instance.addNamespace(nl);
        }
    }
    
    public WSDLModel getWSDLModel(URI locationURI) throws Exception {
        ModelSource source = getDefault().getModelSource(locationURI);
        WSDLModel model = WSDLModelFactory.getDefault().getModel(source);
        return model;
    }
    
    public WSDLModel getWSDLModel(NamespaceLocation nl) throws Exception {
        nl.refreshResourceFile();
        return getWSDLModel(nl.getLocationURI());
    }
    
    public String toString(){
        return "TestCatalogModel"+super.toString();
    }
}

