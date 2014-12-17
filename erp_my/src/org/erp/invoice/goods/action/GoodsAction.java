package org.erp.invoice.goods.action;

import java.util.List;

import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goods.entity.GoodsQueryModel;
import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseAction;

public class GoodsAction extends BaseAction<GoodsModel> {
	public GoodsQueryModel ghq = new GoodsQueryModel();
	public Long supplierUuid;
	public List<GoodsTypeModel> goodsTypeList;
	public String list()
	{
		List<SupplierModel> supplis = supplierServ.findAll();
		put("supplis", supplis);
		list = goodsServ.findAll(ghq, currPage, pageSize);
		rows = goodsServ.rowCount(ghq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			goodsServ.save(model);
		}
		else
		{
			goodsServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		List<SupplierModel> supplierList = supplierServ.findAll();
		
		put("supplierList", supplierList);
		supplierUuid = supplierList.get(0).getUuid();
		if(model.getUuid() != null)
		{
			model = goodsServ.findById(model.getUuid());
			supplierUuid = model.getGoodTypeMode().getSupplierM().getUuid();
		}
		goodsTypeList = goodsTypeServ.findAllBySuppUuid(supplierUuid);
		put("goodsTypeList", goodsTypeList);
		return INPUTUI;
	}
	public String delete()
	{
		goodsServ.delete(model);
		return TO_LIST;
	}
	
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	public void setGoodsTypeList(List<GoodsTypeModel> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}
	public Long getSupplierUuid() {
		return supplierUuid;
	}
	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}
	public String ajaxGetGdtBySuppId()
	{
		goodsTypeList = goodsTypeServ.findAllBySuppUuid(supplierUuid);
		return "ajaxGetGdtBySuppId";
	}
	
}
