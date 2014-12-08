package org.erp.auth.resource.service.service;

import java.util.List;

import org.erp.auth.resource.entity.ResourceModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ResourceService extends BaseService<ResourceModel> 
{

	List<ResourceModel> findByEmpId(Long uuid);

	public void save(ResourceModel model, Long[] uuids);

	public void update(ResourceModel model, Long[] uuids);

}