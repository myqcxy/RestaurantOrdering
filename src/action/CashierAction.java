package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.CashierDao;
import dao.OrderDao;
import dao.UserDao;
import model.Cashier;
import model.Order;
import model.User;
import net.sf.json.JSONObject;

public class CashierAction extends ActionSupport implements ModelDriven<Cashier> {
	Cashier cashier = new Cashier();
	CashierDao cd = new CashierDao();
	long oid;
	int number;
	private User user;
	String searchText;
	String searchCondition;
	float rechargeAmount;
	private List<Order> orders;
	String result;
	

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public float getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(float rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	//����
	public String cashRegister(){
		new OrderDao().cashRegister(oid);
		user = new UserDao().getUser(user.getUid());
		  orders = new OrderDao().getOrders(user.getUid());
		return SUCCESS;
	}
	
	
	public String checkLogin() {
		if (cd.checkLogin(cashier))
			return SUCCESS;
		return SUCCESS;
	}
	
	public String  choiceTable(){
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(oid+" "+number+"22222222");
		int ans = new OrderDao().choiceTable(oid,number);
		map.put("tid", ans);
        JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
        
        result = json.toString();//��result��ֵ�����ݸ�ҳ��
		return SUCCESS;
	}

	public String search() {
		if(searchCondition==null) return SUCCESS;
		if(searchCondition.equals("uid")){
			user = new UserDao().getUser(searchText);
			orders = new OrderDao().getOrders(searchText);
		}
		
		if(searchCondition.equals("oid")){
			orders = new OrderDao().getOrdersById(Long.parseLong(searchText));
		}
		if(searchCondition.equals("phone")){
			orders = new OrderDao().getOrdersByPhone(searchText);
		}
		return SUCCESS;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public Cashier getModel() {
		return cashier;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	//��ֵ
	public String recharge() {
		
		  float balance = cd.recharge(user.getUid(),rechargeAmount);
		  user = new UserDao().getUser(user.getUid());
		  orders = new OrderDao().getOrders(user.getUid());
		 
		return SUCCESS;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
