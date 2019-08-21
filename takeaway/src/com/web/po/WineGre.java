package com.web.po;

import java.io.Serializable;

public class WineGre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer wineGreId;
	private Integer wineGreSmallId;
	private String wineGreNum;
	private String wineGreName;
	private Double wineGrePrice;
	private Double bigPrice;
	private Double smallPrice;
	private Double memberPrice;	
	private Boolean isDiscount;
	private byte[] picture;

	public Integer getWineGreId() {
		return wineGreId;
	}

	public void setWineGreId(Integer wineGreId) {
		this.wineGreId = wineGreId;
	}

	public Integer getWineGreSmallId() {
		return wineGreSmallId;
	}

	public void setWineGreSmallId(Integer wineGreSmallId) {
		this.wineGreSmallId = wineGreSmallId;
	}

	public String getWineGreNum() {
		return wineGreNum;
	}

	public void setWineGreNum(String wineGreNum) {
		this.wineGreNum = wineGreNum;
	}

	public Double getWineGrePrice() {
		return wineGrePrice;
	}

	public String getWineGreName() {
		return wineGreName;
	}

	public void setWineGreName(String wineGreName) {
		this.wineGreName = wineGreName;
	}

	public void setWineGrePrice(Double wineGrePrice) {
		this.wineGrePrice = wineGrePrice;
	}

	public Double getBigPrice() {
		return bigPrice;
	}

	public void setBigPrice(Double bigPrice) {
		this.bigPrice = bigPrice;
	}

	public Double getSmallPrice() {
		return smallPrice;
	}

	public void setSmallPrice(Double smallPrice) {
		this.smallPrice = smallPrice;
	}

	public Double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public Boolean getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Boolean isDiscount) {
		this.isDiscount = isDiscount;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
}
