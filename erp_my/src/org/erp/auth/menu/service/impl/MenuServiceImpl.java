package org.erp.auth.menu.service.impl;

import java.util.List;

import org.erp.auth.menu.dao.dao.MenuDao;
import org.erp.auth.menu.entity.MenuModel;
import org.erp.auth.menu.service.service.MenuService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class MenuServiceImpl implements MenuService
{
	private MenuDao menuDao;
	@Override
	public List<MenuModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return menuDao.findAll(bsm,currPage,pageSize);
	}
	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return menuDao.rowCount(dct);
	}
	@Override
	public void save(MenuModel model) {
		menuDao.save(model);
	}
	@Override
	public MenuModel findById(Long uuid) {
		return menuDao.findById(uuid);
	}
	@Override
	public void update(MenuModel model) {
		menuDao.update(model);
	}
	@Override
	public void delete(MenuModel entity) {
		menuDao.delete(entity);
	}
	@Override
	public List<MenuModel> findAll() {
		return menuDao.findAll();
	}

	public void save(MenuModel model, Long[] uuids) {
		// TODO Auto-generated method stub
		
	}

	public void update(MenuModel model, Long[] uuids) {
		// TODO Auto-generated method stub
		
	}

	public List<MenuModel> findMenuParents() {
		return menuDao.findMenuParents();
	}

}