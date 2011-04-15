/*
 *  Copyright (c) 2005, 2006 Imola Informatica.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the LGPL License v2.1
 *  which accompanies this distribution, and is available at
 *  http://www.gnu.org/licenses/lgpl.html
 */


package it.imolinfo.jbi4cics.netbeansplugin.cpy2wsdlwizard;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import it.imolinfo.jbi4cics.commareaparser.CommareaLexer;
import it.imolinfo.jbi4cics.commareaparser.CommareaParser;
import it.imolinfo.jbi4cics.connection.jca.cics.CICSInteractionDescription;
import it.imolinfo.jbi4cics.exception.Jbi4cicsException;
import it.imolinfo.jbi4cics.exception.ParseException;
import it.imolinfo.jbi4cics.locator.ServiceLocation;
import it.imolinfo.jbi4cics.locator.SimpleLocation;
import it.imolinfo.jbi4cics.messageformat.commarea.CommareaBeanMappingDescriptor;
import it.imolinfo.jbi4cics.security.J2CAccount;
import it.imolinfo.jbi4cics.webservices.descriptor.ServiceDescriptor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Panel to insert mandatory informations to create WSDL file from a CPY file.
 *
 * @author <a href="mailto:mcimatti@imolinfo.it">Marco Cimatti</a>
 */
final class CreateWSDLPanel extends JPanel {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -5258216272467293434L;

    /**
     * Default value for the operation name field.
     *
     * @see #operationName
     */
    private static final String DEFAULT_OPERATION_NAME = "execute";    // NOI18N

    /**
     * Default value for the code page field.
     *
     * @see #codePage
     */
    private static final String DEFAULT_CODE_PAGE = "CP037";           // NOI18N

    /**
     * The size of text fields, expressed in columns.
     */
    private static final int DEFAULT_TEXT_FIELD_SIZE = 20;

    /**
     * Vertical gap to separate graphic components, in pixel.
     */
    private static final int V_GAP = 3;

    /**
     * Horizontal gap to separate graphic components, in pixel.
     */
    private static final int H_GAP = 5;

    /**
     * The border used to separate graphic components.
     */
    private static final Border MARGIN
            = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    /**
     * The resource bundle to use for internationalization.
     */
    private ResourceBundle bundle = NbBundle.getBundle(getClass());

