/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import java.util.List;

import com.accelerate.dash.model.Student;

public interface StudentRepositoryCustom {

    public List<Student> customQuery(String query);

    public List<Student> fieldExists(String field);
}
