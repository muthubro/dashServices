/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

// Status codes used in the API responses
public enum StatusCodes {

    INPUT_VALIDATION_ERROR(101),

    SUCCESS(200),

    UNAUTHORIZED(401),

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
