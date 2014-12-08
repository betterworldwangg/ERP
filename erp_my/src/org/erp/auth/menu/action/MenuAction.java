package org.erp.auth.menu.action;

import java.util.List;

import org.erp.auth.menu.entity.MenuModel;
import org.erp.auth.menu.entity.MenuQueryModel;
import org.erp.util.base.BaseAction;

public class MenuAction extends BaseAction<MenuModel> {
	public MenuQueryModel mhq = new MenuQueryModel();
	public String list()
	{
		List<MenuModel> parentMenus = menuServ.findMenuParents();
		put("parentList", parentMenus);
		list = menuServ.findAll(mhq, currPage, pageSize);
		rows = menuServ.rowCount(mhq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			menuServ.save(model);
		}
		else
		{
			menuServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		List<MenuModel> parentMenus = menuServ.findMenuParents();
		if(model.getUuid() != null)
		{
			model = menuServ.findById(model.getUuid());
		}
		put("parentMenus", parentMenus);
		return INPUTUI;
	}
	public String delete()
	{
		menuServ.delete(model);
		return TO_LIST;
	}
}
