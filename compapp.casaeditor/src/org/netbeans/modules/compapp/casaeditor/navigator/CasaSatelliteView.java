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


package org.netbeans.modules.compapp.casaeditor.navigator;

import java.util.Collection;
import javax.swing.JComponent;
import org.netbeans.modules.compapp.casaeditor.CasaDataObject;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaWrapperModel;

public class CasaSatelliteView implements LookupListener, NavigatorPanel {
    
    private static String navPanelName = NbBundle.getMessage(
            CasaNavigatorView.class, "LBL_SATELLITE_VIEW"); // NOI18N
    
    private CasaSatelliteContentPanel mNavigatorPanel;
    private Lookup.Result mLookupResult;
    private CasaWrapperModel mCasaModel;
    
    
    public CasaSatelliteView() {
    }
    
    
    public static String getUName() {
        return navPanelName;
    }
    
    public String getDisplayName() {
        return navPanelName;
    }
    
    public String getDisplayHint() {
        return NbBundle.getMessage(CasaNavigatorView.class, "LBL_SATELLITE_VIEW_TOOLTIP"); // NOI18N
    }
    
    public JComponent getComponent() {
        if (mNavigatorPanel == null) {
            mNavigatorPanel = new CasaSatelliteContentPanel();
        }
        return mNavigatorPanel;
    }
    
    public void panelActivated(Lookup context) {
        getComponent();
        mLookupResult = context.lookup(new Lookup.Template(DataObject.class));
        mLookupResult.addLookupListener(this);
        resultChanged(null);
    }
    
    public void panelDeactivated() {
        if (mLookupResult != null) {
            mLookupResult.removeLookupListener(this);
            mLookupResult = null;
        }
    }
    
    public Lookup getLookup() {
        // go with default activated Node strategy
        //Default mechanism chooses first Node from Utilities.actionsGlobalContext()
        //as activated Node for Navigator's TopComponent.
        return null;
    }
    
    public void resultChanged(LookupEvent ev) {
        Collection selected = mLookupResult.allInstances();
        if (selected.size() == 1) {
            Object result = selected.iterator().next();
            if (result instanceof CasaDataObject) {
                mNavigatorPanel.navigate((CasaDataObject) result);
            } else {
                mNavigatorPanel.navigate(null);
            }
        }
    }
}
