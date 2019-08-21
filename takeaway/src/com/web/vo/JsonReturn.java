package com.web.vo;

import java.io.Serializable;

public class JsonReturn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5863100019332084445L;
	private Boolean state = false;
	private String msg = "";
	private Integer sum = 0;

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
