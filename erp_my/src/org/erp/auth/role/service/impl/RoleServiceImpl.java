package org.erp.auth.role.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.erp.auth.menu.dao.dao.MenuDao;
import org.erp.auth.menu.entity.MenuModel;
import org.erp.auth.resource.dao.dao.ResourceDao;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.role.dao.dao.RoleDao;
import org.erp.auth.role.entity.RoleModel;
import org.erp.auth.role.service.service.RoleService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class RoleServiceImpl implements RoleService
{
	private RoleDao roleDao;
	private ResourceDao resourceDao;
	private MenuDao menuDao;
	@Override
	public List<RoleModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return roleDao.findAll(bsm,currPage,pageSize);
	}
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return roleDao.rowCount(dct);
	}
	//废弃
	@Override
	public void save(RoleModel model) {
		roleDao.save(model);
	}
	@Override
	public RoleModel findById(Long uuid) {
		return roleDao.findById(uuid);
	}
	//废弃
	@Override
	public void update(RoleModel model) {
		roleDao.update(model);
	}
	@Override
	public void delete(RoleModel entity) {
		roleDao.delete(entity);
	}
	@Override
	public List<RoleModel> findAll() {
		return roleDao.findAll();
	}
	
	public void save(RoleModel model, Long[] uuids,Long[] menuUuids) {
		Set<ResourceModel> rms = new HashSet<ResourceModel>();
		
		for (Long uuid : uuids) {
			ResourceModel resourM = resourceDao.findById(uuid);
			rms.add(resourM);
		}
		Set<MenuModel> mms = new HashSet<MenuModel>();
		for (Long uuid : menuUuids) {
			MenuModel menuM = menuDao.findById(uuid);
			mms.add(menuM);
		}
		model.setMenus(mms);
		model.setResources(rms);
		roleDao.save(model);
	}

	public void update(RoleModel model, Long[] uuids,Long[] menuUuids) {
		
		RoleModel rm = roleDao.findById(model.getUuid());
		rm.setName(model.getName());
		rm.setNumber(model.getNumber());
		
		Set<ResourceModel> rms = new HashSet<ResourceModel>();
		for (Long uuid : uuids) {
			ResourceModel resourM = resourceDao.findById(uuid);
			rms.add(resourM);
		}
		Set<MenuModel> mms = new HashSet<MenuModel>();
		for (Long uuid : menuUuids) {
			MenuModel menuM = menuDao.findById(uuid);
			mms.add(menuM);
		}
		rm.setMenus(mms);
		rm.setResources(rms);
		roleDao.update(rm);
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	

}