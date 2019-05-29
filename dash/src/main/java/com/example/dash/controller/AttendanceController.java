package com.example.dash.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dash.model.Attendance;
import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.AttendanceConfirmationRequest;
import com.example.dash.payload.AttendanceResponse;
import com.example.dash.payload.ErrorResponse;
import com.example.dash.service.AttendanceService;
import com.example.dash.utility.ValidationUtility;

@RestController
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private ValidationUtility validationUtility;

	@GetMapping("/api/attendance")
	public ResponseEntity<ApiResponse> getAttendance(@RequestParam("id") String reg,
										  @RequestParam("from") Optional<String> from,
										  @RequestParam("to") Optional<String> to) {
		List<Attendance> attendances = attendanceService.getAttendance(reg, from, to);
		if (attendances == null) return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "No matching entry found"));
		return ResponseEntity.ok(new AttendanceResponse(true, StatusCodes.SUCCESS, "Attendance fetched successfully", attendances));
	}
	
	@PostMapping("/api/attendance")
	public ResponseEntity<ApiResponse> recordAttendance(@RequestParam("file") MultipartFile[] files) throws IOException {
		List<Attendance> attendances = new ArrayList<>();

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
			int temp2 = 0;
			boolean status;
			int leaveStatus;
			int idx = 0;
			
			try {
				while (true) {
					row = worksheet.getRow(idx + 3);
					
					// ID validation
					id = row.getCell(0).getStringCellValue();
					if (!validationUtility.validateStudentID(id)) {
						workbook.close();
						return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid student ID"));
					}

					// Status validation
					try {	// Try to read status as string
						temp = row.getCell(2).getStringCellValue();
						if (!temp.equals("0") && !temp.equals("1")) {
							workbook.close();
							return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid attendance status"));
						}
					} catch (Exception ex) {	// If that doesn't work, try to read status as integer
						try {
							temp2 = (int) row.getCell(2).getNumericCellValue();
							if (temp2 != 1 && temp2 != 0) {
								workbook.close();
								return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid attendance status"));
							}
						} catch (Exception e) {		// If even that didn't work, give error
							workbook.close();
							return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid attendance status"));
						}
						temp = temp2 == 1 ? "1" : "0";
					}
					status = temp.equals("1") ? true : false;

					// Leave status validation
					try {	// Try to read leave status as string
						temp = row.getCell(3).getStringCellValue();
					} catch (Exception ex) {	// If that doesn't work, try to read leave status as integer
						try {
							temp2 = (int) row.getCell(3).getNumericCellValue();
						} catch (Exception e) {		// If even that doesn't work, give error
							workbook.close();
							return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid leave status"));
						}
						temp = Integer.toString(temp2);
					}
					try {
						leaveStatus = Integer.parseInt(temp);
					} catch (NumberFormatException ex) {
						workbook.close();
						return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid leave status"));
					}
					if (leaveStatus < 0 || leaveStatus > 5) {
						workbook.close();
						return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid leave status"));
					}

					attendances.add(new Attendance(id, date, status, leaveStatus));
					idx++;
				}
			} catch (Exception ex) {
				workbook.close();
			}
		}
		
		return ResponseEntity.ok(new AttendanceResponse(true, StatusCodes.SUCCESS, "Attendance successfully recorded.", attendances));
	}

	@PostMapping("/api/attendance/confirm")
	public ResponseEntity<ApiResponse> confirmAttendance(@RequestBody AttendanceConfirmationRequest request) {
		List<Attendance> attendances = request.getData();
		List<Attendance> newAttendances = new ArrayList<>();

		// Convert date to datestamp
		for (Attendance attendance : attendances) {
			String date = attendance.getDate();
			Pattern regex = Pattern.compile("(\\d\\d)-(\\d\\d)-(\\d\\d\\d\\d)");
			Matcher matcher = regex.matcher(date);

			if (!matcher.find()) {
				return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid date format"));
			}

			String newDate = matcher.group(3) + matcher.group(2) + matcher.group(1);
			attendance.setDate(newDate);

			newAttendances.add(attendance);
		}
		return ResponseEntity.ok(new AttendanceResponse(true, StatusCodes.SUCCESS, "Attendance confirmed", newAttendances));
	}
}
