/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
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
    private Double maxTime;

    @NotNull
    private Double tolerance;

    @NotBlank
    private String teacherCode;

    @NotBlank
    private String dateOfCreation;

    @NotBlank
    private String adminCode;

    @Column(name = "repeat_title")
    private String repeatTitle;

    @Column(name = "comments")
    private String comments;

    @Column(name = "start_date")
    private String startDate = "";

    @Column(name = "end_date")
    private String endDate = "";

    @Column(name = "progress")
    private Double progress = 0.0;

    @Column(name = "logged_sessions_count")
    private Integer loggedSessionsCount = 0;

    @Column(name = "rating")
    private Double rating = 0.0;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "is_frozen")
    private Boolean isFrozen = false;

    @Column(name = "is_complete_acknowledged")
    private boolean isCompleteAcknowledged = false;

    @NotNull
    private Integer partIndex;

    @NotNull
    private Integer repeats;

    public ModuleBatchMapping() {}

    public ModuleBatchMapping(Module module, String batchId, Double maxTime,
            Double tolerance, String teacherCode, String dateOfCreation,
            String adminCode, String repeatTitle, String comments, String startDate, String endDate,
            Double progress, Integer loggedSessionsCount, Double rating, Integer status, Boolean isFrozen,
            boolean isCompleteAcknowledged, Integer partIndex, Integer repeats) {
        this.module = module;
        this.batchId = batchId;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.teacherCode = teacherCode;
        this.dateOfCreation = dateOfCreation;
        this.adminCode = adminCode;
        this.repeatTitle = repeatTitle;
        this.comments = comments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.loggedSessionsCount = loggedSessionsCount;
        this.rating = rating;
        this.status = status;
        this.isFrozen = isFrozen;
        this.isCompleteAcknowledged = isCompleteAcknowledged;
        this.partIndex = partIndex;
        this.repeats = repeats;
    }

    

    public ModuleBatchMapping(Module module, String batchId, Double maxTime,
            Double tolerance, String teacherCode, String dateOfCreation, String adminCode,
            String repeatTitle, String comments, Integer partIndex, Integer repeats) {
        this.module = module;
        this.batchId = batchId;
        this.maxTime = maxTime;
        this.tolerance = tolerance;
        this.teacherCode = teacherCode;
        this.dateOfCreation = dateOfCreation;
        this.adminCode = adminCode;
        this.repeatTitle = repeatTitle;
        this.comments = comments;
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

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public void addProgress(Double duration) {
        progress += duration;
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

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getRepeatTitle() {
        return repeatTitle;
    }

    public void setRepeatTitle(String repeatTitle) {
        this.repeatTitle = repeatTitle;
    }

    public Integer getLoggedSessionsCount() {
        return loggedSessionsCount;
    }

    public void setLoggedSessionsCount(Integer loggedSessionsCount) {
        this.loggedSessionsCount = loggedSessionsCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
}
