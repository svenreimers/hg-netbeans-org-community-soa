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
package org.netbeans.modules.bpel.mapper.tree.models;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.TreePath;
import javax.xml.namespace.QName;
import org.netbeans.modules.bpel.editors.api.Constants.VariableStereotype;
import org.netbeans.modules.bpel.editors.api.nodes.NodeType;
import org.netbeans.modules.bpel.mapper.tree.spi.TreeItemInfoProvider;
import org.netbeans.modules.xml.xam.Named;
import org.netbeans.modules.bpel.editors.api.utils.EditorUtil;
import org.netbeans.modules.bpel.mapper.multiview.BpelDesignContext;
import org.netbeans.modules.bpel.mapper.predicates.AbstractPredicate;
import org.netbeans.modules.bpel.mapper.cast.AbstractTypeCast;
import org.netbeans.modules.bpel.mapper.tree.actions.AddCastAction;
import org.netbeans.modules.bpel.mapper.tree.actions.AddPredicateAction;
import org.netbeans.modules.bpel.mapper.tree.actions.AddSpecialStepAction;
import org.netbeans.modules.bpel.mapper.tree.actions.DeleteCastAction;
import org.netbeans.modules.bpel.mapper.tree.actions.DeletePredicateAction;
import org.netbeans.modules.bpel.mapper.tree.actions.EditPredicateAction;
import org.netbeans.modules.bpel.model.api.AbstractVariableDeclaration;
import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.Process;
import org.netbeans.modules.bpel.model.api.Variable;
import org.netbeans.modules.bpel.mapper.tree.images.NodeIcons;
import org.netbeans.modules.bpel.mapper.tree.spi.MapperTcContext;
import org.netbeans.modules.bpel.mapper.tree.spi.RestartableIterator;
import org.netbeans.modules.bpel.model.api.VariableDeclaration;
import org.netbeans.modules.bpel.model.api.VariableDeclarationScope;
import org.netbeans.modules.soa.mappercore.Mapper;
import org.netbeans.modules.xml.schema.model.Attribute;
import org.netbeans.modules.xml.schema.model.Attribute.Use;
import org.netbeans.modules.xml.schema.model.Element;
import org.netbeans.modules.xml.schema.model.ElementReference;
import org.netbeans.modules.xml.schema.model.GlobalAttribute;
import org.netbeans.modules.xml.schema.model.GlobalElement;
import org.netbeans.modules.xml.schema.model.GlobalSimpleType;
import org.netbeans.modules.xml.schema.model.GlobalType;
import org.netbeans.modules.xml.schema.model.LocalAttribute;
import org.netbeans.modules.xml.schema.model.LocalElement;
import org.netbeans.modules.xml.schema.model.SchemaComponent;
import org.netbeans.modules.xml.schema.model.TypeContainer;
import org.netbeans.modules.xml.wsdl.model.Part;
import org.netbeans.modules.xml.xam.dom.NamedComponentReference;
import org.netbeans.modules.xml.xpath.ext.LocationStep;
import org.netbeans.modules.xml.xpath.ext.StepNodeTestType;
import org.netbeans.modules.xml.xpath.ext.XPathSchemaContext;
import org.netbeans.modules.xml.xpath.ext.XPathSchemaContextHolder;
import org.netbeans.modules.xml.xpath.ext.XPathUtils;
import org.openide.util.NbBundle;

/**
 * The implementation of the TreeItemInfoProvider for the variables' tree.
 * 
 * @author nk160297
 */
public class VariableTreeInfoProvider implements TreeItemInfoProvider {

    private static VariableTreeInfoProvider singleton = new VariableTreeInfoProvider();
    
    public static VariableTreeInfoProvider getInstance() {
        return singleton;
    }

    public static GlobalType getGlobalType(Object treeItem) {
        assert treeItem != null;
        SchemaComponent targetSComp = null;
        //
        if (treeItem == null) {
            return null;
        } else if (treeItem instanceof SchemaComponent) {
            targetSComp = (SchemaComponent)treeItem;
        } else if (treeItem instanceof XPathSchemaContextHolder) {
            targetSComp = XPathSchemaContext.Utilities.getSchemaComp(
                    ((XPathSchemaContextHolder)treeItem).getSchemaContext());
        } else if (treeItem instanceof VariableDeclarationScope) {
            return null;
        } else if (treeItem instanceof VariableDeclaration) {
            targetSComp = EditorUtil.getVariableSchemaType((VariableDeclaration)treeItem);
        } else if (treeItem instanceof Part) {
            targetSComp = EditorUtil.getPartType((Part)treeItem);
        } else if (treeItem instanceof XPathSchemaContextHolder) {
            XPathSchemaContext sContext = 
                    ((XPathSchemaContextHolder)treeItem).getSchemaContext();
            if (sContext != null) {
                targetSComp = XPathSchemaContext.Utilities.getSchemaComp(sContext);
            }
        }
        // 
        if (targetSComp == null) {
            return null;
        }
        //
        GlobalType gType = getGlobalType(targetSComp);
        return gType;
    }
    
