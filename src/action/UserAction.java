package action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.MealDao;
import dao.UserDao;
import model.User;
import net.sf.json.JSONObject;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	User user = new User();
	UserDao ud = new UserDao();
	String result;
	String upass1;
	
	public String getUpass1() {
		return upass1;
	}
	public void setUpass1(String upass1) {
		this.upass1 = upass1;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public void validateUserUpdate() {
		if(user.getUpass().length()<4||user.getUpass().length()>10){
			this.addFieldError("upass", "���볤��Ϊ4-10");
		}
		if(!user.getUpass().equals(upass1)){
			this.addFieldError("upass", "�������벻һ��");
		}
	}
	public String userUpdate(){
		ud.updateUser(user);
		return SUCCESS;
	}
	
	public String userEdit(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		User u = ud.getUser(uid);
		user.setUid(u.getUid());
		user.setBalance(u.getBalance());
		user.setPhone(u.getPhone());
		user.setUpass(u.getUpass());
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
	//	System.out.println(admin.getAid());
		if(ud.login(user)){
			Map session=ActionContext.getContext().getSession();
		
			 session.put("uid", user.getUid());
			return SUCCESS;
		}
		
		else return "false";
	}
	//ע��
	public String logout(){
		Map session=ActionContext.getContext().getSession();
		
		   session.remove("uid");
		return SUCCESS;
	}
	
	//ע��
	public String checkusername(){
		Map<String,Object> map = new HashMap<String,Object>();
        if(ud.uidIsExist(user.getUid())){
        	
            map.put("idtext", "�û����Ѿ�����");
            
        	
        }
		else {
			map.put("idtext", "�û�������ʹ��");
		}
        JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
        
        result = json.toString();//��result��ֵ�����ݸ�ҳ��
        return SUCCESS;
	}
	//ע��
	public String regist(){
		System.out.println(user.getUid()+user.getUpass()+user.getPhone()+"111111");
		if(ud.regist(user)){
			Map session=ActionContext.getContext().getSession();
			
			   session.put("uid", user.getUid());
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	@Override
	public User getModel() {
		
		return user;
	}
	
}
