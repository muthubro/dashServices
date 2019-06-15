/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/log/{batchId}/{moduleId}/{teacherCode}/{time}")
    public ApiResponse logTime(@PathVariable("batchId") String batchId,
                               @PathVariable("moduleId") String moduleId,
                               @PathVariable("teacherCode") String teacherCode,
                               @PathVariable("time") Integer time) {
        return teacherService.logTime(batchId, moduleId, teacherCode, time);
    }

    @GetMapping("/log/{teacherCode}")
    public ApiResponse getLog(@PathVariable("teacherCode") String teacherCode) {
        return teacherService.getLog(teacherCode);
    }

    @GetMapping("/mark/{batchId}/{moduleId}/{teacherCode}")
    public ApiResponse markAsCompleted(@PathVariable("batchId") String batchId,
                                       @PathVariable("moduleId") String moduleId,
                                       @PathVariable("teacherCode") String teacherCode) {
        return teacherService.markAsCompleted(batchId, moduleId, teacherCode);
    }
}
