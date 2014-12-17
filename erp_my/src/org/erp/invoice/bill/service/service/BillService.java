package org.erp.invoice.bill.service.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.erp.invoice.bill.entity.BillQueryModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.springframework.transaction.annotation.Transactional;

public interface BillService
{

	List<Object[]> findBillList(BillQueryModel bhq);

	List<OrderDetailModel> findBillData(BillQueryModel bhq);

	void getPieDataImage(OutputStream outStr, BillQueryModel bhq) throws IOException;

	InputStream writeExecel(BillQueryModel bhq) throws Exception;

}