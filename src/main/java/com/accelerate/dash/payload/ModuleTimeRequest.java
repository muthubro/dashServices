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
import javax.validation.constraints.NotNull;

// Request format to set module time_alloted and tolerance
public class ModuleTimeRequest {

    @NotBlank
    private String moduleId;

    @NotNull
    private Integer timeAllotted;

    @NotNull
    private Integer maximumTolerance;

    @NotBlank
    private String timeUnit;

    public ModuleTimeRequest() {}

    public ModuleTimeRequest(String moduleId, Integer timeAllotted, Integer maximumTolerance, String timeUnit) {
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

    public Integer getTimeAllotted() {
        return timeAllotted;
    }

    public void setTimeAllotted(Integer timeAllotted) {
        this.timeAllotted = timeAllotted;
    }

    public Integer getMaximumTolerance() {
        return maximumTolerance;
    }

    public void setMaximumTolerance(Integer maximumTolerance) {
        this.maximumTolerance = maximumTolerance;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
}
