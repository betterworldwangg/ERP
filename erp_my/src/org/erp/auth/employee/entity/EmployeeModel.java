package org.erp.auth.employee.entity;

import java.text.Format;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.role.entity.RoleModel;
import org.erp.util.FormatTime;

public class EmployeeModel 
{
	public static final String EMP_MAN_VIEW="男";
	public static final String EMP_WOMEN_VIEW="女";
			
	public static final Integer EMP_MAN=0;
	public static final Integer EMP_WOMEN=1;
	
	public static final Map<Integer,String> sexMap = new HashMap<Integer, String>();
	
	static{
		sexMap.put(EMP_MAN, EMP_MAN_VIEW);
		sexMap.put(EMP_WOMEN, EMP_WOMEN_VIEW);
	}
			
			
	private Long uuid;
	//用户名
	private String userName;
	private String userPass;
	//真实名
	private String name;
	private Integer sex;
	private Long birthday;
	private String phone;
	private String email;
	private String addres;
	//最后登录时间
	private Long lastLoginTime;
	//最后登录IP地址
	private String lastLoginIp;
	//登录总次数
	private Integer loginTimes;
	
	//视图值定义
	private String sexView;
	private String birthDayView;
	private String lastLoginTimeView;
	private String resourceView;
	//关联关系
	private DepartmentModel departM;
	private Set<RoleModel> roles = new HashSet<RoleModel>();
	
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
		this.sexView = sexMap.get(sex);
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthDayView = FormatTime.formatTime(birthday);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		//this.lastLoginTimeView = FormatTime.formatTime(lastLoginTime);
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public String getSexView() {
		return sexView;
	}
	public void setSexView(String sexView) {
		this.sexView = sexView;
	}
	public String getBirthDayView() {
		return birthDayView;
	}
	public void setBirthDayView(String birthDayView) {
		this.birthDayView = birthDayView;
	}
	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}
	public void setLastLoginTimeView(String lastLoginTimeView) {
		this.lastLoginTimeView = lastLoginTimeView;
	}
	public DepartmentModel getDepartM() {
		return departM;
	}
	public void setDepartM(DepartmentModel departM) {
		this.departM = departM;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public String getResourceView() {
		return resourceView;
	}
	public void setResourceView(String resourceView) {
		this.resourceView = resourceView;
	}
	
	
	
	
}
