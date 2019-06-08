/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

// All the utilities that don't have a class of their own
@Component
public class MiscUtilities {

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

    public double bytesToMB(long bytes) {
        return ((double) bytes / (1024 * 1024));
    }

    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
