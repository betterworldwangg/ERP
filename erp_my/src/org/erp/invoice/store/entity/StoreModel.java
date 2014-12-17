package org.erp.invoice.store.entity;

import org.erp.auth.employee.entity.EmployeeModel;

public class StoreModel {
	
	private Long uuid;
	private String name;
	private String address;
	private EmployeeModel employee = new EmployeeModel();
	
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
	public EmployeeModel getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}
	
	
}
