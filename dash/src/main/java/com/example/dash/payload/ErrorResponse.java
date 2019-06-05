package com.example.dash.payload;

import com.example.dash.service.StatusCodes;

// Generic response for an error
public class ErrorResponse extends ApiResponse {

    public ErrorResponse(boolean status, StatusCodes statusCode, String message) {
        super(status, statusCode, message);
    }
}
