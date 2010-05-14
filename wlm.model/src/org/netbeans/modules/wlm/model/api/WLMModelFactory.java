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
 * Portions Copyrighted 1997-2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.wlm.model.api;

import javax.swing.text.Document;
import org.netbeans.modules.wlm.model.impl.WLMModelImpl;
import org.netbeans.modules.xml.xam.ModelSource;
import org.netbeans.modules.xml.xam.AbstractModelFactory;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

/**
 *
 * 
 */
public class WLMModelFactory extends AbstractModelFactory<WLMModel> {
    
    private static final WLMModelFactory wsdlModelFactory = new WLMModelFactory();
    
    public static WLMModelFactory getDefault(){
        return wsdlModelFactory;
    }
    
    /** Creates a new instance of WLMModelFactory */
    private WLMModelFactory() {
    }

    /**
     * Gets WLMModel  from given model source.  Model source should 
     * provide lookup for:
     * 1. FileObject of the model source
     * 2. DataObject represent the model
     * 3. Swing Document buffer for in-memory text of the model source
     */
    public WLMModel getModel(ModelSource source) {
        if (source == null) return null;
        Lookup lookup = source.getLookup();
        assert lookup.lookup(Document.class) != null;
        return super.getModel(source);
    }
    
    public WLMModel createModel(ModelSource source) {
        return new WLMModelImpl(source);
    }
    
     /**
     * This method extracts the key from the model source. A subclass can 
     * change the ModelSource lookup requirements and thus this method may
     * be overridden to allow a different key to be used. 
     */
    protected Object getKey(ModelSource source) {
        return source.getLookup().lookup(FileObject.class);
    }
    
}
