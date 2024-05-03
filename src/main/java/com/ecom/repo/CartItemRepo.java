package com.ecom.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.CartItems;

public interface CartItemRepo extends JpaRepository<CartItems, Long> {

}
