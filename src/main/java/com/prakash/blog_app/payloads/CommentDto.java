package com.prakash.blog_app.payloads;

import java.util.Date;

import com.prakash.blog_app.entities.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CommentDto {
	private int id;
	private String content;
	private Date commentDate;
	private UserDto user;
//	private PostDto postDto;

}
