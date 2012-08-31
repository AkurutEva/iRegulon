package view.resultspanel;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.data.Semantics;
import cytoscape.view.CyNetworkView;
import cytoscape.view.cytopanels.CytoPanel;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import domainmodel.AbstractMotif;
import domainmodel.CandidateTargetGene;
import domainmodel.GeneIdentifier;
import domainmodel.TranscriptionFactor;
import view.CytoscapeNetworkUtilities;
import view.ResourceAction;
import view.parametersform.IRegulonVisualStyle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class NetworkDrawAction extends ResourceAction {
    private static final Refreshable NO_VIEW = new Refreshable() {
        @Override
        public void refresh() {
            //Nop ...
            }
    };

    private final String attributeName;
    private final Refreshable view;

    public NetworkDrawAction(final String actionName, final Refreshable view, final String attributeName) {
        super(actionName);
        this.view = view == null ? NO_VIEW : view;
        this.attributeName = attributeName == null ? CytoscapeNetworkUtilities.ID_ATTRIBUTE_NAME : attributeName;

        //TODO: Implementation of undo functionality can be done via cytoscape.util.undo.CyUndo: a tiny class
        // for supporting undo in the Cytoscape context. If you want to post an edit, use
        // CyUndo.getUndoableEditSupport().postEdit(yourEdit) where yourEdit implements the UndoableEdit.
        // Every implementing action needs to use basic operations like addNode, addEdge which
        // are implemented in this abstract class. Adding undo functionality is just adding
        // the creation of UndoableEdit objects, combining them in a composite UndoableEdit and
        // registering this compound object with Cytoscape.
    }

    public Refreshable getView() {
        return view;
    }

    public String getAttributeName() {
        return attributeName;
    }

    protected CyNode addNode(String nodeID, CyNetwork network, CyNetworkView view) {
        return CytoscapeNetworkUtilities.addNode(nodeID, network, view);
    }

    protected void addNodeAttribute(CyNode node, String attributeName, String attributeValue) {
        CytoscapeNetworkUtilities.addNodeAttribute(node, attributeName, attributeValue);
    }

    protected void setNodeAttribute(CyNode node, String attributeName, String attributeValue) {
        CytoscapeNetworkUtilities.setNodeAttribute(node, attributeName, attributeValue);
    }

    protected CyEdge addEdge(CyNode node1, CyNode node2, CyNetwork network, CyNetworkView view, String motif){
		return CytoscapeNetworkUtilities.addEdge(node1, node2, network, view, motif);
	}

    protected void setEdgeAttribute(CyEdge edge, String attributeName, String attributeValue){
		CytoscapeNetworkUtilities.setEdgeAttribute(edge, attributeName, attributeValue);
	}

    protected void setEdgeAttribute(CyEdge edge, String attributeName, int attributeValue){
		CytoscapeNetworkUtilities.setEdgeAttribute(edge, attributeName, attributeValue);
	}

    protected void addEdgeAttribute(CyEdge edge, String attributeName, String attributeValue) {
        CytoscapeNetworkUtilities.addEdgeAttribute(edge, attributeName, attributeValue);
    }

    protected CyEdge createEdge(final CyNode sourceNode, final CyNode targetNode, final TranscriptionFactor factor, final AbstractMotif motif, final GeneIdentifier targetGene) {
        return CytoscapeNetworkUtilities.createEdge(sourceNode, targetNode, factor, motif, targetGene);
    }

    protected CyNode createSourceNode(final CyNetwork network, final CyNetworkView view, final GeneIdentifier factorID, final AbstractMotif motif) {
        return CytoscapeNetworkUtilities.createSourceNode(network, view, getAttributeName(), factorID, motif);
    }

    protected CyNode createSourceNode(final CyNetwork network, final CyNetworkView view, final TranscriptionFactor factor, final AbstractMotif motif) {
        return createSourceNode(network, view, factor.getGeneID(), motif);
    }

    protected CyNode createTargetNode(final CyNetwork network, final CyNetworkView view, final CandidateTargetGene targetGene, final AbstractMotif motif) {
        return CytoscapeNetworkUtilities.createTargetNode(network, view, getAttributeName(), targetGene, motif);
    }
}