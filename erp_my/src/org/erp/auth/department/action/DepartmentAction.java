package org.erp.auth.department.action;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.department.entity.DepartmentQueryModel;
import org.erp.util.base.BaseAction;

public class DepartmentAction extends BaseAction<DepartmentModel> {
	public DepartmentQueryModel dhq = new DepartmentQueryModel();
	public String list()
	{
		list = departmentServ.findAll(dhq, currPage, pageSize);
		rows = departmentServ.rowCount(dhq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			departmentServ.save(model);
		}
		else
		{
			departmentServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = departmentServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		departmentServ.delete(model);
		return TO_LIST;
	}
}
