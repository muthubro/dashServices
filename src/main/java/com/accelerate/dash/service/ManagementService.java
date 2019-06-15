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
import com.accelerate.dash.model.TimeUnit;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ApproveTeacherLogRequest;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.ModuleBatchMappingRequest;
import com.accelerate.dash.payload.ModuleRequest;
import com.accelerate.dash.payload.ModuleStatusModifyRequest;
import com.accelerate.dash.payload.ModuleTimeRequest;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.payload.TeacherLogResponse;
import com.accelerate.dash.repository.ModuleBatchMappingRepository;
import com.accelerate.dash.repository.ModuleRepository;
import com.accelerate.dash.repository.TeacherLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleBatchMappingRepository moduleBatchMappingRepository;

    @Autowired
    private TeacherLogRepository teacherLogRepository;

    public ApiResponse addModule(ModuleRequest moduleRequest) {
        String unit = moduleRequest.getTimeUnit();
        Integer time_allotted = moduleRequest.getTimeAllotted();
        Integer maximum_tolerance = moduleRequest.getMaximumTolerance();

        TimeUnit timeUnit = TimeUnit.valueOf(unit);
        Module module = new Module(moduleRequest.getModuleId(), 
                                   moduleRequest.getModuleName(), 
                                   time_allotted, 
                                   maximum_tolerance, 
                                   timeUnit);

        try {
            moduleRepository.save(module);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Saved module successfully.");
    }

    public ApiResponse setTimeForModule(ModuleTimeRequest moduleTimeRequest) {
        Module module = new Module();
        try {
            module = moduleRepository.findByModuleId(moduleTimeRequest.getModuleId()).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found.");
        }

        TimeUnit timeUnit = TimeUnit.valueOf(moduleTimeRequest.getTimeUnit());
        module.setTimeAllotted(moduleTimeRequest.getTimeAllotted());
        module.setMaximumTolerance(moduleTimeRequest.getMaximumTolerance());
        module.setTimeUnit(timeUnit.getValue());

        try {
            moduleRepository.save(module);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Time set successfully.");
    }

    public ApiResponse mapModuleToBatch(ModuleBatchMappingRequest request) {
        String moduleId = request.getModuleId();
        Module module = new Module();
        try {
            module = moduleRepository.findByModuleId(moduleId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found");
        }

        int partIndex = -1;
        int repeats = -1;

        String batchId = request.getBatchId();
        List<ModuleBatchMapping> mappings = moduleBatchMappingRepository.findByModuleIdAndBatchId(module.getId(), batchId);

        ModuleBatchMapping lastMapping = new ModuleBatchMapping();
        if (!mappings.isEmpty()) {
            for (ModuleBatchMapping mapping : mappings) {
                if (mapping.getRepeats() > repeats)
                    repeats = mapping.getRepeats();
            }
            
            for (ModuleBatchMapping mapping : mappings) {
                if (mapping.getPartIndex() > partIndex && mapping.getRepeats() == repeats){
                    partIndex = mapping.getPartIndex();
                    lastMapping = mapping;
                }
            }
            
            if (request.isRepeat()) partIndex = -1;
        }
        partIndex++;

        int max_time;
        try {
            max_time = request.getMaxTime();
        } catch(Exception ex) {
            max_time = module.getTimeAllotted();
        }

        if (!mappings.isEmpty()) {
            int sum = 0;
            if (!request.isRepeat()) {
                for (ModuleBatchMapping mapping : mappings) {
                    if (repeats == mapping.getRepeats())
                        sum += mapping.getProgress();
                }
            }
            max_time -= sum;
        }

        if (request.isRepeat() || repeats == -1)
            repeats++;

        int tolerance;
        try {
            tolerance = request.getTolerance();
        } catch(Exception ex) {
            if (!mappings.isEmpty())
                tolerance = lastMapping.getTolerance();
            else
                tolerance = module.getMaximumTolerance();
        }

        ModuleBatchMapping mapping = new ModuleBatchMapping(module, 
                                                            batchId,
                                                            max_time,
                                                            tolerance,
                                                            request.getTeacherCode(), 
                                                            partIndex, 
                                                            repeats);

        try {
            moduleBatchMappingRepository.save(mapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save mapping.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Module mapped succesfully.");
    }

    public ApiResponse changeModuleStatus(ModuleStatusModifyRequest request) {
        String moduleId = request.getModuleId();
        Module module = new Module();
        try {
            module = moduleRepository.findByModuleId(moduleId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found");
        }

        List<ModuleBatchMapping> mappings = moduleBatchMappingRepository.findByModuleIdAndBatchId(module.getId(), request.getBatchId());
        ModuleBatchMapping lastMapping = getLastMapping(mappings);

        Integer maxTime;
        Integer tolerance;
        Integer status;

        try {
            maxTime = request.getMaxTime();
        } catch (Exception ex) {
            maxTime = lastMapping.getMaxTime();
        }

        try {
            tolerance = request.getTolerance();
        } catch (Exception ex) {
            tolerance = lastMapping.getTolerance();
        }

        try {
            status = request.getStatus();
        } catch (Exception ex) {
            status = lastMapping.getStatus();
        }

        lastMapping.setMaxTime(maxTime);
        lastMapping.setTolerance(tolerance);
        lastMapping.setStatus(status);

        try {
            moduleBatchMappingRepository.save(lastMapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save mapping.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Module updated successfully.");
    }

    public ApiResponse approveLog(List<ApproveTeacherLogRequest> requests) {
        for (ApproveTeacherLogRequest request : requests) {
            String batchId = request.getBatchId();
            String moduleId = request.getModuleId();
            String teacherCode = request.getTeacherCode();
            String date = request.getDate();

            Module module = new Module();
            try {
                module = moduleRepository.findByModuleId(moduleId).orElseThrow(() -> new Exception());
            } catch (Exception ex) {
                return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found.");
            }

            TeacherLog log = new TeacherLog();
            try {
                log = teacherLogRepository.findByTeacherCodeAndDateAndBatchIdAndModuleId(teacherCode, 
                                                                                        date,
                                                                                        batchId,
                                                                                        moduleId)
                                                                    .orElseThrow(() -> new Exception());
            } catch (Exception ex) {
                return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Log entry not found.");
            }
            int time = log.getTime();

            log.setApproved(true);
            try {
                teacherLogRepository.save(log);
            } catch (Exception ex) {
                return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save log entry.");
            }

            List<ModuleBatchMapping> mappings = moduleBatchMappingRepository.findByModuleIdAndBatchIdAndTeacherCode(module.getId(), batchId, teacherCode);

            if (mappings.isEmpty())
                return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Mapping not found.");

            ModuleBatchMapping lastMapping = getLastMapping(mappings);

            if (lastMapping.getStatus() == 2)
                return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Module has already been completed.");

            lastMapping.setProgress(lastMapping.getProgress() + time);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date now = new Date();
            String formattedDate = dateFormat.format(now);
            if (lastMapping.getStartDate().isEmpty()) {
                lastMapping.setStartDate(formattedDate);
            }

            try {
                moduleBatchMappingRepository.save(lastMapping);
            } catch (Exception ex) {
                return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save module.");
            }
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Logs approved successfully.");
    }

    public TeacherLogResponse getUnapprovedLog() {
        List<TeacherLog> log = teacherLogRepository.findByIsApprovedFalse();
        return new TeacherLogResponse(true, StatusCodes.SUCCESS, "Logs fetched successfully.", log);
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
