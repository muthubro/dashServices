package com.example.dash.payload;

import com.example.dash.controller.StatusCodes;

public class ErrorResponse extends ApiResponse {

    public ErrorResponse(boolean status, StatusCodes statusCode, String message) {
        super(status, statusCode, message);
    }
}
