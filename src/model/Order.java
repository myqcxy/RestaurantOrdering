package model;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order {

	// Fields
	private String realOid;
	public String getRealOid() {
		return realOid;
	}
	public void setRealOid(String realOid) {
		this.realOid = realOid;
	}
	/**
	 * 
	 */
	private Long oid;//订单编号
	private String uid;//下单人id
	private int tid;//餐桌id
	private float price;//支付金额=总共金额totle-discount
	private Date date;//下单日期
	private int state;//订单状态  未就餐（默认）：0；
	private String stateString;
	private  static final Map<Integer,String> stateMap = new HashMap<Integer, String>(){
	{
		put(0,"未就餐");
		put(2,"已就餐");
		put(1,"已送至厨师");
		
	}};
	
	
	private String mid;//餐品信息，使用:分割
	private String midString;
	private float totle;//总共金额
	private String phone;//联系电话
	private int method;//下单方式
	private float discount;//折扣
	private int payMethod;//付款方式：网上支付（0）：默认
	private int payState;//付款状态 未付款（默认）：2 付款：1
	private String payStateString;
	private  static final Map<Integer,String> payStateMap = new HashMap<Integer, String>(){
		{
			put(2,"未付款");
			put(1,"已付款");
			
		}};
	private String note;//批注
	private int number;

	
	
	public String getMidString() {
		return midString;
	}
	public void setMidString(String midString) {
		this.midString = midString;
	}
	public static Map<Integer, String> getStatemap() {
		return stateMap;
	}
	public static Map<Integer, String> getPaystatemap() {
		return payStateMap;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		this.setStateString(stateMap.get(this.state));
	}
	public String getStateString() {
		return stateString;
	}
	public void setStateString(String stateString) {
		this.stateString = stateString;
	}
	public String getPayStateString() {
		return payStateString;
	}
	public void setPayStateString(String payStateString) {
		this.payStateString = payStateString;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public float getTotle() {
		return totle;
	}
	public void setTotle(float totle) {
		this.totle = totle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public int getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}
	public int getPayState() {
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
		this.setPayStateString(payStateMap.get(this.payState));
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	
}