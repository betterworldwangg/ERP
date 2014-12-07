package org.erp.auth.employee.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.erp.auth.employee.dao.dao.EmployeeDao;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.service.service.EmployeeService;
import org.erp.auth.role.dao.dao.RoleDao;
import org.erp.auth.role.entity.RoleModel;
import org.erp.util.MD5Utils;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class EmployeeServiceImpl implements EmployeeService
{
	private EmployeeDao employeeDao;
	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
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
	//废弃
	@Override
	public void save(EmployeeModel model) {
		String pass = model.getUserPass();
		model.setUserPass(MD5Utils.md5(pass));
		employeeDao.save(model);
	}
	@Override
	public EmployeeModel findById(Long uuid) {
		return employeeDao.findById(uuid);
	}
	//废弃
	@Override
	public void update(EmployeeModel model) {
		EmployeeModel em = new EmployeeModel();
		em = employeeDao.findById(model.getUuid());
		
		em.setUserName(model.getUserName());
		em.setName(model.getName());
		em.setEmail(model.getEmail());
		em.setPhone(model.getPhone());
		em.setSex(model.getSex());
		em.setAddres(model.getAddres());
		em.setBirthday(model.getBirthday());
		em.setDepartM(model.getDepartM());
		employeeDao.update(em);
	}
	@Override
	public void delete(EmployeeModel entity) {
		employeeDao.delete(entity);
	}
	@Override
	public List<EmployeeModel> findAll() {
		return employeeDao.findAll();
	}
	public void save(EmployeeModel model, Long[] roleUuids) {
		Set<RoleModel> roms = new HashSet<RoleModel>();
		for (Long uuid : roleUuids) {
			RoleModel rm = roleDao.findById(uuid);
			roms.add(rm);
		}
		model.setRoles(roms);
		employeeDao.save(model);
	}
	public void update(EmployeeModel model, Long[] roleUuids) {
		EmployeeModel em = new EmployeeModel();
		em = employeeDao.findById(model.getUuid());
		
		em.setUserName(model.getUserName());
		em.setName(model.getName());
		em.setEmail(model.getEmail());
		em.setPhone(model.getPhone());
		em.setSex(model.getSex());
		em.setAddres(model.getAddres());
		em.setBirthday(model.getBirthday());
		em.setDepartM(model.getDepartM());
		
		Set<RoleModel> roms = new HashSet<RoleModel>();
		for (Long uuid : roleUuids) {
			RoleModel rm = roleDao.findById(uuid);
			roms.add(rm);
		}
		em.setRoles(roms);
		employeeDao.update(em);
	}
	@Override
	public Boolean changePass(String userName, String userPass, String newPass) {
		newPass = MD5Utils.md5(newPass);
		userPass=MD5Utils.md5(userPass);
		return employeeDao.changePass(userName,userPass,newPass);
	}

}