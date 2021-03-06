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
 * Portions Copyrighted 1997-2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.worklist.wizard2;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.netbeans.modules.worklist.editor.spi.impl.WLMWSDLProvider;
import org.netbeans.modules.wsdleditorapi.generator.ElementOrType;
import org.netbeans.modules.soa.wsdl.bindingsupport.ui.util.BindingComponentUtils;
import org.netbeans.modules.xml.schema.model.GlobalElement;
import org.netbeans.modules.xml.schema.model.GlobalType;
import org.netbeans.modules.xml.schema.model.SchemaComponent;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;

public final class WLMVisualPanel2 extends JPanel {

    static String WSDL_MODEL = "wsdl_model";
    static String INPUT_ELEMENT_OR_TYPE = "input_element_or_type";
    static String OUTPUT_ELEMENT_OR_TYPE = "output_element_or_type";
    static String FAULT_ELEMENT_OR_TYPE = "fault_element_or_type";
    private Project mProject = null;
    private WSDLModel mWsdlModel = null;
    private ElementOrType mInputElementOrType = null;
    private ElementOrType mOutputElementOrType = null;
    private ElementOrType mFaultElementOrType = null;
    private Map<String, String> mNamespaceToPrefixMap = null;

    /** Creates new form WLMVisualPanel2 */
    public WLMVisualPanel2() {
        initComponents();
    }

    WLMVisualPanel2(Project project, WSDLModel wsdlModel) {
        this.mProject = project;
        this.mWsdlModel = wsdlModel;
        initModel();
        initComponents();
    }

    public ElementOrType getInputElementOrType() {
        return this.mInputElementOrType;
    }

    public ElementOrType getOutputElementOrType() {
        return this.mOutputElementOrType;
    }

    public ElementOrType getFaultElementOrType() {
        return this.mFaultElementOrType;
    }

    @Override
    public String getName() {
        return "Message Types";
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tfOutputMessage = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfInputMessage = new javax.swing.JTextField();
        tfFaultMessage = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnOutputMsgSelection = new javax.swing.JButton();
        btnInputMsgSelection = new javax.swing.JButton();
        bthFaultMessageSelection = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.jLabel9.text_1")); // NOI18N

        tfOutputMessage.setEditable(false);
        tfOutputMessage.setText(org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.tfOutputMessage.text_1")); // NOI18N
        tfOutputMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfOutputMessageActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.jLabel10.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.jLabel1.text")); // NOI18N

        tfInputMessage.setEditable(false);
        tfInputMessage.setText(org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.tfInputMessage.text")); // NOI18N
        tfInputMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfInputMessageActionPerformed(evt);
            }
        });

        tfFaultMessage.setEditable(false);
        tfFaultMessage.setText(org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.tfFaultMessage.text")); // NOI18N
        tfFaultMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFaultMessageActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnOutputMsgSelection, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.btnOutputMsgSelection.text")); // NOI18N
        btnOutputMsgSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutputMsgSelectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnInputMsgSelection, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.btnInputMsgSelection.text")); // NOI18N
        btnInputMsgSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputMsgSelectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(bthFaultMessageSelection, org.openide.util.NbBundle.getMessage(WLMVisualPanel2.class, "WLMVisualPanel2.bthFaultMessageSelection.text")); // NOI18N
        bthFaultMessageSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthFaultMessageSelectionActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(248, 248, 248)
                        .add(jLabel8))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(tfFaultMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 212, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jLabel10)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tfOutputMessage))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jLabel9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(tfInputMessage))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnInputMsgSelection)
                            .add(btnOutputMsgSelection)
                            .add(bthFaultMessageSelection))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(tfInputMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnInputMsgSelection))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(tfOutputMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnOutputMsgSelection))
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(tfFaultMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bthFaultMessageSelection))
                .add(202, 202, 202)
                .add(jLabel8))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void tfOutputMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfOutputMessageActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfOutputMessageActionPerformed

private void tfInputMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfInputMessageActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfInputMessageActionPerformed

private void tfFaultMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFaultMessageActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfFaultMessageActionPerformed

private void btnInputMsgSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputMsgSelectionActionPerformed
    mInputElementOrType = getElementOrTypeSelection(mInputElementOrType);
    String partElementOrTypeStr = BindingComponentUtils.getPrefixNameSpace();
    tfInputMessage.setText(partElementOrTypeStr);
}//GEN-LAST:event_btnInputMsgSelectionActionPerformed

private void btnOutputMsgSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutputMsgSelectionActionPerformed
    mOutputElementOrType = getElementOrTypeSelection(mOutputElementOrType);
    String partElementOrTypeStr = BindingComponentUtils.getPrefixNameSpace();
    tfOutputMessage.setText(partElementOrTypeStr);
}//GEN-LAST:event_btnOutputMsgSelectionActionPerformed

private void bthFaultMessageSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthFaultMessageSelectionActionPerformed
    mFaultElementOrType = getElementOrTypeSelection(mFaultElementOrType);
    String partElementOrTypeStr = BindingComponentUtils.getPrefixNameSpace();
    tfFaultMessage.setText(partElementOrTypeStr);
}//GEN-LAST:event_bthFaultMessageSelectionActionPerformed

    private void initModel() {
        if (mNamespaceToPrefixMap == null) {
            this.mNamespaceToPrefixMap = WLMWSDLProvider.buildNamespaceMap(mWsdlModel);        
            this.mInputElementOrType  = new ElementOrType(WLMWSDLProvider.getStringSimpleType(mWsdlModel), mNamespaceToPrefixMap);
            this.mOutputElementOrType = new ElementOrType(WLMWSDLProvider.getStringSimpleType(mWsdlModel), mNamespaceToPrefixMap);
        }
    }

    private ElementOrType  getElementOrTypeSelection (ElementOrType elementOrType) {
        SchemaComponent comp = null;
        if (elementOrType != null) {
            comp = elementOrType.isElement() ? elementOrType.getElement() : elementOrType.getType();
        }
        boolean ok = BindingComponentUtils.browseForElementOrType(mProject, mWsdlModel, comp);
        
        if (ok) {
            GlobalType type = BindingComponentUtils.getElementOrType();
            GlobalElement element = BindingComponentUtils.getSchemaComponent() ;
            Map<String, String> namespaceToPrefixMap = new HashMap<String, String>();
            if (type != null) {
                return new ElementOrType(type, namespaceToPrefixMap);
            } else if (element != null) {
                return new ElementOrType(element, namespaceToPrefixMap);
            }
        }
        
        return null;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bthFaultMessageSelection;
    private javax.swing.JButton btnInputMsgSelection;
    private javax.swing.JButton btnOutputMsgSelection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfFaultMessage;
    private javax.swing.JTextField tfInputMessage;
    private javax.swing.JTextField tfOutputMessage;
    // End of variables declaration//GEN-END:variables
}