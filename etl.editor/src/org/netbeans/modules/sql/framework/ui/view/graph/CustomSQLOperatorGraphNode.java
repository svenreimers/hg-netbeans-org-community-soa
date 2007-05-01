/* *************************************************************************
 *
 *          Copyright (c) 2005, Sun Microsystems,
 *          All Rights Reserved
 *
 *          This program, and all the routines referenced herein,
 *          are the proprietary properties and trade secrets of
 *          Sun Microsystems.
 *
 *          Except as provided for by license agreement, this
 *          program shall not be duplicated, used, or disclosed
 *          without  written consent signed by an officer of
 *          Sun Microsystems.
 *
 ***************************************************************************/
package org.netbeans.modules.sql.framework.ui.view.graph;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.sun.sql.framework.jdbc.SQLUtils;
import com.sun.sql.framework.utils.Logger;

import org.netbeans.modules.sql.framework.model.GUIInfo;
import org.netbeans.modules.sql.framework.model.SQLCanvasObject;
import org.netbeans.modules.sql.framework.model.SQLOperatorArg;
import org.netbeans.modules.sql.framework.model.impl.SQLCustomOperatorImpl;
import org.netbeans.modules.sql.framework.ui.graph.IOperatorField;
import org.netbeans.modules.sql.framework.ui.graph.IOperatorXmlInfo;
import org.netbeans.modules.sql.framework.ui.graph.impl.CustomOperatorNode;
import org.netbeans.modules.sql.framework.ui.graph.impl.OperatorGraphFieldNode;
import org.netbeans.modules.sql.framework.ui.model.CollabSQLUIModel;
import org.netbeans.modules.sql.framework.ui.model.impl.ConditionBuilderSQLUIModelImpl;
import org.netbeans.modules.sql.framework.ui.view.graph.SQLOperatorGraphNode.OperatorActionListener;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;


/**
 * Graphical representation of a custom operator
 *
 * @author Srinivasan Rengarajan
 */
public class CustomSQLOperatorGraphNode extends SQLOperatorGraphNode {
    
    private JMenuItem editItem;
    
    private IOperatorXmlInfo info;
    
    private static final String LOG_CATEGORY = CustomSQLOperatorGraphNode.class.getName();
    
    /** Creates a new instance of OperatorGraphNode */
    public CustomSQLOperatorGraphNode(IOperatorXmlInfo info) {
        
        super(info, false);
        this.info = info;
    }
    
    public CustomSQLOperatorGraphNode(IOperatorXmlInfo info, boolean show) {
        
        super(info, show);
        this.info = info;
    }
    
    public CustomSQLOperatorGraphNode(IOperatorXmlInfo info, boolean show,
            String nameOverride) {
        
        super(info, show, nameOverride);
        this.info = info;
    }
    
    /**
     * populates the menu and adds the edit option
     */
    protected void initializePopUpMenu() {
        
        CustomOperatorActionListener aListener = new CustomOperatorActionListener();
        // remove menu
        if( popUpMenu == null ) {
            popUpMenu = new JPopupMenu();
        }
       
        editItem = new JMenuItem("Edit", new ImageIcon(editUrl));
        editItem.addActionListener(aListener);
        popUpMenu.add(editItem);
        popUpMenu.addSeparator();
        super.initializePopUpMenu();
        
    }
    
    private class CustomOperatorActionListener extends OperatorActionListener {
        public void actionPerformed(ActionEvent e) {
            super.actionPerformed(e);
            Object source = e.getSource();
            if (source == editItem) {
                editItem_actionPerformed(e);
            }
        }
    }
    
    /**
     * method invoked to edit a custom operator definition
     *
     */
    private void editCustomOperator() {
        // step1 : get the original Data object
        SQLCanvasObject sqlObj = (SQLCanvasObject) this.getDataObject();
        Object graphModel = getGraphView().getGraphModel();
        // step2 : clone it
        SQLCanvasObject copyObj = null;
        try {
            // step3 : build the gui
            SQLCustomOperatorImpl custOp = (SQLCustomOperatorImpl) sqlObj;
            SQLOperatorArg retType = null;

            Iterator iter = custOp.getOperatorXmlInfo().getOutputFields().iterator();
            if (iter.hasNext()) {
                IOperatorField operField = (IOperatorField) iter.next();
                String jdbcType = (String) operField.getAttributeValue("retTypeStr");
                retType = new SQLOperatorArg(operField.getName(), SQLUtils.getStdJdbcType(jdbcType));
            }
            List inputArgs = custOp.getOperatorDefinition().getArgList();

            CustomOperatorPane customOptPane = new CustomOperatorPane(inputArgs, retType);

            customOptPane.setFunctionName(custOp.getCustomOperatorName());
            // String title = NbBundle.getMessage(BasicSQLGraphController.class,
            // "TITLE_user_function");
            DialogDescriptor dlgDesc = new DialogDescriptor(customOptPane, "title", true,
                    NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.OK_OPTION,
                    DialogDescriptor.DEFAULT_ALIGN, null, null);
            Dialog customOptDialog = DialogDisplayer.getDefault().createDialog(dlgDesc);
            customOptDialog.show();
            if (NotifyDescriptor.CANCEL_OPTION == dlgDesc.getValue()) {
                return;
            }

            // step 4: apply changes to the custOp which is a clone of the data
            // object
            inputArgs = customOptPane.getArgList();
            retType = customOptPane.getReturnType();
            custOp.setCustomOperatorName(customOptPane.getFunctionName());
            custOp.getOperatorDefinition().setArgList(inputArgs);
            CustomOperatorNode customOptNode = new CustomOperatorNode(info, inputArgs, retType);
            custOp.setOperatorXmlInfo(customOptNode);
            Iterator fieldIter = fieldList.iterator();
            while (fieldIter.hasNext()) {
                OperatorGraphFieldNode fieldNode = (OperatorGraphFieldNode) fieldIter.next();
                this.removeObject(fieldNode);

            }
            this.fieldList.clear();
            super.initialize(customOptNode);
            GUIInfo guiInfo = custOp.getGUIInfo();
            int x = guiInfo.getX();
            int y = guiInfo.getY();
            int width = guiInfo.getWidth();
            int height = guiInfo.getHeight();
            this.getBoundingRect().setLocation(x, y);
            this.getBoundingRect().setSize(width, height);
            this.titleArea.setTitle(customOptPane.getFunctionName());
            this.titleArea.setTopLeft(x, y);
            this.layoutChildren();
            if (graphModel instanceof CollabSQLUIModel) {
                ((CollabSQLUIModel) graphModel).restoreUIState();
            } else if (graphModel instanceof ConditionBuilderSQLUIModelImpl) {
                ((ConditionBuilderSQLUIModelImpl) graphModel).restoreUIState();
            }
        } catch (Exception e) {
            Logger.printThrowable(Logger.ERROR, LOG_CATEGORY, "editCustomOperator", e.getMessage(), e);
        }
    }
    
    /**
     * Invoked when an action occurs.
     */
    private void editItem_actionPerformed(ActionEvent e) {
        this.editCustomOperator();
    }
}
