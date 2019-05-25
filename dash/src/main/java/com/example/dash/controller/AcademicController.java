package com.example.dash.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.MarklistResponse;
import com.example.dash.service.AcademicService;

@RestController
public class AcademicController {
	
	@Autowired
	private AcademicService academicService;

	@GetMapping("/api/marklist/{reg}")
	public ResponseEntity<?> getMarklist(@PathVariable("reg") String reg) {
		Map<String, Integer> marklist = academicService.getMarklist(reg);
		
		if (marklist == null) {
			return ResponseEntity.ok(new MarklistResponse(false, "Didn't find marklist"));
		}
		return ResponseEntity.ok(new MarklistResponse(true, "Successfully fetched marklist", marklist));
	}
	
	@GetMapping("/api/academic/{reg}")
	public ResponseEntity<?> getAcademicReport(@PathVariable("reg") String reg) {
		byte[] file = academicService.getAcademicReport(reg);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		ResponseEntity<byte[]> response = new ResponseEntity(file, headers, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/api/marklist/{reg}")
	public ResponseEntity<?> recordMarklist(@RequestParam("file") MultipartFile[] files) throws IOException {
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
			int marks;
			int stud_count = 3;    // Change to database call
			Map<String, Integer> marklist = new HashMap<>();
			int idx = 0;
			
			try {
				do {
					row = worksheet.getRow(idx + 5);
					
					id = row.getCell(0).getStringCellValue();
					try {
						String temp = row.getCell(2).getStringCellValue();
						marks = Integer.parseInt(temp);
					} catch (Exception ex) {
						workbook.close();
						return ResponseEntity.ok(new ApiResponse(false, "Invalid marks"));
					}
					
					marklist.put(id, marks);
					idx++;
				} while (idx < stud_count);
			} catch (Exception ex) {
				workbook.close();
				return ResponseEntity.ok(new ApiResponse(false, "Wrong number of students"));
			}
			
			workbook.close();
		}
		
		return ResponseEntity.ok(new ApiResponse(true, "Marklist successfully recorded."));
	}
}
