/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class PollQuestion {

    @NotBlank
    private int questionId;

    @NotBlank
    private String questionTitle;

    private String questionBrief;

    @NotBlank
    private String questionType;

    @NotBlank
    private boolean isCompulsory;

    private List<PollOption> options = new ArrayList<>();

    public PollQuestion() {}

	public PollQuestion(int questionId, String questionTitle, String questionBrief,
			String questionType, boolean isCompulsory, List<PollOption> options) {
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

    public List<PollOption> getOptions() {
        return options;
    }

    public void setOptions(List<PollOption> options) {
        this.options = options;
    }
}
