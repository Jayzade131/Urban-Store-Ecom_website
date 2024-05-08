package com.ecom.controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.AuthRequest;
import com.ecom.dto.SignupRequest;
import com.ecom.dto.UserDto;
import com.ecom.entity.Users;
import com.ecom.repo.UserRepo;
import com.ecom.security.JwtHelper;
import com.ecom.service.auth.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	private final UserRepo userRepo;

	private final AuthService authService;
	
	private final JwtHelper jwtHelper;
	
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";

	@PostMapping("/auth")
	public void createAuthenticationToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect Username and Password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		Optional<Users> optionaluser = userRepo.findFirstByEmail(userDetails.getUsername());

		final String jwt = jwtHelper.generateToken(userDetails.getUsername());
		
		if(optionaluser.isPresent())
		{
			response.getWriter().write(new JSONObject().put("userid", optionaluser.get().getId())
					.put("role", optionaluser.get().getRole()).toString());
			
		}
		
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization,X-PINGOTHER,Origin,"+
		"X-requested-With,Content-Type,Accept, X-Custom-header");

		response.addHeader(HEADER_STRING, TOKEN_PREFIX +jwt);

	}
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest)
	{
		if(authService.hasUserWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("User Already Exists",HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto userdto = authService.createUser(signupRequest);
		return new ResponseEntity<>(userdto, HttpStatus.OK);
	}
	
	
}
