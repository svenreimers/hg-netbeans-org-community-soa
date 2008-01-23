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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */


package org.netbeans.modules.compapp.projects.jbi.ui.wizards;

import java.io.File;
import java.text.MessageFormat;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openide.WizardDescriptor;

public class PanelProjectAppVisual extends SettingsPanel implements DocumentListener {

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
        org.openide.awt.Mnemonics.setLocalizedText(Label, org.openide.util.NbBundle.getMessage(PanelProjectAppVisual.class, "LBL_NWP1_SpecifyCreateApplication_Label"));

        setAsMainCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(setAsMainCheckBox, org.openide.util.NbBundle.getMessage(PanelProjectAppVisual.class, "LBL_NWP1_AddToApplication_CheckBox"));
        setAsMainCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel1.setLabelFor(jList1);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PanelProjectAppVisual.class, "LBL_NWP1_CreatedProjectFolder_Lablel"));

        org.openide.awt.Mnemonics.setLocalizedText(Button, org.openide.util.NbBundle.getMessage(PanelProjectAppVisual.class, "LBL_NWP1_BrowseLocation_Button"));
        Button.setActionCommand("BROWSE");

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PanelProjectAppVisual.class, "LBL_NWP1_NewProject_Button"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(setAsMainCheckBox)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jList1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton1)
                    .add(Label, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {Button, jButton1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(Label)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(setAsMainCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jList1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Button))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .add(193, 193, 193))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
///        projectNameTextField.requestFocus();
    }

    boolean valid(WizardDescriptor wizardDescriptor) {
///        if (projectNameTextField.getText().length() == 0) {
///            wizardDescriptor.putProperty("WizardPanel_errorMessage", NbBundle.getMessage(PanelProjectLocationVisual.class,"MSG_IllegalProjectName")); // NOI18N
///            return false; // Display name not specified
///        }

///        File destFolder = new File(createdFolderTextField.getText());
///        File[] children = destFolder.listFiles();
///        if (destFolder.exists() && children != null && children.length > 0) {
            // Folder exists and is not empty
///            wizardDescriptor.putProperty("WizardPanel_errorMessage", NbBundle.getMessage(PanelProjectLocationVisual.class,"MSG_ProjectFolderExists")); // NOI18N
///            return false;
///        }

///        wizardDescriptor.putProperty("WizardPanel_errorMessage", ""); // NOI18N
        return true;
    }

    void store(WizardDescriptor d) {
///        String name = projectNameTextField.getText().trim();

///        d.putProperty(WizardProperties.PROJECT_DIR, new File(createdFolderTextField.getText().trim()));
///        d.putProperty(WizardProperties.NAME, name);

///        File projectsDir = new File(this.projectLocationTextField.getText());
///        if (projectsDir.isDirectory()) {
///            ProjectChooser.setProjectsFolder (projectsDir);
///        }
    }

    void read (WizardDescriptor settings) {
///        File projectLocation = (File) settings.getProperty(WizardProperties.PROJECT_DIR);
///        if (projectLocation == null)
///            projectLocation = ProjectChooser.getProjectsFolder();
///        else
///            projectLocation = projectLocation.getParentFile();

///        projectLocationTextField.setText(projectLocation.getAbsolutePath());

///        String projectName = (String) settings.getProperty(WizardProperties.NAME);
///        if (projectName == null) {
///            int baseCount = FoldersListSettings.getDefault().getNewProjectCount() + 1;
///            String formater = NbBundle.getMessage(PanelProjectLocationVisual.class, "LBL_NPW1_DefaultProjectName");
///            while ((projectName = validFreeProjectName(projectLocation, formater, baseCount)) == null)
///                baseCount++;
///            settings.putProperty(NewJbiProjectWizardIterator.PROP_NAME_INDEX, new Integer(baseCount));
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