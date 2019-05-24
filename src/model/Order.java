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
	private Long oid;//�������
	private String uid;//�µ���id
	private int tid;//����id
	private float price;//֧�����=�ܹ����totle-discount
	private Date date;//�µ�����
	private int state;//����״̬  δ�Ͳͣ�Ĭ�ϣ���0��
	private String stateString;
	private int integral;
	private int grade;
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	private  static final Map<Integer,String> stateMap = new HashMap<Integer, String>(){
	{
		put(0,"�̼��Ѿ��ӵ�,δ�Ͳ�");
		put(2,"�ѾͲ�");
		put(1,"��������ʦ");
		put(3,"�ȴ��̼ҽӵ�");
		put(4,"�̼��Ѿ��ӵ�");
	}};
	
	
	private String mid;//��Ʒ��Ϣ��ʹ��:�ָ�
	private String midString;
	private float totle;//�ܹ����
	private String phone;//��ϵ�绰
	private int method;//�µ���ʽ
	private float discount;//�ۿ�
	private int payMethod;//���ʽ������֧����0����Ĭ��
	private int payState;//����״̬ δ���Ĭ�ϣ���2  ���1
	private String payStateString;
	private  static final Map<Integer,String> payStateMap = new HashMap<Integer, String>(){
		{
			put(2,"δ����");
			put(1,"�Ѹ���");
			
		}};
	private String note;//��ע
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