package com.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillsInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer billsId;
	private Integer warehouseId;
	private Integer warehouseID;
	private Integer staffId;
	private Integer staffID;
	private String billsNum;
	private String supplierName;
	private String departmentName;
	private String warehouseName;
	private String warehouseNames;
	private String staffName;
	private String staffNames;
	private Date billsEntryTime;
	private String billsEntryTimes;
	private BigDecimal billsMoney;
	private String billsRemark;
	private String rawMaterialName;
	private String rawMaterialBigName;
	private String unitName;
	private Double rawMaterialAmount;
	private BigDecimal rawMaterialPrice;
	private BigDecimal totalPrice;

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

	public Integer getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(Integer warehouseID) {
		this.warehouseID = warehouseID;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getStaffID() {
		return staffID;
	}

	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
	}

	public String getBillsNum() {
		return billsNum;
	}

	public void setBillsNum(String billsNum) {
		this.billsNum = billsNum;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseNames() {
		return warehouseNames;
	}

	public void setWarehouseNames(String warehouseNames) {
		this.warehouseNames = warehouseNames;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffNames() {
		return staffNames;
	}

	public void setStaffNames(String staffNames) {
		this.staffNames = staffNames;
	}

	public Date getBillsEntryTime() {
		return billsEntryTime;
	}

	public void setBillsEntryTime(Date billsEntryTime) {
		this.billsEntryTime = billsEntryTime;
	}

	public String getBillsEntryTimes() {
		return billsEntryTimes;
	}

	public void setBillsEntryTimes(Date billsEntryTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(billsEntryTime);
		this.billsEntryTimes = date;
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

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getRawMaterialBigName() {
		return rawMaterialBigName;
	}

	public void setRawMaterialBigName(String rawMaterialBigName) {
		this.rawMaterialBigName = rawMaterialBigName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getRawMaterialAmount() {
		return rawMaterialAmount;
	}

	public void setRawMaterialAmount(Double rawMaterialAmount) {
		this.rawMaterialAmount = rawMaterialAmount;
	}

	public BigDecimal getRawMaterialPrice() {
		return rawMaterialPrice;
	}

	public void setRawMaterialPrice(BigDecimal rawMaterialPrice) {
		this.rawMaterialPrice = rawMaterialPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
