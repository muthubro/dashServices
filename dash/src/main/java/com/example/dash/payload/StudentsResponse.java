package com.example.dash.payload;

import java.util.List;

import com.example.dash.controller.StatusCodes;
import com.example.dash.model.Student;

public class StudentsResponse extends ApiResponse {

	private List<Student> data;
	
	public StudentsResponse(boolean status, StatusCodes statusCode, String message, List<Student> data) {
		super(status, statusCode, message);
		this.data = data;
	}

	public List<Student> getData() {
		return data;
	}

	public void setData(List<Student> data) {
		this.data = data;
	}
}
