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

public class ModuleMappingResponseData {
    
    private String courseId;

    private String courseName;

    private List<ModuleMappingModule> modulesList;

    public ModuleMappingResponseData() {}

    public ModuleMappingResponseData(String courseId, String courseName, List<ModuleMappingModule> modulesList) {
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
