package org.erp.invoice.goods.entity;
import org.erp.util.base.BaseModel;
public class GoodsQueryModel extends GoodsModel implements BaseModel
{
	private Double inPriceFirst;
	private Double inPriceLast;
	private Double outPriceFirst;
	private Double outPriceLast;
	public Double getInPriceFirst() {
		return inPriceFirst;
	}
	public void setInPriceFirst(Double inPriceFirst) {
		this.inPriceFirst = inPriceFirst;
	}
	public Double getInPriceLast() {
		return inPriceLast;
	}
	public void setInPriceLast(Double inPriceLast) {
		this.inPriceLast = inPriceLast;
	}
	public Double getOutPriceFirst() {
		return outPriceFirst;
	}
	public void setOutPriceFirst(Double outPriceFirst) {
		this.outPriceFirst = outPriceFirst;
	}
	public Double getOutPriceLast() {
		return outPriceLast;
	}
	public void setOutPriceLast(Double outPriceLast) {
		this.outPriceLast = outPriceLast;
	}
	
	
}