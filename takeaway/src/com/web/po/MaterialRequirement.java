package com.web.po;

import java.io.Serializable;

public class MaterialRequirement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer materialRequirementId;
	private Integer departmentId;
	private Integer rawMaterialId;
	private Double quantityRequired;

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

	public Integer getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(Integer rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public Double getQuantityRequired() {
		return quantityRequired;
	}

	public void setQuantityRequired(Double quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

}
