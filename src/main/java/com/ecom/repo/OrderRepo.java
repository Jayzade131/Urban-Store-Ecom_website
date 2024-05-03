package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Order;

public interface OrderRepo extends JpaRepository<Order,Long> {

}
