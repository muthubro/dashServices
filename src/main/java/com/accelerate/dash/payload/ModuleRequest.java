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

// Request format for module creation
public class ModuleRequest {

    @NotBlank
    private String moduleId;

    @NotBlank
    private String moduleName;

    private Double timeAllotted;

    private Double maximumTolerance;

    private String timeUnit;

    public ModuleRequest() {}

    public ModuleRequest(String moduleId, String moduleName, Double timeAllotted, Double maximumTolerance, String timeUnit) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.timeAllotted = timeAllotted;
        this.maximumTolerance = maximumTolerance;
        this.timeUnit = timeUnit;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Double getTimeAllotted() {
        return timeAllotted;
    }

    public void setTimeAllotted(Double timeAllotted) {
        this.timeAllotted = timeAllotted;
    }

    public Double getMaximumTolerance() {
        return maximumTolerance;
    }

    public void setMaximumTolerance(Double maximumTolerance) {
        this.maximumTolerance = maximumTolerance;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
