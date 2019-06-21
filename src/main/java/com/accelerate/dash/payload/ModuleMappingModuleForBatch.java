/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Module part of response to viewModuleSchedule given batch
package com.accelerate.dash.payload;

import java.util.List;

public class ModuleMappingModuleForBatch {
    
    private String moduleId;

    private String moduleName;

    private Double allowedTime;

    private Double timeTolerance;

    private String timeUnit;

    private String targetCompletionDate;

    private Boolean isRegistered;

    private Boolean isMapped;

    private Boolean hasRepeats;

    private Integer overallStatus;

    private List<ModuleMappingRepeat> details;

    public ModuleMappingModuleForBatch() {}

    public ModuleMappingModuleForBatch(String moduleId, String moduleName, Double allowedTime, Double timeTolerance,
            String timeUnit, String targetCompletionDate, Boolean isRegistered, Boolean isMapped, 
            Boolean hasRepeats, Integer overallStatus, List<ModuleMappingRepeat> details) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.allowedTime = allowedTime;
        this.timeTolerance = timeTolerance;
        this.timeUnit = timeUnit;
        this.targetCompletionDate = targetCompletionDate;
        this.isRegistered = isRegistered;
        this.isMapped = isMapped;
        this.hasRepeats = hasRepeats;
        this.overallStatus = overallStatus;
        this.details = details;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Double getAllowedTime() {
        return allowedTime;
    }

    public void setAllowedTime(Double allowedTime) {
        this.allowedTime = allowedTime;
    }

    public Double getTimeTolerance() {
        return timeTolerance;
    }

    public void setTimeTolerance(Double timeTolerance) {
        this.timeTolerance = timeTolerance;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTargetCompletionDate() {
        return targetCompletionDate;
    }

    public void setTargetCompletionDate(String targetCompletionDate) {
        this.targetCompletionDate = targetCompletionDate;
    }

    public List<ModuleMappingRepeat> getDetails() {
        return details;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Boolean getIsMapped() {
        return isMapped;
    }

    public void setIsMapped(Boolean isMapped) {
        this.isMapped = isMapped;
    }

    public void setDetails(List<ModuleMappingRepeat> details) {
        this.details = details;
    }

    public Boolean getHasRepeats() {
        return hasRepeats;
    }

    public void setHasRepeats(Boolean hasRepeats) {
        this.hasRepeats = hasRepeats;
    }

    public Integer getOverallStatus() {
        return overallStatus;
    }

    public void setOverallStatus(Integer overallStatus) {
        this.overallStatus = overallStatus;
    }
}
