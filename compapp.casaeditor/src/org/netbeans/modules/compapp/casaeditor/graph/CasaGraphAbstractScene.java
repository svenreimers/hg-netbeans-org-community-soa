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

package org.netbeans.modules.compapp.casaeditor.graph;

import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.compapp.casaeditor.Utilities;

import java.util.*;



/**
 * This class holds and manages graph-oriented model.
 * <p>
 * In comparison with the GraphScene class, in this class the graph consists of nodes, edges and pins. Each pin is assigned to a node (the assigned cannot be change at any time.
 * Each edge could be attach to a single source and target pin.
 * <p>
 * The class is abstract and manages only data model and mapping with widgets. The graphics (widgets) has to be supplied
 * by a developer by overriding the attachNodeWidget, attachPinWidget, attachEdgeWidget, attachEdgeSourceAnchor and attachEdgeTargetAnchor abstract methods.
 * <p>
 * This class is using generics and allows you to specify type representation for nodes and edges in the graph model. Example:
 * <pre>
 * class MyGraph extends GraphScene&lt;MyNode, MyEdge, MyPin&gt; { ... }
 * </pre>
 * Since the type of nodes, edges and pins could be the same, all node, edge and pin instances have to be unique within the whole scene.
 *
 * @author David Kaspar
 */
// TODO - is it asserted that removing a node removes all its pins
// TODO - is it asserted that removing a pin disconnects all the attached edges
public abstract class CasaGraphAbstractScene<N, E, P, G> extends ObjectScene {
    
    private HashSet<N> regions = new HashSet<N> ();
    private Set<N> regionsUm = Collections.unmodifiableSet(regions);
    private HashSet<N> nodes = new HashSet<N> ();
    private Set<N> nodesUm = Collections.unmodifiableSet(nodes);
    private HashSet<E> edges = new HashSet<E> ();
    private Set<E> edgesUm = Collections.unmodifiableSet(edges);
    private HashSet<P> pins = new HashSet<P> ();
    private Set<P> pinsUm = Collections.unmodifiableSet(pins);
    private HashSet<G> processes = new HashSet<G> ();
    private Set<G> processesUm = Collections.unmodifiableSet(processes);
    
    private HashMap<N, HashSet<P>> nodePins = new HashMap<N, HashSet<P>> ();
    private HashMap<N, HashSet<G>> nodeProcesses = new HashMap<N, HashSet<G>> ();
    private HashMap<P, N> pinNodes = new HashMap<P, N> ();
    private HashMap<G, N> processNodes = new HashMap<G, N> ();
    private HashMap<E, P> edgeSourcePins = new HashMap<E, P> ();
    private HashMap<E, P> edgeTargetPins = new HashMap<E, P> ();
    private HashMap<P, List<E>> pinInputEdges = new HashMap<P, List<E>> ();
    private HashMap<P, List<E>> pinOutputEdges = new HashMap<P, List<E>> ();
    
    
    /**
     * Creates a graph scene.
     */
    public CasaGraphAbstractScene() {
    }
    
    
    public Widget addRegion(N region) {
        assert region != null;
        assert !regions.contains(region);
        Widget widget = attachRegionWidget(region);
        addObject(region, widget);
        regions.add(region);
        notifyRegionAdded(region, widget);
        return widget;
    }
    
    public final void removeRegion(N region) {
        assert region != null;
        assert regions.contains(region);
        regions.remove(region);
        Widget widget = findWidget(region);
        removeObject(region);
        detachRegionWidget(region, widget);
    }
    
    /**
     * Returns a collection of all regions registered in the graph model.
     * @return the collection of all regions registered in the graph model
     */
    public final Collection<N> getRegions() {
        return regionsUm;
    }
    
    public Widget addNode(N node) {
        assert node != null;
        assert !nodes.contains(node);
        Widget widget = attachNodeWidget(node);
        addObject(node, widget);
        nodes.add(node);
        nodePins.put(node, new HashSet<P> ());
        nodeProcesses.put(node, new HashSet<G> ());
        notifyNodeAdded(node, widget);
        return widget;
    }
    
