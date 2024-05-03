package com.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.CategoryDto;
import com.ecom.entity.Category;
import com.ecom.repo.CategoryRepo;
import com.ecom.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category createCategory(CategoryDto categoryDto) {

		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());

		return categoryRepo.save(category);
	}
	
	public List<Category> getAllCategory()
	{
		return categoryRepo.findAll();
		
	}
}
