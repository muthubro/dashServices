/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Response to viewModuleSchedule given course
package com.accelerate.dash.payload;

import com.accelerate.dash.service.StatusCodes;

public class ModuleMappingResponseForCourse extends ApiResponse {

    private ModuleMappingDataForCourse data;

    public ModuleMappingResponseForCourse(boolean status, StatusCodes statusCode, String message,
            ModuleMappingDataForCourse data) {
        super(status, statusCode, message);
        this.data = data;
    }

    public ModuleMappingDataForCourse getData() {
        return data;
    }

    public void setData(ModuleMappingDataForCourse data) {
        this.data = data;
    }
}
