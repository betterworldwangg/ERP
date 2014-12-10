package org.erp.invoice.order.service.service;

import org.erp.invoice.order.entity.OrderModel;
import org.erp.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface OrderService extends BaseService<OrderModel> 
{

}