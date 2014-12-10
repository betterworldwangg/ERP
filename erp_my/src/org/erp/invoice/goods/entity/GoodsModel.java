package org.erp.invoice.goods.entity;

import org.erp.invoice.goodstype.entity.GoodsTypeModel;

public class GoodsModel {
	
	 private Long uuid; 
	 private String name;
	 private String origin;
	 private String producer;
	 private String unit;
	 private Double inPrice;
	 private Double outPrice;
	 private GoodsTypeModel goodTypeMode;
	 
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Double getInPrice() {
		return inPrice;
	}
	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}
	public Double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}
	public GoodsTypeModel getGoodTypeMode() {
		return goodTypeMode;
	}
	public void setGoodTypeMode(GoodsTypeModel goodTypeMode) {
		this.goodTypeMode = goodTypeMode;
	}
	
}
