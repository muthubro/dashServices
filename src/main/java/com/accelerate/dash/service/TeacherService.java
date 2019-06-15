/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.accelerate.dash.model.Module;
import com.accelerate.dash.model.ModuleBatchMapping;
import com.accelerate.dash.model.TeacherLog;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.payload.TeacherLogResponse;
import com.accelerate.dash.repository.ModuleBatchMappingRepository;
import com.accelerate.dash.repository.ModuleRepository;
import com.accelerate.dash.repository.TeacherLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleBatchMappingRepository mappingRepository;

    @Autowired
    private TeacherLogRepository teacherLogRepository;

    public ApiResponse logTime(String batchId, String moduleId, String teacherCode, Integer time) {

        Module module = new Module();
        try {
            module = moduleRepository.findByModuleId(moduleId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found.");
        }

        List<ModuleBatchMapping> mappings = mappingRepository.findByModuleIdAndBatchIdAndTeacherCode(module.getId(), batchId, teacherCode);
        if (mappings.isEmpty())
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not mapped to teacher.");

        List<ModuleBatchMapping> fullMappings = mappingRepository.findByModuleIdAndBatchId(module.getId(), batchId);

        // If the teacher isn't currently teaching the course, report it
        int repeat1 = -1;
        for (ModuleBatchMapping mapping : mappings) {
            if (mapping.getRepeats() > repeat1)
                repeat1 = mapping.getRepeats();
        }

        int repeat2 = -1;
        for (ModuleBatchMapping mapping : fullMappings) {
            if (mapping.getRepeats() > repeat2)
                repeat2 = mapping.getRepeats();
        }

        if (repeat1 != repeat2)
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module has been handed over.");

        int partIndex1 = -1;
        for (ModuleBatchMapping mapping : mappings) {
            if (mapping.getPartIndex() > partIndex1 && mapping.getRepeats() == repeat1)
                partIndex1 = mapping.getPartIndex();
        }

        int partIndex2 = -1;
        for (ModuleBatchMapping mapping : mappings) {
            if (mapping.getPartIndex() > partIndex2 && mapping.getRepeats() == repeat2)
                partIndex2 = mapping.getPartIndex();
        }

        if (repeat1 != repeat2)
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module has been handed over.");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String formattedDate = dateFormat.format(date);

        TeacherLog teacherLog = new TeacherLog(teacherCode, formattedDate, time, batchId, moduleId, false);
        try {
            teacherLogRepository.save(teacherLog);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Progress logged successfully.");
    }

    public ApiResponse getLog(String teacherCode) {
        List<TeacherLog> log = teacherLogRepository.findByTeacherCode(teacherCode);
        return new TeacherLogResponse(true, StatusCodes.SUCCESS, "Successfully fetched teacher log.", log);
    }

    public ApiResponse markAsCompleted(String batchId, String moduleId, String teacherCode) {
        Module module = new Module();
        try {
            module = moduleRepository.findByModuleId(moduleId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found.");
        }
        
        List<ModuleBatchMapping> mappings = mappingRepository.findByModuleIdAndBatchIdAndTeacherCode(module.getId(), batchId, teacherCode);

        if (mappings.isEmpty())
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Mapping not found.");

        ModuleBatchMapping lastMapping = getLastMapping(mappings);

        if (lastMapping.getStatus() == 2)
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Module has already been completed.");

        lastMapping.setStatus(2);
        lastMapping.setCompleteAcknowledged(true);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        lastMapping.setEndDate(dateFormat.format(date));

        try {
            mappingRepository.save(lastMapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Successfully marked as completed.");
    }

    private ModuleBatchMapping getLastMapping(List<ModuleBatchMapping> mappings) {
        ModuleBatchMapping lastMapping = new ModuleBatchMapping();
        int max = -1;

        int repeat = 0;
        for (ModuleBatchMapping mapping : mappings) {
            if (mapping.getRepeats() > repeat)
                repeat = mapping.getRepeats();
        }

        for (ModuleBatchMapping mapping : mappings) {
            if (mapping.getPartIndex() > max && mapping.getRepeats() == repeat) {
                max = mapping.getPartIndex();
                lastMapping = mapping;
            }
        }

        return lastMapping;
    }
}
