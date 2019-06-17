/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.accelerate.dash.model.Module;
import com.accelerate.dash.model.ModuleBatchMapping;
import com.accelerate.dash.model.TeacherLog;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ApproveTeacherLogRequest;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.ModuleBatchMappingRequest;
import com.accelerate.dash.payload.ModuleRequest;
import com.accelerate.dash.payload.ModuleStatusModifyRequest;
import com.accelerate.dash.payload.ModuleTimeRequest;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.payload.UnapprovedLogsResponse;
import com.accelerate.dash.payload.UnapprovedLogsResponseUnit;
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
        Module module = new Module(moduleRequest.getModuleId(), 
                                   moduleRequest.getModuleName(), 
                                   moduleRequest.getTimeAllotted(),
                                   moduleRequest.getMaximumTolerance(),
                                   moduleRequest.getTimeUnit());

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

        module.setTimeAllotted(moduleTimeRequest.getTimeAllotted());
        module.setMaximumTolerance(moduleTimeRequest.getMaximumTolerance());

        String timeUnit = moduleTimeRequest.getTimeUnit();
        if (!timeUnit.equals("HOURS") && !timeUnit.equals("DAYS"))
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid time unit.");
        module.setTimeUnit(timeUnit);

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
        List<ModuleBatchMapping> mappings = moduleBatchMappingRepository.findByModuleIdAndBatchIdOrderByPartIndexDesc(module.getId(), batchId);

        ModuleBatchMapping lastMapping = new ModuleBatchMapping();
        if (!mappings.isEmpty()) {
            lastMapping = mappings.get(0);

            repeats = lastMapping.getRepeats();
            partIndex = lastMapping.getPartIndex();
        }
        partIndex++;

        Double max_time = request.getMaxTime();
        if (max_time == null) {
            if (!mappings.isEmpty() && !request.getIsRepeat()) {
                max_time = lastMapping.getMaxTime();
            }
            else {
                max_time = module.getTimeAllotted();
            }
        }

        if (!mappings.isEmpty()) {
            Double sum = 0.0;
            if (!request.getIsRepeat()) {
                for (ModuleBatchMapping mapping : mappings) {
                    if (repeats == mapping.getRepeats())
                        sum += mapping.getProgress();
                }
            }
            max_time -= sum;
        }

        if (request.getIsRepeat() || repeats == -1)
            repeats++;

        Double tolerance = request.getTolerance();
        if (tolerance == null) {
            if (!mappings.isEmpty() && !request.getIsRepeat()) {
                tolerance = lastMapping.getTolerance();
            }
            else {
                tolerance = module.getMaximumTolerance();
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String date = dateFormat.format(now);

        ModuleBatchMapping mapping = new ModuleBatchMapping(module, 
                                                            batchId,
                                                            max_time,
                                                            tolerance,
                                                            request.getTeacherCode(), 
                                                            date,
                                                            request.getAdminCode(),
                                                            request.getRepeatTitle(),
                                                            request.getComments(),
                                                            partIndex, 
                                                            repeats);

        try {
            moduleBatchMappingRepository.save(mapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save mapping.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Module mapped succesfully.");
    }

    public ApiResponse updateModuleMapping(ModuleStatusModifyRequest request) {
        Long entryId = request.getEntryId();
        ModuleBatchMapping mapping = new ModuleBatchMapping();
        try {
            mapping = moduleBatchMappingRepository.findById(entryId).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Module not found");
        }

        Double maxTime;
        Double tolerance;
        Integer status;
        String repeatTitle;
        String comments;
        Boolean isFrozen;

        maxTime = request.getMaxTime();
        tolerance = request.getTolerance();
        repeatTitle = request.getRepeatTitle();
        comments = request.getComments();
        status = request.getStatus();
        isFrozen = request.getIsFrozen();

        if (maxTime == null) {
            maxTime = mapping.getMaxTime();
        }

        if (tolerance == null) {
            tolerance = mapping.getTolerance();
        }

        if (repeatTitle == null) {
            repeatTitle = mapping.getRepeatTitle();
        }

        if (comments == null) {
            comments = mapping.getComments();
        }

        if (status == null) {
            status = mapping.getStatus();
        }

        if (isFrozen == null) {
            isFrozen = mapping.getIsFrozen();
        }

        mapping.setMaxTime(maxTime);
        mapping.setTolerance(tolerance);
        mapping.setStatus(status);
        mapping.setRepeatTitle(repeatTitle);
        mapping.setComments(comments);
        mapping.setIsFrozen(isFrozen);

        try {
            moduleBatchMappingRepository.save(mapping);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save mapping.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Module updated successfully.");
    }

    public ApiResponse getUnapprovedLog() {
        List<TeacherLog> logs = teacherLogRepository.findByIsApprovedFalse();
        Map<Long, ModuleBatchMapping> mappings = new HashMap<>();
        UnapprovedLogsResponse response = new UnapprovedLogsResponse(true, StatusCodes.SUCCESS, "Fetched logs successfully.");
        for (TeacherLog log : logs) {
            Long mappingId = log.getMappingId();
            ModuleBatchMapping mapping = mappings.get(mappingId);
            if (mapping == null) {
                mapping = new ModuleBatchMapping();
                try {
                    mapping = moduleBatchMappingRepository.findById(mappingId).orElseThrow(() -> new Exception());
                } catch (Exception ex) {
                    return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Mapping not found.");
                }

                mappings.put(mappingId, mapping);
            }

            UnapprovedLogsResponseUnit unit = new UnapprovedLogsResponseUnit(log.getId(), 
                                                                             mapping.getBatchId(), 
                                                                             mapping.getModule().getModuleId(), 
                                                                             log.getTeacherCode(), 
                                                                             log.getTeacherName(),
                                                                             log.getDate(), 
                                                                             log.getLoggedDate(), 
                                                                             log.getDuration(), 
                                                                             log.getTimeUnit());

            response.addData(unit);
        }
        return response;
    }

    public ApiResponse approveLog(List<ApproveTeacherLogRequest> requests) {
        Map<Long, ModuleBatchMapping> mappings = new HashMap<>();
        List<ModuleBatchMapping> mappings_list = new ArrayList<>();
        List<TeacherLog> logs = new ArrayList<>();
        List<Long> entries = new ArrayList<>();

        for (ApproveTeacherLogRequest request : requests) {
            entries.add(request.getEntryId());
        }

        logs = teacherLogRepository.findByIdIn(entries);

        if (logs.isEmpty()) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "No valid entry.");
        }

        Integer flag = 0;
        for (int i = 0; i < logs.size(); i++) {
            TeacherLog log = logs.get(i);
            Long mappingId = log.getMappingId();

            ModuleBatchMapping mapping = mappings.get(mappingId);
            if (mapping == null) {
                try {
                    mapping = moduleBatchMappingRepository.findById(mappingId).orElseThrow(() -> new Exception());
                } catch (Exception ex) {
                    flag = 1;
                    continue;
                }
            }

            if (log.isApproved()) {
                flag = 2;
                continue;
            }

            if (mapping.getStatus() == 2)
                continue;

            if (mapping.getStatus() == 0)
                mapping.setStatus(1);

            if (mapping.getStartDate().equals("")) {
                mapping.setStartDate(log.getDate());
            }
            else if (Long.parseLong(mapping.getStartDate()) > Long.parseLong(log.getDate())) {
                mapping.setStartDate(log.getDate());
            }

            if (mapping.getEndDate().equals("")) {
                mapping.setEndDate(log.getDate());
            }
            else if (Long.parseLong(mapping.getEndDate()) < Long.parseLong(log.getDate())) {
                mapping.setEndDate(log.getDate());
            }

            mapping.addProgress(log.getDuration());
            log.setApproved(true);

            mappings.put(mappingId, mapping);
            logs.set(i, log);
        }

        teacherLogRepository.saveAll(logs);

        for (Map.Entry<Long, ModuleBatchMapping> entry : mappings.entrySet()) {
            mappings_list.add(entry.getValue());
        }
        moduleBatchMappingRepository.saveAll(mappings_list);

        if (flag == 1) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "One or more mappings not found.");
        } else if (flag == 2) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "One or more entries are already approved.");
        }
        return new SuccessResponse(true, StatusCodes.SUCCESS, "Logs approved successfully.");
    }
}
