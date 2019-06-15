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

// Request format to modify module status
public class ModuleStatusModifyRequest {

    @NotBlank
    private String moduleId;

    @NotBlank
    private String batchId;

    Integer maxTime;

    Integer tolerance;

    Integer status;

    public ModuleStatusModifyRequest() {}

    public ModuleStatusModifyRequest(@NotBlank String moduleId, @NotBlank String batchId, Integer maxTime,
            Integer tolerance, Integer status) {
        this.moduleId = moduleId;
        this.batchId = batchId;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.status = status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
