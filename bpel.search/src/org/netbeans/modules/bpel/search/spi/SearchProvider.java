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
 * License. When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP. Sun designates this
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
package org.netbeans.modules.bpel.search.spi;

import java.util.Map;
import java.util.WeakHashMap;

import org.netbeans.modules.bpel.search.api.SearchElement;
import org.netbeans.modules.bpel.search.api.SearchTarget;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2008.04.16
 */
public interface SearchProvider {

  /**
   * Returns true if provider can search in given object.
   * @param object is given object
   * @return true if provider can search in given object
   */
  boolean isApplicable(Object object);

  /**
   * Returns root.
   * @return root
   */
  Object getRoot();

  /**
   * Returns targets.
   * @return targets
   */
  SearchTarget [] getTargets();

  /**
   * Returns element by given object.
   * @param object is given object
   * @return element by given object
   */
  SearchElement getElement(Object object);

  // ---------------------------------------------
  public class Adapter implements SearchProvider {

    public Adapter(Object root) {
      myRoot = root;
      myElements = new WeakHashMap<Object,SearchElement>();
    }

    public boolean isApplicable(Object object) {
      return false;
    }

    public Object getRoot() {
      return myRoot;
    }

    public SearchTarget [] getTargets() {
      return null;
    }

    public SearchElement getElement(Object object) {
      SearchElement element = myElements.get(object);

      if (element != null) {
        return element;
      }
      Object father = getFather(object);
      SearchElement parent = null;

      if (father != null) {
        parent = getElement(father);
      }
      element = createElement(object, parent);
      myElements.put(object, element);

      return element;
    }

    protected SearchElement createElement(Object object, SearchElement parent) {
      return null;
    }

    protected Object getFather(Object object) {
      return null;
    }

    private Object myRoot;
    private Map<Object,SearchElement> myElements;
  }
}
