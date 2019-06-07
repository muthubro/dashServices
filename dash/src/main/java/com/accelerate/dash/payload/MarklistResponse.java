/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import java.util.Map;

import com.accelerate.dash.service.StatusCodes;

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
