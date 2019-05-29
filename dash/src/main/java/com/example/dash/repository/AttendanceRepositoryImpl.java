package com.example.dash.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MapReduceResults<Map> findAttendances(String reg, String from, String to) {
        String map = "function() {" +
                    "   emit(this.regNo, this.date);" +
                    "}";
        String reduce = "function(key, values) {return values.length;}";
        
        Query filter = Query.query(Criteria.where("date").gte(from).lte(to));
        MapReduceResults<Map> results;
        try {
        results = mongoTemplate.mapReduce("attendance", map, reduce, Map.class);
        } catch (Exception ex) {
            return null;
        }

        return results;
    }
}
