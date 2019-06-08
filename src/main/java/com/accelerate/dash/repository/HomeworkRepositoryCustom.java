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

import com.accelerate.dash.model.Homework;

public interface HomeworkRepositoryCustom {

    public List<Homework> findByDateBetweenInclusive(String from, String to);
}
