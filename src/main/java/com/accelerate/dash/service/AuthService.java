/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 08 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import javax.servlet.http.HttpServletRequest;

import com.accelerate.dash.model.Admin;
import com.accelerate.dash.model.Role;
import com.accelerate.dash.model.RoleName;
import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.LoginRequest;
import com.accelerate.dash.payload.LoginResponse;
import com.accelerate.dash.payload.LoginWithDashRequest;
import com.accelerate.dash.payload.SignupRequest;
import com.accelerate.dash.payload.SuccessResponse;
import com.accelerate.dash.repository.AdminRepository;
import com.accelerate.dash.repository.RoleRepository;
import com.accelerate.dash.utility.JwtUtility;
import com.accelerate.dash.utility.ValidationUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private RoleRepository roleRepository;

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
        if (adminRepository.findByUsername(username).isPresent()) 
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Mobile number is already taken");

        // Hash the password. BCrypt uses built-in salt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Set role as admin
        Role role = roleRepository.findByName(RoleName.ROLE_ADMIN);

        Admin admin = new Admin(name, username, passwordEncoder.encode("password"));
        admin.setRole(role);
        adminRepository.save(admin);
        
        return new SuccessResponse(true, StatusCodes.SUCCESS, "Signup successful");
    }

    public ApiResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        // Validate username
        String username = loginRequest.getUsername();
        username = username.trim();
        if (!validationUtility.validateMobile(username))
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Invalid mobile number");

        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtility.generateToken(authentication);
        return new LoginResponse(true, StatusCodes.SUCCESS, "Login successful", jwt);
    }

    public String loginWithDash(LoginWithDashRequest request) {
        String username = request.getUsername();
        username = username.trim();
        if (!validationUtility.validateMobile(username))
            return null;

        String password = request.getPassword();

        if (password.equals("000"))
            return jwtUtility.generateToken(username);

        else return null;
    }
}
