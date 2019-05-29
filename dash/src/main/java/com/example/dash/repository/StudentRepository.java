package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>, StudentRepositoryCustom {

    public List<Student> findByName(String name);
}
