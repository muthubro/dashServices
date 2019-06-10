/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

import java.util.List;

// Request format for creating a poll
public class PollRequest {

    private String pollId;

    private List<PollQuestionRequest> pollContent;

    public PollRequest() {}

	public PollRequest(String pollId, List<PollQuestionRequest> pollContent) {
		this.pollId = pollId;
		this.pollContent = pollContent;
	}

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public List<PollQuestionRequest> getPollContent() {
        return pollContent;
    }

    public void setPollContent(List<PollQuestionRequest> pollContent) {
        this.pollContent = pollContent;
    }

    public void addQuestion(PollQuestionRequest question) {
        pollContent.add(question);
    }
}
