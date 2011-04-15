/*
 *  Copyright (c) 2005, 2006 Imola Informatica.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the LGPL License v2.1
 *  which accompanies this distribution, and is available at
 *  http://www.gnu.org/licenses/lgpl.html
 */


package it.imolinfo.jbi4cics.netbeansplugin.cpy2wsdlwizard;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

/**
 * Helper class to show exception details.
 * <p>
 *
 * @author <a href="mailto:mcimatti@imolinfo.it">Marco Cimatti</a>
 */
final class ExceptionVisualizer extends JPanel
        implements ActionListener, Runnable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -11582395258302695L;

    /**
     * The preferred width of this component.
     */
    private static final int SIZE_PREFERRED_WIDTH = 550;

    /**
     * The preferred height of this component.
     */
    private static final int SIZE_PREFERRED_HEIGHT = 250;

    /**
     * The resource bundle for this class and its instances.
     */
    private static final ResourceBundle BUNDLE
            = NbBundle.getBundle(ExceptionVisualizer.class);

    /**
     * The shown exception.
     */
    private final Throwable throwable;

    /**
     * The dialog descriptor.
     */
    private DialogDescriptor descriptor = new DialogDescriptor("", "");

    /**
     * The dialog used to display the exception.
     */
    private Dialog dialog;

    /**
     * The details button.
     */
    private JButton details = new JButton();

    /**
     * The details area.
     */
    private JTextPane output = new JTextPane() {

        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = -3657622955527997101L;

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return false;
        }
    };

    /**
     * Flag to show or hide exception details.
     */
    private boolean showDetails;

    /**
     * Shows a dialog with the specified exception details.
     *
     * @param  t  the exception to show.
     */
    static void visualizeException(final Throwable t) {
        new ExceptionVisualizer(t);
    }

    /**
     * Creates and shows a new GUI component to display the specified exception.
     *
     * @param  throwable  the exception to show.
     */
    private ExceptionVisualizer(final Throwable throwable) {
        super(new BorderLayout());
        this.throwable = throwable;
        setPreferredSize(
                new Dimension(SIZE_PREFERRED_WIDTH, SIZE_PREFERRED_HEIGHT));

        details.setDefaultCapable(false);

        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN,              // NOI18N
                                output.getFont().getSize() + 1));
        output.setForeground(UIManager.getColor("Label.foreground"));  // NOI18N
        output.setBackground(UIManager.getColor("Label.background"));  // NOI18N

        add(new JScrollPane(output));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        output.getAccessibleContext().setAccessibleName(
                BUNDLE.getString("ACSN_ExceptionStackTrace"));         // NOI18N
        output.getAccessibleContext().setAccessibleDescription(
                BUNDLE.getString("ACSD_ExceptionStackTrace"));         // NOI18N
        getAccessibleContext().setAccessibleDescription(
                BUNDLE.getString("ACSD_NotifyExceptionPanel"));        // NOI18N

        descriptor.setMessageType(DialogDescriptor.ERROR_MESSAGE);
        descriptor.setOptions(new Object[] { DialogDescriptor.OK_OPTION });
        descriptor.setAdditionalOptions(new Object[] { details });
        descriptor.setClosingOptions(new Object[0]);
        descriptor.setButtonListener(this);
        descriptor.setModal(isModalDialogPresent()
                && WindowManager.getDefault().getMainWindow().isVisible());

        dialog = DialogDisplayer.getDefault().createDialog(descriptor);
        dialog.getAccessibleContext().setAccessibleName(
                BUNDLE.getString("ACN_NotifyExcPanel_Dialog"));        // NOI18N
        dialog.getAccessibleContext().setAccessibleDescription(
                BUNDLE.getString("ACD_NotifyExcPanel_Dialog"));        // NOI18N
        update();
        dialog.setVisible(true);
    }

    private static boolean isModalDialogPresent() {
        return hasModalDialog(WindowManager.getDefault().getMainWindow())
                || hasModalDialog(new JDialog().getOwner());
    }

    private static boolean hasModalDialog(final Window w) {
        if (w != null) {
            for (Window win : w.getOwnedWindows()) {
                if (win.isVisible()
                        && (win instanceof Dialog)
                        && ((Dialog) win).isModal()) {
                    return true;
                }

                // Recursion
                if (hasModalDialog(win)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the visual state of the dialog.
     */
    private void update() {
        if (showDetails) {
            Mnemonics.setLocalizedText(details,
                    BUNDLE.getString("CTL_Exception_Hide_Details"));   // NOI18N
            details.getAccessibleContext().setAccessibleDescription(
                    BUNDLE.getString("ACSD_Exception_Hide_Details"));  // NOI18N
        } else {
            Mnemonics.setLocalizedText(details,
                    BUNDLE.getString("CTL_Exception_Show_Details"));   // NOI18N
            details.getAccessibleContext().setAccessibleDescription(
                    BUNDLE.getString("ACSD_Exception_Show_Details"));  // NOI18N
        }

        if (showDetails) {
            descriptor.setMessage(this);
            SwingUtilities.invokeLater(this);
        } else {
            String msg = NbBundle.getMessage(ExceptionVisualizer.class,
                    "MSG_ErrorCreatingWsdl",                           // NOI18N
                    throwable.getLocalizedMessage());

            descriptor.setMessage(msg);
        }
        descriptor.setTitle(BUNDLE.getString("CTL_Title_Exception"));  // NOI18N

        ensurePreferredSize();
    }

    private void ensurePreferredSize() {
        Dimension size = dialog.getSize();
        Dimension pref = dialog.getPreferredSize();

        if (pref.height == 0) {
            pref.height = SIZE_PREFERRED_HEIGHT;
        }
        if (pref.width == 0) {
            pref.width = SIZE_PREFERRED_WIDTH;
        }
        if (!size.equals(pref)) {
            dialog.setSize(pref.width, pref.height);
            dialog.validate();
            dialog.repaint();
        }
    }

    /**
     * Shows the exception stacktrace in the output text area, moving the focus
     * to the text area itself and placing its cursor at start position.
     */
    public void run() {
        if (output.getText().length() == 0) {
            StringWriter wr = new StringWriter();

            throwable.printStackTrace(new PrintWriter(wr, true));
            output.setText(wr.toString());
        }
        output.getCaret().setDot(0);
        output.requestFocus();
    }


    // Handlers


    /**
     * Invoked when an action occurs, updates the state of the dialog, showing
     * or hiding exception details or closing the dialog itself.
     *
     * @param  e  the action event.
     */
    public void actionPerformed(final ActionEvent e) {
        Object source = e.getSource();

        if (source == details) {
            showDetails = !showDetails;
            update();
        } else if ((source == DialogDescriptor.OK_OPTION)
                || (source == DialogDescriptor.CLOSED_OPTION)) {
            dialog.dispose();
        }
    }
}
