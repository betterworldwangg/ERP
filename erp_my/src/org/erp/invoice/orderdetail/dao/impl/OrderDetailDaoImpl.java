package org.erp.invoice.orderdetail.dao.impl;

import org.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.orderdetail.entity.OrderDetailQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetailModel> implements OrderDetailDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		OrderDetailQueryModel ohm = (OrderDetailQueryModel) bsm;
		//TODO高级查询判断逻辑
	}
}