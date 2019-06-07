/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: findAttendances does not work
 */


package com.accelerate.dash.repository;

import java.util.List;

import com.accelerate.dash.model.Attendance;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

public interface AttendanceRepositoryCustom {

    public List<Attendance> findByRegNoAndDateBetweenInclusive(String reg, String from, String to);

    public MapReduceResults<?> findAttendances(String reg, String from, String to); // An attempt at mongodb map reduce
}
