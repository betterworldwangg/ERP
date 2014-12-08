package org.erp.invoice.supplier.action;

import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.invoice.supplier.entity.SupplierQueryModel;
import org.erp.util.base.BaseAction;

public class SupplierAction extends BaseAction<SupplierModel> {
	public SupplierQueryModel shq = new SupplierQueryModel();
	public String list()
	{
		list = supplierServ.findAll(shq, currPage, pageSize);
		rows = supplierServ.rowCount(shq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			supplierServ.save(model);
		}
		else
		{
			supplierServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = supplierServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		supplierServ.delete(model);
		return TO_LIST;
	}
}
