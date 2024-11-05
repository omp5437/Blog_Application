package com.prakash.blog_app.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	private int id;
	@NotEmpty
	@Size(min=5 ,max=20)
	private String categoryName;
	@Size(min=10 ,max=50)
	private String categoryDescription;
}
