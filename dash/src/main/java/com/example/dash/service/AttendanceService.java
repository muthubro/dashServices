package com.example.dash.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dash.model.Attendance;
import com.example.dash.model.Student;

@Service
public class AttendanceService {
	
	public List<Attendance> getAttendance(String reg, Optional<Date> from, Optional<Date> to) {
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 05, 19);
		Date date1 = cal.getTime();
		cal.set(2019, 05, 20);
		Date date2 = cal.getTime();
		List<Student> students = new ArrayList<Student>(Arrays.asList(
				new Student("12A001", "Muathasim Mohamed", new ArrayList<Attendance>(Arrays.asList(
							new Attendance(date1, true),
							new Attendance(date2, false)
						))),
				new Student("12A002", "Abhijit CS", new ArrayList<Attendance>(Arrays.asList(
						new Attendance(date1, false),
						new Attendance(date2, true)
					)))
			));

		List<Attendance> attendances = new ArrayList<>();
		for (Student student : students) {
			if (student.getRegNo().equals(reg)) {
				attendances = student.getAttendance();
				break;
			}
		}
		if (attendances.isEmpty()) return null;
		
		return attendances;
	}
}
