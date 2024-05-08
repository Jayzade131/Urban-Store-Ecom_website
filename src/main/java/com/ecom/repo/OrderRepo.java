package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.constants.OrderStatus;
import com.ecom.entity.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

	Order findByUsersIdAndOrderStatus(Long id, OrderStatus orderstatus);

}