    /**
     * Removes a node with all pins that are assigned to the node.
     * @param node the node to be removed; the node must not be null and must be already in the model
     */
    public final void removeNode(N node) {
        assert node != null;
        assert nodes.contains(node);
        if (nodePins.containsKey(node)) {
            for (P pin : new HashSet<P> (nodePins.get(node))) {
                removePin(pin);
            }
            nodePins.remove(node);
        }
        if (nodeProcesses.containsKey(node)) {
            for (G process : new HashSet<G> (nodeProcesses.get(node))) {
                removeProcess(process);
            }
            nodeProcesses.remove(node);
        }
        nodes.remove(node);
        Widget widget = findWidget(node);
        removeObject(node);
        detachNodeWidget(node, widget);
    }

    /**
     * refresh a node annotations, e.g., bagdes and labels
     * @param node
     */
    public final void refreshNode(N node) {
        assert node != null;
        assert nodes.contains(node);
        Widget widget = findWidget(node);
        refreshWidgetBadge(node, widget);
    }

    /**
     * Returns a collection of all nodes registered in the graph model.
     * @return the collection of all nodes registered in the graph model
     */
    public final Collection<N> getNodes() {
        return nodesUm;
    }
    
    /**
     * Returns a collection of all processes registered in the graph model.
     * 
     * @return  the collection of all processes registered in the graph model
     */
    public final Collection<G> getProcesses() {
        return processesUm;
    }
    
    /**
     * Adds an edge.
     * @param edge the edge to be added; the edge must not be null, must not be already in the model and must be unique in the model
     *           (means: there is no other node, edge or pin in the model has is equal to this edge)
     * @return the widget that is created by attachEdgeWidget; null if the edge is non-visual
     */
    public final Widget addEdge(E edge) {
        assert edge != null;
        assert !edges.contains(edge);
        Widget widget = attachEdgeWidget(edge);
        addObject(edge, widget);
        edges.add(edge);
        notifyEdgeAdded(edge, widget);
        return widget;
    }
    
    /**
     * Removes an edge and detaches it from its source and target pins.
     * @param edge the edge to be removed; the edge must not be null and must be already in the model
     */
    public final void removeEdge(E edge) {
        assert edge != null;
        assert edges.contains(edge);
        setEdgeSource(edge, null);
        setEdgeTarget(edge, null);
        edges.remove(edge);
        edgeSourcePins.remove(edge);
        edgeTargetPins.remove(edge);
        Widget widget = findWidget(edge);
        removeObject(edge);
        detachEdgeWidget(edge, widget);
    }
    
    /**
     * Returns a collection of all edges registered in the graph model.
     * @return the collection of all edges registered in the graph model
     */
    public final Collection<E> getEdges() {
        return edgesUm;
    }
    
    /**
     * Adds a pin and assigns it to a specified node.
     * @param node the node where the pin is assigned to
     * @param pin the pin to be added; the pin must not be null, must not be already in the model and must be unique in the model
     *           (means: there is no other node, edge or pin in the model has is equal to this pin)
     * @return the widget that is created by attachPinWidget; null if the pin is non-visual
     */
    public final Widget addPin(N node, P pin) {
        assert node != null;
        assert pin != null;
        assert !pins.contains(pin);
        Widget widget = attachPinWidget(node, pin);
        addObject(pin, widget);
        pins.add(pin);
        nodePins.get(node).add(pin);
        pinNodes.put(pin, node);
        pinInputEdges.put(pin, new ArrayList<E> ());
        pinOutputEdges.put(pin, new ArrayList<E> ());
        notifyPinAdded(node, pin, widget);
        return widget;
    }
        
