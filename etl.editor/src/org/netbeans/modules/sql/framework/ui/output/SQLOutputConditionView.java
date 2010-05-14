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
package org.netbeans.modules.sql.framework.ui.output;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import net.java.hulp.i18n.Logger;
import org.netbeans.modules.etl.logger.Localizer;
import org.netbeans.modules.sql.framework.ui.view.conditionbuilder.IConditionGraphViewContainer;
import org.openide.awt.TabbedPaneFactory;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
public final class SQLOutputConditionView extends TopComponent implements PropertyChangeListener, Lookup.Provider {

    private static SQLOutputConditionView instance;
    private static final String PREFERRED_ID = "ETLOutputWindowTopComponent";
    private static IConditionGraphViewContainer sqlView;
    private Set<Component> components = new HashSet<Component>(1);
    private JTabbedPane tabbedPane = TabbedPaneFactory.createCloseButtonTabbedPane();
    private static transient final Logger mLogger = Logger.getLogger(SQLOutputConditionView.class.getName());
    private static transient final Localizer mLoc = Localizer.get();

    public SQLOutputConditionView(IConditionGraphViewContainer view) {
        sqlView = view;
        initComponents();
        // setIcon(org.openide.util.ImageUtilities.loadImage("org/netbeans/modules/etl/ui/resources/images/ETLCollab.gif"));
        setLayout(new BorderLayout());
        String nbBundle1 = mLoc.t("BUND364: Conditional Builder Output View");
        setName(nbBundle1.substring(15));
        String nbBundle2 = mLoc.t("BUND364: Conditional Builder Output View");
        getAccessibleContext().setAccessibleDescription(nbBundle2.substring(15));
        String nbBundle3 = mLoc.t("BUND364: Conditional Builder Output View");
        setToolTipText(nbBundle3.substring(15));
        tabbedPane.addPropertyChangeListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    public static synchronized SQLOutputConditionView getDefault() {
        if (instance == null) {
            instance = new SQLOutputConditionView(sqlView);
        }
        return instance;
    }

    /**
     * Obtain the ETLOutputWindowTopComponent instance. Never call {@link #getDefault} directly!
     * @return ETLOutputWindowTopComponent defaultInstance
     */
    public static synchronized SQLOutputConditionView findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            mLogger.infoNoloc(mLoc.t("EDIT702: Cannot find{0}component. It will not be located properly in the window system.in{1}", PREFERRED_ID, SQLOutputConditionView.class.getName()));
            return getDefault();
        }
        if (win instanceof SQLOutputConditionView) {
            return (SQLOutputConditionView) win;
        }
        mLogger.infoNoloc(mLoc.t("EDIT703: There seem to be multiple components with the '{0}' ID. That is a potential source of errors and unexpected behavior.", PREFERRED_ID, SQLOutputConditionView.class.getName()));
        return getDefault();
    }

    public void addComponent(Component c) {
        assert SwingUtilities.isEventDispatchThread();
        if (!components.add(c)) {
            return;
        }
        if (components.size() == 1) {
            assert getComponentCount() == 0;
            add(c);
        } else if (components.size() == 2) {
            assert getComponentCount() == 1;
            removeAll();
            Iterator<Component> it = components.iterator();
            tabbedPane.add(it.next());
            tabbedPane.add(it.next());
            tabbedPane.setSelectedComponent(c);
            add(tabbedPane);
        } else {
            assert getComponentCount() == 1;
            tabbedPane.add(c);
            tabbedPane.setSelectedComponent(c);
        }
        //updateName();
        revalidate();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (TabbedPaneFactory.PROP_CLOSE.equals(evt.getPropertyName())) {
            Component c = (Component) evt.getNewValue();
            removeComponent(c);
        }
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
        // updateName();
        revalidate();
    }
}
