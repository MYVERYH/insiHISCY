package com.web.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class RawMaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rawMaterialId;
	private Integer unitId;
	private Integer rawMaterialSmallId;
	private String rawMaterialNum;
	private String rawMaterialName;
	private BigDecimal rawMaterialPrice;
	private String pinyinCode;
	private String unitName;

	public Integer getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(Integer rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getRawMaterialSmallId() {
		return rawMaterialSmallId;
	}

	public void setRawMaterialSmallId(Integer rawMaterialSmallId) {
		this.rawMaterialSmallId = rawMaterialSmallId;
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

	public BigDecimal getRawMaterialPrice() {
		return rawMaterialPrice;
	}

	public void setRawMaterialPrice(BigDecimal rawMaterialPrice) {
		this.rawMaterialPrice = rawMaterialPrice;
	}

	public String getPinyinCode() {
		return pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
