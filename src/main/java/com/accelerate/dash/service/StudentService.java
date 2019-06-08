/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.accelerate.dash.model.Student;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.StudentsResponse;
import com.accelerate.dash.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ApiResponse getStudents(Optional<Map<String, ?>> map) {
        Map<String, ?> request = new HashMap<>();
        if (map.isPresent()) request = map.get();
        else return new StudentsResponse(true, StatusCodes.SUCCESS, "Students list successfully fetched.", studentRepository.findAll());

        /**
         * Create query from user input
         * 
         * For each entry:
         * Check if the given key is a valid field
         *      If yes, check the type of the value
         *          If string, int, or boolean: use it as it is
         *          If map: Use the key and value as special arguments ($regex, $gt, etc.)
         *      Else ignore
         * 
         */
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
                    return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid query");
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

            else return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid query");

            queryString += ",";
        }
        queryString += "}";

        List<Student> students;
        try {
            students = studentRepository.customQuery(queryString);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid query");
        }
        return new StudentsResponse(true, StatusCodes.SUCCESS, "Students list fetched successfully", students);
    }
}
