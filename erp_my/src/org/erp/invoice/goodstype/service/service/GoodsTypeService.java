package org.erp.invoice.goodstype.service.service;

import java.util.List;

import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface GoodsTypeService extends BaseService<GoodsTypeModel> 
{

	List<GoodsTypeModel> findAllBySuppUuid(Long supplierUuid);

}