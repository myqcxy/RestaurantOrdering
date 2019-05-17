package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.OrderDao;
import dao.WaiterDao;
import model.Order;
import model.Waiter;
import net.sf.json.JSONObject;

public class WaiterAction extends ActionSupport implements ModelDriven<Waiter> {

	Waiter waiter = new Waiter();
	WaiterDao wd = new WaiterDao();
	OrderDao od = new OrderDao();
	private List<Order> orders;
	long oid;
	int state;
	String result;
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String checkLogin(){
		if(wd.checkLogin(waiter))
		return SUCCESS;
		this.addFieldError("wid", "�û��������벻ƥ��");
		return "input";
	}
	//��ȡû�н��ܵĶ���
	public String obtainNewOrders(){
		orders = od.getNewOrders();
		return SUCCESS;
	}
	
	public String newOrder(){
		if(od.hasNewOrder()){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("hadpay", 1001);
	        JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
	        result = json.toString();//��result��ֵ�����ݸ�ҳ��
			return SUCCESS;
		}else return SUCCESS;
	}
	public String changeOrderState(){
		od.changeOrderState(oid,state);
		Map<String,Object> map = new HashMap<String,Object>();
		String stateString="";
		if(state==1){
			stateString="�Ѿ�������ʦ";
		}if(state==2){
			stateString="�Ѿ��Ͳ�";
		}if(state==0){
			stateString="�Ѿ��ӵ�";
		}
		map.put("stateString", stateString);
        JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
        
        result = json.toString();//��result��ֵ�����ݸ�ҳ��
		return SUCCESS;
	}
	
	public String obtainUnservedOrders(){
		orders = new OrderDao().getUnservedOrders();
		return SUCCESS;
	}
	
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public Waiter getModel() {
		// TODO Auto-generated method stub
		return waiter;
	}
}
