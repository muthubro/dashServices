/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NaturalId
    private String moduleId;

    @NotBlank
    private String moduleName;

    @Column(name = "time_alloted")
    private Double timeAllotted;

    @Column(name = "maximum_tolerance")
    private Double maximumTolerance;

    @Column(name = "time_unit")
    private String timeUnit;

    @Column(name = "last_completion_date")
    private String lastCompletionDate;

    @Column(name = "max_allowed_gap")
    private Integer maxAllowedGap;

    public Module() {}

    public Module(String moduleId, String moduleName, Double timeAllotted, Double maximumTolerance, String timeUnit,
            String lastCompletionDate, Integer maxAllowedGap) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.timeAllotted = timeAllotted;
        this.maximumTolerance = maximumTolerance;
        this.timeUnit = timeUnit;
        this.lastCompletionDate = lastCompletionDate;
        this.maxAllowedGap = maxAllowedGap;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
