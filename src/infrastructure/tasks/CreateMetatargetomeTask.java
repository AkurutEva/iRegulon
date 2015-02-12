package infrastructure.tasks;


import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.layout.CyLayouts;
import cytoscape.view.CyNetworkView;
import domainmodel.CandidateTargetGene;
import domainmodel.GeneIdentifier;
import infrastructure.CytoscapeNetworkUtilities;
import view.Refreshable;

import java.util.List;


public class CreateMetatargetomeTask extends MetatargetomeTask {
    public CreateMetatargetomeTask(CyNetwork network, CyNetworkView view, final Refreshable resultsPanel,
                            final String attributeName, GeneIdentifier factor, List<CandidateTargetGene> targetome) {
        super(network, view, resultsPanel, attributeName, factor, targetome);
    }

    @Override
    public void run() {
        if (getMonitor() != null) getMonitor().setStatus("Adding nodes and edges");

        final CyNode sourceNode = CytoscapeNetworkUtilities.createSourceNode(getNetwork(), getView(),
                getAttributeName(), getTranscriptionFactor(), NO_MOTIF);
        for (int idx = 0; idx < getTargetome().size(); idx++) {
            if (isInterrupted()) return;
            if (getMonitor() != null) getMonitor().setPercentCompleted((100 * idx) / getTargetome().size());
            final CandidateTargetGene targetGene = getTargetome().get(idx);

            final CyNode targetNode = CytoscapeNetworkUtilities.createTargetNode(getNetwork(), getView(), getAttributeName(), targetGene, NO_MOTIF);
            final CyEdge edge = CytoscapeNetworkUtilities.createEdge(getNetwork(), getView(), sourceNode, targetNode, getTranscriptionFactor(), null, getTargetome().get(idx).getGeneID(), CytoscapeNetworkUtilities.REGULATORY_FUNCTION_METATARGETOME);
            CytoscapeNetworkUtilities.setEdgeAttribute(edge, CytoscapeNetworkUtilities.STRENGTH_ATTRIBUTE_NAME, targetGene.getRank());
        }

        Cytoscape.getEdgeAttributes().setUserVisible(CytoscapeNetworkUtilities.FEATURE_ID_ATTRIBUTE_NAME, false);
        getView().applyLayout(CyLayouts.getDefaultLayout());
        CytoscapeNetworkUtilities.applyVisualStyle();
        getView().redrawGraph(true, true);
        if (getResultsPanel() != null) getResultsPanel().refresh();
        CytoscapeNetworkUtilities.activeSidePanel();
    }
}