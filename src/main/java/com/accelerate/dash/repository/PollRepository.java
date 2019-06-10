/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import java.util.Optional;

import com.accelerate.dash.model.Poll;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {

    Optional<Poll> findByPollId(String pollId);;
}
