package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Homework;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface HomeworkRepository extends MongoRepository<Homework, String> {

    @Query("{'date': {$gte: ?0, $lte: ?1}}")
    public List<Homework> findByDateBetween(String from, String to);
}
