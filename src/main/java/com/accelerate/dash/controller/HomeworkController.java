/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import java.util.Optional;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.service.HomeworkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/homework")
@RestController
public class HomeworkController {

	@Autowired
    private HomeworkService homeworkService;

    @GetMapping("/list")
    public ApiResponse getHomeworkList(@RequestParam("from") Optional<String> from,
                                            @RequestParam("to") Optional<String> to) {
        
        return homeworkService.getHomeworkList(from, to);
    }

    @GetMapping("")
    public ResponseEntity<?> getHomework(@RequestParam("id") String id) {
        return homeworkService.getHomework(id);
    }

    @PostMapping("")
    public ApiResponse addHomework(@RequestParam("file") MultipartFile file, @RequestParam("date") String date) {
        return homeworkService.addHomework(file, date);
    }

    @GetMapping("/delete")
    public ApiResponse deleteHomework(@RequestParam("id") String id) {
        return homeworkService.deleteHomework(id);
    }
}
