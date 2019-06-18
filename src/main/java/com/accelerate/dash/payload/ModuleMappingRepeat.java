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

public class ModuleMappingRepeat {

    private Integer repeatIndex;

    private String title;

    private Boolean hasParts;

    private List<ModuleMappingPart> parts;

    private Double maxTime;

    private Double tolerance;

    private Double progressedTime;

    private Integer status;

    private String completionDeadline;

    private Boolean scheduleWarning;

    private String scheduleWarningText;

    public ModuleMappingRepeat() {}

    public ModuleMappingRepeat(Integer repeatIndex, String title, Boolean hasParts, List<ModuleMappingPart> parts,
            Double maxTime, Double tolerance, Double progressedTime, Integer status, String completionDeadline,
            Boolean scheduleWarning, String scheduleWarningText) {
        this.repeatIndex = repeatIndex;
        this.title = title;
        this.hasParts = hasParts;
        this.parts = parts;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.progressedTime = progressedTime;
        this.status = status;
        this.completionDeadline = completionDeadline;
        this.scheduleWarning = scheduleWarning;
        this.scheduleWarningText = scheduleWarningText;
    }

    public Integer getRepeatIndex() {
        return repeatIndex;
    }

    public void setRepeatIndex(Integer repeatIndex) {
        this.repeatIndex = repeatIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getHasParts() {
        return hasParts;
    }

    public void setHasParts(Boolean hasParts) {
        this.hasParts = hasParts;
    }

    public List<ModuleMappingPart> getParts() {
        return parts;
    }

    public void setParts(List<ModuleMappingPart> parts) {
        this.parts = parts;
    }

    public Double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Double maxTime) {
        this.maxTime = maxTime;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Double getProgressedTime() {
        return progressedTime;
    }

    public void setProgressedTime(Double progressedTime) {
        this.progressedTime = progressedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompletionDeadline() {
        return completionDeadline;
    }

    public void setCompletionDeadline(String completionDeadline) {
        this.completionDeadline = completionDeadline;
    }

    public Boolean getScheduleWarning() {
        return scheduleWarning;
    }

    public void setScheduleWarning(Boolean scheduleWarning) {
        this.scheduleWarning = scheduleWarning;
    }

    public String getScheduleWarningText() {
        return scheduleWarningText;
    }

    public void setScheduleWarningText(String scheduleWarningText) {
        this.scheduleWarningText = scheduleWarningText;
    }
}
