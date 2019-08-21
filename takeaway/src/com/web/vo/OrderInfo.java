package com.web.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer purchaseOrdersId;
	private Integer dispatchingOrdersId;
	private Integer godownOrdersId;
	private Integer creditOrdersId;
	private Integer stocksRequisitionId;
	private Integer pickingCreditOrdersId;
	private Integer warehouseTransferOrderId;
	private Integer billsId;
	private Integer supplierId;
	private Integer departmentId;
	private Integer warehouseId;
	private Integer staffId;
	private Integer staffID;
	private Date billsEntryTime;
	private String billsEntryTimes;
	private String billsNum;
	private String supplierName;
	private String departmentName;
	private String warehouseName;
	private String agent;
	private String approver;
	

	public Integer getPurchaseOrdersId() {
		return purchaseOrdersId;
	}

	public void setPurchaseOrdersId(Integer purchaseOrdersId) {
		this.purchaseOrdersId = purchaseOrdersId;
	}

	public Integer getDispatchingOrdersId() {
		return dispatchingOrdersId;
	}

	public void setDispatchingOrdersId(Integer dispatchingOrdersId) {
		this.dispatchingOrdersId = dispatchingOrdersId;
	}

	public Integer getGodownOrdersId() {
		return godownOrdersId;
	}

	public void setGodownOrdersId(Integer godownOrdersId) {
		this.godownOrdersId = godownOrdersId;
	}

	public Integer getCreditOrdersId() {
		return creditOrdersId;
	}

	public void setCreditOrdersId(Integer creditOrdersId) {
		this.creditOrdersId = creditOrdersId;
	}

	public Integer getStocksRequisitionId() {
		return stocksRequisitionId;
	}

	public void setStocksRequisitionId(Integer stocksRequisitionId) {
		this.stocksRequisitionId = stocksRequisitionId;
	}

	public Integer getPickingCreditOrdersId() {
		return pickingCreditOrdersId;
	}

	public void setPickingCreditOrdersId(Integer pickingCreditOrdersId) {
		this.pickingCreditOrdersId = pickingCreditOrdersId;
	}

	public Integer getWarehouseTransferOrderId() {
		return warehouseTransferOrderId;
	}

	public void setWarehouseTransferOrderId(Integer warehouseTransferOrderId) {
		this.warehouseTransferOrderId = warehouseTransferOrderId;
	}

	public Integer getBillsId() {
		return billsId;
	}

	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
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

	public Integer getStaffID() {
		return staffID;
	}

	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
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

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

}
