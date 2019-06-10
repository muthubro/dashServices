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

// Question structure in a poll request
public class PollQuestionRequest {

    private int questionId;

    private String questionTitle;

    private String questionBrief;

    private String questionType;

    private boolean isCompulsory;

    private List<PollOptionRequest> options;

    public PollQuestionRequest() {}

	public PollQuestionRequest(int questionId, String questionTitle, String questionBrief, String questionType,
			boolean isCompulsory, List<PollOptionRequest> options) {
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionBrief = questionBrief;
		this.questionType = questionType;
		this.isCompulsory = isCompulsory;
		this.options = options;
	}

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionBrief() {
        return questionBrief;
    }

    public void setQuestionBrief(String questionBrief) {
        this.questionBrief = questionBrief;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public boolean isCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(boolean isCompulsory) {
        this.isCompulsory = isCompulsory;
    }

    public List<PollOptionRequest> getOptions() {
        return options;
    }

    public void setOptions(List<PollOptionRequest> options) {
        this.options = options;
    }
}
