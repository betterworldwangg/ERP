package org.erp.auth.department.service.impl;

import java.util.List;

import org.erp.auth.department.dao.dao.DepartmentDao;
import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.department.service.service.DepartmentService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao;
	@Override
	public List<DepartmentModel> findAll(BaseModel dhq, int currPage,
			int pageSize) {
		return departmentDao.findAll(dhq,currPage,pageSize);
	}
	public DepartmentDao getdepartmentDao() {
		return departmentDao;
	}
	public void setdepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return departmentDao.rowCount(dct);
	}
	@Override
	public void save(DepartmentModel model) {
		if(model.getName().trim().length() == 0)
		{
			throw new AppException("INFO_DEPART_NAME_EMPTY");
		}
		departmentDao.save(model);
	}
	@Override
	public DepartmentModel findById(Long uuid) {
		return departmentDao.findById(uuid);
	}
	@Override
	public void update(DepartmentModel model) {
		departmentDao.update(model);
	}
	@Override
	public void delete(DepartmentModel entity) {
		departmentDao.delete(entity);
	}
	
}
