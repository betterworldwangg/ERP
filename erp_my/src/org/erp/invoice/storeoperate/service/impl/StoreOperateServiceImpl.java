package org.erp.invoice.storeoperate.service.impl;

import java.util.List;

import org.erp.invoice.storeoperate.dao.dao.StoreOperateDao;
import org.erp.invoice.storeoperate.entity.StoreOperateModel;
import org.erp.invoice.storeoperate.service.service.StoreOperateService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class StoreOperateServiceImpl implements StoreOperateService
{
	private StoreOperateDao storeOperateDao;
	@Override
	public List<StoreOperateModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return storeOperateDao.findAll(bsm,currPage,pageSize);
	}
	public StoreOperateDao getStoreOperateDao() {
		return storeOperateDao;
	}
	public void setStoreOperateDao(StoreOperateDao storeOperateDao) {
		this.storeOperateDao = storeOperateDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return storeOperateDao.rowCount(dct);
	}
	@Override
	public void save(StoreOperateModel model) {
		storeOperateDao.save(model);
	}
	@Override
	public StoreOperateModel findById(Long uuid) {
		return storeOperateDao.findById(uuid);
	}
	@Override
	public void update(StoreOperateModel model) {
		storeOperateDao.update(model);
	}
	@Override
	public void delete(StoreOperateModel entity) {
		storeOperateDao.delete(entity);
	}
	@Override
	public List<StoreOperateModel> findAll() {
		return storeOperateDao.findAll();
	}

}