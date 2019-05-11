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
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
