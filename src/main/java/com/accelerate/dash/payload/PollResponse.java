/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import com.accelerate.dash.model.Poll;
import com.accelerate.dash.service.StatusCodes;

// Response to a poll GET request
public class PollResponse extends ApiResponse {

    private Poll poll;

    public PollResponse(boolean status, StatusCodes statusCode, String message, Poll poll) {
        super(status, statusCode, message);
        this.poll = poll;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
