package org.erp.auth.menu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	public String showMenu() throws IOException
	{
		
		String root = getRequest().getParameter("root");
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		
		if(root.equals("source"))
		{
			//根据已登录用户——>该用户具有的角色->角色拥有的菜单权限
			List<MenuModel> menus = menuServ.findByEmpUuid(getLoginUser().getUuid());
			//将该用户所具有的所有父菜单权限拼成json字符串
			
			
			for (MenuModel menu : menus) {
				json.append("{\"text\":\"");
				json.append(menu.getName());
				json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				json.append(menu.getUuid());
				json.append("\"");
				json.append("},");
			}
		}
		//当点击父菜单插件会想后台发送id属性也就是该菜单的uuid属性
		else
		{
			
			Long uuid = new Long(root);
			//根据已登录用户uuid和页面传过来的父菜单的uuid查询子菜单
			List<MenuModel> menusSubs = menuServ.findByEmpAndParentUuid(getLoginUser().getUuid(),uuid);
			//将该用户所具有的所有子菜单权限拼成json字符串
			for (MenuModel menu : menusSubs) {
				json.append("{\"text\":\"");
				json.append("<a class='xiu' target='main' href=\'");
				json.append(menu.getUrl());
				json.append("\'>");
				json.append(menu.getName());
				json.append("</a>");
				json.append("\",\"hasChildren\":false,\"classes\":\"file\",\"id\":\"");
				json.append(menu.getUuid());
				json.append("\"");
				json.append("},");
			}
			
		}
		json.deleteCharAt(json.length()-1);
		json.append("]");
		
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.write(json.toString());
		pw.flush();
		
		return null;
	}
}
