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


package org.netbeans.modules.iep.editor.designer;

import com.nwoods.jgo.DomDoc;
import com.nwoods.jgo.DomElement;
import com.nwoods.jgo.DomNode;
import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import java.awt.Rectangle;

/**
 * SimpleNodePort has been implemented as a port of StyleTriangle,
 * assuming ports are only on the left and right sides of a SimpleNode.
 * <p>
 * SimpleNodePort also has some smarts about the kinds of links
 * that can be made to this kind of port.
 * <p>
 * You can easily change the appearance of the port by calling
 * setPortStyle(JGoPort.StyleObject) and then setPortObject to
 * change the object used to represent the port.
 * For example you may want to use an instance of JGoImage.
 * These objects can be shared by more than one JGoPort.
 */
public class SimpleNodePort extends JGoPort {
    /** This creates a StyleEllipse port.  Call initialize() before using. */
    public SimpleNodePort() {
        super();
    }
    
    /** This creates a light gray StyleTriangle port of the appropriate direction. */
    public SimpleNodePort(boolean input, JGoArea parent) {
        super(triangleRect());
        initialize(input, parent);
    }

    public void initialize(boolean input, JGoArea parent) {
        setSelectable(false);
        setDraggable(false);
        setResizable(false);
        setVisible(true);
        // assume an outlined light gray triangle
        setStyle(StyleTriangle);
        setPen(JGoPen.darkGray);
        setBrush(JGoBrush.lightGray);
        // assume inputs are on the left, outputs are on the right
        if (input) {
              setValidSource(false);
              setValidDestination(true);
              setToSpot(LeftCenter);
        } else {
              setValidSource(true);
              setValidDestination(false);
              setFromSpot(RightCenter);
        }
        // put in the SimpleNode area
        setTopLeft(parent.getLeft(), parent.getTop());
        parent.addObjectAtTail(this);
    }

    // ports remember whether they are inputs or outputs
    public final boolean isInput() {
        return isValidDestination();
    }
    
    public final boolean isOutput() {
        return isValidSource();
    }
    
    // Only allow links from output ports to input ports,
    // only between different nodes, and
    // only if there isn't already a link to "to".
    public boolean validLink(JGoPort to)  {
        return (super.validLink(to) &&
            isOutput() &&
            (to instanceof SimpleNodePort) &&
            ((SimpleNodePort)to).isInput());
    }
    
    /**
    * A convenience method for returning the parent as a SimpleNode.
    */
    public SimpleNode getNode() { 
        return (SimpleNode)getParent(); 
    }
    
    
    
    // return the bounding rectangle for the triangle-shaped port
    static public Rectangle triangleRect() {
        return myTriangleRect;
    }
    
    public void SVGWriteObject(DomDoc svgDoc, DomElement jGoElementGroup) {
        // Add <SimpleNodePort> element
        if (svgDoc.JGoXMLOutputEnabled()) {
            svgDoc.createJGoClassElement("org.netbeans.modules.iep.editor.designer.SimpleNodePort", jGoElementGroup);
        }
        
        // Have superclass add to the JGoObject group
        super.SVGWriteObject(svgDoc, jGoElementGroup);
    }
    
    public DomNode SVGReadObject(DomDoc svgDoc, JGoDocument jGoDoc, 
        DomElement svgElement, DomElement jGoChildElement)
    {
        if (jGoChildElement != null) {
          // This is a <SimpleNodePort> element
            super.SVGReadObject(svgDoc, jGoDoc, svgElement, jGoChildElement.getNextSiblingElement());
        }
        return svgElement.getNextSibling();
    }
    
    
   /**
    * Called whenever a link has been added or removed (either normal kind of add/remove 
    * or undo/redo kind of add/remove) from this port.
    * Override JGoPort's linkChange().
    *
    * Call parent node's updateDownstreamNodes() 
    * when this port is a valid destination and 
    * parent node is an EntityNode
    */
    public void linkChange() {
        SimpleNode node = getNode();
        if (this.isValidDestination() && node instanceof EntityNode) {
            ((EntityNode)node).updateDownstreamNodes();
        }
    }
    
    static private Rectangle myTriangleRect = new Rectangle(0, 0, 8, 8);
}

