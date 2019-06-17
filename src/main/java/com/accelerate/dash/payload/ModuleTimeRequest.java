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
import javax.validation.constraints.NotNull;

// Request format to set module time_alloted and tolerance
public class ModuleTimeRequest {

    @NotBlank
    private String moduleId;

    @NotNull
    private Double timeAllotted;

    @NotNull
    private Double maximumTolerance;

    @NotBlank
    private String timeUnit;

    public ModuleTimeRequest() {}

    public ModuleTimeRequest(String moduleId, Double timeAllotted, Double maximumTolerance, String timeUnit) {
        this.moduleId = moduleId;
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
}
