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
package org.netbeans.modules.wsdlextensions.jms;

import org.netbeans.modules.xml.wsdl.model.ExtensibilityElement;

/**
 * JMSComponent
 */
public interface JMSComponent extends ExtensibilityElement {

    
    public interface Visitor {
        void visit(JMSAddress target);
        void visit(JMSJNDIEnv target);
        void visit(JMSJNDIEnvEntry target);
        void visit(JMSJCAOptions target);
        void visit(JMSBinding target);
        void visit(JMSOperation target);
        void visit(JMSOptions target);
        void visit(JMSOption target);
        void visit(JMSMessage target);
        void visit(JMSProperties target);
        void visit(JMSProperty target);
        void visit(JMSMapMessage target);
        void visit(JMSMapMessagePart target);
    }
    
    void accept(Visitor visitor);
}
