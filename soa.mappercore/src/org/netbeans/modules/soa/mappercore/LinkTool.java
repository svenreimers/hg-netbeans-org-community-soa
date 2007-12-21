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

package org.netbeans.modules.soa.mappercore;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import org.netbeans.modules.soa.mappercore.dnd.DnDConstants;
import org.netbeans.modules.soa.mappercore.model.Constant;
import org.netbeans.modules.soa.mappercore.model.Graph;
import org.netbeans.modules.soa.mappercore.model.Link;
import org.netbeans.modules.soa.mappercore.model.MapperModel;
import org.netbeans.modules.soa.mappercore.model.SourcePin;
import org.netbeans.modules.soa.mappercore.model.TargetPin;
import org.netbeans.modules.soa.mappercore.model.TreeSourcePin;
import org.netbeans.modules.soa.mappercore.model.Vertex;
import org.netbeans.modules.soa.mappercore.model.VertexItem;
import org.netbeans.modules.soa.mappercore.utils.MapperCollection;
import org.netbeans.modules.soa.mappercore.utils.MapperTreePath;
import org.netbeans.modules.soa.mappercore.utils.Utils;

/**
 *
 * @author anjeleevich
 */
public class LinkTool extends MapperPropertyAccess {
    
    private JComponent sourceComponent;
    private JComponent targetComponent;
    
    private SourcePin sourcePin;
    private TargetPin targetPin;

    private TreePath targetPath;
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    private boolean outgoing;
    
    private long uid = -1;
    
    private MapperCollection activePins;
    
    public LinkTool(Mapper mapper) {
        super(mapper);
    }
    
    
    CanvasRendererContext getCanvasRendererContext() {
        return new LinkToolCanvasRendererContext(this);
    }
    
    
    public SourcePin getSourcePin() {
        return sourcePin;
    }
    
    
    public TargetPin getTargetPin() {
        return targetPin;
    }
    
    
    public boolean isOutgoing() {
        return outgoing;
    }
    
    
    public boolean isIngoing() {
        return !outgoing;
    }
    
    
    public boolean isActive() {
        return uid >= 0;
    }
    
    
    public JComponent getSourceComponent() {
        return sourceComponent;
    }
    
    
    private boolean canConnect(TreePath treePath, SourcePin source, 
            TargetPin target) 
    {
        return true;
    }
    
    
    MapperCollection getActivePins() {
        return activePins;
    }
    
    
    public Point getSourcePoint() {
        if (sourcePin == null) {
            return Utils.fromScrollPane(sourceComponent, new Point(x1, y1), 
                    null);
        } 
                
        if (sourcePin instanceof TreeSourcePin) {
            TreePath treePath = ((TreeSourcePin) sourcePin).getTreePath();
            JTree tree = (JTree) sourceComponent;
            Rectangle bounds = tree.getRowBounds(tree.getRowForPath(treePath));
            return new Point(
                    bounds.x + bounds.width, 
                    bounds.y + bounds.height / 2);
        }
        
        Mapper mapper = getMapper();
        Canvas canvas = getCanvas();
        int step = mapper.getStepSize();
        
        Vertex vertex = (Vertex) sourcePin;
        MapperNode node = mapper.getNode(targetPath, true);
        
        Rectangle bounds = vertex.getBounds(step);
        
        int px = vertex.getPinGlobalX() * step; // bounds.x + bounds.width;
        int py = vertex.getPinGlobalY() * step; // bounds.y + bounds.height / 2;
        
        px = canvas.toCanvas(px);
        py = node.yToView(py + (step - 1) / 2) + 1;
        
        return new Point(px, py);
    }
    
    
    public JComponent getTargetComponent() {
        return targetComponent;
    }
    
    
    public Point getTargetPoint() {
        if (targetPin == null) {
            return Utils.fromScrollPane(targetComponent, new Point(x2, y2), 
                    null);
        }
        
        Mapper mapper = getMapper();
        MapperNode node = mapper.getNode(targetPath, true);
        
        if (targetPin instanceof Graph) {
            int py = node.yToView(node.getContentCenterY());
            int px = mapper.getRightTree().getWidth() - 
                    node.getLabelWidth() - node.getIndent();
            return new Point(px, py);
        }
        
        Canvas canvas = mapper.getCanvas();
        int step = mapper.getStepSize();
        
        VertexItem item = (VertexItem) targetPin;
        int px = item.getGlobalX() * step;
        int py = (item.getGlobalY() + item.getHeight() / 2) * step;
        
        px = canvas.toCanvas(px);
        py = node.yToView(py + (step - 1) / 2) + 1;
        
        return new Point(px, py);
    }
    
    

