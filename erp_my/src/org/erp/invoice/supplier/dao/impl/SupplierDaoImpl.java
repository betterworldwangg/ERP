package org.erp.invoice.supplier.dao.impl;

import org.erp.invoice.supplier.dao.dao.SupplierDao;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.invoice.supplier.entity.SupplierQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class SupplierDaoImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		SupplierQueryModel shm = (SupplierQueryModel) bsm;
		//TODO高级查询判断逻辑
		if(shm.getName() != null && shm.getName().trim().length()>0)
		{
			dct.add(Restrictions.like("name", "%"+shm.getName()+"%"));
		}
		if(shm.getContact()!= null && shm.getContact().trim().length()>0)
		{
			dct.add(Restrictions.like("contact", "%"+shm.getContact()+"%"));
		}
		if(shm.getPhone() != null && shm.getPhone().trim().length()>0)
		{
			dct.add(Restrictions.like("phone", shm.getPhone()+"%"));
		}
		if(shm.getNeeds() != null && shm.getNeeds() != -1)
		{
			dct.add(Restrictions.eq("needs", shm.getNeeds()));
		}
	}
}