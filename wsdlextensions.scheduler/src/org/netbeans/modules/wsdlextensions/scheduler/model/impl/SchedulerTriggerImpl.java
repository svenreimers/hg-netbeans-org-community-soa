/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
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
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */

package org.netbeans.modules.wsdlextensions.scheduler.model.impl;

import org.netbeans.modules.wsdlextensions.scheduler.model.SchedulerComponent.Visitor;
import org.netbeans.modules.wsdlextensions.scheduler.model.SchedulerTrigger;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.w3c.dom.Element;

/**
 *
 * @author sunsoabi_edwong
 */
public class SchedulerTriggerImpl extends SchedulerComponentImpl
        implements SchedulerTrigger {
    
    public SchedulerTriggerImpl(WSDLModel wsdlModel) {
        this(wsdlModel, createPrefixedElement(SCHED_TRIGGER_QNAME, wsdlModel));
    }
    
    public SchedulerTriggerImpl(WSDLModel wsdlModel, Element element) {
        super(wsdlModel, element);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return getAttribute(SchedulerAttribute.NAME);
    }

    public void setName(String name) {
        setAttribute(SchedulerAttribute.NAME.getName(),
                SchedulerAttribute.NAME, name);
    }

    public String getType() {
        return getAttribute(SchedulerAttribute.TYPE);
    }

    public void setType(String type) {
        setAttribute(SchedulerAttribute.TYPE.getName(),
                SchedulerAttribute.TYPE, type);
    }

    public boolean isEnabled() {
        return Boolean.parseBoolean(getAttribute(SchedulerAttribute.ENABLED));
    }

    public void setEnabled(boolean enabled) {
        setAttribute(SchedulerAttribute.ENABLED.getName(),
                SchedulerAttribute.ENABLED, Boolean.toString(enabled));
    }

    public String getDescription() {
        return getAttribute(SchedulerAttribute.DESCRIPTION);
    }

    public void setDescription(String description) {
        setAttribute(SchedulerAttribute.DESCRIPTION.getName(),
                SchedulerAttribute.DESCRIPTION, description);
    }

    public String getMessage() {
        return getAttribute(SchedulerAttribute.MESSAGE);
    }

    public void setMessage(String message) {
        setAttribute(SchedulerAttribute.MESSAGE.getName(),
                SchedulerAttribute.MESSAGE, message);
    }
}