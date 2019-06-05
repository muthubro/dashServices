package com.example.dash.controller;

import java.util.Map;
import java.util.Optional;

import com.example.dash.payload.ApiResponse;
import com.example.dash.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ApiResponse getStudents(@RequestBody Optional<Map<String, ?>> map) {
        return studentService.getStudents(map);
    }
}
