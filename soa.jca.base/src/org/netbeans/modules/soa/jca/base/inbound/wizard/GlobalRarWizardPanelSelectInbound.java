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

package org.netbeans.modules.soa.jca.base.inbound.wizard;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.netbeans.api.project.Project;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 * wizard panel
 *
 * @author echou
 */
public class GlobalRarWizardPanelSelectInbound implements WizardDescriptor.Panel, ListSelectionListener, ItemListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private GlobalRarVisualPanelSelectInbound component;

    private Project project;

    public GlobalRarWizardPanelSelectInbound(Project project) {
        this.project = project;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        return getUIPanel();
    }

    private GlobalRarVisualPanelSelectInbound getUIPanel() {
        if (component == null) {
            component = new GlobalRarVisualPanelSelectInbound(this, project);
        }
        return component;
    }

    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return new HelpCtx("org.netbeans.modules.soa.jca.base.about");
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    public boolean isValid() {
        return getUIPanel().isWizardValid();
    }

    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }
    public final void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }
    protected final void fireChangeEvent() {
        Iterator<ChangeListener> it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }
        ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            it.next().stateChanged(ev);
        }
    }

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    public void readSettings(Object settings) {
        if (settings instanceof WizardDescriptor) {
            getUIPanel().initFromSettings((WizardDescriptor) settings);
        }
    }

    public void storeSettings(Object settings) {
        if (settings instanceof WizardDescriptor) {
            getUIPanel().storeToSettings((WizardDescriptor) settings);
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        fireChangeEvent();
    }

    public void itemStateChanged(ItemEvent e) {
        fireChangeEvent();
    }

}

