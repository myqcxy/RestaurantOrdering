package action;

import com.opensymphony.xwork2.ActionSupport;

public class Hello extends ActionSupport{
	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}
}