    public Transferable activateIngoing(TreePath targetPath, Graph graph) {
        this.outgoing = false;
        this.uid = createUID();
        
        this.sourcePin = null;
        this.sourceComponent = null;
        
        this.targetPath = targetPath;
        this.targetPin = graph;
        this.targetComponent = getRightTree();
        
        this.sourcePin = null;
        this.sourceComponent = null;
        
        this.activePins = findActivePins();

        return new LinkTransferable(uid);
    }

    
    public Transferable activateIngoing(TreePath targetPath, VertexItem item) {
        this.outgoing = false;
        this.uid = createUID();
        
        this.sourcePin = null;
        this.sourceComponent = null;
        
        this.targetPath = targetPath;
        this.targetPin = item;
        this.targetComponent = getCanvas();
        
        this.sourcePin = null;
        this.sourceComponent = null;
        
        this.activePins = findActivePins();

        return new LinkTransferable(uid);
    }
    
    
    public Transferable activateOutgoing(TreeSourcePin sourcePin) {
        this.outgoing = true;
        this.uid = createUID();
        
        this.sourcePin = sourcePin;
        this.sourceComponent = getLeftTree();
        
        this.targetPath = null;
        this.targetComponent = null;
        this.targetPin = null;

        this.activePins = findActivePins();
        
        return new LinkTransferable(uid);
    }
    
    
    public Transferable activateOutgoing(TreePath targetPath, Vertex sourcePin) {
        this.outgoing = true;
        this.uid = createUID();
        
        this.sourcePin = sourcePin;
        this.sourceComponent = getCanvas();
        
        this.targetPath = targetPath;
        this.targetPin = null;
        this.targetComponent = null;

        this.activePins = findActivePins();
        
        return new LinkTransferable(uid);
    }

    
    
//    public Transferable activate(boolean outgoing, 
//            SourcePin sourcePin, JComponent sourceComponent, Point sourcePoint,
//            TargetPin targetPin, JComponent targetComponent, Point targetPoint) 
//    {
//        this.outgoing = outgoing;
//        this.uid = createUID();
//        
//        setSource(sourcePin, sourceComponent, sourcePoint);
//        setTarget(targetPin, targetComponent, targetPoint);
//        
//        return new LinkTransferable(uid);
//    }
    
    
    private void reset() {
        uid = -1;

        sourcePin = null;
        sourceComponent = null;
        
        targetPath = null;
        targetPin = null;
        targetComponent = null;
        
        activePins = null;
    }
    
    
    private boolean isValidTransferable(Transferable transferable) {
        try {
            long transferableUID = (Long) transferable.getTransferData(
                    LINK_DATA_FLAVOR);
            return (transferableUID == this.uid);
        } catch (Exception exception) {}
        
        return false;
    }
    
    
    public boolean drag(JComponent component, DropTargetDragEvent dtde) {
        if (!isActive()) return false;
        if (!isValidTransferable(dtde.getTransferable())) return false;

        Canvas canvas = getCanvas();
        LeftTree leftTree = getLeftTree();
        RightTree rightTree = getRightTree();
        
        canvas.repaint();
        leftTree.repaint();
        rightTree.repaint();
        
        if (isIngoing()) {
            if (component == leftTree) return dragIngoing(leftTree, dtde);
            if (component == canvas) return dragIngoing(canvas, dtde);
            if (component == rightTree) return dragIngoing(rightTree, dtde);
        } else {
            if (component == leftTree) return dragOutgoing(leftTree, dtde);
            if (component == canvas) return dragOutgoing(canvas, dtde);
            if (component == rightTree) return dragOutgoing(rightTree, dtde);
        }
        
        throw new IllegalArgumentException("Unknown component: " + component);
    }
    

    
    public boolean dragIngoing(LeftTree tree, DropTargetDragEvent dtde) {
        Point point = dtde.getLocation();
        Point scrollPanePoint = Utils.toScrollPane(tree, point, null);
        
        this.sourceComponent = tree;
        this.x1 = scrollPanePoint.x;
        this.y1 = scrollPanePoint.y;
        
        TreeSourcePin treeSourcePin = null;
        TreePath treePath = tree.getPathForLocation(point.x, point.y);
        
        if (treePath != null) {
            treeSourcePin = getMapperModel().getTreeSourcePin(treePath);
        } 
        
        this.sourcePin = treeSourcePin;
        
        acceptReject(dtde, false);
        
        return true;
    }
    
    
    public boolean dragIngoing(Canvas canvas, DropTargetDragEvent dtde) {
        Point canvasPoint = dtde.getLocation();
        Point scrollPanePoint = Utils.toScrollPane(canvas, canvasPoint, null);
        
        sourceComponent = canvas;
        
        MapperNode node = getNodeAt(canvasPoint.y);
        
        int step = getMapper().getStepSize();
        
        Vertex sourceVertex = null;
        
        boolean rejectDrag = false;
        
        if (node != null) {
            TreePath treePath = node.getTreePath();

            int graphX = canvas.toGraph(canvasPoint.x);
            int graphY = node.yToNode(canvasPoint.y);
            
            Graph graph = node.getGraph();

            if (!treePath.equals(targetPath)) {
                rejectDrag = true;
            } else if (graph != null && node.isGraphExpanded()) {
                graphY -= (step - 1) / 2;
                sourceVertex = findVertex(treePath, graph, graphX, graphY, step);
            }
        } else {
            rejectDrag = true;
        }

        x1 = scrollPanePoint.x;
        y1 = scrollPanePoint.y;
        sourceComponent = canvas;
        sourcePin = sourceVertex;
        
        acceptReject(dtde, rejectDrag);
        
        if (targetPin instanceof Graph) getRightTree().repaint();
        getCanvas().repaint();
        return true;
    }

    
    public boolean dragIngoing(RightTree tree, DropTargetDragEvent dtde) {
        Point point = dtde.getLocation();
        Point scrollPanePoint = Utils.toScrollPane(tree, point, null);
        
        this.x1 = scrollPanePoint.x;
        this.y1 = scrollPanePoint.y;
        this.sourceComponent = tree;
        this.sourcePin = null;

        dtde.rejectDrag();
        
        return true;
    }

    
    
