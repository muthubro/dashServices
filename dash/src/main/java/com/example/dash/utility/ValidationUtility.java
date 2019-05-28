package com.example.dash.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Utility used for different validations
@Component
public class ValidationUtility {

    @Value("${app.validation.dateFormat}")
    private String dateFormat;

    @Value("${app.validation.idFormat}")
    private String idFormat;

    public boolean validateStudentID(String id) {
        idFormat = idFormat.replace("/", "\\");
        String regex = idFormat;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        if (!matcher.find()) return false;

        return true;
    }

    public boolean validateDate(String date) {
        try {
            Date date1 = new SimpleDateFormat(dateFormat).parse(date);
        } catch (ParseException ex) {
            return false;
        } 
        return true;
    }
}
