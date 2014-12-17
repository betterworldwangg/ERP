package org.erp.invoice.bill.entity;
import org.erp.util.base.BaseModel;
public class BillQueryModel implements BaseModel
{
	private Long supplierUuid;
	private Long goodsUuid;
	private Long startTime;
	private Long endTime;
	private Integer type;
	
	public Long getSupplierUuid() {
		return supplierUuid;
	}
	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}
	public Long getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}