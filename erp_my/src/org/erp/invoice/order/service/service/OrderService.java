package org.erp.invoice.order.service.service;

import java.util.List;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.invoice.order.entity.OrderQueryModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.util.base.BaseModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface OrderService extends BaseService<OrderModel> 
{

	void save(OrderModel model, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmployeeModel loginUser);

	void orderCheckPass(Long uuid, EmployeeModel loginUser);

	void orderCheckNoPass(Long uuid, EmployeeModel loginUser);

	void transportToEmp(OrderModel model);
	
	public List<OrderModel> findAllTask(BaseModel bsm, int currPage,
			int pageSize);

	int rowCountTask(OrderQueryModel ohq);

	List<OrderModel> findAllTask(OrderQueryModel ohq, int currPage,
			int pageSize, EmployeeModel loginUser);

	int rowCountTask(OrderQueryModel ohq, EmployeeModel loginUser);

	void endTask(Long uuid);

	List<OrderModel> findAllInStore(OrderQueryModel ohq, int currPage,
			int pageSize);

	int rowCountInStore(OrderQueryModel ohq);

	OrderDetailModel inStore(Long storeUuid, Integer inNum, Long odMuuid,
			EmployeeModel loginUser);

}