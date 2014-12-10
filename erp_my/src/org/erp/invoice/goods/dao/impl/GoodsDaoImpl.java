package org.erp.invoice.goods.dao.impl;

import java.util.List;

import org.erp.invoice.goods.dao.dao.GoodsDao;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goods.entity.GoodsQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class GoodsDaoImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao
{
	//商品高级查询
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		GoodsQueryModel ghm = (GoodsQueryModel) bsm;
		
		if(ghm.getName() != null && ghm.getName().trim().length()>0)
		{
			dct.add(Restrictions.like("name", "%"+ghm.getName().trim()+"%"));
		}
		if(ghm.getProducer() != null && ghm.getProducer().trim().length()>0)
		{
			dct.add(Restrictions.like("producer", "%"+ghm.getProducer().trim()+"%"));
		}
		if(ghm.getUnit() != null && ghm.getUnit().length()>0)
		{
			dct.add(Restrictions.eq("unit", ghm.getUnit().trim()));
		}
		if(ghm.getInPriceFirst() != null && ghm.getInPriceFirst()>0)
		{
			dct.add(Restrictions.ge("inPrice", ghm.getInPriceFirst()));
		}
		if(ghm.getInPriceLast() != null && ghm.getInPriceLast() >0)
		{
			dct.add(Restrictions.le("inPrice", ghm.getInPriceLast()));
		}
		if(ghm.getOutPriceFirst() != null && ghm.getOutPriceFirst() >0)
		{
			dct.add(Restrictions.ge("outPrice", ghm.getOutPriceFirst()));
		}
		if(ghm.getOutPriceLast() != null && ghm.getOutPriceLast() >0)
		{
			dct.add(Restrictions.le("outPrice", ghm.getOutPriceLast()));
		}
		if(ghm.getGoodTypeMode() != null && ghm.getGoodTypeMode().getSupplierM() != null && ghm.getGoodTypeMode().getSupplierM().getUuid()!= null
				&& ghm.getGoodTypeMode().getSupplierM().getUuid() != -1)
		{
			//取别名
//			dct.createAlias("goodTypeMode", "goodType");
//			dct.createAlias("goodType.supplierM", "supplier");
//			dct.add(Restrictions.eq("supplier.uuid", ghm.getGoodTypeMode().getSupplierM().getUuid()));
			dct.createAlias("goodTypeMode", "goodType");
			dct.add(Restrictions.eq("goodType.supplierM", ghm.getGoodTypeMode().getSupplierM()));
		}
	}

	
	public List<GoodsModel> findAllByGoodsTypeUuid(Long uuid) {
		
		String hql = "from GoodsModel goods where goods.goodTypeMode.uuid = ?";
		
		return hibernateTemp.find(hql, uuid);
	}
}