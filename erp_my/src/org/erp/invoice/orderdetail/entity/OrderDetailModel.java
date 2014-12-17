package org.erp.invoice.orderdetail.entity;

import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.order.entity.OrderModel;
import org.erp.util.FormatNumber;

public class OrderDetailModel {
	
	private Long uuid;
	
	private Double price;
	private Integer num;
	private String priceView;
	private String totalPriceView;
	//剩余数量
	private Integer surplus;
	
	
	
	private OrderModel orderM;
	private GoodsModel goodsM;
	
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		this.priceView = FormatNumber.format(price);
	
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
		this.totalPriceView = FormatNumber.format(num*price);
	}
	public String getPriceView() {
		return priceView;
	}
	public void setPriceView(String priceView) {
		this.priceView = priceView;
	}
	public OrderModel getOrderM() {
		return orderM;
	}
	public void setOrderM(OrderModel orderM) {
		this.orderM = orderM;
	}
	public GoodsModel getGoodsM() {
		return goodsM;
	}
	public void setGoodsM(GoodsModel goodsM) {
		this.goodsM = goodsM;
	}
	public String getTotalPriceView() {
		return totalPriceView;
	}
	public void setTotalPriceView(String totalPriceView) {
		this.totalPriceView = totalPriceView;
	}
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	
	
}
