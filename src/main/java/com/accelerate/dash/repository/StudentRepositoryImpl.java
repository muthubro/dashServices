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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> customQuery(String query) {
        BasicQuery basicQuery = new BasicQuery(query);
        List<Student> result = mongoTemplate.find(basicQuery, Student.class);
        return result;
    }

    public List<Student> fieldExists(String field) {
        String queryString = "{'" + field + "': {$exists: true, $ne: null}}";
        BasicQuery query = new BasicQuery(queryString);
        List<Student> result = mongoTemplate.find(query, Student.class);
        return result;
    }
}
