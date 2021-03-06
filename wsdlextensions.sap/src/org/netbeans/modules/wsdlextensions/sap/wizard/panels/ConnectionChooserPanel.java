/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ServiceConnectionPanel.java
 *
 * Created on Nov 25, 2009, 2:31:15 PM
 */
package org.netbeans.modules.wsdlextensions.sap.wizard.panels;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.netbeans.modules.wsdlextensions.sap.model.SapConnection;
import org.netbeans.modules.xml.wsdl.bindingsupport.spi.ExtensibilityElementConfigurationEditorComponent;

/**
 *
 * @author jqian
 */
public class ConnectionChooserPanel extends javax.swing.JPanel {

    private static final String persistedConnectionsDir = System.getProperty("user.home"); // NOI18N
    private static final String persistedConnectionsFileName = ".sapconnections.ser"; // NOI18N

    /** Creates new form ServiceConnectionPanel */
    public ConnectionChooserPanel() {
        initComponents();
    }

    public SapConnection getConnection() {
        return (SapConnection) _cbConnection.getSelectedItem();
    }

    private void addConnection(SapConnection connection) {
        assert connection != null;

        DefaultComboBoxModel model = (DefaultComboBoxModel) _cbConnection.getModel();
        assert model.getIndexOf(connection) < 0;

        model.addElement(connection);
        _cbConnection.setSelectedItem(connection);

        updatePageAdvanceState();
    }

    private void setConnection(SapConnection connection) {
        assert connection != null;

        DefaultComboBoxModel model = (DefaultComboBoxModel) _cbConnection.getModel();
        int index = _cbConnection.getSelectedIndex();
        model.removeElementAt(index);
        model.insertElementAt(connection, index);
        _cbConnection.setSelectedIndex(index);

        updatePageAdvanceState();
    }

