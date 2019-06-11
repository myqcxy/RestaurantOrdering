package action;

import java.util.ArrayList;
import java.util.HashMap;
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
import model.Comment;
import model.Meal;
import model.Order;
import model.User;
import net.sf.json.JSONObject;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	Order order = new Order();
	OrderDao od = new OrderDao();
	UserDao ud = new UserDao();
	User user;
	Comment comment;
	String result;
	int useIntegral;
	
	public int getUseIntegral() {
		return useIntegral;
	}


	public void setUseIntegral(int useIntegral) {
		this.useIntegral = useIntegral;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public String drawBack(){
		
			ActionContext actionContext = ActionContext.getContext();
	        Map session = actionContext.getSession();
	        String uid=((String)session.get("uid"));
			Order oo = od.getOrdersById(order.getOid()).get(0);
			System.out.println(oo.getPayState()+"1111111111122");
			if(ud.drawBack(uid,oo.getIntegral(),oo.getPrice(),oo.getPayState())&&od.drawBack(order.getOid())){
				
			}
		
	
		return SUCCESS;
	}

	private List<Meal> list = new ArrayList<Meal>();
	private List<Order> orders;
	//��ȡ�ҵĶ���
	public String myOrder(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        orders = od.getOrders(uid);
        return SUCCESS;
	}
	
	//����
	public String toComment(){
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("number", num);
        JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
        
        result = json.toString();//��result��ֵ�����ݸ�ҳ��
*/		od.comment(comment);
		return SUCCESS;
	}
	public String toPay(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        order.setUid(uid);
        order.setState(3);
        order.setMid(new CacheDao().getMid(uid));
        list = new MealDao().getAllMeals(order.getMid(),uid,false);
        order.setMethod(0);
        order.setPayMethod(0);
        if(useIntegral==0){
        	order.setIntegral(0);
        }else {
        	ud.delIntegral(uid,order.getIntegral());
        }
		if(od.toPay(order)) return SUCCESS;
		else return "toPayFalse";
        //System.out.println("���֣�"+order.getIntegral()+"�ܽ�"+order.getTotle()+"Ӧ����"+order.getPrice());
       // return SUCCESS;

	}
	
	
	public String placeAnOrder(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        order.setUid(uid);
        
        order.setState(3);
        order.setMid(new CacheDao().getMid(uid));
        list = new MealDao().getAllMeals(order.getMid(),uid,true);
        order.setTotle(od.getOrderPrice(uid));
        order.setMethod(0);
        order.setDiscount(od.getDiscount(order.getMid()));
        order.setPrice(order.getTotle()-order.getDiscount());
        order.setPayMethod(0);
        order.setPayState(0);
        order.setNote("");
        int integral = ud.getIntegral(uid,order.getPrice());
        order.setIntegral(integral);
        float balance=new UserDao().getBalance(uid);
        if(balance>=order.getPrice()){
        	session.put("placeAnOrderRes", "ok");
        }else session.put("placeAnOrderRes", "nook");
        user = new UserDao().getUser(uid);
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
