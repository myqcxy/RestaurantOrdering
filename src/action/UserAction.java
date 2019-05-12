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
			this.addFieldError("upass", "密码长度为4-10");
		}
		if(!user.getUpass().equals(upass1)){
			this.addFieldError("upass", "两次密码不一致");
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
	//注销
	public String logout(){
		Map session=ActionContext.getContext().getSession();
		
		   session.remove("uid");
		return SUCCESS;
	}
	
	//注册
	public String checkusername(){
		Map<String,Object> map = new HashMap<String,Object>();
        if(ud.uidIsExist(user.getUid())){
        	
            map.put("idtext", "用户名已经存在");
            
        	
        }
		else {
			map.put("idtext", "用户名可以使用");
		}
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        
        result = json.toString();//给result赋值，传递给页面
        return SUCCESS;
	}
	//注册
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
