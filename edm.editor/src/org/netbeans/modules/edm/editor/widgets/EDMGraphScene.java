/*
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (the License). You may not use this 
 * file except in compliance with the License.  You can obtain a copy of the
 *  License at http://www.netbeans.org/cddl.html
 *
 * When distributing Covered Code, include this CDDL Header Notice in each
 * file and include the License. If applicable, add the following below the
 * CDDL Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2006 Sun Microsystems, Inc. All Rights Reserved
 *
 */
package org.netbeans.modules.edm.editor.widgets;

import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.graph.GraphPinScene;
import org.netbeans.api.visual.graph.layout.GraphLayout;
import org.netbeans.api.visual.graph.layout.GraphLayoutFactory;
import org.netbeans.api.visual.graph.layout.GraphLayoutSupport;
import org.netbeans.api.visual.graph.layout.GridGraphLayout;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.layout.SceneLayout;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.model.StateModel;
import org.netbeans.api.visual.router.Router;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.EventProcessingType;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.edm.editor.dataobject.MashupDataObject;
import org.openide.windows.WindowManager;

/**
 * This class represents a GraphPinScene for the EDM visualization style. Nodes, edges and pins are represented using String class.
 * The visualization is done by: EDMNodeWidget for nodes, EDMPinWidget for pins, ConnectionWidget for edges.
 * <p>
 * The scene has 4 layers: background, main, connection, upper.
 * <p>
 * The scene has following actions: zoom, panning, rectangular selection.
 * 
 * 
 * 
 * @author David Kaspar
 */
// TODO - remove popup menu action
public class EDMGraphScene extends GraphPinScene<String, String, String> {

    public static final String PIN_ID_DEFAULT_SUFFIX = "#default"; // NOI18N
    private LayerWidget backgroundLayer = new LayerWidget(this);
    private LayerWidget mainLayer = new LayerWidget(this);
    private LayerWidget connectionLayer = new LayerWidget(this);
    private LayerWidget upperLayer = new LayerWidget(this);
    protected LayerWidget interactionLayer = new LayerWidget(this);

    private Router router;

    private WidgetAction moveControlPointAction = ActionFactory.createOrthogonalMoveControlPointAction ();
    private WidgetAction moveAction = ActionFactory.createMoveAction ();

    private GraphLayout<String, String> graphLayout;
    private SceneLayout sceneLayout;

    /**
     * Creates a VMD graph scene.
     */
    public EDMGraphScene() {
        setKeyEventProcessingType(EventProcessingType.FOCUSED_WIDGET_AND_ITS_PARENTS);

        addChild(backgroundLayer);
        addChild(mainLayer);
        addChild(connectionLayer);
        addChild(upperLayer);
        addChild(interactionLayer);

        router = RouterFactory.createOrthogonalSearchRouter(mainLayer, connectionLayer);

        //getActions ().addAction (ActionFactory.createSelectAction(new CreateProvider()));
        getActions().addAction(ActionFactory.createZoomAction());
        getActions().addAction(ActionFactory.createPanAction());
        
        sceneLayout = LayoutFactory.createSceneGraphLayout(this, new GridGraphLayout<String, String>().setChecker(false).setGaps(500, 50));

        /*graphLayout = GraphLayoutFactory.createTreeGraphLayout(0, 100, 100, 150, false);
        sceneLayout = LayoutFactory.createSceneGraphLayout(this,graphLayout);*/
    }

    /**
     * Implements attaching a widget to a node. The widget is EDMNodeWidget and has object-hover, select and move actions.
     * 
     * @param node the node
     * @return the widget attached to the node
     */
    protected Widget attachNodeWidget(String node) {
        GraphLayoutSupport.setTreeGraphLayoutRootNode(graphLayout, node);
        EDMNodeWidget widget = new EDMNodeWidget(this);
        widget.getHeader().getActions().addAction(createObjectHoverAction());
        widget.getActions().addAction(createSelectAction());
        widget.getActions().addAction(moveAction);
        mainLayer.addChild(widget);
        revalidate();
        /*widget.getHeader ().getActions ().addAction (createObjectHoverAction ());
        widget.getActions ().addAction (createSelectAction ());
        widget.getActions ().addAction (moveAction);*/

        return widget;
    }

    /**
     * Implements attaching a widget to a pin. The widget is EDMPinWidget and has object-hover and select action.
     * The the node id ends with "#default" then the pin is the default pin of a node and therefore it is non-visual.
     * 
     * @param node the node
     * @param pin the pin
     * @return the widget attached to the pin, null, if it is a default pin
     */
    protected Widget attachPinWidget(String node, String pin) {
        GraphLayoutSupport.setTreeGraphLayoutRootNode(graphLayout, node);
        if (pin.endsWith(PIN_ID_DEFAULT_SUFFIX)) {
            return null;
        }
        EDMPinWidget widget = new EDMPinWidget (this);
        ((EDMNodeWidget) findWidget (node)).attachPinWidget (widget);
        revalidate();
        widget.getActions().addAction(ActionFactory.createConnectAction(interactionLayer,
                new SceneConnectProvider(this)));//createExtendedConnectAction to use with CTRL key
        widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
        widget.getActions().addAction(createObjectHoverAction());
        widget.getActions().addAction(createSelectAction());
        widget.getActions().addAction(moveAction);
        widget.getActions().addAction(moveControlPointAction);
        return widget;
    }

