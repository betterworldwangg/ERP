package org.erp.auth.employee.service.service;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface EmployeeService extends BaseService<EmployeeModel> 
{
	EmployeeModel findByNameAndPass(String userName, String userPass);

}