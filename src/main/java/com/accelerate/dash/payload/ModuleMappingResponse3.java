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

public class ModuleMappingResponse3 extends ApiResponse {

    private ModuleMappingBatchResponse data;

    public ModuleMappingResponse3(boolean status, StatusCodes statusCode, String message,
            ModuleMappingBatchResponse data) {
        super(status, statusCode, message);
        this.data = data;
    }

    public ModuleMappingBatchResponse getData() {
        return data;
    }

    public void setData(ModuleMappingBatchResponse data) {
        this.data = data;
    }
}
