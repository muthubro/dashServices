/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 15 June 2019	
 * Comments			: 
 */

package com.accelerate.dash.service;

import java.util.stream.Collectors;

import com.accelerate.dash.model.Poll;
import com.accelerate.dash.model.PollOption;
import com.accelerate.dash.model.PollQuestion;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.PollQuestionRequest;
import com.accelerate.dash.payload.PollRequest;
import com.accelerate.dash.payload.PollResponse;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.repository.PollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public ApiResponse getPoll(String id) {
        Poll poll;
        try {
            poll = pollRepository.findByPollId(id).orElseThrow(() -> new Exception());
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "Poll not found.");
        }

        return new PollResponse(true, StatusCodes.SUCCESS, "Poll fetched successfully.", poll);
    }
    
    public ApiResponse createPoll(PollRequest pollRequest) {
        Poll poll = new Poll();
        poll.setPollId(pollRequest.getPollId());

        // Validate poll type
        for (PollQuestionRequest question : pollRequest.getPollContent()) {
            String type = question.getQuestionType();
            if (!type.equals("SINGLE_OPTION") && !type.equals("INPUT") && !type.equals("MULTIPLE_OPTIONS"))
                return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid question type");
        }

        // Convert PollRequest to Poll
        pollRequest.getPollContent().forEach(question -> {
            PollQuestion q = new PollQuestion(
                question.getQuestionId(), 
                question.getQuestionTitle(), 
                question.getQuestionBrief(), 
                question.getQuestionType(), 
                question.isCompulsory(), 
                question.getOptions().stream()
                    .map(option -> {
                        PollOption opt = new PollOption(option.getOptionId(), option.getOptionText());
                        return opt;
                    })
                    .collect(Collectors.toList()));
                    
            poll.addQuestion(q);
        });

        try {
            pollRepository.save(poll);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not save poll.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Poll created successfully.");
    }
}
