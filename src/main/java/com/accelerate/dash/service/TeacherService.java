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
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.repository.ModuleBatchMappingRepository;
import com.accelerate.dash.repository.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleBatchMappingRepository mappingRepository;

    public ApiResponse logTime(String batchId, String moduleId, String teacherCode, Integer time) {
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

        lastMapping.setProgress(lastMapping.getProgress() + time);
        if (lastMapping.getStartDate().isEmpty()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            lastMapping.setStartDate(dateFormat.format(date));
        }

        try {
            mappingRepository.save(lastMapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Progress logged successfully.");
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
