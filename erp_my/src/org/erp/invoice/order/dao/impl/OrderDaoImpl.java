package org.erp.invoice.order.dao.impl;

import org.erp.invoice.order.dao.dao.OrderDao;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrderDaoImpl extends BaseDaoImpl<OrderModel> implements OrderDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		OrderQueryModel ohm = (OrderQueryModel) bsm;
		//TODO高级查询判断逻辑
	}
}