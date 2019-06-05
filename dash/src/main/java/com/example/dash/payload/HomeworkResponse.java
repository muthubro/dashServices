package com.example.dash.payload;

import java.util.List;

import com.example.dash.service.StatusCodes;
import com.example.dash.model.Homework;;

// Response to a homework GET request
public class HomeworkResponse extends ApiResponse {

	private List<Homework> data;
	
	public HomeworkResponse(boolean status, StatusCodes statusCode, String message, List<Homework> data) {
		super(status, statusCode, message);
		this.data = data;
	}

	public List<Homework> getData() {
		return data;
	}

	public void setData(List<Homework> data) {
		this.data = data;
	}
}
