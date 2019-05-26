package com.example.dash.payload;

import java.util.List;

import com.example.dash.model.Attendance;

// Response to an attendance GET request
public class AttendanceResponse {

	private boolean status;
	private String message;
	private List<Attendance> data;
	
	public AttendanceResponse(boolean status, String message, List<Attendance> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public AttendanceResponse(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Attendance> getData() {
		return data;
	}

	public void setData(List<Attendance> data) {
		this.data = data;
	}
}
