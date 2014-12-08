package org.erp.auth.resource.service.impl;

import java.util.List;

import org.erp.auth.resource.dao.dao.ResourceDao;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.resource.service.service.ResourceService;
import org.erp.util.base.BaseModel;
import org.erp.util.exception.AppException;

public class ResourceServiceImpl implements ResourceService
{
	private ResourceDao resourceDao;
	@Override
	public List<ResourceModel> findAll(BaseModel bsm, int currPage,
			int pageSize) {
		return resourceDao.findAll(bsm,currPage,pageSize);
	}
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	@Override
	public int rowCount(BaseModel dct) {
		return resourceDao.rowCount(dct);
	}
	@Override
	public void save(ResourceModel model) {
		resourceDao.save(model);
	}
	@Override
	public ResourceModel findById(Long uuid) {
		return resourceDao.findById(uuid);
	}
	@Override
	public void update(ResourceModel model) {
		resourceDao.update(model);
	}
	@Override
	public void delete(ResourceModel entity) {
		resourceDao.delete(entity);
	}
	@Override
	public List<ResourceModel> findAll() {
		return resourceDao.findAll();
	}
	
	public void save(ResourceModel model, Long[] uuids) {
		
	}

	public void update(ResourceModel model, Long[] uuids) {
		
	}
	@Override
	public List<ResourceModel> findByEmpId(Long uuid) {
		return resourceDao.findByEmpId(uuid);
	}

}