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
package org.netbeans.modules.wsdlextensions.rest.configeditor;

import org.openide.util.NbBundle;

/**
 *
 * @author jqian
 */
public class OutboundProperties extends ValidatableProperties {

    public static final String URL_PROPERTY = "url"; // NOI18N
    public static final String METHOD_PROPERTY = "method"; // NOI18N
    public static final String ACCEPT_TYPES_PROPERTY = "accept-types"; // NOI18N
    public static final String ACCEPT_LANGUAGES_PROPERTY = "accept-languages"; // NOI18N
    public static final String CONTENT_TYPE_PROPERTY = "content-type"; // NOI18N
    public static final String HEADERS_PROPERTY = "headers"; // NOI18N
    public static final String PARAM_STYLE_PROPERTY = "param-style"; // NOI18N
    public static final String PARAMS_PROPERTY = "params"; // NOI18N
    public static final String BASIC_AUTH_USER_NAME_PROPERTY = "basic-auth-username"; // NOI18N
    public static final String BASIC_AUTH_PASSWORD_PROPERTY = "basic-auth-password"; // NOI18N
    public static final String USER_DEFINED_PROPERTY = "user-defined"; // NOI18N

    public static final String[] PRE_DEFINED_PROPERTIES = new String[] {
        URL_PROPERTY,
        METHOD_PROPERTY,
        ACCEPT_TYPES_PROPERTY,
        ACCEPT_LANGUAGES_PROPERTY,
        CONTENT_TYPE_PROPERTY,
        HEADERS_PROPERTY,
        PARAM_STYLE_PROPERTY,
        PARAMS_PROPERTY,
        BASIC_AUTH_USER_NAME_PROPERTY,
        BASIC_AUTH_PASSWORD_PROPERTY,
    };

    public OutboundProperties() {
        super("/org/netbeans/modules/wsdlextensions/rest/template/properties/RESTOutboundProperties.txt"); // NOI18N
    }

    public String getValidationError() {
        String ret = null;

        if (get(URL_PROPERTY) == null || get(URL_PROPERTY).trim().length() == 0) {
            ret = NbBundle.getMessage(OutboundProperties.class, "OutboundProperties.URLMissing"); // NOI18N
        }

        return ret;
    }
}
