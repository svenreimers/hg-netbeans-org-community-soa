/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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

/*
 * GradientRectangleColorScheme.java
 *
 * Created on February 11, 2007, 2:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.compapp.casaeditor.graph;

import java.awt.Color;

/**
 * A set of color schemes for use with GradientRectangleDrawer.
 *
 * @author Josh Sandusky
 */
public class GradientRectangleColorScheme {
    
    private Color mBorderColor;
    private Color mColor1;
    private Color mColor2;
    private Color mColor3;
    private Color mColor4;
    private Color mColor5;
    
    
    public GradientRectangleColorScheme(
            Color borderColor, 
            Color c1, 
            Color c2, 
            Color c3, 
            Color c4, 
            Color c5)
    {
        mBorderColor = borderColor;
        mColor1 = c1;
        mColor2 = c2;
        mColor3 = c3;
        mColor4 = c4;
        mColor5 = c5;
    }
    
    //For solid color
    public GradientRectangleColorScheme(
            Color borderColor,
            Color color){
        mBorderColor = borderColor;
        mColor1 = color;
        mColor2 = color;
        mColor3 = color;
        mColor4 = color;
        mColor5 = color;
    }
            
    
    public Color getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(Color color) {
        mBorderColor = color;
    }
    
    public Color getColor1() {
        return mColor1;
    }
    
    public Color getColor2() {
        return mColor2;
    }
    
    public Color getColor3() {
        return mColor3;
    }
    
    public Color getColor4() {
        return mColor4;
    }
    
    public Color getColor5() {
        return mColor5;
    }
    
}
