package com.example.dash.model;

import java.util.Date;

public class Attendance {

	private Date date;
	private boolean status;
	
	public Attendance(Date date, boolean status) {
		this.date = date;
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
