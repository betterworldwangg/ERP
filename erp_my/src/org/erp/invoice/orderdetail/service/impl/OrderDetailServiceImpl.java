package org.erp.invoice.orderdetail.service.impl;

import java.util.List;

import org.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.orderdetail.service.service.OrderDetailService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class OrderDetailServiceImpl implements OrderDetailService
{
	private OrderDetailDao orderDetailDao;
	@Override
	public List<OrderDetailModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return orderDetailDao.findAll(bsm,currPage,pageSize);
	}
	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return orderDetailDao.rowCount(dct);
	}
	@Override
	public void save(OrderDetailModel model) {
		orderDetailDao.save(model);
	}
	@Override
	public OrderDetailModel findById(Long uuid) {
		return orderDetailDao.findById(uuid);
	}
	@Override
	public void update(OrderDetailModel model) {
		orderDetailDao.update(model);
	}
	@Override
	public void delete(OrderDetailModel entity) {
		orderDetailDao.delete(entity);
	}
	@Override
	public List<OrderDetailModel> findAll() {
		return orderDetailDao.findAll();
	}

}