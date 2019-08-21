package com.web.po;

import java.io.Serializable;

public class WineGreSmall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer wineGreSmallId;
	private Integer wineGreBigId;
	private String wineGreSmallNum;
	private String wineGreSmallName;

	public Integer getWineGreSmallId() {
		return wineGreSmallId;
	}

	public void setWineGreSmallId(Integer wineGreSmallId) {
		this.wineGreSmallId = wineGreSmallId;
	}

	public Integer getWineGreBigId() {
		return wineGreBigId;
	}

	public void setWineGreBigId(Integer wineGreBigId) {
		this.wineGreBigId = wineGreBigId;
	}

	public String getWineGreSmallNum() {
		return wineGreSmallNum;
	}

	public void setWineGreSmallNum(String wineGreSmallNum) {
		this.wineGreSmallNum = wineGreSmallNum;
	}

	public String getWineGreSmallName() {
		return wineGreSmallName;
	}

	public void setWineGreSmallName(String wineGreSmallName) {
		this.wineGreSmallName = wineGreSmallName;
	}

}
