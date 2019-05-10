package action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.TableDao;
import model.Table;

public class TableAction extends ActionSupport implements ModelDriven<Table> {
	Table table = new Table();
	List<Table> tables = new ArrayList<Table>();
	
	public List<Table> getTables() {
		return tables;
	}


	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	public String toUpdateTable(){
		Table t = new TableDao().getTableById(table.getTid());
		table.setDescribe(t.getDescribe());
		table.setNumber(t.getNumber());
		table.setTid(t.getTid());
		return SUCCESS;
	}
	public String updateTable(){
		
		if(new TableDao().updateTable(table))
		
		return SUCCESS;	
		else return "updateTableFalse";
	}
	public String showAllTables(){
		tables = new TableDao().getAllTables();
		return SUCCESS;
	}
	
	
	@Override
	public String execute() throws Exception {
		
		if(new TableDao().addTable(table))
		return SUCCESS;
		else
		return "addTableFalse";
	}




	@Override
	public Table getModel() {
		return table;
	}

}
