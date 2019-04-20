package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.AdminDao;
import model.Admin;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	AdminDao ad = new AdminDao();
	Admin admin = new Admin();
	@Override
	public String execute() throws Exception {
	//	System.out.println(admin.getAid());
		if(ad.login(admin))
		return SUCCESS;
		else return "false";
	}
	@Override
	public Admin getModel() {
		
		return admin;
	}
	
}
