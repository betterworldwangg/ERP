package org.erp.invoice.order.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.order.dao.dao.OrderDao;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.invoice.order.service.service.OrderService;
import org.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.store.entity.StoreModel;
import org.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import org.erp.invoice.storedetail.entity.StoreDetailModel;
import org.erp.invoice.storeoperate.dao.dao.StoreOperateDao;
import org.erp.invoice.storeoperate.entity.StoreOperateModel;
import org.erp.util.MD5Utils;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class OrderServiceImpl implements OrderService
{
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private StoreOperateDao storeOperateDao;
	
	Integer[] types = new Integer[]{
			OrderModel.ORDER_TYPE_BUY_CHECK_PASS,
			OrderModel.ORDER_TYPE_BUY_BUYING,
			OrderModel.ORDER_TYPE_BUY_INSTORE,
			OrderModel.ORDER_TYPE_BUY_END
	};
	@Override
	public List<OrderModel> findAllTask(BaseModel bsm, int currPage,
			int pageSize) {
		return orderDao.findAllTask(bsm,currPage,pageSize,types);
	}
	
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	
	
	public StoreDetailDao getStoreDetailDao() {
		return storeDetailDao;
	}

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	
	
	public StoreOperateDao getStoreOperateDao() {
		return storeOperateDao;
	}

	public void setStoreOperateDao(StoreOperateDao storeOperateDao) {
		this.storeOperateDao = storeOperateDao;
	}

	@Override
	public int rowCount(BaseModel dct) {
		return orderDao.rowCount(dct,types);
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
	@Override
	public void save(OrderModel model, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmployeeModel loginUser) {
		//组合订单号
		String orderNum = MD5Utils.md5(System.currentTimeMillis()+"."+loginUser.getUuid());
		model.setOrderNum(orderNum);
		//订单创建者
		model.setCreator(loginUser);
		//订单创建时间
		model.setCreateTime(System.currentTimeMillis());
		//订单类别，销售、采购
		model.setOrderType(OrderModel.ORDER_ORDERTYPE_BUY);
		//订单状态
		model.setType(OrderModel.ORDER_TYPE_BUY_NOCHECK);
		//订单与订单明细关联关系
		
		Set<OrderDetailModel> orderDetails = new HashSet<OrderDetailModel>();
		Integer totalNum = 0;
		Double totalPrice = 0D;
		
		for(int i= 0 ; i<nums.length;i++)
		{
			//商品明细
			OrderDetailModel orderDetail = new OrderDetailModel();
			
			GoodsModel goods = new GoodsModel();
			//商品
			goods.setUuid(goodsUuids[i]);
			orderDetail.setGoodsM(goods);
			//价格
			orderDetail.setPrice(prices[i]);
			//数量
			orderDetail.setNum(nums[i]);
			
			orderDetails.add(orderDetail);
			orderDetail.setOrderM(model);
			
			totalNum += nums[i];
			totalPrice +=prices[i]*nums[i];
			
			
		}
		
		model.setOrderDetails(orderDetails);
		//订单中商品总数
		model.setTotalNum(totalNum);
		model.setTotalPrice(totalPrice);
		
		orderDao.save(model);
	}
	//审查订单通过
	@Override
	public void orderCheckPass(Long uuid, EmployeeModel loginUser) {
		OrderModel orderM = orderDao.findById(uuid);
		if(!orderM.getType().equals(OrderModel.ORDER_TYPE_BUY_NOCHECK))
		{
			throw new AppException("不是待审查订单，不可重复操作");
		}
		orderM.setType(OrderModel.ORDER_TYPE_BUY_CHECK_PASS);
		orderM.setChecker(loginUser);
		orderM.setCheckTime(System.currentTimeMillis());
	}
	@Override
	public void orderCheckNoPass(Long uuid, EmployeeModel loginUser) {
		OrderModel orderM = orderDao.findById(uuid);
		
		if(!orderM.getType().equals(OrderModel.ORDER_TYPE_BUY_NOCHECK))
		{
			throw new AppException("不是待审查订单，不可重复操作");
		}
		orderM.setType(OrderModel.ORDER_TYPE_BUY_CHECK_NOPASS);
		orderM.setChecker(loginUser);
		orderM.setCheckTime(System.currentTimeMillis());
	}
	//指定跟单
	@Override
	public void transportToEmp(OrderModel model) {
		OrderModel orderM = orderDao.findById(model.getUuid());
		
		if(!orderM.getType().equals(OrderModel.ORDER_TYPE_BUY_CHECK_PASS))
		{
			throw new AppException("该订单不在指定范围内");
		}
		orderM.setType(OrderModel.ORDER_TYPE_BUY_BUYING);
		orderM.setCompletor(model.getCompletor());
	}

	@Override
	public List<OrderModel> findAll(BaseModel dhq, int currPage, int pageSize) {
		
		return orderDao.findAll(dhq, currPage, pageSize);
	}

	@Override
	public int rowCountTask(OrderQueryModel ohq) {
		return orderDao.rowCountTask(ohq,types);
	}

	@Override
	public List<OrderModel> findAllTask(OrderQueryModel ohq, int currPage,
			int pageSize, EmployeeModel loginUser) {
		ohq.setCompletor(loginUser);
		return orderDao.findAll(ohq, currPage, pageSize);
	}

	@Override
	public int rowCountTask(OrderQueryModel ohq, EmployeeModel loginUser) {
		ohq.setCompletor(loginUser);
		return orderDao.rowCount(ohq);
	}

	@Override
	public void endTask(Long uuid) {
		OrderModel om = orderDao.findById(uuid);
		
		if(!om.getType().equals(OrderModel.ORDER_TYPE_BUY_BUYING))
		{
			throw new AppException("该订单不在结单范围内！！");
		}
		om.setType(OrderModel.ORDER_TYPE_BUY_INSTORE);
		
	}

	@Override
	public List<OrderModel> findAllInStore(OrderQueryModel ohq, int currPage,
			int pageSize) {
		ohq.setType(OrderModel.ORDER_TYPE_BUY_INSTORE);
		return orderDao.findAll(ohq, currPage, pageSize);
	}

	@Override
	public int rowCountInStore(OrderQueryModel ohq) {
		ohq.setType(OrderModel.ORDER_TYPE_BUY_INSTORE);
		return orderDao.rowCount(ohq);
	}

	@Override
	public OrderDetailModel inStore(Long storeUuid, Integer inNum,
			Long odMuuid, EmployeeModel loginUser) {
		
		OrderDetailModel orderDetailM = orderDetailDao.findById(odMuuid);
		//设置订单明细中的数量
		orderDetailM.setSurplus(orderDetailM.getSurplus()-inNum);
		//获得商品
		GoodsModel goodsM = orderDetailM.getGoodsM();
		//创建一个仓库明细
		StoreModel storeM = new StoreModel();
		storeM.setUuid(storeUuid);
		//通过商品和仓库查看是否有对应的仓库明细
		StoreDetailModel storeDetailM = storeDetailDao.findByStoreAndGoods(goodsM.getUuid(),storeUuid);
		//如果不存在则创建仓库明细
		if(storeDetailM == null)
		{
			storeDetailM = new StoreDetailModel();
			storeDetailM.setNum(inNum);
			storeDetailM.setGoodsM(goodsM);
			storeDetailM.setStoreM(storeM);
			
			storeDetailDao.save(storeDetailM);
		}
		else
		{
			storeDetailM.setNum(storeDetailM.getNum()+inNum);
		}
		//操作仓库操作明细
		
		StoreOperateModel storeOperateM = new StoreOperateModel();
		storeOperateM.setEmpM(loginUser);
		storeOperateM.setGoodsM(goodsM);
		storeOperateM.setNum(inNum);
		storeOperateM.setOperTime(System.currentTimeMillis());
		storeOperateM.setOrderDetailM(orderDetailM);
		storeOperateM.setStoreM(storeM);
		storeOperateM.setType(StoreOperateModel.STOREOPERATE_TYPE_IN);
		
		storeOperateDao.save(storeOperateM);
		
		//如果商品都入库完毕设置入库完毕
		
		int sum = 0 ; 
		
		for (OrderDetailModel orderDM : orderDetailM.getOrderM().getOrderDetails()) {
			sum += orderDM.getSurplus();
		}
		
		if(sum == 0 )
		{
			orderDetailM.getOrderM().setType(OrderModel.ORDER_TYPE_BUY_END);
			orderDetailM.getOrderM().setEndTime(System.currentTimeMillis());
		}
		
		return orderDetailM;
	}

}