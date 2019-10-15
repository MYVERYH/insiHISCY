package com.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RawMaterialInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rawMaterialId;
	private Integer unitId;
	private Integer checkId;
	private Integer materialRequirementId;
	private Integer departmentId;
	private Integer warehouseId;
	private Integer rawMaterialSmallId;
	private Integer rawMaterialBigId;
	private Integer repertoryId;
	private String rawMaterialNum;
	private String rawMaterialName;
	private String warehouseName;
	private String departmentName;
	private BigDecimal rawMaterialPrice;
	private String pinyinCode;
	private String unitName;
	private String rawMaterialBigName;
	private Integer rawMaterialAmount;
	private BigDecimal totalPrice;
	private Integer reportAmount;
	private Integer rawMaterialQuantity;
	private Integer quantityRequired;
	private Integer inventoryCount;
	private Integer numberOfProfitAndLoss;
	private BigDecimal stockAmount;
	private BigDecimal checkAmount;
	private BigDecimal profitAndLossAmount;
	private String remark;
	private Integer minimumQuantity;
	private Integer oldAmount;
	private String titleInfo = "";

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

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getMaterialRequirementId() {
		return materialRequirementId;
	}

	public void setMaterialRequirementId(Integer materialRequirementId) {
		this.materialRequirementId = materialRequirementId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getRawMaterialSmallId() {
		return rawMaterialSmallId;
	}

	public void setRawMaterialSmallId(Integer rawMaterialSmallId) {
		this.rawMaterialSmallId = rawMaterialSmallId;
	}

	public Integer getRawMaterialBigId() {
		return rawMaterialBigId;
	}

	public void setRawMaterialBigId(Integer rawMaterialBigId) {
		this.rawMaterialBigId = rawMaterialBigId;
	}

	public Integer getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(Integer repertoryId) {
		this.repertoryId = repertoryId;
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

	public String getRawMaterialBigName() {
		return rawMaterialBigName;
	}

	public void setRawMaterialBigName(String rawMaterialBigName) {
		this.rawMaterialBigName = rawMaterialBigName;
	}

	public Integer getRawMaterialAmount() {
		return rawMaterialAmount;
	}

	public void setRawMaterialAmount(Integer rawMaterialAmount) {
		this.rawMaterialAmount = rawMaterialAmount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getReportAmount() {
		return reportAmount;
	}

	public void setReportAmount(Integer reportAmount) {
		this.reportAmount = reportAmount;
	}

	public Integer getRawMaterialQuantity() {
		return rawMaterialQuantity;
	}

	public void setRawMaterialQuantity(Integer rawMaterialQuantity) {
		this.rawMaterialQuantity = rawMaterialQuantity;
	}

	public Integer getQuantityRequired() {
		return quantityRequired;
	}

	public void setQuantityRequired(Integer quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	public Integer getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(Integer inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public Integer getNumberOfProfitAndLoss() {
		return numberOfProfitAndLoss;
	}

	public void setNumberOfProfitAndLoss(Integer numberOfProfitAndLoss) {
		this.numberOfProfitAndLoss = numberOfProfitAndLoss;
	}

	public BigDecimal getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(BigDecimal rawMaterialPrice, Integer reportAmount) {
		BigDecimal bigDecimal = new BigDecimal(reportAmount);
		this.stockAmount = bigDecimal.multiply(rawMaterialPrice);
	}

	public BigDecimal getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(BigDecimal rawMaterialPrice,
			Integer inventoryCount) {
		BigDecimal bigDecimal = new BigDecimal(inventoryCount);
		this.checkAmount = bigDecimal.multiply(rawMaterialPrice);
	}

	public BigDecimal getProfitAndLossAmount() {
		return profitAndLossAmount;
	}

	public void setProfitAndLossAmount(BigDecimal rawMaterialPrice,
			Integer numberOfProfitAndLoss) {
		BigDecimal bigDecimal = new BigDecimal(numberOfProfitAndLoss);
		this.profitAndLossAmount = bigDecimal.multiply(rawMaterialPrice);
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

	public Integer getOldAmount() {
		return oldAmount;
	}

	public void setOldAmount(Integer oldAmount) {
		this.oldAmount = oldAmount;
	}

	public String getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(Integer minimumQuantity, Integer rawMaterialQuantity) {
		String msg = "";
		if (minimumQuantity > rawMaterialQuantity) {
			msg = "库存报警";
		}
		this.titleInfo = msg;
	}

}
