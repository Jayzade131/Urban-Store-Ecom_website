package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
	List<Product> findAllByNameContaining(String name);

}
