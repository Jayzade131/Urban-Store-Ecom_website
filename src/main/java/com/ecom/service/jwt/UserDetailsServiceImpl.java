package com.ecom.service.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecom.entity.Users;
import com.ecom.repo.UserRepo;
@Component
public class UserDetailsServiceImpl  implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<Users> optionalUsers=	userRepo.findFirstByEmail(username);
	
	if(optionalUsers.isEmpty()) throw new UsernameNotFoundException("username not found ",null);
		return new User(optionalUsers.get().getEmail(),optionalUsers.get().getPassword(), new ArrayList<>());
	}

}
