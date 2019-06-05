package com.example.dash.repository;

import com.example.dash.model.Homework;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeworkRepository extends MongoRepository<Homework, String>, HomeworkRepositoryCustom {

}
