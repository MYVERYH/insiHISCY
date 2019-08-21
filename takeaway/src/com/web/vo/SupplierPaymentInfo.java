package com.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SupplierPaymentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer supplierPaymentId;
	private Integer supplierId;
	private String rawMaterialBigName;
	private String supplierName;
	private BigDecimal totalMoney;
	private Date paymentDate;
	private String paymentDates;

	public Integer getSupplierPaymentId() {
		return supplierPaymentId;
	}

	public void setSupplierPaymentId(Integer supplierPaymentId) {
		this.supplierPaymentId = supplierPaymentId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getRawMaterialBigName() {
		return rawMaterialBigName;
	}

	public void setRawMaterialBigName(String rawMaterialBigName) {
		this.rawMaterialBigName = rawMaterialBigName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentDates() {
		return paymentDates;
	}

	public void setPaymentDates(Date paymentDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(paymentDate);
		this.paymentDates = date;
	}

}
