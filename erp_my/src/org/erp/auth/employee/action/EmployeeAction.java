package org.erp.auth.employee.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.entity.EmployeeQueryModel;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.role.entity.RoleModel;
import org.erp.util.base.BaseAction;

public class EmployeeAction extends BaseAction<EmployeeModel> {
	public EmployeeQueryModel ehq = new EmployeeQueryModel();
	public String newPass;
	public Long[] roleUuids;
	public String login()
	{
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
		
		EmployeeModel empMode = employeeServ.login(model.getUserName(),model.getUserPass(),loginIp);
		if(empMode != null)
		{
			List<ResourceModel> resourcs = resourceServ.findByEmpId(empMode.getUuid());
			
			StringBuilder sb = new StringBuilder();
			
			for (ResourceModel resourceModel : resourcs) {
				sb.append(resourceModel.getUrl());
				sb.append("-");
			}
			empMode.setResourceView(sb.toString());
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
