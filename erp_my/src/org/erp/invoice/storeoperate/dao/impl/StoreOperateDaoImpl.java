package org.erp.invoice.storeoperate.dao.impl;

import org.erp.invoice.storeoperate.dao.dao.StoreOperateDao;
import org.erp.invoice.storeoperate.entity.StoreOperateModel;
import org.erp.invoice.storeoperate.entity.StoreOperateQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class StoreOperateDaoImpl extends BaseDaoImpl<StoreOperateModel> implements StoreOperateDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		StoreOperateQueryModel shm = (StoreOperateQueryModel) bsm;
		//TODO高级查询判断逻辑
	}
}