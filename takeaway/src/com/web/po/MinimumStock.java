package com.web.po;

import java.io.Serializable;

public class MinimumStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer minimumStockId;
	private Integer repertoryId;
	private Double minimumQuantity;

	public Integer getMinimumStockId() {
		return minimumStockId;
	}

	public void setMinimumStockId(Integer minimumStockId) {
		this.minimumStockId = minimumStockId;
	}

	public Integer getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(Integer repertoryId) {
		this.repertoryId = repertoryId;
	}

	public Double getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Double minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

}
