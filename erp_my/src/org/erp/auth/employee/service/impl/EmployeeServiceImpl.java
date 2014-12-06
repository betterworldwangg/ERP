package org.erp.auth.employee.service.impl;

import java.util.List;

import org.erp.auth.employee.dao.dao.EmployeeDao;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.service.service.EmployeeService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class EmployeeServiceImpl implements EmployeeService
{
	private EmployeeDao employeeDao;
	@Override
	public List<EmployeeModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return employeeDao.findAll(bsm,currPage,pageSize);
	}
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

	@Override
	public int rowCount(BaseModel dct) {
		return employeeDao.rowCount(dct);
	}
	@Override
	public void save(EmployeeModel model) {
		employeeDao.save(model);
	}
	@Override
	public EmployeeModel findById(Long uuid) {
		return employeeDao.findById(uuid);
	}
	@Override
	public void update(EmployeeModel model) {
		EmployeeModel em = new EmployeeModel();
		em.setUserName(model.getUserName());
		em.setName(model.getName());
		em.setEmail(model.getEmail());
		em.setPhone(model.getPhone());
		em.setSex(model.getSex());
		em.setAddres(model.getAddres());
		em.setBirthday(model.getBirthday());
		em.setDepartM(model.getDepartM());
		employeeDao.update(model);
	}
	@Override
	public void delete(EmployeeModel entity) {
		employeeDao.delete(entity);
	}
	@Override
	public List<EmployeeModel> findAll() {
		// TODO Auto-generated method stub
		return employeeDao.findAll();
	}

}