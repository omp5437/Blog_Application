package com.prakash.blog_app.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prakash.blog_app.entities.Category;
import com.prakash.blog_app.entities.Comment;
import com.prakash.blog_app.entities.Post;
import com.prakash.blog_app.entities.User;
import com.prakash.blog_app.exceptions.ResourceNotFoundException;
import com.prakash.blog_app.payloads.PostDto;
import com.prakash.blog_app.repositories.CategoryRepository;
import com.prakash.blog_app.repositories.PostRepository;
import com.prakash.blog_app.repositories.UserRepository;
import com.prakash.blog_app.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	 @Autowired
     PostRepository postRepository;
	 
	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired
	 CategoryRepository categoryRepository;
	 
	 @Autowired
	 ModelMapper modelMapper;
	 
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		Post post= modelMapper.map(postDto, Post.class);
		
		User user=userRepository.findById(userId)
				                .orElseThrow(()->new ResourceNotFoundException("User ", "userId:", userId));
		
		Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ", "categoryId:", categoryId));

		post.setAddedDate(new Date());
		post.setImageName("profilePic.png");
		post.setUser(user);
		post.setCategory(category);
		
		Post createdPost=postRepository.save(post);
		
		return modelMapper.map(createdPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post=postRepository.findById(postId)
				                .orElseThrow(()->new ResourceNotFoundException("Post ", "postId:", postId));
		post.setContent(postDto.getContent());
		post.setPostTitle(postDto.getPostTitle());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=postRepository.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
		
		
	}

	@Override
	public void deletePost(int postId) {
		Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post ", "postId:", postId));

		postRepository.deleteById(postId);
		
	}

	@Override
	public List<PostDto> getPostByUser(int userId,int pagenumber,
			                           int pagesize,String sortby,String sortDir) {
		User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User ", "userId:", userId));
		Sort sort;
		
		if(sortDir.equalsIgnoreCase("desc")) 
		 sort=Sort.by(sortby).descending();
		else
		 sort=Sort.by(sortby).ascending();
		
		Pageable p=PageRequest.of(pagenumber, pagesize,sort);
		Page<Post> pagepost = postRepository.findByUser(user, p);
		List<Post> posts=pagepost.getContent();
		
      return  posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
		Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ", "categoryId:", categoryId));
        List<Post> posts=postRepository.findByCategory(category);
        
       return posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        		
	}

	@Override
	public PostDto getPost(int postId) {
		Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post ", "postId:", postId));
		
//		for(Comment comment:post.getComment()) {
//	       System.out.println(comment);
//		}
         
	  return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPost(int pagenumber,int pagesize,String sortby,String sortDir) {
		Sort sort;
		if(sortDir.equalsIgnoreCase("desc")) {
			sort=Sort.by(sortby).descending();
		}
		else
			sort=Sort.by(sortby).ascending();
		
		//for implementing pagination start
		Pageable p = PageRequest.of(pagenumber, pagesize,sort);
		
		Page<Post> pagePost = postRepository.findAll(p);
		
		List<Post> posts=pagePost.getContent(); 
		//for implementing pagination ends
		
		 
		return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=postRepository.searchByPostTitleContaining(keyword);
		return posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	}

}
