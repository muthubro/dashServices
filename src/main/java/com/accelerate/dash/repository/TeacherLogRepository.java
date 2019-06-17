/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import java.util.List;

import com.accelerate.dash.model.TeacherLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherLogRepository extends JpaRepository<TeacherLog, Long> {

    public List<TeacherLog> findByTeacherCode(String teacherCode);
    
    public List<TeacherLog> findByIdIn(List<Long> ids);

    public List<TeacherLog> findByIsApprovedFalse();
}
