package com.website.apnaStore.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.website.apnaStore.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET_KEY = "b87ec2a5c4c97de2f2c61635aba4fb4f30f5ace5ed0ed0389004220ac59eab2c";

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);

    }

    public boolean isValid(String token,UserDetails user){
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){

        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
        
    }

    public String generateToken(User user){

        String token = Jwts.builder()
                            .subject(user.getUsername())
                            .issuedAt(new Date(System.currentTimeMillis()))
                            .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                            .signWith(getSigningKey())
                            .compact();

        return token;
    }

    private SecretKey getSigningKey() {

        byte[] keyByte = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
