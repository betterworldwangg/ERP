package org.erp.invoice.storeoperate.action;

import org.erp.invoice.storeoperate.entity.StoreOperateModel;
import org.erp.invoice.storeoperate.entity.StoreOperateQueryModel;
import org.erp.util.base.BaseAction;

public class StoreOperateAction extends BaseAction<StoreOperateModel> {
	public StoreOperateQueryModel shq = new StoreOperateQueryModel();
	public String list()
	{
		list = storeOperateServ.findAll(shq, currPage, pageSize);
		rows = storeOperateServ.rowCount(shq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return "storeOperateList";
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			storeOperateServ.save(model);
		}
		else
		{
			storeOperateServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = storeOperateServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		storeOperateServ.delete(model);
		return TO_LIST;
	}
}
