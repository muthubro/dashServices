package com.example.dash.service;

// Status codes used in the API responses
public enum StatusCodes {

    INPUT_VALIDATION_ERROR(101),

    SUCCESS(200),

    MISSING_VALUE(404),

    INTERNAL_SERVER_ERROR(500);

    private final int value;

    StatusCodes(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
