/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.model;

/**
 * Units of time for time_allotted and tolerance of modules
 */
public enum TimeUnit {
    HOURS(0),
    DAYS(1);

    private int value;

    private TimeUnit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