    public boolean dragOutgoing(LeftTree tree, DropTargetDragEvent dtde) {
        Point point = dtde.getLocation();
        Point scrollPanePoint = Utils.toScrollPane(tree, point, null);
        
        this.x2 = scrollPanePoint.x;
        this.y2 = scrollPanePoint.y;
        this.targetComponent = tree;
        this.targetPin = null;
        
        dtde.rejectDrag();
        
        getCanvas().repaint();
        
        return true;
    }
    
    
    public boolean dragOutgoing(Canvas canvas, DropTargetDragEvent dtde) {
        Point canvasPoint = dtde.getLocation();
        Point scrollPanePoint = Utils.toScrollPane(canvas, canvasPoint, null);
        
        MapperNode node = getNodeAt(canvasPoint.y);
        int step = getMapper().getStepSize();
        
        VertexItem targetVertexItem = null;
        
        boolean rejectDrag = false;
        
        if (node != null) {
            TreePath treePath = node.getTreePath();

            int graphX = canvas.toGraph(canvasPoint.x);
            int graphY = node.yToNode(canvasPoint.y);
            
            Graph graph = node.getGraph();
            
            if (graph != null && node.isGraphExpanded()) {
                graphY -= (step - 1) / 2;
                targetVertexItem = findVertexItem(treePath, graph, graphX, graphY, step);
            }
            
            if (sourcePin instanceof TreeSourcePin) {
                targetPath = treePath;
            } else if (!treePath.equals(targetPath)) {
                rejectDrag = true;
            }
        } else {
            rejectDrag = true;
        }

        x2 = scrollPanePoint.x;
        y2 = scrollPanePoint.y;
        targetComponent = canvas;
        targetPin = targetVertexItem;
        
        if (sourcePin instanceof TreeSourcePin) getLeftTree().repaint();
        getCanvas().repaint();

        acceptReject(dtde, rejectDrag);
        
        return true;
    }
    
    
    public boolean dragOutgoing(RightTree tree, DropTargetDragEvent dtde) {
        Point point = dtde.getLocation();
        Point scrollPanePoint = Utils.toScrollPane(tree, point, null);
        
        MapperNode node = getNodeAt(point.y);
        
        Graph targetGraph = null; 
        TreePath targetPath = this.targetPath;
        
        MapperModel model = getMapperModel();
        
        boolean rejectDrag = false;
        
        if (node != null) {
            Graph graph = node.getGraph();
            TreePath treePath = node.getTreePath();
            
            if (graph != null && treePath != null) {
                if (sourcePin instanceof Vertex) {
                    if (Utils.equal(targetPath, treePath) 
                            && model.canConnect(treePath, sourcePin, graph))
                    {
                        targetGraph = graph;
                        targetPath = treePath;
                    } else {
                        rejectDrag = true;
                    }
                } else if (model.canConnect(treePath, sourcePin, graph)) {
                    // TreeSourcePin
                    targetGraph = graph;
                    targetPath = treePath;
                }
            }
        }
        
        this.x2 = scrollPanePoint.x;
        this.y2 = scrollPanePoint.y;
        this.targetPin = targetGraph;
        this.targetPath = targetPath;
        this.targetComponent = tree;
        
        acceptReject(dtde, rejectDrag);
        
        getCanvas().repaint();
        
        return true;
    }
    
    
    private void acceptReject(DropTargetDragEvent dtde, boolean rejectDrag) {
        if (rejectDrag) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(DnDConstants.COPY + DnDConstants.LINK 
                    + DnDConstants.MOVE);
        }
    }
    

