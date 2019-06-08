/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.AttendanceConfirmationRequest;
import com.accelerate.dash.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("")
	public ApiResponse getAttendance(@RequestParam("id") String reg,
										  @RequestParam("from") Optional<String> from,
										  @RequestParam("to") Optional<String> to) {
		return attendanceService.getAttendance(reg, from, to);
	}
	
	@PostMapping("")
	public ApiResponse recordAttendance(@RequestParam("file") MultipartFile file) throws IOException {
		return attendanceService.recordAttendance(file);
	}

	@PostMapping("/confirm")
	public ApiResponse confirmAttendance(@RequestBody AttendanceConfirmationRequest request) {
		return attendanceService.confirmAttendance(request.getData());
	}
}
