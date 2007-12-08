/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
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
package org.netbeans.modules.etl.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.openide.awt.MouseUtils;
import org.openide.awt.TabbedPaneFactory;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
public final class ETLOutputWindowTopComponent extends TopComponent implements PropertyChangeListener {

    private static ETLOutputWindowTopComponent instance;
    /** path to the icon used by the component and its open action */
    //    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "ETLOutputWindowTopComponent";
    private Set<Component> components = new HashSet<Component>(1);
    private JTabbedPane tabbedPane = TabbedPaneFactory.createCloseButtonTabbedPane();
    private PopupListener listener;
    private JPopupMenu pop;
    private CloseListener closeL;
    private transient boolean isVisible = false;
    private boolean inited = false;

    private ETLOutputWindowTopComponent() {
        initComponents();
        setLayout(new BorderLayout());
        setName(NbBundle.getMessage(ETLOutputWindowTopComponent.class, "CTL_ETLOutputWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(ETLOutputWindowTopComponent.class, "HINT_ETLOutputWindowTopComponent"));
        tabbedPane.addPropertyChangeListener(this);
        pop = new JPopupMenu();
        listener = new PopupListener();
        closeL = new CloseListener();
    }

    private class CloseListener implements PropertyChangeListener {

        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            if (TabbedPaneFactory.PROP_CLOSE.equals(evt.getPropertyName())) {
                removePanel((Component) evt.getNewValue());
            }
        }
    }

/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private class PopupListener extends MouseUtils.PopupMouseAdapter {

        /**
         * Called when the sequence of mouse events should lead to actual showing popup menu
         */
        @Override
        protected void showPopup(MouseEvent e) {
            pop.show(ETLOutputWindowTopComponent.this, e.getX(), e.getY());
        }
    } // end of PopupListener

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     * @return ETLOutputWindowTopComponent defaultInstance
     */
    public static synchronized ETLOutputWindowTopComponent getDefault() {
        if (instance == null) {
            instance = new ETLOutputWindowTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ETLOutputWindowTopComponent instance. Never call {@link #getDefault} directly!
     * @return ETLOutputWindowTopComponent defaultInstance
     */
    public static synchronized ETLOutputWindowTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(ETLOutputWindowTopComponent.class.getName()).warning("Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ETLOutputWindowTopComponent) {
            return (ETLOutputWindowTopComponent) win;
        }
        Logger.getLogger(ETLOutputWindowTopComponent.class.getName()).warning("There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }
    //removePanel

    public void removePanel(Component panel) {
        Component comp = getComponentCount() > 0 ? getComponent(0) : null;
        if (comp instanceof JTabbedPane) {
            JTabbedPane tabs = (JTabbedPane) comp;
            if (panel == null) {
                panel = tabs.getSelectedComponent();
            }
            tabs.remove(panel);
            if (tabs.getComponentCount() == 1) {
                Component c = tabs.getComponent(0);
                tabs.removeMouseListener(listener);
                tabs.removePropertyChangeListener(closeL);
                remove(tabs);
                add(c, BorderLayout.CENTER);
            }
        } else {
            if (comp != null) {
                remove(comp);
            }
            isVisible = false;
            close();
        }
        validate();
    }

    public void addPanel(Component panel) {
        if (getComponentCount() == 0) {
            add(panel, BorderLayout.CENTER);
        } else {
            Component comp = getComponent(0);
            if (comp instanceof JTabbedPane) {
                ((JTabbedPane) comp).addTab(panel.getName() + "  ", null, panel, panel.getName()); //NOI18N
                ((JTabbedPane) comp).setSelectedComponent(panel);
                comp.validate();
            } else if (comp instanceof JLabel) {
                remove(comp);
                add(panel, BorderLayout.CENTER);
            } else {
                remove(comp);
                JTabbedPane pane = TabbedPaneFactory.createCloseButtonTabbedPane();
                pane.addMouseListener(listener);
                pane.addPropertyChangeListener(closeL);
                add(pane, BorderLayout.CENTER);
                pane.addTab(comp.getName() + "  ", null, comp, comp.getName()); //NOI18N
                pane.addTab(panel.getName() + "  ", null, panel, panel.getName()); //NOI18N
                pane.setSelectedComponent(panel);
                pane.validate();
            }
        }
        if (!isVisible) {
            isVisible = true;
            open();
        }
        validate();
        requestActive();
    }
    //remove Component

    private void removeComponent(Component c) {
        assert SwingUtilities.isEventDispatchThread();
        assert components.remove(c);
        if (components.size() == 0) {
            removeAll();
        } else if (components.size() == 1) {
            tabbedPane.removeAll();
            removeAll();
            add(components.iterator().next());
        } else {
            tabbedPane.remove(c);
        }
        revalidate();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    static final class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return ETLOutputWindowTopComponent.getDefault();
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (TabbedPaneFactory.PROP_CLOSE.equals(evt.getPropertyName())) {
            Component c = (Component) evt.getNewValue();
            removeComponent(c);
        }
    }
}
