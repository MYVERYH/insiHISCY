package com.web.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class Repertory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer repertoryId;
	private Integer warehouseId;
	private Integer rawMaterialId;
	private Integer rawMaterialQuantity;
	private BigDecimal totalPrice;

	public Repertory() {
		super();
	}

	public Repertory(Integer repertoryId, Integer warehouseId,
			Integer rawMaterialId, Integer rawMaterialQuantity,
			BigDecimal totalPrice) {
		super();
		this.repertoryId = repertoryId;
		this.warehouseId = warehouseId;
		this.rawMaterialId = rawMaterialId;
		this.rawMaterialQuantity = rawMaterialQuantity;
		this.totalPrice = totalPrice;
	}

	public Integer getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(Integer repertoryId) {
		this.repertoryId = repertoryId;
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

	public Integer getRawMaterialQuantity() {
		return rawMaterialQuantity;
	}

	public void setRawMaterialQuantity(Integer rawMaterialQuantity) {
		this.rawMaterialQuantity = rawMaterialQuantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
