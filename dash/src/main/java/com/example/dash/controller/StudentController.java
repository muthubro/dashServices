package com.example.dash.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.dash.model.Student;
import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.ErrorResponse;
import com.example.dash.payload.StudentsResponse;
import com.example.dash.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/api/students")
    public ResponseEntity<ApiResponse> getStudents(@RequestBody Optional<Map<String, ?>> map) {
        Map<String, ?> request = new HashMap<>();
        if (map.isPresent()) request = map.get();
        else return ResponseEntity.ok(new StudentsResponse(true, StatusCodes.SUCCESS, "Students list successfully fetched.", studentRepository.findAll()));

        ObjectMapper mapper = new ObjectMapper();
        String queryString = "{";

    	for (Map.Entry<String, ?> entry : request.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            List<Student> check = studentRepository.fieldExists(key);
            if (check.isEmpty()) continue;

            queryString += "'" + key + "': ";

            if (value instanceof String)
                queryString += "'" + value.toString() + "'";

            else if (value instanceof Boolean || value instanceof Integer)
                queryString += value.toString();

            else if (value instanceof Map) {
                Map<String, String> obj;
                try {
                    obj = mapper.convertValue(value, Map.class);
                } catch (IllegalArgumentException ex) {
                    return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid query"));
                }
                queryString += "{";
                for (Map.Entry<String, String> ent : obj.entrySet()) {
                    queryString += "$" + ent.getKey().toString() + ":";
                    
                    Object val = ent.getValue();
                    
                    if (val instanceof String)
                        queryString += "'" + ent.getValue().toString() + "'";

                    else if (val instanceof Boolean || val instanceof Integer)
                        queryString += ent.getValue().toString();
                        
                    queryString += ",";
                }
                queryString += "}";
            }
            queryString += ",";
        }
        queryString += "}";

        List<Student> students;
        try {
            students = studentRepository.customQuery(queryString);
        } catch (Exception ex) {
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid query"));
        }
        return ResponseEntity.ok(new StudentsResponse(true, StatusCodes.SUCCESS, "Students list fetched successfully", students));
    }
}
