/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
 * License. When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
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
package org.netbeans.modules.xslt.project.wizard.element;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.Collection;

import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.JTextComponent;
import javax.xml.namespace.QName;

import org.openide.WizardDescriptor;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.FileObject;

import org.netbeans.api.project.Project;
import org.netbeans.modules.xml.xam.dom.NamedComponentReference;
import org.netbeans.modules.xml.wsdl.model.Message;
import org.netbeans.modules.xml.wsdl.model.Operation;
import org.netbeans.modules.xml.wsdl.model.OperationParameter;
import org.netbeans.modules.xml.wsdl.model.Part;
import org.netbeans.modules.xml.wsdl.model.PortType;
import org.netbeans.modules.xml.xam.NamedReferenceable;
import org.netbeans.modules.xslt.project.XsltproConstants;
import org.netbeans.modules.xslt.tmap.ui.editors.FileDialog;
import org.netbeans.modules.xml.reference.ReferenceFile;
import org.netbeans.modules.xml.reference.ReferenceUtil;
import static org.netbeans.modules.xml.misc.UI.*;

/**
 * @author Vitaly Bychkov
 */
public abstract class WizardSettingsPanel {

    protected WizardSettingsPanel(Project project) {
        myProject = project;
        myFolder = ReferenceUtil.getSrcFolder(project);
    }

    protected final Project getProject() {
        return myProject;
    }

    protected final FileObject getFolder() {
        return myFolder;
    }

    protected abstract void createPanel(JPanel panel, GridBagConstraints c);

    protected void setEnabled(boolean enabled) {
    }

    protected void update() {
    }

    protected Object getResult() {
        return null;
    }

    protected String getError() {
        return null;
    }

    protected final String getError(String error1, String error2) {
        if (error1 != null) {
            return error1;
        }
        return error2;
    }

    protected String isUniqueTransformName(List<TransformationItem> dataModel,
            String name, int ownIndex) {
        assert name != null;

        if (dataModel != null) {
            TransformationItem item = null;
            for (int i = 0; i < dataModel.size(); i++) {
                if (i == ownIndex) { // skip own index
                    continue;
                }
                item = dataModel.get(i);
                if (item == null) {
                    continue;
                }
                if (name.equals(item.getName())) {
                    return i18n("ERR_NonUniqueTransformName", name); // NOI18N
                }
            }
        }
        return null;
    }

    protected String isUniqueTransformName(List<TransformationItem> dataModel,
            String name) {
        return isUniqueTransformName(dataModel, name, -1);
    }

    public void storeSettings(WizardDescriptor object) {
    }

    public void readSettings(WizardDescriptor object) {
        myWizardDescriptor = (WizardDescriptor) object;
    }

    public WizardDescriptor getWizardDescriptor() {
        return myWizardDescriptor;
    }

    protected final int getXslFileNumber(int start) {
        return getXslFileNumber(myFolder, start);
    }

    public static final int getXslFileNumber(FileObject folder, int start) {
        assert folder != null;
        int count = start;

        while (true) {
            if (folder.getFileObject(NAME + count, EXT) == null) {
                return count;
            }
            count++;
        }
    }

    protected final String getXslFileName(int number) {
        return NAME + number;
    }

    protected final String getType(OperationParameter parameter) {
        if (parameter == null) {
//out("1");
            return EMPTY;
        }
        NamedComponentReference<Message> reference = parameter.getMessage();

        if (reference == null) {
//out("2");
            return EMPTY;
        }
        Message message = reference.get();

        if (message == null) {
//out("3");
            return EMPTY;
        }
        Collection<Part> parts = message.getParts();

        if (parts == null) {
//out("4");
            return EMPTY;
        }
        java.util.Iterator<Part> iterator = parts.iterator();

        if (!iterator.hasNext()) {
//out("5");
            return EMPTY;
        }
        return getType(iterator.next());
    }

    private String getType(Part part) {
        NamedComponentReference<? extends NamedReferenceable> refTypeEl = part.
                getType();
        refTypeEl = refTypeEl == null ? part.getElement() : refTypeEl;

        if (refTypeEl != null) {

            QName qName = refTypeEl.getQName();
            if (qName != null) {
                return qName.getLocalPart();
            }
        }
        return EMPTY;
    }

    protected final String addExtension(String file) {
        file = File.separator.equals("\\")
                ? file.replace(File.separatorChar, '/') : file;

        if (file.endsWith(DOT + EXT)) {
            return file;
        }
        return file + DOT + EXT;
    }

