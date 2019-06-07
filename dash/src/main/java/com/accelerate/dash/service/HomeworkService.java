/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accelerate.dash.model.Homework;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.HomeworkResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.repository.HomeworkRepository;
import com.accelerate.dash.utility.MiscUtilities;;

@Service
public class HomeworkService {

    // Allowed file types
	private static final List<String> ALLOWED_TYPES = new ArrayList<String>(Arrays.asList("pdf", "png", "jpg", "jpeg"));

    // Required to set the headers for response
    private static final Map<String, MediaType> MEDIA_TYPE_MAP = Stream.of(new Object[][] {
        {"pdf", MediaType.APPLICATION_PDF},
        {"png", MediaType.IMAGE_PNG},
        {"jpg", MediaType.IMAGE_JPEG},
        {"jpeg", MediaType.IMAGE_JPEG},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (MediaType) data[1]));

    private static final double MAX_FILE_SIZE = 1.0;

    private static final String HOMEWORK_DIR = "D:\\Work\\Dash\\dashServices\\dash\\src\\main\\resources\\homework";

	@Autowired
	private HomeworkRepository homeworkRepository;

	@Autowired
	private MiscUtilities utilities;

	public ApiResponse getHomeworkList(Optional<String> from, Optional<String> to) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");   // Datestamp format

		Date date = new Date();     // Default 'to' date

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date before = cal.getTime();    // Default 'from' date

		String fromDate, toDate;

        // If from and to dates are given, convert to datestamp. Else use default
		if (from.isPresent()) {
			fromDate = from.get();
			fromDate = utilities.convertDateToDatestamp(fromDate);
		}
		else fromDate = dateFormat.format(before);

		if (to.isPresent()) {
			toDate = to.get();
			toDate = utilities.convertDateToDatestamp(toDate);
		}
		else toDate = dateFormat.format(date);

		List<Homework> homeworks = new ArrayList<>();
		homeworks = homeworkRepository.findByDateBetweenInclusive(fromDate, toDate);

		if (homeworks.isEmpty()) return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "No matching entry found");
        return new HomeworkResponse(true, StatusCodes.SUCCESS, "Homework fetched successfully", homeworks);
	}

	public ResponseEntity<?> getHomework(String id) {
        Optional<Homework> homework_temp = homeworkRepository.findById(id);
        if (homework_temp.isEmpty())
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "File not found."));
        Homework homework = homework_temp.get();

        String fileName = homework.getFileName();
        String ext = utilities.getFileExtension(fileName);

        // Read file into byte array
        Path path = Paths.get(HOMEWORK_DIR, id + "." + ext);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
        } catch (Exception ex) {
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not load file."));
        }

        // Set necessary headers
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MEDIA_TYPE_MAP.get(ext));
		headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        return response;
    }

	public ApiResponse addHomework(MultipartFile file, String date) {
        String fileName = file.getOriginalFilename();
        String ext = utilities.getFileExtension(fileName);

        if (!ALLOWED_TYPES.contains(ext))
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "File type not allowed.");

        double fileSize = utilities.bytesToMB(file.getSize());
        if (fileSize > MAX_FILE_SIZE)
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "File is too big.");

        date = utilities.convertDateToDatestamp(date);
        Homework homework = new Homework(fileName, date);
        homework = homeworkRepository.save(homework);

        // Save file with filename as the file ID
        Path path = Paths.get(HOMEWORK_DIR, homework.getId() + "." + ext);
        try {
            file.transferTo(path);
        } catch (Exception ex) {
            homeworkRepository.delete(homework);
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save file.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Homework successfully uploaded.");
	}
	
	public ApiResponse deleteHomework(String id) {
        Optional<Homework> homework_temp = homeworkRepository.findById(id);
        if (homework_temp.isEmpty())
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "File not found.");
        Homework homework = homework_temp.get();

        String fileName = homework.getFileName();
        String ext = utilities.getFileExtension(fileName);

        Path path = Paths.get(HOMEWORK_DIR, id + "." + ext);
        try {
            File file = path.toFile();
            file.delete();
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not delete file.");
        }
        homeworkRepository.delete(homework);

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Homework deleted successfully.");
    }
}
