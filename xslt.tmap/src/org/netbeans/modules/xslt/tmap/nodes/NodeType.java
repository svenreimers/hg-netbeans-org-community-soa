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

package org.netbeans.modules.xslt.tmap.nodes;

import java.awt.Image;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.modules.xslt.tmap.model.api.Import;
import org.netbeans.modules.xslt.tmap.model.api.Invoke;
import org.netbeans.modules.xslt.tmap.model.api.Operation;
import org.netbeans.modules.xslt.tmap.model.api.Param;
import org.netbeans.modules.xslt.tmap.model.api.Service;
import org.netbeans.modules.xslt.tmap.model.api.TMapComponent;
import org.netbeans.modules.xslt.tmap.model.api.Transform;
import org.netbeans.modules.xslt.tmap.model.api.TransformMap;
import org.openide.util.ImageUtilities;
import org.netbeans.modules.xslt.tmap.model.api.Variable;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
 *
 * @author Vitaly Bychkov
 * @version 1.0
 */
public enum NodeType {
    UNKNOWN_TYPE, // Special element which means that the value isn't known.
    TRANSFORMMAP(TransformMap.class),
    IMPORT(Import.class),
    VARIABLE(Variable.class),
    SERVICE(Service.class),
    OPERATION(Operation.class),
    INVOKE(Invoke.class),
    TRANSFORM(Transform.class),
    PARAM(Param.class),
    IMPORTS_CONTAINER(),
    VARIABLES_CONTAINER(Operation.class),
    WSDL_FILE(),
    NON_IMPORTED_ARTIFACTS(),
    PORT_TYPE(),
    WSDL_OPERATION();

    private AtomicReference<String> myDisplayName = new AtomicReference<String>();
    private AtomicReference<Image> myDefaultImage = new AtomicReference<Image>();
    private Class myComponentType;
    private static final String IMAGE_FOLDER_PATH =
            "org/netbeans/modules/xslt/tmap/resources/images/"; // NOI18N
    public static final Image UNKNOWN_IMAGE = getImageImpl(UNKNOWN_TYPE, null);

    private NodeType() {
    }

    private NodeType(Class tMapComponentType) {
        myComponentType = tMapComponentType;
    }
    
    public static NodeType getNodeType(TMapComponent entity) {
        if (entity == null) {
            return null;
        }
        return getNodeType(entity.getComponentType());
    }
    
    public static NodeType getNodeType(Class tMapComponentType) {
        NodeType type = UNKNOWN_TYPE;
        NodeType[] types =  values();
        for (NodeType typeEl : types) {
            if (tMapComponentType == typeEl.getComponentType()) {
                type = typeEl;
                break;
            } 
        }
        return type;
    }
    
    public Class<? extends TMapComponent> getComponentType() {
        return myComponentType;
    }
    
    public String getDisplayName() {
        if (myDisplayName.get() == null) {
            try {
                myDisplayName.compareAndSet(null,
                        NbBundle.getMessage(NodeType.class, this.toString()));
            } catch(Exception ex) {
                myDisplayName.compareAndSet(null, name());
            }
        }
        return myDisplayName.get();
    }
    
    public String getDisplayName(Object modificator) {
        String displayName = null;
        try {
            String key = this.toString() + "_" + modificator.toString();
            displayName = NbBundle.getMessage(NodeType.class, key);
        } catch (Exception ex) {
            // do nothing
        }
        // can return null intentionally!
        return displayName;
    }

    public Icon getIcon() {
        return new ImageIcon(getImage());
    }    
    
    public Image getImage() {
        if (myDefaultImage.get() == null) {
            Image image = getImageImpl(this, null);
            if (image == null) {
                image = UNKNOWN_IMAGE;
            }
            //
            myDefaultImage.compareAndSet(null, image);
        }
        return myDefaultImage.get();
    }
    
    private static Image getImageImpl(Object name, Object modificator) {
        String fileName = null;
        if (modificator == null) {
            fileName = IMAGE_FOLDER_PATH + name + ".png"; // NOI18N
        } else {
            fileName = IMAGE_FOLDER_PATH + name + "_" + modificator + ".png"; // NOI18N
        }
        return ImageUtilities.loadImage(fileName);
    }
}
