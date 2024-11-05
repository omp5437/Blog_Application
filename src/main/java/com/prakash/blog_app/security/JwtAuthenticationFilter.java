package com.prakash.blog_app.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prakash.blog_app.utils.JwtTokenHelper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Autowired
	JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			                        HttpServletResponse response, 
			                        FilterChain filterChain)
			                       throws ServletException, IOException {

		logger.info("doFilterInternal Called");
		
		String requestToken=request.getHeader("Authorization");
		String userName=null;
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			token=requestToken.substring(7);
			 try {
			 userName = jwtTokenHelper.getUserNameFromToken(token);
			 logger.info("Jwt userName {}",userName);
			 }catch(IllegalArgumentException e) {
				 logger.info("Illegal Argument while fetching the username !!");
	                e.printStackTrace();
	            } catch (ExpiredJwtException e) {
	                logger.info("Given jwt token is expired !!");
	                e.printStackTrace();
	            } catch (MalformedJwtException e) {
	                logger.info("Some changed has done in token !! Invalid Token");
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }	
			
		}else {
			logger.info("Invalid header info");
		}
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
			
			boolean validateToken=jwtTokenHelper.validateToken(token, userDetails);
			
			if(validateToken) {
				 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);

	            } else {
	                logger.info("Validation fails !!");
	            }
		}
		filterChain.doFilter(request, response);	
	}

}
