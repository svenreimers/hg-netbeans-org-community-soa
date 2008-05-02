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


package org.netbeans.modules.compapp.projects.base.ui.wizards;

import java.io.File;
import java.text.MessageFormat;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openide.WizardDescriptor;

class PanelProjectAppVisual
    extends SettingsPanel
    implements DocumentListener, org.netbeans.modules.compapp.projects.base.IcanproConstants {

    private PanelConfigureProjectApp panel;

    /** Creates new form PanelProjectLocationVisual */
    public PanelProjectAppVisual(PanelConfigureProjectApp panel) {
        initComponents();
        this.panel = panel;

        // Register listener on the textFields to make the automatic updates
///        projectNameTextField.getDocument().addDocumentListener(this);
///        projectLocationTextField.getDocument().addDocumentListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        Label = new javax.swing.JLabel();
        setAsMainCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        Button = new javax.swing.JButton();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        Label.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(PanelProjectAppVisual.class, "LBL_NWP1_ProjectName_LabelMnemonic").charAt(0));
        org.openide.awt.Mnemonics.setLocalizedText(Label, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/projects/base/ui/wizards/Bundle").getString("LBL_NWP1_SpecifyCreateApplication_Label"));

        setAsMainCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(setAsMainCheckBox, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/projects/base/ui/wizards/Bundle").getString("LBL_NWP1_AddToApplication_CheckBox"));
        setAsMainCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel1.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/projects/base/ui/wizards/Bundle").getString("LBL_NWP1_CreatedProjectFolder_LablelMnemonic").charAt(0));
        jLabel1.setLabelFor(jList1);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/projects/base/ui/wizards/Bundle").getString("LBL_NWP1_CreatedProjectFolder_Lablel"));

        org.openide.awt.Mnemonics.setLocalizedText(Button, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/projects/base/ui/wizards/Bundle").getString("LBL_NWP1_BrowseLocation_Button"));
        Button.setActionCommand("BROWSE");

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, java.util.ResourceBundle.getBundle("org/netbeans/modules/compapp/projects/base/ui/wizards/Bundle").getString("LBL_NWP1_NewProject_Button"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, setAsMainCheckBox)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jList1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton1))
                        .addContainerGap())
                    .add(Label, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(new java.awt.Component[] {Button, jButton1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(Label)
                .add(11, 11, 11)
                .add(setAsMainCheckBox)
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(Button)
                        .add(11, 11, 11)
                        .add(jButton1))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jList1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
///        projectNameTextField.requestFocus();
    }

    public boolean valid(WizardDescriptor wizardDescriptor) {
///        if (projectNameTextField.getText().length() == 0) {
///            wizardDescriptor.putProperty("WizardPanel_errorMessage", NbBundle.getBundle(WIZARD_BUNDLE).getString("MSG_IllegalProjectName")); //NOI18N
///            return false; // Display name not specified
///        }

///        File destFolder = new File(createdFolderTextField.getText());
///        File[] children = destFolder.listFiles();
///        if (destFolder.exists() && children != null && children.length > 0) {
            // Folder exists and is not empty
///            wizardDescriptor.putProperty("WizardPanel_errorMessage", NbBundle.getBundle(WIZARD_BUNDLE).getString("MSG_ProjectFolderExists")); //NOI18N
///            return false;
///        }

///        wizardDescriptor.putProperty("WizardPanel_errorMessage", ""); //NOI18N
        return true;
    }

    public void store(WizardDescriptor d) {
///        String name = projectNameTextField.getText().trim();

///        d.putProperty(WizardProperties.PROJECT_DIR, new File(createdFolderTextField.getText().trim()));
///        d.putProperty(WizardProperties.NAME, name);

///        File projectsDir = new File(this.projectLocationTextField.getText());
///        if (projectsDir.isDirectory()) {
///            ProjectChooser.setProjectsFolder (projectsDir);
///        }
    }

    public void read (WizardDescriptor settings) {
///        File projectLocation = (File) settings.getProperty(WizardProperties.PROJECT_DIR);
///        if (projectLocation == null)
///            projectLocation = ProjectChooser.getProjectsFolder();
///        else
///            projectLocation = projectLocation.getParentFile();

///        projectLocationTextField.setText(projectLocation.getAbsolutePath());

///        String projectName = (String) settings.getProperty(WizardProperties.NAME);
///        if (projectName == null) {
///            int baseCount = FoldersListSettings.getDefault().getNewProjectCount() + 1;
///            String formater = NbBundle.getBundle(WIZARD_BUNDLE).getString("LBL_NPW1_DefaultProjectName");
///            while ((projectName = validFreeProjectName(projectLocation, formater, baseCount)) == null)
///                baseCount++;
///            settings.putProperty(NewIcanproProjectWizardIterator.PROP_NAME_INDEX, new Integer(baseCount));
///        }

///        projectNameTextField.setText(projectName);                
///        projectNameTextField.selectAll();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button;
    private javax.swing.JLabel Label;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JCheckBox setAsMainCheckBox;
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
///        createdFolderTextField.setText(getCreatedFolderPath());

///        panel.fireChangeEvent(); // Notify that the panel changed
    }

///    private String getCreatedFolderPath() {
///        StringBuffer folder = new StringBuffer(projectLocationTextField.getText().trim());
///        if (!projectLocationTextField.getText().endsWith(File.separator))
///            folder.append(File.separatorChar);
///        folder.append(projectNameTextField.getText().trim());

///        return folder.toString();
///    }

}

//TODO implement check for project folder name and location
