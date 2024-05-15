package com.ecom.service;

import com.ecom.dto.FAQDto;

public interface FAQService {

	public FAQDto insertFAQ(Long productId,FAQDto faqDto);
}
