package com.svl.servicebase.jwt;

import com.svl.servicebase.entity.PersonCredentials;
import com.svl.servicebase.exception.SecurityBadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${spring.security.jwt.secret}")
    private String secret;
    @Value("${spring.security.jwt.expired}")
    private long validityInMilliseconds;
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(PersonCredentials personCredentials) {
        Claims claims = Jwts.claims().setSubject(personCredentials.getLogin());
        claims.put("roles", personCredentials.getRoles().stream().map(Enum::toString).toList());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public Authentication authenticate(String token) {
        String login = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        if (!userDetails.isEnabled())
            throw new SecurityBadRequestException("That account is not enabled");

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

     public String getUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(6).trim();
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                log.info("IN validateToken - JWT token has expired");
                return false;
            }
            log.info("IN validateToken - JWT token valid");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("IN validateToken  - expired or invalid JWT token");
            return false;
        }
    }
}
