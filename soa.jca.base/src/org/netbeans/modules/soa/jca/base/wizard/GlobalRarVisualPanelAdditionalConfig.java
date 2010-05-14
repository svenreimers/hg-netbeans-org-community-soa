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

package org.netbeans.modules.soa.jca.base.wizard;

import java.util.Properties;
import javax.swing.JPanel;
import org.netbeans.modules.soa.jca.base.GlobalRarRegistry;
import org.openide.WizardDescriptor;
import org.openide.explorer.propertysheet.PropertySheet;
import org.openide.nodes.Node;

/**
 * GUI part of wizard panel
 *
 * @author echou
 */
public final class GlobalRarVisualPanelAdditionalConfig extends JPanel {

    private GlobalRarWizardPanelAdditionalConfig wizardPanel;
    private GlobalRarRegistry globalRarRegistry;
    private Properties additionalConfig;

    /** Creates new form GLOBALRARVisualPanel1 */
    public GlobalRarVisualPanelAdditionalConfig(GlobalRarWizardPanelAdditionalConfig wizardPanel) {
        this.wizardPanel = wizardPanel;
        globalRarRegistry = GlobalRarRegistry.getInstance();
        initComponents();
    }

    public void initFromSettings(WizardDescriptor settings) {
        String rarName = (String) settings.getProperty(GlobalRarWizardAction.RAR_NAME_PROP);
        additionalConfig = (Properties) globalRarRegistry.getRar(rarName).getAdditionalConfig().clone();
        if (additionalConfig.size() > 0) {
            Node[] nodes = wizardPanel.getPropertyNodes(additionalConfig);
            PropertySheet ps = new PropertySheet();
            ps.setNodes(nodes);
            jScrollPane1.setViewportView(ps);
        } else {
            jScrollPane1.setViewportView(null);
        }
    }

    public void storeToSettings(WizardDescriptor settings) {
        settings.putProperty(GlobalRarWizardAction.ADDITIONAL_CONFIG_PROP, additionalConfig);
    }

    public boolean isWizardValid() {
        return true;
    }

    public boolean displayMe() {
        if (additionalConfig.size() > 0) {
            return true;
        }
        return false;
    }

    public String getName() {
        return java.util.ResourceBundle.getBundle("org/netbeans/modules/soa/jca/base/wizard/Bundle").getString("Edit_Additional_Configuration");
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 353, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


}

