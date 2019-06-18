/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// The 'part' unit of response to viewModuleSchedule
package com.accelerate.dash.payload;

public class ModuleMappingPart {

    private Long entryId;

    private Integer partIndex;

    private String teacherCode;

    private String teacherName;

    private Double progressedTime;

    private String comments;

    private Boolean isTeacherRatingSubmitted;

    private Double teacherRating;

    private String dateStart;

    private String dateEnd;

    private Integer totalLoggedSessions;

    public ModuleMappingPart() {}

    public ModuleMappingPart(Long entryId, Integer partIndex, String teacherCode, String teacherName,
            Double progressedTime, String comments, Boolean isTeacherRatingSubmitted, Double teacherRating,
            String dateStart, String dateEnd, Integer totalLoggedSessions) {
        this.entryId = entryId;
        this.partIndex = partIndex;
        this.teacherCode = teacherCode;
        this.teacherName = teacherName;
        this.progressedTime = progressedTime;
        this.comments = comments;
        this.isTeacherRatingSubmitted = isTeacherRatingSubmitted;
        this.teacherRating = teacherRating;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.totalLoggedSessions = totalLoggedSessions;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Integer getPartIndex() {
        return partIndex;
    }

    public void setPartIndex(Integer partIndex) {
        this.partIndex = partIndex;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Double getProgressedTime() {
        return progressedTime;
    }

    public void setProgressedTime(Double progressedTime) {
        this.progressedTime = progressedTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsTeacherRatingSubmitted() {
        return isTeacherRatingSubmitted;
    }

    public void setIsTeacherRatingSubmitted(Boolean isTeacherRatingSubmitted) {
        this.isTeacherRatingSubmitted = isTeacherRatingSubmitted;
    }

    public Double getTeacherRating() {
        return teacherRating;
    }

    public void setTeacherRating(Double teacherRating) {
        this.teacherRating = teacherRating;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getTotalLoggedSessions() {
        return totalLoggedSessions;
    }

    public void setTotalLoggedSessions(Integer totalLoggedSessions) {
        this.totalLoggedSessions = totalLoggedSessions;
    }
}
