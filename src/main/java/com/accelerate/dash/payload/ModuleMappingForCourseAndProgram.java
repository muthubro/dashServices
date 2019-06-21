/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Sub-unit of response of viewModuleSchedule given a course or given a program
package com.accelerate.dash.payload;

import java.util.List;

public class ModuleMappingForCourseAndProgram {
    
    private String courseId;

    private String courseName;

    private List<ModuleMappingModule> modulesList;

    public ModuleMappingForCourseAndProgram() {}

    public ModuleMappingForCourseAndProgram(String courseId, String courseName, List<ModuleMappingModule> modulesList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.modulesList = modulesList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<ModuleMappingModule> getModulesList() {
        return modulesList;
    }

    public void setModulesList(List<ModuleMappingModule> modulesList) {
        this.modulesList = modulesList;
    }
}
