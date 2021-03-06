/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 1997-2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.worklist.editor.designview;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.modules.wlm.model.api.TAction;
import org.netbeans.modules.wlm.model.api.TTask;
import org.netbeans.modules.wlm.model.api.WLMComponent;
import org.netbeans.modules.wlm.model.api.WLMModel;
import org.netbeans.modules.worklist.editor.designview.components.ExTabbedPane;
import org.netbeans.modules.worklist.editor.designview.components.ExUtils;
import org.netbeans.modules.worklist.editor.designview.components.LinkButton;
import org.netbeans.modules.worklist.editor.designview.components.StyledLabel;
import org.netbeans.modules.worklist.editor.designview.components.TitledPanel;
import org.netbeans.modules.worklist.editor.nodes.ActionsNode;
import org.netbeans.modules.worklist.editor.nodes.WLMNodeType;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 *
 * @author anjeleevich
 */
public class ActionsPanel extends DesignViewPanel implements Widget {
    
    private AddActionAction addActionAction;
    private AddAction addAction;
    
    public ActionsPanel(DesignView designView) {
        super(designView);

        ExUtils.setA11Y(this, "ActionsPanel"); // NOI18N

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addActionAction = new AddActionAction();
        addAction = new AddAction();
        
        add(addAction);

        processWLMModelChanged(false);
    }
    
    public void processWLMModelChanged(boolean processChildren) {
        TTask task = getTask();
        List<TAction> actionsList = (task == null) ? null 
                : task.getActions();
        
        if (actionsList == null || actionsList.size() == 0) {
            for (int i = getComponentCount() - 2; i >= 0; i--) {
                remove(i);
            }
        } else {
            Map<TAction, ActionPanel> modelToViewMap 
                    = new HashMap<TAction, ActionPanel>();
            
            for (int i = getComponentCount() - 3; i >= 0; i -= 2) {
                Component component = getComponent(i);
                ActionPanel actionPanel 
                        = ((ActionPanel) ((TitledPanel) component)
                        .getContent());
                
                TAction action = actionPanel.getAction();
                if (actionsList.contains(action)) {
                    modelToViewMap.put(actionPanel.getAction(), 
                            actionPanel);
                } else {
                    remove(i + 1);
                    remove(i);
                }
            }
            
            for (int i = 0; i < actionsList.size(); i++) {
                TAction action = actionsList.get(i);

                ActionPanel actionPanel = modelToViewMap
                        .get(action);
                
                if (actionPanel == null) {
                    actionPanel = new ActionPanel(this, getDesignView(),
                            action);
                    add(actionPanel.getView(), i * 2);
                    add(Box.createVerticalStrut(12), i * 2 + 1)
                            .setFocusable(false);
                } 
           }

            if (processChildren) {
                for (ActionPanel actionPanel : modelToViewMap.values()) 
                {
                    actionPanel.processWLMModelChanged();
                }
            }
        }
        
        revalidate();
        repaint();
    }

    void addToTabbedPane(ExTabbedPane tabbedPane) {
        ExTabbedPane.Tab tab = tabbedPane.addTab(NAME, this, true,
                null, null);
        
        tab.addHeaderRow(getMessage("LBL_ACTIONS"), null, // NOI18N
                StyledLabel.PLAIN_STYLE);        
    }

    public Widget getWidgetParent() {
        return getDesignView();
    }

    public Widget getWidget(int index) {
        int componentCount = getComponentCount();

        for (int i = 0; i <= componentCount; i++) {
            Component component = getComponent(i);
            if (component instanceof TitledPanel) {
                JComponent content = ((TitledPanel) component).getContent();
                if (content instanceof ActionPanel) {
                    if (index == 0) {
                        return (ActionPanel) content;
                    } else {
                        index--;
                    }
                }
            }
        }

        throw new IndexOutOfBoundsException();
    }

