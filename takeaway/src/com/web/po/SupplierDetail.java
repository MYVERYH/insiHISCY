package com.web.po;

import java.io.Serializable;

public class SupplierDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer supplierDetailId;
	private Integer supplierId;
	private String supplierPrincipal;
	private String supplierLinkman;
	private String supplierPhone;
	private String supplierTell;
	private String supplierSite;
	private String supplierMail;
	private String supplierRemark;

	public Integer getSupplierDetailId() {
		return supplierDetailId;
	}

	public void setSupplierDetailId(Integer supplierDetailId) {
		this.supplierDetailId = supplierDetailId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
