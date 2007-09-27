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

package org.netbeans.modules.iep.editor.ps;

import org.netbeans.modules.iep.editor.designer.GuiConstants;
import org.netbeans.modules.iep.editor.model.ModelManager;
import org.netbeans.modules.iep.editor.model.Plan;
import org.netbeans.modules.iep.editor.model.Schema;
import org.netbeans.modules.iep.editor.tcg.dialog.NotifyHelper;
import org.netbeans.modules.iep.editor.share.SharedConstants;
import org.netbeans.modules.iep.editor.tcg.model.TcgComponent;
import org.netbeans.modules.iep.editor.tcg.model.TcgProperty;
import org.netbeans.modules.iep.editor.tcg.ps.TcgComponentNodeProperty;
import org.netbeans.modules.iep.editor.tcg.ps.TcgComponentNodePropertyCustomizerState;
import org.netbeans.modules.iep.editor.tcg.ps.TcgComponentNodePropertyCustomizer;
import org.netbeans.modules.iep.editor.tcg.util.StringUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import org.openide.explorer.propertysheet.PropertyEnv;
import org.openide.util.NbBundle;

/**
 * DefaultCustomizer.java
 *
 * Created on November 10, 2006, 9:49 AM
 *
 * @author Bing Lu
 */
public class DefaultCustomizer extends TcgComponentNodePropertyCustomizer implements SharedConstants {
    protected static final Logger mLog = Logger.getLogger(DefaultCustomizer.class.getName());
    
    protected TcgComponent mComponent;
    protected PropertyPanel mNamePanel;
    protected PropertyPanel mOutputSchemaNamePanel;
    protected InputSchemaTreePanel mInputPanel;
    protected SelectPanel mSelectPanel;
    protected PropertyPanel mFromPanel;
    protected PropertyPanel mWherePanel;
    protected PropertyPanel mGroupByPanel;
    protected JLabel mStatusLbl;
    
    protected boolean mIsSchemaOwner;
    protected boolean mHasExpressionColumn;
    protected boolean mHasFromClause;
    protected boolean mHasWhereClause;
    protected boolean mHasGroupBy;
    
    public DefaultCustomizer(TcgComponentNodeProperty prop, PropertyEnv env) {
        super(prop, env);
    }
    
