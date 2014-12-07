package org.erp.util.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface BaseService<T> {
	List<T> findAll(BaseModel dhq, int currPage, int pageSize);

	List<T> findAll();
	
	int rowCount(BaseModel dhq);

	void save(T model);
	
	void save(T model,Long[] uuids);

	public T findById(Long uuid);

	void update(T model);
	
	void update(T model,Long[] uuids);

	void delete(T entity);

}
