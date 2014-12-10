package org.erp.invoice.supplier.service.service;

import java.util.List;

import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface SupplierService extends BaseService<SupplierModel> 
{

	List<SupplierModel> findAllHaveGoods();

}