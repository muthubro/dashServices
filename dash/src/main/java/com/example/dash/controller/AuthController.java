package com.example.dash.controller;

import javax.validation.Valid;

import com.example.dash.model.Admin;
import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.ErrorResponse;
import com.example.dash.payload.LoginRequest;
import com.example.dash.payload.SignupRequest;
import com.example.dash.payload.SuccessResponse;
import com.example.dash.repository.AdminRepository;
import com.example.dash.utility.ValidationUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignupRequest signupRequest) {
        String name = signupRequest.getName();
        name = name.trim();
        name = name.replaceAll("( )+", " ");
        if (!validationUtility.validateName(name))
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid name"));
        
        String username = signupRequest.getUsername();
        username = username.trim();
        if (!validationUtility.validateMobile(username))
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid mobile number"));

        if (adminRepository.findByUsername(username) != null) 
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Mobile number is already taken"));

        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        adminRepository.save(new Admin(name, username, "password"));
        
        return ResponseEntity.ok(new SuccessResponse(true, StatusCodes.SUCCESS, "Signup successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        username = username.trim();
        if (!validationUtility.validateMobile(username))
            return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid mobile number"));

        String password = loginRequest.getPassword();

        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.MISSING_VALUE, "No user found for the given username"));

        if (admin.getPassword().equals(password))
            return ResponseEntity.ok(new SuccessResponse(true, StatusCodes.SUCCESS, "Login successful"));

        return ResponseEntity.ok(new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Wrong username or password"));
    }
}
