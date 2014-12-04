package org.erp.auth.employee.entity;
public class EmployeeModel 
{
	
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
	//关联关系
	//private DepartModel departM;
	
	
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
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
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
//	public DepartModel getDepartM() {
//		return departM;
//	}
//	public void setDepartM(DepartModel departM) {
//		this.departM = departM;
//	}
	
	
	
}
