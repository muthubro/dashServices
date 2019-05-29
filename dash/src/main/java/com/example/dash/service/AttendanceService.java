package com.example.dash.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dash.model.Attendance;
import com.example.dash.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	public List<Attendance> getAttendance(String reg, Optional<String> from, Optional<String> to) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -31);
		Date before = cal.getTime();

		Pattern regex = Pattern.compile("(\\d\\d)-(\\d\\d)-(\\d\\d\\d\\d)");
		String fromDate, toDate;

		if (from.isPresent()) {
			fromDate = from.get();
			Matcher matcher = regex.matcher(fromDate);
			
			if (!matcher.find()) return null;

			fromDate = matcher.group(3) + matcher.group(2) + matcher.group(1);
		}
		else fromDate = dateFormat.format(before);

		if (to.isPresent()) {
			toDate = to.get();
			Matcher matcher = regex.matcher(toDate);
			
			if (!matcher.find()) return null;

			toDate = matcher.group(3) + matcher.group(2) + matcher.group(1);
		}
		else toDate = dateFormat.format(date);

		List<Attendance> attendances = new ArrayList<>();
		attendances = attendanceRepository.findByRegNoBetween(reg, fromDate, toDate);

		if (attendances.isEmpty()) return null;
		return attendances;
	}
}
