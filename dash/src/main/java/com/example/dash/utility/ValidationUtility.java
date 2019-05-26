package com.example.dash.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

// Utility used for different validations
@Component
public class ValidationUtility {

    public boolean validateStudentID(String id) {
        String regex = "(\\d\\d)\\w\\d\\d\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        if (!matcher.find()) return false;

        int claz = Integer.parseInt(matcher.group(1));
        if (claz < 1 || claz > 12) return false;

        return true;
    }

    public boolean validateDate(String date) {
        try {
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException ex) {
            return false;
        } 
        return true;
    }

    public boolean validateClass(String cls) {
        String regex = "(\\d\\d)-\\w";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cls);
        
        if (!matcher.find()) return false;

        int claz = Integer.parseInt(matcher.group(1));
        if (claz < 1 || claz > 12) return false;

        return true;
    }
}
