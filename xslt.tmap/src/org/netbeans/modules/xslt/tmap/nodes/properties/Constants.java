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
package org.netbeans.modules.xslt.tmap.nodes.properties;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.modules.xml.schema.model.GlobalComplexType;
import org.netbeans.modules.xml.schema.model.GlobalElement;
import org.netbeans.modules.xml.schema.model.GlobalSimpleType;
import org.netbeans.modules.xml.schema.model.GlobalType;
import org.netbeans.modules.xml.schema.model.SchemaComponent;
import org.netbeans.modules.xml.schema.model.SchemaModelFactory;
import org.netbeans.modules.xml.wsdl.model.Message;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 * Keeps constants for identification of the TMap Diagram elements and theirs properties.
 * @author Vitaly Bychkov
 * @author nk160297
 */
public interface Constants{
    
//    StereotypeFilter CORRELATION_PROPERTY_STEREO_TYPE_FILTER 
//            = new StereotypeFilter(/*VariableStereotype.GLOBAL_TYPE
//                        , VariableStereotype.GLOBAL_SIMPLE_TYPE
//                        , VariableStereotype.GLOBAL_COMPLEX_TYPE
//                        , VariableStereotype.PRIMITIVE_TYPE
//                        , VariableStereotype.GLOBAL_ELEMENT*/
//                        VariableStereotype.PRIMITIVE_TYPE
//            );
    
    
    enum PropertiesGroups {
        MAIN_SET,
        MESSAGE_SET,
        FAULT_SET, 
        EXPERT_SET;
        
        private String myDisplayName;
        
        public String getDisplayName() {
            if (myDisplayName == null) {
                myDisplayName = NbBundle.getMessage(
                        PropertyType.class, this.toString());
            }
            return myDisplayName;
        }
    }
    
    enum MessageDirection {
        INPUT, 
        OUTPUT, 
        FAULT;
    }
    
    enum VariableStereotype {
        GLOBAL_TYPE,
        GLOBAL_SIMPLE_TYPE,
        GLOBAL_COMPLEX_TYPE,
        PRIMITIVE_TYPE,
        MESSAGE,
        GLOBAL_ELEMENT;
        
        private String myDisplayName;
        
        public String getDisplayName() {
            if (myDisplayName == null) {
                myDisplayName = NbBundle.getMessage(
                        PropertyType.class, this.toString());
            }
            return myDisplayName;
        }
        
        public static VariableStereotype recognizeStereotype(Object type) {
            if (type == null) {
                return null;
            }
            if (type instanceof Message) {
                return VariableStereotype.MESSAGE;
            } else if (type instanceof GlobalType) {
                if (type instanceof GlobalSimpleType) {
                    if (((GlobalSimpleType)type).getModel() == 
                            SchemaModelFactory.getDefault().
                            getPrimitiveTypesModel()) {
                        return PRIMITIVE_TYPE;
                    } else {
                        return GLOBAL_SIMPLE_TYPE;
                    }
                } else if (type instanceof GlobalComplexType) {
                    return GLOBAL_COMPLEX_TYPE;
                } else {
                    return VariableStereotype.GLOBAL_TYPE;
                }
            } else if (type instanceof GlobalElement) {
                return VariableStereotype.GLOBAL_ELEMENT;
            }
            assert false : "type paramether can be one of the following types" +
                    "(or subtype of them): Message, Part, GlobalType, GlobalElement";
            return null;
        }
    }
    
}
