package org.erp.invoice.goods.dao.impl;

import org.erp.invoice.goods.dao.dao.GoodsDao;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goods.entity.GoodsQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class GoodsDaoImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		GoodsQueryModel ghm = (GoodsQueryModel) bsm;
		//TODO高级查询判断逻辑
	}
}