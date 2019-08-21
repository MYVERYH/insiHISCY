package com.web.vo;

import java.io.Serializable;

public class SupplierInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer supplierId;
	private Integer rawMaterialBigId;
	private Integer supplierDetailId;
	private String rawMaterialBigName;
	private String supplierNum;
	private String supplierName;	
	private String supplierPrincipal;
	private String supplierLinkman;
	private String supplierPhone;
	private String supplierTell;
	private String supplierSite;
	private String supplierMail;
	private String supplierRemark;

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

	public String getRawMaterialBigName() {
		return rawMaterialBigName;
	}

	public void setRawMaterialBigName(String rawMaterialBigName) {
		this.rawMaterialBigName = rawMaterialBigName;
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

	public Integer getSupplierDetailId() {
		return supplierDetailId;
	}

	public void setSupplierDetailId(Integer supplierDetailId) {
		this.supplierDetailId = supplierDetailId;
	}

	public String getSupplierPrincipal() {
		return supplierPrincipal;
	}

	public void setSupplierPrincipal(String supplierPrincipal) {
		this.supplierPrincipal = supplierPrincipal;
	}

	public String getSupplierLinkman() {
		return supplierLinkman;
	}

	public void setSupplierLinkman(String supplierLinkman) {
		this.supplierLinkman = supplierLinkman;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierTell() {
		return supplierTell;
	}

	public void setSupplierTell(String supplierTell) {
		this.supplierTell = supplierTell;
	}

	public String getSupplierSite() {
		return supplierSite;
	}

	public void setSupplierSite(String supplierSite) {
		this.supplierSite = supplierSite;
	}

	public String getSupplierMail() {
		return supplierMail;
	}

	public void setSupplierMail(String supplierMail) {
		this.supplierMail = supplierMail;
	}

	public String getSupplierRemark() {
		return supplierRemark;
	}

	public void setSupplierRemark(String supplierRemark) {
		this.supplierRemark = supplierRemark;
	}

}
