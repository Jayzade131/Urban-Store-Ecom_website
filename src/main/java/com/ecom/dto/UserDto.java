package com.ecom.dto;

import com.ecom.constants.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private Long userid;
	private String email;
	private String name;
	private UserRole role;
	
}
