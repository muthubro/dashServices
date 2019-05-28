package com.example.dash.payload;

import java.util.List;

import com.example.dash.model.Attendance;

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