    /**
     * Persists user-configured connections.
     */
    public void persistConnections() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(
                    new File(persistedConnectionsDir, persistedConnectionsFileName));
            oos = new ObjectOutputStream(fos);
            ComboBoxModel model = _cbConnection.getModel();
            for (int i = 0; i < model.getSize(); i++) {
                SapConnection connection = (SapConnection) model.getElementAt(i);
                oos.writeObject(connection);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * Restore user-configured connections.
     */
    public void loadConnections() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        File sapConnectionsFile =
                new File(persistedConnectionsDir, persistedConnectionsFileName);
        if (sapConnectionsFile.exists()) {
            try {
                fis = new FileInputStream(sapConnectionsFile);
                ois = new ObjectInputStream(fis);
                SapConnection connection = null;
                while ((connection = (SapConnection) ois.readObject()) != null) {
                    addConnection(connection);
                }
            } catch (EOFException ex) {
                // ignore
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                    }
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        _lblConnection = new javax.swing.JLabel();
        _cbConnection = new javax.swing.JComboBox();
        _lblSystemID = new javax.swing.JLabel();
        _lblAppServer = new javax.swing.JLabel();
        _lblSystemNumber = new javax.swing.JLabel();
        _lblSystemIDValue = new javax.swing.JLabel();
        _lblAppServerValue = new javax.swing.JLabel();
        _lblSystemNumberValue = new javax.swing.JLabel();
        _btnAdd = new javax.swing.JButton();
        _btnEdit = new javax.swing.JButton();
        _btnBrowse = new javax.swing.JButton();

        jLabel1.setText("<html>A SAP R/3 connection is required to configure this adapter. Select a SAP R/3 connection already defined in your project or create a New Connection.</html>");

        _lblConnection.setText("Connection:");

        _cbConnection.setRenderer(new SapConnectionRenderer());
        _cbConnection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                _cbConnectionItemStateChanged(evt);
            }
        });

        _lblSystemID.setText("System ID:");
        _lblSystemID.setEnabled(false);

        _lblAppServer.setText("Application Server:");
        _lblAppServer.setEnabled(false);

        _lblSystemNumber.setText("System Number:");
        _lblSystemNumber.setEnabled(false);

        _lblSystemIDValue.setText(" ");

        _lblAppServerValue.setText(" ");

        _lblSystemNumberValue.setText(" ");

        _btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/wsdlextensions/sap/wizard/panels/resources/add_16x16.png"))); // NOI18N
        _btnAdd.setBorder(null);
        _btnAdd.setMargin(new java.awt.Insets(2, 2, 2, 2));
        _btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnAddActionPerformed(evt);
            }
        });

        _btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/wsdlextensions/sap/wizard/panels/resources/edit_16x16.png"))); // NOI18N
        _btnEdit.setBorder(null);
        _btnEdit.setBorderPainted(false);
        _btnEdit.setMargin(new java.awt.Insets(2, 2, 2, 2));
        _btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnEditActionPerformed(evt);
            }
        });

        _btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/wsdlextensions/sap/wizard/panels/resources/browse_16x16.png"))); // NOI18N
        _btnBrowse.setBorder(null);
        _btnBrowse.setMargin(new java.awt.Insets(2, 2, 2, 2));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(_lblConnection)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(_cbConnection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 406, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(_btnAdd)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(_btnEdit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(_btnBrowse)))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(_lblSystemNumber)
                        .add(18, 18, 18)
                        .add(_lblSystemNumberValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(_lblAppServer)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(_lblAppServerValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(_lblSystemID)
                        .add(44, 44, 44)
                        .add(_lblSystemIDValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                        .add(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(_lblConnection)
                        .add(_cbConnection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(_btnAdd)
                        .add(_btnEdit)
                        .add(_btnBrowse)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(_lblSystemID)
                    .add(_lblSystemIDValue))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(_lblAppServer)
                    .add(_lblAppServerValue))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(_lblSystemNumber)
                    .add(_lblSystemNumberValue))
                .addContainerGap(163, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showConnectionDialog(SapConnection connection,
            List<String> exsitingConnectionNames,
            final boolean add) {

        final ConnectionConfigPanel connectionPanel = new ConnectionConfigPanel();
        connectionPanel.setConnection(connection);
        connectionPanel.setExistingConnectionNames(exsitingConnectionNames);

        final JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (connectionPanel.validateContent()) {
                    JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(
                            JDialog.class, okButton);
                    dialog.setVisible(false);

                    SapConnection connection = connectionPanel.getConnection();
                    if (add) {
                        addConnection(connection);
                    } else {
                        setConnection(connection);
                    }
                }
            }
        });

        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(
                        JDialog.class, okButton);
                dialog.setVisible(false);
            }
        });

        JOptionPane optionPane = new JOptionPane(connectionPanel,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION, null,
                new Object[]{okButton, cancelButton});

        JDialog dialog = optionPane.createDialog(this,
                add ? "Create SAP R/3 Connection" : "Edit SAP R/3 Connection");
        dialog.setVisible(true);
    }

    private List<String> getExistingConnectionNames(boolean includeSelected) {
        List<String> ret = new ArrayList<String>();
        DefaultComboBoxModel model = (DefaultComboBoxModel) _cbConnection.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            if (i != _cbConnection.getSelectedIndex() || includeSelected) {
                SapConnection connection = (SapConnection) model.getElementAt(i);
                ret.add(connection.getName());
            }
        }

        return ret;
    }

    private void updatePageAdvanceState() {
        if (getConnection() == null) {
            firePropertyChange(ExtensibilityElementConfigurationEditorComponent.PROPERTY_ERROR_EVT, null, "");
        } else {
            firePropertyChange(ExtensibilityElementConfigurationEditorComponent.PROPERTY_CLEAR_MESSAGES_EVT, null, "");
        }
    }

    private void _btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__btnAddActionPerformed
        List<String> existingConnectionNames = getExistingConnectionNames(true);
        showConnectionDialog(SapConnection.getDefault(), existingConnectionNames, true);
    }//GEN-LAST:event__btnAddActionPerformed

    private void _btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__btnEditActionPerformed
        List<String> existingConnectionNames = getExistingConnectionNames(false);
        showConnectionDialog(
                (SapConnection)_cbConnection.getSelectedItem(), existingConnectionNames, false);
    }//GEN-LAST:event__btnEditActionPerformed

    private void _cbConnectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event__cbConnectionItemStateChanged
        boolean connectionSelected = false;
        String systemID = null;
        String appServer = null;
        String systemNumber = null;

        SapConnection selectedConnection = (SapConnection) _cbConnection.getSelectedItem();
        if (selectedConnection != null) {
            connectionSelected = true;
            systemID = selectedConnection.getSystemID();
            appServer = selectedConnection.getApplicationServer();
            systemNumber = selectedConnection.getSystemNumber();
        }

        _lblSystemIDValue.setText(systemID);
        _lblAppServerValue.setText(appServer);
        _lblSystemNumberValue.setText(systemNumber);

        _lblSystemID.setEnabled(connectionSelected);
        _lblAppServer.setEnabled(connectionSelected);
        _lblSystemNumber.setEnabled(connectionSelected);

        _lblSystemIDValue.setEnabled(connectionSelected);
        _lblAppServerValue.setEnabled(connectionSelected);
        _lblSystemNumberValue.setEnabled(connectionSelected);
    }//GEN-LAST:event__cbConnectionItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _btnAdd;
    private javax.swing.JButton _btnBrowse;
    private javax.swing.JButton _btnEdit;
    private javax.swing.JComboBox _cbConnection;
    private javax.swing.JLabel _lblAppServer;
    private javax.swing.JLabel _lblAppServerValue;
    private javax.swing.JLabel _lblConnection;
    private javax.swing.JLabel _lblSystemID;
    private javax.swing.JLabel _lblSystemIDValue;
    private javax.swing.JLabel _lblSystemNumber;
    private javax.swing.JLabel _lblSystemNumberValue;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables


    class SapConnectionRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

            if (value instanceof SapConnection) {
                value = ((SapConnection) value).getName();
            }
            return super.getListCellRendererComponent(list, value,
                    index, isSelected, cellHasFocus);
        }
    }
}
