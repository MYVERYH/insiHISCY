package com.web.po;

import java.io.Serializable;

public class Order implements Serializable {

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
	private Integer repertoryCheckId;
	private Integer billsId;
	private Integer supplierId;
	private Integer departmentId;
	private Integer warehouseId;
	private Integer staffId;

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

	public Integer getRepertoryCheckId() {
		return repertoryCheckId;
	}

	public void setRepertoryCheckId(Integer repertoryCheckId) {
		this.repertoryCheckId = repertoryCheckId;
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

}
