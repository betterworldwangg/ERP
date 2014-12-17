package org.erp.invoice.storedetail.dao.impl;

import java.util.List;

import org.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import org.erp.invoice.storedetail.entity.StoreDetailModel;
import org.erp.invoice.storedetail.entity.StoreDetailQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class StoreDetailDaoImpl extends BaseDaoImpl<StoreDetailModel> implements StoreDetailDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		StoreDetailQueryModel shm = (StoreDetailQueryModel) bsm;
		//TODO高级查询判断逻辑
	}

	@Override
	public StoreDetailModel findByStoreAndGoods(Long uuid, Long storeUuid) {
		String hql = "from StoreDetailModel where storeM.uuid = ? and goodsM.uuid = ?";
		List<StoreDetailModel> list = hibernateTemp.find(hql, storeUuid,uuid);
		
		return list.size()>0?list.get(0):null;
	}
}