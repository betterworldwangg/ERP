package org.erp.auth.menu.dao.dao;

import java.util.List;

import org.erp.auth.menu.entity.MenuModel;
import org.erp.util.base.BaseDao;
public interface MenuDao extends BaseDao<MenuModel>
{

	List<MenuModel> findMenuParents();

}