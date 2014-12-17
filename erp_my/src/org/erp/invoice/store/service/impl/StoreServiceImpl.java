package org.erp.invoice.store.service.impl;

import java.util.List;

import org.erp.invoice.store.dao.dao.StoreDao;
import org.erp.invoice.store.entity.StoreModel;
import org.erp.invoice.store.service.service.StoreService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class StoreServiceImpl implements StoreService
{
	private StoreDao storeDao;
	@Override
	public List<StoreModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return storeDao.findAll(bsm,currPage,pageSize);
	}
	public StoreDao getStoreDao() {
		return storeDao;
	}
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return storeDao.rowCount(dct);
	}
	@Override
	public void save(StoreModel model) {
		storeDao.save(model);
	}
	@Override
	public StoreModel findById(Long uuid) {
		return storeDao.findById(uuid);
	}
	@Override
	public void update(StoreModel model) {
		storeDao.update(model);
	}
	@Override
	public void delete(StoreModel entity) {
		storeDao.delete(entity);
	}
	@Override
	public List<StoreModel> findAll() {
		return storeDao.findAll();
	}

}