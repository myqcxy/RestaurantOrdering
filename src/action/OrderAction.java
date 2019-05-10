package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.CacheDao;
import dao.MealDao;
import dao.OrderDao;
import dao.UserDao;
import model.Cache;
import model.Meal;
import model.Order;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	Order order = new Order();
	OrderDao od = new OrderDao();
	private List<Meal> list = new ArrayList<Meal>();
	private List<Order> orders;
	//获取我的订单
	public String myOrder(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        orders = od.getOrders(uid);
       
        return SUCCESS;
	}
	
	
	public String toPay(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        order.setUid(uid);
        order.setPrice(od.getOrderPrice(uid));
        order.setState(0);
        order.setMid(new CacheDao().getMid(uid));
        list = new MealDao().getAllMeals(order.getMid(),uid);
        order.setTotle(order.getPrice());
        order.setMethod(0);
        order.setPayMethod(0);
       
     
		if(od.toPay(order)) return SUCCESS;
		else return "toPayFalse";

	}
	
	
	public String placeAnOrder(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
	//	od.placeAnOrder(order,uid);
        order.setUid(uid);
        order.setPrice(od.getOrderPrice(uid));
        order.setState(0);
        order.setMid(new CacheDao().getMid(uid));
        list = new MealDao().getAllMeals(order.getMid(),uid);
        order.setTotle(order.getPrice());
        order.setMethod(0);
        order.setDiscount(0);
        order.setPayMethod(0);
        order.setPayState(0);
        order.setNote("");
        float balance=new UserDao().getBalance(uid);
        if(balance>=order.getPrice()){
        	session.put("placeAnOrderRes", "ok");
        }else session.put("placeAnOrderRes", "nook");
		 return SUCCESS;
	}
	
	
	public List<Meal> getList() {
		return list;
	}


	public List<Order> getOrders() {
		return orders;
	}


	@Override
	public Order getModel() {
		return order;
	}

}
