package org.erp.auth.menu.dao.impl;

import java.util.List;

import org.erp.auth.menu.dao.dao.MenuDao;
import org.erp.auth.menu.entity.MenuModel;
import org.erp.auth.menu.entity.MenuQueryModel;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class MenuDaoImpl extends BaseDaoImpl<MenuModel> implements MenuDao
{
	public void highQuery(BaseModel bsm,DetachedCriteria dct)
	{
		MenuQueryModel mhm = (MenuQueryModel) bsm;
		//TODO高级查询判断逻辑
		dct.add(Restrictions.not(Restrictions.eq("uuid", MenuModel.SYSTEM_MENU_UUID)));
		if(mhm.getName() != null && mhm.getName().trim().length()>0)
		{
			dct.add(Restrictions.like("name", "%"+mhm.getName()+"%"));
		}
		if(mhm.getParent()!= null && mhm.getParent().getUuid()!=null && mhm.getParent().getUuid() != -1)
		{
		
			dct.add(Restrictions.eq("parent", mhm.getParent()));
		}
		
	}

	@Override
	public List<MenuModel> findMenuParents() {
		String hql = "from MenuModel where uuid = ? or parent_uuid = ?";
		
		return hibernateTemp.find(hql, 1L,1L);
	}
}