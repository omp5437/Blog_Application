package com.prakash.blog_app.services;

import java.util.List;

import com.prakash.blog_app.payloads.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto userdto);
	UserDto updateUser(UserDto userdto, int userId);
	UserDto getUser(int userId);
	List<UserDto> getAllUsers();
	
	void deleteUser(int userId);

}
