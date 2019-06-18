/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 18 June 2019
 * Modified Date	: 18 June 2019	
 * Comments			: 
 */


// Response to a viewModuleSchedule given a batch
package com.accelerate.dash.payload;

public class ModuleMappingBatchResponse {

    private String dateFrom;

    private String dateTo;

    private String programId;

    private String programName;

    private ModuleMappingResponseData2 batch;

    public ModuleMappingBatchResponse() {}

    public ModuleMappingBatchResponse(String dateFrom, String dateTo, String programId, String programName,
            ModuleMappingResponseData2 batch) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.programId = programId;
        this.programName = programName;
        this.batch = batch;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ModuleMappingResponseData2 getBatch() {
        return batch;
    }

    public void setBatch(ModuleMappingResponseData2 batch) {
        this.batch = batch;
    }
}
