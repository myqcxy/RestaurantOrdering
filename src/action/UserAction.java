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
