package org.erp.invoice.goods.dao.dao;

import java.util.List;

import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.util.base.BaseDao;
public interface GoodsDao extends BaseDao<GoodsModel>
{

	List<GoodsModel> findAllByGoodsTypeUuid(Long uuid);

}