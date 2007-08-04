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

package org.netbeans.modules.compapp.casaeditor.nodes.actions;

import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.netbeans.modules.compapp.casaeditor.model.casa.CasaEndpointRef;
import org.openide.util.NbBundle;

/**
 * 
 * A panel for selecting an endpoint from a list.
 *
 * @author  jqian
 */
public class EndpointSelectionPanel extends javax.swing.JPanel {
             
    private List<CasaEndpointRef> endpoints;
    
    /** Creates new form EndpointSelectionPanel */
    public EndpointSelectionPanel(final List<CasaEndpointRef> endpoints) {
        
        this.endpoints = endpoints;
        
        initComponents();
        
        TableModel tableModel = new AbstractTableModel() {
            public int getRowCount() {
                return endpoints.size();
            }
            public int getColumnCount() {
                return 2;
            }
            public String getColumnName(int columnIndex) {
                if (columnIndex == 0) {
                    return NbBundle.getMessage(EndpointSelectionPanel.class, 
                            "TITLE_EndpointName"); // NOI18N
                } else {
                    return NbBundle.getMessage(EndpointSelectionPanel.class, 
                            "TITLE_ServiceQName"); // NOI18N
                }
            }
            public Object getValueAt(int rowIndex, int columnIndex) {
                CasaEndpointRef endpoint = endpoints.get(rowIndex);
                if (columnIndex == 0) {
                    return endpoint.getEndpointName();
                } else {
                    return endpoint.getServiceQName();
                }
            }            
        };
        endpointTable.setModel(tableModel);
        endpointTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        connectableEndpointsLabel.setLabelFor(endpointTable);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectableEndpointsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        endpointTable = new javax.swing.JTable();

        connectableEndpointsLabel.setFont(new java.awt.Font("Dialog", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(connectableEndpointsLabel, org.openide.util.NbBundle.getMessage(EndpointSelectionPanel.class, "LBL_AvailableEndpoints")); // NOI18N

        endpointTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Endpoint Name", "Service QName"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(endpointTable);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .add(connectableEndpointsLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(connectableEndpointsLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        connectableEndpointsLabel.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EndpointSelectionPanel.class, "ACS_Endpoint_Selection_Panel")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    public CasaEndpointRef getSelectedItem() {
        int row = endpointTable.getSelectedRow();
        return row == -1 ? null : endpoints.get(row);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel connectableEndpointsLabel;
    private javax.swing.JTable endpointTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
