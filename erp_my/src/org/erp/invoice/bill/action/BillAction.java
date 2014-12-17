package org.erp.invoice.bill.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.erp.invoice.bill.entity.BillQueryModel;
import org.erp.invoice.bill.service.impl.BillServiceImpl;
import org.erp.invoice.bill.service.service.BillService;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseAction;

public class BillAction extends BaseAction<BillQueryModel>{
	public BillService billServ = new BillServiceImpl();
	public BillQueryModel bhq = new BillQueryModel();
	
	public String list()
	{
		List<Object[]> billList = billServ.findBillList(bhq);
		put("billList", billList);
		List<SupplierModel> supplierList = supplierServ.findAll();
		put("supplierList", supplierList);
		return "billList";
	}
	//获得饼状图
	public String getPieDataImage() throws IOException
	{
		OutputStream outStr = getResponse().getOutputStream();
		billServ.getPieDataImage(outStr,bhq);
		outStr.flush();
		return null;
	}
	//下载Execel报表
	public InputStream downloadInput;
	
	public InputStream getDownloadInput() {
		return downloadInput;
	}
	
	public void setDownloadInput(InputStream downloadInput) {
		this.downloadInput = downloadInput;
	}
	private String execelName;
	
	public String getExecelName() throws UnsupportedEncodingException {
		//解决中文乱码
		return new String(execelName.getBytes("utf-8"),"iso-8859-1");
	}
	public void setExecelName(String execelName) {
		this.execelName = execelName;
	}
	public String getExecelBill() throws Exception
	{
		execelName = "采购报表";
		downloadInput = billServ.writeExecel(bhq);
		return "getExecelBill";
	}
	List<OrderDetailModel> orderDeList;
	  
	public List<OrderDetailModel> getOrderDeList() {
		return orderDeList;
	}

	public void setOrderDeList(List<OrderDetailModel> orderDeList) {
		this.orderDeList = orderDeList;
	}

	//Ajax
	public String ajaxFindODmByGoods()
	{
		orderDeList = billServ.findBillData(bhq);
		return "ajaxFindODmByGoods";
	}
	
	public BillService getBillServ() {
		return billServ;
	}

	public void setBillServ(BillService billServ) {
		this.billServ = billServ;
	}
	
}