    public DefaultCustomizer(TcgComponentNodeProperty prop, TcgComponentNodePropertyCustomizerState customizerState) {
        super(prop, customizerState);
    }
    
    
    protected void initialize() {
        try {
            mComponent = mProperty.getProperty().getParentComponent();
            mIsSchemaOwner = mComponent.getProperty(IS_SCHEMA_OWNER_KEY).getBoolValue();
            String inputType = mComponent.getProperty(INPUT_TYPE_KEY).getStringValue();
            mHasExpressionColumn = mIsSchemaOwner && !inputType.equals(IO_TYPE_NONE);
            mHasFromClause = mComponent.hasProperty(FROM_CLAUSE_KEY);
            mHasWhereClause = mComponent.hasProperty(WHERE_CLAUSE_KEY);
            mHasGroupBy = mComponent.hasProperty(GROUP_BY_COLUMN_LIST_KEY);
            
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3);
            int gGridy = 0;
            
            // create selection panel first to parse schema information
            // which is used by properties in property panel created
            // by createPropertyPanel()
            mSelectPanel = new SelectPanel((Plan)mProperty.getNode().getDoc(), mComponent);
            
            // property pane
            gbc.gridx = 0;
            gbc.gridy = gGridy++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0D;
            gbc.weighty = 0.0D;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JPanel topPanel = createPropertyPanel();
            add(topPanel, gbc);
            
            // attribute pane
            gbc.gridx = 0;
            gbc.gridy = gGridy++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0D;
            gbc.weighty = 1.0D;
            gbc.fill = GridBagConstraints.BOTH;
            JComponent attributePane;
            if (mHasExpressionColumn) {
                attributePane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            } else {
                attributePane = new JPanel();
                attributePane.setLayout(new BorderLayout());
            }
            String msg = NbBundle.getMessage(DefaultCustomEditor.class, "CustomEditor.ATTRIBUTES");
            attributePane.setBorder(new TitledBorder(LineBorder.createGrayLineBorder(), msg, TitledBorder.LEFT, TitledBorder.TOP));
            add(attributePane, gbc);
            
            // left attribute pane
            if (mHasExpressionColumn) {
                ((JSplitPane)attributePane).setOneTouchExpandable(true);
                mInputPanel = new InputSchemaTreePanel((Plan)mProperty.getNode().getDoc(), mComponent);
                mInputPanel.setPreferredSize(new Dimension(200, 400));
                ((JSplitPane)attributePane).setLeftComponent(mInputPanel);
            }
            
            // right attribute pane
            JPanel rightPane = new JPanel();
            rightPane.setPreferredSize(new Dimension(700, 400));
            if (mHasExpressionColumn) {
                ((JSplitPane)attributePane).setRightComponent(rightPane);
            } else {
                attributePane.add(rightPane, BorderLayout.CENTER);
            }
            rightPane.setLayout(new GridBagLayout());
            int rightPaneGridY = 0;
            gbc.gridx = 0;
            gbc.gridy = rightPaneGridY++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0D;
            gbc.weighty = 0.5D;
            gbc.fill = GridBagConstraints.BOTH;
            rightPane.add(mSelectPanel, gbc);
            
            if (mHasExpressionColumn) {
                if (mHasFromClause) {
                    gbc.gridx = 0;
                    gbc.gridy = rightPaneGridY++;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.weightx = 1.0D;
                    gbc.weighty = 0.0D;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    TcgProperty fromProp = mComponent.getProperty(FROM_CLAUSE_KEY);
                    boolean truncateColumn = true;
                    mFromPanel = PropertyPanel.createSmartSingleLineTextPanel("FROM", fromProp, truncateColumn, true);
                    rightPane.add(mFromPanel.panel, gbc);
                }
                if (mHasWhereClause) {
                    gbc.gridx = 0;
                    gbc.gridy = rightPaneGridY++;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.weightx = 1.0D;
                    gbc.weighty = 0.5D;
                    gbc.fill = GridBagConstraints.BOTH;
                    TcgProperty whereProp = mComponent.getProperty(WHERE_CLAUSE_KEY);
                    mWherePanel = PropertyPanel.createSmartMultiLineTextPanel("WHERE", whereProp);
                    rightPane.add(mWherePanel.panel, gbc);
                }
                if (mHasGroupBy) {
                    gbc.gridx = 0;
                    gbc.gridy = rightPaneGridY++;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.weightx = 1.0D;
                    gbc.weighty = 0.0D;
                    gbc.fill = GridBagConstraints.BOTH;
                    TcgProperty groupByProp = mComponent.getProperty(GROUP_BY_COLUMN_LIST_KEY);
                    boolean truncateColumn = false;
                    mGroupByPanel = PropertyPanel.createSmartSingleLineTextPanel("GROUP BY", groupByProp, truncateColumn, true);
                    rightPane.add(mGroupByPanel.panel, gbc);
                }
            }
            
            // status bar
            gbc.gridx = 0;
            gbc.gridy = gGridy++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0D;
            gbc.weighty = 0.0D;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mStatusLbl = new JLabel();
            mStatusLbl.setForeground(Color.RED);
            mStatusLbl.setPreferredSize(new Dimension(160, 20));
            add(mStatusLbl, gbc);
        } catch(Exception e) {
            mLog.log(Level.SEVERE,
                    NbBundle.getMessage(DefaultCustomEditor.class, "CustomEditor.FAILED_TO_LAYOUT"),
                    e);
        }
    }
    
    protected JPanel createPropertyPanel() throws Exception {
        JPanel pane = new JPanel();
        String msg = NbBundle.getMessage(DefaultCustomEditor.class, "CustomEditor.DETAILS");
        pane.setBorder(new CompoundBorder(
                    new TitledBorder(LineBorder.createGrayLineBorder(), msg, TitledBorder.LEFT, TitledBorder.TOP),
                    BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        
        // name
        TcgProperty nameProp = mComponent.getProperty(NAME_KEY);
        String nameStr = NbBundle.getMessage(DefaultCustomEditor.class, "CustomEditor.NAME");
        mNamePanel = PropertyPanel.createSingleLineTextPanel(nameStr, nameProp, false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0D;
        gbc.weighty = 0.0D;
        gbc.fill = GridBagConstraints.NONE;
        pane.add(mNamePanel.component[0], gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0D;
        gbc.weighty = 0.0D;
        gbc.fill = GridBagConstraints.NONE;
        pane.add(mNamePanel.component[1], gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0D;
        gbc.weighty = 0.0D;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pane.add(Box.createHorizontalGlue(), gbc);
        
        // output schema
        TcgProperty outputSchemaNameProp = mComponent.getProperty(OUTPUT_SCHEMA_ID_KEY);
        String outputSchemaNameStr = NbBundle.getMessage(DefaultCustomEditor.class, "CustomEditor.OUTPUT_SCHEMA_NAME");
        mOutputSchemaNamePanel = PropertyPanel.createSingleLineTextPanel(outputSchemaNameStr, outputSchemaNameProp, false);
        if (mIsSchemaOwner) {
            if (mOutputSchemaNamePanel.getStringValue() == null || mOutputSchemaNamePanel.getStringValue().trim().equals("")) {
                mOutputSchemaNamePanel.setStringValue(((Plan)mProperty.getNode().getDoc()).getNameForNewSchema());
            }
        } else {
            ((JTextField)mOutputSchemaNamePanel.input[0]).setEditable(false);
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0D;
        gbc.weighty = 0.0D;
        gbc.fill = GridBagConstraints.NONE;
        pane.add(mOutputSchemaNamePanel.component[0], gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0D;
        gbc.weighty = 0.0D;
        gbc.fill = GridBagConstraints.NONE;
        pane.add(mOutputSchemaNamePanel.component[1], gbc);
        
        // glue
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0D;
        gbc.weighty = 0.0D;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pane.add(Box.createHorizontalGlue(), gbc);
        
         
        return pane;
    }
    
    public void validateContent(PropertyChangeEvent evt) throws PropertyVetoException {
        try {
            Plan plan = (Plan)mProperty.getNode().getDoc();
            // name
            mNamePanel.validateContent(evt);
            String newName = mNamePanel.getStringValue();
            String name = mComponent.getProperty(NAME_KEY).getStringValue();
            if (!newName.equals(name) && plan.hasOperator(newName)) {
                String msg = NbBundle.getMessage(DefaultCustomEditor.class,
                        "CustomEditor.NAME_IS_ALREADY_TAKEN_BY_ANOTHER_OPERATOR",
                        newName);
                throw new PropertyVetoException(msg, evt);
            }
            
            if (mIsSchemaOwner) {
                // output schema name
                mOutputSchemaNamePanel.validateContent(evt);
                String newSchemaName = mOutputSchemaNamePanel.getStringValue();
                String schemaName = mComponent.getProperty(OUTPUT_SCHEMA_ID_KEY).getStringValue();
                if (!newSchemaName.equals(schemaName) && plan.hasSchema(newSchemaName)) {
                    String msg = NbBundle.getMessage(DefaultCustomEditor.class,
                            "CustomEditor.OUTPUT_SCHEMA_NAME_IS_ALREADY_TAKENBY_ANOTHER_SCHEMA",
                            newSchemaName);
                    throw new PropertyVetoException(msg, evt);
                }
                
                // schema
                mSelectPanel.validateContent(evt);
                
                if (mHasExpressionColumn) {
                    if (mHasFromClause) {
                        // from clause
                        mFromPanel.validateContent(evt);
                        String from = mFromPanel.getStringValue();
                        List actualInpuNameList = StringUtil.getTokenList(from.trim(), ",");
                        for (int i = 0; i < actualInpuNameList.size(); i++) {
                            // from could be: T t, S s
                            String s = ((String)actualInpuNameList.get(i)).trim();
                            int idx = s.indexOf(" ");
                            if (0 < idx) {
                                s = s.substring(0, idx);
                            }
                            if (!mSelectPanel.hasInput(s)) {
                                String msg = NbBundle.getMessage(DefaultCustomEditor.class,
                                        "CustomEditor.INPUT_NAME_CANNOT_BE_FOUND_FROM_THE_INPUTS",
                                        s);
                                throw new PropertyVetoException(msg, evt);
                            }
                        }
                    }
                    if (mHasWhereClause) {
                        // where clause
                        mWherePanel.validateContent(evt);
                    }
                    // group by
                    if (mHasGroupBy) {
                        mGroupByPanel.validateContent(evt);
                        String value = mGroupByPanel.getStringValue();
                        List attributeList = StringUtil.getTokenList(value, ",");
                        // group-by attribute name must be found from input tree
                        for (int i = 0, I = attributeList.size(); i < I; i++) {
                            String attributeName = (String)attributeList.get(i);
                            if (mSelectPanel.getAttributeMetadata(attributeName) == null) {
                                String msg = NbBundle.getMessage(DefaultCustomEditor.class,
                                        "CustomEditor.GROUP_BY_ATTRIBUTE_NAME_CANNOT_FOUND_FROM_THE_INPUT_ATTRIBUTES",
                                        attributeName);
                                throw new PropertyVetoException(msg, evt);
                            }
                        }
                        // no duplicate group-by attribute names
                        Set nameSet = new HashSet();
                        for (int i = 0, I = attributeList.size(); i < I; i++) {
                            String attributeName = (String)attributeList.get(i);
                            if (nameSet.contains(attributeName)) {
                                String msg = NbBundle.getMessage(DefaultCustomEditor.class,
                                        "CustomEditor.DUPLICATE_GROUP_BY_ATTRIBUTE_NAME_IS_NOT_ALLOWED",
                                        attributeName);
                                throw new PropertyVetoException(msg, evt);
                            }
                            nameSet.add(attributeName);
                        }
                        
                        // single-attribute expression in expression list must show in group by list
                        List expList = mSelectPanel.getExpressionList();
                        for (int i = 0, I = expList.size(); i < I; i++) {
                            String exp = (String)expList.get(i);
                            exp = exp.trim();
                            if (mSelectPanel.hasInputAttribute(exp) && !nameSet.contains(exp)) {
                                String msg = NbBundle.getMessage(DefaultCustomEditor.class,
                                        "CustomEditor.SINGLE_ATTRIBUTE_EXPRESSION_CANNOT_BE_FOUND_FROM_GROUP_BY_ATTRIBUTES",
                                        exp);
                                throw new PropertyVetoException(msg, evt);
                            }
                        }
                    }
                }
            }
            // Nothing to check for now
        } catch (Exception e) {
            String msg = e.getMessage();
            mStatusLbl.setText(msg);
            mStatusLbl.setIcon(GuiConstants.ERROR_ICON);
            throw new PropertyVetoException(msg, evt);
        }
        // everything looks good, window close
    }
    
    public void setValue() {
        Plan plan = (Plan)mProperty.getNode().getDoc();
        Schema newSchema = null;
        try {
            // name
            mNamePanel.store();
            
            // schema
            if (mIsSchemaOwner) {
                String newSchemaName = mOutputSchemaNamePanel.getStringValue();
                String schemaName = mComponent.getProperty(OUTPUT_SCHEMA_ID_KEY).getStringValue();
                Schema schema = plan.getSchema(schemaName);
                boolean schemaExist = schemaName != null && !schemaName.trim().equals("") && schema != null;
                List attributes = mSelectPanel.getAttributeMetadataAsList();
                if (schemaExist) {
                    if (!newSchemaName.equals(schemaName)) {
                        newSchema = ModelManager.createSchema(newSchemaName);
                        newSchema.setAttributeMetadataAsList(attributes);
                        plan.addSchema(newSchema);
                        plan.removeSchema(schemaName);
                        mOutputSchemaNamePanel.store();
                        mProperty.getNode().getView().updateTcgComponentNodeView();
                        plan.getPropertyChangeSupport().firePropertyChange("Schema Name",
                                schemaName, newSchemaName);
                        
                    } else {
                        if (!schema.hasSameAttributeMetadata(attributes)) {
                            schema.setAttributeMetadataAsList(attributes);
                            plan.getPropertyChangeSupport().firePropertyChange("Schema Column Metadata",
                                    "old", "new");
                        }
                    }
                } else {
                    newSchema = ModelManager.createSchema(newSchemaName);
                    plan.addSchema(newSchema);
                    newSchema.setAttributeMetadataAsList(attributes);
                    mOutputSchemaNamePanel.store();
                    mProperty.getNode().getView().updateTcgComponentNodeView();
                    plan.getPropertyChangeSupport().firePropertyChange("Schema", null, newSchema);
                }
                
                if (mHasExpressionColumn) {
                    // expression
                    List expList = mSelectPanel.getExpressionList();
                    mComponent.getProperty(FROM_COLUMN_LIST_KEY).setValue(expList);
                    
                    // to column list
                    List toList = mSelectPanel.getToColumnList();
                    mComponent.getProperty(TO_COLUMN_LIST_KEY).setValue(toList);
                    
                    if (mHasFromClause) {
                        // from clause
                        mFromPanel.store();
                    }
                    
                    if (mHasWhereClause) {
                        // where clause
                        mWherePanel.store();
                    }
                    
                    if (mHasGroupBy) {
                        // groupby column list
                        mGroupByPanel.store();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            NotifyHelper.reportError(e.getMessage());
        }
    }
    
}
