package com.web.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierPayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer supplierPaymentId;
	private Integer supplierId;
	private BigDecimal totalMoney = new BigDecimal(0);
	private Date paymentDate = new Date();

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

}
