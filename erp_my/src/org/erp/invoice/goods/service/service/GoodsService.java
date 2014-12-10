package org.erp.invoice.goods.service.service;

import java.util.List;

import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface GoodsService extends BaseService<GoodsModel> 
{

	List<GoodsModel> findAllByGoodsTypeUuid(Long uuid);

}