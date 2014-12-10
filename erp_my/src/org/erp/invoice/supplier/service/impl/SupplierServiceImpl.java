package org.erp.invoice.supplier.service.impl;

import java.util.List;

import org.erp.invoice.supplier.dao.dao.SupplierDao;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.invoice.supplier.service.service.SupplierService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class SupplierServiceImpl implements SupplierService
{
	private SupplierDao supplierDao;
	@Override
	public List<SupplierModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return supplierDao.findAll(bsm,currPage,pageSize);
	}
	public SupplierDao getSupplierDao() {
		return supplierDao;
	}
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return supplierDao.rowCount(dct);
	}
	@Override
	public void save(SupplierModel model) {
		supplierDao.save(model);
	}
	@Override
	public SupplierModel findById(Long uuid) {
		return supplierDao.findById(uuid);
	}
	@Override
	public void update(SupplierModel model) {
		supplierDao.update(model);
	}
	@Override
	public void delete(SupplierModel entity) {
		supplierDao.delete(entity);
	}
	@Override
	public List<SupplierModel> findAll() {
		// TODO Auto-generated method stub
		return supplierDao.findAll();
	}
	
	public List<SupplierModel> findAllHaveGoods() {
		return supplierDao.findAllHaveGoods();
	}

}