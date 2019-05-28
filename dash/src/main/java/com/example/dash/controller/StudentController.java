package com.example.dash.controller;

import java.util.List;

import com.example.dash.model.StudentModel;
import com.example.dash.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/api/students")
    public ResponseEntity<?> getStudents() {
        List<StudentModel> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }
}
