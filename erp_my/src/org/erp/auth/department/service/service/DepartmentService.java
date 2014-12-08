package org.erp.auth.department.service.service;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface DepartmentService extends BaseService<DepartmentModel> {

	public void save(DepartmentModel model, Long[] uuids);

	
	public void update(DepartmentModel model, Long[] uuids);
}
