package com.prakash.blog_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prakash.blog_app.entities.User;
import com.prakash.blog_app.exceptions.ResourceNotFoundException;
import com.prakash.blog_app.repositories.UserRepository;
import com.prakash.blog_app.services.UserService;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
     
    @Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(username);
		
		if(user==null) 
		 throw new ResourceNotFoundException("User ", "username: "+username,0 );
		
		return user;
	}
	

}
