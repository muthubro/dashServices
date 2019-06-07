/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.example.dash.payload;

import java.util.List;

import com.example.dash.service.StatusCodes;
import com.example.dash.model.Attendance;

// Response to an attendance GET request
public class AttendanceResponse extends ApiResponse {

	private List<Attendance> data;
	
	public AttendanceResponse(boolean status, StatusCodes statusCode, String message, List<Attendance> data) {
		super(status, statusCode, message);
		this.data = data;
	}

	public List<Attendance> getData() {
		return data;
	}

	public void setData(List<Attendance> data) {
		this.data = data;
	}
}
