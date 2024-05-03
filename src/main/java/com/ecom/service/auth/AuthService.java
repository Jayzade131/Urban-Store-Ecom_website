package com.ecom.service.auth;

import com.ecom.dto.SignupRequest;
import com.ecom.dto.UserDto;

public interface AuthService {
	public UserDto createUser(SignupRequest signupRequest);

	public Boolean hasUserWithEmail(String email);

}
