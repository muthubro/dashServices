/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import javax.validation.Valid;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.TeacherLogRequest;
import com.accelerate.dash.service.StatusCodes;
import com.accelerate.dash.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/log")
    public ApiResponse logTime(@Valid @RequestBody TeacherLogRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid request");
        return teacherService.logTime(request);
    }

    @GetMapping("/log/{teacherCode}")
    public ApiResponse getLog(@PathVariable("teacherCode") String teacherCode) {
        return teacherService.getLog(teacherCode);
    }

    @GetMapping("/mark/{mappingId}/{teacherCode}")
    public ApiResponse markAsCompleted(@PathVariable("mappingId") String mappingId,
                                       @PathVariable("teacherCode") String teacherCode) {
        return teacherService.markAsCompleted(mappingId, teacherCode);
    }
}
