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
package org.netbeans.modules.wsdlextensions.jdbc.wizards;

import java.awt.Component;
import java.io.IOException;
import javax.swing.JComponent;
import org.openide.WizardDescriptor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.netbeans.modules.xml.wsdl.bindingsupport.spi.WSDLWizardContext;
import org.netbeans.modules.xml.wsdl.bindingsupport.spi.WSDLWizardDescriptorPanel;
import org.netbeans.modules.xml.wsdl.bindingsupport.spi.WSDLWizardExtensionIterator;

/**
 * A wizard iterator (sequence of panels). Used to create a wizard. Create one or more panels from
 * template as needed too.
 */
//public abstract class JDBCWizardIterator implements WizardDescriptor.InstantiatingIterator {
public class JDBCWizardIterator extends WSDLWizardExtensionIterator {

    /** Tracks index of current panel */
    protected transient int index = 0;

    /* Set <ChangeListener> */
    private final transient Set listeners = new HashSet(1);

    /* Contains panels to be iterated */
    private transient WizardDescriptor.Panel[] panels = null;

    private transient WizardDescriptor wiz;

    private transient WizardDescriptor.Iterator simpleIterator;

    private String templateName;
    private String[] steps;
    private int currentStepIndex = -1;
    private WSDLWizardDescriptorPanel[] bindingPanels;
    
    public JDBCWizardIterator(WSDLWizardContext context) {
        super(context); 
        wiz = context.getWizardDescriptor();  
    }   
    
    public void setup() {
        initialize(wiz);
    }
    /**
     * 
     *
     */
    public JDBCWizardIterator() {
        this(null);
    }

    // If something changes dynamically (besides moving between panels),
    // e.g. the number of panels changes in response to user input, then
    // implement fireChangeEvent().

    /**
     * @see org.openide.WizardDescriptor.Iterator#addChangeListener
     */
    public final void addChangeListener(final ChangeListener l) {

        synchronized (this.listeners) {
            this.listeners.add(l);
        }
        // getSimpleIterator().addChangeListener(l);
    }

    public int getIndex() {
        return this.index;
    }

    /**
     * @see org.openide.WizardDescriptor.Iterator#current
     */
    public WSDLWizardDescriptorPanel current() {
        return this.getPanels(this.wiz)[this.index];
        // return getSimpleIterator().current();
    }

    /**
     * @see org.openide.WizardDescriptor.Iterator#hasNext
     */
    public boolean hasNext() {
        return this.index < this.getPanels(this.wiz).length - 1;
        // return getSimpleIterator().hasNext();
    }

    /**
     * @see org.openide.WizardDescriptor.Iterator#hasPrevious
     */
    public boolean hasPrevious() {
        return this.index > 0;
        // return getSimpleIterator().hasPrevious();
    }

    /**
     * @see org.openide.WizardDescriptor.Iterator#name
     */
//TODO    public abstract String name();
    

    /**
     * @see org.openide.WizardDescriptor.Iterator#nextPanel
     */
    public void nextPanel() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        this.index++;
        // getSimpleIterator().nextPanel();
    }

    /**
     * @see org.openide.WizardDescriptor.Iterator#previousPanel
     */
    public void previousPanel() {
        if (!this.hasPrevious()) {
            throw new NoSuchElementException();
        }

        this.index--;
        // getSimpleIterator().previousPanel();
    }

    /**
     * @see org.openide.WizardDescriptor.Iterator#removeChangeListener
     */
    public final void removeChangeListener(final ChangeListener l) {
        synchronized (this.listeners) {
            this.listeners.remove(l);
        }
        // getSimpleIterator().removeChangeListener(l);
    }

    /**
     * Creates list of panels to be displayed.
     * 
     * @return List of panels
     */
//    protected abstract List createPanels(WizardDescriptor wiz);
    public List createPanels(WizardDescriptor wiz) {
        return null;// let subclass' implementation take over
    }

    /**
     * Creates array of step descriptions
     * 
     * @return array of Strings representing task summaries for each panel
     */
//    protected abstract String[] createSteps();
    public String[] createSteps() {
        return null; // let subclass' implementation take over
    }

    /**
     * Gets panels to be displayed.
     * 
     * @return array of WizardDescriptor.Panel objects
     */
