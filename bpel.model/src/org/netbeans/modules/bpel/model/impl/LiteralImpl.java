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

/**
 *
 */
package org.netbeans.modules.bpel.model.impl;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.Literal;
import org.netbeans.modules.bpel.model.api.events.VetoException;
import org.netbeans.modules.bpel.model.api.support.BpelModelVisitor;
import org.netbeans.modules.bpel.model.xam.BpelAttributes;
import org.netbeans.modules.bpel.model.xam.BpelElements;
import org.netbeans.modules.xml.xam.dom.Attribute;
import org.w3c.dom.Element;


/**
 * @author ads
 * @author Vitaly Bychkov
 *
 */
public class LiteralImpl extends BpelContainerImpl implements Literal {

    LiteralImpl( BpelModelImpl model, Element e ) {
        super(model, e);
    }

    LiteralImpl( BpelBuilderImpl builder ) {
        super(builder, BpelElements.LITERAL.getName() );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.BpelEntity#getElementType()
     */
    public Class<? extends BpelEntity> getElementType() {
        return Literal.class;
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.ContentElement#getContent()
     */
    public String getContent() {
        return getCorrectedText();
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.ContentElement#setContent(java.lang.String)
     */
    public void setContent( String content ) throws VetoException {
        setText( content );
    }
    

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.impl.BpelEntityImpl#acceptThis(org.netbeans.modules.soa.model.bpel20.api.support.BpelModelVisitor)
     */
    public void accept( BpelModelVisitor visitor ){
        visitor.visit( this );
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.impl.BpelEntityImpl#getDomainAttributes()
     */
    protected Attribute[] getDomainAttributes() {
        if ( myAttributes.get() == null ){
            Attribute[] ret = new Attribute[]{ BpelAttributes.CONTENT };
            myAttributes.compareAndSet( null, ret);
        }
        return myAttributes.get();
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.impl.BpelContainerImpl#create(org.w3c.dom.Element)
     */
    @Override
    protected BpelEntity create( Element element )
    {
        return null;
    } 
       
    public String getXmlContent() {
        return getCorrectedXmlContent();
    }

    public void setXmlContent(String xmlContent) throws VetoException, IOException {
        getAttributeAccess().setXmlContent(xmlContent);
    }

    private static AtomicReference<Attribute[]> myAttributes  = 
        new AtomicReference<Attribute[]>();

    public String getCDataContent() {
        return getCorrectedCDataContent();
    }

    public void setCDataContent(String content) throws VetoException, IOException {
        getAttributeAccess().setCDataContent(content);
    }

}
