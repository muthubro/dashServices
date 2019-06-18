/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import java.util.List;

public class ModuleMappingModule {
    
    private String moduleId;

    private String moduleName;

    private Double allowedTime;

    private Double timeTolerance;

    private String timeUnit;

    private String targetCompletionDate;

    private List<ModuleMappingBatch> batchSchedule;

    public ModuleMappingModule() {}

    public ModuleMappingModule(String moduleId, String moduleName, Double allowedTime, Double timeTolerance,
            String timeUnit, String targetCompletionDate, List<ModuleMappingBatch> batchSchedule) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.allowedTime = allowedTime;
        this.timeTolerance = timeTolerance;
        this.timeUnit = timeUnit;
        this.targetCompletionDate = targetCompletionDate;
        this.batchSchedule = batchSchedule;
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

    public List<ModuleMappingBatch> getBatchSchedule() {
        return batchSchedule;
    }

    public void setBatchSchedule(List<ModuleMappingBatch> batchSchedule) {
        this.batchSchedule = batchSchedule;
    }
}
