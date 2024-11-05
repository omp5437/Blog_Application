package com.prakash.blog_app.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenHelper {
	private static final long JWT_TOKEN_VALIDITY=5*60*60;
	private String secret="jwttokenkey";
	private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public String getUserNameFromToken(String token){
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }

	    //for retrieving any information from token we will need the secret key
	    private Claims getAllClaimsFromToken(String token) {
	         return Jwts.parserBuilder().setSigningKey(secretKey).        		 
	        		 build().
	        		 parseClaimsJws(token).
	        		 getBody();
	    }

	    //check if the token has expired
	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }

	    //generate token for user
	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return doGenerateToken(claims, userDetails.getUsername());
	    }

	    //while creating the token -
	    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	    //2. Sign the JWT using the HS512 algorithm and secret key.
	    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	    //   compaction of the JWT to a URL-safe string
	    private String doGenerateToken(Map<String, Object> claims, String subject) {

	    	Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + JWT_TOKEN_VALIDITY*1000);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(now)
	                .setExpiration(expiryDate).signWith(secretKey)                
	                .compact();
	    }

	    //validate token
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUserNameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	
  
} 
