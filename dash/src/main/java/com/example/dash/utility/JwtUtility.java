/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.example.dash.utility;

import java.util.Date;

import com.example.dash.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Utility to generate and validate JWTs
@Component
public class JwtUtility {

    @Value("${app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${app.security.jwtExpiration}")
    private Long jwtExpiration;

    public String generateToken(Authentication authentication) {
        UserPrincipal admin = (UserPrincipal) authentication.getPrincipal();
        Long id = admin.getId();

        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpiration);

        String jwt = Jwts.builder()
                        .setSubject(Long.toString(id))
                        .setIssuedAt(new Date())
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS512, jwtSecret)
                        .compact();        
        return jwt;
    }

    public Long getIdFromJwt(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
