package view.resultspanel.motifview.detailpanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class TFMouseListener extends MouseAdapter {
	private PopupMenu menu;
	
	public TFMouseListener(JTable table, TFandMotifSelected tfMotif){
		if (table == null) {
			throw new IllegalArgumentException();
		}
		menu = new PopupMenu();
		final TFDetailFrameAction tfDetailFrameAction = new TFDetailFrameAction(tfMotif);
		table.getSelectionModel().addListSelectionListener(tfDetailFrameAction);
		menu.addAction(tfDetailFrameAction);
	}

	public void mouseClicked(MouseEvent e){
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
