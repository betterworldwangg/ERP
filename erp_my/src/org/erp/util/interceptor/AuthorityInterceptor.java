package org.erp.util.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.resource.service.service.ResourceService;
import org.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	private ResourceService rs;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName+"."+methodName;
		
		String allResource = ServletActionContext.getServletContext().getAttribute("allResource").toString();
		
		if(!allResource.contains(allName))
		{
			return invocation.invoke();
		}
		EmployeeModel loginEm = (EmployeeModel) ActionContext.getContext().getSession().get("userName");
		if(loginEm.getResourceView().contains(allName))
		{
			return invocation.invoke();
		}
		
		throw new AppException("无权操作，请勿非法访问");
	}

}
