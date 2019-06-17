/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import java.util.Optional;

import com.accelerate.dash.model.Attendance;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<Attendance, String>, AttendanceRepositoryCustom {

    public Optional<Attendance> findByRegNoAndDate(String reg, String date);

    public int countByRegNoAndStatus(String reg, boolean status);
}
