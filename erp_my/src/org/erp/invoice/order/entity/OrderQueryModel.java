package org.erp.invoice.order.entity;
import org.erp.util.base.BaseModel;
public class OrderQueryModel extends OrderModel implements BaseModel
{
	public Integer totalNumEnd;
	public Long createTimeEnd;
	public Double totalPriceEnd;
	
	public Integer getTotalNumEnd() {
		return totalNumEnd;
	}
	public void setTotalNumEnd(Integer totalNumEnd) {
		this.totalNumEnd = totalNumEnd;
	}
	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Double getTotalPriceEnd() {
		return totalPriceEnd;
	}
	public void setTotalPriceEnd(Double totalPriceEnd) {
		this.totalPriceEnd = totalPriceEnd;
	}
	
	
	
}