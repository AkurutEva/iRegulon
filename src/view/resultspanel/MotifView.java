package view.resultspanel;


import domainmodel.AbstractMotif;
import domainmodel.TranscriptionFactor;
import javax.swing.*;


public interface MotifView extends Refreshable {
    public JTable getMasterTable();

    public AbstractFilterMotifTableModel getModel();

    public DetailPanelIF getDetailPanel();

    public FilterAttributeActionListener getFilterAttributeListener();

    public void setFilterAttributeListener(FilterAttributeActionListener listener);

    public FilterPatternDocumentListener getFilterPatternListener();

    public void setFilterPatternListener(FilterPatternDocumentListener listener);

    public AbstractMotif getSelectedMotif();

    public void setSelectedMotif(AbstractMotif motif);

    public TranscriptionFactor getSelectedTranscriptionFactor();

    public void registerSelectionComponents(final SelectedMotif selectedMotif, final TranscriptionFactorComboBox transcriptionFactorCB);

    public void unregisterSelectionComponents(final SelectedMotif selectedMotif, final TranscriptionFactorComboBox transcriptionFactorCB);

    public void registerFilterComponents(final JComboBox filterAttributeTF, final JTextField filterValueTF);

    public void unregisterFilterComponents(JComboBox filterAttributeCB, JTextField filterValueTF);
}
