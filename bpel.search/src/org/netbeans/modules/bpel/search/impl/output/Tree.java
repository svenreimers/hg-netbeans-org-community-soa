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
package org.netbeans.modules.bpel.search.impl.output;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.netbeans.modules.print.api.PrintManager;
import org.netbeans.modules.bpel.search.api.SearchElement;

import org.netbeans.modules.bpel.search.impl.ui.Export;
import org.netbeans.modules.bpel.search.impl.util.Util;
import static org.netbeans.modules.soa.ui.util.UI.*;

/**
 * @author Vladimir Yaroslavskiy
 * @version 2006.11.24
 */
final class Tree extends JTree {

  Tree() {
    super(new DefaultTreeModel(new DefaultMutableTreeNode()));
    myRoot = (DefaultMutableTreeNode) getModel().getRoot();
  }

  void addElement(SearchElement element) {
    addElement(myRoot, element, getParents(element));
  }

  private void addElement(
    MutableTreeNode root,
    SearchElement element,
    Iterator<SearchElement> parents)
  {
    SearchElement next = null;

    if (parents.hasNext()) {
      next = parents.next();
    }
    if (next == null) { // leaf
//out("add leaf: " + element);
      root.insert(new DefaultMutableTreeNode(element), 0);
      return;
    }
    // try to find node among children
    Enumeration children = root.children();

//out("try to add child: " + next);
    while (children.hasMoreElements()) {
      DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();

      if (child.isLeaf()) {
//out("skip leaf: " + child);
        continue;
      }
      if (child.getUserObject().equals(next)) {
        // go to the next level
//out("next level");
        addElement(child, element, parents);
        return;
      }
    }
    // cannot find the same node, add new node
    MutableTreeNode node = new DefaultMutableTreeNode(next);
//out("add internal: " + next);
    root.insert(node, 0);
    addElement(node, element, parents);
  }

  void finished(String target, String text, int count) {
    myText = i18n(Tree.class, "LBL_Search_Tab", target, text); // NOI18N
    String title = i18n(Tree.class, "LBL_Found", target, text, "" + count); // NOI18N
    String catchword = i18n(Tree.class, "LBL_Catchword", target, text, "" + count); // NOI18N

    myRoot.setUserObject(new SearchElement.Adapter(
      title, title, icon(Util.class, "find"), null)); // NOI18N

    // vlv: print
    putClientProperty(java.awt.print.Printable.class, catchword);

    createOccurences();
    updateRoot();
    expose(myRoot);
  }

  @Override
  public String toString()
  {
    return myText;
  }

  private void createOccurences() {
    myOccurences = new ArrayList<DefaultMutableTreeNode>();
    createOccurences(myRoot);
    myIndex = -1;
  }

  private void createOccurences(DefaultMutableTreeNode node) {
    Enumeration children = node.children();

    if (node.isLeaf()) {
      myOccurences.add(node);
    }
    while (children.hasMoreElements()) {
      createOccurences((DefaultMutableTreeNode) children.nextElement());
    }
  }

