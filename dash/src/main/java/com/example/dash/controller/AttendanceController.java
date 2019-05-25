package com.example.dash.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dash.model.Attendance;
import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.AttendanceResponse;
import com.example.dash.service.AttendanceService;

@RestController
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/api/attendance")
	public ResponseEntity<?> getAttendance(@RequestParam("id") String reg,
										  @RequestParam("from") Optional<Date> from,
										  @RequestParam("to") Optional<Date> to) {
		List<Attendance> attendances = attendanceService.getAttendance(reg, from, to);
		if (attendances == null) return ResponseEntity.ok(new AttendanceResponse(false, "No user found"));
		return ResponseEntity.ok(new AttendanceResponse(true, "Attendance fetched successfully", attendances));
	}
	
	@PostMapping("/api/attendance")
	public ResponseEntity<?> recordAttendance(@RequestParam("file") MultipartFile[] files) throws IOException {
		for (MultipartFile file : files) {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			
			Row row = worksheet.getRow(3);
			String date = row.getCell(1).getStringCellValue();
			Date date1 = new Date();
			try {
				date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			} catch (ParseException ex) {
				workbook.close();
				return ResponseEntity.ok(new ApiResponse(false, "Invalid date format"));
			}
			
			String id;
			String status;
			int stud_count = 3;    // Change to database call
			Map<String, String> attendances = new HashMap<>();
			int idx = 0;
			
			try {
				do {
					row = worksheet.getRow(idx + 5);
					
					id = row.getCell(0).getStringCellValue();
					status = row.getCell(2).getStringCellValue();
					
					attendances.put(id, status);
					idx++;
				} while (idx < stud_count);
			} catch (Exception ex) {
				workbook.close();
				return ResponseEntity.ok(new ApiResponse(false, "Wrong number of students"));
			}
			
			workbook.close();
		}
		
		return ResponseEntity.ok(new ApiResponse(true, "Attendance successfully recorded."));
	}
}
