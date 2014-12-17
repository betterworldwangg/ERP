package org.erp.invoice.bill.dao.dao;

import java.util.List;

import org.erp.invoice.bill.entity.BillQueryModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.util.base.BaseDao;
public interface BillDao
{

	List<Object[]> findBillList(BillQueryModel bhq);

	List<OrderDetailModel> findBillData(BillQueryModel bhq);

}