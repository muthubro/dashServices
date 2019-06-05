package com.example.dash.repository;

import java.util.List;

import com.example.dash.model.Homework;

public interface HomeworkRepositoryCustom {

    public List<Homework> findByDateBetweenInclusive(String from, String to);
}
