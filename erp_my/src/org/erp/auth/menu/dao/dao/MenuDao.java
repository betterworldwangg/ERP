package org.erp.auth.menu.dao.dao;

import java.util.List;

import org.erp.auth.menu.entity.MenuModel;
import org.erp.util.base.BaseDao;
public interface MenuDao extends BaseDao<MenuModel>
{

	List<MenuModel> findMenuParents();

	List<MenuModel> findByEmpUuid(Long uuid);

	List<MenuModel> findByEmpAndParentUuid(Long uuid, Long uuid2);

}