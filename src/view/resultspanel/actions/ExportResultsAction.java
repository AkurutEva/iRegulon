package view.resultspanel.actions;


import view.ResourceAction;
import view.resultspanel.ResultsView;
import view.actions.SaveLoadDialogs;
import view.actions.SaveResults;

import java.awt.event.ActionEvent;

public class ExportResultsAction extends ResourceAction {
    private static final String NAME = "action_export_results_view";
    private ResultsView view;

    public ExportResultsAction(final ResultsView view) {
        super(NAME);
        this.view = view;
    }

    public ResultsView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final SaveResults results = new SaveResults();
        String tabfile = results.convertResultsToTSV(getView().getResults());
        SaveLoadDialogs.showDialog(tabfile, getView().getRunName(), SaveResults.TSV_FILE_EXTENSION);
    }
}