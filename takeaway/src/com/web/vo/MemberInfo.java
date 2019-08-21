package com.web.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer memberID;
	private Integer papersTypeID;
	private Integer clubCardID;
	private Integer memberTypeID;
	private String memberMC;
	private String papersMarkMemb;
	private String birthdayMember;
	private String phoneMember;
	private String unitsMember;
	private String siteMember;
	private String markClub;
	private String passwordClub;
	private String balanceClub;
	private String integralClub;
	private Date delivertimeClub;
	private String delivertimeClubs;
	private String papersTypeMC;
	private String memberTypeMC;
	private Boolean cardClub = false;

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getPapersTypeID() {
		return papersTypeID;
	}

	public void setPapersTypeID(Integer papersTypeID) {
		this.papersTypeID = papersTypeID;
	}

	public Integer getClubCardID() {
		return clubCardID;
	}

	public void setClubCardID(Integer clubCardID) {
		this.clubCardID = clubCardID;
	}

	public Integer getMemberTypeID() {
		return memberTypeID;
	}

	public void setMemberTypeID(Integer memberTypeID) {
		this.memberTypeID = memberTypeID;
	}

	public String getMemberMC() {
		return memberMC;
	}

	public void setMemberMC(String memberMC) {
		this.memberMC = memberMC;
	}

	public String getPapersMarkMemb() {
		return papersMarkMemb;
	}

	public void setPapersMarkMemb(String papersMarkMemb) {
		this.papersMarkMemb = papersMarkMemb;
	}

	public String getBirthdayMember() {
		return birthdayMember;
	}

	public void setBirthdayMember(String birthdayMember) {
		this.birthdayMember = birthdayMember;
	}

	public String getPhoneMember() {
		return phoneMember;
	}

	public void setPhoneMember(String phoneMember) {
		this.phoneMember = phoneMember;
	}

	public String getUnitsMember() {
		return unitsMember;
	}

	public void setUnitsMember(String unitsMember) {
		this.unitsMember = unitsMember;
	}

	public String getSiteMember() {
		return siteMember;
	}

	public void setSiteMember(String siteMember) {
		this.siteMember = siteMember;
	}

	public String getMarkClub() {
		return markClub;
	}

	public void setMarkClub(String markClub) {
		this.markClub = markClub;
	}

	public String getPasswordClub() {
		return passwordClub;
	}

	public void setPasswordClub(String passwordClub) {
		this.passwordClub = passwordClub;
	}

	public String getBalanceClub() {
		return balanceClub;
	}

	public void setBalanceClub(String balanceClub) {
		this.balanceClub = balanceClub;
	}

	public String getIntegralClub() {
		return integralClub;
	}

	public void setIntegralClub(String integralClub) {
		this.integralClub = integralClub;
	}

	public Date getDelivertimeClub() {
		return delivertimeClub;
	}

	public void setDelivertimeClub(Date delivertimeClub) {
		this.delivertimeClub = delivertimeClub;
	}

	public String getDelivertimeClubs() {
		return delivertimeClubs;
	}

	public void setDelivertimeClubs(Date delivertimeClub) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(delivertimeClub);
		this.delivertimeClubs = date;
	}

	public String getPapersTypeMC() {
		return papersTypeMC;
	}

	public void setPapersTypeMC(String papersTypeMC) {
		this.papersTypeMC = papersTypeMC;
	}

	public String getMemberTypeMC() {
		return memberTypeMC;
	}

	public void setMemberTypeMC(String memberTypeMC) {
		this.memberTypeMC = memberTypeMC;
	}

	public Boolean getCardClub() {
		return cardClub;
	}

	public void setCardClub(Boolean cardClub) {
		this.cardClub = cardClub;
	}

}
