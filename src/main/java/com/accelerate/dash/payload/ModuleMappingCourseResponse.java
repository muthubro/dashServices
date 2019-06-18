/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

public class ModuleMappingCourseResponse {

    private String dateFrom;

    private String dateTo;

    private String programId;

    private String programName;

    private ModuleMappingResponseData course;

    public ModuleMappingCourseResponse() {}

    public ModuleMappingCourseResponse(String dateFrom, String dateTo, String programId, String programName,
            ModuleMappingResponseData course) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.programId = programId;
        this.programName = programName;
        this.course = course;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ModuleMappingResponseData getCourse() {
        return course;
    }

    public void setCourse(ModuleMappingResponseData course) {
        this.course = course;
    }
}
