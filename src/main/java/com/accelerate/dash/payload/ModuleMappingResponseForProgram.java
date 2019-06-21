/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Response to viewModuleSchedule given program
package com.accelerate.dash.payload;

import com.accelerate.dash.service.StatusCodes;

public class ModuleMappingResponseForProgram extends ApiResponse {

    private ModuleMappingDataForProgram data;

    public ModuleMappingResponseForProgram(boolean status, StatusCodes statusCode, String message,
            ModuleMappingDataForProgram data) {
        super(status, statusCode, message);
        this.data = data;
    }

    public ModuleMappingDataForProgram getData() {
        return data;
    }

    public void setData(ModuleMappingDataForProgram data) {
        this.data = data;
    }
}
