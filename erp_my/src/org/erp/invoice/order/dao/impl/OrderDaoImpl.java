package org.erp.invoice.order.dao.impl;

import java.util.List;

import org.erp.invoice.order.dao.dao.OrderDao;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class OrderDaoImpl extends BaseDaoImpl<OrderModel> implements OrderDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		OrderQueryModel ohm = (OrderQueryModel) bsm;
		
		if(ohm.getCreator() != null && ohm.getCreator().getName() != null && ohm.getCreator().getName().trim().length()>0)
		{
			dct.createAlias("creator", "c");
			dct.add(Restrictions.like("c.name", "%"+ohm.getCreator().getName()+"%"));
		}
		if(ohm.getTotalNum() != null && ohm.getTotalNum()>0)
		{
			dct.add(Restrictions.ge("totalNum", ohm.getTotalNum()));
		}
		if(ohm.getTotalNumEnd() != null && ohm.getTotalNumEnd()>0)
		{
			dct.add(Restrictions.le("totalNum", ohm.getTotalNumEnd()));
		}
		if(ohm.getCreateTime() != null)
		{
			dct.add(Restrictions.ge("createTime", ohm.getCreateTime()));
		}
		if(ohm.getCreateTimeEnd() != null)
		{
			dct.add(Restrictions.le("createTime", ohm.getCreateTimeEnd()));
		}
		if(ohm.getTotalPrice() != null && ohm.getTotalPrice() >0)
		{
			dct.add(Restrictions.ge("totalPrice", ohm.getTotalPrice()));
		}
		if(ohm.getTotalPriceEnd() != null)
		{
			dct.add(Restrictions.le("totalPrice", ohm.getTotalPriceEnd()));
		}
		if(ohm.getType() != null && ohm.getType() != -1)
		{
			dct.add(Restrictions.eq("type", ohm.getType()));
		}
		if(ohm.getCompletor()!= null && ohm.getCompletor().getUuid() != null && ohm.getCompletor().getUuid() != -1)
		{
			dct.add(Restrictions.eq("completor", ohm.getCompletor()));
		}
	}
	public void highQuery(BaseModel bsm,DetachedCriteria dct,Integer[] types)
	{
		dct.add(Restrictions.in("type", types));
		highQuery(bsm, dct);
	}
	public List<OrderModel> findAllTask(BaseModel dhq, int currPage, int pageSize,Integer[] types)
	{
		DetachedCriteria dct = DetachedCriteria.forClass(OrderModel.class);
		highQuery(dhq,dct,types);
		
		return hibernateTemp.findByCriteria(dct, (currPage-1)*pageSize, pageSize);
	}
	public int rowCount(BaseModel dhq,Integer[] types) {
		DetachedCriteria dct = DetachedCriteria.forClass(OrderModel.class);
		dct.setProjection(Projections.rowCount());
		highQuery(dhq, dct,types);
		List<Long> counts = hibernateTemp.findByCriteria(dct);
		
		
		return counts.get(0).intValue();
	}
	@Override
	public int rowCountTask(OrderQueryModel ohq, Integer[] types) {
		DetachedCriteria dct = DetachedCriteria.forClass(OrderModel.class);
		dct.setProjection(Projections.rowCount());
		highQuery(ohq, dct,types);
		List<Long> counts = hibernateTemp.findByCriteria(dct);
		
		
		return counts.get(0).intValue();
	}
}