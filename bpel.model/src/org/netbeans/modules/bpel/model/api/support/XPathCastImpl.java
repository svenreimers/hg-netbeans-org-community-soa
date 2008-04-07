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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
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
package org.netbeans.modules.bpel.model.api.support;

import org.netbeans.modules.xml.schema.model.GlobalType;
import org.netbeans.modules.xml.xpath.ext.spi.XPathCast;
import org.netbeans.modules.bpel.model.api.references.SchemaReference;
import org.netbeans.modules.bpel.model.ext.editor.api.Cast;
import org.netbeans.modules.xml.xpath.ext.XPathException;
import org.netbeans.modules.xml.xpath.ext.XPathExpression;
import org.netbeans.modules.xml.xpath.ext.XPathModel;
import org.openide.ErrorManager;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2008.03.27
 */
public class XPathCastImpl implements XPathCast {

    public static XPathExpression getExpression(Cast cast) {
        String pathText = cast.getPath();
        XPathModel xPathModel = BpelXPathModelFactory.create(cast);
        XPathExpression xPathExpr = null;
        try {
            xPathExpr = xPathModel.parseExpression(pathText);
        } catch (XPathException ex) {
            ErrorManager.getDefault().log(ErrorManager.WARNING,
                    "Unresolved XPath: " + pathText); //NOI18N

        }
        return xPathExpr;
    }

    public static XPathCastImpl convert(Cast cast) {
        SchemaReference<GlobalType> ref = cast.getType();
        if (ref == null) {
            return null;
        }
        GlobalType castTo = ref.get();
        String pathText = cast.getPath();
        //
        if (cast == null || pathText == null || pathText.length() == 0) {
            return null;
        }
        XPathCastImpl result = new XPathCastImpl();
        result.mCast = cast;
        result.myPathText = pathText;
        result.mCastTo = castTo;
        //
        return result;
    }
    
    private XPathCastImpl() {
    }
    
    public XPathCastImpl(XPathExpression castWhat, GlobalType castTo) {
        mXPathExpression = castWhat;
        mCastTo = castTo;
    }
    
    public String getPathText() {
        return myPathText;
    }

    public GlobalType getCastTo() {
        return mCastTo;
    }

    public XPathExpression getPath() {
        if (mXPathExpression == null) {
            mXPathExpression = getExpression(mCast);
        }
        return mXPathExpression;
    }

    @Override
    public String toString() {
        return "(" + mCastTo.toString() + ")" + mXPathExpression.getExpressionString();
    }
    
    private Cast mCast;
    private String myPathText;
    private GlobalType mCastTo;
    private XPathExpression mXPathExpression;
}
