package com.example.dash.payload;

import java.util.Map;

public class MarklistResponse {

	private boolean status;
	private String message;
	private Map<String, Integer> data;
	
	public MarklistResponse(boolean status, String message, Map<String, Integer> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public MarklistResponse(boolean status, String message) {
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

	public Map<String, Integer> getData() {
		return data;
	}

	public void setData(Map<String, Integer> data) {
		this.data = data;
	}
}
