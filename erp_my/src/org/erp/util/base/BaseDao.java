package org.erp.util.base;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	void save(T entity);

	void delete(T entity);

	T findById(Long id);

	void update(T entity);

	List<T> findAll();
	
	public List<T> findAll(BaseModel dhq, int currPage,
			int pageSize);

	int rowCount(BaseModel dhq);

	void highQuery(BaseModel dhq, DetachedCriteria dct);

}
