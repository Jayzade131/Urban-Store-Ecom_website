package com.ecom.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.CartItems;
@Repository
public interface CartItemRepo extends JpaRepository<CartItems, Long> {

	Optional<CartItems> findByProductIdAndOrderIdAndUsersId(Long productId, Long orderId, Long usersId);

	

}
