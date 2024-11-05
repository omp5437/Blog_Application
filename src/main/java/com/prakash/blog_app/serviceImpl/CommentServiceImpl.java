package com.prakash.blog_app.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakash.blog_app.entities.Comment;
import com.prakash.blog_app.entities.Post;
import com.prakash.blog_app.entities.User;
import com.prakash.blog_app.exceptions.ResourceNotFoundException;
import com.prakash.blog_app.payloads.CommentDto;
import com.prakash.blog_app.repositories.CommentRepository;
import com.prakash.blog_app.repositories.PostRepository;
import com.prakash.blog_app.repositories.UserRepository;
import com.prakash.blog_app.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository comRepository;
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto,int postId,int userId) {
		User user=userRepo.findById(userId)
				          .orElseThrow(()->new ResourceNotFoundException("User ", "userId:", userId));
		
		Post post=postRepo.findById(postId)
		          .orElseThrow(()->new ResourceNotFoundException("Post ", "postid:", postId));
		Comment comment=modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		comment.setCommentDate(new Date());
		
		Comment newComment=comRepository.save(comment);	
		return modelMapper.map(newComment,CommentDto.class);
	}

//	@Override
////	public CommentDto updateComment(CommentDto commentDto, int commentId) {
////		Comment comment=comRepository.findById(commentId)
////				                     .orElseThrow(()->new ResourceNotFoundException("Comment ", "commentId:", commentId));
////		
////		comment.setContent(commentDto.getContent());
////		comment.setCommentDate(new Date());
////		
////		Comment updatedComment=comRepository.save(comment);
////		return modelMapper.map(updatedComment,CommentDto.class);
////	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment=comRepository.findById(commentId)
				                     .orElseThrow(()->new ResourceNotFoundException("Comment " , "commentId:", commentId));
		comRepository.deleteById(commentId);
	}

	@Override
	public CommentDto getComment(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<CommentDto> getAllComment() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<CommentDto> getCommentByPost(int postId) {
//		Post post=postRepo.findById(postId)
//				          .orElseThrow(()->new ResourceNotFoundException("Post ", "postid:", postId));
//		List<Comment> comments=comRepository.findByPost(post);
//		
//		return comments.stream().map((comment)->modelMapper.map(comment, CommentDto.class))
//				       .collect(Collectors.toList());
//	}

}
