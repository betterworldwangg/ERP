package org.erp.auth.role.dao.impl;

import org.erp.auth.role.dao.dao.RoleDao;
import org.erp.auth.role.entity.RoleModel;
import org.erp.auth.role.entity.RoleQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class RoleDaoImpl extends BaseDaoImpl<RoleModel> implements RoleDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		RoleQueryModel rhm = (RoleQueryModel) bsm;
		//TODO高级查询判断逻辑
		if(rhm.getName() != null && rhm.getName().trim().length()>0)
		{
			dct.add(Restrictions.like("name", "%"+rhm.getName()+"%"));
		}
		if(rhm.getNumber() !=null && rhm.getNumber().trim().length()>0)
		{
			dct.add(Restrictions.like("number", rhm.getNumber()+"%"));
		}
	}
}