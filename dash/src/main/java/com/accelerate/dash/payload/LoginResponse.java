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

public class LoginResponse extends ApiResponse {

    private String token;

    public LoginResponse(boolean status, StatusCodes statusCode, String message, String token) {
        super(status, statusCode, message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
