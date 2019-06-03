package com.example.dash.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.dash.model.Homework;
import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.ErrorResponse;
import com.example.dash.payload.HomeworkResponse;
import com.example.dash.payload.SuccessResponse;
import com.example.dash.repository.HomeworkRepository;
import com.example.dash.service.HomeworkService;
import com.example.dash.utility.ConversionUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/homework")
@RestController
public class HomeworkController {

    private static final List<String> ALLOWED_TYPES = new ArrayList<String>(Arrays.asList("pdf", "png", "jpg", "jpeg"));

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
    private HomeworkService homeworkService;

    @Autowired
    private ConversionUtility conversionUtility;

    @PostMapping("")
    public ResponseEntity<ApiResponse> addHomework(@RequestParam("file") MultipartFile file, @RequestParam("date") String date) {
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

        if (!ALLOWED_TYPES.contains(ext))
            return ResponseEntity
                    .ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "File type not allowed."));

        double fileSize = bytesToMB(file.getSize());
        if (fileSize > MAX_FILE_SIZE)
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "File is too big."));

        date = conversionUtility.convertDateToDatestamp(date);
        Homework homework = new Homework(fileName, date);
        homework = homeworkRepository.save(homework);

        Path path = Paths.get(HOMEWORK_DIR, homework.getId() + "." + ext);
        try {
            file.transferTo(path);
        } catch (Exception ex) {
            homeworkRepository.delete(homework);
            return ResponseEntity
                    .ok(new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save file."));
        }

        return ResponseEntity.ok(new SuccessResponse(true, StatusCodes.SUCCESS, "Homework successfully uploaded."));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getHomeworkList(@RequestParam("from") Optional<String> from,
                                            @RequestParam("to") Optional<String> to) {
        
        List<Homework> homeworks = homeworkService.getHomework(from, to);
        if (homeworks == null) return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "No matching entry found"));
        return ResponseEntity.ok(new HomeworkResponse(true, StatusCodes.SUCCESS, "Homework fetched successfully", homeworks));
    }

    @GetMapping("")
    public ResponseEntity<?> getHomework(@RequestParam("id") String id) {
        Optional<Homework> homework_temp = homeworkRepository.findById(id);
        if (homework_temp.isEmpty())
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "File not found."));
        Homework homework = homework_temp.get();

        String fileName = homework.getFileName();
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

        Path path = Paths.get(HOMEWORK_DIR, id + "." + ext);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
        } catch (Exception ex) {
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not load file."));
        }

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MEDIA_TYPE_MAP.get(ext));
		headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/delete")
    public ResponseEntity<ApiResponse> deleteHomework(@RequestParam("id") String id) {
        Optional<Homework> homework_temp = homeworkRepository.findById(id);
        if (homework_temp.isEmpty())
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "File not found."));
        Homework homework = homework_temp.get();

        String fileName = homework.getFileName();
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

        Path path = Paths.get(HOMEWORK_DIR, id + "." + ext);
        try {
            File file = path.toFile();
            file.delete();
        } catch (Exception ex) {
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not delete file."));
        }
        homeworkRepository.delete(homework);

        return ResponseEntity.ok(new SuccessResponse(true, StatusCodes.SUCCESS, "Homework deleted successfully."));
    }

    private double bytesToMB(long bytes) {
        return ((double) bytes / (1024 * 1024));
    }
}
