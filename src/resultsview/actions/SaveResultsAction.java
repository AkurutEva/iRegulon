package resultsview.actions;


import resultsview.ResourceAction;
import resultsview.ResultsView;
import saveActions.SaveLoadDialogs;
import saveActions.SaveResults;

import java.awt.event.ActionEvent;

public class SaveResultsAction extends ResourceAction {
    private static final String NAME = "action_save_results_view";
    private ResultsView view;

    public SaveResultsAction(final ResultsView view) {
        super(NAME);
        this.view = view;
    }

    public ResultsView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final SaveResults results = new SaveResults();
        boolean saved = SaveLoadDialogs.showDialog(results.convertResultsToXML(getView().getResults()),
                getView().getRunName(),
                SaveResults.NATIVE_FILE_EXTENSION);
        if (saved && !view.isSaved()) {
            getView().setSaved();
        }
    }
}