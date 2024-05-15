package com.ecom.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecom.dto.ReviewDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "review_table")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long rating;
	
	@Lob
	private String description;
	
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] img;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Users users;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	public ReviewDto getReviewDto() {
		ReviewDto reviewDto=new ReviewDto();
		reviewDto.setId(id);
		reviewDto.setRating(rating);
		reviewDto.setDescription(description);
		reviewDto.setReturnedImg(img);
		reviewDto.setProductId(product.getId());
		reviewDto.setUserId(users.getId());
		reviewDto.setUsername(users.getName());
		return reviewDto;
	}
}