    public int getWidgetCount() {
        int count = 0;
        for (int i = getComponentCount() - 1; i >= 0; i--) {
            Component component = getComponent(i);
            if ((component instanceof TitledPanel) && (((TitledPanel)
                    component).getContent() instanceof ActionPanel))
            {
                count++;
            }
        }
        return count;
    }

    public Node getWidgetNode() {
        return new ActionsNode(getTask(), Children.LEAF, getNodeLookup());
    }

    public void requestFocusToWidget() {
        getDesignView().showActionsTab();
    }

    public WLMComponent getWidgetWLMComponent() {
        return getTask();
    }

    public WLMNodeType getWidgetNodeType() {
        return WLMNodeType.ACTIONS;
    }

    private class AddAction extends JPanel implements FocusListener {
        private LinkButton addActionButton;

        AddAction() {
            setOpaque(false);

            ExUtils.setA11Y(this, ActionsPanel.class,
                    "AddActionPanel"); // NOI18N

            addActionButton = new LinkButton(getMessage(
                    "LBL_ADD_ACTION")); // NOI18N
            addActionButton.addActionListener(addActionAction);
            addActionButton.addFocusListener(this);

            ExUtils.setA11Y(addActionButton, ActionsPanel.class,
                    "AddActionButtton"); // NOI18N

            add(addActionButton);
        }
        
        @Override
        public Insets getInsets() {
            return new Insets(8, 8, 8, 8);
        }

        @Override
        public void doLayout() {
            Insets insets = getInsets();
            
            int w = getWidth() - insets.left - insets.right;
            int h = getHeight() - insets.top - insets.bottom;
            
            Dimension size = addActionButton.getPreferredSize();
            
            addActionButton.setBounds(
                    insets.left + (w - size.width) / 2,
                    insets.top + (h - size.height) / 2, 
                    size.width, 
                    size.height);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Stroke oldStoke = g2.getStroke();
            
            g2.setColor(ExTabbedPane.TAB_BORDER_COLOR);
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, 
                    BasicStroke.JOIN_ROUND, 1, new float[] { 4, 4 } , 0));
            g2.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
            
            g2.setStroke(oldStoke);
        }
        
        @Override
        public Dimension getPreferredSize() {
            Insets insets = getInsets();
            Dimension size = addActionButton.getPreferredSize();
            size.width += insets.left + insets.right;
            size.height += insets.top + insets.bottom;
            return size;
        }
        
        @Override
        public Dimension getMaximumSize() {
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public void focusGained(FocusEvent e) {
            selectWidget(ActionsPanel.this);
        }

        public void focusLost(FocusEvent e) {
            // do nothing
        }
    }
    
    
    private class AddActionAction extends AbstractAction {
        AddActionAction() {
            super(getMessage("LBL_ADD_ACTION")); // NOI18N
        }
        
        public void actionPerformed(ActionEvent event) {
            WLMModel model = getModel();
            TTask task = model.getTask();

            Set<String> actionsNames = new HashSet<String>();
            
            List<TAction> actions = task.getActions();
            if (actions != null) {
                for (TAction action : actions) {
                    String name = action.getName();
                    if (name == null) {
                        name = ""; // NOI18N
                    } else {
                        name = name.trim();
                    }
                    
                    if (name.length() > 0) {
                        actionsNames.add(name);
                    }
                }
            }
            
            String baseName = "newAction"; // NOI18N
            String actionName = baseName;
            int i = 1;
            while (actionsNames.contains(actionName)) {
                actionName = baseName + i;
                i++;
            }
            
            if (model.startTransaction()) {
                TAction action = null;
                try {
                    action = model.getFactory().createAction(model);
                    action.setName(actionName);
                    task.addAction(action);
                } finally {
                    model.endTransaction();
                }
            } 
        }
    }
    
    private static final String NAME = "ACTIONS"; // NOI18N
    
    private static String getMessage(String key) {
        return NbBundle.getMessage(ActionsPanel.class, key);
    }          
}
