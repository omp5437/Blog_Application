package com.prakash.blog_app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prakash.blog_app.entities.User;
import com.prakash.blog_app.exceptions.ResourceNotFoundException;
import com.prakash.blog_app.payloads.UserDto;
import com.prakash.blog_app.repositories.UserRepository;
import com.prakash.blog_app.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository){
		this.userRepository=userRepository;
	}

	@Override
	public UserDto createUser(UserDto userdto) {
		User user=new User();
		user=userDtoToUser(userdto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser=userRepository.save(user);
		
		return userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, int userId) {
		User user=userRepository.findById(userId)
				  .orElseThrow(()->new ResourceNotFoundException("User ", "userId ", userId));
			user.setName(userdto.getName());
			user.setEmail(userdto.getEmail());
			user.setPassword(passwordEncoder.encode(userdto.getPassword()));

			User updatedUser=userRepository.save(user);
			return userToUserDto(updatedUser);
		
	}

	@Override
	public UserDto getUser(int userId) {

		User user=userRepository.findById(userId)
				                .orElseThrow(()->new ResourceNotFoundException("User ", "userId ", userId));
				                
		
		return userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
//		Pageable pageable= PageRequest.of(0,2);
		List<User> users=userRepository.findAll();
		
		List<UserDto> userDtos=users.stream()
				                    .map(user-> userToUserDto(user))
				                    .collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {
		
		User user=userRepository.findById(userId)
				                .orElseThrow(()-> new ResourceNotFoundException("User ", "userId", userId));
		userRepository.deleteById(userId);	
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto= modelMapper.map(user,UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//	    userDto.setEmail(user.getEmail());
//	    userDto.setPassword(user.getPassword());
	    return userDto;	
	}
	
	public User userDtoToUser(UserDto userDto) {
		User user=modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}

}
