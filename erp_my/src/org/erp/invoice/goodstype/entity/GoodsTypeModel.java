package org.erp.invoice.goodstype.entity;

import org.erp.invoice.supplier.entity.SupplierModel;

public class GoodsTypeModel {
	private Long uuid;
	private String name;
	private SupplierModel supplierM;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SupplierModel getSupplierM() {
		return supplierM;
	}
	public void setSupplierM(SupplierModel supplierM) {
		this.supplierM = supplierM;
	}
	
	

}
