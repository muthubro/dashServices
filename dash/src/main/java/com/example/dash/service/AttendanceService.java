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

import org.springframework.stereotype.Service;

import com.example.dash.model.Attendance;
import com.example.dash.model.Student;

@Service
public class AttendanceService {

	public List<Attendance> getAttendance(String reg, Optional<String> from, Optional<String> to) {
		List<Student> students = new ArrayList<Student>(Arrays.asList(
				new Student("12A001", "Muathasim Mohamed", new ArrayList<Attendance>(Arrays.asList(
							new Attendance("20190519", true, 0),
							new Attendance("20190520", false, 1)
						))),
				new Student("12A002", "Abhijit CS", new ArrayList<Attendance>(Arrays.asList(
						new Attendance("20190519", false, 4),
						new Attendance("20190520", true, 0)
					)))
			));

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

		int fromStamp = Integer.parseInt(fromDate);
		int toStamp = Integer.parseInt(toDate);

		List<Attendance> attendances = new ArrayList<>();
		for (Student student : students) {
			if (student.getRegNo().equals(reg)) {
				for (Attendance attendance : student.getAttendance()) {
					int temp = Integer.parseInt(attendance.getDate());
					if (temp >= fromStamp && temp <= toStamp) {
						attendances.add(attendance);
					}
				}
				// return student.getAttendance();
			}
		}
		if (attendances.isEmpty()) return null;
		return attendances;
	}
}