    public static GlobalType getGlobalType(SchemaComponent sComp) {
        if (sComp == null) {
            return null;
        }
        //
        GlobalType gType = null;
        //
        if (sComp instanceof GlobalType) {
            gType = (GlobalType)sComp;
        } else if (sComp instanceof TypeContainer) {
            TypeContainer typeContainer = (TypeContainer)sComp;
            NamedComponentReference<? extends GlobalType> typeRef = 
                    typeContainer.getType();
            if (typeRef != null) {
                gType = typeRef.get();
            }
        } else {
            if (sComp instanceof LocalAttribute) {
                NamedComponentReference<GlobalSimpleType> gTypeRef = 
                        ((LocalAttribute)sComp).getType();
                if (gTypeRef != null) {
                    gType = gTypeRef.get();
                }
            } else if (sComp instanceof GlobalAttribute) {
                NamedComponentReference<GlobalSimpleType> gTypeRef = 
                        ((GlobalAttribute)sComp).getType();
                if (gTypeRef != null) {
                    gType = gTypeRef.get();
                }
            }
        }
        //
        return gType;
    }
    
    public String getDisplayName(Object treeItem) {
        if (treeItem instanceof Process) {
            return NodeType.VARIABLE_CONTAINER.getDisplayName();
        } 
        if (treeItem instanceof ElementReference) {
            NamedComponentReference<GlobalElement> elementRef = 
                    ((ElementReference)treeItem).getRef();
            QName qName = elementRef.getQName();
            return qName.getLocalPart();
        }
        if (treeItem instanceof Named) {
            return ((Named)treeItem).getName();
        }
        if (treeItem instanceof AbstractPredicate) {
            return ((AbstractPredicate)treeItem).getDisplayName();
        }
        if (treeItem instanceof BpelEntity) {
            if (treeItem instanceof Variable) {
                return ((Variable)treeItem).getVariableName();
            }
            //
            Class<? extends BpelEntity> bpelInterface = 
                    ((BpelEntity)treeItem).getElementType();
            NodeType nodeType = EditorUtil.getBasicNodeType(bpelInterface);
            if (nodeType != null && nodeType != NodeType.UNKNOWN_TYPE) {
                return nodeType.getDisplayName();
            }
        }
        if (treeItem instanceof AbstractVariableDeclaration) {
            return ((AbstractVariableDeclaration)treeItem).getVariableName();
        }
        //
        if (treeItem instanceof AbstractTypeCast) {
            Object castableObject = ((AbstractTypeCast)treeItem).getCastedObject();
            GlobalType gType = ((AbstractTypeCast)treeItem).getCastTo();
            return "(" + gType + ")" + getDisplayName(castableObject);
        }
        //
        return null;
    }

