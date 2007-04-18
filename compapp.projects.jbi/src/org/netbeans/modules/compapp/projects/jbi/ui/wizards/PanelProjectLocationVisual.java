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


package org.netbeans.modules.compapp.projects.jbi.ui.wizards;

import java.io.File;
import java.text.MessageFormat;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.compapp.projects.jbi.ui.FoldersListSettings;

import org.netbeans.spi.project.ui.support.ProjectChooser;

import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public class PanelProjectLocationVisual extends SettingsPanel implements DocumentListener {

    private PanelConfigureProject panel;
    
    /** Creates new form PanelProjectLocationVisual */
    public PanelProjectLocationVisual(PanelConfigureProject panel) {
        initComponents();
        this.panel = panel;
        
        // Register listener on the textFields to make the automatic updates
        projectNameTextField.getDocument().addDocumentListener(this);
        projectLocationTextField.getDocument().addDocumentListener(this);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        projectNameLabel = new javax.swing.JLabel();
        projectNameTextField = new javax.swing.JTextField();
        projectLocationLabel = new javax.swing.JLabel();
        projectLocationTextField = new javax.swing.JTextField();
        Button = new javax.swing.JButton();
        createdFolderLabel = new javax.swing.JLabel();
        createdFolderTextField = new javax.swing.JTextField();

        projectNameLabel.setLabelFor(projectNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectNameLabel, org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "LBL_NWP1_ProjectName_Label"));

        projectNameTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "ACS_LBL_NWP1_ProjectName_A11YDesc"));

        projectLocationLabel.setLabelFor(projectLocationTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectLocationLabel, org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "LBL_NWP1_ProjectLocation_Label"));

        projectLocationTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "ACS_LBL_NPW1_ProjectLocation_A11YDesc"));

        org.openide.awt.Mnemonics.setLocalizedText(Button, org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "LBL_NWP1_BrowseLocation_Button"));
        Button.setActionCommand("BROWSE");
        Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseLocationAction(evt);
            }
        });

        Button.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "ACS_LBL_NWP1_BrowseLocation_A11YDesc"));

        createdFolderLabel.setLabelFor(createdFolderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(createdFolderLabel, org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "LBL_NWP1_CreatedProjectFolder_Lablel"));

        createdFolderTextField.setEditable(false);
        createdFolderTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PanelProjectLocationVisual.class, "ACS_LBL_NWP1_CreatedProjectFolder_A11YDesc"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(projectNameLabel)
                    .add(createdFolderLabel)
                    .add(projectLocationLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(projectNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .add(createdFolderTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, projectLocationTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Button))
        );

        layout.linkSize(new java.awt.Component[] {createdFolderLabel, projectLocationLabel, projectNameLabel}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(projectNameLabel)
                    .add(projectNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(projectLocationLabel)
                    .add(projectLocationTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Button))
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(createdFolderLabel)
                    .add(createdFolderTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseLocationAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseLocationAction
        String command = evt.getActionCommand();
        
        if ("BROWSE".equals(command)) { // NOI18N
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle(NbBundle.getMessage(PanelProjectLocationVisual.class,"LBL_NWP1_SelectProjectLocation")); // NOI18N
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String path = projectLocationTextField.getText();
            if (path.length() > 0) {
                File f = new File(path);
                if (f.exists())
                    chooser.setSelectedFile(f);
            }
            if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
                File projectDir = chooser.getSelectedFile();
                projectLocationTextField.setText(projectDir.getAbsolutePath());
            }            
            panel.fireChangeEvent();
        }
    }//GEN-LAST:event_browseLocationAction
    
    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
        projectNameTextField.requestFocus();
    }
    
    boolean valid(WizardDescriptor wizardDescriptor) {
        if (projectNameTextField.getText().length() == 0) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage", NbBundle.getMessage(PanelProjectLocationVisual.class,"MSG_IllegalProjectName")); // NOI18N
            return false; // Display name not specified
        }
        
        File destFolder = new File(createdFolderTextField.getText());
        File[] children = destFolder.listFiles();
        if (destFolder.exists() && children != null && children.length > 0) {
            // Folder exists and is not empty
            wizardDescriptor.putProperty("WizardPanel_errorMessage", NbBundle.getMessage(PanelProjectLocationVisual.class,"MSG_ProjectFolderExists")); // NOI18N
            return false;
        }
                
        wizardDescriptor.putProperty("WizardPanel_errorMessage", ""); // NOI18N
        return true;
    }
    
    void store(WizardDescriptor d) {        
        String name = projectNameTextField.getText().trim();
        
        d.putProperty(WizardProperties.PROJECT_DIR, new File(createdFolderTextField.getText().trim()));
        d.putProperty(WizardProperties.NAME, name);
        
        File projectsDir = new File(this.projectLocationTextField.getText());
        if (projectsDir.isDirectory()) {
            ProjectChooser.setProjectsFolder (projectsDir);
        }
    }
        
    void read (WizardDescriptor settings) {
        File projectLocation = (File) settings.getProperty(WizardProperties.PROJECT_DIR);
        if (projectLocation == null)
            projectLocation = ProjectChooser.getProjectsFolder();
        else
            projectLocation = projectLocation.getParentFile();
        
        projectLocationTextField.setText(projectLocation.getAbsolutePath());
        
        String projectName = (String) settings.getProperty(WizardProperties.NAME);
        if (projectName == null) {
            int baseCount = FoldersListSettings.getDefault().getNewProjectCount() + 1;
            String formater = NbBundle.getMessage(PanelProjectLocationVisual.class, "LBL_NPW1_DefaultProjectName");
            while ((projectName = validFreeProjectName(projectLocation, formater, baseCount)) == null)
                baseCount++;
        }
        
        projectNameTextField.setText(projectName);                
        projectNameTextField.selectAll();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button;
    private javax.swing.JLabel createdFolderLabel;
    private javax.swing.JTextField createdFolderTextField;
    private javax.swing.JLabel projectLocationLabel;
    private javax.swing.JTextField projectLocationTextField;
    private javax.swing.JLabel projectNameLabel;
    protected javax.swing.JTextField projectNameTextField;
    // End of variables declaration//GEN-END:variables
        
    private static JFileChooser createChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        return chooser;
    }

    private String validFreeProjectName(final File parentFolder, final String formater, final int index) {
        String name = MessageFormat.format(formater, new Object[] {new Integer (index)});                
        File file = new File(parentFolder, name);
        return file.exists() ? null : name;
    }

    // Implementation of DocumentListener --------------------------------------
    public void changedUpdate(DocumentEvent e) {
        updateTexts(e);
    }
    
    public void insertUpdate(DocumentEvent e) {
        updateTexts(e);
    }
    
    public void removeUpdate(DocumentEvent e) {
        updateTexts(e);
    }
    // End if implementation of DocumentListener -------------------------------
    
    
    /** Handles changes in the project name and project directory
     */
    private void updateTexts(DocumentEvent e) {
        createdFolderTextField.setText(getCreatedFolderPath());

        panel.fireChangeEvent(); // Notify that the panel changed
    }
    
    private String getCreatedFolderPath() {
        StringBuffer folder = new StringBuffer(projectLocationTextField.getText().trim());
        if (!projectLocationTextField.getText().endsWith(File.separator))
            folder.append(File.separatorChar);
        folder.append(projectNameTextField.getText().trim());
        
        return folder.toString();
    }
    
}

//TODO implement check for project folder name and location
