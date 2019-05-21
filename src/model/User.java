package model;

import java.util.HashMap;
import java.util.Map;

public class User {
	String uid;
	String upass;
	float balance;
	String phone;
	int integral;
	int vip;
	String vipString;
	String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private  static final Map<Integer,String> vipMap = new HashMap<Integer, String>(){
		{
			put(0,"��ͨ��Ա");
			put(1,"�ƽ��Ա");
			put(2,"�׽��Ա");
			put(3,"��ʯ��Ա");
		}};
	
	public String getVipString() {
		return vipString;
	}
	public void setVipString(String vipString) {
		this.vipString = vipString;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
		this.setVipString(vipMap.get(vip));
	
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
