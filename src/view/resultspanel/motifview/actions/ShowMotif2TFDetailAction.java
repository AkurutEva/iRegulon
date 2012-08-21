package view.resultspanel.motifview.actions;

import view.ResourceAction;
import view.resultspanel.motifview.Motif2TFDetailFrame;
import view.resultspanel.motifview.detailpanel.TFandMotifSelected;

import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ShowMotif2TFDetailAction extends ResourceAction implements ListSelectionListener {
    private static final String NAME = "action_detail_frame";

	private TFandMotifSelected tfMotif;
	
	public ShowMotif2TFDetailAction(final TFandMotifSelected tfMotif) {
        super(NAME);
		setEnabled(false);
		this.tfMotif = tfMotif;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new Motif2TFDetailFrame(tfMotif);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		final ListSelectionModel model = (ListSelectionModel) e.getSource();
		setEnabled(!model.isSelectionEmpty());
	}
}
