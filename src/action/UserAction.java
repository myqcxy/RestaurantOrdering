package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.MealDao;
import dao.UserDao;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import utils.MailUtils;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	User user = new User();
	UserDao ud = new UserDao();
	String result;
	String upass1;
	String sid;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
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
	public String retrievePassword(){
		if(!user.getUpass().equals(upass1)||user.getUpass().length()<4){
			this.addFieldError("error", "密码过短或两次密码不一致");
			return INPUT;
		}
		ud.modifyPassWord(user);
		return SUCCESS;
	}
	public String toFindPassword(){
		User u=ud.hasSid(sid);
		if(u!=null){
			user.setUid(u.getUid());
			user.setBalance(u.getBalance());
			user.setPhone(u.getPhone());
			user.setUpass(u.getUpass());
			user.setIntegral(u.getIntegral());
			user.setVip(u.getVip());
			user.setEmail(u.getEmail());
			return SUCCESS;
		}
		else return ERROR;
	}
	public String showme(){
		User u = ud.getUser(user.getUid());
		Gson gson = new Gson();
		result = gson.toJson(u);
		
		
		return SUCCESS;
		
	}
	public String findPassword(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(user==null) return SUCCESS;
		User u=ud.getUser(user.getUid());
		if(u==null||u.getUid()==null|| u.getUid().length()<1){
			map.put("tfpMessage", "用户名不存在");
			
		}else if(u.getEmail().length()<1){
			map.put("tfpMessage", "该用户无法使用邮箱找回密码");
		}else{
			MailUtils mu = new MailUtils();
			System.out.println(u.getEmail()+"111111111111");
			if(mu.sendMail(u.getEmail().trim())&&ud.setSid( mu.getDigitalSignature(),u.getUid()))
			map.put("tfpMessage", "重置密码的邮件已经发送至"+u.getEmail());
			else map.put("tfpMessage", "邮箱发送失败，请重试");
		}
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		result = json.toString();//给result赋值，传递给页面
		return SUCCESS;
	}
	
	
	
	public void validateUserUpdate() {
		if(user.getUpass().length()<4||user.getUpass().length()>10){
			this.addFieldError("upass", "密码长度为4-10");
		}
		if(!user.getUpass().equals(upass1)){
			this.addFieldError("upass", "两次密码不一致");
		}
	}
	public String modifyEmail(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(user.getEmail()==null||user.getEmail().length()<1||!user.getEmail().contains("@")){
			map.put("message", "邮箱格式不正确");
		}
		else if(ud.emailIsRegisted(user.getEmail())){
			map.put("message", "邮箱已经绑定其他账户，可以找回密码");
		}
		else if(ud.modifyEmail(user)){
			map.put("message", "邮箱修改成功");
		}else{
			map.put("message", "修改失败，请稍后重试");
		}
		
			
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		result = json.toString();//给result赋值，传递给页面
		
		
		return SUCCESS;
	}
	public String modifyPhone() throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		if(user.getPhone()!=null&&user.getPhone().trim().length()==11){
			if(ud.modifyPhone(user)){
				map.put("message", "手机号修改成功");
			}else{
				map.put("message", "手机号修改失败，请稍后重试");
			}
		}else{
			map.put("message", "手机号格式不对");
		}
			
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		result = json.toString();//给result赋值，传递给页面
		return SUCCESS;
	}
	
	
	public String modifyPassWord() throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		if(upass1!=null&&upass1.trim().length()>3&&user.getUpass().equals(upass1)){
			if(ud.modifyPassWord(user)){
				map.put("message", "密码修改成功");
			}else{
				map.put("message", "密码修改失败，请稍后重试");
			}
		}else{
			map.put("message", "密码不能为空或两次密码不同");
		}
			
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
		result = json.toString();//给result赋值，传递给页面
		return SUCCESS;
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
		user.setIntegral(u.getIntegral());
		user.setVip(u.getVip());
		user.setEmail(u.getEmail());
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
		this.addFieldError("upass", "用户名和密码不匹配");
		return "input";
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
	
	public void validateRegist() {
		String telRegex = "[1][3578]\\d{9}";
		if(ud.uidIsExist(user.getUid()))
			this.addFieldError("error", "用户名已经存在");
		else if(user.getUpass().length()<4||user.getUpass().length()>10){
			this.addFieldError("error", "密码长度为4-10");
		}
		else if(!user.getUpass().equals(upass1)){
			this.addFieldError("error", "两次密码不一致");
		}
		
		else if(user.getPhone()==null || !user.getPhone().matches(telRegex)){
			this.addFieldError("error", "手机号不正确");
		}
		
	}
	
	//注册
	public String regist(){
		if(ud.regist(user)){
			Map session=ActionContext.getContext().getSession();
			
			   session.put("uid", user.getUid());
			return SUCCESS;
		}
		return INPUT;
	}
	
	@Override
	public User getModel() {
		
		return user;
	}
	
}
