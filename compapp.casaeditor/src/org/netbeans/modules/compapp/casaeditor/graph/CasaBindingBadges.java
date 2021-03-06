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

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.NbBundle;

/**
 *
 * @author jsandusky
 * @author jqian
 */
public class CasaBindingBadges {
    
    private static final String SOAP_BINDING = "soap"; // NOI18N
    private static final String SOAP12_BINDING = "soap12"; // NOI18N

    public enum Badge {
        IS_EDITABLE(RegionUtilities.IMAGE_EDIT_16_ICON, "EDIT_BADGE_TOOLTIP"), // NOI18N
        WS_POLICY(RegionUtilities.IMAGE_WS_POLICY_16_ICON, "WS_POLICY_BADGE_TOOLTIP"); // NOI18N

        private Badge(Image image, String tooltipLabel) {
            mImage = image;
            mTooltipLabel = tooltipLabel;
        }

        public Image getImage() {
            return mImage;
        }
        
        public String getTooltip() {
            return NbBundle.getMessage(getClass(), mTooltipLabel);
        }

        private Image mImage;
        private String mTooltipLabel;
    }
    
    private Widget mContainerWidget;
    private Map<Badge, ImageWidget> mBadgeWidgets = new HashMap<Badge, ImageWidget>();
    
    
    public CasaBindingBadges(Scene scene, String bindingType) {
        mContainerWidget = new Widget(scene);
        mContainerWidget.setOpaque(false);
        mContainerWidget.setLayout(
            LayoutFactory.createVerticalFlowLayout(LayoutFactory.SerialAlignment.LEFT_TOP, 1));
        mContainerWidget.setPreferredSize(new Dimension(16, 0));
                
        for (Badge badge : Badge.values()) {
            // skip WSIT config for non-soap binding
            if (SOAP_BINDING.equalsIgnoreCase(bindingType) ||
                    SOAP12_BINDING.equalsIgnoreCase(bindingType) ||
                    (badge != Badge.WS_POLICY)) {
                ImageWidget badgeWidget = new ImageWidget(scene);
                badgeWidget.setToolTipText(badge.getTooltip());  // FIXME: tooltip not showing!
                mBadgeWidgets.put(badge, badgeWidget);
                mContainerWidget.addChild(badgeWidget);
            }
        }
    }
    
    public void setBadge(Badge badge, boolean isActive) {
        ImageWidget imageWidget = mBadgeWidgets.get(badge);
        if (imageWidget != null) {
            imageWidget.setImage(isActive ? badge.getImage() : null);
        }
    }
    
    public void setBadgePressed(Badge badge, boolean isPressed) {
        mBadgeWidgets.get(badge).setPaintAsDisabled(isPressed);
    }

    /**
     * Sets the pressed state on all badges.
     *
     * @param isPressed     the new pressed state
     */
    public void setBadgePressed(boolean isPressed) {
        for (Badge badge : Badge.values()) {
            ImageWidget badgeWidget = mBadgeWidgets.get(badge);
            if (badgeWidget != null) {
                badgeWidget.setPaintAsDisabled(isPressed);
            }
        }
    }
    
    public Rectangle getBadgeBoundsForParent(Badge badge, Widget parentWidget) {
        Widget widget = mBadgeWidgets.get(badge);
        if (widget == null) {
            return null;
        }
        
        Point location = widget.getLocation();
        Widget iterParent = widget.getParentWidget();
        while (iterParent != null && iterParent != parentWidget) {
            location.x += iterParent.getLocation().x;
            location.y += iterParent.getLocation().y;
            iterParent = iterParent.getParentWidget();
        }
        return new Rectangle(location, widget.getBounds().getSize());
    }
    
    public Widget getContainerWidget() {
        return mContainerWidget;
    }
}
