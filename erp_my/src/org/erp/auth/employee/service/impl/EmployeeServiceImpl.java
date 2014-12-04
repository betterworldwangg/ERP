package org.erp.auth.employee.service.impl;

import org.erp.auth.employee.dao.dao.EmployeeDao;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.service.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;
	@Override
	public EmployeeModel findByNameAndPass(String userName, String userPass) {
		return employeeDao.findByNameAndPass(userName,userPass);
	}
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	
}
