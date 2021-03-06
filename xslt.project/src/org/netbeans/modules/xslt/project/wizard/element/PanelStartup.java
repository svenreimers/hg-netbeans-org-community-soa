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
 * License. When distributing the software, include this License Header
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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
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
package org.netbeans.modules.xslt.project.wizard.element;

import java.awt.AWTKeyStroke;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import org.netbeans.api.project.Project;
import static org.netbeans.modules.xml.misc.UI.*;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2006.12.25
 */
final class PanelStartup extends Panel {

    PanelStartup(Project project, Panel parent) {
        super(project, parent);
        myTransformationPanel = new PanelWSDL(getProject(), this);
        myProxyPanel = new PanelWSDLs(getProject(), this);
    }

    @Override
    protected String getComponentName() {
        return NAME_TYPE;
    }

    @Override
    protected Panel getNext() {
        if (myTransformation != null && myTransformation.isSelected()) {
            return myTransformationPanel;
        } else {
            return myProxyPanel;
        }
    }

    @Override
    protected void createPanel(JPanel mainPanel, GridBagConstraints cc) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ButtonGroup group = new ButtonGroup();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1.0;
        c.weighty = 1.0;

        // (o) Request-Reply Service
        c.gridy++;
        c.insets = new Insets(LARGE_SIZE, 0, 0, 0);
        myTransformation = createRadioButton(i18n("LBL_Service"), i18n("TLT_Service")); // NOI18N
//    myTransformation.getAccessibleContext().setAccessibleDescription(i18n("ACSD_LBL_Service"));
        panel.add(myTransformation, c);
        group.add(myTransformation);

        // text
        c.gridy++;
        c.insets = new Insets(LARGE_SIZE, HUGE_SIZE * 2, TINY_SIZE, 0);
        JTextArea serviceText = createTextArea(TEXT_WIDTH, i18n("LBL_Service_Text"), false);
        a11y(serviceText, "ACSN_LBL_Service_Text", "ACSD_LBL_Service_Text");
        panel.add(serviceText, c);//NOI18N
        // this text area is used to describe myTransformation radio button functionality
        myTransformation.getAccessibleContext().setAccessibleDescription(serviceText.getText());

        // (o) Proxy Service
        c.gridy++;
        c.insets = new Insets(LARGE_SIZE, 0, 0, 0);
        myProxy = createRadioButton(i18n("LBL_Bridge"), i18n("TLT_Bridge")); // NOI18N
//    myProxy.getAccessibleContext().setAccessibleDescription(i18n("ACSD_LBL_Bridge"));
        panel.add(myProxy, c);
        group.add(myProxy);

        myTransformation.setSelected(true);

        // text
        c.gridy++;
        c.insets = new Insets(LARGE_SIZE, HUGE_SIZE * 2, TINY_SIZE, 0);
        JTextArea bridgeText = createTextArea(TEXT_WIDTH, i18n("LBL_Bridge_Text"), false);
        a11y(bridgeText, "ACSN_LBL_Bridge_Text", "ACSD_LBL_Bridge_Text");
        panel.add(bridgeText, c); // NOI18N
        myProxy.getAccessibleContext().setAccessibleDescription(bridgeText.getText());

        Set<AWTKeyStroke> downTraversKeys = panel.getFocusTraversalKeys(KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS);
        for (AWTKeyStroke aWTKeyStroke : downTraversKeys) {
            System.out.println("down key stroke: "+aWTKeyStroke);
        }

        // add arrow traversing support
        Set<AWTKeyStroke> forwardTraversKeys = panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> newForwardTKs = new HashSet<AWTKeyStroke>();
        newForwardTKs.addAll(forwardTraversKeys);

        newForwardTKs.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, 0));
        newForwardTKs.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardTKs);

        Set<AWTKeyStroke> backwardTraversKeys = panel.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> newBackwardTKs = new HashSet<AWTKeyStroke>();
        newBackwardTKs.addAll(backwardTraversKeys);

        newBackwardTKs.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_UP, 0));
        newBackwardTKs.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newBackwardTKs);

//  panel.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue));
        mainPanel.add(panel, cc);
        mainPanel.getAccessibleContext().setAccessibleDescription(i18n("ACSD_LBL_NewXsltService"));
    }

    private Panel myTransformationPanel;
    private JRadioButton myTransformation;
    private Panel myProxyPanel;
    private JRadioButton myProxy;
    private static final int TEXT_WIDTH = 40;
}
