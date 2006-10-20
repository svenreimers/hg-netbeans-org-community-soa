/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.

 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.bpel.design.decoration;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import org.netbeans.modules.bpel.design.GUtils;


public class StrokeDescriptor implements Descriptor {

    private Color color;

    private double width;
    private double dash;

    private boolean dashed;


    public StrokeDescriptor(Color color, double width) {
        this.color = color;
        this.width = width;
        this.dashed = false;
    }
    
    
    public StrokeDescriptor(Color color, double width, double dash) {
        this.color = color;
        this.width = width;
        this.dash = dash;
        this.dashed = true;
    }
    
    
    public void paint(Graphics2D g2, Shape shape) {
        Paint oldPaint = g2.getPaint();
        Stroke oldStroke = g2.getStroke();
        
        if (dashed) {
            GUtils.setDashedStroke(g2, width, dash);
        } else {
            GUtils.setSolidStroke(g2, width);
        }
        
        GUtils.setPaint(g2, color);
        GUtils.draw(g2, shape, true);
        
        g2.setStroke(oldStroke);
        g2.setPaint(oldPaint);
    }
}
