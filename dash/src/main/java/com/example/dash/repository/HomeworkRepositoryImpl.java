package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class HomeworkRepositoryImpl implements HomeworkRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Homework> findByDateBetweenInclusive(String from, String to) {
        Query query = new Query();
        query.addCriteria(Criteria.where("date").gte(from).lte(to));
        query.with(new Sort(Sort.Direction.DESC, "date"));
        List<Homework> result = mongoTemplate.find(query, Homework.class);
        return result;
    }
}
