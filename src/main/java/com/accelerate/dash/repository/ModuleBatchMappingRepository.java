/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import java.util.List;
import java.util.Optional;

import com.accelerate.dash.model.ModuleBatchMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleBatchMappingRepository extends JpaRepository<ModuleBatchMapping, Long> {

    Optional<ModuleBatchMapping> findById(Long id);

    List<ModuleBatchMapping> findByBatchIdIn(List<String> batchId);

    List<ModuleBatchMapping> findByModuleIdAndBatchIdOrderByPartIndexDesc(Long moduleId, String batchId);

    List<ModuleBatchMapping> findByModuleIdAndBatchIdAndTeacherCode(Long moduleId, String batchId, String teacherCode);
}
