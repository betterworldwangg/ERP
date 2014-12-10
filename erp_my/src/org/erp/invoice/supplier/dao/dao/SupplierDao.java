package org.erp.invoice.supplier.dao.dao;

import java.util.List;

import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseDao;
public interface SupplierDao extends BaseDao<SupplierModel>
{

	List<SupplierModel> findAllHaveGoods();

}