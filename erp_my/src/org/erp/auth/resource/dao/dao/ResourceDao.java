package org.erp.auth.resource.dao.dao;

import java.util.List;

import org.erp.auth.resource.entity.ResourceModel;
import org.erp.util.base.BaseDao;
public interface ResourceDao extends BaseDao<ResourceModel>
{

	List<ResourceModel> findByEmpId(Long uuid);

}