    /**
     * The service name text field.
     */
    private JTextField serviceName = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The service namespace text field.
     */
    private JTextField serviceNamespace
            = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The package name text field.
     */
    private JTextField packageName = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The interface name text field.
     */
    private JTextField interfaceName = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The operation name text field.
     */
    private JTextField operationName = new JTextField(DEFAULT_OPERATION_NAME,
                                                      DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The output copybook file name.
     */
    private JTextField outputCopybook = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The JNDI connection name text field.
     */
    private JTextField jndiConnectionName
            = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The program name text field.
     */
    private JTextField programName = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The transaction name text field.
     */
    private JTextField transactionName
            = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The code page text field.
     */
    private JTextField codePage = new JTextField(DEFAULT_CODE_PAGE,
                                                 DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The TPN check box.
     */
    private JCheckBox tpn = new JCheckBox();

    /**
     * The username text field.
     */
    private JTextField username = new JTextField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The password text field.
     */
    private JTextField password = new JPasswordField(DEFAULT_TEXT_FIELD_SIZE);

    /**
     * The button to confirm WSDL creation.
     */
    private JButton createWSDL
            = new JButton(bundle.getString("CTL_CreateWSDL"));         // NOI18N

    /**
     * The button to cancel WSDL creation.
     */
    private JButton cancel
            = new JButton(bundle.getString("CTL_Cancel"));             // NOI18N

    /**
     * The listener that verifies panel data to enable or disable the button
     * used to confirm WSDL creation.
     *
     * @see #createWSDL
     */
    private DocumentListener confirmButtonEnabler = new DocumentListener() {
        public void changedUpdate(final DocumentEvent e) {
            checkPanelData();
        }

        public void insertUpdate(final DocumentEvent e) {
            checkPanelData();
        }

        public void removeUpdate(final DocumentEvent e) {
            checkPanelData();
        }
    };

    /**
     * Flag that indicates if it's active the automatic suggestion of interface
     * name field while other fields contained in this panel are changed by the
     * user.
     */
    private boolean suggestInterfaceName = true;

    /**
     * Creates a new panel used to edit additional data necessary to create a
     * WSDL file starting from an CPY file.
     */
    public CreateWSDLPanel() {
        super(new BorderLayout());
        JLabel label
                = new JLabel(bundle.getString("LBL_Instructions"));    // NOI18N
        JPanel p = new JPanel(new BorderLayout());

        label.setBorder(MARGIN);
        add(label, BorderLayout.NORTH);

        p.add(createWSDLServiceRectangle(), BorderLayout.CENTER);
        p.add(createConnectionRectangle(), BorderLayout.SOUTH);
        add(p, BorderLayout.CENTER);

        add(createButtons(), BorderLayout.SOUTH);
    }

    /**
     * Creates the graphic component shown to host WSDL service input fields.
     *
     * @return  the <code>JComponent</code> used to show WSDL service input
     *          fields.
     */
    private JComponent createWSDLServiceRectangle() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        String title = bundle.getString("LBL_WSDLService");            // NOI18N
        JButton browse = new JButton(bundle.getString("CTL_Browse"));  // NOI18N

        panel.setBorder(BorderFactory.createCompoundBorder(
                MARGIN, BorderFactory.createTitledBorder(title)));

        // 1st row
        constraints.insets  = new Insets(V_GAP, H_GAP, V_GAP, H_GAP);
        constraints.anchor  = GridBagConstraints.WEST;
        constraints.fill    = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        panel.add(new JLabel(bundle.getString("LBL_ServiceName")),     // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(serviceName, constraints);
        serviceName.getDocument().addDocumentListener(confirmButtonEnabler);
        serviceName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(final DocumentEvent e) {
                tryToSuggestInterfaceName();
            }

            public void insertUpdate(final DocumentEvent e) {
                tryToSuggestInterfaceName();
            }

            public void removeUpdate(final DocumentEvent e) {
                tryToSuggestInterfaceName();
            }

            private void tryToSuggestInterfaceName() {
                if (suggestInterfaceName) {
                    interfaceName.setText(suggestedInterfaceName());
                }
            }
        });

        // 2nd row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_ServiceNamespace")), //NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(serviceNamespace, constraints);
        serviceNamespace.getDocument().addDocumentListener(
                confirmButtonEnabler);

        // 3rd row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_PackageName")),     // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(packageName, constraints);
        packageName.getDocument().addDocumentListener(confirmButtonEnabler);

        // 4th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_InterfaceName")),   // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(interfaceName, constraints);
        interfaceName.getDocument().addDocumentListener(confirmButtonEnabler);
        interfaceName.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(final KeyEvent e) {

                /*
                 * We need to delay the listener execution, so it will be
                 * invoked AFTER ALL OTHERS KeyListener have terminated their
                 * execution and the JTextField is updated cause key typed
                 */
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        String name = interfaceName.getText().trim();

                        /*
                         * If the user has modified the suggested interface name
                         * by-hand, we terminate the suggest interface name
                         */
                        if (!name.equals(suggestedInterfaceName())) {
                            suggestInterfaceName = false;
                        }
                    }
                });
            }
        });

        // 5th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_OperationName")),   // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(operationName, constraints);
        operationName.getDocument().addDocumentListener(confirmButtonEnabler);

        // 6th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_OutputCopybook")),  // NOI18N
                  constraints);
        panel.add(outputCopybook, constraints);
        outputCopybook.getDocument().addDocumentListener(confirmButtonEnabler);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        browse.setMnemonic(bundle.getString("MNEM_Browse").charAt(0)); // NOI18N
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                chooseOutputCopyCobol();
            }
        });
        panel.add(browse, constraints);

        return panel;
    }

    /**
     * Calculates the suggested interface name from current value of other
     * fields contained in this panel.
     *
     * @return  the interface name suggested to the user, calculated from
     *          current fields value.
     */
    private String suggestedInterfaceName() {
        return serviceName.getText().trim().concat("Interface");       // NOI18N
    }

    /**
     * Creates the graphic component shown to host connection input fields.
     *
     * @return  the <code>JComponent</code> used to show connection input
     *          fields.
     */
    private JComponent createConnectionRectangle() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        String title = bundle.getString("LBL_Connection");             // NOI18N

        panel.setBorder(BorderFactory.createCompoundBorder(
                MARGIN, BorderFactory.createTitledBorder(title)));

        // 1st row
        constraints.insets  = new Insets(V_GAP, H_GAP, V_GAP, H_GAP);
        constraints.anchor  = GridBagConstraints.WEST;
        constraints.fill    = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        panel.add(
                new JLabel(bundle.getString("LBL_JndiConnectionName")), //NOI18N
                constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(jndiConnectionName, constraints);
        jndiConnectionName.getDocument().addDocumentListener(
                confirmButtonEnabler);

        // 2nd row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_ProgramName")),     // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(programName, constraints);
        programName.getDocument().addDocumentListener(confirmButtonEnabler);

        // 3rd row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_TransactionName")), // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(transactionName, constraints);
        transactionName.getDocument().addDocumentListener(confirmButtonEnabler);

        // 4th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_CodePage")),        // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(codePage, constraints);
        transactionName.getDocument().addDocumentListener(confirmButtonEnabler);

        // 5th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_TPN")),             // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(tpn, constraints);

        // 6th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_Username")),        // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(username, constraints);
        username.getDocument().addDocumentListener(confirmButtonEnabler);

        // 7th row
        constraints.gridwidth = 1;
        panel.add(new JLabel(bundle.getString("LBL_Password")),        // NOI18N
                  constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(password, constraints);
        password.getDocument().addDocumentListener(confirmButtonEnabler);

        return panel;
    }

    /**
     * Creates the GUI component containing action buttons.
     *
     * @return  the graphical component hosting action buttons for this panel.
     */
    private JComponent createButtons() {
        JPanel panel
                = new JPanel(new FlowLayout(FlowLayout.RIGHT, H_GAP, V_GAP));

        createWSDL.setEnabled(false);
        createWSDL.setMnemonic(
                bundle.getString("MNEM_CreateWSDL").charAt(0));        // NOI18N
        panel.add(createWSDL);
        cancel.setMnemonic(bundle.getString("MNEM_Cancel").charAt(0)); // NOI18N
        panel.add(cancel);
        return panel;
    }

    /**
     * Validates all data contained in this <code>JPanel</code>, enabling or
     * disabling confirm button. The button is enabled if and only if all data
     * are valid.
     */
    private void checkPanelData() {
        String fileName = outputCopybook.getText().trim();

        createWSDL.setEnabled(
                isValidJavaClassName(serviceName.getText().trim())
                && !isBlank(serviceNamespace)
                && !isBlank(packageName)
                && isValidJavaPackageName(packageName.getText().trim())
                && isValidJavaClassName(interfaceName.getText().trim())
                && isValidJavaIdentifier(operationName.getText().trim())
                && ((fileName.length() == 0) || new File(fileName).exists())
                && !isBlank(jndiConnectionName)
                && !isBlank(programName)
                && !isBlank(transactionName)
                && !isBlank(codePage)
                && !isBlank(username)
                && !isBlank(password));
    }

    /**
     * Checks if the given string is a valid Java identifier.
     *
     * @param   s  the string to check.
     * @return  <code>true</code> if and only if <code>s</code> is a valid
     *          Java identifier, not <code>null</code> and not empty
     *          (<code>""</code>).
     */
    private static boolean isValidJavaIdentifier(final String s) {
        if ((s == null) || (s.length() == 0)) {
            return false;
        }

        if (!Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i = s.length() - 1; i > 0; --i) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given class name is a valid Java class name, eventually
     * containing the package prefix.
     *
     * @param   className  the name to check.
     * @return  <code>true</code> if and only if the given string is a valid
     *          Java class name.
     */
    private static boolean isValidJavaClassName(final String className) {
        return (className != null) && (className.length() > 0)
               && isValidJavaPackageName(className);
    }

    /**
     * Checks if the given class name is a valid Java package name. The default
     * package (<code>""</code>) is evaluated as a valid name. Strings ending
     * with a dot (<code>'.'</code>) are considered illegal.
     *
     * @param   packageName  the name to check.
     * @return  <code>true</code> if and only if the given string is a valid
     *          Java package name.
     */
    private static boolean isValidJavaPackageName(final String packageName) {
        boolean lastCharIsDot = true;

        if (packageName == null) {
            return false;
        }
        if (packageName.length() == 0) {
            return true;
        }
        for (int i = 0, length = packageName.length(); i < length; i++) {
            char ch = packageName.charAt(i);

            if (lastCharIsDot) {
                if (!Character.isJavaIdentifierStart(ch)) {
                    return false;
                }
                lastCharIsDot = false;
            } else {
                if (ch == '.') {
                    lastCharIsDot = true;
                } else if (!Character.isJavaIdentifierPart(ch)) {
                    return false;
                }
            }
        }
        return !lastCharIsDot;
    }

    /**
     * Tests if a text field is blank, containing only spaces or not printable
     * chars.
     *
     * @param   textField  the text field to check. Must be not
     *                     <code>null</code>.
     * @return  <code>true</code> if and only if the text field doesn't contain
     *          any printable character.
     */
    private static boolean isBlank(final JTextField textField) {
        return textField.getText().trim().length() == 0;
    }

    /**
     * Open a <code>javax.swing.JFileChooser</code> to choose the output copy
     * Cobol.
     */
    private void chooseOutputCopyCobol() {
        JFileChooser chooser = new JFileChooser();
        File currentDir = findStartingDirectory();
        int option;

        chooser.setFileFilter(CpyFileFilter.getInstance());
        chooser.setMultiSelectionEnabled(false);
        FileUtil.preventFileChooserSymlinkTraversal(chooser, currentDir);
        // HelpCtx.setHelpIDString(chooser, getHelpCtx().getHelpID());

        option = chooser.showOpenDialog(
                WindowManager.getDefault().getMainWindow());
        if (option == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            try {
                outputCopybook.setText(f.getCanonicalPath());
            } catch (IOException ignored) {

                // OK, ignore the IOException
            }
        }
    }

    /**
     * Try to find a directory to open the file chooser.
     * <p>
     * First the directory of current output copybook is searched, then if there
     * is a file among selected nodes (e.g. open editor windows), use that
     * directory; else just stick to the user's home directory.
     *
     * @return  the target directory as a <code>java.io.File</code>.
     */
    private File findStartingDirectory() {
        File f = new File(outputCopybook.getText().trim());

        if (f.exists()) {
            File dir = f.getParentFile();

            if (dir != null) {
                return dir;
            }
        }

        for (Node node : TopComponent.getRegistry().getActivatedNodes()) {
            DataObject dataObj = node.getCookie(DataObject.class);

            if (dataObj != null) {
                File file = FileUtil.toFile(dataObj.getPrimaryFile());

                if (file != null) {
                    if (file.isFile()) {
                        file = file.getParentFile();
                    }
                    return file;
                }
            }
        }

        // Backup:
        return new File(System.getProperty("user.home"));
    }

    /**
     * Gets the button to confirm WSDL creation.
     *
     * @return  the button to confirm WSDL creation, contained in this panel.
     */
    JButton getCreateWSDLButton() {
        return createWSDL;
    }

    /**
     * Gets the button to cancel WSDL creation.
     *
     * @return  the button to cancel WSDL creation.
     */
    JButton getCancelButton() {
        return cancel;
    }

    /**
     * Gets the output copybook file.
     *
     * @return  the output copybook file or <code>null</code> if there is no
     *          value selected by the user.
     */
    File getOutputCopybook() {
        String fileName = outputCopybook.getText().trim();

        if (fileName.length() == 0) {
            return null;
        }
        return new File(fileName);
    }


    /**
     * Creates the WSDL descriptor from current values contained in this panel.
     * This method must be called when all values inserted by the user are legal
     * and correct.
     *
     * @param   copyCobol  the copy Cobol content.
     * @return  a new <code>ServiceDescriptor</code> describing the WSDL to
     *          generate from current values contained in this panel.
     * @throws  Jbi4cicsException  if an error happens.
     */
    ServiceDescriptor createServiceDescriptor(final String copyCobol)
            throws Jbi4cicsException {
        ServiceDescriptor desc = new ServiceDescriptor();
        J2CAccount account = new J2CAccount();
        SimpleLocation location = new SimpleLocation();
        CICSInteractionDescription intDesc = new CICSInteractionDescription();
        CommareaParser parser;
        String sName = serviceName.getText().trim();
        File outputCopyCobol = getOutputCopybook();

        desc.setServiceName(sName);
        desc.setServiceNameSpace(serviceNamespace.getText().trim());
        desc.setOperationName(operationName.getText().trim());
        desc.setServiceInterfacePackageName(packageName.getText().trim());
        desc.setServiceInterfaceName(interfaceName.getText().trim());
        account.setUsername(username.getText().trim());
        account.setPassword(password.getText().trim());
        desc.setAccount(account);
        location.setConnectionType(ServiceLocation.CICS);
        location.setLocationName(jndiConnectionName.getText().trim());
        desc.setServiceLocation(location);
        intDesc.setProgramName(programName.getText().trim());
        intDesc.setTransactionName(transactionName.getText().trim());
        intDesc.setTpn(tpn.isSelected());
        desc.setInteractionDescription(intDesc);
        desc.setInputBeanClassName(sName + "InputBean");
        desc.setOutputBeanClassName(sName + "OutputBean");
        parser = new CommareaParser(
                new CommareaLexer(new StringReader(copyCobol)));
        try {
            CommareaBeanMappingDescriptor cbmd = parser.commarea_definition();

            // We must set code page <<AFTER>> the CommareaBeanMappingDescriptor
            desc.setInputMappingDescriptor(cbmd);
            if (outputCopyCobol != null) {
                parser = new CommareaParser(
                        new CommareaLexer(new FileReader(outputCopyCobol)));
                cbmd = parser.commarea_definition();
            }
            desc.setOutputMappingDescriptor(cbmd);
            desc.setCodePage(codePage.getText().trim());
            return desc;
        } catch (ParseException e) {
            throw new Jbi4cicsException(e);
        } catch (TokenStreamException e) {
            throw new Jbi4cicsException(e);
        } catch (RecognitionException e) {
            throw new Jbi4cicsException(e);
        } catch (IOException e) {
            throw new Jbi4cicsException(e);
        }
    }
}
