package com.prakash.blog_app.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.blog_app.entities.Comment;
import com.prakash.blog_app.payloads.ApiResponse;
import com.prakash.blog_app.payloads.PostDto;
import com.prakash.blog_app.services.PostService;

@RestController
@RequestMapping("api/")
public class PostController {
	@Autowired
	PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			                                  @PathVariable int userId,
			                                  @PathVariable int categoryId){
	    PostDto createdPost=postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, 
			                                  @PathVariable int postId){
	PostDto updatedPost=	postService.updatePost(postDto, postId);
	return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
		postService.deletePost( postId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(
    		@PathVariable int userId,
    		@RequestParam(value="pageNumber",defaultValue="0",required=false) int pagenumber,
    		@RequestParam(value="pageSize", defaultValue="5",required=false) int pagesize,
    		@RequestParam(value="sortBy", defaultValue="postId",required=false) String sortby,
    		@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir){
		List<PostDto> postDto= postService.getPostByUser(userId,pagenumber,pagesize,sortby,sortDir);
		
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
    	
    }
	
	@GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
		List<PostDto> postDto= postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);	
    }
	
	@GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId){
		PostDto postDto= postService.getPost(postId);
		
//		for(Comment comment:postDto.getComments()) {
//			System.out.println(comment);
//		}
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);	
    }
	
	@GetMapping("/post/")
    public ResponseEntity<List<PostDto>> getAllPost(
    		@RequestParam(value="pageNumber",defaultValue="0",required=false) int pagenumber,
    		@RequestParam(value="pageSize", defaultValue="5",required=false) int pagesize,
    		@RequestParam(value="sortBy", defaultValue="postId",required=false) String sortby,
    		@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir){
			
		List<PostDto> postDto= postService.getAllPost(pagenumber,pagesize,sortby,sortDir);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);	
    }
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
		List<PostDto> postDtos=postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	} 

}