    // -------------------------------------------------------
    protected class Renderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean hasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected,
                    hasFocus);

            if (value instanceof ReferenceFile) {
                setText(((ReferenceFile) value).getName());
            }
            if (value instanceof Operation) {
                setText(((Operation) value).getName());
            }
            if (value instanceof PortType) {
                setText(((PortType) value).getName());
            }
            return this;
        }
    }

    //-----------------------------------
    protected final JButton createBrowseButton(JTextField fileTextField) {
        return createBrowseButton(fileTextField, BROWSE_LABEL);
    }

    protected final JButton createBrowseButton(final JTextField fileTextField,
            String labelKey) {
        final JPanel panel = myComponent;

        JButton browseButton = createButton(
                new ButtonAction(
                i18n(labelKey), // NOI18N
                i18n("TLT_Browse")) { // NOI18N

                    public void actionPerformed(ActionEvent event) {

                        FileFilter xsltFileFilter = new FileFilter() {

                            public boolean accept(File f) {
                                if (f.isDirectory()) {
                                    return true;
                                }
                                String extension = FileUtil.getExtension(f.
                                        getName());
                                if (XsltproConstants.XSLT_EXTENSION.equals(
                                        extension) ||
                                        XsltproConstants.XSLT_EXTENSION2.equals(
                                        extension)) {
                                    return true;
                                }
                                return false;
                            }

                            public String getDescription() {
                                return i18n("LBL_Transformation_Filter_Descr"); // NOI18N
                            }
                        };

                        JFileChooser fileChooser = new JFileChooser(FileUtil.
                                toFile(getFolder()),
                                new FileDialog(getProject()));
                        fileChooser.setFileFilter(xsltFileFilter);

                        int result = fileChooser.showOpenDialog(panel);
                        File selectedFile = fileChooser.getSelectedFile();

                        if (result == JFileChooser.APPROVE_OPTION &&
                                selectedFile != null) {
                            String relPath = "";
                            selectedFile = FileUtil.normalizeFile(selectedFile);

                            if (!selectedFile.exists()) {
                                relPath = FileUtil.getRelativePath(getFolder(),
                                        FileUtil.toFileObject(selectedFile.
                                        getParentFile()));
                                relPath = (relPath == null || "".equals(relPath)
                                        ? "" : relPath + "/") + selectedFile.
                                        getName();
                            } else {
                                relPath = FileUtil.getRelativePath(getFolder(),
                                        FileUtil.toFileObject(selectedFile));
                            }
                            fileTextField.setText(relPath);
                        }
                    }
                });
        return browseButton;
    }

    protected final String i18n(String key) {
        return org.netbeans.modules.xml.misc.UI.i18n(WizardSettingsPanel.class,
                key);
    }

    protected final String i18n(String key, String param) {
        return org.netbeans.modules.xml.misc.UI.i18n(WizardSettingsPanel.class,
                key, param);
    }

    protected final String i18n(String key, String param1, String param2) {
        return org.netbeans.modules.xml.misc.UI.i18n(
                WizardSettingsPanel.class, key, param1, param2);
    }

    public static JTextArea createTextArea(int columns, String message, boolean isFocusable) {
        JTextArea text = org.netbeans.modules.xml.misc.UI.createTextArea(columns, message);
        text.setFocusable(isFocusable);
        return text;
    }
    
    public static class CheckSelectAll implements FocusListener {

        private JTextComponent mTextComponent;

        public CheckSelectAll(JTextComponent textComponent) {
            mTextComponent = textComponent;
        }

        public void focusGained(FocusEvent e) {
            if (mTextComponent != null) {
                mTextComponent.selectAll();
            }
        }

        public void focusLost(FocusEvent e) {
        }
    }
    private Project myProject;
    private JPanel myComponent;
    private FileObject myFolder;
    private WizardDescriptor myWizardDescriptor;
    private static final String DOT = "."; // NOI18N
    private static final String EXT = "xsl"; // NOI18N
    private static final String NAME = "newXSLFile"; // NOI18N
    private static final String BROWSE_LABEL = "LBL_Browse"; // NOI18N
    private static final String BROWSE_LABEL2 = "LBL_Browse2"; // NOI18N
    protected static final String EMPTY = ""; // NOI18N
    protected static final String NAME_TYPE = org.netbeans.modules.xml.misc.UI.
            i18n(Panel.class, "LBL_Service_Type"); // NOI18N
    protected static final String NAME_WSDL = org.netbeans.modules.xml.misc.UI.
            i18n(Panel.class, "LBL_WSDL_File"); // NOI18N
    protected static final String NAME_XSLT = org.netbeans.modules.xml.misc.UI.
            i18n(Panel.class, "LBL_XSLT_Configuration"); // NOI18N
    public static final String INPUT_TRANSFORMATIONS =
            "input.transformation.model"; // NOI18N
    public static final String IMPL_OPERATION = "implemented.operation"; // NOI18N
    public static final String OUTPUT_TRANSFORMATIONS =
            "output.transformation.model"; // NOI18N
    public static final String CALLED_OPERATION = "called.operation"; // NOI18N
    public static final String XSLT_SERVICE_NAME = "xslt.service.name"; // NOI18N
    public static final String CHOICE = "choice"; // NOI18N
    public static final String CHOICE_REQUEST_REPLY = "choice.request.reply"; // NOI18N
    public static final String CHOICE_FILTER_ONE_WAY = "choice.filter.one.way"; // NOI18N
    public static final String CHOICE_FILTER_REQUEST_REPLY =
            "choice.filter.request.reply"; // NOI18N
}
