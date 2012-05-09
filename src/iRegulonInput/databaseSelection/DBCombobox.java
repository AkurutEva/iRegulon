package iRegulonInput.databaseSelection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;

import domainModel.Database;

public class DBCombobox extends JComboBox{

	private List<Database> database;
	
	public DBCombobox(){
		super();
		this.database = new ArrayList<Database>();
	}
	
	public void updateDatabases(List<Database> newDatabase){
		this.database = newDatabase;
		this.removeAllItems();
		for (Database db : newDatabase){
			this.addItem(db);
		}
	}
	
	public boolean canBeSelected(Database aDatabase){
		return this.database.contains(aDatabase);
	}
	
	
}
