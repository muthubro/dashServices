package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Attendance;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

public interface AttendanceRepositoryCustom {

    public List<Attendance> findByRegNoAndDateBetweenInclusive(String reg, String from, String to);

    public MapReduceResults<?> findAttendances(String reg, String from, String to); // An attempt at mongodb map reduce
}
