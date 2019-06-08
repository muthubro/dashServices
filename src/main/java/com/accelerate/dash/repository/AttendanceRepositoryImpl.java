/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: findAttendances does not work
 */


package com.accelerate.dash.repository;

import java.util.List;
import java.util.Map;

import com.accelerate.dash.model.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Attendance> findByRegNoAndDateBetweenInclusive(String reg, String from, String to) {
        Query query = new Query();
        query.addCriteria(Criteria.where("regNo").is(reg).and("date").gte(from).lte(to));
        query.with(new Sort(Sort.Direction.ASC, "date"));
        List<Attendance> result = mongoTemplate.find(query, Attendance.class);
        return result;
    }

    // An attempt at mongodb map reduce (Does not work right now)
    public MapReduceResults<Map> findAttendances(String reg, String from, String to) {
        String map = "function() { emit([this.regNo,this.date], this.mark); }";
       
        String reduce = "function(key, values) { return values.length; }";
        
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
