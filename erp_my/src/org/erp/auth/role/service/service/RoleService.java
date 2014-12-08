package org.erp.auth.role.service.service;

import org.erp.auth.role.entity.RoleModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface RoleService extends BaseService<RoleModel> 
{

	void save(RoleModel model, Long[] resourUuids, Long[] menuUuids);

	void update(RoleModel model, Long[] resourUuids, Long[] menuUuids);

}