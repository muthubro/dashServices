/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.example.dash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.LoginRequest;
import com.example.dash.payload.SignupRequest;
import com.example.dash.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ApiResponse signUp(@Valid @RequestBody SignupRequest signupRequest) {
        return authService.signUp(signupRequest);
    }

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return authService.login(loginRequest, request);
    }
}
