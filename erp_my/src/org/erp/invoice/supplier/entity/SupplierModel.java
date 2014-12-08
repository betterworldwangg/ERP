package org.erp.invoice.supplier.entity;

import java.util.HashMap;
import java.util.Map;

public class SupplierModel {
	
	public static final String SUPPLIER_NEEDS_YES = "送货";
	public static final String SUPPLIER_NEEDS_NO = "提货";
	
	public static final Integer SUPPLIER_NEEDS_YES_VIEW = 0;
	public static final Integer SUPPLIER_NEEDS_NO_VIEW = 1;
	
	public static final Map<Integer, String> needsMap = new HashMap<Integer, String>();
	
	static{
		needsMap.put(SUPPLIER_NEEDS_YES_VIEW, SUPPLIER_NEEDS_YES);
		needsMap.put(SUPPLIER_NEEDS_NO_VIEW, SUPPLIER_NEEDS_NO);
		
	}
	
	private Long uuid;
	private String name;
	private String address;
	private String phone;
	private String contact;
	private Integer needs;
	private String needsView;
	
	
	
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getNeeds() {
		return needs;
	}
	public void setNeeds(Integer needs) {
		this.needs = needs;
		this.needsView = needsMap.get(needs);
	}
	public String getNeedsView() {
		return needsView;
	}
	public void setNeedsView(String needsView) {
		this.needsView = needsView;
	}
	
	
}
