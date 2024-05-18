package com.ecom.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.constants.OrderStatus;
import com.ecom.entity.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

	Order findByUsersIdAndOrderStatus(Long id, OrderStatus orderstatus);
	
	List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatuslist);

	List<Order> findByUsersIdAndOrderStatusIn(Long id, List<OrderStatus> orderstatus);

	List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus stauts);

	Long countByOrderStatus(OrderStatus status);
}
