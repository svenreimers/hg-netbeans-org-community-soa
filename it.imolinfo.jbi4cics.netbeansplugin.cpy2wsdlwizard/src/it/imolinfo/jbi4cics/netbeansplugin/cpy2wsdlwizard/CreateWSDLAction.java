/*
 *  Copyright (c) 2005, 2006 Imola Informatica.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the LGPL License v2.1
 *  which accompanies this distribution, and is available at
 *  http://www.gnu.org/licenses/lgpl.html
 */


package it.imolinfo.jbi4cics.netbeansplugin.cpy2wsdlwizard;

import it.imolinfo.jbi4cics.exception.Jbi4cicsException;
import it.imolinfo.jbi4cics.webservices.descriptor.ServiceDescriptor;
import it.imolinfo.jbi4cics.webservices.runtime.ServiceCreator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JDialog;
import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

/**
 * Action to create WSDL file from a CPY file.
 *
 * @author <a href="mailto:mcimatti@imolinfo.it">Marco Cimatti</a>
 */
public final class CreateWSDLAction extends CookieAction {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -7170499092554718593L;

    /**
     * The platform dependent line separator string.
     */
    private static final String LINE_SEPARATOR
            = System.getProperty("line.separator");                 // NOI18N

    /**
     * Creates a new instance of this class.
     */
    public CreateWSDLAction() {
    }