//    private boolean dragOverCanvas(Canvas canvas, 
//            DropTargetDragEvent dtde) 
//    {
//        Point point = dtde.getLocation();
//
//        int graphX = canvas.toGraph(point.x);
//        
//        MapperNode node = getNodeAt(point.y);
//        
//        TreePath treePath = null;
//        
//        if (node != null) {
//            treePath = node.getTreePath();
//        }
//        
//        Graph graph = node.getGraph();
//        
//        if (isIngoing()) {
//            // ingoing
//            if (treePath != null && treePath.equals(targetPath)) {
//                // drag over correct graph
//                
//            }
//        } else if (sourcePin instanceof TreeSourcePin) {
//            // outgoing from left tree
//            if (treePath != null && graph != null) {
//                this.targetPath = treePath;
//            } else {
//                this.targetPath = null;
//            }
//        } else {
//            // outgoing from vertex
//            if (treePath != null && treePath.equals(targetPath)) {
//                // drag over correct graph
//                
//            }
//        }
//        
//        return true;
//    }
    
    
    private Vertex findVertex(TreePath treePath, Graph graph, 
            int graphX, int graphY, int step) 
    {
        for (int i = graph.getVertexCount() - 1; i >= 0; i--) {
            Vertex vertex = graph.getVertex(i);
            if (vertex.dontContains(graphX, graphY, step)) continue;
            
            if (vertex.contains(graphX, graphY, step)) {
                if (activePins.contains(treePath, vertex)) {
                    return vertex;
                }
                
                return null;
            }
            
            if (activePins.contains(treePath, vertex)) {
                if (vertex.sourcePinContains(graphX, graphY, step)) {
                    return vertex;
                }
            }
        }
        
        return null;
    }
    
    
    private VertexItem findVertexItem(TreePath treePath, Graph graph,
            int graphX, int graphY, int step) 
    {
        for (int i = graph.getVertexCount() - 1; i >= 0; i--) {
            Vertex vertex = graph.getVertex(i);
            if (vertex.dontContains(graphX, graphY, step)) continue;
            
            if (vertex.contains(graphX, graphY, step)) {
                for (int j = vertex.getItemCount() - 1; j >= 0; j--) {
                    VertexItem item = vertex.getItem(j);
                    if (item.contains(graphX, graphY, step)) {
                        if (activePins.contains(treePath, item)) {
                            return item;
                        }
                        return null;
                    }
                }
                return null;
            }
            
            if (!(vertex instanceof Constant)) {
                for (int j = vertex.getItemCount() - 1; j >= 0; j--) {
                    VertexItem item = vertex.getItem(j);
                    if (activePins.contains(treePath, item) 
                            && item.targetPinContains(graphX, graphY, step))
                    {
                        return item;
                    }
                }
            }
        }
        
        return null;
    }

    
