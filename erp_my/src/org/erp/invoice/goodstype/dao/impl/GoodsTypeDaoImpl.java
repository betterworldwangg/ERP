package org.erp.invoice.goodstype.dao.impl;

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
	}
}