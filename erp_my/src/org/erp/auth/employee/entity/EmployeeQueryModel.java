package org.erp.auth.employee.entity;
import org.erp.util.base.BaseModel;
public class EmployeeQueryModel extends EmployeeModel implements BaseModel
{
	//TODO添加查询范围字段
	private Long loginTimeFirst;
	private Long loginTimeEnd;
	public Long getLoginTimeFirst() {
		return loginTimeFirst;
	}
	public void setLoginTimeFirst(Long loginTimeFirst) {
		this.loginTimeFirst = loginTimeFirst;
	}
	public Long getLoginTimeEnd() {
		return loginTimeEnd;
	}
	public void setLoginTimeEnd(Long loginTimeEnd) {
		this.loginTimeEnd = loginTimeEnd;
	}
	
	

}