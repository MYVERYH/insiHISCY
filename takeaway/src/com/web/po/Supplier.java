package com.web.po;

import java.io.Serializable;

public class Supplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer supplierId;
	private Integer rawMaterialBigId;
	private String supplierNum;
	private String supplierName;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getRawMaterialBigId() {
		return rawMaterialBigId;
	}

	public void setRawMaterialBigId(Integer rawMaterialBigId) {
		this.rawMaterialBigId = rawMaterialBigId;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
