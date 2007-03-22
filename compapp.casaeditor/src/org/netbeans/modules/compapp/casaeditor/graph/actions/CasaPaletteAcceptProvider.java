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

package org.netbeans.modules.compapp.casaeditor.graph.actions;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ant.AntArtifact;
import org.netbeans.modules.compapp.casaeditor.design.CasaModelGraphScene;
import org.netbeans.modules.compapp.casaeditor.graph.CasaNodeWidgetEngineExternal;
import org.netbeans.modules.compapp.casaeditor.graph.CasaRegionWidget;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaServiceEngineServiceUnit;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaWrapperModel;
import org.netbeans.modules.compapp.casaeditor.model.casa.JBIServiceUnitTransferObject;
import org.netbeans.modules.compapp.casaeditor.palette.CasaCommonAcceptProvider;
import org.netbeans.modules.compapp.casaeditor.palette.CasaPalette;
import org.netbeans.modules.compapp.casaeditor.palette.CasaPaletteItem;
import org.netbeans.modules.compapp.projects.jbi.api.JbiProjectConstants;
import org.netbeans.spi.project.ant.AntArtifactProvider;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.datatransfer.MultiTransferObject;

/**
 *
 * @author rdara
 */
public class CasaPaletteAcceptProvider extends CasaCommonAcceptProvider {
    
