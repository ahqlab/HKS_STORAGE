package com.hankisul.storage.domain;

import lombok.Data;

@Data
public class User implements Domain {
	private int id;
	private String userId;
	private String password;
	private String beforePassword;
	private String newPassword1;
	private String newPassword2;
	private int role;
	private String roleKorNm;
	private String company;
	private String serviceEmail;
	private String serviceEmailPassword;
	private String createDate;

	public int getId() {
		return this.id;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getPassword() {
		return this.password;
	}

	public String getBeforePassword() {
		return this.beforePassword;
	}

	public String getNewPassword1() {
		return this.newPassword1;
	}

	public String getNewPassword2() {
		return this.newPassword2;
	}

	public int getRole() {
		return this.role;
	}

	public String getRoleKorNm() {
		return this.roleKorNm;
	}

	public String getCompany() {
		return this.company;
	}

	public String getServiceEmail() {
		return this.serviceEmail;
	}

	public String getServiceEmailPassword() {
		return this.serviceEmailPassword;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBeforePassword(String beforePassword) {
		this.beforePassword = beforePassword;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setRoleKorNm(String roleKorNm) {
		this.roleKorNm = roleKorNm;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setServiceEmail(String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}

	public void setServiceEmailPassword(String serviceEmailPassword) {
		this.serviceEmailPassword = serviceEmailPassword;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String toString() {
		return "User(id=" + getId() + ", userId=" + getUserId() + ", password=" + getPassword() + ", beforePassword="
				+ getBeforePassword() + ", newPassword1=" + getNewPassword1() + ", newPassword2=" + getNewPassword2()
				+ ", role=" + getRole() + ", roleKorNm=" + getRoleKorNm() + ", company=" + getCompany()
				+ ", serviceEmail=" + getServiceEmail() + ", serviceEmailPassword=" + getServiceEmailPassword()
				+ ", createDate=" + getCreateDate() + ")";
	}

}