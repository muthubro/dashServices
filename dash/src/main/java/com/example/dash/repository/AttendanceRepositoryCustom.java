package com.example.dash.repository;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

public interface AttendanceRepositoryCustom {

    public MapReduceResults<?> findAttendances(String reg, String from, String to);
}
