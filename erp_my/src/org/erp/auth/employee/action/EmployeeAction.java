package org.erp.auth.employee.action;

import java.util.List;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.entity.EmployeeQueryModel;
import org.erp.auth.role.entity.RoleModel;
import org.erp.util.base.BaseAction;

public class EmployeeAction extends BaseAction<EmployeeModel> {
	public EmployeeQueryModel ehq = new EmployeeQueryModel();
	public String newPass;
	public Long[] roleUuids;
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
			employeeServ.save(model,roleUuids);
		}
		else
		{
			employeeServ.update(model,roleUuids);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = employeeServ.findById(model.getUuid());
			roleUuids = new Long[model.getRoles().size()];
			int i = 0;
			for(RoleModel rm : model.getRoles())
			{
				roleUuids[i++] = rm.getUuid();
			}
		}
		List<DepartmentModel> depts = departmentServ.findAll();
		List<RoleModel> roles = roleServ.findAll();
		put("roles",roles);
		put("depts", depts);
		return INPUTUI;
	}
	public String delete()
	{
		employeeServ.delete(model);
		return TO_LIST;
	}
	public String logout()
	{
		putSession("userName", null);
		return "loginFail";
	}
	public String changePwd()
	{
		return "toChangePwd";
	}
	public String changePass()
	{
		EmployeeModel em = (EmployeeModel) getSession("userName");
		Boolean flag = employeeServ.changePass(em.getUserName(),model.getUserPass(),newPass);
		if(flag)
		{
			return "loginFail";
		}
		else
		{
			addActionError("用户名或密码错误");
			return "toChangePwd";
		}
	}
}
