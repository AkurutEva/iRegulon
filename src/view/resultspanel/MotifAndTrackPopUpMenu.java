package view.resultspanel;


import view.Refreshable;
import view.actions.AddRegulatoryInteractionsAction;
import view.actions.AddRegulatoryNetworkAction;
import view.actions.BedExportAction;
import view.actions.OpenLinkToGenomeBrowserAction;
import view.resultspanel.guiwidgets.TranscriptionFactorComboBox;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MotifAndTrackPopUpMenu extends MouseAdapter implements Refreshable {
    private PopupMenu menu;

    private final AddRegulatoryInteractionsAction drawEdgesAction;

    public MotifAndTrackPopUpMenu(final SelectedMotifOrTrack selectedMotifOrTrack,
                                  final TranscriptionFactorComboBox transcriptionFactorComboBox,
                                  final boolean isRegionBased,
                                  final Refreshable view,
                                  final String attributeName) {
        if (selectedMotifOrTrack == null || transcriptionFactorComboBox == null || view == null) {
            throw new IllegalArgumentException();
        }

        menu = new PopupMenu();

        final AddRegulatoryNetworkAction networkAction = AddRegulatoryNetworkAction.createCreateNewRegulatoryNetworkAction(
                attributeName, selectedMotifOrTrack, transcriptionFactorComboBox, view);
        menu.addAction(networkAction);

        final AddRegulatoryNetworkAction drawRegulonsAndEdgesAction = AddRegulatoryNetworkAction.createAddRegulatoryNetworkAction(
                attributeName, selectedMotifOrTrack, transcriptionFactorComboBox, view);
        menu.addAction(drawRegulonsAndEdgesAction);

        drawEdgesAction = new AddRegulatoryInteractionsAction(selectedMotifOrTrack, transcriptionFactorComboBox, view, attributeName);
        menu.addAction(drawEdgesAction);

        if (isRegionBased) {
            final BedExportAction bedExportAction = new BedExportAction(selectedMotifOrTrack);
            menu.addAction(bedExportAction);

            final OpenLinkToGenomeBrowserAction bedLinkToBrowserAction = new OpenLinkToGenomeBrowserAction(selectedMotifOrTrack);
            menu.addAction(bedLinkToBrowserAction);
        }
    }

    public void refresh() {
        drawEdgesAction.refresh();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private static class PopupMenu extends JPopupMenu {

        public PopupMenu() {
            super();
        }

        public void addAction(Action action) {
            add(new JMenuItem(action));
        }
    }
}
