/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import javax.validation.Valid;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.ModuleBatchMappingRequest;
import com.accelerate.dash.payload.ModuleRequest;
import com.accelerate.dash.payload.ModuleStatusModifyRequest;
import com.accelerate.dash.payload.ModuleTimeRequest;
import com.accelerate.dash.service.ManagementService;
import com.accelerate.dash.service.StatusCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manage")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @PostMapping("/module")
    public ApiResponse addModule(@Valid @RequestBody ModuleRequest moduleRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid request");
        return managementService.addModule(moduleRequest);
    }

    @PostMapping("/module/time")
    public ApiResponse setTimeForModule(@Valid @RequestBody ModuleTimeRequest moduleTimeRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid request");
        return managementService.setTimeForModule(moduleTimeRequest);
    }

    @PostMapping("/module/batch")
    public ApiResponse mapModuleToBatch(@Valid @RequestBody ModuleBatchMappingRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid request");
        return managementService.mapModuleToBatch(request);
    }

    @PostMapping("/module/edit")
    public ApiResponse changeModuleStatus(@Valid @RequestBody ModuleStatusModifyRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid request");
        return managementService.changeModuleStatus(request);
    }
}
