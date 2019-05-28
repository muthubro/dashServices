package com.example.dash.model;

public class Attendance {

	private String date;
	private boolean status;
	private int leaveStatus;
	
	public Attendance(String date, boolean status, int leaveStatus) {
		this.date = date;
		this.status = status;
		this.leaveStatus = leaveStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(int leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
}
