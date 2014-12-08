package org.erp.auth.menu.entity;

import java.util.HashSet;
import java.util.Set;

import org.erp.auth.role.entity.RoleModel;

public class MenuModel {
	
	public static final Long SYSTEM_MENU_UUID = 1L;
	
	private Long uuid;
	private String name;
	private String url;
	private MenuModel parent;
	private Set<MenuModel> submenus = new HashSet<MenuModel>();
	private Set<RoleModel> roles = new HashSet<RoleModel>();
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MenuModel getParent() {
		return parent;
	}
	public void setParent(MenuModel parent) {
		this.parent = parent;
	}
	public Set<MenuModel> getSubmenus() {
		return submenus;
	}
	public void setSubmenus(Set<MenuModel> submenus) {
		this.submenus = submenus;
	}
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	
	
}
