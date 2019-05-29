package com.example.dash.controller;

public enum StatusCodes {

    INPUT_VALIDATION_ERROR(101),

    SUCCESS(200),

    MISSING_VALUE(404);

    private final int value;

    StatusCodes(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
