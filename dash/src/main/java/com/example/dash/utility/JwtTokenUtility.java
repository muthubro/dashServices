package com.example.dash.utility;

import java.util.Date;

import com.example.dash.model.Admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtility {

    @Value("${app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${app.security.jwtExpiration}")
    private Long jwtExpiration;

    public String generateToken(Admin admin) {
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

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
