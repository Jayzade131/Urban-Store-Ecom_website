package com.ecom.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.FAQDto;
import com.ecom.entity.FAQ;
import com.ecom.entity.Product;
import com.ecom.repo.FAQRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.service.FAQService;
@Service
public class FAQServiceImpl implements FAQService {
	@Autowired
	private FAQRepo faqRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	public FAQDto insertFAQ(Long productId,FAQDto faqDto)
	{
			Optional<Product> optionalProduct = productRepo.findById(productId);
			if(optionalProduct.isPresent())
			{
				FAQ faq=new FAQ();
				faq.setQue(faqDto.getQue());
				faq.setAns(faqDto.getAns());
				faq.setProduct(optionalProduct.get());
				
				return faqRepo.save(faq).getFAQDto();
				
			}
			
			return null;
	}
}
