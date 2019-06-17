/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 17 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeacherLogRequest {

    @NotNull
    private Long entryId;

    @NotBlank
    private String date;

    @NotNull
    private Double duration;

    @NotBlank
    private String timeUnit;

    public TeacherLogRequest() {}

    public TeacherLogRequest(Long entryId, String date, Double duration, String timeUnit) {
        this.entryId = entryId;
        this.date = date;
        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
}