    /**
     * Removes an pin and detaches all edges that are connected to it.
     * @param pin the pin to be removed; the pin must not be null and must be already in the model
     */
    public final void removePin(P pin) {
        assert pin != null;
        assert pins.contains(pin);
        for (E edge : findPinEdges(pin, true, false))
            setEdgeSource(edge, null);
        for (E edge : findPinEdges(pin, false, true))
            setEdgeTarget(edge, null);
        pins.remove(pin);
        N node = pinNodes.remove(pin);
        nodePins.get(node).remove(pin);
        pinInputEdges.remove(pin);
        pinOutputEdges.remove(pin);
        Widget widget = findWidget(pin);
        removeObject(pin);
        detachPinWidget(pin, widget);
    }
    
    
    /**
     * Adds a process and assigns it to a specified node.
     * 
     * @param node  the node where the process is assigned to
     * @param process   the process to be added; the process must not be null, 
     *          must not be already in the model and must be unique in the model
     * 
     * @return the widget that is created by addProcess
     */
    public final Widget addProcess(N node, G process) {
        assert node != null;
        assert process != null;
        Widget widget = attachProcessWidget(node, process);
        //System.out.println("add " + process);
        addObject(process, widget);
        processes.add(process);
        nodeProcesses.get(node).add(process);
        processNodes.put(process, node);
        return widget;
    }
    
    /**
     * Removes a process and detach it.
     * 
     * @param process   the process to be removed; the process must not be null
     *                  and must be already in the model
     */
    public final void removeProcess(G process) {
        assert process != null;
        assert processes.contains(process);
        processes.remove(process);
        N node = processNodes.remove(process);
        nodeProcesses.get(node).remove(process);
        Widget widget = findWidget(process);
        removeObject(process);
        //System.out.println("remove " + group);
        detachGroupWidget(process, widget);
    }
    
    /**
     * Returns a node where the pin assigned to.
     * @param pin the pin
     * @return the node where the pin assigned to.
     */
    public final N getPinNode(P pin) {
        return pinNodes.get(pin);
    }
    
    /**
     * Returns all pins registered in the graph model.
     * @return the collection of all pins registered in the graph model
     */
    public final Collection<P> getPins() {
        return pinsUm;
    }
    
    /**
     * Returns a collection of pins that are assigned to a specified node
     * @param node the node
     * @return the collection of pins
     */
    public final Collection<P> getNodePins(N node) {
        if (node == null)
            return null;
        HashSet<P> ps = nodePins.get(node);
        if (ps == null)
            return null;
        return Collections.unmodifiableCollection(ps);
    }
    
    /**
     * Sets an edge source.
     * @param edge the edge which source is going to be changed
     * @param sourcePin the source pin; if null, then the edge source will be detached
     */
    public final void setEdgeSource(E edge, P sourcePin) {
        assert edge != null;
        assert edges.contains(edge);
        if (sourcePin != null)
            assert pins.contains(sourcePin);
        P oldPin = edgeSourcePins.put(edge, sourcePin);
        if (Utilities.equals(oldPin, sourcePin))
            return;
        if (oldPin != null)
            pinOutputEdges.get(oldPin).remove(edge);
        if (sourcePin != null)
            pinOutputEdges.get(sourcePin).add(edge);
        attachEdgeSourceAnchor(edge, oldPin, sourcePin);
    }
    
    /**
     * Sets an edge target.
     * @param edge the edge which target is going to be changed
     * @param targetPin the target pin; if null, then the edge target will be detached
     */
    public final void setEdgeTarget(E edge, P targetPin) {
        assert edge != null;
        assert edges.contains(edge);
        if (targetPin != null)
            assert pins.contains(targetPin);
        P oldPin = edgeTargetPins.put(edge, targetPin);
        if (Utilities.equals(oldPin, targetPin))
            return;
        if (oldPin != null)
            pinInputEdges.get(oldPin).remove(edge);
        if (targetPin != null)
            pinInputEdges.get(targetPin).add(edge);
        attachEdgeTargetAnchor(edge, oldPin, targetPin);
    }
    
    /**
     * Returns an edge source.
     * @param edge the edge
     * @return the edge source; null, if edge does not have source attached
     */
    public final P getEdgeSource(E edge) {
        return edgeSourcePins.get(edge);
    }
    
    /**
     * Returns an edge target.
     * @param edge the edge
     * @return the edge target; null, if edge does not have target attached
     */
    public final P getEdgeTarget(E edge) {
        return edgeTargetPins.get(edge);
    }
    
