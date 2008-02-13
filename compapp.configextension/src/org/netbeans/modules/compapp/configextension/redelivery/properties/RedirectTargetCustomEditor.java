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
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.compapp.configextension.redelivery.properties;

import org.netbeans.modules.compapp.configextension.redelivery.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.xml.namespace.QName;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaEndpoint;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaLink;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaWrapperModel;
import org.netbeans.modules.xml.wsdl.model.Operation;
import org.netbeans.modules.xml.wsdl.model.PortType;
import org.openide.explorer.propertysheet.editors.EnhancedCustomPropertyEditor;

/**
 *
 * @author  jqian
 */
public class RedirectTargetCustomEditor extends javax.swing.JPanel 
    implements EnhancedCustomPropertyEditor {

    private CasaWrapperModel model;
    
    /** Creates new form ErrorEndpointAndOperationCustomEditor */
    public RedirectTargetCustomEditor(CasaWrapperModel model) {
        initComponents();

        this.model = model;

        List<Endpoint> endpoints = new ArrayList<Endpoint>();

        List<CasaEndpoint> casaEndpoints =
                model.getRootComponent().getEndpoints().getEndpoints();

        for (CasaEndpoint casaEndpoint : casaEndpoints) {
            Endpoint endpoint = new Endpoint(
                    casaEndpoint.getServiceQName(),
                    casaEndpoint.getEndpointName());
            endpoints.add(endpoint);
        }

        ComboBoxModel endpointComboBoxModel =
                new DefaultComboBoxModel(endpoints.toArray(new Object[]{}));
        endpointComboBox.setModel(endpointComboBoxModel);
    }
    
    public void setValue(Object value) {
        Object[] values = (Object[]) value;
        Endpoint endpoint = (Endpoint) values[0];
        String operationName = (String) values[1];
        endpointComboBox.setSelectedItem(endpoint);   
        operationComboBox.setSelectedItem(operationName);
        
        endpointChanged(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        endpointLabel = new javax.swing.JLabel();
        endpointComboBox = new javax.swing.JComboBox();
        operationLabel = new javax.swing.JLabel();
        operationComboBox = new javax.swing.JComboBox();

        endpointLabel.setText(org.openide.util.NbBundle.getMessage(RedirectTargetCustomEditor.class, "RedirectTargetCustomEditor.endpointLabel.text")); // NOI18N

        endpointComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                endpointComboBoxItemStateChanged(evt);
            }
        });
        endpointComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endpointChanged(evt);
            }
        });

        operationLabel.setText(org.openide.util.NbBundle.getMessage(RedirectTargetCustomEditor.class, "RedirectTargetCustomEditor.operationLabel.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(endpointLabel)
                    .add(operationLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(operationComboBox, 0, 461, Short.MAX_VALUE)
                    .add(endpointComboBox, 0, 461, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(endpointLabel)
                    .add(endpointComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(operationLabel)
                    .add(operationComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void endpointChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endpointChanged

    Endpoint endpoint = (Endpoint) endpointComboBox.getSelectedItem();

    if (endpoint == null) {
        operationLabel.setEnabled(false);
        operationComboBox.setEnabled(false);
    } else {
        operationLabel.setEnabled(true);
        operationComboBox.setEnabled(true);
        
        String endpointName = endpoint.getEndpointName();
        List<CasaEndpoint> casaEndpoints =
                model.getRootComponent().getEndpoints().getEndpoints();

        QName epInterfaceQName = null;

        for (CasaEndpoint casaEndpoint : casaEndpoints) {
            String epName = casaEndpoint.getEndpointName();
//            QName epServiceQName = casaEndpoint.getServiceQName();
            if (endpointName.equals(epName)) {
                epInterfaceQName = casaEndpoint.getInterfaceQName();
                break;
            }
        }

        if (epInterfaceQName != null) {

            List<String> operationNames = new ArrayList<String>();
            try {
                for (CasaLink link : model.getRootComponent().getPortTypes().getLinks()) {
                    String linkHref = link.getHref();
                    PortType portType =
                            model.getWSDLComponentFromXLinkHref(linkHref, PortType.class);
                    String portTypeName = portType.getName();
                    if (portTypeName.equals(epInterfaceQName.getLocalPart())) {
                        for (Operation operation : portType.getOperations()) {
                            operationNames.add(operation.getName());
                        }
                    }
                }

                ComboBoxModel operationComboBoxModel = 
                    new DefaultComboBoxModel(operationNames.toArray(new Object[]{}));
                operationComboBox.setModel(operationComboBoxModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}//GEN-LAST:event_endpointChanged

private void endpointComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_endpointComboBoxItemStateChanged
    endpointChanged(null);
}//GEN-LAST:event_endpointComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox endpointComboBox;
    private javax.swing.JLabel endpointLabel;
    private javax.swing.JComboBox operationComboBox;
    private javax.swing.JLabel operationLabel;
    // End of variables declaration//GEN-END:variables

    public Object getPropertyValue() throws IllegalStateException {
        Endpoint endpoint = (Endpoint) endpointComboBox.getSelectedItem();
        String operationName = (String) operationComboBox.getSelectedItem();
              
        return new Object[] {endpoint, operationName};
    }
    
    public void validateValue() throws PropertyVetoException {
        Endpoint endpoint = (Endpoint) endpointComboBox.getSelectedItem();
        String operationName = (String) operationComboBox.getSelectedItem();
        
        if (endpoint == null) {
            throw new PropertyVetoException("An error endpoint must be selected.", null);
        }
        
        if (operationName == null) {
            throw new PropertyVetoException("An operation must be selected.", null);
        }
    }
}
