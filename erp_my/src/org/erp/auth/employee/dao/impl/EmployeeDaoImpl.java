package org.erp.auth.employee.dao.impl;

import java.util.List;

import org.erp.auth.employee.dao.dao.EmployeeDao;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.util.MD5Utils;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EmployeeDaoImpl extends BaseDaoImpl<EmployeeModel>implements EmployeeDao {
	@Override
	public EmployeeModel findByNameAndPass(String userName, String userPass) {
		String hql = "from EmployeeModel where userName = ? and userPass = ?";
		List<EmployeeModel> list = hibernateTemp.find(hql, userName,MD5Utils.md5(userPass));
		return list.size()>0 ? list.get(0) : null;
	}

	@Override
	public void highQuery(BaseModel dhq, DetachedCriteria dct) {
		// TODO Auto-generated method stub
		
	}
	
}
