package com.prakash.blog_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prakash.blog_app.entities.Comment;
import com.prakash.blog_app.entities.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByPost(Post post);

}
