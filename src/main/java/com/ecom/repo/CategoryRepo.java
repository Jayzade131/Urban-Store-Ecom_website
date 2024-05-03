package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
