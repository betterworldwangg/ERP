package org.erp.invoice.goodstype.service.impl;

import java.util.List;

import org.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.goodstype.service.service.GoodsTypeService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class GoodsTypeServiceImpl implements GoodsTypeService
{
	private GoodsTypeDao goodsTypeDao;
	@Override
	public List<GoodsTypeModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return goodsTypeDao.findAll(bsm,currPage,pageSize);
	}
	public GoodsTypeDao getGoodsTypeDao() {
		return goodsTypeDao;
	}
	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return goodsTypeDao.rowCount(dct);
	}
	@Override
	public void save(GoodsTypeModel model) {
		goodsTypeDao.save(model);
	}
	@Override
	public GoodsTypeModel findById(Long uuid) {
		return goodsTypeDao.findById(uuid);
	}
	@Override
	public void update(GoodsTypeModel model) {
		goodsTypeDao.update(model);
	}
	@Override
	public void delete(GoodsTypeModel entity) {
		goodsTypeDao.delete(entity);
	}
	@Override
	public List<GoodsTypeModel> findAll() {
		return goodsTypeDao.findAll();
	}
	@Override
	public List<GoodsTypeModel> findAllBySuppUuid(Long supplierUuid) {
		return goodsTypeDao.findAllBySuppUuid(supplierUuid);
	}

}