package com.prakash.blog_app.services;

import java.util.List;

import com.prakash.blog_app.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto catDto);
	
	CategoryDto updateCategory(CategoryDto catDto, int catId);
	
	void deleteCategory(int catId);
	
	CategoryDto getCategory(int catId);
	
	List<CategoryDto> getAllCategories();

}
