/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// Request format for module creation
public class ModuleRequest {

    @NotBlank
    private String moduleId;

    @NotBlank
    private String moduleName;

    @NotNull
    private Integer moduleIndex;

    private Double timeAllotted;

    private Double maximumTolerance;

    private String timeUnit;

    private String lastCompletionDate;

    private Integer maxAllowedGap;

    public ModuleRequest() {}

    public ModuleRequest(String moduleId, String moduleName, Integer moduleIndex, Double timeAllotted, 
            Double maximumTolerance, String timeUnit, String lastCompletionDate, Integer maxAllowedGap) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleIndex = moduleIndex;
        this.timeAllotted = timeAllotted;
        this.maximumTolerance = maximumTolerance;
        this.timeUnit = timeUnit;
        this.lastCompletionDate = lastCompletionDate;
        this.maxAllowedGap = maxAllowedGap;
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

    public String getLastCompletionDate() {
        return lastCompletionDate;
    }

    public void setLastCompletionDate(String lastCompletionDate) {
        this.lastCompletionDate = lastCompletionDate;
    }

    public Integer getMaxAllowedGap() {
        return maxAllowedGap;
    }

    public void setMaxAllowedGap(Integer maxAllowedGap) {
        this.maxAllowedGap = maxAllowedGap;
    }

    public Integer getModuleIndex() {
        return moduleIndex;
    }

    public void setModuleIndex(Integer moduleIndex) {
        this.moduleIndex = moduleIndex;
    }
}
