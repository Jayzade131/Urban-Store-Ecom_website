package com.ecom.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CategoryDto;
import com.ecom.entity.Category;
import com.ecom.service.CategoryService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto)
	{
		Category createCategory = categoryService.createCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
		
	}
	@GetMapping("categories")
	public ResponseEntity<List<Category>> getAllCategory()
	{
		List<Category> allCategory = categoryService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}
}
