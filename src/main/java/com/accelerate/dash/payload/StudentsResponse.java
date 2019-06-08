/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import java.util.List;

import com.accelerate.dash.service.StatusCodes;
import com.accelerate.dash.model.Student;

// Response to a student list GET request
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
