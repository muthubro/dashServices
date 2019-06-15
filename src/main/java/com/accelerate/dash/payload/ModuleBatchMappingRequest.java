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

// Request format for module-batch mapping
public class ModuleBatchMappingRequest {

    @NotBlank
    private String moduleId;

    @NotBlank
    private String batchId;

    @NotBlank
    private String teacherCode;

    private Boolean repeat;

    Integer maxTime;

    Integer tolerance;

    public ModuleBatchMappingRequest(String moduleId, String batchId, String teacherCode, Boolean repeat, Integer maxTime,
            Integer tolerance) {
        this.moduleId = moduleId;
        this.batchId = batchId;
        this.teacherCode = teacherCode;
        this.repeat = repeat;
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

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getTolerance() {
        return tolerance;
    }

    public void setTolerance(Integer tolerance) {
        this.tolerance = tolerance;
    }

    public Boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }
}
