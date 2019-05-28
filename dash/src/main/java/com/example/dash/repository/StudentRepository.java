package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.StudentModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentModel, String> {

    public List<StudentModel> findByName(String name);
}
