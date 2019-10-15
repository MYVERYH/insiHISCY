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
	private Integer quantityRequired;

	public MaterialRequirement() {
		super();
	}

	public MaterialRequirement(Integer materialRequirementId, Integer departmentId, Integer rawMaterialId,
			Integer quantityRequired) {
		super();
		this.materialRequirementId = materialRequirementId;
		this.departmentId = departmentId;
		this.rawMaterialId = rawMaterialId;
		this.quantityRequired = quantityRequired;
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

	public Integer getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(Integer rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public Integer getQuantityRequired() {
		return quantityRequired;
	}

	public void setQuantityRequired(Integer quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

}
