/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Response to a viewModuleSchedule with just program id
package com.accelerate.dash.payload;

import java.util.List;

public class ModuleMappingDataForProgram {

    private String dateFrom;

    private String dateTo;

    private String programId;

    private String programName;

    private List<ModuleMappingForCourseAndProgram> course;

    public ModuleMappingDataForProgram() {}

    public ModuleMappingDataForProgram(String dateFrom, String dateTo, String programId, String programName,
            List<ModuleMappingForCourseAndProgram> course) {
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

    public List<ModuleMappingForCourseAndProgram> getCourse() {
        return course;
    }

    public void setCourse(List<ModuleMappingForCourseAndProgram> course) {
        this.course = course;
    }
}
