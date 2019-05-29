package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Student;

public interface StudentRepositoryCustom {

    public List<Student> customQuery(String query);

    public List<Student> fieldExists(String field);
}
