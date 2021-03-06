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

import org.netbeans.modules.bpel.model.api.BpelEntity;
import org.netbeans.modules.bpel.model.api.Condition;
import org.netbeans.modules.bpel.model.api.Source;
import org.netbeans.modules.bpel.model.api.support.BpelModelVisitor;
import org.netbeans.modules.bpel.model.xam.BpelElements;
import org.w3c.dom.Element;


/**
 * @author ads
 *
 */
public class SourceImpl extends TargetImpl implements Source {

    SourceImpl( BpelModelImpl model, Element e ) {
        super(model, e);
    }

    SourceImpl( BpelBuilderImpl builder ) {
        super(builder, BpelElements.SOURCE.getName() );
    }


    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.Source#getTransitionCondition()
     */
    public Condition getTransitionCondition() {
        return getChild( Condition.class );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.Source#setTransitionCondition(org.netbeans.modules.soa.model.bpel20.api.Condition)
     */
    public void setTransitionCondition( Condition condition ) {
        setChild(  condition , Condition.class );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.Source#removeTransitionCondition()
     */
    public void removeTransitionCondition() {
        removeChild( Condition.class );
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.api.BpelEntity#getElementType()
     */
    public Class<? extends BpelEntity> getElementType() {
        return Source.class;
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.impl.BpelEntityImpl#acceptThis(org.netbeans.modules.soa.model.bpel20.api.support.BpelModelVisitor)
     */
    @Override
    public void accept( BpelModelVisitor visitor ) {
        visitor.visit( (Source)this );
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.soa.model.bpel20.impl.ExtensibleElementsImpl#create(org.w3c.dom.Element)
     */
    @Override
    protected BpelEntity create( Element element ) {
        if ( BpelElements.TRANSITION_CONDITION.getName().equals( 
                element.getLocalName()))
        {
            return new ConditionImpl( getModel(), element );
        }
        return super.create(element);
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.bpel.model.impl.BpelContainerImpl#getMultiplicity(org.netbeans.modules.bpel.model.api.BpelEntity)
     */
    @Override
    protected Multiplicity getMultiplicity( BpelEntity entity ) {
        if ( getChildType( entity).equals(Condition.class ))  {
            return Multiplicity.SINGLE;
        }
        return super.getMultiplicity(entity);
    }

}
