/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.accelerate.dash.model.ModuleBatchMapping;
import com.accelerate.dash.model.TeacherLog;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.payload.TeacherLogRequest;
import com.accelerate.dash.payload.TeacherLogResponse;
import com.accelerate.dash.payload.TeacherLogResponseUnit;
import com.accelerate.dash.repository.ModuleBatchMappingRepository;
import com.accelerate.dash.repository.TeacherLogRepository;
import com.accelerate.dash.utility.MiscUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private ModuleBatchMappingRepository mappingRepository;

    @Autowired
    private TeacherLogRepository teacherLogRepository;

    @Autowired
    private MiscUtilities utilities;

    public ApiResponse logTime(TeacherLogRequest request) {
        Long entryId = request.getEntryId();
        
        ModuleBatchMapping mapping = new ModuleBatchMapping();
        try {
            mapping = mappingRepository.findById(entryId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Mapping not found.");
        }

        if (mapping.getIsFrozen()) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Mapping has been frozen.");
        }

        if (mapping.getStatus() == 2) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Module has already been completed.");
        }

        String today = utilities.getCurrentDatestamp();
        String teacherName = "Prof. Abhijit CS";
        TeacherLog teacherLog = new TeacherLog(mapping.getId(), 
                                                mapping.getTeacherCode(), 
                                                teacherName, 
                                                request.getDate(),
                                                today, 
                                                request.getDuration(), 
                                                request.getTimeUnit(), 
                                                false);
        try {
            teacherLogRepository.save(teacherLog);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save log.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Progress logged successfully.");
    }

    public ApiResponse getLog(String teacherCode) {
        List<TeacherLog> logs = teacherLogRepository.findByTeacherCode(teacherCode);
        Map<Long, String> batchIds = new HashMap<>();
        Map<Long, String> moduleIds = new HashMap<>();
        TeacherLogResponse response = new TeacherLogResponse(true, StatusCodes.SUCCESS, "Fetched logs successfully.");
        for (TeacherLog log : logs) {
            Long mappingId = log.getMappingId();
            String moduleId;
            String batchId = batchIds.get(mappingId);
            if (batchId == null) {
                ModuleBatchMapping mapping = new ModuleBatchMapping();
                try {
                    mapping = mappingRepository.findById(mappingId).orElseThrow(() -> new Exception());
                } catch (Exception ex) {
                    return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Mapping not found.");
                }

                batchId = mapping.getBatchId();
                moduleId = mapping.getModule().getModuleId();

                batchIds.put(mappingId, batchId);
                moduleIds.put(mappingId, moduleId);
            } else {
                moduleId = moduleIds.get(log.getMappingId());
            }

            TeacherLogResponseUnit unit = new TeacherLogResponseUnit(batchId, moduleId, log.getDate(),
                    log.getDuration(), log.isApproved());

            response.addData(unit);
        }
        return response;
    }

    public ApiResponse markAsCompleted(String id, String teacherCode) {
        Long mappingId;
        try {
            mappingId = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid mapping id.");
        }

        ModuleBatchMapping mapping = new ModuleBatchMapping();
        try {
            mapping = mappingRepository.findById(mappingId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Mapping not found.");
        }

        if (!mapping.getTeacherCode().equals(teacherCode)) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "You are not allowed to close this course.");
        }

        if (mapping.getIsFrozen()) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Module has been frozen.");
        }

        if (mapping.getStatus() == 2)
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Module has already been completed.");

        Double completion = ((Double) mapping.getProgress()) / mapping.getMaxTime();
        if (completion < 0.7)
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Module has less than 70% progress.");

        mapping.setStatus(2);
        mapping.setCompleteAcknowledged(true);
        
        try {
            mappingRepository.save(mapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Successfully marked as completed.");
    }
}
