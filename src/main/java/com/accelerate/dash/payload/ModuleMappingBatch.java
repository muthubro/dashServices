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

public class ModuleMappingBatch {

    private String batchCode;

    private String batchTargetCompletionDate;

    private Boolean isRegistered;

    private Boolean isMapped;

    private Boolean hasRepeats;

    private List<ModuleMappingRepeat> details;

    private Integer overallStatus;

    public ModuleMappingBatch() {}

    public ModuleMappingBatch(String batchCode, String batchTargetCompletionDate, Boolean isRegistered,
            Boolean isMapped, Boolean hasRepeats, List<ModuleMappingRepeat> details, Integer overallStatus) {
        this.batchCode = batchCode;
        this.batchTargetCompletionDate = batchTargetCompletionDate;
        this.isRegistered = isRegistered;
        this.isMapped = isMapped;
        this.hasRepeats = hasRepeats;
        this.details = details;
        this.overallStatus = overallStatus;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBatchTargetCompletionDate() {
        return batchTargetCompletionDate;
    }

    public void setBatchTargetCompletionDate(String batchTargetCompletionDate) {
        this.batchTargetCompletionDate = batchTargetCompletionDate;
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

    public Boolean getHasRepeats() {
        return hasRepeats;
    }

    public void setHasRepeats(Boolean hasRepeats) {
        this.hasRepeats = hasRepeats;
    }

    public List<ModuleMappingRepeat> getDetails() {
        return details;
    }

    public void setDetails(List<ModuleMappingRepeat> details) {
        this.details = details;
    }

    public Integer getOverallStatus() {
        return overallStatus;
    }

    public void setOverallStatus(Integer overallStatus) {
        this.overallStatus = overallStatus;
    }
}
