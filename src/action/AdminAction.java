package action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.AdminDao;
import dao.MealDao;
import model.Admin;
import model.Meal;
import model.Order;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	AdminDao ad = new AdminDao();
	Admin admin = new Admin();
	int statisticMethod;
	private List<Meal> list;
	private List<Order> orders;
	
	public int getStatisticMethod() {
		return statisticMethod;
	}


	public void setStatisticMethod(int statisticMethod) {
		this.statisticMethod = statisticMethod;
	}


	@Override
	public String execute() throws Exception {
	//	System.out.println(admin.getAid());
		if(ad.login(admin))
		return SUCCESS;
		this.addFieldError("aid", "用户名和密码不匹配");
		return "input";
	}
	
	
	@Override
	public Admin getModel() {
		
		return admin;
	}
	public String salesStatistics(){
		list = new MealDao().getHotMeals(statisticMethod);
		return SUCCESS;
	}
	public List<Meal> getList() {
		return list;
	}
	public void setList(List<Meal> list) {
		this.list = list;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
