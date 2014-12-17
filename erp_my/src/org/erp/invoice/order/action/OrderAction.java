package org.erp.invoice.order.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.store.entity.StoreModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseAction;

public class OrderAction extends BaseAction<OrderModel> {
	public OrderQueryModel ohq = new OrderQueryModel();
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String used;
	
	public List<GoodsTypeModel> goodsTypeList;
	public List<GoodsModel> goodsList;
	
	public GoodsModel goods;
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	
	public String list()
	{
		list = orderServ.findAll(ohq, currPage, pageSize);
		rows = orderServ.rowCount(ohq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		
		
		return LIST;
	}
	//保存订单
	public String save()
	{
		orderServ.save(model,goodsUuids,nums,prices,getLoginUser());
		
		return TO_LIST;
	}
	//增加订单页面
	public String input()
	{
		List<SupplierModel> supplierList = supplierServ.findAllHaveGoods();
		goodsTypeList = goodsTypeServ.findAllBySuppUuid(supplierList.get(0).getUuid());
		goodsList = goodsServ.findAllByGoodsTypeUuid(goodsTypeList.get(0).getUuid());
		goods = goodsList.get(0);
		put("supplierList", supplierList);
		put("goodsTypeList", goodsTypeList);
		put("goodsList", goodsList);
		put("goods", goods);
		if(model.getUuid() != null)
		{
			model = orderServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		orderServ.delete(model);
		return TO_LIST;
	}
	//订单详情
	public String inDetailList()
	{
		model = orderServ.findById(model.getUuid());
		model.getOrderDetails();
		return "detail";
	}
	
	//采购审批列表
	public String inApproveList()
	{
		list = orderServ.findAll(ohq, currPage, pageSize);
		rows = orderServ.rowCount(ohq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return "approveList";
	}
	public String inApprove()
	{
		model = orderServ.findById(model.getUuid());
		return "approve";
	}
	//采购订单通过
	public String orderCheckPass()
	{
		orderServ.orderCheckPass(model.getUuid(),getLoginUser());
		return "checkPass";
	}
	//采购订单被驳回
	public String orderCheckNoPass()
	{
		orderServ.orderCheckNoPass(model.getUuid(),getLoginUser());
		return "checkNoPass";
	}
	
	//运输任务的指配
	
	public String transportList()
	{
		list = orderServ.findAllTask(ohq, currPage, pageSize);
		rows = orderServ.rowCount(ohq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		
		return "transportList";
	}
	//进入任务指配页
	public String inputTrans()
	{
		model = orderServ.findById(model.getUuid());
		List<EmployeeModel>  empList = employeeServ.findByDepartUuid(getLoginUser().getDepartM().getUuid());
		put("empList", empList);
		return "transportInput";
	}
	//分配任务
	public String transportToEmp()
	{
		orderServ.transportToEmp(model);
		return "transportToEmp";
	}
	
	//任务查询列表
	
	public String tasks()
	{
		list = orderServ.findAllTask(ohq, currPage, pageSize,getLoginUser());
		rows = orderServ.rowCountTask(ohq,getLoginUser());
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		
		return "tasksList";
	}
	//任务详情
	public String taskDetail()
	{
		model = orderServ.findById(model.getUuid());
		return "taskDetail";
	}
	//结束任务,结单
	public String endTask()
	{
		orderServ.endTask(model.getUuid());
		
		return "endTask";
	}
	//入库列表页
	
	public String inStoreList()
	{
		list = orderServ.findAllInStore(ohq, currPage, pageSize);
		rows = orderServ.rowCountInStore(ohq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		
		return "inStoreList";
	}
	//入库明细页
	public String inStoreDetail()
	{
		List<StoreModel> storeList = storeServ.findAll();
		put("storeList", storeList);
		model = orderServ.findById(model.getUuid());
		return "inStoreDetail";
	}
	
	//Ajax
	
	public String ajaxGetGoodTypeBySupp()
	{
		goodsTypeList = goodsTypeServ.findAllBySuppUuid(supplierUuid);
		goodsList = goodsServ.findAllByGoodsTypeUuid(goodsTypeList.get(0).getUuid());
		goods = goodsList.get(0);
		return "ajaxGetGoodTypeBySupp";
	}
	public String ajaxGetGoodsByType()
	{
		goodsList = goodsServ.findAllByGoodsTypeUuid(goodsTypeUuid);
		
		String[] usedUuid = used.split(",");
		Set<Long> usedSet = new HashSet<Long>();
		
		for (String  us : usedUuid) {
			usedSet.add(new Long(us));
		}
		
		//遍历goodsList中的商品，如果goodsList中有已使用过的商品，则从goodsList中过滤掉
		for(int i = goodsList.size()-1;i>=0;i--)
		{
			GoodsModel goodM = goodsList.get(i);
			if(usedSet.contains(goodM.getUuid()))
			{
				goodsList.remove(i);
			}
		}
		goods = goodsList.get(0);
		return "ajaxGetGoodsByType";
	}
	public String ajaxGetPrice()
	{
		goods = goodsServ.findById(goodsUuid);
		return "ajaxGetPrice";
	}
	
	
	public String ajaxGetGoodTypeBySupp2()
	{
		
		String[] usedUuid =  used.split(",");
		
		Set<Long> usedSet = new HashSet<Long>();
		
		for (String s : usedUuid) {
			usedSet.add(new Long(s));
		}
		//如果商品类别已没有商品，则过滤掉该类别
		
		//根据供货商的uuid获取商品类别
		goodsTypeList = goodsTypeServ.findAllBySuppUuid(supplierUuid);
		//遍历该供货商下所有商品类别
		gtm:
		for (int i = goodsTypeList.size()-1;i>=0;i--) {
			//根据该商品类别获得所有商品
			GoodsTypeModel goodsT = goodsTypeList.get(i);
			List<GoodsModel> goods = goodsServ.findAllByGoodsTypeUuid(goodsT.getUuid());
			//遍历该商品类别下所有商品
			for (GoodsModel goodsModel : goods) {
				//如果该商品不在已使用的集合内，保留该商品类型
				if(!usedSet.contains(goodsModel.getUuid()))
				{
					continue gtm;
				}
			}
			//如果该商品类型下所有商品都已被使用，从goodsTypeList中过滤掉
			goodsTypeList.remove(i);
		}
		
		//如果商品已被使用，则过滤掉该商品
		
		//获取第一个goodsTypeList中的所有商品
		goodsList = goodsServ.findAllByGoodsTypeUuid(goodsTypeList.get(0).getUuid());
		//过滤到goodsList中已被使用的商品
		for(int i =goodsList.size()-1;i>=0;i--)
		{
			GoodsModel goodTemp = goodsList.get(i);
			if(usedSet.contains(goodTemp.getUuid()))
			{
				goodsList.remove(i);
			}
		}
		goods = goodsList.get(0);
		
		return "ajaxGetGoodTypeBySupp";
	}
	//入库操作
	public Long storeUuid;
	public Integer inNum;
	public Long odMuuid;
	public OrderDetailModel orderM;
	
	public String ajaxInstore()
	{
		orderM = orderServ.inStore(storeUuid,inNum,odMuuid,getLoginUser());
		
		return "ajaxInstore";
	}
	
	public OrderDetailModel getOrderM() {
		return orderM;
	}
	public Long getSupplierUuid() {
		return supplierUuid;
	}
	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	public void setGoodsTypeList(List<GoodsTypeModel> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}
	public Long getGoodsTypeUuid() {
		return goodsTypeUuid;
	}
	public void setGoodsTypeUuid(Long goodsTypeUuid) {
		this.goodsTypeUuid = goodsTypeUuid;
	}
	public List<GoodsModel> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GoodsModel> goodsList) {
		this.goodsList = goodsList;
	}
	public Long getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public GoodsModel getGoods() {
		return goods;
	}
	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	
	
}
