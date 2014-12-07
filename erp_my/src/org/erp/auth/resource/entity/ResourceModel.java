package org.erp.auth.resource.entity;

import java.util.HashSet;
import java.util.Set;

import org.erp.auth.role.entity.RoleModel;

public class ResourceModel {
	private Long uuid;
	private String name;
	private Set<RoleModel> roles = new HashSet<RoleModel>();
	private String url;
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
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