//    public boolean drag(JComponent component, DropTargetDragEvent dtde) {
//        if (!isActive()) return false;
//        if (!isValidTransferable(dtde.getTransferable())) return false;
//
//        LeftTree leftTree = getLeftTree();
//        RightTree rightTree = getRightTree();
//        Canvas canvas = getCanvas();
//        
//        Point p = dtde.getLocation();
//        
//        if (component == canvas) {
//            p.x = canvas.toGraph(p.x);
//            if (isIngoing()) {
//                SourcePin edgeSource = canvas.findEdgeSource(p);
//                setSource(edgeSource, component, p);
//            } else {
//                TargetPin edgeTarget = canvas.findEdgeTarget(p);
//                setTarget(edgeTarget, component, p);
//            }
//        } else if (component == leftTree) {
//            if (isIngoing()) {
//                TreeSourcePin edgeSource = leftTree.findEdgeSource(p, true);
//                setSource(edgeSource, component, p);
//            } else {
//                setTarget(null, component, p);
//                dtde.rejectDrag();
//            }
//        } else if (component == rightTree) {
//            if (isOutgoing()) {
//                Graph edgeTarget = rightTree.findEdgeTarget(p, true);
//                setTarget(edgeTarget, component, p);
//            } else {
//                setSource(null, component, p);
//                dtde.rejectDrag();
//            }
//        } else {
//            assert false;
//        }
//        
//        leftTree.repaint();
//        rightTree.repaint();
//        canvas.repaint();
//        return true;
//    }
//    
//    
//    
//    private boolean dragOverCanvas(Canvas canvas, 
//            DropTargetDragEvent dtde) 
//    {
//        Point point = dtde.getLocation();
//
//        int graphX = canvas.toGraph(point.x);
//        
//        MapperNode node = getNodeAt(point.y);
//        
//        TreePath treePath = null;
//        
//        if (node != null) {
//            treePath = node.getTreePath();
//        }
//        
//        Graph graph = node.getGraph();
//        
//        if (isIngoing()) {
//            // ingoing
//            if (treePath != null && treePath.equals(targetPath)) {
//                // drag over correct graph
//                
//            }
//        } else if (sourcePin instanceof TreeSourcePin) {
//            // outgoing from left tree
//            if (treePath != null && graph != null) {
//                this.targetPath = treePath;
//            } else {
//                this.targetPath = null;
//            }
//        } else {
//            // outgoing from vertex
//            if (treePath != null && treePath.equals(targetPath)) {
//                // drag over correct graph
//                
//            }
//        }
//    }
//    
//    
//    private void dragOverLeftTree(LeftTree leftTree, 
//            DropTargetDragEvent dtde) 
//    {
//        
//    }
//
//    
//    public Vertex findVertex(Graph graph, int x, int y, int step) {
//        for (int i = graph.getVertexCount() - 1; i >= 0; i--) {
//            Vertex vertex = graph.getVertex(i);
//            
//        }
//    }
//    
    
    public boolean drop(JComponent component, DropTargetDropEvent dtde) {
        if (!isActive()) return false;
        if (!isValidTransferable(dtde.getTransferable())) return false;
        
        MapperModel model = getMapperModel();
        
        if (sourcePin != null && targetPin != null && targetPath != null) {
            model.connect(targetPath, sourcePin, targetPin);
        }
        
        getMapper().repaint();
        return true;
    }
    
    
    public void dragDone() {
        if (!isActive()) return;
        reset();
        getMapper().repaint();
    }
    
    
    private void setSource(SourcePin sourcePin, JComponent c, Point p) {
        this.sourcePin = sourcePin;
        this.sourceComponent = c;
        this.x1 = p.x;
        this.y1 = p.y;
    }
    
    
    private void setTarget(TreePath targetPath, TargetPin targetPin, 
            JComponent c, Point p) 
    {
        this.targetPin = targetPin;
        this.targetComponent = c;
        this.x2 = p.x;
        this.y2 = p.y;
    }
    
    
    public void paintLeftTree(LeftTree tree, Graphics g) {
        if (!isActive()) return;
        if (sourcePin instanceof TreeSourcePin && targetComponent != tree) {
            Point sourcePoint = getSourcePoint();
            g.setColor(MapperStyle.LINK_COLOR_SELECTED_NODE);
            g.drawLine(sourcePoint.x, sourcePoint.y, 
                    tree.getWidth() - 1, sourcePoint.y);
        }
    }
    
    
    public void paintCanvas(Canvas canvas, Graphics g) {
        if (!isActive()) return;
        if (sourceComponent != targetComponent 
                || (sourceComponent == canvas && targetComponent == canvas)) {
            LeftTree leftTree = getLeftTree();
            RightTree rightTree = getRightTree();
            
            Point sourcePoint = getSourcePoint();
            Point targetPoint = getTargetPoint();
            
            Rectangle visibleRect = canvas.getViewport().getViewRect();
            
            boolean paintSourceDecoration = true;
            boolean paintTargetDecoration = true;
            
            if (sourceComponent == leftTree) {
                paintSourceDecoration = false;
                sourcePoint.y = canvas.yFromMapper(leftTree
                        .yToMapper(sourcePoint.y));
                sourcePoint.x = Integer.MIN_VALUE;
            } else if (sourceComponent == rightTree) {
                sourcePoint.y = canvas.yFromMapper(rightTree
                        .yToMapper(sourcePoint.y));
                sourcePoint.x = visibleRect.x + visibleRect.width;
            }
            
            if (targetComponent == rightTree) {
                paintTargetDecoration = false;
                targetPoint.y = canvas.yFromMapper(rightTree
                        .yToMapper(targetPoint.y));
                targetPoint.x = Integer.MAX_VALUE;
            } else if (targetComponent == leftTree) {
                targetPoint.y = canvas.yFromMapper(leftTree
                        .yToMapper(targetPoint.y));
                targetPoint.x = visibleRect.x;
            }
            
            int step = getMapper().getStepSize();
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            Link.paintLine(g2, MapperStyle.LINK_COLOR_SELECTED_NODE, null, 
                    sourcePoint, targetPoint, step,
                    visibleRect.x, visibleRect.x + visibleRect.width);
            
            if (paintSourceDecoration) {
                Link.paintSourceDecoration(g2, sourcePoint, null, step);
            }
            
            if (paintTargetDecoration) {
                Link.paintTargetDecoration(g2, targetPoint, null, step);
            }
            
            
            g2.dispose();
        }
    }
    
    
    public void paintRightTree(RightTree tree, Graphics g) {
        if (!isActive()) return;
        if (targetPin instanceof Graph && sourceComponent != tree) {
            Point targetPoint = getTargetPoint();
            g.setColor(MapperStyle.LINK_COLOR_SELECTED_NODE);
            
            
            int step = getMapper().getStepSize();
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.drawLine(0, targetPoint.y, targetPoint.x - step / 2, targetPoint.y);
            
            Link.paintTargetDecoration(g2, targetPoint, null, step);
            
            g2.dispose();
        }
    }
    
    
    private MapperCollection findActivePins() {
        return (isOutgoing()) 
                ? findActiveTargetPins()
                : findActiveSourcePins();
    }
    
    
    private MapperCollection findActiveSourcePins() {
        MapperModel model = getMapperModel();
        if (model != null) {
            if (model.getRoot() != null && targetPath != null 
                    && targetPin != null) 
            {
                Set<Vertex> verteces = new HashSet<Vertex>();
                findSourceVerteces(targetPin, model, targetPath, verteces);
                return MapperCollection.createVerteces(targetPath, verteces);
            }
        }
        return MapperCollection.create();
    }    
    
    
    private MapperCollection findActiveTargetPins() {
        MapperModel model = getMapperModel();
        if (model != null) {
            Object root = model.getRoot();
            if (root != null) { 
                if (sourcePin instanceof TreeSourcePin) {
                    Map<TreePath, Set<VertexItem>> vertexItems 
                            = new HashMap<TreePath, Set<VertexItem>>();
                    findTargetVertexItems((TreeSourcePin) sourcePin, 
                            model, new TreePath(root), vertexItems);
                    return MapperCollection.create(vertexItems);
                } else if (sourcePin instanceof Vertex && targetPath != null) {
                    Set<VertexItem> vertexItems = new HashSet<VertexItem>();
                    findTargetVertexItems((Vertex) sourcePin, model, 
                            targetPath, vertexItems);
                    return MapperCollection.createVertexItems(targetPath, 
                            vertexItems);
                }
            }
        }
        
        return MapperCollection.create();
    }
    
    
    private static void findSourceVerteces(TargetPin targetPin, 
            MapperModel model, TreePath treePath, Set<Vertex> result)
    {
        Graph graph = model.getGraph(treePath);

        if (graph != null) {
            for (int i = graph.getVertexCount() - 1; i >= 0; i--) {
                Vertex vertex = graph.getVertex(i);
                if (model.canConnect(treePath, vertex, targetPin)) {
                    result.add(vertex);
                }
            }
        }
    }
    
    
    private static void findTargetVertexItems(TreeSourcePin treeSourcePin, 
            MapperModel model, TreePath treePath, Map<TreePath, 
            Set<VertexItem>> result) 
    {
        Object parent = treePath.getLastPathComponent();
        Graph graph = model.getGraph(treePath);
        
        if (graph != null) {
            Set<VertexItem> items = new HashSet<VertexItem>();
            
            for (int i = graph.getVertexCount() - 1; i >= 0; i--) {
                Vertex vertex = graph.getVertex(i);
                if (!(vertex instanceof Constant)) {
                    for (int j = vertex.getItemCount() - 1; j >= 0; j--) {
                        VertexItem item = vertex.getItem(j);
                        if (model.canConnect(treePath, treeSourcePin, item)) {
                            items.add(item);
                        }
                    }
                }
            }
            
            if (!items.isEmpty()) {
                result.put(treePath, items);
            }
            
            items = null;
        }
        
        if (!model.isLeaf(parent) && model.searchGraphsInside(treePath)) {
            for (int i = model.getChildCount(parent) - 1; i >= 0; i--) {
                Object child = model.getChild(parent, i);
                findTargetVertexItems(treeSourcePin, model, 
                        new MapperTreePath(treePath, child), result);
            }
        }
    }
    
    
    private static void findTargetVertexItems(Vertex sourceVertex, MapperModel model, 
            TreePath treePath, Set<VertexItem> vertexItems) 
    {
        Graph graph = model.getGraph(treePath);
        
        if (graph != null) {
            for (int i = graph.getVertexCount() - 1; i >= 0; i--) {
                Vertex vertex = graph.getVertex(i);
                if (!(vertex instanceof Constant)) {
                    for (int j = vertex.getItemCount() - 1; j >= 0; j--) {
                        VertexItem item = vertex.getItem(j);
                        if (model.canConnect(treePath, sourceVertex, item)) {
                            vertexItems.add(item);
                        }
                    }
                }
            }
        }
    }
    
    
    private static class LinkTransferable implements Transferable {
        private Long uid;
        
        public LinkTransferable(long uid) {
            this.uid = new Long(uid);
        }
        
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { LINK_DATA_FLAVOR };
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor == LINK_DATA_FLAVOR;
        }

        public Object getTransferData(DataFlavor flavor) 
                throws UnsupportedFlavorException, IOException 
        {
            if (flavor == LINK_DATA_FLAVOR) return uid;
            throw new UnsupportedFlavorException(flavor);
        }
    }

    
    private static final DataFlavor LINK_DATA_FLAVOR = new DataFlavor(
            LinkTransferable.class, "MapperLinkToolUID");


    private static synchronized long createUID() {
        return UID++;
    }
    
    
    private static long UID = 0;
}
