package org.erp.invoice.store.action;

import java.util.List;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.invoice.store.entity.StoreModel;
import org.erp.invoice.store.entity.StoreQueryModel;
import org.erp.util.base.BaseAction;

public class StoreAction extends BaseAction<StoreModel> {
	public StoreQueryModel shq = new StoreQueryModel();
	public String list()
	{
		list = storeServ.findAll(shq, currPage, pageSize);
		rows = storeServ.rowCount(shq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			storeServ.save(model);
		}
		else
		{
			storeServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		List<EmployeeModel> employeeList = employeeServ.findByDepartUuid(getLoginUser().getDepartM().getUuid());
		put("employeeList", employeeList);
		if(model.getUuid() != null)
		{
			model = storeServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		storeServ.delete(model);
		return TO_LIST;
	}
}
