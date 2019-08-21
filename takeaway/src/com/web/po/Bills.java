package com.web.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Bills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer billsId;
	private Integer warehouseId;
	private Integer staffId;
	private Date billsEntryTime;
	private String billsNum;
	private BigDecimal billsMoney = new BigDecimal(0);
	private String billsRemark;

	public Integer getBillsId() {
		return billsId;
	}

	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Date getBillsEntryTime() {
		return billsEntryTime;
	}

	public void setBillsEntryTime(Date billsEntryTime) {
		this.billsEntryTime = billsEntryTime;
	}

	public String getBillsNum() {
		return billsNum;
	}

	public void setBillsNum(String billsNum) {
		this.billsNum = billsNum;
	}

	public BigDecimal getBillsMoney() {
		return billsMoney;
	}

	public void setBillsMoney(BigDecimal billsMoney) {
		this.billsMoney = billsMoney;
	}

	public String getBillsRemark() {
		return billsRemark;
	}

	public void setBillsRemark(String billsRemark) {
		this.billsRemark = billsRemark;
	}

}
