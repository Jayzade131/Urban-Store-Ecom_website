package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.FAQ;

public interface FAQRepo extends JpaRepository<FAQ, Long> {

	List<FAQ> findAllByProductId(Long productId);

}
