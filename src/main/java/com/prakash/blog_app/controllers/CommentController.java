package com.prakash.blog_app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.blog_app.payloads.ApiResponse;
import com.prakash.blog_app.payloads.CommentDto;
import com.prakash.blog_app.services.CommentService;

@RestController
@RequestMapping("api/")
@Slf4j
public class CommentController {
	
	@Autowired
	CommentService commentService;

	Logger logger= LoggerFactory.getLogger(CommentController.class);
	
	@PostMapping("/user/{userId}/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			                                        @PathVariable int postId,
			                                        @PathVariable int userId){
		
		CommentDto createdComment=commentService.createComment(commentDto,postId,userId);

		logger.info(createdComment.toString());
		
		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
		
		commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!",true), HttpStatus.CREATED);
		
	}

}
