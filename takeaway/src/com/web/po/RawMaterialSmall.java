package com.web.po;

import java.io.Serializable;

public class RawMaterialSmall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rawMaterialSmallId;
	private Integer rawMaterialBigId;
	private String rawMaterialSmallNum;
	private String rawMaterialSmallName;

	public Integer getRawMaterialSmallId() {
		return rawMaterialSmallId;
	}

	public void setRawMaterialSmallId(Integer rawMaterialSmallId) {
		this.rawMaterialSmallId = rawMaterialSmallId;
	}

	public Integer getRawMaterialBigId() {
		return rawMaterialBigId;
	}

	public void setRawMaterialBigId(Integer rawMaterialBigId) {
		this.rawMaterialBigId = rawMaterialBigId;
	}

	public String getRawMaterialSmallNum() {
		return rawMaterialSmallNum;
	}

	public void setRawMaterialSmallNum(String rawMaterialSmallNum) {
		this.rawMaterialSmallNum = rawMaterialSmallNum;
	}

	public String getRawMaterialSmallName() {
		return rawMaterialSmallName;
	}

	public void setRawMaterialSmallName(String rawMaterialSmallName) {
		this.rawMaterialSmallName = rawMaterialSmallName;
	}

}
