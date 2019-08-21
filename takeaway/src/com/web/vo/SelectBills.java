package com.web.vo;

import java.io.Serializable;
import java.util.Calendar;

public class SelectBills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer warehouseID = 0;
	private Integer warehouseId = 0;
	private Integer supplierId = 0;
	private Integer departmentId = 0;
	private Integer staffID = 0;
	private Integer staffId = 0;
	private Integer rawMaterialBigId = 0;
	private String billsNum = "";
	Calendar cal = Calendar.getInstance();
	private String month = cal.get(Calendar.YEAR)
			+ "-"
			+ ((cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1)
					: "0" + (cal.get(Calendar.MONTH) + 1)).toString();
	private String startTime = "2000-01-01";
	private String endTime = "2100-12-31";
	private String rawMaterialNum = "";
	private String rawMaterialName = "";
	private String rawMaterialBigName = "";

	public Integer getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(Integer warehouseID) {
		this.warehouseID = warehouseID;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getStaffID() {
		return staffID;
	}

	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getRawMaterialBigId() {
		return rawMaterialBigId;
	}

	public void setRawMaterialBigId(Integer rawMaterialBigId) {
		this.rawMaterialBigId = rawMaterialBigId;
	}

	public String getBillsNum() {
		return billsNum;
	}

	public void setBillsNum(String billsNum) {
		this.billsNum = billsNum;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRawMaterialNum() {
		return rawMaterialNum;
	}

	public void setRawMaterialNum(String rawMaterialNum) {
		this.rawMaterialNum = rawMaterialNum;
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

}
