package org.erp.auth.department.entity;


public class DepartmentModel 
{
	private Long uuid;
	private String name;
	private String phone;
	private String departNum;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartNum() {
		return departNum;
	}
	public void setDepartNum(String departNum) {
		this.departNum = departNum;
	}
	
	
}
