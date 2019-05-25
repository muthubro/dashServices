package com.example.dash.model;

import java.util.List;

public class Student {

	private String regNo;
	private String name;
	private List<Attendance> attendance;
	
	public Student(String regNo, String name, List<Attendance> attendance) {
		this.regNo = regNo;
		this.name = name;
		this.attendance = attendance;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
}
