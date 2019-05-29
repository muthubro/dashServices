package com.example.dash.payload;

import com.example.dash.controller.StatusCodes;

public class SuccessResponse extends ApiResponse {

    public SuccessResponse(boolean status, StatusCodes statusCode, String message) {
        super(status, statusCode, message);
    }
}
