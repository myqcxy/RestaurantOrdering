package action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.MealDao;
import dao.ShopDao;
import model.Discount;
import model.Meal;
import model.Shop;
import net.sf.json.JSONObject;

public class MealAction extends ActionSupport implements ModelDriven<Meal> {
	Meal meal = new Meal();
	MealDao md = new MealDao();
	private File mphoto;//上传的文件
	private String mphotoContenType;//上传的文件类型
	private String mphotoFileName;//上传的文件名
	private List<Meal> list;
	String searchContent;
	private Discount dist;
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	

	public Discount getDist() {
		return dist;
	}

	public void setDist(Discount dist) {
		this.dist = dist;
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
		md.addDiscount(dist);
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
		dist = md.getDiscount(m.getMid());
		if(dist!=null){
			dist.setDescribe(dist.getDescribe().trim());
			
		}
		
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
	
	public String obtainMealsByPrice(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		list=md.getLowPriceMeals(uid);
		
		return SUCCESS;
	}
	public String obtainMealsBySales(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		list=md.getHotMeals(uid);
		return SUCCESS;
	}
	
	public String delMeal(){
		if(new MealDao().delMeal(meal.getMid())) return SUCCESS;
		else return "delFalse";
	}
	public String AllMeals(){
		list= md.getHotMeals(0);
		return SUCCESS;
	}
	
	public String specialOffer(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		list=md.getSpecialOffer(uid);
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
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));//获取uid
		Map<String,String> m = new HashMap<String,String>();
		m.put("1", "主食");
		m.put("2", "晚餐");
		m.put("3", "饮料");
		m.put("4", "小吃");//传递的参数的匹配关系
		list = md.getByCategory(m.get(meal.getCategory()),uid);
		if(list!=null&&list.size()>0)//得到的餐品非空且有值
			return SUCCESS;
		else return "obtainMealsByCategoryError";
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
		if(mphoto!=null&&mphoto.length()>0) savePhoto();
		if(md.updateMeal(meal)) return SUCCESS;
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
			e.printStackTrace();
		}
		meal.setPhoto(newFileName);
	}
	
	
	
	
	public void validateAddMeal() {
		if(meal.getMname().length()<1){
			this.addFieldError("mname", "餐品名称不能为空");
		}else if(meal.getPrice()==0||!(meal.getPrice()+"").matches("^\\d+.\\d+?$")){
			this.addFieldError("price", "价格须为正小数");
			
		}
	}

	
	public String addMeal() throws Exception {
		
		if(mphoto!=null&&mphoto.length()>0) savePhoto();
		if(md.addMeal(meal))
			return SUCCESS;
		else return INPUT;
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
