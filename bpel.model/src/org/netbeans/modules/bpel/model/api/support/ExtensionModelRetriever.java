/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
 * License. When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
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
package org.netbeans.modules.bpel.model.api.support;

import java.io.InputStream;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

import org.netbeans.modules.xml.schema.model.SchemaModel;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;

import org.netbeans.modules.bpel.model.api.BpelModel;
import org.netbeans.modules.bpel.model.api.resources.ResourcePackageMarker;
import org.netbeans.modules.bpel.model.ext.Extensions;
import org.netbeans.modules.bpel.model.xam.spi.ExternalModelRetriever;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2008.10.28
 */
public class ExtensionModelRetriever implements ExternalModelRetriever {

    public Collection<SchemaModel> getSchemaModels(BpelModel model, String namespace) {
        if (Extensions.ERROR_HANDLER_URI.equals(namespace)) {
//System.out.println("== GET XML ErrorHandler");
            return ERROR_HANDLER;
        }
        if (Extensions.TRACE_EXT_URI.equals(namespace)) {
            return TRACE;
        }
        if (Extensions.EDITOR_EXT_URI.equals(namespace)) {
            return EDITOR;
        }
        return Collections.EMPTY_LIST;
    }

    public Collection<WSDLModel> getWSDLModels(BpelModel model, String namespace) {
        if (Extensions.ERROR_HANDLER_URI.equals(namespace)) {
//System.out.println("== GET WSDL ErrorHandler");
            return FAULT_MESSAGE;
        }
        return Collections.EMPTY_LIST;
    }

    // # 161603
    public static WSDLModel getFaultMessageModel() {
        return FAULT_MESSAGE_MODEL;
    }

    private static final WSDLModel FAULT_MESSAGE_MODEL = Utils.getWsdlModel(ResourcePackageMarker.class.getResourceAsStream(ResourcePackageMarker.ERROR_HANDLING_WSDL));

    private static final List<WSDLModel> FAULT_MESSAGE = Collections.singletonList(FAULT_MESSAGE_MODEL);
    private static final List<SchemaModel> ERROR_HANDLER = Collections.singletonList(Utils.getSchemaModel(ResourcePackageMarker.class.getResourceAsStream(ResourcePackageMarker.ERROR_HANDLING_SCHEMA)));
    private static final List<SchemaModel> TRACE = Collections.singletonList(Utils.getSchemaModel(ResourcePackageMarker.class.getResourceAsStream(ResourcePackageMarker.TRACE_SCHEMA)));
    private static final List<SchemaModel> EDITOR = Collections.singletonList(Utils.getSchemaModel(ResourcePackageMarker.class.getResourceAsStream(ResourcePackageMarker.EDITOR_EXT_SCHEMA)));
}
