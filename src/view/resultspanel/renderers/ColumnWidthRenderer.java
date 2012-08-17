package view.resultspanel.renderers;

import view.resultspanel.motifview.tablemodels.MotifTableModel;

import javax.swing.JTable;




public class ColumnWidthRenderer{

	private JTable table;
	
	public ColumnWidthRenderer(JTable table){
		this.table = table;
	}
	
	public void widthSetter(){
		MotifTableModel model = (MotifTableModel) this.table.getModel();
		int[] imp = model.getColumnImportance();
		int totImp = 0;
		int maxImp = 0;
		for (int index = 0; index < imp.length; index++){
			totImp += imp[index];
			if (imp[index] > maxImp){
				maxImp = imp[index];
			}
		}
		for (int index = 0; index < imp.length; index++){
			int width = 20;
			switch(imp[index]){
			case 1 : width = 250;
			break;
			case 2 : width = 60;
			break;
			case 3 : width = 25;
			break;
			}
			this.table.getColumnModel().getColumn(index).setPreferredWidth(width);
		}
		
	}

}
