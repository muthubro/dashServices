/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import com.accelerate.dash.model.Homework;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeworkRepository extends MongoRepository<Homework, String>, HomeworkRepositoryCustom {

}
