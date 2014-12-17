package org.erp.invoice.order.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.FormatNumber;
import org.erp.util.FormatTime;

public class OrderModel 
{
	//订单类型，采购、销售、采购退货、销售退货
	public static final Integer ORDER_ORDERTYPE_BUY=1;
	public static final Integer ORDER_ORDERTYPE_SALE=2;
	public static final Integer ORDER_ORDERTYPE_RETURN_BUY=3;
	public static final Integer ORDER_ORDERTYPE_RETURN_SALE=4;
	
	public static final String ORDER_ORDERTYPE_BUY_VIEW="采购";
	public static final String ORDER_ORDERTYPE_SALE_VIEW = "销售";
	public static final String ORDER_ORDERTYPE_RETURN_BUY_VIEW="采购退货";
	public static final String ORDER_ORDERTYPE_RETURN_SALE_VIEW = "销售退货";
	
	//采购订单状态，未审核、通过、驳回、采购中、入库中、结单
	public static final Integer ORDER_TYPE_BUY_NOCHECK = 111;
	public static final Integer ORDER_TYPE_BUY_CHECK_PASS= 121;
	public static final Integer ORDER_TYPE_BUY_CHECK_NOPASS= 131;
	public static final Integer ORDER_TYPE_BUY_BUYING= 141;
	public static final Integer ORDER_TYPE_BUY_INSTORE= 222;
	public static final Integer ORDER_TYPE_BUY_END = 232;
	
	public static final String ORDER_TYPE_BUY_NOCHECK_VIEW = "未审核";
	public static final String ORDER_TYPE_BUY_CHECK_PASS_VIEW = "通过";
	public static final String ORDER_TYPE_BUY_CHECK_NOPASS_VIEW = "驳回";
	public static final String ORDER_TYPE_BUY_BUYING_VIEW = "采购中";
	public static final String ORDER_TYPE_BUY_INSTORE_VIEW = "入库中";
	public static final String ORDER_TYPE_BUY_END_VIEW = "结单";
	
	//销售订单状态
	public static final Integer ORDER_TYPE_SALE_NO_CHECK = 243;
	public static final Integer ORDER_TYPE_SALE_CHECK_NOPASS=324;
	
	public static final String ORDER_TYPE_SALE_NO_CHECK_VIEW = "未审核";
	public static final String ORDER_TYPE_SALE_CHECK_NOPASS_VIEW = "驳回";
	
	//装各种状态的map集合
	public static Map< Integer, String> orderTypeMap = new TreeMap<Integer, String>();
	private static Map<Integer, String> typeMap = new TreeMap<Integer, String>();
	public static Map<Integer, String> buyTypeMap = new TreeMap<Integer, String>();
	public static Map<Integer, String> saleTypeMap = new TreeMap<Integer, String>();
	
	static{
		orderTypeMap.put(ORDER_ORDERTYPE_BUY, ORDER_ORDERTYPE_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_SALE, ORDER_ORDERTYPE_SALE_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_RETURN_BUY, ORDER_ORDERTYPE_RETURN_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_RETURN_SALE, ORDER_ORDERTYPE_RETURN_SALE_VIEW);
		
		buyTypeMap.put(ORDER_TYPE_BUY_NOCHECK, ORDER_TYPE_BUY_NOCHECK_VIEW);
		buyTypeMap.put(ORDER_TYPE_BUY_CHECK_PASS, ORDER_TYPE_BUY_CHECK_PASS_VIEW);
		buyTypeMap.put(ORDER_TYPE_BUY_CHECK_NOPASS, ORDER_TYPE_BUY_CHECK_NOPASS_VIEW);
		buyTypeMap.put(ORDER_TYPE_BUY_BUYING, ORDER_TYPE_BUY_BUYING_VIEW);
		buyTypeMap.put(ORDER_TYPE_BUY_INSTORE, ORDER_TYPE_BUY_INSTORE_VIEW);
		buyTypeMap.put(ORDER_TYPE_BUY_END, ORDER_TYPE_BUY_END_VIEW);
		
		saleTypeMap.put(ORDER_TYPE_SALE_NO_CHECK, ORDER_TYPE_SALE_NO_CHECK_VIEW);
		saleTypeMap.put(ORDER_TYPE_SALE_CHECK_NOPASS, ORDER_TYPE_SALE_CHECK_NOPASS_VIEW);
		
		typeMap.putAll(buyTypeMap);
		typeMap.putAll(saleTypeMap);
	}
	
	private Long uuid;
	private String orderNum;
	private Integer totalNum;
	
	private Double totalPrice;
	
	private Long createTime;
	private Long checkTime;
	private Long endTime;
	
	private Integer orderType;
	private Integer type;
	
	private String totalPriceView;
	private String createTimeView;
	private String checkTimeView;
	private String endTimeView;
	
	private String orderTypeView;
	private String typeView;
	
	private EmployeeModel checker;
	private EmployeeModel creator;
	private EmployeeModel completor;
	private SupplierModel supplier;
	
	private Set<OrderDetailModel> orderDetails = new HashSet<OrderDetailModel>();
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		this.totalPriceView = FormatNumber.format(totalPrice);
		
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		this.createTimeView = FormatTime.formatTime(createTime);
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
		this.checkTimeView = FormatTime.formatTime(checkTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView = FormatTime.formatTime(endTime);
	}
	public EmployeeModel getChecker() {
		return checker;
	}
	public void setChecker(EmployeeModel checker) {
		this.checker = checker;
	}
	public EmployeeModel getCreator() {
		return creator;
	}
	public void setCreator(EmployeeModel creator) {
		this.creator = creator;
	}
	public EmployeeModel getCompletor() {
		return completor;
	}
	public void setCompletor(EmployeeModel completor) {
		this.completor = completor;
	}
	public SupplierModel getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
		this.orderTypeView = orderTypeMap.get(orderType);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public String getTotalPriceView() {
		return totalPriceView;
	}
	public void setTotalPriceView(String totalPriceView) {
		this.totalPriceView = totalPriceView;
	}
	public String getCreateTimeView() {
		return createTimeView;
	}
	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}
	public String getCheckTimeView() {
		return checkTimeView;
	}
	public void setCheckTimeView(String checkTimeView) {
		this.checkTimeView = checkTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
	public void setEndTimeView(String endTimeView) {
		this.endTimeView = endTimeView;
	}
	public String getOrderTypeView() {
		return orderTypeView;
	}
	public void setOrderTypeView(String orderTypeView) {
		this.orderTypeView = orderTypeView;
	}
	public String getTypeView() {
		return typeView;
	}
	public void setTypeView(String typeView) {
		this.typeView = typeView;
	}
	public Set<OrderDetailModel> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetailModel> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	 
}
