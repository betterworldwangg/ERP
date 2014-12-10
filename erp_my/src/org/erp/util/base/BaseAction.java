package org.erp.util.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.erp.auth.department.service.service.DepartmentService;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.employee.service.service.EmployeeService;
import org.erp.auth.menu.service.service.MenuService;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.resource.service.service.ResourceService;
import org.erp.auth.role.service.service.RoleService;
import org.erp.invoice.goods.service.service.GoodsService;
import org.erp.invoice.goodstype.service.service.GoodsTypeService;
import org.erp.invoice.supplier.service.service.SupplierService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction<T> extends ActionSupport{
	
	public static final String LIST="list";
	public static final String TO_LIST="toList";
	public static final String INPUTUI="inputUI";
	
	public int currPage = 1;
	public int pageSize = 8;
	public int totalPage;
	public int rows;
	protected EmployeeService employeeServ;
	protected DepartmentService departmentServ;
	protected RoleService roleServ;
	protected ResourceService resourceServ;
	protected MenuService menuServ;
	protected SupplierService supplierServ;
	protected GoodsTypeService goodsTypeServ;
	protected GoodsService goodsServ;
	public List<T> list = new ArrayList<T>();
	public T model;
	public BaseAction() {
		// 获得父类（BaseAction）类型
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 获得父类上的泛型数组
		Type[] typeArguments = type.getActualTypeArguments();
		// 实体的类型
		Class<T> clazz = (Class<T>) typeArguments[0];
		try {
			// 根据反射创建对象
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public void putSession(String name , Object obj)
	{
		ActionContext.getContext().getSession().put(name,obj);
	}
	public void put(String name , Object obj)
	{
		ActionContext.getContext().put(name, obj);
	}
	public Object getSession(String name)
	{
		return ActionContext.getContext().getSession().get(name);
	}
	public EmployeeModel getLoginUser()
	{
		return (EmployeeModel) getSession("userName");
	}
	public String getActionName(){
		String allName = getClass().getSimpleName();
		String name = allName.substring(0, allName.length()-6);
		String first = name.substring(0, 1).toLowerCase();
		String finalStr = first + name.substring(1);
		return finalStr;
	}
	public EmployeeService getEmployeeServ() {
		return employeeServ;
	}
	public void setEmployeeServ(EmployeeService employeeServ) {
		this.employeeServ = employeeServ;
	}
	public DepartmentService getDepartmentServ() {
		return departmentServ;
	}
	public void setDepartmentServ(DepartmentService departmentServ) {
		this.departmentServ = departmentServ;
	}
	public RoleService getRoleServ() {
		return roleServ;
	}
	public void setRoleServ(RoleService roleServ) {
		this.roleServ = roleServ;
	}
	public ResourceService getResourceServ() {
		return resourceServ;
	}
	public void setResourceServ(ResourceService resourceServ) {
		this.resourceServ = resourceServ;
	}
	public HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}
	public HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}
	public MenuService getMenuServ() {
		return menuServ;
	}
	public void setMenuServ(MenuService menuServ) {
		this.menuServ = menuServ;
	}
	public SupplierService getSupplierServ() {
		return supplierServ;
	}
	public void setSupplierServ(SupplierService supplierServ) {
		this.supplierServ = supplierServ;
	}
	public GoodsTypeService getGoodsTypeServ() {
		return goodsTypeServ;
	}
	public void setGoodsTypeServ(GoodsTypeService goodsTypeServ) {
		this.goodsTypeServ = goodsTypeServ;
	}
	public GoodsService getGoodsServ() {
		return goodsServ;
	}
	public void setGoodsServ(GoodsService goodsServ) {
		this.goodsServ = goodsServ;
	}
	
}
