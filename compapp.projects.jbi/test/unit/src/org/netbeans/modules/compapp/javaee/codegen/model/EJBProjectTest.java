package org.netbeans.modules.compapp.javaee.codegen.model;

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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.project.TestUtil;
import org.netbeans.junit.NbTestCase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author gpatil
 */
public class EJBProjectTest extends NbTestCase {

    private File scratchDir = null;
    
    
    private static final String ALL_COMBINATION_EJB_JAR1 = "projects/ejbWebSvcs.jar"; // NOI18N
    private static final String ALL_COMBINATION_SU_EJB_JAR1 = "projects/ejbWebSvcs_jbi.jar"; // NOI18N

    private static final String PORT_NAME_MISSING_EJB_JAR1 = "projects/ejbPortNameMissing.jar"; // NOI18N
    private static final String PORT_NAME_MISSING_SU_EJB_JAR1 = "projects/ejbPortNameMissing_jbi.jar"; // NOI18N
    
    private static final String ALL_DEFAULT_WEB_APP = "projects/webAppDefault.war"; // NOI18N
    private static final String ALL_DEFAULT_SU_WEB_APP = "projects/webAppDefault_jbi.war"; // NOI18N

    private static final Logger logger = Logger.getLogger(EJBProjectTest.class.getName());

    public EJBProjectTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        FileObject scratch = TestUtil.makeScratchDir(this);
        this.scratchDir = FileUtil.toFile(scratch);
    }

    public void tearDown() throws Exception {
        TestUtil.deleteRec(scratchDir);
    }
    
    public void testSUForEJBJarMissingPortName() throws Exception {
        File f = new File(getDataDir().getAbsolutePath(), PORT_NAME_MISSING_EJB_JAR1);
        File eF = new File(getDataDir().getAbsolutePath(), PORT_NAME_MISSING_SU_EJB_JAR1);
        EJBProject ejbProj = new EJBProject(f.getAbsolutePath());
        String ret = ejbProj.createJar(this.scratchDir.getAbsolutePath(), null);
        assertTrue(ret.endsWith("jar")); //NOI18N
        JarFile expected = new JarFile(eF);
        JarFile actual = new JarFile(ret);
        assertTrue("Generated jar with jbi.xml did not macth:", CompAppTestUtil.compareJar(expected, actual));
    }
    
    public void testSUForWebAppAllDefault() throws Exception {
        File f = new File(getDataDir().getAbsolutePath(), ALL_DEFAULT_WEB_APP);
        File eF = new File(getDataDir().getAbsolutePath(), ALL_DEFAULT_SU_WEB_APP);
        WebappProject webProj = new WebappProject(f.getAbsolutePath());
        String ret = webProj.createJar(this.scratchDir.getAbsolutePath(), null);
        assertTrue(ret.endsWith("war")); //NOI18N
        JarFile expected = new JarFile(eF);
        JarFile actual = new JarFile(ret);
        assertTrue("Generated jar with jbi.xml did not macth:", CompAppTestUtil.compareJar(expected, actual));
    }    
    
    public void testSUForEJBJarAllCombination() throws Exception {
        File f = new File(getDataDir().getAbsolutePath(), ALL_COMBINATION_EJB_JAR1);
        File eF = new File(getDataDir().getAbsolutePath(), ALL_COMBINATION_SU_EJB_JAR1);
        EJBProject ejbProj = new EJBProject(f.getAbsolutePath());
        String ret = ejbProj.createJar(this.scratchDir.getAbsolutePath(), null);
        assertTrue(ret.endsWith("jar")); //NOI18N
        JarFile expected = new JarFile(eF);
        JarFile actual = new JarFile(ret);
        assertTrue("Generated jar with jbi.xml did not macth:", CompAppTestUtil.compareJar(expected, actual));
    }
    
}