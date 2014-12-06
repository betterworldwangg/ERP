package org.erp.auth.department.dao.impl;

import org.erp.auth.department.dao.dao.DepartmentDao;
import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.department.entity.DepartmentQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class DepartmentDaoImpl extends BaseDaoImpl<DepartmentModel> implements DepartmentDao {
	
	public void highQuery(BaseModel dhq,DetachedCriteria dct)
	{
		DepartmentQueryModel dhm = (DepartmentQueryModel) dhq;
		if(dhm.getName() != null && !dhm.getName().trim().equals(""))
		{
			dct.add(Restrictions.like("name", "%"+dhm.getName()+"%"));
		}
		if(dhm.getPhone()!=null && dhm.getPhone().trim().equals(""))
		{
			dct.add(Restrictions.like("phone", dhm.getPhone()+"%"));
		}

	}

}
