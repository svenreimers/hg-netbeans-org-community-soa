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
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.compapp.casaeditor.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LabelWidget.Alignment;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 * Widget for CASA service engine service unit's process title.
 * 
 * @author jqian
 */
public class CasaProcessTitleWidget extends Widget implements CasaMinimizable {

    private ImageWidget imageWidget;
    private LabelWidget titleWidget;

    public CasaProcessTitleWidget(Scene scene, String processName, Image image) {
        super(scene);

        setLayout(RegionUtilities.createHorizontalFlowLayoutWithJustifications(
                LayoutFactory.SerialAlignment.CENTER, 5));

        setBorder(BorderFactory.createEmptyBorder(
                0, CasaNodeWidgetEngine.ARROW_PIN_WIDTH,
                0, CasaNodeWidgetEngine.ARROW_PIN_WIDTH));

        imageWidget = new ImageWidget(scene, image);
        
        titleWidget = new LabelWidget(getScene(), processName);
        titleWidget.setOpaque(false);
        titleWidget.setAlignment(Alignment.RIGHT);
        titleWidget.setFont(getScene().getDefaultFont().deriveFont(Font.BOLD));

        addChild(imageWidget);
        addChild(titleWidget);

        setSelected(false);
    }

    /**
     * Called to notify about the change of the widget state.
     * 
     * @param previousState the previous state
     * @param state the new state
     */
    @Override
    protected void notifyStateChanged(ObjectState previousState, ObjectState state) {
        super.notifyStateChanged(previousState, state);

        if ((!previousState.isSelected() && state.isSelected()) ||
                (!previousState.isFocused() && state.isFocused())) {
            setSelected(true);
        } else if ((previousState.isSelected() && !state.isSelected()) ||
                (previousState.isFocused() && !state.isFocused())) {
            setSelected(false);
        }
    }

    private void setSelected(boolean isSelected) {
        titleWidget.setForeground(isSelected ? Color.black : Color.gray);
    }
    
    public void setMinimized(boolean isMinimized) {
        if (isMinimized) {            
            imageWidget.removeFromParent();
            titleWidget.removeFromParent();
        } else {
            addChild(imageWidget);
            addChild(titleWidget);
        }
    }
}