package com.ecom.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.constants.UserRole;
import com.ecom.entity.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Long>{
	
	Optional<Users> findFirstByEmail(String email);
	
	Users findByRole(UserRole userRole);
	
}
