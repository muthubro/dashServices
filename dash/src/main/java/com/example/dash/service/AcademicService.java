package com.example.dash.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Map<String, String> data = new HashMap<>();
		data.put("name", "Muathasim Mohamed P");
		byte[] array = pdfUtil.createPdf("temp", data);
		
		return array;
	}
}
