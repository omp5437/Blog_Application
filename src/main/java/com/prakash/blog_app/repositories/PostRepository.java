package com.prakash.blog_app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prakash.blog_app.entities.Category;
import com.prakash.blog_app.entities.Post;
import com.prakash.blog_app.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Page<Post> findByUser(User user,Pageable p);
	List<Post> findByCategory(Category category);
	List<Post> searchByPostTitleContaining(String keyword);
	
}
