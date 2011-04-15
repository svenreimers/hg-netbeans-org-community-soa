/*
 *  Copyright (c) 2005, 2006 Imola Informatica.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the LGPL License v2.1
 *  which accompanies this distribution, and is available at
 *  http://www.gnu.org/licenses/lgpl.html
 */


package it.imolinfo.jbi4cics.netbeansplugin.cpy2wsdlwizard;

import java.io.File;
import java.util.Locale;
import javax.swing.filechooser.FileFilter;
import org.openide.util.NbBundle;

/**
 * File filter to accept copy Cobol files.
 *
 * @author <a href="mailto:mcimatti@imolinfo.it">Marco Cimatti</a>
 */
final class CpyFileFilter extends FileFilter {

    /**
     * The only instance of this class.
     *
     * @see #getInstance()
     */
    private static final CpyFileFilter THE_INSTANCE = new CpyFileFilter();

    /**
     * Initializes a new instance of this class.
     */
    private CpyFileFilter() {}

    /**
     * Retrieves an instance of this class ready to be used.
     *
     * @return  an instance of <code>CpyFileFilter</code> class, ready to use.
     */
    static CpyFileFilter getInstance() {
        return THE_INSTANCE;
    }

    /**
     * Whether the given file is accepted by this filter.
     *
     *@param    f  the file to be accepted or not.
     * @return  <code>true</code> if and only if <code>f</code> name ends with
     *          <i>cpy</i> extension, ignoring case.
     */
    @Override
    public boolean accept(final File f) {
        return f.getName().toUpperCase(Locale.US).endsWith(".CPY");    // NOI18N
    }

    /**
     * Gets the description of this filter.
     *
     * @return  the string <code>"Copybook file"</code>.
     */
    @Override
    public String getDescription() {
        return NbBundle.getMessage(CpyFileFilter.class,
                                   "LBL_Copybook_file");               // NOI18N
    }
}
