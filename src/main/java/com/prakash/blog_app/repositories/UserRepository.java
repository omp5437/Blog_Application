package com.prakash.blog_app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prakash.blog_app.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String username);
	Page<User> findAll(Pageable p);
	

}
