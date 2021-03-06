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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2009 Sun
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
package org.netbeans.modules.compapp.test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openide.xml.EntityCatalog;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * An EntityResolver for offline compapp unit testing.
 *
 * @author jqian
 */
// #
// See org.netbeans.modules.editor.settings.storage.Utils.load() and
// org.openide.xml.EntityCatalog.getDefault().
public class MyEntityCatalog extends EntityCatalog {

    private static final String EDITOR_KEY_BINDINGS_DTD_1_0_SYSTEM_ID =
            "http://www.netbeans.org/dtds/EditorKeyBindings-1_0.dtd"; // NOI18N
    private static final String EDITOR_KEY_BINDINGS_1_0_DTD =
            "EditorKeyBindings-1_0.dtd"; // NOI18N
    
    private static final String EDITOR_KEY_BINDINGS_DTD_1_1_SYSTEM_ID =
            "http://www.netbeans.org/dtds/EditorKeyBindings-1_1.dtd"; // NOI18N
    private static final String EDITOR_KEY_BINDINGS_1_1_DTD =
            "EditorKeyBindings-1_1.dtd"; // NOI18N

    private static final String EDITOR_PREFERENCES_DTD_1_0_SYSTEM_ID =
            "http://www.netbeans.org/dtds/EditorPreferences-1_0.dtd"; // NOI18N
    private static final String EDITOR_PREFERENCES_1_0_DTD =
            "EditorPreferences-1_0.dtd"; // NOI18N

    private static final String EDITOR_PROPERTIES_DTD_1_0_SYSTEM_ID =
            "http://www.netbeans.org/dtds/EditorProperties-1_0.dtd"; // NOI18N
    private static final String EDITOR_PROPERTIES_1_0_DTD =
            "EditorProperties-1_0.dtd"; // NOI18N

    /**
     * A map mapping system ID to local DTD file name.
     */
    private static Map<String, String> systemID2DTD = new HashMap<String, String>();

    static {
        systemID2DTD.put(EDITOR_KEY_BINDINGS_DTD_1_0_SYSTEM_ID, EDITOR_KEY_BINDINGS_1_0_DTD);
        systemID2DTD.put(EDITOR_KEY_BINDINGS_DTD_1_1_SYSTEM_ID, EDITOR_KEY_BINDINGS_1_1_DTD);
        systemID2DTD.put(EDITOR_PREFERENCES_DTD_1_0_SYSTEM_ID, EDITOR_PREFERENCES_1_0_DTD);
        systemID2DTD.put(EDITOR_PROPERTIES_DTD_1_0_SYSTEM_ID, EDITOR_PROPERTIES_1_0_DTD);
    }

    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
        String dtd = systemID2DTD.get(systemId);
        if (dtd != null) {
            URL url = getClass().getResource("resources/" + dtd); // NOI18N
            return new InputSource(url.openStream());
        } else {
            return null;
        }
    }

}
