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

package org.netbeans.modules.iep.editor.tcg.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.netbeans.modules.iep.editor.tcg.ps.TcgPsI18n;
import org.openide.util.NbBundle;
//import java.util.logging.Logger;

/**
 * DefaultValidator.java
 * 
 * Created on September 9, 2005, 2:30 PM
 * 
 * @author Bing Lu
 */
public class DefaultValidator implements TcgComponentValidator {
    
    //private static Logger mLogger = Logger.getLogger(DefaultValidator.class.getName());
    /**
     * Creates a new instance of DefaultValidator 
     */
    public DefaultValidator() {
    }
    
    public TcgComponentValidationReport validate(TcgComponent component) {
        List messageList = new ArrayList();
        List childReportList = new ArrayList();
        String type = VALIDATION_OK_KEY;
        for (Iterator it = component.getPropertyList().iterator(); it.hasNext();) {
            TcgProperty property = (TcgProperty) it.next();
            TcgPropertyType propertyType = property.getType();
            if (propertyType.isRequired() && 
                (property.getStringValue() == null || property.getStringValue().equals(""))) 
            {
                messageList.add(new TcgComponentValidationMsg(VALIDATION_ERROR_KEY, "'" + TcgPsI18n.getDisplayName(propertyType) + "' " +
                        NbBundle.getMessage(DefaultValidator.class,"DefaultValidator.property_is_required_but_undefined")));
                type = VALIDATION_ERROR_KEY;
                continue;
            }

            if (propertyType.isRequired() && property.getValue().equals(propertyType.getDefaultValue())) {
                Object defVal = propertyType.getDefaultValue();
                String strVal = propertyType.getType().format(property, defVal);
                messageList.add(
                    new TcgComponentValidationMsg(
                        VALIDATION_WARNING_KEY, 
                        "'" + TcgPsI18n.getDisplayName(propertyType) + "' " + 
                        NbBundle.getMessage(DefaultValidator.class,"DefaultValidator.property_uses_default_value") + " (" + strVal + ")"));
                if (type.equals(VALIDATION_OK_KEY)) {
                    type = VALIDATION_WARNING_KEY;
                }
                continue;
            }
            
            
        }
        for (Iterator it = component.getComponentList().iterator(); it.hasNext();) {
            TcgComponent child = (TcgComponent)it.next();
            TcgComponentValidationReport r = child.validate();
            childReportList.add(r);
            if ((r.getType().equals(VALIDATION_WARNING_KEY) && type.equals(VALIDATION_OK_KEY)) ||
                (r.getType().equals(VALIDATION_ERROR_KEY) && type.equals(VALIDATION_WARNING_KEY)))
            {
               type = r.getType();
            }
        }
        return new TcgComponentValidationReport(component, type, messageList, childReportList);
    }
    
}