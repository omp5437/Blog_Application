package com.prakash.blog_app.services;

import java.util.List;

import com.prakash.blog_app.payloads.CommentDto;

public interface CommentService {
	
   CommentDto createComment(CommentDto commentDto,int postId,int userId);
   
   void deleteComment(int commentId);
   
   CommentDto getComment(int commentId);
   
   
   
   
   

}
