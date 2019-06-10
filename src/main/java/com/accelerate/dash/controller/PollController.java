/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */

package com.accelerate.dash.controller;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.PollRequest;
import com.accelerate.dash.service.PollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping("/{id}")
    @ResponseBody
    public ApiResponse getPoll(@PathVariable("id") String id) {
        return pollService.getPoll(id);
    }

    @PostMapping("/create")
    @ResponseBody
    public ApiResponse createPoll(@RequestBody PollRequest pollRequest) {
        return pollService.createPoll(pollRequest);
    }
}