  private void updateRoot() {
    getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    ToolTipManager.sharedInstance().registerComponent(this);
    setCellRenderer(new TreeRenderer());
    setShowsRootHandles(false);
    setRootVisible(true);
    setSelectionPath(new TreePath(myRoot.getPath()));
    expandPath(new TreePath(myRoot.getPath()));

    addTreeWillExpandListener(new TreeWillExpandListener() {
      public void treeWillExpand(TreeExpansionEvent event) {
      }
      public void treeWillCollapse(TreeExpansionEvent event) {
//out("Will collapse: " + getNode(event.getPath()));
        collapseChildren(getNode(event.getPath()));
      }
    });
    addTreeExpansionListener(new TreeExpansionListener() {
      public void treeExpanded(TreeExpansionEvent event) {
//out("Expanded: " + getNode(event.getPath()));
        expandChildren(getNode(event.getPath()));
        updateSize();
      }
      public void treeCollapsed(TreeExpansionEvent event) {
        updateSize();
      }
    });
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent event) {
        // popup menu
        if (SwingUtilities.isRightMouseButton(event)) {
          showPopupMenu(event, event.getX(), event.getY());
        }
      }
      public void mouseClicked(MouseEvent event) {
        // double click
        if (event.getClickCount() == 2 && getSelectedNode().isLeaf()) {
          gotoVisual(getSelectedNode());
          event.consume();
        }
      }
    });
    addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent event) {
        handleEvent(event);
      }
    });
    updateSize();
  }

  private void handleEvent(KeyEvent event) {
//out();
//out("handleEvent");
    DefaultMutableTreeNode node = getSelectedNode();
    int code = event.getKeyCode();
    int modifiers = event.getModifiers();
//out(" code: " + code);

    if (code == KeyEvent.VK_F10 && isShift(modifiers)) {
      showPopupMenu(event, POPUP_MENU_X, POPUP_MENU_Y);
    }
    else if (code == KeyEvent.VK_F12 && isShift(modifiers)) {
      previousOccurence(node);
    }
    else if (code == KeyEvent.VK_F12) {
      nextOccurence(node);
    }
    else if (code == KeyEvent.VK_DELETE) {
      remove(node);
    }
  }

  private void showPopupMenu(ComponentEvent event, int x, int y) {
    final DefaultMutableTreeNode node = getSelectedNode();
    JPopupMenu popup = new JPopupMenu();
    JMenuItem item;

    createAction(popup, node);

    popup.addSeparator(); // -----------------------------------------------------

    // remove
    item = createItem("LBL_Remove"); // NOI18N
    item.setEnabled( !node.isRoot());
    item.setIcon(EMPTY);
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        remove(node);
      }
    });
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
    popup.add(item);

    popup.addSeparator(); // -----------------------------------------------------

    // previous occurence
    item = createItem("LBL_Previous_Occurence"); // NOI18N
    item.setEnabled(true);
    item.setIcon(icon(Util.class, "previous")); // NOI18N
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        previousOccurence(node);
      }
    });
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12,KeyEvent.SHIFT_MASK));
    popup.add(item);

    // next occurence
    item = createItem("LBL_Next_Occurence"); // NOI18N
    item.setEnabled(true);
    item.setIcon(icon(Util.class, "next")); // NOI18N
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        nextOccurence(node);
      }
    });
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
    popup.add(item);

    popup.addSeparator(); // -----------------------------------------------------

    // export
    item = createItem("LBL_Export"); // NOI18N
    item.setIcon(icon(Util.class, "export")); // NOI18N
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        export(node);
      }
    });
    popup.add(item);

    // vlv: print
    item = createItem("LBL_Print_Preview"); // NOI18N
    item.setEnabled(true);
    item.setIcon(getPrintIcon());
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        PrintManager.getDefault().getPrintPreviewAction().actionPerformed(event);
      }
    });
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
      KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK | KeyEvent.SHIFT_MASK
    ));
    popup.add(item);

    // show
    popup.show(event.getComponent(), x, y);
  }

  private void createAction(JPopupMenu popup, final DefaultMutableTreeNode node) {
    JMenuItem item;

    // go to visual
    item = createItem("LBL_Go_to_Visual"); // NOI18N
    item.setEnabled( !node.isRoot());
    item.setIcon(EMPTY);
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        gotoVisual(node);
      }
    });
    popup.add(item);

    // go to source
    item = createItem("LBL_Go_to_Source"); // NOI18N
    item.setEnabled( !node.isRoot());
    item.setIcon(EMPTY);
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        gotoSource(node);
      }
    });
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_MASK));
    popup.add(item);

    // copy
    item = createItem("LBL_Copy"); // NOI18N
    item.setIcon(icon(Util.class, "copy")); // NOI18N
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        copy(node);
      }
    });
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
    popup.add(item);

    // collapse / expand
    item = createItem("LBL_Collapse_Expand"); // NOI18N
    item.setEnabled( !node.isLeaf());
    item.setIcon(icon(Util.class, "expose")); // NOI18N
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        expose(node);
      }
    });
    popup.add(item);
  }

  private Icon getPrintIcon() {
    Object object = PrintManager.getDefault().
      getPrintPreviewAction().getValue(Action.SMALL_ICON);

    if (object instanceof Icon) {
      return (Icon) object;
    }
    return EMPTY;
  }

  private void updateSize() {
    putClientProperty(Dimension.class, getMaximumSize());
  }

  private void gotoSource(DefaultMutableTreeNode node) {
    ((SearchElement) node.getUserObject()).gotoSource();
  }

  private void gotoVisual(DefaultMutableTreeNode node) {
    ((SearchElement) node.getUserObject()).gotoVisual();
  }

  void previousOccurence() {
    previousOccurence(getSelectedNode());
  }

  private void previousOccurence(TreeNode node) {
    myIndex--;

    if (myIndex < 0) {
      myIndex = myOccurences.size() - 1;
    }
    selectOccurence();
    requestFocus();
  }

  void nextOccurence() {
    nextOccurence(getSelectedNode());
  }
  
  private void nextOccurence(TreeNode node) {
    myIndex++;

    if (myIndex == myOccurences.size()) {
      myIndex = 0;
    }
    selectOccurence();
    requestFocus();
  }

  private void selectOccurence() {
    select(myOccurences.get(myIndex));
  }

  private void select(DefaultMutableTreeNode node) {
    TreePath path = new TreePath(node.getPath());
    setSelectionPath(path);
    scrollPathToVisible(path);
  }

  private void copy(TreeNode node) {
    StringBuffer buffer = new StringBuffer();

    copy(node, buffer, ""); // NOI18N
    buffer.append(LS);

    StringSelection selection = new StringSelection(buffer.toString());
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
      selection, selection);
  }

  private void copy(TreeNode node, StringBuffer buffer, String indent) {
    buffer.append(indent + node + LS);
    Enumeration children = node.children();

    while (children.hasMoreElements()) {
      TreeNode child = (TreeNode) children.nextElement();
      copy(child, buffer, indent + "    "); // NOI18N
    }
  }

  void export() {
    export(getSelectedNode());
  }

  private void export(DefaultMutableTreeNode node) {
    List<List<String>> descriptions = new ArrayList<List<String>>();
    export(node, descriptions);
    descriptions.add (null);

    if (myExport == null) {
      myExport = new Export();
    }
    myExport.show(descriptions, myRoot.toString());
    requestFocus();
  }

  private void export(DefaultMutableTreeNode node, List<List<String>> descriptions) {
    if (node.isLeaf()) {
      List<String> description = new ArrayList<String>();
      description.add(getDescription(node));
      description.add(node.toString());
      descriptions.add(description);
    }
    Enumeration children = node.children();

    while (children.hasMoreElements()) {
      DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
      export(child, descriptions);
    }
  }

  private String getDescription(DefaultMutableTreeNode node) {
    if (isRoot(node)) {
      return ""; // NOI18N
    }
    String description = getDescription((DefaultMutableTreeNode) node.getParent());

    if ( !node.isLeaf()) {
      if ( !isRoot((DefaultMutableTreeNode) node.getParent())) {
        description += LS;
      }
      description += node;
    }
    return description;
  }

  private boolean isRoot(DefaultMutableTreeNode node) {
    if (node == null) {
      return true;
    }
    if (node.getParent() == null) {
      return true;
    }
    if (node.getParent().getParent() == null) {
      return true;
    }
    return false;
  }

  void expose() {
    expose(getSelectedNode());
  }

  private void expose(DefaultMutableTreeNode node) {
    if (node == null || node.isLeaf()) {
      return;
    }
    myIsReformAll = true;
    TreePath path = new TreePath(node.getPath());
    boolean isExpanded = isExpanded(path);

    // special check for root
    if (node.isRoot()) {
      Enumeration children = node.children();
      isExpanded = false;

      while (children.hasMoreElements()) {
        DefaultMutableTreeNode child =
          (DefaultMutableTreeNode) children.nextElement();
        isExpanded = isExpanded(new TreePath(child.getPath()));

        if (isExpanded) {
          break;
        }
      }
    }
    if (isExpanded) {
      // collapse
      if (node.isRoot()) {
        collapseChildren(node);
      }
      else {
        collapsePath(path);
      }
    }
    else {
      // expand
      expandPath(path);

      if (isExpanded(path)) {
        expandChildren(node);
      }
    }
    myIsReformAll = false;
    requestFocus();
  }

  private void expandChildren(TreeNode node) {
    Enumeration children = node.children();
    
    if (myIsReformAll) {
      while (children.hasMoreElements()) {
        DefaultMutableTreeNode child =
          (DefaultMutableTreeNode) children.nextElement();
        TreePath path = new TreePath(child.getPath());
        expandPath(path);

        if (isExpanded(path)) {
          expandChildren(child);
        }
      }
    }
    else {
      // process node which has only one child
      if (children.hasMoreElements()) {
        DefaultMutableTreeNode child =
          (DefaultMutableTreeNode) children.nextElement();

        if ( !children.hasMoreElements()) {
          TreePath path = new TreePath(child.getPath());
          expandPath(path);

          if (isExpanded(path)) {
            expandChildren(child);
          }
        }
      }
    }
  }

  private void collapseChildren(TreeNode node) {
    if (myIsReformAll) {
      Enumeration children = node.children();
    
      while (children.hasMoreElements()) {
        DefaultMutableTreeNode child =
          (DefaultMutableTreeNode) children.nextElement();
        TreePath path = new TreePath(child.getPath());
        collapsePath(path);
      }
    }
  }

  private void remove(DefaultMutableTreeNode node) {
    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();

    if (parent == null) {
      return;
    }
    if (printConfirmation(i18n(Tree.class, "LBL_Are_You_Sure"))) { // NOI18N
      parent.remove(node);
      select(parent);
      updateUI();
    }
    requestFocus();
  }

  private DefaultMutableTreeNode getNode(TreePath path) {
    return (DefaultMutableTreeNode) path.getLastPathComponent();
  }

  private DefaultMutableTreeNode getSelectedNode() {
    TreePath [] paths = getSelectionPaths();

    if (paths == null || paths.length == 0) {
      return myRoot;
    }
    TreePath path = paths [0];

    if (path == null) {
      return myRoot;
    }
    return getNode(path);
  }

  private JMenuItem createItem(String name) {
    return new JMenuItem(i18n(Tree.class, name));
  }

  private Iterator<SearchElement> getParents(SearchElement element) {
    List<SearchElement> elements = new ArrayList<SearchElement>();
    SearchElement parent = element.getParent();

    while (parent != null) {
      elements.add(0, parent);
      parent = parent.getParent();
    }
    return elements.iterator();
  }

  @Override
  public String convertValueToText(
    Object node,
    boolean selected,
    boolean expanded,
    boolean leaf,
    int row,
    boolean hasFocus)
  {
    if (node != null) {
      String text = node.toString();
  
      if (text != null) {
        int k = text.lastIndexOf("."); // NOI18N

        if (k == -1) {
          return text;
        }
        return text.substring(k + 1);
      }
    }
    return "";
  }

  // ----------------------------------------------------------------------
  private static final class TreeRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent (
      JTree tree, Object value, boolean select, boolean expanded,
      boolean leaf, int row, boolean focus)
    {
      super.getTreeCellRendererComponent(tree, value, select, expanded, leaf, row, focus);
      SearchElement element =
        (SearchElement) ((DefaultMutableTreeNode) value).getUserObject();

      setText("<html>" + getHtmlName(element.getName(), leaf, row) + "</html>"); // NOI18N
      setToolTipText(element.getToolTip());
      setIcon(element.getIcon());

      return this;
    }

    private String getHtmlName(String name, boolean leaf, int row) {
      if (row == 0) {
        return name;
      }
      if (leaf) {
        int k = name.lastIndexOf(".");

        if (k == -1) {
          return getBold(name);
        }
        return getGrey(name.substring(0, k + 1)) + getBold(name.substring(k + 1));
      }
      return getGrey(name);
    }

    private String getGrey(String value) {
      return "<font color=\"#999999\">" + value + "</font>"; // NOI18N
    }

    private String getBold(String value) {
      return "<b>" + value + "</b>"; // NOI18N
    }
  }

  private int myIndex;
  private String myText;
  private Export myExport;
  private boolean myIsReformAll;
  private DefaultMutableTreeNode myRoot;
  private List<DefaultMutableTreeNode> myOccurences;
  private static final int POPUP_MENU_X = 16;
  private static final int POPUP_MENU_Y = 16;
  private static final Icon EMPTY = icon(Util.class, "empty"); // NOI18N
}
