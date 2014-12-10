package org.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.goodstype.entity.GoodsTypeQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class GoodsTypeDaoImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao
{
	
	
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		GoodsTypeQueryModel ghm = (GoodsTypeQueryModel) bsm;
		//TODO高级查询判断逻辑
		if(ghm.getName() != null && ghm.getName().trim().length()>0)
		{
			dct.add(Restrictions.like("name", "%"+ghm.getName()+"%"));
		}
		if(ghm.getSupplierM() != null && ghm.getSupplierM().getUuid() != null && ghm.getSupplierM().getUuid() != -1)
		{
			dct.add(Restrictions.eq("supplierM", ghm.getSupplierM()));
		}
	}

	@Override
	public List<GoodsTypeModel> findAllBySuppUuid(Long supplierUuid) {
		
		String hql = "from GoodsTypeModel gtm where gtm.supplierM.uuid = ?";
		
		
		return hibernateTemp.find(hql, supplierUuid);
	}
}