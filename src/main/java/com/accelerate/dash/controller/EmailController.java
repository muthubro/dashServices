/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 07 June 2019
 * Modified Date	: 07 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/api/email/{name}")
    public ApiResponse sendEmail(@PathVariable("name") String name) {
        return emailService.sendEmail(name);
    }
}
