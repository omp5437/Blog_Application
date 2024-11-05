package com.prakash.blog_app.payloads;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtAuthResponse {	
	private String jwtToken;
	private String username;	
}