    /**
     * Performs the action based on the currently activated nodes. Note that if
     * the source of the event triggering this action was itself a node, that
     * node will be the sole argument to this method, rather than the activated
     * nodes.
     *
     * @param  activatedNodes  current activated nodes, may be empty but not
     *                         <code>null</code>.
     */
    protected void performAction(final Node[] activatedNodes) {
        final CPYDataObject c = activatedNodes[0].getCookie(CPYDataObject.class);
        final CreateWSDLPanel panel = new CreateWSDLPanel();
        String title = NbBundle.getMessage(CreateWSDLAction.class,
                                           "LBL_CreateWSDLWindow"); // NOI18N
        Object[] options = new Object[] { panel.getCreateWSDLButton(),
                                          panel.getCancelButton() };
        DialogDescriptor descriptor = new DialogDescriptor(panel, title, true,
                options, null, DialogDescriptor.DEFAULT_ALIGN, null,
                new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        doActionPerformed(e, panel, c.getPrimaryFile());
                    }
                });
        DialogDisplayer displayer = DialogDisplayer.getDefault();
        JDialog dialog = (JDialog) displayer.createDialog(descriptor);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * Get the mode of the action: how strict it should be about cookie support.
     *
     * @return  the mode of the action. Possible values are disjunctions of the
     *          <code>MODE_XXX</code> constants.
     */
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    /**
     * Get a human presentable name of the action. This may be presented as an
     * item in a menu.
     * <p>
     * Using the normal menu presenters, an included ampersand before a letter
     * will be treated as the name of a mnemonic.
     *
     * @return  the name of the action.
     */
    public String getName() {
        return NbBundle.getMessage(CreateWSDLAction.class,
                                   "CTL_CreateWSDLAction");         // NOI18N
    }

    /**
     * Get the cookies that this action requires. The cookies are disjunctive,
     * i.e. a node must support AT LEAST ONE of the cookies specified by this
     * method.
     *
     * @return  a list of cookies.
     */
    @SuppressWarnings("unchecked")
    protected Class[] cookieClasses() {
        return new Class[] { CPYDataObject.class };
    }

    /**
     * Specify the proper resource name for the action's icon. Typically this
     * should be a 16x16 color GIF. Do not use relative paths nor an initial
     * slash. If e.g. myIcon.gif is accompanied with myIcon_pressed.gif,
     * myIcon_disabled.gif and/or myIcon_rollover.gif these images are used to
     * call methods on <code>JButton.setPressedIcon()</code>,
     * <code>JButton.setDisabledIcon()</code> and/or
     * <code>JButton.setRolloverIcon()</code> with appropriate images. If you do
     * not want an icon, do not override this to return a blank icon. Leave it
     * <code>null</code>, but call <code>putValue("noIconInMenu",
     * Boolean.TRUE)</code> to make sure that no extra space is allotted for an
     * icon in the menu item.
     *
     * @return  the resource name for the icon, e.g.
     *          <code>com/mycom/mymodule/myIcon.gif</code>, or <code>null</code>
     *          to have no icon (make a text label).
     */
    @Override
    protected String iconResource() {
        return "it/imolinfo/jbi4cics/netbeansplugin/cpy2wsdlwizard/"
               + "cpy16x16.png";                                    // NOI18N
    }

    /**
     * Get a help context for the action.
     *
     * @return  the help context for this action.
     */
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    /**
     * If <code>true</code>, this action should be performed asynchronously in a
     * private thread. If <code>false</code>, it will be performed synchronously
     * as called in the event thread.
     *
     * @return  <code>false</code>.
     */
    @Override
    protected boolean asynchronous() {
        return false;
    }

    /**
     * Handles an action performed from the <code>CreateWSDLPanel</code>. The
     * action may be the WSDL file creation confirmation or another action that
     * implies the dialog window closing.
     * <p>
     * If the user has confirmed WSDL file generation, then an attempt to write
     * a new file is made, closing the dialog if all has gone OK. If a file with
     * the target name also exists or an error happens, then a warning is shown
     * and the dialog keeps open, giving to user another opportunity to confirm
     * the WSDL file creation or to cancel the operation.
     *
     * @param  event    the action event occurred.
     * @param  panel    the source panel.
     * @param  fileObj  the source copy Cobol file from which to create a
     *                  corresponding WSDL file.
     */
    private void doActionPerformed(final ActionEvent event,
            final CreateWSDLPanel panel, final FileObject fileObj) {
        if (event.getSource() == panel.getCreateWSDLButton()) {
            File cpyFile = FileUtil.toFile(fileObj);
            Thread thread = Thread.currentThread();
            ClassLoader origLoader = thread.getContextClassLoader();

            try {
                String copyCobol = readFileAsString(cpyFile);
                ServiceDescriptor desc;

                /*
                 * FIXME Patch to use CPY2WSDL and IDL2WSDL plugins together:
                 *       changes the current thread context ClassLoader, because
                 *       NetBeans sets it as the "system class loader", able to
                 *       load classes from any enabled module, as well as the
                 *       JDK and all startup libraries.
                 *    PROBLEM:
                 *       this patch prevents also ClassCastException caused by
                 *       ANTLR library, cause many versions of this jar are on
                 *       present on NetBeans, but causes the Corba plugin to
                 *       launch a NoClassDefFoundError if activated after this
                 *       plugin
                 */
                thread.setContextClassLoader(
                        ServiceCreator.class.getClassLoader());

                desc = panel.createServiceDescriptor(copyCobol);
                if (createWSDLfromCPYFile(cpyFile, copyCobol,
                                          panel.getOutputCopybook(), desc)) {
                    closePanelJDialog(panel);
                }
            } catch (IOException e) {
                ExceptionVisualizer.visualizeException(e);
            } catch (WSDLException e) {
                ExceptionVisualizer.visualizeException(e);
            } catch (Jbi4cicsException e) {
                ExceptionVisualizer.visualizeException(e);
            } finally {
                thread.setContextClassLoader(origLoader);
            }
        } else {
            closePanelJDialog(panel);
        }
    }

    /**
     * Closes the <code>javax.swing.JDialog</code> that contains the specified
     * <code>CreateWSDLPanel</code>.
     *
     * @param  panel  the panel contained inside the dialog to close.
     */
    private static void closePanelJDialog(final CreateWSDLPanel panel) {
        ((JDialog) panel.getTopLevelAncestor()).dispose();
    }

    /**
     * Tries to create the WSDL file from specified copy Cobol.
     *
     * @param   cpy                the copy Cobol file.
     * @param   copyCobol          the copy Cobol. It must represent the content
     *                             of <code>cpy</code>.
     * @param   outputCpy          the optional output copy Cobol file. Must be
     *                             <code>null</code> to indicate that
     *                             <code>cpy</code> is used for both input and
     *                             output.
     * @param   desc               the WSDL service descriptor.
     * @return  <code>true</code> if and only there was no file with the WSDL
     *          service name and a new file so called has been correctly
     *          created; <code>false</code> if a file with the service name is
     *          also present, avoiding the WSDL file creation.
     */
    private boolean createWSDLfromCPYFile(final File cpy,
            final String copyCobol, final File outputCpy,
            final ServiceDescriptor desc)
            throws IOException, Jbi4cicsException, WSDLException {
        String wsdlFileName = desc.getServiceName() + ".wsdl";
        File wsdl
                = new File(cpy.getParent() + File.separatorChar + wsdlFileName);
        String outputCopyCobol = null;
        Definition def;

        if (wsdl.exists()) {
            String msg = NbBundle.getMessage(CreateWSDLAction.class,
                    "MSG_FileAlreadyExists", wsdlFileName);         // NOI18N
            NotifyDescriptor nd = new NotifyDescriptor.Message(
                                    msg, NotifyDescriptor.ERROR_MESSAGE);

            DialogDisplayer.getDefault().notify(nd);
            return false;
        }

        if (outputCpy != null) {
            outputCopyCobol = readFileAsString(outputCpy);
        }
        def = new ServiceCreator().createWsdlFromCopyCobol(
                copyCobol, outputCopyCobol, desc);
        WSDLFactory.newInstance().newWSDLWriter().writeWSDL(def,
                new FileWriter(wsdl));

        return true;
    }

    /**
     * Reads file content as a string.
     *
     * @param   f  the file to read.
     * @return  the string representing
     * @throws  IOException  if the file <code>f</code> does not exist, is a
     *                       directory rather than a regular file, cannot be
     *                       opened for reading or an I/O error occurs.
     */
    private static String readFileAsString(final File f) throws IOException {
        BufferedReader file = null;
        StringBuilder buf = new StringBuilder();

        try {
            file = new BufferedReader(new FileReader(f));
            for (String s = file.readLine(); s != null; s = file.readLine()) {
                buf.append(s).append(LINE_SEPARATOR);
            }
        } finally {
            if (file != null) {
                file.close();
            }
        }
        return buf.toString();
    }
}
