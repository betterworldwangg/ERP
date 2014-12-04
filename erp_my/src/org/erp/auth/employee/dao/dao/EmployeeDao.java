package org.erp.auth.employee.dao.dao;

import org.erp.auth.employee.entity.EmployeeModel;

public interface EmployeeDao{

	EmployeeModel findByNameAndPass(String userName, String userPass);

}
