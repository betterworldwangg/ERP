package org.erp.invoice.store.dao.impl;

import org.erp.invoice.store.dao.dao.StoreDao;
import org.erp.invoice.store.entity.StoreModel;
import org.erp.invoice.store.entity.StoreQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class StoreDaoImpl extends BaseDaoImpl<StoreModel> implements StoreDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		StoreQueryModel shm = (StoreQueryModel) bsm;
		//TODO高级查询判断逻辑
	}
}