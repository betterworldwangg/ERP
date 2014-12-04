package org.erp.auth.employee.action;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.util.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

public class EmployeeAction extends BaseAction<EmployeeModel>
{
	public String login()
	{
		EmployeeModel empMode = employeeServ.findByNameAndPass(model.getUserName(),model.getUserPass());
		if(empMode != null)
		{
			ActionContext.getContext().getSession().put("userName", empMode);
			return "loginSucc";
		}
		this.addActionError("用户名或密码错误");
		return "loginFail";
		
	}
}
