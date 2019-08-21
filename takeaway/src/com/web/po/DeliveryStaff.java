package com.web.po;

import java.io.Serializable;

public class DeliveryStaff implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer deliveryStaffID;
	private Integer indentId;
	private String deliveryStaffNum;
	private String deliveryStaffName;
	private Boolean deliveryState;

	public Integer getDeliveryStaffID() {
		return deliveryStaffID;
	}

	public void setDeliveryStaffID(Integer deliveryStaffID) {
		this.deliveryStaffID = deliveryStaffID;
	}

	public Integer getIndentId() {
		return indentId;
	}

	public void setIndentId(Integer indentId) {
		this.indentId = indentId;
	}

	public String getDeliveryStaffNum() {
		return deliveryStaffNum;
	}

	public void setDeliveryStaffNum(String deliveryStaffNum){
		this.deliveryStaffNum = deliveryStaffNum;
	}

	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}

	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}

	public Boolean getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(Boolean deliveryState) {
		this.deliveryState = deliveryState;
	}

}
