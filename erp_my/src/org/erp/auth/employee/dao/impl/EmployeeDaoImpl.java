package org.erp.auth.employee.dao.impl;

import java.util.List;

import org.erp.auth.employee.dao.dao.EmployeeDao;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.entity.EmployeeQueryModel;
import org.erp.util.MD5Utils;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class EmployeeDaoImpl extends BaseDaoImpl<EmployeeModel> implements EmployeeDao
{
	@Override
	public EmployeeModel findByNameAndPass(String userName, String userPass) {
		String hql = "from EmployeeModel where userName = ? and userPass = ?";
		List<EmployeeModel> list = hibernateTemp.find(hql, userName,MD5Utils.md5(userPass));
		return list.size()>0 ? list.get(0) : null;
	}
	
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		EmployeeQueryModel ehm = (EmployeeQueryModel) bsm;
		//TODO高级查询判断逻辑
		if(ehm.getUserName() != null && ehm.getUserName().trim().length()>0)
		{
			dct.add(Restrictions.like("userName", "%"+ehm.getUserName()+"%"));
		}
		if(ehm.getName() != null && ehm.getName().trim().length()>0)
		{
			dct.add(Restrictions.like("name", "%"+ehm.getName()+"%"));
		}
		if(ehm.getPhone() != null && ehm.getPhone().trim().length()>0)
		{
			dct.add(Restrictions.like("phone",ehm.getPhone()+"%"));
		}
		if(ehm.getSex() != null && ehm.getSex() != -1)
		{
			dct.add(Restrictions.eq("sex", ehm.getSex()));
		}
		if(ehm.getEmail() != null && ehm.getEmail().trim().length()>0)
		{
			dct.add(Restrictions.like("email", ehm.getEmail()+"%"));
		}
		if(ehm.getLoginTimeFirst() != null)
		{
			dct.add(Restrictions.ge("loginTimeFirst", ehm.getLoginTimeFirst()));
		}
		if(ehm.getLoginTimeEnd() !=null)
		{
			dct.add(Restrictions.le("loginTimeEnd", ehm.getLoginTimeEnd()));
		}
		if(ehm.getDepartM()!=null && ehm.getDepartM().getUuid() != null && ehm.getDepartM().getUuid() != -1)
		{
			dct.add(Restrictions.eq("departM", ehm.getDepartM()));
		}
		
		
	}

	@Override
	public Boolean changePass(String userName, String userPass, String newPass) {
		String hql = "update EmployeeModel e set e.userPass=? where userName=? and userPass= ?";
		return hibernateTemp.bulkUpdate(hql, newPass,userName,userPass)>0 ? true : false;
	}
}