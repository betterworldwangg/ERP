package org.erp.util.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	protected HibernateTemplate hibernateTemp;
	private Class<T> clazz;
	
	
	public HibernateTemplate getHibernateTemp() {
		return hibernateTemp;
	}

	public void setHibernateTemp(HibernateTemplate hibernateTemp) {
		this.hibernateTemp = hibernateTemp;
	}

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();// 获得父类（BaseDaoImpl）类型
		// 获取泛型数组
		Type[] typeArguments = type.getActualTypeArguments();
		clazz = (Class<T>) typeArguments[0];
	}

	@Override
	public void save(T entity) {
		
		hibernateTemp.save(entity);
	}

	@Override
	public void delete(T entity) {
		
		hibernateTemp.delete(entity);
	}

	@Override
	public T findById(Long id) {
		return hibernateTemp.get(clazz,id);
	}

	@Override
	public void update(T entity) {
		
		hibernateTemp.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		DetachedCriteria dct = DetachedCriteria.forClass(clazz);
	
		return hibernateTemp.findByCriteria(dct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(BaseModel dhq, int currPage, int pageSize) {
		DetachedCriteria dct = DetachedCriteria.forClass(clazz);
		highQuery(dhq,dct);
		
		return hibernateTemp.findByCriteria(dct, (currPage-1)*pageSize, pageSize);
	}
	@Override
	public int rowCount(BaseModel dhq) {
		DetachedCriteria dct = DetachedCriteria.forClass(clazz);
		dct.setProjection(Projections.rowCount());
		highQuery(dhq, dct);
		List<Long> counts = hibernateTemp.findByCriteria(dct);
		
		
		return counts.get(0).intValue();
	}
	@Override
	public abstract void highQuery(BaseModel dhq,DetachedCriteria dct);
	

}
