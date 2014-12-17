package org.erp.invoice.storedetail.action;

import org.erp.invoice.storedetail.entity.StoreDetailModel;
import org.erp.invoice.storedetail.entity.StoreDetailQueryModel;
import org.erp.util.base.BaseAction;

public class StoreDetailAction extends BaseAction<StoreDetailModel> {
	public StoreDetailQueryModel shq = new StoreDetailQueryModel();
	public String list()
	{
		list = storeDetailServ.findAll(shq, currPage, pageSize);
		rows = storeDetailServ.rowCount(shq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return "storeDetailList";
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			storeDetailServ.save(model);
		}
		else
		{
			storeDetailServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = storeDetailServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		storeDetailServ.delete(model);
		return TO_LIST;
	}
}
