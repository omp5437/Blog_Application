package com.prakash.blog_app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakash.blog_app.entities.Category;
import com.prakash.blog_app.exceptions.ResourceNotFoundException;
import com.prakash.blog_app.payloads.CategoryDto;
import com.prakash.blog_app.repositories.CategoryRepository;
import com.prakash.blog_app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		Category category=modelMapper.map(catDto,  Category.class);
		Category createdCategory=categoryRepository.save(category);
		
		return modelMapper.map(createdCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto, int catId) {
		Category category=categoryRepository.findById(catId)
				          .orElseThrow(()->new ResourceNotFoundException("Category ","id",catId));
		category.setCategoryName(catDto.getCategoryName());
		category.setCategoryDescription(catDto.getCategoryDescription());
		
	    Category updatedCategory=categoryRepository.save(category);
		
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int catId) {
		Category category=categoryRepository.findById(catId)
		          .orElseThrow(()->new ResourceNotFoundException("Category ","id",catId));
		categoryRepository.deleteById(catId);		
	}

	@Override
	public CategoryDto getCategory(int catId) {
		Category category=categoryRepository.findById(catId)
		          .orElseThrow(()->new ResourceNotFoundException("Category ","id",catId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories=categoryRepository.findAll();
		return categories.stream()
		          .map(category-> modelMapper.map(category, CategoryDto.class))
		          .collect(Collectors.toList());	
	}

}
