package org.erp.util.interceptor;

import org.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			
			ActionSupport actionSupp = (ActionSupport) invocation.getAction();
			actionSupp.addActionError(actionSupp.getText(e.getMessage()));
			return "error";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return invocation.invoke();
		}
	
	}

}
