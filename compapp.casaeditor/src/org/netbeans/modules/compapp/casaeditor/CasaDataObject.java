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

package org.netbeans.modules.compapp.casaeditor;

import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.DataFolder;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author tli
 */
public class CasaDataObject extends MultiDataObject {
    
    static final long serialVersionUID = 7527025549386876556L;
    
    public static final String CASA_ICON_BASE_WITH_EXT = "org/netbeans/modules/compapp/casaeditor/resources/casa16.gif"; // NOI18N
    
    private transient CasaDataEditorSupport editorSupport;
    
    private transient AtomicReference<Lookup> myLookup =
            new AtomicReference<Lookup>();
    private transient AtomicReference<InstanceContent> myServices =
            new AtomicReference<InstanceContent>();
    private transient AtomicBoolean isLookupInit = new AtomicBoolean( false );

    
    public CasaDataObject(FileObject pf, MultiFileLoader loader)
    throws DataObjectExistsException
    {
        super(pf, loader);
        
        editorSupport = new CasaDataEditorSupport(this);
        
        CookieSet set = getCookieSet();
        set.add(editorSupport);
        
        
    }
           
    public CasaDataEditorSupport getEditorSupport() {
        return editorSupport;
    }
    
    @Override
    protected Node createNodeDelegate() {
        return new CasaDataNode(this);
    }
    
    public HelpCtx getHelpCtx() {
        return new HelpCtx(CasaDataObject.class);
    }
    
    @Override
    protected void handleDelete() throws IOException {
        if (isModified()) {
            setModified(false);
        }
        getEditorSupport().getEnv().unmarkModified();
        super.handleDelete();
    }
    
    protected FileObject handleMove(DataFolder df) throws IOException {
        //TODO:make sure we save file before moving This is what jave move does.
        //It also launch move refactoring dialog which we should be doing
        //as well
        if (isModified()) {
            SaveCookie sCookie = (SaveCookie) this.getCookie(SaveCookie.class);
            if (sCookie != null) {
                sCookie.save();
            }
        }
        return super.handleMove(df);
    }
    
    @Override
    public void setModified( boolean modified ) {
        super.setModified(modified);
        if (modified) {
            getCookieSet().add(getSaveCookie());
            if ( isLookupInit.get() ) {
                myServices.get().add(getSaveCookie());
            }
        } else {
            getCookieSet().remove(getSaveCookie());
            if ( isLookupInit.get() ) {
                myServices.get().remove( getSaveCookie());
            }
        }
    }
    
    private SaveCookie getSaveCookie() {
        return new SaveCookie() {
            
            public void save() throws IOException {
                getEditorSupport().saveDocument();
            }
            
            @Override
            public int hashCode() {
                return getClass().hashCode();
            }
            
            @Override
            public boolean equals( Object other ) {
                return other != null && getClass().equals(other.getClass());
            }
        };
    }
}
