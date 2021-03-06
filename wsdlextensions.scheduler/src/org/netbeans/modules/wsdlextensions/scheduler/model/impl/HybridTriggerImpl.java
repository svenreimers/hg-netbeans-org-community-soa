/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
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
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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

package org.netbeans.modules.wsdlextensions.scheduler.model.impl;

import org.netbeans.modules.wsdlextensions.scheduler.model.HybridTrigger;
import org.netbeans.modules.wsdlextensions.scheduler.utils.Utils;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.w3c.dom.Element;

/**
 *
 * @author sunsoabi_edwong
 */
public class HybridTriggerImpl extends SchedulerTriggerImpl
        implements HybridTrigger {
    
    public HybridTriggerImpl(WSDLModel wsdlModel) {
        super(wsdlModel);
    }
    
    public HybridTriggerImpl(WSDLModel wsdlModel, Element element) {
        super(wsdlModel, element);
    }

    @Override
    public String getType() {
        return TriggerType.HYBRID.getProgName();
    }

    @Override
    public void setType(String type) {
        super.setType(TriggerType.HYBRID.getProgName());
    }

    public String getCronExpression() {
        return getAttribute(SchedulerAttribute.CRON_EXPR);
    }

    public void setCronExpression(String expression) {
        setAttribute(SchedulerAttribute.CRON_EXPR.getName(),
                SchedulerAttribute.CRON_EXPR, expression);
    }
    
    public long getDuration() {
        String duration = getDurationAsString();
        if (!Utils.isEmpty(duration)) {
            try {
                return Long.parseLong(duration);
            } catch (NumberFormatException nfe) {
                // ignore
            }
        }
        return 0L;
    }
    
    public String getDurationAsString() {
        return getAttribute(SchedulerAttribute.DURATION);
    }
    
    public void setDuration(long duration) {
        setAttribute(SchedulerAttribute.DURATION.getName(),
                SchedulerAttribute.DURATION, Long.toString(duration));
    }
    
    public String getRepeat() {
        return getAttribute(SchedulerAttribute.REPEAT);
    }

    public void setRepeat(String repeat) {
        setAttribute(SchedulerAttribute.REPEAT.getName(),
                SchedulerAttribute.REPEAT, repeat);
    }

    public long getInterval() {
        String str = getIntervalAsString();
        Long interval = 0L;
        if (!Utils.isEmpty(str)) {
            try {
                interval = Long.parseLong(str);
            } catch (NumberFormatException nfe) {
                interval = 0L;
            }
        }
        return interval;
    }
    
    public String getIntervalAsString() {
        return getAttribute(SchedulerAttribute.INTERVAL);
    }

    public void setInterval(long interval) {
        setAttribute(SchedulerAttribute.INTERVAL.getName(),
                SchedulerAttribute.INTERVAL, Long.toString(interval));
    }

}
