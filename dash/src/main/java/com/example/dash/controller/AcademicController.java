package com.example.dash.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.ErrorResponse;
import com.example.dash.payload.MarklistResponse;
import com.example.dash.payload.SuccessResponse;
import com.example.dash.service.AcademicService;
import com.example.dash.utility.ValidationUtility;

@RestController
@RequestMapping("/api")
public class AcademicController {
	
	@Autowired
	private AcademicService academicService;

	@Autowired
	private ValidationUtility validationUtility;

	@GetMapping("/marklist/{reg}")
	public ResponseEntity<ApiResponse> getMarklist(@PathVariable("reg") String reg) {
		Map<String, Integer> marklist = academicService.getMarklist(reg);
		
		if (marklist == null) {
			return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Didn't find marklist"));
		}
		return ResponseEntity.ok(new MarklistResponse(true, StatusCodes.SUCCESS, "Successfully fetched marklist", marklist));
	}
	
	@GetMapping("/academic/{reg}")
	public ResponseEntity<?> getAcademicReport(@PathVariable("reg") String reg) {
		byte[] file = academicService.getAcademicReport(reg);
		
		// Necessary headers for sending PDF file
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/marklist")
	public ResponseEntity<ApiResponse> recordMarklist(@RequestParam("file") MultipartFile[] files) throws IOException {
		for (MultipartFile file : files) {
			// Get excel workbook, worksheet and read the rows
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
						
			// Date validation
			Row row = worksheet.getRow(1);
			String date = row.getCell(1).getStringCellValue();
			if (!validationUtility.validateDate(date)) {
				workbook.close();
				return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid date format"));
			}
			
			String id, temp = "";
			int marks, temp2 = 0;
			int stud_count = 3;    // Change to database call
			Map<String, Integer> marklist = new HashMap<>();
			int idx = 0;
			
			try {
				do {
					row = worksheet.getRow(idx + 3);
					
					// ID validation
					id = row.getCell(0).getStringCellValue();
					if (!validationUtility.validateStudentID(id)) {
						workbook.close();
						return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid student ID"));
					}
					
					// Marks validation
					try {	// Try to read marks as string
						temp = row.getCell(2).getStringCellValue();
					} catch (Exception ex) {	// If that doesn't work, try to read marks as integer
						try {
							temp2 = (int) row.getCell(2).getNumericCellValue();
						} catch (Exception e) {		// If even that doesn't work, give error
							workbook.close();
							return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid marks"));
						}
						temp = Integer.toString(temp2);
					}
					try {
						marks = Integer.parseInt(temp);
					} catch (NumberFormatException ex) {
						workbook.close();
						return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid marks"));
					}
					
					marklist.put(id, marks);
					idx++;
				} while (idx < stud_count);
			} catch (Exception ex) { // Gives error if number of students in file is less than stud_count
				workbook.close();
				return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Wrong number of students"));
			}
			
			workbook.close();
		}
		
		return ResponseEntity.ok(new SuccessResponse(true, StatusCodes.SUCCESS, "Marklist successfully recorded."));
	}
}
