/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import javax.validation.constraints.NotBlank;

// Request format for module-batch mapping
public class ModuleBatchMappingRequest {

    @NotBlank
    private String moduleId;

    @NotBlank
    private String batchId;

    @NotBlank
    private String teacherCode;

    @NotBlank
    private String adminCode;

    private String repeatTitle;

    private String comments;

    private Boolean isRepeat;

    private Double maxTime = -1.0;

    private Double tolerance = -1.0;

    public ModuleBatchMappingRequest(String moduleId, String batchId, String teacherCode, String adminCode, Boolean isRepeat, 
            String repeatTitle, String comments, Double maxTime, Double tolerance) {
        this.moduleId = moduleId;
        this.batchId = batchId;
        this.teacherCode = teacherCode;
        this.adminCode = adminCode;
        this.repeatTitle = repeatTitle;
        this.comments = comments;
        this.isRepeat = isRepeat;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Double maxTime) {
        this.maxTime = maxTime;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getRepeatTitle() {
        return repeatTitle;
    }

    public void setRepeatTitle(String repeatTitle) {
        this.repeatTitle = repeatTitle;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(Boolean isRepeat) {
        this.isRepeat = isRepeat;
    }
}
