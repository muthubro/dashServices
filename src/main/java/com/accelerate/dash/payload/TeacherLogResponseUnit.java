/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 17 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

public class TeacherLogResponseUnit {

    private String batchId;

    private String moduleId;

    private String date;

    private Double duration;

    private Boolean isApproved;

    public TeacherLogResponseUnit() {}

    public TeacherLogResponseUnit(String batchId, String moduleId, String date, Double duration, Boolean isApproved) {
        this.batchId = batchId;
        this.moduleId = moduleId;
        this.date = date;
        this.duration = duration;
        this.isApproved = isApproved;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }    
}
