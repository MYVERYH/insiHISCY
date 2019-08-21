package com.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RepertoryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rawMaterialId;
	private Integer unitId;
	private String rawMaterialNum;
	private String rawMaterialName;
	private String unitName;
	private Double preRawMaterialQuantity;
	private Double godownQuantity;
	private Double goOutQuantity;
	private Double adjustQuantity;
	private Double rawMaterialQuantity;
	private Double llQuantity;
	private Double ltQuantity;
	private Double dcQuantity;
	private Double drQuantity;
	private BigDecimal rawMaterialPrice;
	private BigDecimal profit;

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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getPreRawMaterialQuantity() {
		return preRawMaterialQuantity;
	}

	public void setPreRawMaterialQuantity(Double preRawMaterialQuantity) {
		this.preRawMaterialQuantity = preRawMaterialQuantity;
	}

	public Double getGodownQuantity() {
		return godownQuantity;
	}

	public void setGodownQuantity(Double godownQuantity) {
		this.godownQuantity = godownQuantity;
	}

	public Double getGoOutQuantity() {
		return goOutQuantity;
	}

	public void setGoOutQuantity(Double goOutQuantity) {
		this.goOutQuantity = goOutQuantity;
	}

	public Double getAdjustQuantity() {
		return adjustQuantity;
	}

	public void setAdjustQuantity(Double adjustQuantity) {
		this.adjustQuantity = adjustQuantity;
	}

	public Double getRawMaterialQuantity() {
		return rawMaterialQuantity;
	}

	public void setRawMaterialQuantity(Double rawMaterialQuantity) {
		this.rawMaterialQuantity = rawMaterialQuantity;
	}

	public Double getLlQuantity() {
		return llQuantity;
	}

	public void setLlQuantity(Double llQuantity) {
		this.llQuantity = llQuantity;
	}

	public Double getLtQuantity() {
		return ltQuantity;
	}

	public void setLtQuantity(Double ltQuantity) {
		this.ltQuantity = ltQuantity;
	}

	public Double getDcQuantity() {
		return dcQuantity;
	}

	public void setDcQuantity(Double dcQuantity) {
		this.dcQuantity = dcQuantity;
	}

	public Double getDrQuantity() {
		return drQuantity;
	}

	public void setDrQuantity(Double drQuantity) {
		this.drQuantity = drQuantity;
	}

	public BigDecimal getRawMaterialPrice() {
		return rawMaterialPrice;
	}

	public void setRawMaterialPrice(BigDecimal rawMaterialPrice) {
		this.rawMaterialPrice = rawMaterialPrice;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

}
