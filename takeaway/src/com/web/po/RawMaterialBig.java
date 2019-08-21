package com.web.po;

import java.io.Serializable;

public class RawMaterialBig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rawMaterialBigId;
	private String rawMaterialBigNum;
	private String rawMaterialBigName;

	public Integer getRawMaterialBigId() {
		return rawMaterialBigId;
	}

	public void setRawMaterialBigId(Integer rawMaterialBigId) {
		this.rawMaterialBigId = rawMaterialBigId;
	}

	public String getRawMaterialBigNum() {
		return rawMaterialBigNum;
	}

	public void setRawMaterialBigNum(String rawMaterialBigNum) {
		this.rawMaterialBigNum = rawMaterialBigNum;
	}

	public String getRawMaterialBigName() {
		return rawMaterialBigName;
	}

	public void setRawMaterialBigName(String rawMaterialBigName) {
		this.rawMaterialBigName = rawMaterialBigName;
	}

}
