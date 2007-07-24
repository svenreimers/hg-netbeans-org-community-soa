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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */
package org.netbeans.modules.iep.model.impl;

import org.netbeans.modules.iep.model.Component;
import org.netbeans.modules.iep.model.IEPComponent;
import org.netbeans.modules.iep.model.IEPModel;
import org.netbeans.modules.iep.model.IEPQNames;
import org.netbeans.modules.iep.model.IEPVisitor;
import org.netbeans.modules.xml.wsdl.model.spi.GenericExtensibilityElement.StringAttribute;
import org.w3c.dom.Element;

/**
 *
 * 
 */
public class ComponentImpl extends IEPComponentBase implements Component {

    public ComponentImpl(IEPModel model,  Element e) {
    	super(model, e);
    }

    public ComponentImpl(IEPModel model) {
        this(model, createNewElement(IEPQNames.COMPONENT.getQName(), model));
    }

    public void accept(IEPVisitor visitor) {
    	visitor.visitComponent(this);
    }

    public IEPComponent createChild(Element childEl) {
        IEPComponent child = null;
        
        if (childEl != null) {
            String localName = childEl.getLocalName();
            if (localName == null || localName.length() == 0) {
                    localName = childEl.getTagName();
            }
            if (localName.equals(COMPONENT_CHILD)) {
                    child = new ComponentImpl(getModel(), childEl);
            } else if (localName.equals(PROPERTY_CHILD)) {
                    child = new PropertyImpl(getModel(), childEl);
            }
        }
        
        return child;
    }

    public String getName() {
        return getAttribute(ATTR_NAME);
    }

    public void setName(String name) {
        setAttribute(NAME_PROPERTY, ATTR_NAME, name);
    }

    public String getTitle() {
        return getAttribute(ATTR_TITLE);
    }

    public void setTitle(String title) {
        setAttribute(TITLE_PROPERTY, ATTR_TITLE, title);
    }

    public String getType() {
        return getAttribute(ATTR_TYPE);
    }

    public void setType(String type) {
        setAttribute(TYPE_PROPERTY, ATTR_TYPE, type);
    }

    
}
