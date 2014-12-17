package org.erp.invoice.storeoperate.entity;

import java.util.HashMap;
import java.util.Map;

import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.invoice.store.entity.StoreModel;
import org.erp.util.FormatTime;

public class StoreOperateModel {
	
	public static final Integer STOREOPERATE_TYPE_IN = 1;
	public static final Integer STOREOPERATE_TYPE_OUT=2;
	
	public static final String STOREOPERATE_TYPE_IN_VIEW = "入库";
	public static final String STOREOPERATE_TYPE_OUT_VIEW = "出库";
	
	public static Map<Integer, String> typeMap = new HashMap<Integer, String>();
	
	static{
		typeMap.put(STOREOPERATE_TYPE_IN, STOREOPERATE_TYPE_IN_VIEW);
		typeMap.put(STOREOPERATE_TYPE_OUT, STOREOPERATE_TYPE_OUT_VIEW);
	}
	
	private Long uuid;
	private Integer num;
	
	private Long operTime;
	private Integer type;
	
	private String operTimeView;
	private String typeView;
	
	private StoreModel storeM;
	private EmployeeModel empM;
	private GoodsModel goodsM;
	private OrderDetailModel orderDetailM;
	
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getOperTime() {
		return operTime;
	}
	public void setOperTime(Long operTime) {
		this.operTime = operTime;
		this.operTimeView = FormatTime.formatTime(operTime);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public String getOperTimeView() {
		return operTimeView;
	}
	public void setOperTimeView(String operTimeView) {
		this.operTimeView = operTimeView;
	}
	public String getTypeView() {
		return typeView;
	}
	public void setTypeView(String typeView) {
		this.typeView = typeView;
	}
	public StoreModel getStoreM() {
		return storeM;
	}
	public void setStoreM(StoreModel storeM) {
		this.storeM = storeM;
	}
	public EmployeeModel getEmpM() {
		return empM;
	}
	public void setEmpM(EmployeeModel empM) {
		this.empM = empM;
	}
	public GoodsModel getGoodsM() {
		return goodsM;
	}
	public void setGoodsM(GoodsModel goodsM) {
		this.goodsM = goodsM;
	}
	public OrderDetailModel getOrderDetailM() {
		return orderDetailM;
	}
	public void setOrderDetailM(OrderDetailModel orderDetailM) {
		this.orderDetailM = orderDetailM;
	}
	
	
}
