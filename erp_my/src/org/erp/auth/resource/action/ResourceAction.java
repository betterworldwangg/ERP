package org.erp.auth.resource.action;

import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.resource.entity.ResourceQueryModel;
import org.erp.util.base.BaseAction;

public class ResourceAction extends BaseAction<ResourceModel> {
	public ResourceQueryModel rhq = new ResourceQueryModel();
	public String list()
	{
		list = resourceServ.findAll(rhq, currPage, pageSize);
		rows = resourceServ.rowCount(rhq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			resourceServ.save(model);
		}
		else
		{
			resourceServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = resourceServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		resourceServ.delete(model);
		return TO_LIST;
	}
}
