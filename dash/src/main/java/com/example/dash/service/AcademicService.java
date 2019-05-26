package com.example.dash.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dash.model.Attendance;
import com.example.dash.model.Student;
import com.example.dash.utility.PdfGenerationUtility;

@Service
public class AcademicService {
	
	@Autowired
	private PdfGenerationUtility pdfUtil;

	public Map<String, Integer> getMarklist(String reg) {
		Map<String, Integer> marklist = new HashMap<>();
		marklist.put("Physics", 100);
		marklist.put("Chemistry", 100);
		marklist.put("Mathematics", 100);
		marklist.put("Biology", 100);
		
		return marklist;
	}
	
	public byte[] getAcademicReport(String reg) {
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

		Map<String, Object> data = new HashMap<>();
		data.put("name", reg);
		data.put("students", students);
		byte[] array = pdfUtil.createPdf("temp", data);
		
		return array;
	}
}
