package com.example.dash.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dash.payload.ApiResponse;
import com.example.dash.service.AcademicService;

@RestController
@RequestMapping("/api")
public class AcademicController {
	
	@Autowired
	private AcademicService academicService;

	@GetMapping("/marklist/{reg}")
	public ApiResponse getMarklist(@PathVariable("reg") String reg) {
		return academicService.getMarklist(reg);
	}
	
	@GetMapping("/academic/{reg}")
	public ResponseEntity<?> getAcademicReport(@PathVariable("reg") String reg) {
		return academicService.getAcademicReport(reg);
	}
	
	@PostMapping("/marklist")
	public ApiResponse recordMarklist(@RequestParam("file") MultipartFile file) throws IOException {
		return academicService.recordMarklist(file);
	}
}
