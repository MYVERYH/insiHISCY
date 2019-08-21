package com.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class IndentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer indentId;
	private Integer userId;
	private Integer orderStatusId;
	private Integer wineGreId;
	private String indentNum;
	private Timestamp indentTime;
	private String indentTimes;
	private String indentRemark;
	private String wineGreBigName;
	private String wineGreName;
	private BigDecimal wineGrePrice;
	private BigDecimal totalPice;
	private String orderStatus;
	private Double indentQuantity;
	private String userAddress;
	private String contactNumber;
	private String paymentMethod;

	public Integer getIndentId() {
		return indentId;
	}

	public void setIndentId(Integer indentId) {
		this.indentId = indentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Integer getWineGreId() {
		return wineGreId;
	}

	public void setWineGreId(Integer wineGreId) {
		this.wineGreId = wineGreId;
	}

	public String getIndentNum() {
		return indentNum;
	}

	public void setIndentNum(String indentNum) {
		this.indentNum = indentNum;
	}

	public Timestamp getIndentTime() {
		return indentTime;
	}

	public void setIndentTime(Timestamp indentTime) {
		this.indentTime = indentTime;
	}

	public String getIndentTimes() {
		return indentTimes;
	}

	public void setIndentTimes(Timestamp indentTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(indentTime);
		this.indentTimes = date;
	}

	public String getIndentRemark() {
		return indentRemark;
	}

	public void setIndentRemark(String indentRemark) {
		this.indentRemark = indentRemark;
	}

	public String getWineGreBigName() {
		return wineGreBigName;
	}

	public void setWineGreBigName(String wineGreBigName) {
		this.wineGreBigName = wineGreBigName;
	}

	public String getWineGreName() {
		return wineGreName;
	}

	public void setWineGreName(String wineGreName) {
		this.wineGreName = wineGreName;
	}

	public BigDecimal getWineGrePrice() {
		return wineGrePrice;
	}

	public BigDecimal getTotalPice() {
		return totalPice;
	}

	public void setTotalPice(BigDecimal totalPice) {
		this.totalPice = totalPice;
	}

	public void setWineGrePrice(BigDecimal wineGrePrice) {
		this.wineGrePrice = wineGrePrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getIndentQuantity() {
		return indentQuantity;
	}

	public void setIndentQuantity(Double indentQuantity) {
		this.indentQuantity = indentQuantity;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
