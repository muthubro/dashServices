package com.example.dash.payload;

import java.util.Map;

import com.example.dash.controller.StatusCodes;

// Response to a marklist GET request
public class MarklistResponse extends ApiResponse {

	private Map<String, Integer> data;
	
	public MarklistResponse(boolean status, StatusCodes statusCode, String message, Map<String, Integer> data) {
		super(status, statusCode, message);
		this.data = data;
	}

	public Map<String, Integer> getData() {
		return data;
	}

	public void setData(Map<String, Integer> data) {
		this.data = data;
	}
}