//    protected final WizardDescriptor.Panel[] getPanels(final WizardDescriptor wiz) {
public WSDLWizardDescriptorPanel[] getPanels(final WizardDescriptor wiz) {    
        if (this.bindingPanels == null) {
            final List myPanels = this.createPanels(wiz);

            final WSDLWizardDescriptorPanel[] pnlArray = new WSDLWizardDescriptorPanel[myPanels.size()];
            this.bindingPanels = (WSDLWizardDescriptorPanel[]) myPanels.toArray(pnlArray);
        }
        return this.bindingPanels;
    }

    /**
     * Gets list of steps corresponding to each panel
     * 
     * @return array of Strings summarizing the task in each panel
     */
//    protected final String[] getSteps() {
    public String[] getSteps() {
        return this.createSteps();
    }

    public void initialize(final WizardDescriptor wiz) {
        this.bindingPanels = this.getPanels(wiz);
        this.wiz = wiz;

        final Object prop = wiz.getProperty("WizardPanel_contentData"); // NOI18N
        if (prop != null && prop instanceof String[]) {
        }
        final String[] steps = this.createSteps();
        for (int i = 0; i < this.bindingPanels.length; i++) {
            final Component c = this.bindingPanels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                final JComponent jc = (JComponent) c;
                // Step #.
                jc.putClientProperty("WizardPanel_contentSelectedIndex", Integer.valueOf(String.valueOf(i))); // NOI18N
                // Step name (actually the whole list for reference).
                jc.putClientProperty("WizardPanel_contentData", steps); // NOI18N
            }
        }
    }

    public void uninitialize(final WizardDescriptor wiz) {
        this.wiz = null;
        this.panels = null;
        this.bindingPanels = null;
    }

    public Set instantiate() throws IOException {
        return new HashSet();
    }

    protected WizardDescriptor.Iterator getSimpleIterator() {
        if (simpleIterator == null) {
            assert (panels != null) && (panels.length > 0);
            simpleIterator = new WizardDescriptor.ArrayIterator(panels);
            
        }

        return simpleIterator;
    }
    
    /**
     * Based on the template selected, define the appropriate panels
     * and steps to show
     * @param templateName
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
        currentStepIndex = -1;
        WSDLWizardContext context = getWSDLWizardContext();
        wiz = context.getWizardDescriptor();
        
//        if (templateName.equals(NbBundle.getMessage(
//                FileWSDLWizardExtensionIterator.class, 
//                "TEMPLATE_name_InboundOneWay"))) {
//            panels = new WSDLWizardDescriptorPanel[]{
//                        new InboundMessageStepWizardPanel(context)
//                    };
//        } else if (templateName.equals(NbBundle.getMessage(
//                FileWSDLWizardExtensionIterator.class, 
//                "TEMPLATE_name_OutboundOneWay"))) {
//            panels = new WSDLWizardDescriptorPanel[]{
//                        new OutboundMessageInStepWizardPanel(context)
//                    };
//        } else if (templateName.equals(NbBundle.getMessage(
//                FileWSDLWizardExtensionIterator.class, 
//                "TEMPLATE_name_InboundRequestResponse"))) {
//            panels = new WSDLWizardDescriptorPanel[]{
//                        new InboundMessageStepWizardPanel(context),
//                        new OutboundMessageStepWizardPanel(context)
//                    };
//        } else if (templateName.equals(NbBundle.getMessage(
//                FileWSDLWizardExtensionIterator.class, 
//                "TEMPLATE_name_SyncRead"))) {
//            panels = new WSDLWizardDescriptorPanel[]{
//                        new SolicitedReadStepWizardPanel(context)
//                    };
//        }
//        
//        steps = new String[panels.length];
//        int i = 0;
//        for (WSDLWizardDescriptorPanel panel : panels) {
//            steps[i++] = panel.getName();
//        }
//        
    }

    
    /*** BINDING ****/
    @Override
    public boolean commit() {
        boolean status = true;
//        WSDLWizardContext context = getWSDLWizardContext();
//        WSDLModel wsdlModel = context.getWSDLModel();
//        
//        for (WSDLWizardDescriptorPanel panel : panels) {
//            if (panel instanceof InboundMessageStepWizardPanel) {                                
//                status = ((InboundMessageStepWizardPanel)panel).commit();                 
//            } else if (panel instanceof OutboundMessageInStepWizardPanel) {
//                status = ((OutboundMessageInStepWizardPanel)panel).commit();
//            } else if (panel instanceof OutboundMessageStepWizardPanel) {
//                status = ((OutboundMessageStepWizardPanel)panel).commit();
//            } else if (panel instanceof SolicitedReadStepWizardPanel) {
//                status = ((SolicitedReadStepWizardPanel)panel).commit();
//            }
//            
//            if (!status) {
//                return status;
//            }
//        }
        return status;
    }    
}
