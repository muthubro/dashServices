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

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "polls")
public class Poll {

    @Id
    private String id;

    @NotBlank
    private String pollId;

    private List<PollQuestion> pollContent = new ArrayList<>();

    public Poll() {}

	public Poll(String pollId, List<PollQuestion> pollContent) {
		this.pollId = pollId;
		this.pollContent = pollContent;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public List<PollQuestion> getPollContent() {
        return pollContent;
    }

    public void setPollContent(List<PollQuestion> pollContent) {
        this.pollContent = pollContent;
    }

    public void addQuestion(PollQuestion question) {
        pollContent.add(question);
    }
}
