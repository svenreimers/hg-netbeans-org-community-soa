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

package org.netbeans.modules.bpel.debugger.api;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;

import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.modules.bpel.debugger.api.psm.ProcessStaticModel;
import org.netbeans.modules.bpel.debugger.spi.EditorContext;

/**
 * @author Alexander Zgursky
 * @author Vladimir Yaroslavskiy
 */
public final class EditorContextBridge {

    private static EditorContext context;
    
    private EditorContextBridge() {};

    public static String normalizeXpath(String xpath) {
        if (xpath == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer(200);
        StringTokenizer tokenizer = new StringTokenizer(xpath, "/");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.length() > 0) {
                String noPrefix = token.substring(token.indexOf(':') + 1);
                buffer.append('/').append(ProcessStaticModel.BPEL_NAMESPACE_PREFIX).append(':');
                if (noPrefix.equals("process[1]") || noPrefix.equals("process")) {
                    buffer.append("process");
                } else {
                    buffer.append(noPrefix);
                    if (noPrefix.charAt(noPrefix.length()-1) != ']') {
                        buffer.append("[1]");
                    }
                }
            }
        }
        
        return buffer.toString();
    }
    
    /**
     * Opens given file (url) in the editor and navigates to the given position.
     * Method delegates this call to all registered EditorContext
     * implementations and returns <code>true</code> if any of them succeeded.
     *
     * @param url full path to the source file to show
     * @param position position to navigate to
     *
     * @return <code>true</code> if any of the registered EditorContext
     *         implementations succeeded to show the source.
     */
    public static boolean showSource(String url, String xpath) {
        return getContext().showSource(url, xpath);
    }
    
    /**
     * Annotates the given position in the given file (url) with the given
     * annotation type.
     *
     * @param url full path to the source file to add annotation for
     * @param position annotation position
     * @param annotationType annotation type
     *
     * @return a reference to the created annotation object. This object should
     *         be supplied as a parameter to subsequent
     *         {@link #removeAnnotation} call
     */
    public static Object annotate(
            String url, String xpath, AnnotationType annotationType)
    {
        return getContext().annotate(url, xpath, annotationType);
    }
    
    /**
     * Removes the given annotation.
     *
     * @param annotation a reference to the annotation object that is returned
     *                   from {@link #annotate} method
     */
    public static void removeAnnotation(Object annotation) {
        getContext().removeAnnotation(annotation);
    }
    
    public static String getXpath(Object annotation) {
        return getContext().getXpath(annotation);
    }
    
    public static QName getProcessQName(String url) {
        return getContext().getProcessQName(url);
    }
    
    public static QName getCurrentProcessQName() {
        return getContext().getCurrentProcessQName();
    }
    
    /**
     * Returns the more appropriate line number for the
     * given the url and line number.
     */
    public static int translateBreakpointLine(String url, int lineNumber) {
        return getContext().translateBreakpointLine(url, lineNumber);
    }
    
    public static void addAnnotationListener(Object annotation, PropertyChangeListener l) {
        getContext().addAnnotationListener(annotation, l);
    }
    
    public static void removeAnnotationListener(Object annotation, PropertyChangeListener l) {
        getContext().removeAnnotationListener(annotation, l);
    }
    
    private static EditorContext getContext() {
        if (context == null) {
            List l = DebuggerManager.getDebuggerManager().lookup
                    (null, EditorContext.class);
            context = (EditorContext) l.get(0);
            int i;
            int k = l.size();
            for (i = 1; i < k; i++) {
                assert false : "compound editor context is not supported";
//                context = new CompoundContextProvider(
//                        (EditorContext) l.get(i), context);
            }
        }
        return context;
    }
    
//    /**
//     * Context provider that embeds two given Context Providers and
//     * delegates methods calls to them.
//     */
//    private static class CompoundContextProvider implements EditorContext {
//        
//        /** First of the two embeded Context Providers. */
//        private EditorContext myContext1;
//        
//        /** Second of the two embeded Context Providers. */
//        private EditorContext myContext2;
//        
//        CompoundContextProvider(EditorContext context1, EditorContext context2) {
//            myContext1 = context1;
//            myContext2 = context2;
//        }
//        
//        public boolean showSource(String url, Position position) {
//            return  myContext1.showSource(url, position) |
//                    myContext2.showSource(url, position);
//        }
//        
//        public Object annotate(
//                String url, Position position, AnnotationType annotationType)
//        {
//            CompoundAnnotation ca = new CompoundAnnotation(
//                        annotate(url, position, annotationType),
//                        annotate(url, position, annotationType)
//                    );
//            return ca;
//        }
//        
//        public void removeAnnotation(Object annotation) {
//            CompoundAnnotation ca = (CompoundAnnotation) annotation;
//            myContext1.removeAnnotation(ca.getAnnotation1());
//            myContext2.removeAnnotation(ca.getAnnotation2());
//        }
//        
//        public int getLineNumber(Object annotation) {
//            int ln = myContext1.getLineNumber(annotation);
//            if (ln >= 0) {
//                return ln;
//            } else {
//                return myContext2.getLineNumber(annotation);
//            }
//        }
//
//        /**
//         * Returns the more appropriate line number for the
//         * given the url and line number.
//         */
//        public int translateBreakpointLine(String url, int lineNumber) {
//            int newLineNumber = myContext1.translateBreakpointLine(url, lineNumber);
//            if (newLineNumber < 0) {
//                newLineNumber = myContext2.translateBreakpointLine(url, lineNumber);
//            }
//            return newLineNumber;
//        }
//        
//        public void addAnnotationListener(Object annotation, PropertyChangeListener l) {
//            //TODO: implement this
//            //the following commented impl will not do since the subscriber
//            //would receive events for the different annotation object from
//            //it has subscribed for
//            
//            CompoundAnnotation ca = (CompoundAnnotation) annotation;
//            myContext1.addAnnotationListener(ca.getAnnotation1(), l);
//            myContext2.addAnnotationListener(ca.getAnnotation2(), l);
//            
//            throw new UnsupportedOperationException(
//                    "Not supported for compound context");  //NOI18N
//            
//        }
//
//        public void removeAnnotationListener(Object annotation, PropertyChangeListener l) {
//            //TODO: implement this
//            //the following commented impl will not do since the subscriber
//            //would receive events for the different annotation object from
//            //it has subscribed for
//            
////            CompoundAnnotation ca = (CompoundAnnotation) annotation;
////            myCp1.removeAnnotationListener(ca.getAnnotation1(), l);
////            myCp2.removeAnnotationListener(ca.getAnnotation2(), l);
//            
//            throw new UnsupportedOperationException(
//                    "Not supported for compound context");  //NOI18N
//        }
//    }
    
//    private static final class CompoundAnnotation {
//        private Object myAnnotation1;
//        private Object myAnnotation2;
//        
//        public CompoundAnnotation(Object annotation1, Object annotation2) {
//            myAnnotation1 = annotation1;
//            myAnnotation2 = annotation2;
//        }
//        
//        public Object getAnnotation1() {
//            return myAnnotation1;
//        }
//        
//        public Object getAnnotation2() {
//            return myAnnotation2;
//        }
//    }
}
