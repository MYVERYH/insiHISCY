package com.web.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class BillsDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer billsDetailId;
	private Integer billsId;
	private Integer rawMaterialId;
	private Integer rawMaterialAmount;
	private String remark = "";
	private BigDecimal totalPrice;

	public Integer getBillsDetailId() {
		return billsDetailId;
	}

	public void setBillsDetailId(Integer billsDetailId) {
		this.billsDetailId = billsDetailId;
	}

	public Integer getBillsId() {
		return billsId;
	}

	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
	}

	public Integer getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(Integer rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public Integer getRawMaterialAmount() {
		return rawMaterialAmount;
	}

	public void setRawMaterialAmount(Integer rawMaterialAmount) {
		this.rawMaterialAmount = rawMaterialAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
