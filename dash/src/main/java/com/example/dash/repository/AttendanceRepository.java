package com.example.dash.repository;

import com.example.dash.model.Attendance;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<Attendance, String>, AttendanceRepositoryCustom {

    public int countByRegNoAndStatus(String reg, boolean status);
}
