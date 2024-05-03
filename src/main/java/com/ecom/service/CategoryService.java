package com.ecom.service;

import java.util.List;

import com.ecom.dto.CategoryDto;
import com.ecom.entity.Category;

public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);
	
	public List<Category> getAllCategory();
		
}
