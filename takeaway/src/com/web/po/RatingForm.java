package com.web.po;

import java.io.Serializable;

public class RatingForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer ratingFormID;
	private Integer indentID;
	private Integer commonDetailID;
	private Double totalRating;
	private String songCanSuDu;
	private String zongFeel;

	public Integer getRatingFormID() {
		return ratingFormID;
	}

	public void setRatingFormID(Integer ratingFormID) {
		this.ratingFormID = ratingFormID;
	}

	public Integer getIndentID() {
		return indentID;
	}

	public void setIndentID(Integer indentID) {
		this.indentID = indentID;
	}

	public Integer getCommonDetailID() {
		return commonDetailID;
	}

	public void setCommonDetailID(Integer commonDetailID) {
		this.commonDetailID = commonDetailID;
	}

	public Double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(Double totalRating) {
		this.totalRating = totalRating;
	}

	public String getSongCanSuDu() {
		return songCanSuDu;
	}

	public void setSongCanSuDu(String songCanSuDu) {
		this.songCanSuDu = songCanSuDu;
	}

	public String getZongFeel() {
		return zongFeel;
	}

	public void setZongFeel(String zongFeel) {
		this.zongFeel = zongFeel;
	}

}
