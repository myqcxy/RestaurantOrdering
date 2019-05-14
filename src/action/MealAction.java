package action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.MealDao;
import model.Discount;
import model.Meal;
import net.sf.json.JSONObject;

public class MealAction extends ActionSupport implements ModelDriven<Meal> {
	Meal meal = new Meal();
	MealDao md = new MealDao();
	private File mphoto;//上传的文件
	private String mphotoContenType;//上传的文件类型
	private String mphotoFileName;//上传的文件名
	private List<Meal> list;
	String searchContent;
	private Discount discount;
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public void setList(List<Meal> list) {
		this.list = list;
	}

	public List<Meal> getList() {
		return list;
	}
	
	public String addDiscount(){
		/*System.out.println(discount.getDescribe()+discount.getDiscount()
		+discount.getMid()+discount.getStarttime()+discount.getEndtime());
		System.out.println(discount.getStarttime().toString());*/
		md.addDiscount(discount);
		 Map<String,Object> map = new HashMap<String,Object>();

     map.put("message", "优惠信息添加成功");
     JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
     
     result = json.toString();//给result赋值，传递给页面
		return SUCCESS;
	}
	
	public String setDiscount(){
		Meal m = md.getMealById(meal.getMid());
		meal.setMid(m.getMid());
		meal.setMname(m.getMname());
		meal.setPhoto(m.getPhoto());
		meal.setPrice(m.getPrice());
		meal.setSales(m.getSales());
		meal.setCategory(m.getCategory());
		discount = md.getDiscount(m.getMid());
		if(discount!=null)
		discount.setDescribe(discount.getDescribe().trim());
		return SUCCESS;
	}
	public String userSearch(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        if(uid==null) uid="";
		list= md.userSearch(searchContent,uid);
		
		return SUCCESS;
	}
	public String delMeal(){
		if(new MealDao().delMeal(meal.getMid())) return SUCCESS;
		else return "delFalse";
	}
	public String AllMeals(){
		list= new MealDao().getHotMeals();
		
		return SUCCESS;
	}
	
	public String initRecommend(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		list = md.initRecommend(uid);
		return SUCCESS;
	}
	
	
	public String obtainMealsByCategory(){
		//meal.setCategory("主食");
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		Map<String,String> m = new HashMap<String,String>();
		m.put("1", "主食");
		m.put("2", "晚餐");
		m.put("3", "饮料");
		m.put("4", "小吃");
		list = md.getByCategory(m.get(meal.getCategory()),uid);
		return SUCCESS;
	}
	
	
	public String toUpdateMeal(){
		
		Meal m = md.getMealById(meal.getMid());
	
		meal.setMid(m.getMid());
		meal.setMname(m.getMname());
		meal.setPhoto(m.getPhoto());
		meal.setPrice(m.getPrice());
		return SUCCESS;
	}
	
	public String updateMeal(){
		savePhoto();
		if(new MealDao().updateMeal(meal)) return SUCCESS;
		else return "updateFalse";
	
	}
	
	
	private void savePhoto(){
		String realPath = ServletActionContext.getServletContext().getRealPath("/images");
		File f = new File(realPath);
		if(!f.exists()) f.mkdirs();
		int start = mphotoFileName.lastIndexOf(".");
		String fileType = mphotoFileName.substring(start);
		String newFileName;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		newFileName=sdf.format(date)+fileType;
		newFileName=meal.getMid()+newFileName;
		try {
			FileUtils.copyFile(mphoto, new File(f,newFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meal.setPhoto(newFileName);
	}
	@Override
	public String execute() throws Exception {

		savePhoto();
		if(md.addMeal(meal))
			return SUCCESS;
		else return "false";
	}

	@Override
	public Meal getModel() {
	
		return meal;
	}

	public File getMphoto() {
		return mphoto;
	}

	public void setMphoto(File mphoto) {
		this.mphoto = mphoto;
	}

	public String getMphotoContenType() {
		return mphotoContenType;
	}

	public void setMphotoContenType(String mphotoContenType) {
		this.mphotoContenType = mphotoContenType;
	}

	public String getMphotoFileName() {
		return mphotoFileName;
	}

	public void setMphotoFileName(String mphotoFileName) {
		this.mphotoFileName = mphotoFileName;
	}

	
}
