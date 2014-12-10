package org.erp.invoice.goodstype.action;

import java.util.List;

import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.goodstype.entity.GoodsTypeQueryModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseAction;

public class GoodsTypeAction extends BaseAction<GoodsTypeModel> {
	public GoodsTypeQueryModel ghq = new GoodsTypeQueryModel();
	public String list()
	{
		List<SupplierModel> supplierList = supplierServ.findAll();
		put("supplierList", supplierList);
		list = goodsTypeServ.findAll(ghq, currPage, pageSize);
		rows = goodsTypeServ.rowCount(ghq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			goodsTypeServ.save(model);
		}
		else
		{
			goodsTypeServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		List<SupplierModel> supplierList = supplierServ.findAll();
		put("supplierList", supplierList);
		if(model.getUuid() != null)
		{
			model = goodsTypeServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		goodsTypeServ.delete(model);
		return TO_LIST;
	}
}
