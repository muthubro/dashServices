/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import java.util.List;

import com.accelerate.dash.model.TeacherLog;
import com.accelerate.dash.service.StatusCodes;

public class TeacherLogResponse extends ApiResponse {

    private List<TeacherLog> log;

    public TeacherLogResponse(boolean status, StatusCodes statusCode, String message, List<TeacherLog> log) {
        super(status, statusCode, message);
        this.log = log;
    }

    public List<TeacherLog> getLog() {
        return log;
    }

    public void setLog(List<TeacherLog> log) {
        this.log = log;
    }
}
