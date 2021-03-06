
/*
 * Chooser.java
 *
 * Created on Aug 6, 2009, 2:34:46 PM
 */
package org.netbeans.modules.wsdlextensions.email.editor.panels;

import java.awt.Cursor;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.swing.tree.TreeSelectionModel;
import org.openide.explorer.ExplorerManager;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openide.loaders.DataFolder;

/**
 *
 * @author skini
 */
public class Chooser extends javax.swing.JPanel implements ExplorerManager.Provider {

    private ExplorerManager explorerManager;
    private final List<String> selectedFolders;
    private final ReceiverEmailConnection emailConnection;
    private final String INBOX_FOLDER = "INBOX";
    private String previousSelection = INBOX_FOLDER;

    /** Creates new form Chooser */
    public Chooser(ReceiverEmailConnection emailConnection, String folder) {
        initComponents();
        this.emailConnection = emailConnection;
        if (folder != null && folder.trim().length() > 0) {
            this.previousSelection = folder;
        }
        this.selectedFolders = new ArrayList<String>();
        explorerManager = new ExplorerManager();
        explorerManager.addPropertyChangeListener(new ExplorerPropertyChangeListener());
        populateFolderTree();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        treeView = new org.openide.explorer.view.BeanTreeView();
        treeLabel = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        treeView.setDoubleBuffered(true);
        treeView.setDragSource(false);
        treeView.setDropTarget(false);
        treeView.setName("treeView"); // NOI18N
        treeView.setRootVisible(false);
        treeView.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treeLabel.setLabelFor(treeView);
        org.openide.awt.Mnemonics.setLocalizedText(treeLabel, org.openide.util.NbBundle.getMessage(Chooser.class, "Chooser.treeLabel.text")); // NOI18N
        treeLabel.setName("treeLabel"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, treeView, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(treeLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(treeLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(treeView, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        treeView.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(Chooser.class, "Chooser.treeView.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel treeLabel;
    org.openide.explorer.view.BeanTreeView treeView;
    // End of variables declaration//GEN-END:variables

    private void populateFolderTree() {
        Node node = new AbstractNode(Children.LEAF) {

            @Override
            public Image getIcon(int arg0) {
                return ImageUtilities.loadImage("org/netbeans/modules/xml/text/navigator/resources/wait.gif"); // NOI18N
            }
        };
        node.setDisplayName("Loading Folders ...");
        explorerManager.setRootContext(node);
        treeView.setRootVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (emailConnection.getRootNode() != null) {
            explorerManager.setRootContext(emailConnection.getRootNode());
            selectPreviousSelection();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public static String PROP_ACTION_APPLY = "APPLY";

    private void selectPreviousSelection() {
        treeView.setRootVisible(explorerManager.getRootContext().getName().length() > 0);
        if (previousSelection != null) {
            String[] splits = previousSelection.split("\\/");
            Node rootContext = explorerManager.getRootContext();
            Node n = rootContext;
            int i = 0;
            boolean selectionCompleted = false;
            for (;i < splits.length; i++) {
                String name = splits[i];
                boolean found = false;
                for (Node node : n.getChildren().getNodes()) {
                    if (node.getName().equalsIgnoreCase(name)) {
                        explorerManager.setExploredContext(node);
                        n = node;
                        found = true;
                        break;
                    }
                }
                selectionCompleted = (i == splits.length - 1 && found);
            }
            if (selectionCompleted) {
                try {
                    explorerManager.setSelectedNodes(new Node[]{n});
                } catch (PropertyVetoException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

//    Node getSelectedNode(Node[] nodes, String name) {
//        for (Node node : nodes) {
//            if (node.getName().equals(name))  {
//                explorerManager.setExploredContext(node);
//                return node;
//            }
//        }
//        return null;
//    }
    class ExplorerPropertyChangeListener implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(ExplorerManager.PROP_SELECTED_NODES)) {
                Node[] nodes = (Node[]) evt.getNewValue();
                selectedFolders.clear();
                if (nodes.length > 0) {
                    //set the selected node to null and state as invalid by default
                    firePropertyChange(PROP_ACTION_APPLY, true, false);
                    for (Node node : nodes) {
                        StringBuilder builder = new StringBuilder(node.getName());
                        Node parent = node;
                        while ((parent = parent.getParentNode()) != null) {
                            builder.insert(0, parent.getName() + "/");
                        }
                        if (builder.toString().startsWith("/")) {
                            builder.deleteCharAt(0);
                        }
                        selectedFolders.add(builder.toString());
                    }
                    firePropertyChange(PROP_ACTION_APPLY, false, true);
                }
            }
        }
    }

    public List<String> getSelectedFolders() {
        return selectedFolders;
    }

    static class IMAPFolderChildFactory extends ChildFactory<Folder> {

        private final Folder folder;
        private final boolean hasChildren;

        public IMAPFolderChildFactory(Folder folder, boolean hasChildren) {
            this.folder = folder;
            this.hasChildren = hasChildren;
        }

        @Override
        protected Node createNodeForKey(Folder fld) {
            Children children = Children.LEAF;
            if (hasChildren) {
                try {
                    children = Children.create(new IMAPFolderChildFactory(fld, fld.list().length > 0), false);
                } catch (MessagingException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            Node node = new AbstractNode(children, Lookups.fixed(fld));
            node.setName(fld.getName());
            node.setDisplayName(fld.getName());
            return node;
        }

        @Override
        protected boolean createKeys(List<Folder> arg0) {
            try {
                return arg0.addAll(Arrays.asList(folder.list()));
            } catch (MessagingException ex) {
                Exceptions.printStackTrace(ex);
            }
            return false;
        }
    }

    /**
     * Copied from bpel.
     * @author Vitaly Bychkov
     * @version 1.0
     *
     */
    public static class FolderIcon {

        private static AtomicReference<Image> CLOSED_FOLDER_ICON =
                new AtomicReference<Image>();
        private static AtomicReference<Image> OPENED_FOLDER_ICON =
                new AtomicReference<Image>();

        private FolderIcon() {
        }

        public static Image getOpenedIcon(int type) {
            if (OPENED_FOLDER_ICON.get() == null) {
                Image image = getSystemFolderImage(true, type);
                OPENED_FOLDER_ICON.compareAndSet(null, image);
            }
            return OPENED_FOLDER_ICON.get();
        }

        public static Image getIcon(int type) {
            if (CLOSED_FOLDER_ICON.get() == null) {
                Image image = getSystemFolderImage(false, type);
                CLOSED_FOLDER_ICON.compareAndSet(null, image);
            }
            return CLOSED_FOLDER_ICON.get();
        }

        private static Image getSystemFolderImage(boolean isOpened, int type) {
            Node n = DataFolder.findFolder(FileUtil.getConfigRoot()).getNodeDelegate();
            return isOpened ? n.getOpenedIcon(type) : n.getIcon(type);
        }
    }
}
