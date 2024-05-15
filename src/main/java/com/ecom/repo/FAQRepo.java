package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.FAQ;

public interface FAQRepo extends JpaRepository<FAQ, Long> {

}
