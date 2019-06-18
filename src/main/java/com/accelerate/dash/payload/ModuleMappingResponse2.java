/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import com.accelerate.dash.service.StatusCodes;

public class ModuleMappingResponse2 extends ApiResponse {

    private ModuleMappingAllResponse data;

    public ModuleMappingResponse2(boolean status, StatusCodes statusCode, String message,
            ModuleMappingAllResponse data) {
        super(status, statusCode, message);
        this.data = data;
    }

    public ModuleMappingAllResponse getData() {
        return data;
    }

    public void setData(ModuleMappingAllResponse data) {
        this.data = data;
    }
}
