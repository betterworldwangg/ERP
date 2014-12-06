package org.erp.auth.employee.action;

import java.util.List;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.entity.EmployeeQueryModel;
import org.erp.util.base.BaseAction;

public class EmployeeAction extends BaseAction<EmployeeModel> {
	public EmployeeQueryModel ehq = new EmployeeQueryModel();
	public String login()
	{
		EmployeeModel empMode = employeeServ.findByNameAndPass(model.getUserName(),model.getUserPass());
		if(empMode != null)
		{
			putSession("userName", empMode);
			return "loginSucc";
		}
		this.addActionError("用户名或密码错误");
		return "loginFail";
		
	}
	
	public String list()
	{
		list = employeeServ.findAll(ehq, currPage, pageSize);
		rows = employeeServ.rowCount(ehq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		
		List<DepartmentModel> depts = departmentServ.findAll();
		put("depts", depts);
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			employeeServ.save(model);
		}
		else
		{
			employeeServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = employeeServ.findById(model.getUuid());
		}
		List<DepartmentModel> depts = departmentServ.findAll();
		put("depts", depts);
		return INPUTUI;
	}
	public String delete()
	{
		employeeServ.delete(model);
		return TO_LIST;
	}
}
