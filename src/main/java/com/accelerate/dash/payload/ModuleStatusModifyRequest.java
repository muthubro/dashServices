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

// Request format to modify module status
public class ModuleStatusModifyRequest {

    @NotNull
    private Long entryId;

    private Double maxTime;

    private Double tolerance;

    private String repeatTitle;

    private String comments;

    private Integer status;

    private Boolean isFrozen;

    public ModuleStatusModifyRequest() {}

    public ModuleStatusModifyRequest(@NotBlank Long entryId, Double maxTime, Double tolerance, String repeatTitle,
            String comments, Integer status, Boolean isFrozen) {
        this.entryId = entryId;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.repeatTitle = repeatTitle;
        this.comments = comments;
        this.status = status;
        this.isFrozen = isFrozen;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

    public String getRepeatTitle() {
        return repeatTitle;
    }

    public void setRepeatTitle(String repeatTitle) {
        this.repeatTitle = repeatTitle;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
}
