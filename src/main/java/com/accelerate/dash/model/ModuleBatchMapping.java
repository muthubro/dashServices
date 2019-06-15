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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "module_batch_mapping")
public class ModuleBatchMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @NotBlank
    private String batchId;

    @NotNull
    private Integer maxTime;

    @NotNull
    private Integer tolerance;

    @NotBlank
    private String teacherCode;

    @Column(name = "comments")
    private String comments = "";

    @Column(name = "start_date")
    private String startDate = "";

    @Column(name = "end_date")
    private String endDate = "";

    @Column(name = "progress")
    private Integer progress = 0;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "is_complete_acknowledged")
    private boolean isCompleteAcknowledged = false;

    @NotNull
    private Integer partIndex;

    @NotNull
    private Integer repeats;

    public ModuleBatchMapping() {}

    public ModuleBatchMapping(Module module, String batchId, Integer maxTime,
            Integer tolerance, String teacherCode, String comments, String startDate, String endDate,
            Integer progress, Integer status, boolean isCompleteAcknowledged, Integer partIndex, Integer repeats) {
        this.module = module;
        this.batchId = batchId;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.teacherCode = teacherCode;
        this.comments = comments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.status = status;
        this.isCompleteAcknowledged = isCompleteAcknowledged;
        this.partIndex = partIndex;
        this.repeats = repeats;
    }

    public ModuleBatchMapping(Module module, @NotBlank String batchId, @NotNull Integer maxTime,
            @NotNull Integer tolerance, @NotBlank String teacherCode, Integer partIndex, Integer repeats) {
        this.module = module;
        this.batchId = batchId;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.teacherCode = teacherCode;
        this.partIndex = partIndex;
        this.repeats = repeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getTolerance() {
        return tolerance;
    }

    public void setTolerance(Integer tolerance) {
        this.tolerance = tolerance;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isCompleteAcknowledged() {
        return isCompleteAcknowledged;
    }

    public void setCompleteAcknowledged(boolean isCompleteAcknowledged) {
        this.isCompleteAcknowledged = isCompleteAcknowledged;
    }

    public Integer getPartIndex() {
        return partIndex;
    }

    public void setPartIndex(Integer partIndex) {
        this.partIndex = partIndex;
    }

    public Integer getRepeats() {
        return repeats;
    }

    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }
}
