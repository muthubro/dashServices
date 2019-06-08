/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.MarklistResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.utility.PdfGenerationUtility;
import com.accelerate.dash.utility.ValidationUtility;

@Service
public class AcademicService {

	private static final int EXCEL_HEADER_COUNT = 3;
	
	@Autowired
	private PdfGenerationUtility pdfUtil;

	@Autowired
	private ValidationUtility validationUtility;

	public ApiResponse getMarklist(String reg) {
		// Marklist stub data. Replace with database call.
		Map<String, Integer> marklist = new HashMap<>();
		marklist.put("Physics", 100);
		marklist.put("Chemistry", 100);
		marklist.put("Mathematics", 100);
		marklist.put("Biology", 100);
		
		return new MarklistResponse(true, StatusCodes.SUCCESS, "Successfully fetched marklist", marklist);
	}
	
	public ResponseEntity<?> getAcademicReport(String reg) {
		// Create PDF file as a byte array
		Map<String, Object> data = new HashMap<>();
		data.put("name", reg);
		byte[] file = pdfUtil.createPdf("academic", "temp", data);
		
		// Set necessary headers to send PDF
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
		return response;
	}

	public ApiResponse recordMarklist(MultipartFile file) throws IOException {
		// Get excel workbook, worksheet and read the rows
		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
		} catch (IOException ex) {
			return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not read file.");
		}
		XSSFSheet worksheet = workbook.getSheetAt(0);
					
		// Date validation
		Row row = worksheet.getRow(1);
		String date = row.getCell(1).getStringCellValue();
		if (!validationUtility.validateDate(date)) {
			workbook.close();
			return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid date format");
		}
		
		String id, temp = "";
		int marks, temp2 = 0;
		Map<String, Integer> marklist = new HashMap<>();
		int idx = 0;
		
		try {
			while (true) {
				row = worksheet.getRow(idx + EXCEL_HEADER_COUNT);
				
				// ID validation
				id = row.getCell(0).getStringCellValue();
				if (id.isEmpty()) {
					workbook.close();
					break;
				}
				if (!validationUtility.validateStudentID(id)) {
					workbook.close();
					return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid student ID");
				}
				
				// Marks validation
				try {	// Try to read marks as string
					temp = row.getCell(2).getStringCellValue();
				} catch (Exception ex) {	// If that doesn't work, try to read marks as integer
					try {
						temp2 = (int) row.getCell(2).getNumericCellValue();
					} catch (Exception e) {		// If even that doesn't work, give error
						workbook.close();
						return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid marks");
					}
					temp = Integer.toString(temp2);
				}
				try {
					marks = Integer.parseInt(temp);
				} catch (NumberFormatException ex) {
					workbook.close();
					return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid marks");
				}
				
				marklist.put(id, marks);
				idx++;
			}
		} catch (Exception ex) { // Close when file end is encountered
			workbook.close();
		}
		
		workbook.close();
		
		return new SuccessResponse(true, StatusCodes.SUCCESS, "Marklist successfully recorded.");
	}
}
