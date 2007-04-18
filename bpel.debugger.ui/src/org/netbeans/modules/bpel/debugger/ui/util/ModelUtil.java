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

package org.netbeans.modules.bpel.debugger.ui.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.text.StyledDocument;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.netbeans.modules.bpel.debugger.api.EditorContextBridge;
import org.netbeans.modules.bpel.debugger.api.psm.ProcessStaticModel;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.BpelModel;
import org.netbeans.modules.bpel.model.api.support.UniqueId;
import org.netbeans.modules.bpel.model.spi.FindHelper;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentModel;
import org.openide.text.NbDocument;
import org.openide.util.Lookup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Alexander Zgursky
 */
public final class ModelUtil {
    private static FindHelper findHelper =
            (FindHelper)Lookup.getDefault().lookup(FindHelper.class);
    
    private ModelUtil() {
    }
    
    public static FindHelper getFindHelper() {
        return findHelper;
    }
    
    public static String getXpath(final UniqueId bpelEntityId) {
        final BpelModel model = bpelEntityId.getModel();
        
        try {
            model.sync();
        } catch (IOException ex) {
            return null;
        }
        
        
        class MyRunnable implements Runnable{
            private String result = null;
            
            public String getResult(){
                return this.result;
            }
            
            public void run() {
                if (!model.getState().equals(BpelModel.State.VALID)) {
                    return;
                }
                BpelEntity bpelEntity = model.getEntity(bpelEntityId);
                if (bpelEntity != null) {
                    result = EditorContextBridge.normalizeXpath(
                            findHelper.getXPath(bpelEntity));
                }
                
                
            }
        };
        
        MyRunnable r = new MyRunnable();
        
        model.invoke(r);
        
        return r.getResult();
    }
    
    public static UniqueId getBpelEntityId(
            final BpelModel model, final String xpath)
    {
        try {
            model.sync();
        } catch (IOException ex) {
            return null;
        }
        
        class MyRunnable implements Runnable{
            private UniqueId result = null;
            
            public UniqueId getResult(){
                return this.result;
            }
            
            public void run() {
                if (!model.getState().equals(BpelModel.State.VALID)) {
                    return;
                }
                
//                BpelEntity[] entities = findHelper.findModelElements(model, xpath);
//                if (entities.length == 1) {
//                    result = entities[0].getUID();
//                }
                BpelEntity entity = findBpelEntity(model, xpath);
                if (entity != null) {
                    result = entity.getUID();
                }
                
            }
        };
        
        MyRunnable r = new MyRunnable();
        
        model.invoke(r);
        
        return r.getResult();
    }
    
    private static BpelEntity findBpelEntity(BpelModel model, String xpathExpression) {
        if (model.getProcess() == null) {
            return null;
        }
        Document doc = model.getProcess().getPeer().getOwnerDocument();
        XPath xpath = XPathFactory.newInstance().newXPath();
        NamespaceContext myNsContext = new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                if (ProcessStaticModel.BPEL_NAMESPACE_PREFIX.equals(prefix)) {
                    return ProcessStaticModel.BPEL_NAMESPACE_URI;
                } else {
                    return XMLConstants.NULL_NS_URI;
                }
            }
            public String getPrefix(String namespaceURI) {
                if (ProcessStaticModel.BPEL_NAMESPACE_URI.equals(namespaceURI)) {
                    return ProcessStaticModel.BPEL_NAMESPACE_PREFIX;
                } else {
                    return null;
                }
            }
            public Iterator getPrefixes(String namespaceURI) {
                String prefix = getPrefix(namespaceURI);
                if (prefix != null) {
                    return Collections.singletonList(prefix).iterator();
                } else {
                    return Collections.emptyList().iterator();
                }
            }
        };
        
        xpath.setNamespaceContext(myNsContext);
        Node node;
        try {
            node = (Node)xpath.evaluate(xpathExpression, doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            return null;
        }
        
        if ( node instanceof Element ){
            AbstractDocumentModel<BpelEntity> xamModel = (AbstractDocumentModel<BpelEntity>)model;
            List<Element> pathToRoot = xamModel.getAccess().
                    getPathFromRoot(xamModel.getDocument(), 
                            (Element)node );
            Component comp = xamModel.findComponent(pathToRoot);
            if (comp instanceof BpelEntity) {
                return (BpelEntity)comp;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public static UniqueId getBpelEntityId(
            final BpelModel model, final int offset)
    {
        try {
            model.sync();
        } catch (IOException ex) {
            return null;
        }
        
        
        class MyRunnable implements Runnable{
            private UniqueId result = null;
            
            public UniqueId getResult(){
                return this.result;
            }
            
            public void run() {
                if (!model.getState().equals(BpelModel.State.VALID)) {
                    return;
                }
                BpelEntity bpelEntity = model.findElement(offset);
                if (bpelEntity != null) {
                    result =  bpelEntity.getUID();
                }
                
            }
        };
        
        MyRunnable r = new MyRunnable();
        
        model.invoke(r);
        
        return r.getResult();
    }
    
    public static int getLineNumber(final UniqueId bpelEntityId) {
        final BpelModel model = bpelEntityId.getModel();
        
        try {
            model.sync();
        } catch (IOException ex) {
            return -1;
        }
        
        class MyRunnable implements Runnable{
            private int result = -1;
            
            public int getResult(){
                return this.result;
            }
            
            public void run() {
                if (!model.getState().equals(BpelModel.State.VALID)) {
                    return;
                }
                BpelEntity bpelEntity = model.getEntity(bpelEntityId);
                if (bpelEntity != null) {
                    result = bpelEntity.findPosition();
                }
            }
        };
        
        MyRunnable r = new MyRunnable();
        model.invoke(r);
        int offset = r.getResult();
        if (offset < 0) {
            return -1;
        }
        
        StyledDocument doc =(StyledDocument)model.getModelSource().getLookup().
                lookup(StyledDocument.class);

        return NbDocument.findLineNumber(doc, offset) + 1;
    }
}
