package interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginValidateInterceptor extends AbstractInterceptor {
	private boolean isAjaxRequest(HttpServletRequest request) { 
        String header = request.getHeader("X-Requested-With"); 
        if (header != null && "XMLHttpRequest".equals(header)) 
            return true; 
        else 
            return false; 
    } 
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        if(uid!=null)
        	return  invocation.invoke();
        else {
        	/*HttpServletRequest req = ServletActionContext.getRequest();
        	HttpServletResponse rep= ServletActionContext.getResponse();
        	if(isAjaxRequest(req)){
                try{
                   write("-2", rep);
                   return null;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }*/
        	return Action.LOGIN;
        }
        	
	}

}
