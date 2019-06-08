/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.security;

import com.accelerate.dash.model.Admin;
import com.accelerate.dash.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Service used by authentication providers to get the user details
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        UserPrincipal userPrincipal = UserPrincipal.create(admin);

        return userPrincipal;
    }

    // Used by JwtAuthenticationFilter
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Admin admin = adminRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id " + id));
        UserPrincipal userPrincipal = UserPrincipal.create(admin);

        return userPrincipal;
    }
}
