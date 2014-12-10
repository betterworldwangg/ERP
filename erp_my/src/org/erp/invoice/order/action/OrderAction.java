package org.erp.invoice.order.action;

import java.util.List;

import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseAction;

public class OrderAction extends BaseAction<OrderModel> {
	public OrderQueryModel ohq = new OrderQueryModel();
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public List<GoodsTypeModel> goodsTypeList;
	public List<GoodsModel> goodsList;
	public GoodsModel goods;
	
	public String list()
	{
		/*list = orderServ.findAll(ohq, currPage, pageSize);
		rows = orderServ.rowCount(ohq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;*/
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			orderServ.save(model);
		}
		else
		{
			orderServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		List<SupplierModel> supplierList = supplierServ.findAllHaveGoods();
		goodsTypeList = goodsTypeServ.findAllBySuppUuid(supplierList.get(0).getUuid());
		goodsList = goodsServ.findAllByGoodsTypeUuid(goodsTypeList.get(0).getUuid());
		put("supplierList", supplierList);
		put("goodsTypeList", goodsTypeList);
		put("goodsList", goodsList);
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
		goods = goodsList.get(0);
		return "ajaxGetGoodsByType";
	}
	public String ajaxGetPrice()
	{
		goods = goodsServ.findById(goodsUuid);
		return "ajaxGetPrice";
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
	
	
}
