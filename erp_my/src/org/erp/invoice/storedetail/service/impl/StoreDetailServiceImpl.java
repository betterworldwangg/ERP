package org.erp.invoice.storedetail.service.impl;

import java.util.List;

import org.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import org.erp.invoice.storedetail.entity.StoreDetailModel;
import org.erp.invoice.storedetail.service.service.StoreDetailService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class StoreDetailServiceImpl implements StoreDetailService
{
	private StoreDetailDao storeDetailDao;
	@Override
	public List<StoreDetailModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return storeDetailDao.findAll(bsm,currPage,pageSize);
	}
	public StoreDetailDao getStoreDetailDao() {
		return storeDetailDao;
	}
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return storeDetailDao.rowCount(dct);
	}
	@Override
	public void save(StoreDetailModel model) {
		storeDetailDao.save(model);
	}
	@Override
	public StoreDetailModel findById(Long uuid) {
		return storeDetailDao.findById(uuid);
	}
	@Override
	public void update(StoreDetailModel model) {
		storeDetailDao.update(model);
	}
	@Override
	public void delete(StoreDetailModel entity) {
		storeDetailDao.delete(entity);
	}
	@Override
	public List<StoreDetailModel> findAll() {
		return storeDetailDao.findAll();
	}

}