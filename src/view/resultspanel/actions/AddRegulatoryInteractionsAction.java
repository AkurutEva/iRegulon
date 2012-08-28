package view.resultspanel.actions;


import cytoscape.CyNetwork;
import cytoscape.view.CyNetworkView;
import domainmodel.AbstractMotif;
import domainmodel.TranscriptionFactor;
import view.resultspanel.Refreshable;
import view.resultspanel.TFComboBox;
import view.resultspanel.TranscriptionFactorDependentAction;
import view.resultspanel.SelectedMotif;

import java.awt.event.ActionEvent;

import cytoscape.Cytoscape;



public class AddRegulatoryInteractionsAction extends TranscriptionFactorDependentAction {
    private static final String NAME = "action_draw_edges";

    public AddRegulatoryInteractionsAction(SelectedMotif selectedMotif, final TFComboBox selectedTranscriptionFactor,
                                              final Refreshable view) {
		super(NAME, selectedMotif, selectedTranscriptionFactor, view);
		if (selectedMotif == null) throw new IllegalArgumentException();
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        final AbstractMotif motif = this.getSelectedMotif().getMotif();
		final TranscriptionFactor factor = this.getTranscriptionFactor();

		final CyNetwork network =  Cytoscape.getCurrentNetwork();
		final CyNetworkView view = Cytoscape.getCurrentNetworkView();

		if (!addEdges(network, view, factor, motif, false)) return;

        Cytoscape.getEdgeAttributes().setUserVisible(FEATURE_ID_ATTRIBUTE_NAME, false);

        view.redrawGraph(true, true);

        getView().refresh();

        activeSidePanel();
	}
}
