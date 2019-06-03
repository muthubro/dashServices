package com.example.dash.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ConversionUtility {

    public String convertDateToDatestamp(String date) {
        Pattern regex = Pattern.compile("^(\\d\\d)-(\\d\\d)-(\\d\\d\\d\\d)$");
        Matcher matcher = regex.matcher(date);
        if (!matcher.find()) return null;
        date = matcher.group(3) + matcher.group(2) + matcher.group(1);
        return date;
    }

    public String convertDatestampToDate(String datestamp) {
        Pattern regex = Pattern.compile("^(\\d\\d\\d\\d)(\\d\\d)(\\d\\d)$");
        Matcher matcher = regex.matcher(datestamp);
        if (!matcher.find()) return null;
        datestamp = matcher.group(3) + "-" + matcher.group(2) + "-" + matcher.group(1);
        return datestamp;
    }
}
