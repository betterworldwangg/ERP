package org.erp.invoice.bill.dao.impl;

import java.util.List;

import org.erp.invoice.bill.dao.dao.BillDao;
import org.erp.invoice.bill.entity.BillQueryModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BillDaoImpl implements BillDao
{
	public HibernateTemplate hibernateTemp;

	@Override
	public List<Object[]> findBillList(BillQueryModel bhq) {

		DetachedCriteria dct = DetachedCriteria.forClass(OrderDetailModel.class);
		
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.groupProperty("goodsM"));
		proList.add(Projections.sum("num"));
		
		dct.setProjection(proList);
		
		dct.createAlias("orderM", "om");
		
		if(bhq.getType() != null && bhq.getType() != -1)
		{
			dct.add(Restrictions.eq("om.type", bhq.getType()));
		}
		if(bhq.getStartTime() != null && bhq.getStartTime() != -1)
		{
			dct.add(Restrictions.ge("om.createTime", bhq.getStartTime()));
		}
		if(bhq.getEndTime() != null && bhq.getEndTime() != -1)
		{
			dct.add(Restrictions.le("om.endTime", bhq.getEndTime()));
		}
		if(bhq.getSupplierUuid() != null && bhq.getSupplierUuid() != -1)
		{
			dct.createAlias("om.supplier", "s");
			dct.add(Restrictions.eq("s.uuid", bhq.getSupplierUuid()));
		}
		return hibernateTemp.findByCriteria(dct);
	}

	public HibernateTemplate getHibernateTemp() {
		return hibernateTemp;
	}

	public void setHibernateTemp(HibernateTemplate hibernateTemp) {
		this.hibernateTemp = hibernateTemp;
	}

	@Override
	public List<OrderDetailModel> findBillData(BillQueryModel bhq) {
		
		DetachedCriteria dct = DetachedCriteria.forClass(OrderDetailModel.class);
		
		dct.createAlias("orderM", "om");
		
		if(bhq.getType() != null && bhq.getType() != -1)
		{
			dct.add(Restrictions.eq("om.type", bhq.getType()));
		}
		if(bhq.getStartTime() != null && bhq.getStartTime() != -1)
		{
			dct.add(Restrictions.ge("om.createTime", bhq.getStartTime()));
		}
		if(bhq.getEndTime() != null && bhq.getEndTime() != -1)
		{
			dct.add(Restrictions.le("om.endTime", bhq.getEndTime()));
		}
		if(bhq.getSupplierUuid() != null && bhq.getSupplierUuid() != -1)
		{
			dct.createAlias("om.supplier", "s");
			dct.add(Restrictions.eq("s.uuid", bhq.getSupplierUuid()));
		}
		if(bhq.getGoodsUuid() != null && bhq.getGoodsUuid() != -1)
		{
			dct.add(Restrictions.eq("goodsM.uuid", bhq.getGoodsUuid()));
		}
		return hibernateTemp.findByCriteria(dct);
	}
	
	
}