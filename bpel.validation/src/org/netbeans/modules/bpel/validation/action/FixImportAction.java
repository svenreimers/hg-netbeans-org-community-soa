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
package org.netbeans.modules.bpel.validation.action;

import java.awt.event.ActionEvent;
import org.openide.nodes.Node;

import org.netbeans.modules.xml.xam.Model;
import org.netbeans.modules.xml.xam.spi.Validation.ValidationType;
import org.netbeans.modules.soa.validation.core.Controller;

import org.netbeans.modules.bpel.model.api.BpelModel;
import org.netbeans.modules.bpel.model.api.Import;
import org.netbeans.modules.bpel.model.api.Process;

import static org.netbeans.modules.xml.ui.UI.*;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2008.05.20
 */
public final class FixImportAction extends IconAction {

  public FixImportAction() {
    super(
      i18n(FixImportAction.class, "CTL_Fix_Import_Action"), // NOI18N
      i18n(FixImportAction.class, "TLT_Fix_Import_Action"), // NOI18N
      null
    );
  }

  public void actionPerformed(ActionEvent event) {
    Node node = getSelectedNode();
//out();
//out("node: " + node);

    if (node == null) {
      return;
    }
    Controller controller = node.getLookup().lookup(Controller.class);

    if (controller == null) {
      return;
    }
//out("controller: " + controller);
    Model model = controller.getModel();
//out("model: " + model);

    if ( !(model instanceof BpelModel)) {
      return;
    }
    fixImport((BpelModel) model, controller);
  }

  private void fixImport(BpelModel model, Controller controller) {
    Process process = model.getProcess();

    if (process == null) {
      return;
    }
    Import [] imports = process.getImports();

    if (imports == null) {
      return;
    }
//out();
    for (int i=imports.length - 1; i >= 0; i--) {
//out();
//out("see: " + imports[i].getLocation());
      model.startTransaction();
      process.removeImport(i);

      if (controller.validate(ValidationType.PARTIAL).isEmpty()) {
        model.endTransaction();
//out("     REMOVE");
      }
      else {
        model.rollbackTransaction();
//out("     ++");
      }
    }
  }
}