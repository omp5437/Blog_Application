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
import org.springframework.web.bind.annotation.RestController;

import com.prakash.blog_app.payloads.ApiResponse;
import com.prakash.blog_app.payloads.CategoryDto;
import com.prakash.blog_app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService; 
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory=categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto,
			                                          @PathVariable int catId){
		CategoryDto updatedCategory=categoryService.updateCategory(categoryDto,catId);
		
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int catId){
		categoryService.deleteCategory(catId);
		
		ApiResponse apiResponse=new ApiResponse("Category Deleted Successfully", true);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);	
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int catId){
		CategoryDto fetchedCategory=categoryService.getCategory(catId);
		
		return new ResponseEntity<CategoryDto>(fetchedCategory, HttpStatus.OK);	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categoryDtos=categoryService.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);	
	}
	
	
	
	

}
