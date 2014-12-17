package org.erp.invoice.storedetail.entity;

import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.store.entity.StoreModel;

public class StoreDetailModel {
	private Long uuid;
	private Integer num;
	private StoreModel storeM;
	private GoodsModel goodsM;
	
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
	public StoreModel getStoreM() {
		return storeM;
	}
	public void setStoreM(StoreModel storeM) {
		this.storeM = storeM;
	}
	public GoodsModel getGoodsM() {
		return goodsM;
	}
	public void setGoodsM(GoodsModel goodsM) {
		this.goodsM = goodsM;
	}
	
	

}