    public Icon getIcon(Object treeItem) {
        if (treeItem instanceof BpelEntity) {
            if (treeItem instanceof Variable) {
                Variable var = (Variable)treeItem;
                VariableStereotype vst = EditorUtil.getVariableStereotype(var);
                Image img = NodeType.VARIABLE.getImage(vst);
                return new ImageIcon(img);
            }
            //
            if (treeItem instanceof Process) {
                return NodeType.VARIABLE_CONTAINER.getIcon();
            }
            //
            Class<? extends BpelEntity> bpelInterface = 
                    ((BpelEntity)treeItem).getElementType();
            NodeType nodeType = EditorUtil.getBasicNodeType(bpelInterface);
            if (nodeType != null && nodeType != NodeType.UNKNOWN_TYPE) {
                Icon icon = nodeType.getIcon();
                if (icon != null) {
                    return icon;
                }
            }
        }
        //
        if (treeItem instanceof AbstractVariableDeclaration) {
            AbstractVariableDeclaration var = (AbstractVariableDeclaration)treeItem;
            VariableStereotype vst = EditorUtil.getVariableStereotype(var);
            Image img = NodeType.VARIABLE.getImage(vst);
            return new ImageIcon(img);
        }
        //
        if (treeItem instanceof SchemaComponent) {
            if (treeItem instanceof Element) {
                Element element = (Element)treeItem;
                boolean isOptional = false;
                boolean isRepeating = false;
                String maxOccoursStr = null;
                if (element instanceof GlobalElement) {
                    return NodeIcons.ELEMENT.getIcon();
                } else if (element instanceof LocalElement) {
                    LocalElement lElement = (LocalElement)element;
                    isOptional = lElement.getMinOccursEffective() < 1;
                    //
                    maxOccoursStr = lElement.getMaxOccursEffective();
                } else if (element instanceof ElementReference) {
                    ElementReference elementRef = (ElementReference)element;
                    isOptional = elementRef.getMinOccursEffective() < 1;  
                    //
                    maxOccoursStr = elementRef.getMaxOccursEffective();
                }
                //
                if (maxOccoursStr != null) {
                    try {
                        int maxOccoursInt = Integer.parseInt(maxOccoursStr);
                        isRepeating = maxOccoursInt > 1;  
                    } catch (NumberFormatException ex) {
                        // Do Nothing
                        isRepeating = true;
                    }
                }
                //
                if (isOptional) {
                    if (isRepeating) {
                        return NodeIcons.ELEMENT_OPTIONAL_REPEATING.getIcon();
                    } else {
                        return NodeIcons.ELEMENT_OPTIONAL.getIcon();
                    }
                } else {
                    if (isRepeating) {
                        return NodeIcons.ELEMENT_REPEATING.getIcon();
                    } else {
                        return NodeIcons.ELEMENT.getIcon();
                    }
                }
            } 
            //
            if (treeItem instanceof Attribute) {
                 Attribute attribute = (Attribute)treeItem;
                if (attribute instanceof LocalAttribute) {
                    Use use = ((LocalAttribute)attribute).getUseEffective();
                    if (use == Use.OPTIONAL) {
                        return NodeIcons.ATTRIBUTE_OPTIONAL.getIcon();
                    } else {
                        return NodeIcons.ATTRIBUTE.getIcon();
                    }
                } else {
                    return NodeIcons.ATTRIBUTE.getIcon();
                }
            } 
            //
            if (treeItem instanceof GlobalType) {
                return NodeIcons.UNKNOWN_IMAGE;
            } 
        } 
        //
        if (treeItem instanceof Part) {
            return NodeType.MESSAGE_PART.getIcon();
        }
        //
        if (treeItem instanceof AbstractPredicate) {
            SchemaComponent sComp = 
                    ((AbstractPredicate)treeItem).getSComponent();
            return getIcon(sComp);
        }
        //
        if (treeItem instanceof AbstractTypeCast) {
            Object castableObject = ((AbstractTypeCast)treeItem).getCastedObject();
            return getIcon(castableObject);
        }
        //
        return null;
    }

