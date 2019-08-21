package com.web.po;

import java.io.Serializable;

public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer unitId;
	private String unitName;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
