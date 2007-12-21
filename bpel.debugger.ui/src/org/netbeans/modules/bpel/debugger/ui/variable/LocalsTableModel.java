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

package org.netbeans.modules.bpel.debugger.ui.variable;

import java.util.Vector;
import javax.swing.JToolTip;
import org.netbeans.modules.bpel.debugger.api.BpelDebugger;
import org.netbeans.modules.bpel.debugger.ui.util.VariablesUtil;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.spi.debugger.ui.Constants;
import org.netbeans.spi.viewmodel.ModelEvent;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.TableModel;
import org.netbeans.spi.viewmodel.TreeModel;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.w3c.dom.Node;

/**
 * Table model supporting the Local Variable view.
 * 
 * @author Alexander Zgursky
 * @author Kirill Sorokin
 */
public class LocalsTableModel implements TableModel, Constants {
    
    private BpelDebugger myDebugger;
    private VariablesUtil myHelper;
    
    private Vector myListeners = new Vector();
    
    /**{@inheritDoc}*/
    public LocalsTableModel(
            final ContextProvider contextProvider) {
        
        myDebugger = (BpelDebugger) contextProvider.lookupFirst(
                null, BpelDebugger.class);
        myHelper = new VariablesUtil(myDebugger);
    }
    
    /**{@inheritDoc}*/
    public Object getValueAt(
            final Object object, 
            final String column) throws UnknownTypeException {
        
        if (object == TreeModel.ROOT) {
            return "";
        }
        
        if (column.equals(LOCALS_VALUE_COLUMN_ID)) {
            if (object instanceof JToolTip) {
                final Object realObject = ((JToolTip) object).
                        getClientProperty("getShortDescription"); // NOI18N
                
                return myHelper.getValueTooltip(realObject);
            }
            
            return object;
        }
        
        if (column.equals(LOCALS_TYPE_COLUMN_ID)) {
            if (object instanceof JToolTip) {
                final Object realObject = ((JToolTip) object).
                        getClientProperty("getShortDescription"); // NOI18N
                
                return myHelper.getTypeTooltip(realObject);
            }
            
            return object;
        }
        
        if (column.equals(LOCALS_TO_STRING_COLUMN_ID)) {
            return object.toString();
        }
        
        throw new UnknownTypeException(object);
    }
    
    
    /**{@inheritDoc}*/
    public void setValueAt(
            final Object object, 
            final String column, 
            final Object value) throws UnknownTypeException {
        
        if (column.equals(LOCALS_VALUE_COLUMN_ID)) {
            if (object instanceof Node) {
                myHelper.setValue(object, (String) value);
            }
            
            fireTableValueChanged(object, LOCALS_VALUE_COLUMN_ID);
        }
        
        throw new UnknownTypeException(object);
    }
    
    /**{@inheritDoc}*/
    public boolean isReadOnly(
            final Object object, 
            final String column) throws UnknownTypeException {
        
        if (column.equals(LOCALS_VALUE_COLUMN_ID)) {
            return myHelper.isValueReadOnly(object);
        }
        
        if (column.equals(LOCALS_TYPE_COLUMN_ID)) {
            return true;
        }
        
        throw new UnknownTypeException(object);
    }
    
    /**{@inheritDoc}*/
    public void addModelListener(
            final ModelListener listener) {
        
        myListeners.add(listener);
    }
    
    /**{@inheritDoc}*/
    public void removeModelListener(
            final ModelListener listener) {
        
        myListeners.remove(listener);
    }
    
    // Private /////////////////////////////////////////////////////////////////
    
    //TODO:this is neither an effecient nor right way to notify about variable
    //change. Need to fire event on the BpelDebugger level and subscribe tree
    //model to listen for "variable changed" events. With this approach 
    //WatchesTreeModel can subscribe as well
    private void fireTableValueChanged(
            final Object node, 
            final String propertyName) {
        
        final Vector clone = (Vector) myListeners.clone();
        
        for (int i = 0; i < clone.size(); i++) {
            ((ModelListener) clone.get(i)).modelChanged(
                    new ModelEvent.TreeChanged(this));
        }
    }
}
