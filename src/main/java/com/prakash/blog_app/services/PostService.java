package com.prakash.blog_app.services;

import java.util.List;

import com.prakash.blog_app.entities.User;
import com.prakash.blog_app.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	PostDto updatePost(PostDto postDto,int postId);
	
	void deletePost(int postId);
	
	List<PostDto> getPostByUser(int userId,int pagenumber,int pagesize,String sortby,String sortDir);
	
	List<PostDto> getPostByCategory(int categoryId);
	
	PostDto getPost(int postId);
	
	List<PostDto> getAllPost(int pagenumber,int pagesize,String sortby,String sortDir);
	
	List<PostDto> searchPosts(String keyword);
	
	
	

}