    /**
     * Implements attaching a widget to an edge. the widget is ConnectionWidget and has object-hover, select and move-control-point actions.
     * @param edge the edge
     * @return the widget attached to the edge
     */
    EDMConnectionWidget connectionWidget;

    protected Widget attachEdgeWidget(String edge) {
        //connectionWidget = new EDMConnectionWidget(this, router);
        connectionWidget.getActions().addAction(createObjectHoverAction());
        connectionWidget.getActions().addAction(createSelectAction());
        connectionWidget.getActions().addAction(moveControlPointAction);
        connectionLayer.addChild(connectionWidget);
        revalidate();
        return connectionWidget;
    }

    /**
     * Attaches an anchor of a source pin an edge.
     * The anchor is a ProxyAnchor that switches between the anchor attached to the pin widget directly and
     * the anchor attached to the pin node widget based on the minimize-state of the node.
     * @param edge the edge
     * @param oldSourcePin the old source pin
     * @param sourcePin the new source pin
     */
    protected void attachEdgeSourceAnchor (String edge, String oldSourcePin, String sourcePin) {
        ((ConnectionWidget) findWidget (edge)).setSourceAnchor (getPinAnchor (sourcePin));
    }

    /**
     * Attaches an anchor of a target pin an edge.
     * The anchor is a ProxyAnchor that switches between the anchor attached to the pin widget directly and
     * the anchor attached to the pin node widget based on the minimize-state of the node.
     * @param edge the edge
     * @param oldTargetPin the old target pin
     * @param targetPin the new target pin
     */
    protected void attachEdgeTargetAnchor (String edge, String oldTargetPin, String targetPin) {
        ((ConnectionWidget) findWidget (edge)).setTargetAnchor (getPinAnchor (targetPin));
    }

    private Anchor getPinAnchor (String pin) {
        EDMNodeWidget nodeWidget = (EDMNodeWidget) findWidget (getPinNode (pin));
        Widget pinMainWidget = findWidget (pin);
        Anchor anchor;
        if (pinMainWidget != null) {
            anchor = AnchorFactory.createDirectionalAnchor (pinMainWidget, AnchorFactory.DirectionalAnchorKind.HORIZONTAL, 8);
            anchor = nodeWidget.createAnchorPin (anchor);
        } else
            anchor = nodeWidget.getNodeAnchor ();
        return anchor;
    }

    /**
     * Invokes layout of the scene.
     */
    public void layoutScene (boolean isInitial) {
        revalidate();
        if(isInitial) {
            SceneLayout devolveLayout = LayoutFactory.createDevolveWidgetLayout(
                    mainLayer, LayoutFactory.createHorizontalFlowLayout (), true);
            devolveLayout.invokeLayout();
        } else {
            sceneLayout.invokeLayout ();
        }
    }

    private class SceneConnectProvider implements ConnectProvider {

        private EDMGraphScene scene;

        SceneConnectProvider(EDMGraphScene scene) {
            this.scene = scene;
}

        public boolean isSourceWidget(Widget sourceWidget) {
            return true;
        }

        public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) {
            return sourceWidget != targetWidget && targetWidget instanceof EDMPinWidget ? ConnectorState.ACCEPT : ConnectorState.REJECT_AND_STOP;
        }

        public boolean hasCustomTargetWidgetResolver(Scene scene) {
            return false;
        }

        public Widget resolveTargetWidget(Scene scene, Point sceneLocation) {
            return null;
        }

        public void createConnection(Widget sourceWidget, Widget targetWidget) {            
            connectionWidget = new EDMConnectionWidget(scene, router);
            connectionWidget.setSourceAnchor(AnchorFactory.createDirectionalAnchor(sourceWidget, AnchorFactory.DirectionalAnchorKind.HORIZONTAL, 8));//.createRectangularAnchor(sourceWidget));
            connectionWidget.setTargetAnchor(AnchorFactory.createDirectionalAnchor(targetWidget, AnchorFactory.DirectionalAnchorKind.HORIZONTAL, 8));//.createRectangularAnchor(targetWidget));
            connectionLayer.addChild(connectionWidget);
            //setEdgeSource(PIN_ID_DEFAULT_SUFFIX, PIN_ID_DEFAULT_SUFFIX);
        }
    }
}