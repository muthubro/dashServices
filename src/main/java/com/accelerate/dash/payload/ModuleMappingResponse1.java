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

public class ModuleMappingResponse1 extends ApiResponse {

    private ModuleMappingCourseResponse data;

    public ModuleMappingResponse1(boolean status, StatusCodes statusCode, String message,
            ModuleMappingCourseResponse data) {
        super(status, statusCode, message);
        this.data = data;
    }

    public ModuleMappingCourseResponse getData() {
        return data;
    }

    public void setData(ModuleMappingCourseResponse data) {
        this.data = data;
    }
}
