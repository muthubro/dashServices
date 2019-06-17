/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 15 June 2019
 * Modified Date	: 17 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import javax.validation.constraints.NotBlank;

public class ApproveTeacherLogRequest {

    @NotBlank
    private Long entryId;

    public ApproveTeacherLogRequest() {}

    public ApproveTeacherLogRequest(Long entryId) {
        this.entryId = entryId;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }
}
