/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Response to viewModuleSchedule given batch
package com.accelerate.dash.payload;

import com.accelerate.dash.service.StatusCodes;

public class ModuleMappingResponseForBatch extends ApiResponse {

    private ModuleMappingDataForBatch data;

    public ModuleMappingResponseForBatch(boolean status, StatusCodes statusCode, String message,
            ModuleMappingDataForBatch data) {
        super(status, statusCode, message);
        this.data = data;
    }

    public ModuleMappingDataForBatch getData() {
        return data;
    }

    public void setData(ModuleMappingDataForBatch data) {
        this.data = data;
    }
}
