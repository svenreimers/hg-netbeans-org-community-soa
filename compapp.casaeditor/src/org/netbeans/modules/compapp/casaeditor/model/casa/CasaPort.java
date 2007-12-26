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
package org.netbeans.modules.compapp.casaeditor.model.casa;

/**
 *
 * @author jqian
 */
public interface CasaPort extends CasaComponent {
    public static final String X_PROPERTY = "x";                        // NOI18N
    public static final String Y_PROPERTY = "y";                        // NOI18N
    public static final String STATE_PROPERTY = "state";                // NOI18N
    public static final String LINK_PROPERTY = "link";                  // NOI18N
    public static final String CONSUMES_PROPERTY = "consumes";          // NOI18N
    public static final String PROVIDES_PROPERTY = "provides";          // NOI18N
    
    // The bindingType can not be derived by its binding component name, e.g.,  
    // both SOAP and HTTP type shared the same binding component, sun-http-binding.
    public static final String BINDINGTYPE_PROPERTY = "bindingType";    // NOI18N
//    public static final String PORTTYPE_PROPERTY = "portType";          // NOI18N
//
//    String getPortType();
//    void setPortType(String portType);
//    
    String getBindingType();
    void setBindingType(String bindingType);
    
    int getX();
    void setX(int x);
    
    int getY();
    void setY(int y);
    
    String getState();
    void setState(String state);
    
    CasaLink getLink();
    void setLink(CasaLink link);
    
    CasaConsumes getConsumes();
    void setConsumes(CasaConsumes casaConsumes);
    
    CasaProvides getProvides();
    void setProvides(CasaProvides casaProvides);
    
    // Convenience methods
    String getEndpointName();
}