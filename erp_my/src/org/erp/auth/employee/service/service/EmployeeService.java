package org.erp.auth.employee.service.service;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.role.entity.RoleModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface EmployeeService extends BaseService<EmployeeModel> 
{
	EmployeeModel findByNameAndPass(String userName, String userPass);
	
	void save(EmployeeModel model, Long[] roleUuids);

	void update(EmployeeModel model, Long[] roleUuids);

	Boolean changePass(String userName, String userPass, String newPass);

	EmployeeModel login(String userName, String userPass, String loginIp);
	
	public void save(RoleModel model, Long[] uuids,Long[] menuUuids);
	
	public void update(RoleModel model, Long[] uuids,Long menuUuids);

}