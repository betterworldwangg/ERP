package org.erp.auth.resource.dao.impl;

import org.erp.auth.resource.dao.dao.ResourceDao;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.resource.entity.ResourceQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ResourceDaoImpl extends BaseDaoImpl<ResourceModel> implements ResourceDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		ResourceQueryModel rhm = (ResourceQueryModel) bsm;
		//TODO高级查询判断逻辑
	}
}