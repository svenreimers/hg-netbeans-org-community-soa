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

package org.netbeans.modules.edm.editor.graph.actions;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.edm.editor.dataobject.MashupDataObject;
import org.netbeans.modules.edm.editor.ui.view.MashupDataObjectProvider;
import org.netbeans.modules.edm.editor.property.impl.PropertyNode;
import org.netbeans.modules.edm.editor.property.impl.TemplateFactory;
import org.netbeans.modules.edm.editor.ui.view.property.SQLCollaborationProperties;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.WindowManager;

/**
 *
 * @author admin
 */

public class SceneSelectProvider implements SelectProvider {

        public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2) {             
            return true;
        }

        public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2) {             
            return true;
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void select(Widget arg0, Point arg1, boolean arg2) {            
            String template = "Collaboration";
            final MashupDataObject mObj = MashupDataObjectProvider.getProvider().getActiveDataObject();
            Object pBean = new SQLCollaborationProperties(mObj.getModel().getSQLDefinition());

            PropertyNode pNode = mObj.getPropertyViewManager().getPropertyNodeForTemplateName(template, null, pBean);
            final Object pb = pBean;
            pNode.addPropertyChangeSupport(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent evt) {
                    // if value is differnt then only set it
                    if (evt.getOldValue() != null && !evt.getOldValue().equals(evt.getNewValue())) {
                        try {
                            TemplateFactory.invokeSetter(pb, evt.getPropertyName(), evt.getNewValue());
                            mObj.getMashupDataEditorSupport().synchDocument();
                            mObj.setModified(true);
                        } catch (Exception ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                }
            });
            WindowManager.getDefault().getRegistry().getActivated().setActivatedNodes(new Node[]{pNode});
        }        
    }