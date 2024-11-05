package com.prakash.blog_app.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.blog_app.payloads.ApiResponse;
import com.prakash.blog_app.payloads.UserDto;
import com.prakash.blog_app.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	Logger logger= LoggerFactory.getLogger(UserController.class);

	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		 UserDto response=userService.createUser(userDto);
		 logger.info("Create User called");
		 logger.info(response.toString());
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int userId){
		UserDto response=userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") int uid){
		UserDto response=userService.getUser(uid);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> response=userService.getAllUsers();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
		ApiResponse apiResponse=new ApiResponse("User deleted Successfully", false);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
    }
	

}
