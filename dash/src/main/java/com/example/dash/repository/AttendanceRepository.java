package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Attendance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AttendanceRepository extends MongoRepository<Attendance, String>, AttendanceRepositoryCustom {

    @Query("{'regNo': ?0, 'date': {$gte: ?1, $lte: ?2}}")
    public List<Attendance> findByRegNoBetween(String reg, String from, String to);

    public int countByRegNoAndStatus(String reg, boolean status);
}
