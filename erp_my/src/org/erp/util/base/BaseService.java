package org.erp.util.base;

import java.util.List;

import org.erp.auth.menu.entity.MenuModel;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface BaseService<T> {
	List<T> findAll(BaseModel dhq, int currPage, int pageSize);

	List<T> findAll();
	
	int rowCount(BaseModel dhq);

	void save(T model);
	
	

	public T findById(Long uuid);

	void update(T model);
	
	

	void delete(T entity);

}
