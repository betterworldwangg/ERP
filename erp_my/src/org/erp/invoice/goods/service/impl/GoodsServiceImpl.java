package org.erp.invoice.goods.service.impl;

import java.util.List;

import org.erp.invoice.goods.dao.dao.GoodsDao;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goods.service.service.GoodsService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class GoodsServiceImpl implements GoodsService
{
	private GoodsDao goodsDao;
	@Override
	public List<GoodsModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return goodsDao.findAll(bsm,currPage,pageSize);
	}
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return goodsDao.rowCount(dct);
	}
	@Override
	public void save(GoodsModel model) {
		goodsDao.save(model);
	}
	@Override
	public GoodsModel findById(Long uuid) {
		return goodsDao.findById(uuid);
	}
	@Override
	public void update(GoodsModel model) {
		goodsDao.update(model);
	}
	@Override
	public void delete(GoodsModel entity) {
		goodsDao.delete(entity);
	}
	@Override
	public List<GoodsModel> findAll() {
		return goodsDao.findAll();
	}
	
	public List<GoodsModel> findAllByGoodsTypeUuid(Long uuid) {
		return goodsDao.findAllByGoodsTypeUuid(uuid);
	}

}