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
package org.netbeans.modules.xslt.model.impl;

import org.netbeans.modules.xslt.model.Attribute;
import org.netbeans.modules.xslt.model.AttributeValueTemplate;
import org.netbeans.modules.xslt.model.XslComponent;
import org.netbeans.modules.xslt.model.XslVisitor;
import org.netbeans.modules.xslt.model.enums.Validation;
import org.w3c.dom.Element;


/**
 * @author ads
 *
 */
class AttributeImpl extends  TypeableNameableSeqElCtor implements Attribute 
{

    AttributeImpl( XslModelImpl model, Element element ) {
        super( model , element );
    }
    
    AttributeImpl( XslModelImpl model ) {
        super( model , XslElements.ATTRIBUTE );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.xslt.model.impl.XslComponentImpl#accept(org.netbeans.modules.xslt.model.XslVisitor)
     */
    @Override
    public void accept( XslVisitor visitor )
    {
        visitor.visit( this );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.xslt.model.impl.XslComponentImpl#getComponentType()
     */
    @Override
    public Class<? extends XslComponent> getComponentType()
    {
        return Attribute.class;
    }


    /* (non-Javadoc)
     * @see org.netbeans.modules.xslt.model.ValidationSpec#getValidation()
     */
    public Validation getValidation() {
        return Validation.forString( getAttribute( XslAttributes.VALIDATION ));
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.xslt.model.ValidationSpec#setValidation(org.netbeans.modules.xslt.model.enums.Validation)
     */
    public void setValidation( Validation validation ) {
        setAttribute( XslAttributes.VALIDATION , validation );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.xslt.model.Attribute#getSeparator()
     */
    public AttributeValueTemplate getSeparator() {
        return AttributeValueTemplateImpl.creatAttributeValueTemplate(
                this , XslAttributes.SEPARATOR );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.xslt.model.Attribute#setSeparator(org.netbeans.modules.xslt.model.AttributeValueTemplate)
     */
    public void setSeparator( AttributeValueTemplate avt ) {
        setAttribute( XslAttributes.SEPARATOR, avt );
    }

}
