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

package org.netbeans.modules.compapp.casaeditor.graph;

import java.awt.Image;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.modules.compapp.casaeditor.graph.RegionUtilities.Directions;
import org.openide.util.ImageUtilities;

/**
 *
 * @author rdara
 * @author jqian
 */
public abstract class CasaPinWidgetBinding extends CasaPinWidget {
    
    private static final Image IMAGE_ARROW_RIGHT_CONSUMES = ImageUtilities.loadImage(
            "org/netbeans/modules/compapp/casaeditor/graph/resources/consumesRight.png"); // NOI18N    
    private static final Image IMAGE_ARROW_RIGHT_CONSUMES_CLASSIC = ImageUtilities.loadImage(
            "org/netbeans/modules/compapp/casaeditor/graph/resources/consumesRightClassic.png"); // NOI18N
    
    private static final Image IMAGE_ARROW_LEFT_PROVIDES = ImageUtilities.loadImage(
            "org/netbeans/modules/compapp/casaeditor/graph/resources/providesLeft.png"); // NOI18N
    private static final Image IMAGE_ARROW_LEFT_PROVIDES_CLASSIC = ImageUtilities.loadImage(
            "org/netbeans/modules/compapp/casaeditor/graph/resources/providesLeftClassic.png"); // NOI18N


    public CasaPinWidgetBinding(Scene scene, Image pinImage, Image classicPinImage) {
        super(scene, pinImage, classicPinImage);
        
        addChild(mImageWidget);
    }
            
    protected void setPinName(String name) {
        // do nothing
    }
  
    @Override
    public Anchor getAnchor() {
        return RegionUtilities.createFixedDirectionalAnchor(this, Directions.RIGHT, 0);
    }

    /**
     * The Consume Pin inside Binding Widget.
     */
    public static class Consumes extends CasaPinWidgetBinding {
        public Consumes(Scene scene) {
            super(scene, IMAGE_ARROW_RIGHT_CONSUMES, IMAGE_ARROW_RIGHT_CONSUMES_CLASSIC);
        }
    }

    /**
     * The Provide Pin inside Binding Widget.
     */
    public static class Provides extends CasaPinWidgetBinding {
        public Provides(Scene scene) {
            super(scene, IMAGE_ARROW_LEFT_PROVIDES, IMAGE_ARROW_LEFT_PROVIDES_CLASSIC);
        }
    }
}