    private CasaWrapperModel mModel;
    private List<String> artifactTypes = new ArrayList<String>();
    
    
    public CasaPaletteAcceptProvider(CasaModelGraphScene scene, CasaWrapperModel model) {
        super(scene);
        mModel = model;
        artifactTypes.add(JbiProjectConstants.ARTIFACT_TYPE_JBI_ASA);
    }
    
    
    private String getJbiProjectType(Project p) {
        if (p == null) {
            return null;
        }
        AntArtifactProvider prov = (AntArtifactProvider)p.getLookup().lookup(AntArtifactProvider.class);
        if (prov != null) {
            AntArtifact[] artifacts = prov.getBuildArtifacts();
            Iterator<String> artifactTypeItr = null;
            String artifactType = null;
            if (artifacts != null) {
                for (int i = 0; i < artifacts.length; i++) {
                    artifactTypeItr = this.artifactTypes.iterator();
                    while (artifactTypeItr.hasNext()){
                        artifactType = artifactTypeItr.next();
                        String arts = artifacts[i].getType();
                        if (arts.startsWith(artifactType)) {
                            int idx = arts.indexOf(':') + 1;
                            return arts.substring(idx);
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    public ConnectorState isAcceptable(Widget widget, Point point, Transferable transferable){
        ConnectorState retState = ConnectorState.REJECT;
        try {
            // JBIMGR
            try { 
                Object transferData = transferable.getTransferData(CasaPalette.CasaPaletteDataFlavor); //?
                if (transferData instanceof List &&
                        ((List)transferData).size() == 3 &&
                        ((List)transferData).get(0) instanceof String &&
                        ((List)transferData).get(0).equals("JBIMGR_SU_TRANSFER")) {
                    return isAcceptableFromJBIManager(widget, point, transferData);
                }
            } catch (Exception e) {
                ;
            }
            // JBIMGR
            
            if (transferable.isDataFlavorSupported(CasaPalette.CasaPaletteDataFlavor)) {
                CasaPaletteItem selNode = (CasaPaletteItem) transferable.getTransferData(CasaPalette.CasaPaletteDataFlavor);
                if (selNode != null) {
                    retState = isAcceptableFromPalette(point, selNode);
                }
            } else {
                retState = isAcceptableFromOther(point, transferable, false);
            }
        } catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retState;
    }
    
    // JBIMGR
    private ConnectorState isAcceptableFromJBIManager(Widget widget, Point point,
            Object transferData) {
        ConnectorState retState = ConnectorState.REJECT;
        CasaRegionWidget extRegion = getScene().getExternalRegion();
        if (extRegion.getBounds().contains(extRegion.convertSceneToLocal(point))) {
            String projName /*suName*/ = (String) ((List) transferData).get(1); // FIXME: 
            if (mModel.existingServiceUnit(projName)) { // FIXME: existingExternalServiceUnit?
                retState = ConnectorState.REJECT;
            } else {
                retState = ConnectorState.ACCEPT;
            }
        }
        return retState;
    }
    // JBIMGR
    
    private ConnectorState isAcceptableFromPalette(Point point, CasaPaletteItem selNode) {
        CasaRegionWidget region = getApplicableRegion(selNode);
        ConnectorState retState = ConnectorState.REJECT;
        if (
                region != null &&
                region.getBounds().contains(region.convertSceneToLocal(point))) {
            retState = ConnectorState.ACCEPT;
        }
        return retState;
    }
    
    private ConnectorState isAcceptableFromOther(Point point, Transferable transferable, boolean bIgnoreRegionCheck) 
    throws Exception {
        CasaRegionWidget region = getScene().getEngineRegion();
        ConnectorState curState = ConnectorState.REJECT;
        if (bIgnoreRegionCheck || region.getBounds().contains(region.convertSceneToLocal(point))) {
            for(Object dfo : getTransferableObjects(transferable)) {
                curState = ConnectorState.REJECT;
                if (dfo instanceof Node) {
                    DataObject obj = (DataObject) ((Node) dfo).getCookie(DataObject.class);
                    Project p = getProjectFromDataObject(obj); // ProjectManager.getDefault().findProject(obj.getPrimaryFile());
                    if (getJbiProjectType(p) != null) {
                        String pname = p.getProjectDirectory().getName();
                        // todo: 01/24/07 needs to check for duplicates...
                        if (!mModel.existingServiceUnit(pname)) {
                            curState = ConnectorState.ACCEPT;
                        } 
                    } 
                }
                if(curState == ConnectorState.REJECT) {
                    break;
                }
            }
        }
        return curState;
    }
    
    /* Receive all transferable objects */
    private List getTransferableObjects(Transferable transferable) {
        List retList = new ArrayList();
        DataFlavor[] dfs = transferable.getTransferDataFlavors();
        if (dfs.length > 0) {
            try {
                if(dfs[0].getRepresentationClass().equals(MultiTransferObject.class)){
                    MultiTransferObject mto = (MultiTransferObject)transferable.getTransferData(dfs[0]);
                    if(mto.getCount() > 0) {
                        DataFlavor[] df = mto.getTransferDataFlavors(0);
                        if(df.length > 0) {
                            for(int i = 0; i < mto.getCount(); i++) {
                                retList.add(mto.getTransferData(i, df[0]));
                            }
                        }
                    }
                } else {
                    retList.add(transferable.getTransferData(dfs[0]));
                }
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            }  catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return retList;
    }
    
    public void accept(Widget widget, Point point, Transferable transferable) {
        try {
            // JBIMGR
            if (transferable.isDataFlavorSupported(CasaPalette.CasaPaletteDataFlavor)) {
                Object data = transferable.getTransferData(CasaPalette.CasaPaletteDataFlavor);
                if (data instanceof List &&
                        ((List)data).size() == 3 &&
                        ((List)data).get(0) instanceof String &&
                        ((List)data).get(0).equals("JBIMGR_SU_TRANSFER")) {
                    
                    JBIServiceUnitTransferObject suTransfer =
                            new JBIServiceUnitTransferObject(
                            (String) ((List)data).get(1),
                            (String) ((List)data).get(2));
                    acceptFromJBIManager(widget, point, suTransfer);
                    return;
                }
            }
            // JBIMGR
            
            if (transferable.isDataFlavorSupported(CasaPalette.CasaPaletteDataFlavor)) {
                
                CasaPaletteItem selNode =
                        (CasaPaletteItem) transferable.getTransferData(CasaPalette.CasaPaletteDataFlavor);
                if (selNode != null) {
                    acceptFromPalette(point, selNode);
                }
            } else {
                acceptFromOther(point, transferable);
            }
        } catch (Throwable t) {
            // Catch all exceptions, including those from the model.
            // There must be visual feedback of an error if the drop failed.
            ErrorManager.getDefault().notify(t);
        }
    }
    
    private void acceptFromPalette(Point point, CasaPaletteItem selNode) {
        switch(selNode.getCategory()) {
            case WSDL_BINDINGS :
                point = getScene().getBindingRegion().convertSceneToLocal(point);
                mModel.addCasaPort(
                        selNode.getTitle(),
                        selNode.getComponentName(),
                        point.x,
                        point.y);
                break;
            case SERVICE_UNITS :
                if        (CasaPalette.CASA_PALETTE_ITEM_TYPE.INT_SU == selNode.getPaletteItemType()) {
                    // add an internal SU to the model
                    point = getScene().getEngineRegion().convertSceneToLocal(point);
                    mModel.addServiceEngineServiceUnit(true, point.x, point.y);
                } else if (CasaPalette.CASA_PALETTE_ITEM_TYPE.EXT_SU == selNode.getPaletteItemType()) {
                    // add an external SU to the model
                    point = getScene().getExternalRegion().convertSceneToLocal(point);
                    mModel.addServiceEngineServiceUnit(false, point.x, point.y);
                }
                break;
            default:
                break;
        }
    }
    
    private void acceptFromOther(Point point, Transferable transferable)
    throws Exception {
        for(Object dfo : getTransferableObjects(transferable)) {
            if (dfo instanceof Node) {
                DataObject obj = (DataObject) ((Node) dfo).getCookie(DataObject.class);
                Project p = getProjectFromDataObject(obj); // ProjectManager.getDefault().findProject(obj.getPrimaryFile());
                String type = getJbiProjectType(p);
                point = getScene().getEngineRegion().convertSceneToLocal(point);
                mModel.addInternalJBIModule(p, type, point.x, point.y);
            }
        }
    }

    // JBIMGR
    private void acceptFromJBIManager(Widget widget, Point point,
            final JBIServiceUnitTransferObject suTransfer) throws Exception {
        
        point = getScene().getExternalRegion().convertSceneToLocal(point);
        
        String suName = suTransfer.getServiceUnitName();
        final CasaServiceEngineServiceUnit seSU = 
                mModel.addServiceEngineServiceUnit(
                suName, "", false, false, point.x, point.y); // NOI18N // FIXME
               
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {  
                mModel.addEndpointsToServiceEngineServiceUnit(suTransfer, seSU);    
            }
        });
    }
    // JBIMGR
    
    // todo: 02/15/07 fix the problem created by bpel project changes...
    private Project getProjectFromDataObject(DataObject obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ProjectManager pm = ProjectManager.getDefault();
        Project p = null;
        for (FileObject fo=obj.getPrimaryFile(); fo != null; fo=fo.getParent()) {
            p = pm.findProject(fo);
            if (p != null) {
                return p;
            }
        }
        return p;
    }
    
    
    public void acceptStarted(Transferable transferable) {
        super.acceptStarted(transferable);
        CasaRegionWidget region = null;
        if (transferable.isDataFlavorSupported(CasaPalette.CasaPaletteDataFlavor)) {
            CasaPaletteItem selNode = getCasaPaletteItem(transferable);
            region = getApplicableRegion(selNode);
            if(region == null) {
                if(selNode != null) {
                    if(selNode.getCategory() == CasaPalette.CASA_CATEGORY_TYPE.END_POINTS) {
                        highlightExtSUs(true);
                    }
                }
            }
       } else {
            try {
                if (isAcceptableFromOther(null, transferable, true) == ConnectorState.ACCEPT) {
                    region = getScene().getEngineRegion();
                }
            }
            catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
       }
       if(region != null) {
            highlightRegion(region); 
       }
    }
    
    public void acceptFinished() {
        super.acceptFinished();
        highlightExtSUs(false);
        getScene().getBindingRegion().setHighlighted(false);
        getScene().getEngineRegion().setHighlighted(false);
        getScene().getExternalRegion().setHighlighted(false);
    }
    
    private void highlightRegion(CasaRegionWidget region) {
        region.setHighlighted(true);
        showRegion(region);
    }
    
    private void highlightExtSUs(boolean bValue) {
        for (Widget widget : getScene().getExternalRegion().getChildren()) {
            if (widget instanceof CasaNodeWidgetEngineExternal) {
                ((CasaNodeWidgetEngineExternal) widget).setHighlighted(bValue);
            }
        }
        if (bValue) {
            showRegion(getScene().getExternalRegion());
        }
    }
    
    private void showRegion(CasaRegionWidget region) {
        Rectangle visibleRect = region.getBounds();     //Scroll to visible
        visibleRect.x += region.getLocation().x;
        visibleRect.y += region.getLocation().y;
        visibleRect.width += region.getLocation().x;
        visibleRect.height += region.getLocation().y;
        getScene().getViewComponent().scrollRectToVisible(visibleRect);
        
    }
    
    private CasaRegionWidget getApplicableRegion(CasaPaletteItem selNode) {
        CasaRegionWidget region = null;
        if (selNode != null) {
            switch(selNode.getCategory()) {
                case WSDL_BINDINGS :
                    region = getScene().getBindingRegion();
                    break;
                case SERVICE_UNITS :
                    if(CasaPalette.CASA_PALETTE_ITEM_TYPE.INT_SU == selNode.getPaletteItemType()) {
                        region = getScene().getEngineRegion();
                    } else if (CasaPalette.CASA_PALETTE_ITEM_TYPE.EXT_SU == selNode.getPaletteItemType()) {
                        region = getScene().getExternalRegion();
                    }
                    break;
                default:
                    break;
            }
        } 
        return region;
    }
    
    private CasaPaletteItem getCasaPaletteItem(Transferable transferable) {
        CasaPaletteItem selNode = null;
        if (transferable.isDataFlavorSupported(CasaPalette.CasaPaletteDataFlavor)) {
            try {
                selNode = (CasaPaletteItem) transferable.getTransferData(CasaPalette.CasaPaletteDataFlavor);
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return selNode;
    }
}

