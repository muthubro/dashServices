package com.example.dash.service;

import com.example.dash.model.Admin;
import com.example.dash.payload.ApiResponse;
import com.example.dash.payload.ErrorResponse;
import com.example.dash.payload.LoginRequest;
import com.example.dash.payload.SignupRequest;
import com.example.dash.payload.SuccessResponse;
import com.example.dash.repository.AdminRepository;
import com.example.dash.utility.ValidationUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private AdminRepository adminRepository;

    public ApiResponse signUp(SignupRequest signupRequest) {
        // Validate name
        String name = signupRequest.getName();
        name = name.trim();
        name = name.replaceAll("( )+", " ");
        if (!validationUtility.validateName(name))
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid name");
        
        // Validate username
        String username = signupRequest.getUsername();
        username = username.trim();
        if (!validationUtility.validateMobile(username))
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid mobile number");
        if (adminRepository.findByUsername(username) != null) 
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Mobile number is already taken");

        // Hash the password. BCrypt uses built-in salt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        adminRepository.save(new Admin(name, username, passwordEncoder.encode("password")));
        
        return new SuccessResponse(true, StatusCodes.SUCCESS, "Signup successful");
    }

    public ApiResponse login(LoginRequest loginRequest) {
        // Validate username
        String username = loginRequest.getUsername();
        username = username.trim();
        if (!validationUtility.validateMobile(username))
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid mobile number");

        String password = loginRequest.getPassword();

        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) return new ErrorResponse(false, StatusCodes.MISSING_VALUE, "No user found for the given username");

        // Always use encoder.matches() because of the salt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, admin.getPassword()))
            return new SuccessResponse(true, StatusCodes.SUCCESS, "Login successful");

        return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Wrong username or password");
    }
}
