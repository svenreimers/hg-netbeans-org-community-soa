/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.bpel.properties.props.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import org.netbeans.modules.soa.ui.form.ValidablePropertyCustomizer;
import org.netbeans.modules.bpel.properties.Util;
import org.netbeans.modules.bpel.properties.editors.controls.AbstractTreeChooserPanel;
import org.netbeans.modules.soa.ui.form.valid.Validator;
import org.netbeans.modules.bpel.properties.props.PropertyVetoError;
import org.netbeans.modules.soa.ui.SoaUiUtil;
import org.openide.explorer.propertysheet.PropertyEnv;

/**
 *
 * @author nk160297
 */
public abstract class TreeChooserPropertyCustomizer<T extends AbstractTreeChooserPanel>
        extends ValidablePropertyCustomizer implements PropertyChangeListener {
    
    protected PropertyEditor myPropertyEditor;
    
    protected TreeChooserPropertyCustomizer() {
        initComponents();
        T pnlChooser = getChooserPanel();
        pnlChooser.createContent();
        //
        Util.attachDefaultDblClickAction(pnlChooser, pnlChooser);
        SoaUiUtil.activateInlineMnemonics(this);
    }
    
    protected synchronized T getChooserPanel() {
        if (pnlChooser == null) {
            pnlChooser = createChooserPanel();
        }
        return (T)pnlChooser;
    }
    
    protected abstract T createChooserPanel();
    protected abstract void applyNewValues();
    
    public void init(PropertyEnv propertyEnv, PropertyEditor propertyEditor) {
        if (myPropertyEnv != null) {
            myPropertyEnv.removePropertyChangeListener(this);
        }
        //
        super.init(propertyEnv, propertyEditor);
        myPropertyEditor = propertyEditor;
        //
        myPropertyEnv.addPropertyChangeListener(this);
        //
        // Synchronize curent state
        myPropertyEnv.setState(getValidStateManager(true).isValid() ?
            PropertyEnv.STATE_NEEDS_VALIDATION :
            PropertyEnv.STATE_INVALID);
    }
    
    public void propertyChange(PropertyChangeEvent event) {
        if (PropertyEnv.PROP_STATE.equals(event.getPropertyName()) &&
                event.getNewValue() == PropertyEnv.STATE_VALID) {
            try {
                applyNewValues();
            } catch (PropertyVetoError ex) {
                myPropertyEnv.setState(PropertyEnv.STATE_NEEDS_VALIDATION);
                PropertyVetoError.defaultProcessing(ex);
            }
        }
    }
    
    public Validator createValidator() {
        return getChooserPanel().getValidator();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlChooser = getChooserPanel();

        org.jdesktop.layout.GroupLayout pnlChooserLayout = new org.jdesktop.layout.GroupLayout(pnlChooser);
        pnlChooser.setLayout(pnlChooserLayout);
        pnlChooserLayout.setHorizontalGroup(
            pnlChooserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 376, Short.MAX_VALUE)
        );
        pnlChooserLayout.setVerticalGroup(
            pnlChooserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 274, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(pnlChooser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(pnlChooser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlChooser;
    // End of variables declaration//GEN-END:variables
    
}
