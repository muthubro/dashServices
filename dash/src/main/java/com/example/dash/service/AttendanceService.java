package com.example.dash.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dash.model.Attendance;
import com.example.dash.repository.AttendanceRepository;
import com.example.dash.utility.ConversionUtility;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private ConversionUtility conversionUtility;

	public List<Attendance> getAttendance(String reg, Optional<String> from, Optional<String> to) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -31);
		Date before = cal.getTime();

		String fromDate, toDate;

		if (from.isPresent()) {
			fromDate = from.get();
			fromDate = conversionUtility.convertDateToDatestamp(fromDate);
		}
		else fromDate = dateFormat.format(before);

		if (to.isPresent()) {
			toDate = to.get();
			toDate = conversionUtility.convertDateToDatestamp(toDate);
		}
		else toDate = dateFormat.format(date);

		List<Attendance> attendances = new ArrayList<>();
		attendances = attendanceRepository.findByRegNoAndDateBetween(reg, fromDate, toDate);

		if (attendances.isEmpty()) return null;
		return attendances;
	}
}
