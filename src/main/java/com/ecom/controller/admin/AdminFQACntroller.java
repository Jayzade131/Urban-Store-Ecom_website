package com.ecom.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.FAQDto;
import com.ecom.service.FAQService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminFQACntroller {
	@Autowired
	private FAQService faqService;
		
		@PostMapping("/faq/{productId}")
		public ResponseEntity<FAQDto> CreateFAQ(@PathVariable Long productId,@RequestBody FAQDto faqDto) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(faqService.insertFAQ(productId, faqDto));
		}
}
