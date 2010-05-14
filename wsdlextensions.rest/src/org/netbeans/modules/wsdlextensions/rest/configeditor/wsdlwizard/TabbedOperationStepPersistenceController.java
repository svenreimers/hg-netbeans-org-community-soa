/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved.
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
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */
package org.netbeans.modules.wsdlextensions.rest.configeditor.wsdlwizard;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.modules.wsdlextensions.rest.configeditor.TabbedOperationForm;
import org.netbeans.modules.wsdlextensions.rest.configeditor.TabbedOperationFormWsdlAdapter;
import org.netbeans.modules.wsdlextensions.rest.configeditor.RESTError;
import org.netbeans.modules.wsdlextensions.rest.configeditor.ModelModificationException;
import org.netbeans.modules.xml.wsdl.bindingsupport.spi.ExtensibilityElementConfigurationEditorComponent;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;

/**
 *
 * @author jqian
 */
public class TabbedOperationStepPersistenceController {

    private TabbedOperationForm tabbedOperationForm = null;
    private WSDLModel mWsdlModel = null;
    private static final Logger logger = Logger.getLogger(
            TabbedOperationStepPersistenceController.class.getName());

    public TabbedOperationStepPersistenceController(WSDLModel wsdlModel,
            TabbedOperationForm visualComponent) {
        tabbedOperationForm = visualComponent;
        mWsdlModel = wsdlModel;
    }

    /**
     * Commit all changes
     * @return
     */
    public boolean commit() {
        boolean success = true;
        RESTError restError = tabbedOperationForm.validateMe();
        if (ExtensibilityElementConfigurationEditorComponent.PROPERTY_ERROR_EVT.equals(restError.getErrorMode())) {
            return false;
        }
        tabbedOperationForm.commit();
        TabbedOperationForm.Model model = (TabbedOperationForm.Model) tabbedOperationForm.getModel();
        TabbedOperationFormWsdlAdapter modelAdapter = new TabbedOperationFormWsdlAdapter(mWsdlModel);
        modelAdapter.focus(mWsdlModel);
        try {
            tabbedOperationForm.syncTabbedOperationPanel_ToFrom(modelAdapter, model);
            modelAdapter.commit();
        } catch (ModelModificationException ex) {
            logger.log(Level.SEVERE, "Configuration failed to save to WSDL document", ex);
            success = false;
        }

        return success;
    }
}
