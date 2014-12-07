package org.erp.auth.role.entity;

import java.util.HashSet;
import java.util.Set;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.resource.entity.ResourceModel;

public class RoleModel {
	private Long uuid;
	private String name;
	private String number;
	private Set<EmployeeModel> employees = new HashSet<EmployeeModel>();
	private Set<ResourceModel> resources = new HashSet<ResourceModel>();
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Set<EmployeeModel> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<EmployeeModel> employees) {
		this.employees = employees;
	}
	public Set<ResourceModel> getResources() {
		return resources;
	}
	public void setResources(Set<ResourceModel> resources) {
		this.resources = resources;
	}
	
	
}
