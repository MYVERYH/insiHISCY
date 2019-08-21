package com.web.po;

import java.io.Serializable;

public class Check implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer checkId;
	private Integer warehouseId;
	private Integer rawMaterialId;
	private Double inventoryCount;

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(Integer rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public Double getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(Double inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

}
