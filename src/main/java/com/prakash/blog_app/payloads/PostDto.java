package com.prakash.blog_app.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prakash.blog_app.entities.Category;
import com.prakash.blog_app.entities.Comment;
import com.prakash.blog_app.entities.User;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostDto {
	
    private int postId;
	private String postTitle;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private List<CommentDto> comment;
}
