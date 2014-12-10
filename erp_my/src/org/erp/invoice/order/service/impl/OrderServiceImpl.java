package org.erp.invoice.order.service.impl;

import java.util.List;

import org.erp.invoice.order.dao.dao.OrderDao;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.service.service.OrderService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class OrderServiceImpl implements OrderService
{
	private OrderDao orderDao;
	@Override
	public List<OrderModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return orderDao.findAll(bsm,currPage,pageSize);
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return orderDao.rowCount(dct);
	}
	@Override
	public void save(OrderModel model) {
		orderDao.save(model);
	}
	@Override
	public OrderModel findById(Long uuid) {
		return orderDao.findById(uuid);
	}
	@Override
	public void update(OrderModel model) {
		orderDao.update(model);
	}
	@Override
	public void delete(OrderModel entity) {
		orderDao.delete(entity);
	}
	@Override
	public List<OrderModel> findAll() {
		return orderDao.findAll();
	}

}