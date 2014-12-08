package org.erp.auth.menu.service.service;

import java.util.List;

import org.erp.auth.menu.entity.MenuModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface MenuService extends BaseService<MenuModel> 
{

	List<MenuModel> findMenuParents();
	public void save(MenuModel model, Long[] uuids);

	public void update(MenuModel model, Long[] uuids);
		
}