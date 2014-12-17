package org.erp.invoice.storedetail.dao.dao;

import org.erp.invoice.storedetail.entity.StoreDetailModel;
import org.erp.util.base.BaseDao;
public interface StoreDetailDao extends BaseDao<StoreDetailModel>
{

	StoreDetailModel findByStoreAndGoods(Long uuid, Long storeUuid);

}