package action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.AdminDao;
import dao.MealDao;
import dao.OrderDao;
import dao.UserDao;
import model.Admin;
import model.Meal;
import model.Order;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	AdminDao ad = new AdminDao();
	Admin admin = new Admin();
	int statisticMethod;
	private List<Meal> list;
	private List<Order> orders;
	String searchText;
	String searchCondition;
	private MealDao md= new MealDao();
	public String getSearchText() {
		return searchText;
	}


	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}


	public String getSearchCondition() {
		return searchCondition;
	}


	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}


	public int getStatisticMethod() {
		return statisticMethod;
	}


	public void setStatisticMethod(int statisticMethod) {
		this.statisticMethod = statisticMethod;
	}
	//管理员搜索框
	public String adminSearch(){
		if(searchCondition==null) return SUCCESS;
		if(searchCondition.equals("mid")){
			
			
			Meal m = md.getMealById(Integer.parseInt(searchText) );
			if(m!=null&&m.getMname()!=null&&m.getMname().length()>0){
				list=new ArrayList<Meal>();
				list.add(m);
			}
			
		}
	
		if(searchCondition.equals("mname")){
			list=md.userSearch(searchText, null);
		}
		return SUCCESS;
		
	}

	@Override
	public String execute() throws Exception {
		if(ad.login(admin))
		return SUCCESS;
		this.addFieldError("aid", "用户名和密码不匹配");
		return "input";
	}
	
	@Override
	public Admin getModel() {
		
		return admin;
	}
	public String seachDrawback(){
		orders=md.getDrawbackOrders();
		return SUCCESS;
	}
	public String salesStatistics(){
		if(statisticMethod==2){
			orders = md.getMealsByPraise(statisticMethod);
		}else if(statisticMethod==3){
			orders=md.getMealsByPraise(statisticMethod);
		}
		else list = md.getHotMeals(statisticMethod);
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
