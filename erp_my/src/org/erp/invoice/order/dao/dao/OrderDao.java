package org.erp.invoice.order.dao.dao;

import java.util.List;

import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.util.base.BaseDao;
import org.erp.util.base.BaseModel;
public interface OrderDao extends BaseDao<OrderModel>
{

	List<OrderModel> findAllTask(BaseModel bsm, int currPage, int pageSize,
			Integer[] types);

	int rowCount(BaseModel dct, Integer[] types);

	int rowCountTask(OrderQueryModel ohq, Integer[] types);

}