/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
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
    private int timeAllotted;

    @Column(name = "maximum_tolerance")
    private int maximumTolerance;

    @Column(name = "time_unit")
    private int timeUnit;

    public Module() {}

	public Module(String moduleId, String moduleName, int timeAllotted, int maximumTolerance, TimeUnit timeUnit) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.timeAllotted = timeAllotted;
        this.maximumTolerance = maximumTolerance;
        this.timeUnit = timeUnit.getValue();
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

    public int getTimeAllotted() {
        return timeAllotted;
    }

    public void setTimeAllotted(int timeAllotted) {
        this.timeAllotted = timeAllotted;
    }

    public int getMaximumTolerance() {
        return maximumTolerance;
    }

    public void setMaximumTolerance(int maximumTolerance) {
        this.maximumTolerance = maximumTolerance;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }
}
