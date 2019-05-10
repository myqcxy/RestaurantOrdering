package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.CacheDao;
import dao.MealDao;
import model.Cache;
import model.Meal;
import net.sf.json.JSONObject;

public class CacheAction extends ActionSupport implements ModelDriven<Cache> {
	Cache cache = new Cache();
	CacheDao cd = new CacheDao();
	private String result;
	private List<Meal> list = new ArrayList<Meal>();
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String settlement(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
		Cache cache = new CacheDao().getCache(uid);
		list = new MealDao().getAllMeals(cache.getMid(),uid);
		 return SUCCESS;
		
	}
	
	public List<Meal> getList() {
		return list;
	}

	public String delFromCache(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        cache.setUid((String)session.get("uid"));
        Map<String,Object> map = new HashMap<String,Object>();
        if(cd.delFromCache(cache)){
        	list=new MealDao().getAllMeals(cache.getMid(),cache.getUid());
       	 int num=new MealDao().getAddToCacheNumber(Integer.parseInt(cache.getMid()), cache.getUid());
            map.put("number", num);
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            
            result = json.toString();//给result赋值，传递给页面
        	return SUCCESS;
        }
		else return "delFromCacheFalse";
	}
	
	
	public String addToCache(){
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        cache.setUid((String)session.get("uid"));
		
		 
        Map<String,Object> map = new HashMap<String,Object>();

         if(cd.addToCache(cache)){
        	 list=new MealDao().getAllMeals(cache.getMid(),cache.getUid());
        	 int num=new MealDao().getAddToCacheNumber(Integer.parseInt(cache.getMid()), cache.getUid());
             map.put("number", num);
             JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
             
             result = json.toString();//给result赋值，传递给页面
        	 return SUCCESS;
         }
 		else return SUCCESS;
        
	}
	

	

	public Cache getModel() {
		return cache;
	}
}