    /**
     * Returns a collection of edges that are attached to a specified pin.
     * @param pin the pin which edges connections are searched for
     * @param allowOutputEdges if true, the output edges are included in the collection; if false, the output edges are not included
     * @param allowInputEdges if true, the input edges are included in the collection; if false, the input edges are not included
     * @return the collection of edges
     */
    public final Collection<E> findPinEdges(P pin, boolean allowOutputEdges, boolean allowInputEdges) {
        ArrayList<E> list = new ArrayList<E> ();
        if (allowInputEdges)
            list.addAll(pinInputEdges.get(pin));
        if (allowOutputEdges)
            list.addAll(pinOutputEdges.get(pin));
        return list;
    }
    
    public final Collection<E> findEdgesBetween(P sourcePin, P targetPin) {
        HashSet<E> list = new HashSet<E> ();
        List<E> inputEdges = pinInputEdges.get(targetPin);
        List<E> outputEdges = pinOutputEdges.get(sourcePin);
        for (E edge : inputEdges)
            if (outputEdges.contains(edge))
                list.add(edge);
        return list;
    }
    
    /**
     * Checks whether an object is registered as a node in the graph model.
     * @param object the object
     * @return true, if the object is registered as a node
     */
    public boolean isNode(Object object) {
        return nodes.contains(object);
    }
    
    /**
     * Checks whether an object is registered as an edge in the graph model.
     * @param object the object
     * @return true, if the object is registered as a edge
     */
    public boolean isEdge(Object object) {
        return edges.contains(object);
    }
    
    /**
     * Checks whether an object is registered as a pin in the graph model.
     * @param object the object
     * @return true, if the object is registered as a pin
     */
    public boolean isPin(Object object) {
        return pins.contains(object);
    }
    
    /**
     * Called by the addRegion method to notify that a region is added into the graph model.
     * @param region the added region
     * @param widget the widget created by the attachRegionWidget method as a visual representation of the region
     */
    protected void notifyRegionAdded(N region, Widget widget) {
    }
    
    /**
     * Called by the addNode method to notify that a node is added into the graph model.
     * @param node the added node
     * @param widget the widget created by the attachNodeWidget method as a visual representation of the node
     */
    protected void notifyNodeAdded(N node, Widget widget) {
    }
    
    /**
     * Called by the addEdge method to notify that an edge is added into the graph model.
     * @param edge the added node
     * @param widget the widget created by the attachEdgeWidget method as a visual representation of the edge
     */
    protected void notifyEdgeAdded(E edge, Widget widget) {
    }
    
    /**
     * Called by the addPin method to notify that a pin is added into the graph model.
     * @param node the node where the pin is assigned to
     * @param pin the added pin
     * @param widget the widget created by the attachPinWidget method as a visual representation of the pin
     */
    protected void notifyPinAdded(N node, P pin, Widget widget) {
    }
    
    /**
     * Called by the removeRegion method to notify that a region is removed from the graph model.
     * The default implementation removes the region widget from its parent widget.
     * @param region the removed region
     * @param widget the removed region widget; null if the region is non-visual
     */
    protected void detachRegionWidget(N region, Widget widget) {
        if (widget != null)
            widget.removeFromParent();
    }
    
    /**
     * Called by the removeNode method to notify that a node is removed from the graph model.
     * The default implementation removes the node widget from its parent widget.
     * @param node the removed node
     * @param widget the removed node widget; null if the node is non-visual
     */
    protected void detachNodeWidget(N node, Widget widget) {
        if (widget != null)
            widget.removeFromParent();
    }
    
    /**
     * Called by the removeEdge method to notify that an edge is removed from the graph model.
     * The default implementation removes the edge widget from its parent widget.
     * @param edge the removed edge
     * @param widget the removed edge widget; null if the edge is non-visual
     */
    protected void detachEdgeWidget(E edge, Widget widget) {
        if (widget != null)
            widget.removeFromParent();
    }
    
    /**
     * Called by the removePin method to notify that a pin is removed from the graph model.
     * The default implementation removes the pin widget from its parent widget.
     * @param pin the removed pin
     * @param widget the removed pin widget; null if the pin is non-visual
     */
    protected void detachPinWidget(P pin, Widget widget) {
        if (widget != null)
            widget.removeFromParent();
    }
    
