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
package org.netbeans.modules.wsdlextensions.ldap.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import org.netbeans.modules.xml.wsdl.model.WSDLComponent;
import org.netbeans.modules.wsdlextensions.ldap.LDAPQName;
import org.netbeans.modules.xml.wsdl.model.spi.ElementFactory;
import org.w3c.dom.Element;

/**
 ** @author 
 */
public class LDAPElementFactoryProvider {

    public static class BindingFactory extends ElementFactory {

        public Set<QName> getElementQNames() {
            return Collections.singleton(LDAPQName.BINDING.getQName());
        }

        public WSDLComponent create(WSDLComponent context, Element element) {
            return new LDAPBindingImpl(context.getModel(), element);
        }
    }

    public static class AddressFactory extends ElementFactory {

        public Set<QName> getElementQNames() {
            return Collections.singleton(LDAPQName.ADDRESS.getQName());
        }

        public WSDLComponent create(WSDLComponent context, Element element) {
            return new LDAPAddressImpl(context.getModel(), element);
        }
    }

    public static class OperationFactory extends ElementFactory {

        public Set<QName> getElementQNames() {
            return Collections.singleton(LDAPQName.OPERATION.getQName());
        }

        public WSDLComponent create(WSDLComponent context, Element element) {
            return new LDAPOperationImpl(context.getModel(), element);
        }
    }

    public static class InputFactory extends ElementFactory {

        public Set<QName> getElementQNames() {
            return Collections.singleton(LDAPQName.INPUT.getQName());
        }

        public WSDLComponent create(WSDLComponent context, Element element) {
            return new LDAPOperationInputImpl(context.getModel(), element);
        }
    }

    public static class OutputFactory extends ElementFactory {

        public Set<QName> getElementQNames() {
            return Collections.singleton(LDAPQName.OUTPUT.getQName());
        }

        public WSDLComponent create(WSDLComponent context, Element element) {
            return new LDAPOperationOutputImpl(context.getModel(), element);
        }
    }
}
