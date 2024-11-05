package com.prakash.blog_app.payloads;

import lombok.Data;

@Data
public class JwtRequest {

	private String email;
	private String password;
}
