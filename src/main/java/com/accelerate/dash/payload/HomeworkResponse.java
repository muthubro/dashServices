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
import com.accelerate.dash.model.Homework;;

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
