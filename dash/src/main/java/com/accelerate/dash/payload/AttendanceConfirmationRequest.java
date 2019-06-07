/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import java.util.List;

import com.accelerate.dash.model.Attendance;

// Request format for attendance confirmation
public class AttendanceConfirmationRequest {

    private List<Attendance> data;

    public AttendanceConfirmationRequest() {}

    public AttendanceConfirmationRequest(List<Attendance> data) {
        this.data = data;
    }

    public List<Attendance> getData() {
        return data;
    }

    public void setData(List<Attendance> data) {
        this.data = data;
    }
}
