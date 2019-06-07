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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accelerate.dash.model.Attendance;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.AttendanceResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.repository.AttendanceRepository;
import com.accelerate.dash.utility.MiscUtilities;
import com.accelerate.dash.utility.ValidationUtility;;

@Service
public class AttendanceService {

	private static final int EXCEL_HEADER_COUNT = 3;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private ValidationUtility validationUtility;

	@Autowired
	private MiscUtilities utilities;

	public ApiResponse getAttendance(String reg, Optional<String> from, Optional<String> to) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");	// Datestamp format

		Date date = new Date();		// Default 'to' date

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date before = cal.getTime();	// Default 'from' date (beginning of month)

		String fromDate, toDate;

		// If from and to dates are given, convert to datestamp. Else use default
		if (from.isPresent()) {
			fromDate = from.get();
			fromDate = utilities.convertDateToDatestamp(fromDate);
		}
		else fromDate = dateFormat.format(before);

		if (to.isPresent()) {
			toDate = to.get();
			toDate = utilities.convertDateToDatestamp(toDate);
		}
		else toDate = dateFormat.format(date);

		List<Attendance> attendances = new ArrayList<>();
		attendances = attendanceRepository.findByRegNoAndDateBetweenInclusive(reg, fromDate, toDate);

		if (attendances.isEmpty()) return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "No matching entry found");
		return new AttendanceResponse(true, StatusCodes.SUCCESS, "Attendance fetched successfully", attendances);
	}

	public ApiResponse recordAttendance(MultipartFile file) throws IOException {
		List<Attendance> attendances = new ArrayList<>();

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
		int temp2 = 0;
		boolean status;
		int leaveStatus;
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

				// Status validation
				try {	// Try to read status as string
					temp = row.getCell(2).getStringCellValue();
					if (!temp.equals("0") && !temp.equals("1")) {
						workbook.close();
						return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid attendance status");
					}
				} catch (Exception ex) {	// If that doesn't work, try to read status as integer
					try {
						temp2 = (int) row.getCell(2).getNumericCellValue();
						if (temp2 != 1 && temp2 != 0) {
							workbook.close();
							return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid attendance status");
						}
					} catch (Exception e) {		// If even that didn't work, give error
						workbook.close();
						return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid attendance status");
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
						return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid leave status");
					}
					temp = Integer.toString(temp2);
				}
				try {
					leaveStatus = Integer.parseInt(temp);
				} catch (NumberFormatException ex) {
					workbook.close();
					return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid leave status");
				}
				if (leaveStatus < 0 || leaveStatus > 5) {
					workbook.close();
					return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid leave status");
				}

				attendances.add(new Attendance(id, date, status, leaveStatus));
				idx++;
			}
		} catch (Exception ex) { // Close when file end is encountered
			workbook.close();
		}
		
		return new AttendanceResponse(true, StatusCodes.SUCCESS, "Attendance successfully recorded.", attendances);
	}

	public ApiResponse confirmAttendance(List<Attendance> attendances) {
		// Convert dates to datestamps
		for (Attendance attendance : attendances) {
			String date = attendance.getDate();
			date = utilities.convertDateToDatestamp(date);

			if (date == null) {
				return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid date format");
			}

			attendance.setDate(date);
			attendanceRepository.save(attendance);
		}
		return new SuccessResponse(true, StatusCodes.SUCCESS, "Attendance confirmed");
	}
}
