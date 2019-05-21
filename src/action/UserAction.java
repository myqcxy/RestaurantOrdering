package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

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
			this.addFieldError("error", "������̻��������벻һ��");
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
	
	public String findPassword(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(user==null) return SUCCESS;
		User u=ud.getUser(user.getUid());
		if(u==null||u.getUid()==null|| u.getUid().length()<1){
			map.put("tfpMessage", "�û���������");
			
		}else if(u.getEmail().length()<1){
			map.put("tfpMessage", "���û��޷�ʹ�������һ�����");
		}else{
			MailUtils mu = new MailUtils();
			System.out.println(u.getEmail()+"111111111111");
			if(mu.sendMail(u.getEmail().trim())&&ud.setSid( mu.getDigitalSignature(),u.getUid()))
			map.put("tfpMessage", "����������ʼ��Ѿ�������"+u.getEmail());
			else map.put("tfpMessage", "���䷢��ʧ�ܣ�������");
		}
		JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
		result = json.toString();//��result��ֵ�����ݸ�ҳ��
		return SUCCESS;
	}
	
	
	
	public void validateUserUpdate() {
		if(user.getUpass().length()<4||user.getUpass().length()>10){
			this.addFieldError("upass", "���볤��Ϊ4-10");
		}
		if(!user.getUpass().equals(upass1)){
			this.addFieldError("upass", "�������벻һ��");
		}
	}
	
	public String modifyPhone() throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		if(user.getPhone()!=null&&user.getPhone().trim().length()==11){
			if(ud.modifyPhone(user)){
				map.put("message", "�ֻ����޸ĳɹ�");
			}else{
				map.put("message", "�ֻ����޸�ʧ�ܣ����Ժ�����");
			}
		}else{
			map.put("message", "�ֻ��Ÿ�ʽ����");
		}
			
		JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
		result = json.toString();//��result��ֵ�����ݸ�ҳ��
		return SUCCESS;
	}
	
	
	public String modifyPassWord() throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		if(upass1!=null&&upass1.trim().length()>3&&user.getUpass().equals(upass1)){
			if(ud.modifyPassWord(user)){
				map.put("message", "�����޸ĳɹ�");
			}else{
				map.put("message", "�����޸�ʧ�ܣ����Ժ�����");
			}
		}else{
			System.out.println(user.getUpass()+upass1+"1111111");
			map.put("message", "���벻��Ϊ�ջ��������벻ͬ");
		}
			
		JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
		result = json.toString();//��result��ֵ�����ݸ�ҳ��
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
		this.addFieldError("upass", "�û��������벻ƥ��");
		return "input";
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
	
	public void validateRegist() {
		if(ud.uidIsExist(user.getUid()))
			this.addFieldError("error", "�û����Ѿ�����");
		if(user.getUpass().length()<4||user.getUpass().length()>10){
			this.addFieldError("error", "���볤��Ϊ4-10");
		}
		if(!user.getUpass().equals(upass1)){
			this.addFieldError("error", "�������벻һ��");
		}
		String telRegex = "[1][3578]\\d{9}";
		if(user.getPhone()==null || !user.getPhone().matches(telRegex)){
			this.addFieldError("error", "�ֻ��Ų���ȷ");
		}
	}
	
	//ע��
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
