package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginValidateInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        String uid=((String)session.get("uid"));
        if(uid!=null) return  invocation.invoke();
        else return Action.LOGIN;
	}

}
