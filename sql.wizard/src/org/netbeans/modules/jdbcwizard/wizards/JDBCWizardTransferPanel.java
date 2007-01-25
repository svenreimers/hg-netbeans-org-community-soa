/*
 * 
 * Copyright 2005 Sun Microsystems, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.netbeans.modules.jdbcwizard.wizards;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 * Implements a two-list transfer panel with bulk add/remove capability.
 * 
 * @author
 */
public class JDBCWizardTransferPanel extends JPanel implements ActionListener, WizardDescriptor.Panel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Log4J category string */
    private static final String LOG_CATEGORY = JDBCWizardTransferPanel.class.getName();

    /* Set <ChangeListeners> */
    private final Set listeners = new HashSet(1);

    private List selTableList = new ArrayList();

    private JDBCWizardTablePanel tablePanel;

    /** Creates a default instance of JDBCWizardTransferPanel. */
    public JDBCWizardTransferPanel() {
    }

    /**
     * Creates a new instance of JDBCWizardTransferPanel using the given ListModels to initially
     * populate the source and destination panels.
     * 
     * @param title String to be displayed as title of this panel
     * @param dsList List of DatabaseModels used to populate datasource panel
     * @param destColl Collection of selected DatabaseModels
     * @param sourceOTD true if this panel displays available selections for source OTDs; false if
     *            it displays available destination OTDs
     */
    public JDBCWizardTransferPanel(final String title) {
        this();
        if (title != null && title.trim().length() != 0) {
            this.setName(title);
        }

        final GridBagLayout gridbag = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();

        final ArrayList testList = new ArrayList();
        this.tablePanel = new JDBCWizardTablePanel(testList);

        this.setLayout(new BorderLayout());
        this.add(this.tablePanel, BorderLayout.CENTER);
        JDBCWizardTransferPanel.this.tablePanel.resetTable(this.selTableList);
    }

    /**
     * Invoked whenever one of the transfer buttons is clicked.
     * 
     * @param e ActionEvent to handle
     */
    public void actionPerformed(final ActionEvent e) {
        // String cmd = e.getActionCommand();

    }

    /**
     * @see org.openide.WizardDescriptor.Panel#addChangeListener
     */
    public void addChangeListener(final ChangeListener l) {
        synchronized (this.listeners) {
            this.listeners.add(l);
        }
    }

    /**
     * @see org.openide.WizardDescriptor.Panel#getComponent
     */
    public Component getComponent() {
        return this;
    }

    /**
     * @see org.openide.WizardDescriptor.Panel#getHelp
     */
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;

    }

    /**
     * @see org.openide.WizardDescriptor.Panel#isValid
     */
    public boolean isValid() {
        boolean returnVal = false;

        if (this.tablePanel.getTables().size() != 0) {
            returnVal = true;
        }
        return returnVal;
    }

    /**
     * @see org.openide.WizardDescriptor.Panel#readSettings
     */
    public void readSettings(final Object settings) {
        WizardDescriptor wizard = null;
        if (settings instanceof JDBCWizardContext) {
            final JDBCWizardContext wizardContext = (JDBCWizardContext) settings;
            wizard = (WizardDescriptor) wizardContext.getProperty(JDBCWizardContext.WIZARD_DESCRIPTOR);

        } else if (settings instanceof WizardDescriptor) {
            wizard = (WizardDescriptor) settings;
        }

        if (wizard != null && WizardDescriptor.NEXT_OPTION.equals(wizard.getValue())) {

            final Object[] sources = (Object[]) wizard.getProperty(JDBCWizardContext.SELECTEDTABLES);
            this.selTableList = Arrays.asList(sources);
            this.tablePanel.resetTable(this.selTableList);
        }
    }

    /**
     * @see org.openide.WizardDescriptor.Panel#removeChangeListener
     */
    public void removeChangeListener(final ChangeListener l) {
        synchronized (this.listeners) {
            this.listeners.remove(l);
        }
    }

    /**
     * @see org.openide.WizardDescriptor.Panel#storeSettings
     */
    public void storeSettings(final Object settings) {
        WizardDescriptor wizard = null;
        if (settings instanceof JDBCWizardContext) {
            final JDBCWizardContext wizardContext = (JDBCWizardContext) settings;
            wizard = (WizardDescriptor) wizardContext.getProperty(JDBCWizardContext.WIZARD_DESCRIPTOR);

        } else if (settings instanceof WizardDescriptor) {
            wizard = (WizardDescriptor) settings;
        }
    }

}
