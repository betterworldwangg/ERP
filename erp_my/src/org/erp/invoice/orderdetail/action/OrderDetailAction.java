package org.erp.invoice.orderdetail.action;

import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.orderdetail.entity.OrderDetailQueryModel;
import org.erp.util.base.BaseAction;

public class OrderDetailAction extends BaseAction<OrderDetailModel> {
	public OrderDetailQueryModel ohq = new OrderDetailQueryModel();
	public Long orderDuuid;
	
	public String list()
	{
		list = orderDetailServ.findAll(ohq, currPage, pageSize);
		rows = orderDetailServ.rowCount(ohq);
		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;
		return LIST;
	}
	public String save()
	{
		if(model.getUuid() == null)
		{
			orderDetailServ.save(model);
		}
		else
		{
			orderDetailServ.update(model);
		}
		return TO_LIST;
	}
	public String input()
	{
		if(model.getUuid() != null)
		{
			model = orderDetailServ.findById(model.getUuid());
		}
		return INPUTUI;
	}
	public String delete()
	{
		orderDetailServ.delete(model);
		return TO_LIST;
	}
	
	//AJAX
	
	public String ajaxFindByUuid()
	{
		model = orderDetailServ.findById(orderDuuid);
		
		return "ajaxFindByUuid";
	}
	
	
	public Long getOrderDuuid() {
		return orderDuuid;
	}
	public void setOrderDuuid(Long orderDuuid) {
		this.orderDuuid = orderDuuid;
	}
	
}
