package org.erp.invoice.goodstype.dao.dao;

import java.util.List;

import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.util.base.BaseDao;
public interface GoodsTypeDao extends BaseDao<GoodsTypeModel>
{

	List<GoodsTypeModel> findAllBySuppUuid(Long supplierUuid);

}