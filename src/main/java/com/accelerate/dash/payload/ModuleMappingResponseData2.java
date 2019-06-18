/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Sub-unit of response of viewModuleSchedule given a batch
package com.accelerate.dash.payload;

import java.util.List;

public class ModuleMappingResponseData2 {
    
    private String batchId;

    private String batchName;

    private List<ModuleMappingCourse> courseList;

    public ModuleMappingResponseData2() {}

    public ModuleMappingResponseData2(String batchId, String batchName, List<ModuleMappingCourse> courseList) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.courseList = courseList;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public List<ModuleMappingCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<ModuleMappingCourse> courseList) {
        this.courseList = courseList;
    }
}
