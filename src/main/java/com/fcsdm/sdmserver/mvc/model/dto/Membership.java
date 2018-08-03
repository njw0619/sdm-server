package com.fcsdm.sdmserver.mvc.model.dto;

public class Membership {
	private int memberId;
	private String name;
	private String grade;
	private String occupation;
	private String occurMonth;
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOccurMonth() {
		return occurMonth;
	}
	public void setOccurMonth(String occurMonth) {
		this.occurMonth = occurMonth;
	}
}
