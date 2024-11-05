package com.prakash.blog_app.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	private String resource;
	private String fieldName;
	private long fieldValue;
	
	public ResourceNotFoundException(String resource, String fieldName,long fieldValue) {
		super(String.format("%s not found with %s : %s",resource,fieldName,fieldValue));
		this.resource=resource;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}

}
