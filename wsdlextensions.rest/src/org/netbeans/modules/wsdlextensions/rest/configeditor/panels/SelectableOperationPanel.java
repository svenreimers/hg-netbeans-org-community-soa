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
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */
package org.netbeans.modules.wsdlextensions.rest.configeditor.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.modules.wsdlextensions.rest.configeditor.ElementOrType;
import org.netbeans.modules.wsdlextensions.rest.configeditor.RESTError;
import org.netbeans.modules.wsdlextensions.rest.configeditor.ValidatableProperties;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;

/**
 *
 * @author jqian
 */
public class SelectableOperationPanel extends javax.swing.JPanel {

    private String templateConst;
    private boolean twoWay;
    private DescriptionPanel descriptionPanel;

    /** Creates new form SelectableOperationPanel */
    public SelectableOperationPanel(String templateConst,
            boolean twoWay,
            DescriptionPanel descriptionPanel) {

        this.templateConst = templateConst;
        this.twoWay = twoWay;
        this.descriptionPanel = descriptionPanel;

        initComponents();

        // propagate property change event
        operationPanel.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                firePropertyChange(evt.getPropertyName(),
                        evt.getOldValue(), evt.getNewValue());
            }
        });
    }

    public boolean isSelected() {
        return checkbox.isSelected();
    }

    public void setSelected(boolean selected) {
        checkbox.setSelected(selected);
    }

    public void addSelectionChangeListener(ChangeListener l) {
        checkbox.addChangeListener(l);
    }

    public void setProject(Project mProject) {
        operationPanel.setProject(mProject);
    }

    public void setWSDLModel(WSDLModel wsdlModel) {
        operationPanel.setWSDLModel(wsdlModel);
    }

    public String getOperationName() {
        return operationPanel.getOperationName();
    }

    public void setOperationName(String operationName) {
        operationPanel.setOperationName(operationName);
    }

    public ValidatableProperties getValidatableProperties() {
        return operationPanel.getValidatableProperties();
    }

    public void setValidatableProperties(ValidatableProperties properties) {
        operationPanel.setValidatableProperties(properties);
    }

    public ElementOrType getRequestEOT() {
        return operationPanel.getRequestEOT();
    }

    public ElementOrType getResponseEOT() {
        return operationPanel.getResponseEOT();
    }

    public RESTError validateMe(boolean fireEvent) {
        return operationPanel.validateMe(fireEvent);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox = new javax.swing.JCheckBox();
        operationPanel = new OperationPanel(templateConst, twoWay, descriptionPanel);

        setLayout(new java.awt.BorderLayout());
        add(checkbox, java.awt.BorderLayout.WEST);
        add(operationPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox;
    private org.netbeans.modules.wsdlextensions.rest.configeditor.panels.OperationPanel operationPanel;
    // End of variables declaration//GEN-END:variables

}
