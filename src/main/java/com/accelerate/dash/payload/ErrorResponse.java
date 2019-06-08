/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import com.accelerate.dash.service.StatusCodes;

// Generic response for an error
public class ErrorResponse extends ApiResponse {

    public ErrorResponse(boolean status, StatusCodes statusCode, String message) {
        super(status, statusCode, message);
    }
}
