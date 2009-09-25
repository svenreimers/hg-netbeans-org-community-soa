/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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

package org.netbeans.modules.compapp.test.ui.actions;

import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;

import org.netbeans.modules.compapp.test.ui.TestCaseOutputNode;
import org.netbeans.modules.compapp.test.ui.TestCaseResultNode;
import org.netbeans.modules.compapp.test.ui.TestcaseNode;
import org.netbeans.modules.compapp.test.util.FileNodeUtil;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.DeleteAction;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.Task;
import org.openide.util.RequestProcessor;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 * Action to save the recent result of a test case as the expected output.
 *
 * @author Jun Qian
 */
public class TestCaseSaveRecentResultAsOutputAction extends CookieAction {
    
    protected Class[] cookieClasses() {
        return new Class[] {TestCaseOutputCookie.class};
    }
    
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }
    
    protected boolean enable(Node[] activatedNodes) {
        if (! super.enable(activatedNodes)) {
            return false;
        } else {
            // Also check if there is some result available
            TestCaseOutputCookie testCaseOutputCookie =
                    ((TestCaseOutputCookie) activatedNodes[0].getCookie(TestCaseOutputCookie.class));
            TestCaseOutputNode outputNode =
                    testCaseOutputCookie.getTestCaseOutputNode();
            TestcaseNode testCaseNode = (TestcaseNode) outputNode.getParentNode();
            FileObject fileObject = testCaseNode.getLatestResultFileObject();
            return fileObject != null;
        }
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    protected void performAction(Node[] activatedNodes) {
        if (activatedNodes.length == 1) {
            TestCaseOutputCookie testCaseOutputCookie =
                    ((TestCaseOutputCookie) activatedNodes[0].getCookie(TestCaseOutputCookie.class));
            TestCaseOutputNode outputNode =
                    testCaseOutputCookie.getTestCaseOutputNode();
            
            TestcaseNode testCaseNode = (TestcaseNode) outputNode.getParentNode();
            FileObject resultFO = testCaseNode.getLatestResultFileObject();
            if (resultFO == null) {
                // This should not happen
                return;
            }
            
            try {
                FileNodeUtil.overwriteFile(resultFO, outputNode);
            } catch (java.io.IOException e) {
                e.printStackTrace();
                NotifyDescriptor d = new NotifyDescriptor.Message(e.getMessage(),
                        NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }
        }
    }
    
    
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getName() {
        return NbBundle.getMessage(TestCaseSaveRecentResultAsOutputAction.class,
                "LBL_TestCaseSaveRecentResultAsOutputAction_Name");  // NOI18N
    }
    
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
        
        // If you will provide context help then use:
        // return new HelpCtx(TestCaseResultSaveAsOutputAction.class);
    }
}
