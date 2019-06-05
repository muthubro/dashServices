package com.example.dash.payload;

import com.example.dash.service.StatusCodes;

// Generic response to a successful API call
public class SuccessResponse extends ApiResponse {

    public SuccessResponse(boolean status, StatusCodes statusCode, String message) {
        super(status, statusCode, message);
    }
}
