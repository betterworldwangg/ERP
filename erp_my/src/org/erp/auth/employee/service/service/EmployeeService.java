package org.erp.auth.employee.service.service;

import org.erp.auth.employee.entity.EmployeeModel;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface EmployeeService{

	EmployeeModel findByNameAndPass(String userName, String userPass);

}
