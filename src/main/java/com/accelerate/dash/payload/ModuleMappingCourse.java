/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Course unit of response of viewModuleSchedule (given batch)
package com.accelerate.dash.payload;

import java.util.List;

public class ModuleMappingCourse {

    private String courseId;

    private String courseName;

    private List<ModuleMappingModuleForBatch> modules;

    public ModuleMappingCourse() {}

    public ModuleMappingCourse(String courseId, String courseName, List<ModuleMappingModuleForBatch> modules) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.modules = modules;
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

    public List<ModuleMappingModuleForBatch> getModules() {
        return modules;
    }

    public void setModules(List<ModuleMappingModuleForBatch> modules) {
        this.modules = modules;
    }
}