    protected void detachGroupWidget(G group, Widget widget) {
        if (widget != null)
            widget.removeFromParent();
    }
    
    
    /**
     * Called by the addRegion method before the region is registered to acquire a widget that is going to represent the region in the scene.
     * The method is responsible for creating the widget, adding it into the scene and returning it from the method.
     * @param region the region that is going to be added
     * @return the widget representing the region; null, if the node is non-visual
     */
    protected abstract Widget attachRegionWidget(N region);
    
    /**
     * Called by the addNode method before the node is registered to acquire a widget that is going to represent the node in the scene.
     * The method is responsible for creating the widget, adding it into the scene and returning it from the method.
     * @param node the node that is going to be added
     * @return the widget representing the node; null, if the node is non-visual
     */
    protected abstract Widget attachNodeWidget(N node);
    
    /**
     * Called by the addEdge method before the edge is registered to acquire a widget that is going to represent the edge in the scene.
     * The method is responsible for creating the widget, adding it into the scene and returning it from the method.
     * @param edge the edge that is going to be added
     * @return the widget representing the edge; null, if the edge is non-visual
     */
    protected abstract Widget attachEdgeWidget(E edge);
    
    /**
     * Called by the addPin method before the pin is registered to acquire a widget that is going to represent the pin in the scene.
     * The method is responsible for creating the widget, adding it into the scene and returning it from the method.
     * @param node the node where the pin is assigned to
     * @param pin the pin that is going to be added
     * @return the widget representing the pin; null, if the pin is non-visual
     */
    protected abstract Widget attachPinWidget(N node, P pin);
    
    /**
     * Called by the addProcess method before the process is registered to 
     * acquire a widget that is going to represent the process in the scene.
     * The method is responsible for creating the widget, adding it into 
     * the scene and returning it from the method.
     * 
     * @param node      the node where the process is assigned to
     * @param process   the process that is going to be added
     * 
     * @return the widget representing the process
     */
    protected abstract Widget attachProcessWidget(N node, G process);
    
    /**
     * Called by the setEdgeSource method to notify about the changing the edge source in the graph model.
     * The method is responsible for attaching a new source pin to the edge in the visual representation.
     * <p>
     * Usually it is implemented as:
     * <pre>
     * Widget sourcePinWidget = findWidget (sourcePin);
     * Anchor sourceAnchor = AnchorFactory.createRectangularAnchor (sourcePinWidget)
     * ConnectionWidget edgeWidget = (ConnectionWidget) findWidget (edge);
     * edgeWidget.setSourceAnchor (sourceAnchor);
     * </pre>
     * @param edge the edge which source is changed in graph model
     * @param oldSourcePin the old source pin
     * @param sourcePin the new source pin
     */
    protected abstract void attachEdgeSourceAnchor(E edge, P oldSourcePin, P sourcePin);
    
    /**
     * Called by the setEdgeTarget method to notify about the changing the edge target in the graph model.
     * The method is responsible for attaching a new target pin to the edge in the visual representation.
     * <p>
     * Usually it is implemented as:
     * <pre>
     * Widget targetPinWidget = findWidget (targetPin);
     * Anchor targetAnchor = AnchorFactory.createRectangularAnchor (targetPinWidget)
     * ConnectionWidget edgeWidget = (ConnectionWidget) findWidget (edge);
     * edgeWidget.setTargetAnchor (targetAnchor);
     * </pre>
     * @param edge the edge which target is changed in graph model
     * @param oldTargetPin the old target pin
     * @param targetPin the new target pin
     */
    protected abstract void attachEdgeTargetAnchor(E edge, P oldTargetPin, P targetPin);
    
    /**
     * This method is called when the selection has changed.
     * By the time this method is called, the widgets in the scene are already
     * visually selected. This method can be used to update external state.
     */
    protected abstract void fireSelectionChanged();

    /**
     * This methos is used to refresh the dynamic status of a widget, e.g., badge or
     * annotation information not stored in the wrapper model
     * @param widget the widget to be refreshed
     */
    protected abstract void refreshWidgetBadge(N node, Widget widget);

    @Override
    public void userSelectionSuggested(Set<?> suggestedSelectedObjects, boolean invertSelection) {
        super.userSelectionSuggested(suggestedSelectedObjects, invertSelection);
        fireSelectionChanged();
    }
}
