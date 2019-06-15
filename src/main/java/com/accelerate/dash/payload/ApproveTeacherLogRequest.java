/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import javax.validation.constraints.NotBlank;

public class ApproveTeacherLogRequest {

    @NotBlank
    private String teacherCode;

    @NotBlank
    private String date;

    @NotBlank
    private String batchId;

    @NotBlank
    private String moduleId;

    public ApproveTeacherLogRequest() {}

    public ApproveTeacherLogRequest(@NotBlank String teacherCode, @NotBlank String date) {
        this.teacherCode = teacherCode;
        this.date = date;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