    private void populateActionsList(Object treeItem, List<Action> result, 
            MapperTcContext mapperTcContext, 
            boolean inLeftTree, TreePath treePath, 
            RestartableIterator<Object> dataObjectPathItr) {
        //
        boolean isProcessed = false;
        if (treeItem instanceof SchemaComponent) {
            // if (BpelMapperUtils.isRepeating((SchemaComponent)treeItem)) {
            Action action = new AddPredicateAction(
                    mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
            result.add(action);
            //
            // Only for components which have any children!
            boolean notLeaf = XPathUtils.hasSubcomponents((SchemaComponent)treeItem);
            if (notLeaf) {
                addSpecialStepActions(result, mapperTcContext, inLeftTree, 
                        treePath, dataObjectPathItr);
            }
            isProcessed = true;
        } else if (treeItem instanceof AbstractPredicate) {
            Action action = new EditPredicateAction(
                    mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
            result.add(action);
            //
            action = new DeletePredicateAction(
                    mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
            result.add(action);
            isProcessed = true;
        } else if (treeItem instanceof AbstractTypeCast) {
            Action action = new DeleteCastAction(
                    mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
            result.add(action);
            //
            Object castableObject = ((AbstractTypeCast)treeItem).getCastedObject();
            populateActionsList(castableObject, result, 
                    mapperTcContext, inLeftTree, treePath, 
                    dataObjectPathItr);

            
            isProcessed = true;
        }
        //
        if (!isProcessed) {
            // If the tree item is a variable or a part then use its schema type!
            SchemaComponent sComp = null;
            if (treeItem instanceof VariableDeclarationScope) {
                // nothing to add
            } else if (treeItem instanceof VariableDeclarationWrapper) {
                VariableDeclaration varDecl = 
                        ((VariableDeclarationWrapper)treeItem).getDelegate();
                sComp = EditorUtil.getVariableSchemaType(varDecl);
            } else if (treeItem instanceof VariableDeclaration) {
                sComp = EditorUtil.getVariableSchemaType((VariableDeclaration)treeItem);
            } else if (treeItem instanceof Part) {
                Part part = (Part)treeItem;
                sComp = EditorUtil.getPartType(part);
            } 
            if (sComp != null) {
                //
                // Only for components which have any children!
                boolean notLeaf = XPathUtils.hasSubcomponents((SchemaComponent)sComp);
                if (notLeaf) {
                    addSpecialStepActions(result, mapperTcContext, inLeftTree, 
                            treePath, dataObjectPathItr);
                }
            } 
        }
    }
    
    public List<Action> getMenuActions(MapperTcContext mapperTcContext, 
            boolean inLeftTree, TreePath treePath, 
            RestartableIterator<Object> dataObjectPathItr) {
        //
        dataObjectPathItr.restart();
        Object treeItem = dataObjectPathItr.next();
        //
        Mapper mapper = mapperTcContext.getMapper();
        BpelDesignContext context = mapperTcContext.
                getDesignContextController().getContext();
        if (mapper == null || context == null) {
            return Collections.EMPTY_LIST;
        }
        //
        List<Action> result = new ArrayList<Action>();
        //
        if (!(treeItem instanceof LocationStep || 
                treeItem instanceof AbstractPredicate || 
                treeItem instanceof AbstractTypeCast)) {
            GlobalType gType = getGlobalType(treeItem);
            if (gType != null) {
                Action action = new AddCastAction(gType, mapperTcContext, 
                        inLeftTree, treePath, dataObjectPathItr);
                result.add(action);
            }
        }
        //
        populateActionsList(treeItem, result, mapperTcContext, inLeftTree, 
                treePath, dataObjectPathItr);
        //
        return result;
//        if (result.isEmpty()) {
//            return null;
//        } else {
//            return result;
//        }
    }
    
    private void addSpecialStepActions(List<Action> result, 
            MapperTcContext mapperTcContext, 
            boolean inLeftTree, TreePath treePath, 
            RestartableIterator<Object> dataObjectPathItr) {
        Action action;
        action = new AddSpecialStepAction(StepNodeTestType.NODETYPE_NODE, 
                mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
        result.add(action);
        //
        action = new AddSpecialStepAction(StepNodeTestType.NODETYPE_TEXT, 
                mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
        result.add(action);
        //
        action = new AddSpecialStepAction(StepNodeTestType.NODETYPE_COMMENT, 
                mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
        result.add(action);
        //
        action = new AddSpecialStepAction(StepNodeTestType.NODETYPE_PI, 
                mapperTcContext, inLeftTree, treePath, dataObjectPathItr);
        result.add(action);
    }

    
    public String getToolTipText(Object treeItem) {
        if (treeItem instanceof GlobalElement) {
            if (((GlobalElement) treeItem).getType() != null) {
                return ((GlobalElement) treeItem).getType().getRefString();
            }
        }
        
        if (treeItem instanceof Part) {
            if (((Part) treeItem).getType() != null ) {
                return ((Part) treeItem).getType().getRefString() ;
            }
            if (((Part) treeItem).getElement() != null) {
                return ((Part) treeItem).getElement().getRefString() ;
            }
        }
        if (treeItem instanceof LocalElement) {
            if (((LocalElement)treeItem).getType() != null)
            {
                return ((LocalElement)treeItem).getType().getRefString();
            }
        }
        
        if (treeItem instanceof LocalAttribute) {
            if (((LocalAttribute) treeItem).getType() != null) {
                return ((LocalAttribute) treeItem).getType().getRefString();
            }
        }
        
        if (treeItem instanceof GlobalAttribute) {
            
            if (((GlobalAttribute)treeItem).getType() != null) {
                return ((GlobalAttribute)treeItem).getType().getRefString();
            }
        }
        
        if (treeItem instanceof GlobalType) {
            return ((GlobalType)treeItem).getName();
        }
        
        if (treeItem instanceof Variable) {
            if (((Variable) treeItem).getMessageType() != null) {
                return ((Variable) treeItem).getMessageType().getRefString();
            }
            if (((Variable) treeItem).getType() != null) {
                return ((Variable) treeItem).getType().getRefString();
            }
            if (((Variable) treeItem).getElementType() != null) {
                return ((Variable) treeItem).getElementType().getName();
            }
        }    
        
        if (treeItem instanceof AbstractTypeCast) {
            Object castableObject = ((AbstractTypeCast)treeItem).getCastedObject();
            String baseTooltip = getToolTipText(castableObject);
            GlobalType gType = ((AbstractTypeCast)treeItem).getCastTo();
            String castedToLbl = NbBundle.getMessage(
                    VariableTreeInfoProvider.class, "CASTED_TO"); // NOI18N
            return baseTooltip + " " + castedToLbl + " " + gType.getName();
        }
        //
        String notNamedTypeLbl = NbBundle.getMessage(
                VariableTreeInfoProvider.class, "NOT_NAMED_TYPE"); // NOI18N
        return notNamedTypeLbl;
    }
}
