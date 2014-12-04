package org.erp.util.interceptor;

import org.erp.auth.employee.entity.EmployeeModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EmployLoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		EmployeeModel sessionEmplo = (EmployeeModel) ActionContext.getContext().getSession().get("userName");
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		if((actionName+"."+methodName).equals("org.erp.auth.employee.action.EmployeeAction.login"))
		{
			return invocation.invoke();
		}
		if(sessionEmplo == null)
		{
			return "loginFail";
		}
		return invocation.invoke();
	}

}
